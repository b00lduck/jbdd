/*jslint node: true */
'use strict';

//define(['angular-translate', 'angularAMD', 'angular-route', 'AuthenticationModule', 'NavigationModule', 'HomeModule',
// 'AdminModule', 'angular-cookies', 'angular-translate'
define(['angularAMD', 'angular-route', 'angular-translate', 'angular-translate-loader-static-files', 'bootstrap'


], function (angularAMD) {

    //var app = angular.module('jbddApp',
    // ['Authentication','Navigation','Home','Admin','ngRoute','ngCookies','ngTouch','pascalprecht.translate']);

    var app = angular.module('jbddApp', ['ngRoute', 'pascalprecht.translate']);

    app.config(function ($routeProvider) {

        $routeProvider

            .when('/', angularAMD.route({
                controller: 'LoginController',
                templateUrl: 'modules/authentication/controllers/views/login.html',
                hideMenus: true
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
    });


    app.config(function ($translateProvider) {

            $translateProvider.useStaticFilesLoader({
                prefix: 'i18n/',
                suffix: '.json'
            });

            $translateProvider.preferredLanguage('en_GB');

        });
    /*
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
     */
//    require(['domReady!'], function (document) {
//        try {
    return angularAMD.bootstrap(app);
//        } catch (e) {
//            console.error(e.stack || e.message || e);
//            return null;
//        }
//    }
//);

});
