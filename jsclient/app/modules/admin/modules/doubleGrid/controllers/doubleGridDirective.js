/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.controller('DoubleGridDirectiveController', ['$scope', '$q',
        function ($scope, $q) {

            var leftGridApi,
                rightGridApi,
                leftGridConfig,
                rightGridConfig,
                moveItemToLeft,
                moveItemToRight,
                getLeftGridData,
                getRightGridData;

            function internalGetLeftGridData() {
                getLeftGridData().then(
                    function (payload) {
                        leftGridConfig.totalItems = payload.data.meta.totalItems;
                        leftGridConfig.data = payload.data.data;
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            function internalGetRightGridData() {
                getRightGridData().then(
                    function (payload) {
                        rightGridConfig.totalItems = payload.data.meta.totalItems;
                        rightGridConfig.data = payload.data.data;
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            function refresh() {
                internalGetLeftGridData();
                internalGetRightGridData();
            }

            function getMoveArrayLeftPromise(rows) {
                var promises = [],
                    length = rows.length,
                    i,
                    promise;

                for (i = 0; i < length; i++) {
                    promise = moveItemToLeft(rows[i]);
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
                    promise = moveItemToRight(rows[i]);
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

                leftGridConfig = $scope.config.leftGridConfig;
                rightGridConfig = $scope.config.rightGridConfig;
                moveItemToLeft = $scope.config.moveItemToLeft;
                moveItemToRight = $scope.config.moveItemToRight;
                getLeftGridData = $scope.config.getLeftGridData;
                getRightGridData = $scope.config.getRightGridData;

                leftGridConfig.onRegisterApi = function (gridApi) {
                    leftGridApi = gridApi;
                };

                rightGridConfig.onRegisterApi = function (gridApi) {
                    rightGridApi = gridApi;
                };

                $scope.leftGridOptions = leftGridConfig;

                $scope.rightGridOptions = rightGridConfig;

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
                var rows = rightGridConfig.data;
                moveArrayLeft(rows);
            };

            $scope.moveAllRight = function () {
                var rows = leftGridConfig.data;
                moveArrayRight(rows);
            };

        }
    ]);
});
