/*jslint node: true */
'use strict';

angular.module('Admin')

    .directive('myUserPlayerAssignGrids', ['DataService', '$q',
        function (DataService, $q) {

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

            var leftGridApi,
                rightGridApi;

            return {

                restrict: 'E',

                templateUrl: 'modules/admin/templates/myUserPlayerAssignGrids.html',

                link: function (scope, element, attrs) {

                    var userId = attrs.userid;

                    function getLeftGridData() {
                        DataService.getPlayersOfUser(userId).then(
                            function (payload) {
                                scope.leftGridOptions.totalItems = payload.data.meta.totalItems;
                                scope.leftGridOptions.data = payload.data.data;
                            },
                            function () {
                                window.alert('ERROR');
                                // TODO: proper error handling
                            });
                    }

                    function getRightGridData() {
                        DataService.getUnusedPlayers(userId).then(
                            function (payload) {
                                scope.rightGridOptions.totalItems = payload.data.meta.totalItems;
                                scope.rightGridOptions.data = payload.data.data;
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

                    scope.leftGridOptions = getBasicGridConfig();
                    scope.rightGridOptions = getBasicGridConfig();

                    scope.leftGridOptions.onRegisterApi = function (gridApi) {
                        leftGridApi = gridApi;
                    };

                    scope.rightGridOptions.onRegisterApi = function (gridApi) {
                        rightGridApi = gridApi;
                    };

                    refresh();

                    function getMoveArrayLeftPromise(rows) {
                        var promises = [];
                        var length = rows.length;
                        var i;

                        for (i = 0; i < length; i++) {
                            var promise = DataService.assignPlayerToUser(userId, rows[i].id);
                            promises.push(promise);
                        }

                        return $q.all(promises);
                    }

                    function getMoveArrayRightPromise(rows) {
                        var promises = [];
                        var length = rows.length;
                        var i;

                        for (i = 0; i < length; i++) {
                            var promise = DataService.removePlayerFromUser(userId, rows[i].id);
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

                    scope.moveSelectionLeft = function () {
                        var rows = rightGridApi.selection.getSelectedRows();
                        moveArrayLeft(rows);
                    };

                    scope.moveSelectionRight = function () {
                        var rows = leftGridApi.selection.getSelectedRows();
                        moveArrayRight(rows);
                    };

                    scope.moveAllLeft = function () {
                        var rows = scope.rightGridOptions.data;
                        moveArrayLeft(rows);
                    };

                    scope.moveAllRight = function () {
                        var rows = scope.leftGridOptions.data;
                        moveArrayRight(rows);
                    };

                }

            };

        }])

    .directive('myFormFieldId', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myFormFieldId.html'
            };
        }])

    .directive('myFormFieldEnabled', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myFormFieldEnabled.html'
            };
        }])

    .directive('myEditButtons', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myEditButtons.html'
            };
        }])

    .directive('myFormFieldNameAndDesc', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myFormFieldNameAndDesc.html'
            };
        }]);
