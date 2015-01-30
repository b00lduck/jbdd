/*jslint node: true */
'use strict';

define(['angularAMD', 'adminDoubleGrid', '../controllers/userPlayerAssignGridDirective'], function (angularAMD) {

    angularAMD.directive('myUserPlayerAssignGrid', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/modules/userPlayerAssignGrid/directives/templates/myUserPlayerAssignGrid.html',
            controller: 'UserPlayerAssignGridDirectiveController',
            scope: {
	            getUserId: '&userId',
            },
            link: function (scope, element, attrs, controller) {



            }
        };

    });

});
