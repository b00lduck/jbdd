/*jslint node: true */
'use strict';

define(['DataModule'], function () {

    var myUserPlayerAssignGrids = function (DataService, $q) {

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

    };

    return ['DataService', '$q', myUserPlayerAssignGrids];

});
