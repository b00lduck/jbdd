/*jslint node: true */
'use strict';

angular.module('Navigation')

    .directive('myMenu', ['AuthenticationService', function (AuthenticationService) {

        return {

            restrict: 'E',

            templateUrl: 'modules/navigation/templates/myMenu.html',

            link: function (scope, element, attrs) {

                scope.userIsAnyAdmin = function() {
                    return AuthenticationService.userIsAnyAdmin();
                };

                scope.userIsUserAdmin = function () {
                    return AuthenticationService.userIsUserAdmin();
                };

                scope.userIsPlayerAdmin = function () {
                    return AuthenticationService.userIsPlayerAdmin();
                };

                scope.userIsBuildingAdmin = function () {
                    return AuthenticationService.userIsBuildingAdmin();
                };

            }

        };

    }])

    .directive('myNavbar', function () {
        return {
            restrict: 'E',
            templateUrl: 'modules/navigation/templates/myNavbar.html'
        };
    });

