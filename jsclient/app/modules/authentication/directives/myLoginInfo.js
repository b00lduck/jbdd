/*jslint node: true */
'use strict';

define(['angularAMD', 'angular-route', 'AuthenticationService', 'angular-ui-bootstrap-bower'], function (angularAMD) {

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


                    scope.items = [
                        'The first choice!',
                        'And another choice for you.',
                        'but wait! A third!'
                    ];

                    scope.status = {
                        isopen: false
                    };

                    scope.toggleDropdown = function ($event) {
                        $event.preventDefault();
                        $event.stopPropagation();
                        scope.status.isopen = !scope.status.isopen;
                    };

                }

            };

        }

    ]);

});
