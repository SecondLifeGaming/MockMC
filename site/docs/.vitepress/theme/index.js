import DefaultTheme from 'vitepress/theme';
import VersionRangeBrowser from '../components/VersionRangeBrowser.vue';

export default {
  extends: DefaultTheme,
  enhanceApp({ app }) {
    app.component('VersionRangeBrowser', VersionRangeBrowser);
  }
};
