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

        }]);
