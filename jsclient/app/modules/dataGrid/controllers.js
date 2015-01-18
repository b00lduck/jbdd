/*jslint node: true */
'use strict';

angular.module('Authentication')

    .controller('DeleteModalInstanceController', ['$scope', '$modalInstance', 'entity',
        function ($scope, $modalInstance, entity) {

            $scope.ok = function () {
                $modalInstance.close();
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };

            $scope.entity = entity;

        }]);
