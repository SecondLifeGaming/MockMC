import { defineConfig } from 'vitepress';

export default defineConfig({
  title: 'MockMC Reference & API Explorer',
  description: 'Version-Aware Minecraft Plugin Developer Reference and Compatibility Explorer',
  base: '/MockMC/',
  head: [
    ['meta', { 'http-equiv': 'Content-Security-Policy', content: "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; img-src 'self' data: https:; font-src 'self' data:; connect-src 'self';" }],
    ['meta', { name: 'referrer', content: 'no-referrer-when-downgrade' }],
    ['meta', { name: 'color-scheme', content: 'dark light' }]
  ],
  themeConfig: {
    siteTitle: 'MockMC',
    nav: [
      { text: 'Guide', link: '/guides/getting-started' },
      { text: 'Multi-Version Testing', link: '/guides/multi-version-testing' },
      { text: 'API Explorer', link: '/explorer/' },
      { text: 'Architecture', link: '/guides/architecture' },
      { text: 'GitHub', link: 'https://github.com/secondlifegaming/MockMC' }
    ],
    sidebar: [
      {
        text: 'Getting Started',
        items: [
          { text: 'Introduction & Setup', link: '/guides/getting-started' },
          { text: 'Your First MockMC Test', link: '/guides/first-test' }
        ]
      },
      {
        text: 'Testing Recipes',
        items: [
          { text: 'Multi-Version Testing (Paper & Folia)', link: '/guides/multi-version-testing' },
          { text: 'Player & Inventory Simulation', link: '/guides/player-simulation' }
        ]
      },
      {
        text: 'API & Compatibility Explorer',
        items: [
          { text: 'Interactive API Explorer', link: '/explorer/' },
          { text: 'Compatibility Diff Viewer', link: '/explorer/diff-viewer' }
        ]
      },
      {
        text: 'Engine Architecture',
        items: [
          { text: 'MetaMiner & Engine First', link: '/guides/architecture' },
          { text: 'Manifest Specification (RFC)', link: '/guides/manifest-specification' }
        ]
      }
    ],
    search: {
      provider: 'local'
    },
    socialLinks: [
      { icon: 'github', link: 'https://github.com/SecondLifeGaming/MockMC' }
    ],
    footer: {
      message: 'Released under the MIT License.',
      copyright: 'Copyright © 2026 MockMC Contributors'
    }
  }
});
