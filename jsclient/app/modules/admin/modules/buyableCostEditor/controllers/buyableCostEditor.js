/*jslint node: true */
'use strict';

define(['angularAMD', 'DataService'], function (angularAMD) {

    angularAMD.controller('BuyableCostEditorController', ['$scope', 'DataService', '$q',

        function ($scope, DataService, $q) {

            $scope.doubleGridConfig = {

                leftGridConfig: {
                    columnDefs: [
                        {name: 'buyableId', width: 55},
                        {name: 'goodId', width: 55},
                        {name: 'amount'}
                    ],
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
                }

            };

        }

    ]);

});
