const path = require('path');

module.exports = {
    entry: './src/js/index.js',
    output: {
        filename: 'app.js',
        // __dirname tells you the absolute path of the directory containing the currently executing file.
        path: path.resolve(__dirname, '../backend/src/main/resources/static/js')
    },
};