/*jslint node: true */
'use strict';

define(['angularAMD', 'DataService'], function (angularAMD) {

    angularAMD.controller('BuyableRequirementsEditorController', ['$scope', 'DataService', '$q', '$translate',

        function ($scope, DataService, $q, $translate) {


            function editRequirement(buyableId, amount) {
                DataService.updateCost($scope.resourceName, $scope.getBuyableId(), buyableId, amount).then(
                    function (payload) {
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            $scope.doubleGridConfig = {

                panelTitle: 'admin.doubleGrid.buyableRequirementsEditor.header',

                leftGridConfig: {
                    columnDefs: [
                        {name: 'buyableId', visible: false},
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
                    return DataService.getBuyableRequirements($scope.resourceName, $scope.getBuyableId());
                },

                getRightGridData: function () {
                    return DataService.getAddableRequirementBuyables($scope.resourceName, $scope.getBuyableId());
                },

                moveItemToLeft: function (obj) {
                    return DataService.addRequirementToBuyable($scope.resourceName, $scope.getBuyableId(), obj.id);
                },

                moveItemToRight: function (obj) {
                    return DataService.removeRequirementFromBuyable($scope.resourceName, $scope.getBuyableId(), obj.buyableId);
                },

                onRegisterLeftApi: function (gridApi) {
                    gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
                        editRequirement(rowEntity.buyableId, rowEntity.amount);
                    });
                },

                onRegisterRightApi: function (gridApi) {}

            };

        }

    ]);

});
