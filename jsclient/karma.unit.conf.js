// Karma configuration
module.exports = function (config) {
    'use strict';
    config.set({
        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,

        // base path, that will be used to resolve files and exclude
        basePath: '.',

        // testing framework to use (jasmine/mocha/qunit/...)
        frameworks: ['jasmine', 'requirejs'],

        preprocessors: {
            'app/!(bower_components)/**/*.html': ['ng-html2js']
        },

        ngHtml2JsPreprocessor: {
            stripPrefix: 'app/',
            moduleName: 'precompiledTemplates'
        },

        // list of files / patterns to load in the browser
        files: [
            'app/bower_components/jquery/dist/jquery.js',
            'app/bower_components/angular/angular.js',
            'app/bower_components/angular-route/angular-route.js',
            'app/bower_components/angular-cookies/angular-cookies.js',
            'app/bower_components/angular-touch/angular-touch.js',
            'app/bower_components/angular-ui-grid/ui-grid.js',
            'app/bower_components/angular-translate/angular-translate.js',
            'app/bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
            'app/bower_components/bootstrap/dist/js/bootstrap.js',
            'app/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js',
            'app/bower_components/angular-mocks/angular-mocks.js',

            'app/!(bower_components)/**/admin.module.js',
            'app/!(bower_components)/**/*.js',
            'app/*.js',
            'app/!(bower_components)/**/*.html',

            'test/**/*.unit.spec.js'
        ],

        proxies: {
            '/images/': '/base/images/'
        },

        // list of files / patterns to exclude
        exclude: [
            'app/config.js'
        ],

        // web server port
        port: 7777,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: [
            'PhantomJS'
        ],

        // Which plugins to enable
        plugins: [
            'karma-phantomjs-launcher', 'karma-chrome-launcher', 'karma-jasmine', 'karma-ng-html2js-preprocessor'
        ],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false,

        colors: true,

        // level of logging
        // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
        logLevel: config.LOG_INFO,

        reporters: ['progress']

    });

};
