/*jslint node: true */
'use strict';

define(['angularAMD', 'UserPlayerAssignGridDirectiveController'], function (angularAMD) {

    angularAMD.directive('myUserPlayerAssignGrids', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/directives/templates/myUserPlayerAssignGrids.html',
            controller: 'UserPlayerAssignGridDirectiveController',
            scope: {
                getUserId: '&userid'
            },
            link: function (scope, element, attrs, controller) {
                controller.init();
            }
        };

    });

});
