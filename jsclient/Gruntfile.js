module.exports = function (grunt) {
    'use strict';

    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);
    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);


    grunt.initConfig({
        bowerRequirejs: {
            target: {
                rjsConfig: 'app/config.js'
            }
        },
        connect: {
            options: {
                port: 9000,
                hostname: 'localhost'
            },
            develop: {
                options: {
                    port: 9000,
                    open: {
                        target: 'http://localhost:9000/'
                    },
                    base: ['.tmp', 'app'],
                    middleware: function (connect, options) //noinspection JSDeclarationsAtScopeStart
                    {
                        if (!Array.isArray(options.base)) {
                            options.base = [options.base];
                        }
                        var cacheClear = function (req, res, next) {
                            res.setHeader('Expires', 'Thu, 01 Jan 1970 00:00:00 GMT');
                            res.setHeader('Pragma', 'no-cache');
                            res.setHeader('Cache-Control', 'no-store');
                            next();
                        }, middlewares = [cacheClear], directory = (options.directory || options.base[options.base.length - 1]);
                        // Serve static files.
                        options.base.forEach(function (base) {
                            var items = connect.static(base);
                            middlewares.push(items);
                        });
                        // Make directory browse-able.
                        var items2 = connect.directory(directory);
                        middlewares.push(items2);

                        return middlewares;
                    }
                }
            }
        },
        less: {
            all: {
                options: {
                    compress: false
                },
                files: {
                    '.tmp/main/css/main.css': 'app/main/css/main.less'
                }
            }
        },
        watch: {
            options: { livereload: true },
            less: {
                files: ['app/main/**/*.less'],
                tasks: ['less']
            },
            all: {
                files: ['app/**', '!/app/bower_components/**']
            }
        },
        karma: {
            unit: {
                configFile: 'karma.unit.conf.js',
                singleRun: false,
            },
            unit_coverage: {
                configFile: 'karma.coverage.unit.conf.js',
                singleRun: false,
            },
            unit_requirejs: {
                configFile: 'karma.requirejs.unit.conf.js',
                singleRun: false,
            },
            unit_chrome: {
                configFile: 'karma.unit.conf.js',
                singleRun: false,
                browsers: [
                    'Chrome'
                ]
            },
            ci: {
                configFile: 'karma.unit.conf.js',
                singleRun: true
            }
        },

        protractor: {
            options: {
                configFile: 'node_modules/protractor/referenceConf.js', // Default config file
                keepAlive: true, // If false, the grunt process stops when the test fails.
                noColor: false, // If true, protractor will not use colors in its output.
                args: {
                    // Arguments passed to the command
                }
            },
            your_target: {   // Grunt requires at least one target to run so you can simply put 'all: {}' here too.
                options: {
                    configFile: 'protractor.conf.js', // Target-specific config file
                    args: {} // Target-specific arguments
                }
            }
        }

    });

    grunt.registerTask('test', [
        'karma:unit'
    ]);
    grunt.registerTask('test_coverage', [
        'karma:unit_coverage'
    ]);
    grunt.registerTask('test:debug', [
        'karma:unit_chrome'
    ]);
    grunt.registerTask('test:ci', [
        'karma:ci', 'coveralls'
    ]);
    grunt.registerTask('default', [
        'karma:unit'
    ]);
    grunt.registerTask('serve', [
        'less', 'bowerRequirejs', 'connect:develop', 'watch'
    ]);
    grunt.registerTask('serve-minimal', [
        'connect:develop', 'watch'
    ]);

    grunt.loadNpmTasks('grunt-bower-requirejs');

    grunt.loadNpmTasks('grunt-protractor-runner');

    grunt.loadNpmTasks('grunt-istanbul');


};
