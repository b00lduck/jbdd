/*jslint node: true */
'use strict';

define(['angularAMD', 'doubleGrid', '../controllers/buyableCostEditor'], function (angularAMD) {

    angularAMD.directive('myBuyableCostEditor', ['DataService', '$q', function (DataService, $q) {

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
