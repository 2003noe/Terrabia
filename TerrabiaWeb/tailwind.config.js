/** @type {import('tailwindcss').Config} */

export default {
  content: [
     "./index.html",
    "./src/**/*.{js,jsx,ts,tsx}",
    "./node_modules/flyonui/dist/**/*.js"
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require("flyonui"),    
    // require("flyonui/plugin")
   ],
   flyonui: {
    themes: ["light", "dark", "gourmet", "corporate", "luxury", "soft"],
    vendors: true 
  }
}


