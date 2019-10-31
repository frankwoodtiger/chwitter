const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const resourcePath = path.resolve(__dirname, '../backend/src/main/resources/static');


module.exports = {
    mode: "production",
    entry: './src/js/index.js',
    output: {
        filename: 'app.js',
        // __dirname tells you the absolute path of the directory containing the currently executing file.
        path: resourcePath + '/js'
    },
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
};