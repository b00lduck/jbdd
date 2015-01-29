/*jslint node: true */
'use strict';

define(['angularAMD', 'doubleGrid', '../controllers/buyableCostEditor'], function (angularAMD) {

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

                $scope.leftGridConfig = {
                    columnDefs: [
                        {name: 'id'},
                        {name: 'name'},
                        {name: 'amount'}
                    ],
                    enableRowSelection: true,
                    enableRowHeaderSelection: false,
                    multiSelect: true,
                    modifierKeysToMultiSelect: true,
                    noUnselect: false,
                    enableSorting: false,
                    enableSelectionBatchEvent: false
                };

                $scope.rightGridConfig = {
                    columnDefs: [
                        {name: 'id'},
                        {name: 'name'}
                    ],
                    enableRowSelection: true,
                    enableRowHeaderSelection: false,
                    multiSelect: true,
                    modifierKeysToMultiSelect: true,
                    noUnselect: false,
                    enableSorting: false,
                    enableSelectionBatchEvent: false
                };

                $scope.getLeftGridData = function() {

                };

                $scope.getRightGridData = function() {

                };


            }
        };

    }]);

});
