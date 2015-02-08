/*jslint node: true */
'use strict';

define(['angularAMD', 'DataService'], function (angularAMD) {

    angularAMD.controller('BuyableCostEditorController', ['$scope', 'DataService', '$q', '$translate',

        function ($scope, DataService, $q, $translate) {


            function editCost(goodId, amount) {
                DataService.updateCost($scope.resourceName, $scope.getBuyableId(), goodId, amount).then(
                    function (payload) {
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            $scope.doubleGridConfig = {

                panelTitle: 'admin.doubleGrid.buyableCostEditor.header',

                leftGridConfig: {
                    columnDefs: [
                        {name: 'buyableId', visible: false},
                        {name: 'goodId', width: 55},
                        {name: 'good.name', i18nField: true},
                        {name: 'amount', enableCellEdit: true, validator: 'nonzero_unsigned_int'}],
                    enableRowSelection: true,
                    enableRowHeaderSelection: false,
                    multiSelect: true,
                    modifierKeysToMultiSelect: true,
                    noUnselect: false,
                    enableSorting: false,
                    enableSelectionBatchEvent: false
                },

                rightGridConfig: {
                    columnDefs: [
                        {name: 'id', width: 55},
                        {name: 'name', i18nField: true}
                    ],
                    enableRowSelection: true,
                    enableRowHeaderSelection: false,
                    multiSelect: true,
                    modifierKeysToMultiSelect: true,
                    noUnselect: false,
                    enableSorting: false,
                    enableSelectionBatchEvent: false
                },

                getLeftGridData: function () {
                    return DataService.getBuyableCosts($scope.resourceName, $scope.getBuyableId());
                },

                getRightGridData: function () {
                    return DataService.getAddableCostGoods($scope.resourceName, $scope.getBuyableId());
                },

                moveItemToLeft: function (obj) {
                    return DataService.addCostToBuyable($scope.resourceName, $scope.getBuyableId(), obj.id);
                },

                moveItemToRight: function (obj) {
                    return DataService.removeCostFromBuyable($scope.resourceName, $scope.getBuyableId(), obj.goodId);
                },

                onRegisterLeftApi: function (gridApi) {
                    gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
                        editCost(rowEntity.goodId, rowEntity.amount);
                    });
                },

                onRegisterRightApi: function (gridApi) {}

            };

        }

    ]);

});
