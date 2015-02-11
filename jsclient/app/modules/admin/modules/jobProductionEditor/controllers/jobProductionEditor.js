/*jslint node: true */
'use strict';

define(['angularAMD', 'DataService'], function (angularAMD) {

    angularAMD.controller('JobProductionEditorController', ['$scope', 'DataService', '$q', '$translate',

        function ($scope, DataService, $q, $translate) {


            function editProduction(goodId, amount) {
                DataService.updateProduction($scope.resourceName, $scope.getJobId(), goodId, amount).then(
                    function (payload) {
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            $scope.doubleGridConfig = {

                panelTitle: 'admin.doubleGrid.jobProductionEditor.header',

                leftGridConfig: {
                    columnDefs: [
                        {name: 'jobId', visible: false},
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
                    return DataService.getJobProductions($scope.resourceName, $scope.getJobId());
                },

                getRightGridData: function () {
                    return DataService.getAddableProductionGoods($scope.resourceName, $scope.getJobId());
                },

                moveItemToLeft: function (obj) {
                    return DataService.addProductionToJob($scope.resourceName, $scope.getJobId(), obj.id);
                },

                moveItemToRight: function (obj) {
                    return DataService.removeProductionFromJob($scope.resourceName, $scope.getJobId(), obj.goodId);
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
