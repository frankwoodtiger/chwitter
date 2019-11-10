const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'development',
    watch: false, // TODO: need to think of a way to not stop the backend build
    /*
        inline-source-map - A SourceMap is added as a DataUrl to the bundle. Help with knowing which line in the
        original source file causing issue while debugging.
    */
    devtool: 'inline-source-map',
    module: {
        rules: [
            {
                test: /\.s[ac]ss$/i,
                use: [
                    'style-loader',
                    // Translates CSS into CommonJS
                    'css-loader',
                    // Compiles Sass to CSS
                    'sass-loader'
                ],
            },
        ],
    },
});