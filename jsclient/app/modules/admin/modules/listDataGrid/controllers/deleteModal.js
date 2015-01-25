/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.controller('DeleteModalController', ['$scope', '$modalInstance', 'entity',
        function ($scope, $modalInstance, entity) {

            $scope.ok = function () {
                $modalInstance.close();
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };

            $scope.entity = entity;

        }

    ]);

});
