/*jslint node: true */
'use strict';

angular.module('Admin')

    .controller('AdminEditController', ['$scope', '$routeParams', '$location', 'DataService', '$translate', '$rootScope',

        function ($scope, $routeParams, $location, DataService, $translate, $rootScope) {

            var resourceName = $routeParams.resource;
            var listUrl = '/admin/' + resourceName;

            var id = $routeParams.id;

            if ('new' === id) {
                $scope.obj = {};
                $scope.obj.id = $translate.instant('admin.newid');

                $rootScope.$on('$translateChangeSuccess', function () {
                    $scope.obj.id = $translate.instant('admin.newid');
                });

            } else {
                DataService.getItem(resourceName, id).then(
                    function (payload) {
                        $scope.obj = payload.data;
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }
            $scope.id = id;

            $scope.cancel = function () {
                $location.path(listUrl);
            };

            $scope.save = function () {
                if ('new' === id) {
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
