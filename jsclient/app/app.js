/*jslint node: true */
'use strict';

define(['angularAMD', 'angular-route'], function (angularAMD) {

    var app = angular.module('jbddApp', ['Authentication','Navigation','Home','Admin','ngRoute','ngCookies','ngTouch','pascalprecht.translate']);

    app.config(function ($routeProvider) {

        $routeProvider
            .when('/', angularAMD.route({
                controller: 'LoginController',
                templateUrl: 'modules/authentication/views/login.html',
                hideMenus: true
            }))

            .when('/home', angularAMD.route({
                controller: 'HomeController',
                templateUrl: 'modules/home/views/home.html'
            }))

            .when('/admin/:resource', angularAMD.route({
                controller: 'AdminListController',
                templateUrl: 'modules/admin/views/list.html'
            }))

            .when('/admin/:resource/:id', angularAMD.route({
                controller: 'AdminEditController',
                templateUrl: function (params) { return 'modules/admin/views/edit_' + params.resource + '.html'; }
            }))

            .otherwise({redirectTo: '/'});
    });

    app.config(function ($translateProvider) {

            $translateProvider.useStaticFilesLoader({
                prefix: 'i18n/',
                suffix: '.json'
            });

            $translateProvider.preferredLanguage('en_GB');

        });

    app.run(function ($rootScope, $location, AuthenticationService) {

            $rootScope.$on('$locationChangeStart', function (event, next, current) {
                // redirect to login page if not logged in
                if ('/' !== $location.path() && !AuthenticationService.isLoggedIn()) {
                    $location.path('/');
                }

                if ('/' === $location.path() && AuthenticationService.isLoggedIn()) {
                    $location.path('/home');
                }

                if ('' === $location.path() && AuthenticationService.isLoggedIn()) {
                    $location.path('/home');
                }

            });
        });

    return angularAMD.bootstrap(app);
});
