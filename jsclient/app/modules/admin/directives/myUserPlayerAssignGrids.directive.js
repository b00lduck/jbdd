/*jslint node: true */
'use strict';

angular.module('Admin')

    .directive('myUserPlayerAssignGrids', ['DataService', '$q',
        function (DataService, $q) {

            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myUserPlayerAssignGrids.html',
                controller: 'UserPlayerAssignGridDirectiveController',
                scope: {
                    getUserId: '&userid'
                },
                link: function (scope, element, attrs, controller) {
                    controller.init();
                }
            };

        }]);
