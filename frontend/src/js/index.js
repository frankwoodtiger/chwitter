import '../scss/style.scss';

// https://stackoverflow.com/questions/52376720/how-to-make-font-awesome-5-work-with-webpack
import '@fortawesome/fontawesome-free/js/all';

import * as Chweets from './chweets.js';
import * as AjaxUtils from './ajaxUtils';

// some discussion why we need this: https://github.com/webpack/webpack/issues/4258
// might be more clear: https://stackoverflow.com/questions/29080148/expose-jquery-to-real-window-object-with-webpack/29083197#29083197
// TODO: use expose-loader
import $ from 'jquery';
window.jQuery = $;
window.$ = $;
window.AjaxUtils = AjaxUtils;

Chweets.bindAll();