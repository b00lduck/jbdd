/*jslint node: true */
'use strict';

define(['angularAMD', '../controllers/buyableCostEditor'], function (angularAMD) {

    angularAMD.directive('myBuyableCostEditor', function () {

        return {
            restrict: 'E',
            templateUrl: './modules/admin/modules/buyableCostEditor/directives/templates/myBuyableCostEditor.html',
            controller: 'BuyableCostEditorController',
            scope: {
                getUserId: '&userid'
            },
            link: function (scope, element, attrs, controller) {

            }
        };

    });

});
