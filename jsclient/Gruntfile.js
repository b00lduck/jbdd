module.exports = function (grunt) {
    'use strict';

    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);
    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    function cacheClearMiddleware(req, res, next) {
        res.setHeader('Expires', 'Thu, 01 Jan 1970 00:00:00 GMT');
        res.setHeader('Pragma', 'no-cache');
        res.setHeader('Cache-Control', 'no-store');
        next();
    }

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
                    middleware: function (connect, options) {
                        return [cacheClearMiddleware, connect.static('app')];
                    }
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
                singleRun: false
            },
            unit_chrome: {
                configFile: 'karma.unit.conf.js',
                singleRun: false,
                browsers: ['Chrome']
            },
            unit_coverage: {
                configFile: 'karma.coverage.unit.conf.js',
                singleRun: false
            },
            unit_ci: {
                configFile: 'karma.coverage.unit.conf.js',
                singleRun: true
            }
        },

        requirejs: {

            compile: {
                options: {
                    mainConfigFile: 'app/config.js',
                    baseUrl: './app',
                    dir: 'app-optimized',
                    modules: [
                        {
                            name: 'app'
                        }],
                    logLevel: 0,
                    done: function (done, output) {
                        var duplicates = require('rjs-build-analysis').duplicates(output);

                        if (0 < Object.keys(duplicates).length) {
                            grunt.log.subhead('Duplicates found in requirejs build:');
                            for (var key in duplicates) {
                                grunt.log.error(duplicates[key] + ': ' + key);
                            }
                            return done(new Error('r.js built duplicate modules, please check the excludes option.'));
                        } else {
                            grunt.log.success('No duplicates found!');
                        }

                        done();
                    }
                }
            }
        },

        wiredep: {
            task: {
                src: [
                    'test/test-config.js',
                    'app/config.js'
                ],
                options: {}
            }
        }

    });

    grunt.registerTask('test', [
        'karma:unit'
    ]);
    grunt.registerTask('test_coverage', [
        'karma:unit_coverage'
    ]);
    grunt.registerTask('test_browser', [
        'karma:unit_chrome'
    ]);
    grunt.registerTask('test_ci', [
        'karma:unit_ci'
    ]);

    grunt.registerTask('serve', [
        'bowerRequirejs', 'connect:develop', 'watch'
    ]);

    grunt.registerTask('serve-minimal', [
        'connect:develop', 'watch'
    ]);

    grunt.loadNpmTasks('grunt-bower-requirejs');
    grunt.loadNpmTasks('grunt-protractor-runner');
    grunt.loadNpmTasks('grunt-istanbul');
    grunt.loadNpmTasks('grunt-contrib-requirejs');

};
