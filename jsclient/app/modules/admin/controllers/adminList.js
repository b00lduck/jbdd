/*jslint node: true */
'use strict';

define(['angularAMD',
    'MyNavbarDirective',
    'adminListDataGrid'], function (angularAMD) {

    angularAMD.controller('AdminListController', ['$scope', '$routeParams', '$location',
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
                        {name: 'name', displayNameI18n: 'i18n.name', i18nField: true},
                        {name: 'weight', displayNameI18n: 'good.weight'},
                        {name: 'density', displayNameI18n: 'good.density'}
                    ]
                },

                'building': {
                    columnDefs: [
                        {name: 'name', displayNameI18n: 'i18n.name', i18nField: true}
                    ]
                },

                'technology': {
                    columnDefs: [
                        {name: 'name', displayNameI18n: 'i18n.name', i18nField: true}
                    ]
                },

                'job': {
                    columnDefs: [
                        {name: 'name', displayNameI18n: 'i18n.name', i18nField: true}
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

        }
    ]);
});
