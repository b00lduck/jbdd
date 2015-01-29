/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.controller('DoubleGridDirectiveController', ['$scope', '$q',
        function ($scope, $q) {

            var leftGridApi,
                rightGridApi;

            /*
                array $scope.getLeftGridConfig();
                array $scope.getRightGridConfig();
                http promise $scope.getLeftGridData();
                http promise $scope.getRightGridData();
                promise $scope.moveItemLeft(id);
                promise $scope.moveItemRight(id);

            */

            function getLeftGridData() {
                $scope.getLeftGridData().then(
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
                $scope.getRightGridData().then(
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
                    promise = $scope.moveItemLeft(rows[i].id);
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
                    promise = $scope.moveItemRight(rows[i].id);
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

                $scope.leftGridOptions = $scope.getLeftGridConfig();
                $scope.rightGridOptions = $scope.getRightGridConfig();

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

        }
    ]);
});
