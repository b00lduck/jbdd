/*jslint node: true */
'use strict';

define(['angular-route'], function () {

    var AdminListController = function ($scope, $routeParams, $location) {

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
                    {name: 'weight', displayNameI18n: 'good.weight'}
                ]
            },

            'building': {
                columnDefs: [
                    {name: 'name.de-DE', displayNameI18n: 'i18n.name'}
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

    };

    return ['$scope', '$routeParams', '$location', AdminListController];

});
