/*jslint node: true */
'use strict';

angular.module('Admin')

    .controller('AdminListController', ['$scope', '$routeParams', '$location',

        function ($scope, $routeParams, $location) {

            var resourceName = $routeParams.resource;
            var listUrl = '/admin/' + resourceName;

            var allGridConfigs = {

                'user': {
                    columnDefs: [
                        {name: 'username', displayNameI18n: 'user.username'},
                        {name: 'email', displayNameI18n: 'user.email'},
                        {name: 'numPlayers', displayNameI18n: 'player.numPlayers'}
                    ]
                },

                'player': {
                    columnDefs: [
                        {name: 'nickname', displayNameI18n: 'player.nickname'}
                    ]
                },

                'good': {
                    columnDefs: [
                        {name: 'name.de-DE', displayNameI18n: 'i18n.name'},
                    ]
                }

            };

            function getGridConfig() {
                var config = allGridConfigs[resourceName];
                config.resourceName = resourceName;
                config.editUrlGetter = getEditUrl;
                return config;
            }

            function getEditUrl(itemId) {
                return listUrl + '/' + itemId;
            }

            $scope.gridConfig = function () {
                return getGridConfig();
            };

            $scope.create = function () {
                var path = getEditUrl('new');
                $location.path(path);
            };

        }])

    .controller('AdminEditController', ['$scope', '$routeParams', '$location', 'DataService', '$translate', '$rootScope',

        function ($scope, $routeParams, $location, DataService, $translate, $rootScope) {

            var resourceName = $routeParams.resource;
            var listUrl = '/admin/' + resourceName;

            var id = $routeParams.id;

            if (id !== 'new') {
                DataService.getItem(resourceName, id).then(
                    function (payload) {
                        $scope.obj = payload.data;
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            } else {
                $scope.obj = {};
                $scope.obj.id = $translate.instant('admin.newid');

                $rootScope.$on('$translateChangeSuccess', function () {
                    $scope.obj.id = $translate.instant('admin.newid');
                });

            }
            $scope.id = id;

            $scope.cancel = function () {
                $location.path(listUrl);
            };

            $scope.save = function () {
                if (id === 'new') {
                    DataService.createItem(resourceName, $scope.obj).then(
                        function () {
                            $location.path(listUrl);
                        },
                        function () {
                            window.alert('ERROR');
                            // TODO: proper error handling
                        }
                    );
                } else {
                    DataService.saveItem(resourceName, $scope.obj).then(
                        function () {
                            $location.path(listUrl);
                        },
                        function () {
                            window.alert('ERROR');
                            // TODO: proper error handling
                        }
                    );
                }
            };

        }])

    .controller('UserPlayerAssignGridDirectiveController', ['$scope', 'DataService', '$q', '$attrs',

        function ($scope, DataService, $q, $attrs) {

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

        }]);
