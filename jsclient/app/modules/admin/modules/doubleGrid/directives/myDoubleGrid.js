/*jslint node: true */
'use strict';

define(['angularAMD', 'ngload!angular-ui-grid', 'angular-ui-bootstrap-bower'], function (angularAMD) {

    angularAMD.directive('myDoubleGrid', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/modules/doubleGrid/directives/templates/myDoubleGrid.html',
            controller: 'DoubleGridDirectiveController',
            scope: {
                config: '='
            },
            link: function (scope, element, attrs, controller) {
                controller.init();
            }
        };

    });

});
