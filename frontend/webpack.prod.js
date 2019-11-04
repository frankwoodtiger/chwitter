const merge = require('webpack-merge');
const common = require('./webpack.common.js');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = merge(common, {
    mode: 'production',
    /*
        We encourage you to have source maps enabled in production, as they are useful for debugging as well as running
        benchmark tests. That said, you should choose one with a fairly quick build speed that's recommended for
        production use (see devtool). We'll use the source-map option in the production as opposed to the
        inline-source-map we used in the development:
     */
    devtool: 'source-map',
    plugins: [
        new MiniCssExtractPlugin({
            // Options similar to the same options in webpackOptions.output
            // all options are optional
            filename: '../css/app.css',
            ignoreOrder: false, // Enable to remove warnings about conflicting order
        }),
    ],
    module: {
        rules: [
            {
                test: /\.s[ac]ss$/i,
                use: [
                    MiniCssExtractPlugin.loader,
                    // Translates CSS into CommonJS
                    'css-loader',
                    // Compiles Sass to CSS
                    'sass-loader'
                ],
            },
        ],
    },
});