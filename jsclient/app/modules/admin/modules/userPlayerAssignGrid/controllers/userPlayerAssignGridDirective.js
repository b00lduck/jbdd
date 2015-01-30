/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.controller('UserPlayerAssignGridDirectiveController', ['$scope', 'DataService',
        function ($scope, DataService) {

	        $scope.doubleGridConfig = {

		        leftGridConfig: {
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
		        },

		        rightGridConfig: {
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
		        },

		        getLeftGridData: function () {
			        return DataService.getPlayersOfUser($scope.getUserId());
		        },

		        getRightGridData: function () {
			        return DataService.getUnusedPlayers($scope.getUserId());
		        },

		        moveItemToLeft: function (obj) {
			        return DataService.assignPlayerToUser($scope.getUserId(), obj.id)
		        },

		        moveItemToRight: function (obj) {
			        return DataService.removePlayerFromUser($scope.getUserId(), obj.id);
		        }

	        };

        }

    ]);

});
