
/** @type {import('tailwindcss').Config} */
export default {
  content: [
     "./index.html",
    "./src/**/*.{js,jsx,ts,tsx}",
    "./node_modules/flyonui/dist/js/*.js",
  ],
  theme: {
    extend: {},
  },
  plugins: [],
  flyonui: {
    themes: ["light", "dark", "gourmet", "corporate", "luxury", "soft"],
    vendors: true 
  }
}

