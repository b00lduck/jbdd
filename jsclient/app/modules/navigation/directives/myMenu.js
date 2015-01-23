/*jslint node: true */
'use strict';

define(['app', 'AuthenticationService'], function (app) {

    app.directive('myMenu', ['AuthenticationService', function (AuthenticationService) {

        return {

            restrict: 'E',

            templateUrl: 'modules/navigation/directives/templates/myMenu.html',

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

    }]);

});
