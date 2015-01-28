/*jslint node: true */
'use strict';

define(['angularAMD', 'DataService'], function (angularAMD) {

    angularAMD.controller('BuyableCostEditorController', ['$scope', 'DataService', '$q',

        function ($scope, DataService, $q) {

            var addableGoods = [];
            var buyableCosts = [];

            function refresh() {
                DataService.getAddableCostGoods($scope.resourceName, $scope.getBuyableId()).then(function (payload) {
                    addableGoods = payload.data;
                });

                DataService.getBuyableCosts($scope.resourceName, $scope.getBuyableId()).then(function (payload) {
                    buyableCosts = payload.data.data;
                    $scope.gridOptions.data = buyableCosts;
                });
            }

            refresh();

            $scope.getAddableCostGoods = function () {
                return addableGoods.data;
            };

            $scope.addCost = function () {
                DataService.addCostToBuyable($scope.resourceName, $scope.getBuyableId(), $scope.selectedGood.id).then(function () {
                    refresh();
                }).catch(function () {
                    alert(1);
                });
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
                    {name: 'goodId'},
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
