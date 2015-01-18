/*jslint node: true */
'use strict';

angular.module('jbddApp', [
    'Authentication',
    'Navigation',
    'Home',
    'Admin',
    'ngRoute',
    'ngCookies',
    'ngTouch',
    'pascalprecht.translate'
])

    .config(['$routeProvider', function ($routeProvider) {

        $routeProvider
            .when('/', {
                controller: 'LoginController',
                templateUrl: 'modules/authentication/views/login.html',
                hideMenus: true
            })

            .when('/home', {
                controller: 'HomeController',
                templateUrl: 'modules/home/views/home.html'
            })

            .when('/admin/:resource', {
                controller: 'AdminListController',
                templateUrl: 'modules/admin/views/list.html'
            })

            .when('/admin/:resource/:id', {
                controller: 'AdminEditController',
                templateUrl: function (params) { return 'modules/admin/views/edit_' + params.resource + '.html'; }
            })

            .otherwise({redirectTo: '/'});
    }])

    .config(['$translateProvider', function($translateProvider){

        $translateProvider.useStaticFilesLoader({
            prefix: 'i18n/',
            suffix: '.json'
        });

        $translateProvider.preferredLanguage('en_GB');

    }])


    .run(['$rootScope', '$location', 'AuthenticationService',
        function ($rootScope, $location, AuthenticationService) {

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
        }]);
