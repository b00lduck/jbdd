/*jslint node: true */
'use strict';

define(['angularAMD', '../controllers/buyableCostEditor'], function (angularAMD) {

    angularAMD.directive('myBuyableCostEditor', ['DataService', function (DataService) {

        return {
            restrict: 'E',
            templateUrl: './modules/admin/modules/buyableCostEditor/directives/templates/myBuyableCostEditor.html',
            controller: 'BuyableCostEditorController',
            scope: {
                getBuyableId: '&buyableid',
                resourceName: '@resourcename'
            },
            link: function ($scope, $element, $attrs) {
                $scope.resourceName = $attrs.resourcename;
            }
        };

    }]);

});
