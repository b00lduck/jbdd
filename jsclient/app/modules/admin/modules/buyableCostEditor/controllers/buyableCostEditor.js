/*jslint node: true */
'use strict';

define(['angularAMD', 'DataService'], function (angularAMD) {

    angularAMD.controller('BuyableCostEditorController', ['$scope', 'DataService', '$q',

        function ($scope, DataService, $q) {

            var ret = [];
            DataService.getAddableCostGoods().then(function (payload) {
                ret = payload.data;
            });

            $scope.getAddableCostGoods = function () {
                return ret.data;
            };

            $scope.addCost = function () {
            };

            $scope.removeCost = function () {
            };

            $scope.setCostAmount = function () {
            };

            $scope.currentLanguage = function () {
                return 'de-DE';
            };

        }

    ]);

});
