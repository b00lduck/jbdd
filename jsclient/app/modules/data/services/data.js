/*jslint node: true */
'use strict';


define(['angularAMD'], function (angularAMD) {

    angularAMD.service('DataService', ['$http', '$q',
        function ($http, $q) {

            var restServiceBaseUrl = 'http://localhost:8080/';

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
                return restServiceBaseUrl + resourceName;
            };

            var getItemUrl = function (resourceName, id) {
                return restServiceBaseUrl + resourceName + '/' + id;
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
                var url = getResourceBaseUrl(resourceName);
                delete obj.id;
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

            service.getAddableCostGoods = function (resourceName, buyableId) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/cost/addable';
                return queryRestServiceGet(url);
            };

            service.addCostToBuyable = function (resourceName, buyableId, goodId) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/cost';
                var data = {
                    buyableId: parseInt(buyableId),
                    goodId: parseInt(goodId),
                    amount: 1
                };
                return queryRestServicePost(url, data);
            };

            service.removeCostFromBuyable = function (resourceName, buyableId, goodId) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/cost/' + goodId;
                return queryRestServiceDelete(url);
            };

            service.getBuyableCosts = function (resourceName, buyableId) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/cost';
                return queryRestServiceGet(url);
            };

            service.updateCost = function (resourceName, buyableId, goodId, amount) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/cost';
                var data = {
                    buyableId: parseInt(buyableId),
                    goodId: parseInt(goodId),
                    amount: parseInt(amount)
                };
                return queryRestServicePut(url, data);
            };

            service.getBuyableRequirements = function (resourceName, buyableId) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/requirement';
                return queryRestServiceGet(url);
            };

            service.getAddableRequirementBuyables = function (resourceName, buyableId) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/requirement/addable';
                return queryRestServiceGet(url);
            };

            service.addRequirementToBuyable = function (resourceName, buyableId, requiredBuyableId) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/requirement';
                var data = {
                    buyableId: parseInt(buyableId),
                    requiredBuyableId: parseInt(requiredBuyableId),
                    amount: 1
                };
                return queryRestServicePost(url, data);
            };

            service.removeRequirementFromBuyable = function (resourceName, buyableId, requiredBuyableId) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/requirement/' + requiredBuyableId;
                return queryRestServiceDelete(url);
            };

            service.updateRequirement = function (resourceName, buyableId, requiredBuyableId, amount) {
                var url = getSpecificResourceUrl(resourceName, buyableId) + '/requirement';
                var data = {
                    buyableId: parseInt(buyableId),
                    requiredBuyableId: parseInt(requiredBuyableId),
                    amount: parseInt(amount)
                };
                return queryRestServicePut(url, data);
            };


            return service;

        }

    ]);

});


