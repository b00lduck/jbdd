/*jslint node: true */
'use strict';

define(['angularAMD', 'DataService'], function (angularAMD) {

    angularAMD.controller('BuildingProductionEditorController', ['$scope', 'DataService', '$q', '$translate',

        function ($scope, DataService, $q, $translate) {


            function editProduction(goodId, amount) {
                DataService.updateProduction($scope.resourceName, $scope.getBuildingId(), goodId, amount).then(
                    function (payload) {
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            $scope.doubleGridConfig = {

                panelTitle: 'admin.doubleGrid.buildingProductionEditor.header',

                leftGridConfig: {
                    columnDefs: [
                        {name: 'buildingId', visible: false},
                        {name: 'goodId', width: 55},
                        {name: 'good.name', i18nField: true},
                        {name: 'amount', enableCellEdit: true, validator: 'nonzero_int'}],
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
                    return DataService.getBuildingProductions($scope.resourceName, $scope.getBuildingId());
                },

                getRightGridData: function () {
                    return DataService.getAddableProductionGoods($scope.resourceName, $scope.getBuildingId());
                },

                moveItemToLeft: function (obj) {
                    return DataService.addProductionToBuilding($scope.resourceName, $scope.getBuildingId(), obj.id);
                },

                moveItemToRight: function (obj) {
                    return DataService.removeProductionFromBuilding($scope.resourceName, $scope.getBuildingId(), obj.goodId);
                },

                onRegisterLeftApi: function (gridApi) {
                    gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
                        editProduction(rowEntity.goodId, rowEntity.amount);
                    });
                },

                onRegisterRightApi: function (gridApi) {}

            };

        }

    ]);

});
