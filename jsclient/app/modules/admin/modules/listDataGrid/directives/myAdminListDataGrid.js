/*jslint node: true */
'use strict';

define(['angularAMD', 'ngload!angular-ui-grid', 'angular-ui-bootstrap-bower'], function (angularAMD) {

    angularAMD.directive('myAdminListDataGrid', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/modules/listDataGrid/directives/templates/myAdminListDataGrid.html',
                controller: 'AdminListDataGridDirectiveController',

                link: function (scope, element, attrs, controller) {
                    // TODO: do this with isolate scope
                    scope.config = scope[attrs.config]();
                    controller.init();
                }
            };

        }

    ]);

});
