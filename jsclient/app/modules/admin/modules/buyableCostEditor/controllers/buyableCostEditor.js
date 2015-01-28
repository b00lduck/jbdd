/*jslint node: true */
'use strict';

define(['angularAMD', 'DataService'], function (angularAMD) {

    angularAMD.controller('BuyableCostEditorController', ['$scope', 'DataService', '$q',

        function ($scope, DataService, $q) {

            var ret = [];
            DataService.getAddableCostGoods($scope.resourceName, $scope.getBuyableId()).then(function (payload) {
                ret = payload.data;
            });

            $scope.getAddableCostGoods = function () {
                return ret.data;
            };

            $scope.addCost = function () {
                DataService.addCostToBuyable($scope.resourceName, $scope.getBuyableId(), $scope.selectedGood.id);
            };

            $scope.removeCost = function () {
            };

            $scope.setCostAmount = function () {
            };

            $scope.currentLanguage = function () {
                return 'de-DE';
            };

            $scope.gridOptions = {
                columnDefs: [
                    {name: 'id', width: 55},
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

        }

    ]);

});
