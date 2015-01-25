/*jslint node: true */
'use strict';

define(['angularAMD', 'angular-route', 'AuthenticationService'], function (angularAMD) {

    angularAMD.directive('myLoginInfo', ['$route', '$location', 'AuthenticationService',
        function ($route, $location, AuthenticationService) {

            return {

                restrict: 'E',

                templateUrl: 'modules/authentication/directives/templates/myLoginInfo.html',

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

        }

    ]);

});
