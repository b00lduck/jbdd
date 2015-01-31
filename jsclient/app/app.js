/*jslint node: true */
'use strict';

define(['angularAMD',
    'angular-route',
    'angular-translate',
    'angular-translate-loader-static-files',
    'bootstrap'
], function (angularAMD) {

    var app = angular.module('jbddApp', ['ngRoute', 'pascalprecht.translate']);

    app.config(['$routeProvider', function ($routeProvider) {

        $routeProvider

            .when('/', angularAMD.route({
                controller: 'LoginController',
                templateUrl: 'modules/authentication/controllers/views/login.html'
            }))

            .when('/home', angularAMD.route({
                controller: 'HomeController',
                templateUrl: 'modules/home/controllers/views/home.html'
            }))

            .when('/admin/:resource', angularAMD.route({
                controller: 'AdminListController',
                templateUrl: 'modules/admin/controllers/views/list.html'
            }))

            .when('/admin/:resource/:id', angularAMD.route({
                controller: 'AdminEditController',
                templateUrl: function (params) { return 'modules/admin/controllers/views/edit_' + params.resource + '.html'; }
            }))

            .otherwise({redirectTo: '/'});
    }]);


    app.config(['$translateProvider', function ($translateProvider) {

            $translateProvider.useStaticFilesLoader({
                prefix: 'i18n/',
                suffix: '.json'
            });

        $translateProvider.preferredLanguage('en-GB');

    }]);

    return angularAMD.bootstrap(app);

});
