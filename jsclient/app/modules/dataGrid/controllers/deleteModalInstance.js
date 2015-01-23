/*jslint node: true */
'use strict';

define(['app'], function (app) {

    app.controller('LoginController', ['$scope', '$modalInstance', 'entity',
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
