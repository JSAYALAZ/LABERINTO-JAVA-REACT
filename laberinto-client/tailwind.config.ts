import type { Config } from "tailwindcss";

const config: Config = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  darkMode: "selector",
  theme: {
    extend: {
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },
      colors: {
        search: {
          light: "#f4f4f4",
          dark: "#2f2f2f",
        },
        input: {
          light: "#f4f4f0",
          dark: "#404040",
        },
        button: {
          light: "#d7d7d7",
          dark: "#676767",
        },
        background: {
          light: "#ffffff",
          dark: "#212121",
        },
        sidebar: {
          light: "#f9f9f9",
          dark: "#171717",
        },
        textPrimary: {
          light: "#000000",
          dark: "#ececec",
        },
        textSecondary: {
          light: "#676767",
          dark: "#9b9b9b",
        },
        
      },
    },
  },
  plugins: [],
};
export default config;
