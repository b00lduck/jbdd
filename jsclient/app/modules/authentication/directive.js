/*jslint node: true */
'use strict';

define(['angular-route', 'AuthenticationService'], function () {
    var AuthenticationDirective = function ($route, $location, AuthenticationService) {

        return {

            restrict: 'E',

            templateUrl: 'modules/authentication/templates/myLoginInfo.html',

            link: function (scope, element, attrs) {

                scope.logout = function () {
                    AuthenticationService.logout();
                };

                scope.getUsername = function () {
                    return AuthenticationService.getUsername();
                };

                scope.isLoggedIn = function () {
                    return AuthenticationService.isLoggedIn();
                };

                scope.getRoles = function () {
                    return AuthenticationService.getRoles();
                };

            }

        };

    };

    return ['$route', '$location', 'AuthenticationService', AuthenticationDirective];
});
