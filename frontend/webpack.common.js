/*
    While we will separate the production and development specific bits out, note that we'll still maintain a "common"
    configuration to keep things DRY. In order to merge these configurations together, we'll use a utility called
    webpack-merge. With the "common" configuration in place, we won't have to duplicate code within the
    environment-specific configurations.
 */
const path = require('path');
const resourcePath = path.resolve(__dirname, '../backend/src/main/resources/static');
const webpack = require("webpack");

module.exports = {
    entry: './src/js/index.js',
    output: {
        filename: 'app.js',
        // __dirname tells you the absolute path of the directory containing the currently executing file.
        path: resourcePath + '/js'
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        })
    ]
};