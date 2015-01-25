/*jslint node: true */
'use strict';

define(['angularAMD', 'adminUserPlayerAssignGrid'], function (angularAMD) {

    angularAMD.directive('myUserPlayerAssignGrid', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/modules/userPlayerAssignGrid/directives/templates/myUserPlayerAssignGrid.html',
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
