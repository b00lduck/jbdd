/*jslint node: true */
'use strict';

angular.module('Data')
    .factory('DataService',
    ['$http', '$q',
        function ($http, $q) {

            var restServiceBaseUrl = 'http://localhost:8080/jbdd-restservice/';

            var service = {};

            var queryRestServiceGet = function (query) {
                return $http.get(query);
            };

            var queryRestServiceDelete = function (query) {
                return $http.delete(query);
            };

            var queryRestServicePut = function (query, data) {
                return $http.put(query, data);
            };

            var queryRestServicePost = function (query, data) {
                return $http.post(query, data);
            };

            var getResourceBaseUrl = function (resourceName) {
                var url = restServiceBaseUrl + resourceName;
                return url;
            };

            var getItemUrl = function (resourceName, id) {
                var url = restServiceBaseUrl + resourceName + '/' + id;
                return url;
            };

            var getPagedResourceListUrl = function (resourceName, pagingOptions) {
                var first = (pagingOptions.pageNumber - 1) * pagingOptions.pageSize;

                var url = getResourceBaseUrl(resourceName) + '?first=' + first + '&size=' + pagingOptions.pageSize;

                if (null !== pagingOptions.sortColumn) {
                    url += '&sort=' + pagingOptions.sortColumn;
                    url += '&desc=' + pagingOptions.sortDesc;
                }

                return url;
            };

            var getSpecificResourceUrl = function (resourceName, itemId) {
                return getResourceBaseUrl(resourceName) + '/' + itemId;
            };

            service.getList = function (resourceName, pagingOptions) {
                var url = getPagedResourceListUrl(resourceName, pagingOptions);
                return queryRestServiceGet(url);
            };

            service.getItem = function (resourceName, id) {
                var url = getItemUrl(resourceName, id);
                return queryRestServiceGet(url);
            };

            service.deleteItem = function (resourceName, itemId) {
                var url = getSpecificResourceUrl(resourceName, itemId);
                return queryRestServiceDelete(url);
            };

            service.saveItem = function (resourceName, obj) {
                var url = getSpecificResourceUrl(resourceName, obj.id);
                return queryRestServicePut(url, obj);
            };

            service.createItem = function (resourceName, obj) {
                delete obj.id;
                var url = getResourceBaseUrl(resourceName);
                return queryRestServicePost(url, obj);
            };

            service.getPlayersOfUser = function (id) {
                var url = getSpecificResourceUrl('user', id) + '/player';
                return queryRestServiceGet(url);
            };

            service.getUnusedPlayers = function () {
                var url = getResourceBaseUrl('player') + '/unused';
                return queryRestServiceGet(url);
            };

            service.assignPlayerToUser = function (userId, playerId) {
                var url = getSpecificResourceUrl('user', userId) + '/player/' + playerId;
                return queryRestServicePut(url);
            };

            service.removePlayerFromUser = function (userId, playerId) {
                var url = getSpecificResourceUrl('user', userId) + '/player/' + playerId;
                return queryRestServiceDelete(url);
            };

            return service;

        }]);
