/*jslint node: true */
'use strict';

define(['angularAMD', 'AuthenticationService', 'angular-translate'], function (angularAMD) {

    angularAMD.directive('myMenu', ['AuthenticationService', function (AuthenticationService) {

        return {

            restrict: 'E',

            templateUrl: 'modules/navigation/directives/templates/myMenu.html',

            link: function (scope, element, attrs) {

                scope.showAdminMenu = function() {
                    return AuthenticationService.hasAnyRole([
	                    'ROLE_ADMIN_PLAYER',
	                    'ROLE_ADMIN_USER',
	                    'ROLE_ADMIN_BUILDING',
	                    'ROLE_ADMIN_TECHNOLOGY',
	                    'ROLE_ADMIN_JOB'
	                ]);
                };

	            scope.showAdminBuildingMenu = function() {
		            return AuthenticationService.hasRole('ROLE_ADMIN_BUILDING');
	            };

	            scope.showAdminTechnologyMenu = function() {
		            return AuthenticationService.hasRole('ROLE_ADMIN_TECHNOLOGY');
	            };

	            scope.showAdminJobMenu = function() {
		            return AuthenticationService.hasRole('ROLE_ADMIN_JOB');
	            };

	            scope.showAdminPlayerMenu = function() {
		            return AuthenticationService.hasRole('ROLE_ADMIN_PLAYER');
	            };

	            scope.showAdminUserMenu = function() {
		            return AuthenticationService.hasRole('ROLE_ADMIN_USER');
	            };

            }

        };

    }]);

});
