/*jslint node: true */
'use strict';

define(['app'], function (app) {

    app.controller('UserPlayerAssignGridDirectiveController', ['$scope', 'DataService', '$q',
        function ($scope, DataService, $q) {

            var leftGridApi,
                rightGridApi,
                userId;

            function getBasicGridConfig() {
                return {
                    columnDefs: [
                        {name: 'id', width: 55},
                        {name: 'nickname'}
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

            function getLeftGridData() {
                DataService.getPlayersOfUser(userId).then(
                    function (payload) {
                        $scope.leftGridOptions.totalItems = payload.data.meta.totalItems;
                        $scope.leftGridOptions.data = payload.data.data;
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            function getRightGridData() {
                DataService.getUnusedPlayers(userId).then(
                    function (payload) {
                        $scope.rightGridOptions.totalItems = payload.data.meta.totalItems;
                        $scope.rightGridOptions.data = payload.data.data;
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            function refresh() {
                getLeftGridData();
                getRightGridData();
            }

            function getMoveArrayLeftPromise(rows) {
                var promises = [],
                    length = rows.length,
                    i,
                    promise;

                for (i = 0; i < length; i++) {
                    promise = DataService.assignPlayerToUser(userId, rows[i].id);
                    promises.push(promise);
                }

                return $q.all(promises);
            }

            function getMoveArrayRightPromise(rows) {
                var promises = [],
                    length = rows.length,
                    i,
                    promise;

                for (i = 0; i < length; i++) {
                    promise = DataService.removePlayerFromUser(userId, rows[i].id);
                    promises.push(promise);
                }

                return $q.all(promises);
            }

            function moveArrayLeft(rows) {
                var promise = getMoveArrayLeftPromise(rows);
                promise.then(function () {
                    rightGridApi.selection.clearSelectedRows();
                });
                promise.catch(function () {
                    window.alert('ERROR');
                    // TODO: proper error handling
                });
                promise.finally(function () {
                    refresh();
                });
            }

            function moveArrayRight(rows) {
                var promise = getMoveArrayRightPromise(rows);
                promise.then(function () {
                    leftGridApi.selection.clearSelectedRows();
                });
                promise.catch(function () {
                    window.alert('ERROR');
                    // TODO: proper error handling
                });
                promise.finally(function () {
                    refresh();
                });
            }

            this.init = function () {
                userId = $scope.getUserId();

                $scope.leftGridOptions = getBasicGridConfig();
                $scope.rightGridOptions = getBasicGridConfig();

                $scope.leftGridOptions.onRegisterApi = function (gridApi) {
                    leftGridApi = gridApi;
                };

                $scope.rightGridOptions.onRegisterApi = function (gridApi) {
                    rightGridApi = gridApi;
                };

                refresh();
            };

            $scope.moveSelectionLeft = function () {
                var rows = rightGridApi.selection.getSelectedRows();
                moveArrayLeft(rows);
            };

            $scope.moveSelectionRight = function () {
                var rows = leftGridApi.selection.getSelectedRows();
                moveArrayRight(rows);
            };

            $scope.moveAllLeft = function () {
                var rows = $scope.rightGridOptions.data;
                moveArrayLeft(rows);
            };

            $scope.moveAllRight = function () {
                var rows = $scope.leftGridOptions.data;
                moveArrayRight(rows);
            };

            this.init();

        }
    ]);
});
