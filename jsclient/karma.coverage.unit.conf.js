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
            stripPrefix: 'app/'
        },

        // list of files / patterns to load in the browser
        files: [
            {pattern: 'app/bower_components/jquery/dist/jquery.js', included: false},
            {pattern: 'app/bower_components/angular/angular.js', included: false},
            {pattern: 'app/bower_components/angular-route/angular-route.js', included: false},
            {pattern: 'app/bower_components/angularAMD/angularAMD.js', included: false},
            {pattern: 'app/bower_components/angular-cookies/angular-cookies.js', included: false},
            {pattern: 'app/bower_components/angular-touch/angular-touch.js', included: false},
            {pattern: 'app/bower_components/angular-ui-grid/ui-grid.js', included: false},
            {pattern: 'app/bower_components/angular-translate/angular-translate.js', included: false},
            {pattern: 'app/bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js', included: false},
            {pattern: 'app/bower_components/bootstrap/dist/js/bootstrap.js', included: false},
            {pattern: 'app/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js', included: false},
            {pattern: 'app/bower_components/angular-mocks/angular-mocks.js', included: false},

            {pattern: 'app/modules/**/*.js', included: false},
            {pattern: 'app/modules/**/*.html', included: false},
            {pattern: 'app/images/**/*.png', included: false},

            {pattern: 'app/app.js', included: false},

            {pattern: 'test_requirejs/**/*.unit.spec.js', included: false},

            'test_requirejs/test-config.js'
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
            'karma-phantomjs-launcher', 'karma-chrome-launcher', 'karma-jasmine', 'karma-ng-html2js-preprocessor', 'karma-requirejs'
        ],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false,

        colors: true,

        // level of logging
        // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
        logLevel: config.LOG_INFO,

        reporters: ['progress'],

        coverageReporter: {
            type: 'html',
            dir: 'coverage/'
        }

    });

};
