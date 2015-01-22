/*jslint node: true */
'use strict';

define(['DataModule', 'angular-translate', 'angular-ui-grid'], function () {

    var DeleteModalInstanceController = function ($scope, $modalInstance, entity) {

        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.entity = entity;

    };

    return ['$scope', '$modalInstance', 'entity', DeleteModalInstanceController];

});
