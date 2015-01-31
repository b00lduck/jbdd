({
    mainConfigFile: 'app/config.js',
    baseUrl: './app',
    dir: 'app-optimized',
    fileExclusionRegExp: /src|\.min\.|test|bower\.json|\.md|package\.json|examples|\.txt/,
    optimize: 'uglify2'
});
