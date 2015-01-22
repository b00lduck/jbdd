/*jslint node: true */
'use strict';

define(['AuthenticationService'], function () {

    var AuthenticationController = function ($scope, $rootScope, $location, AuthenticationService) {

        $scope.showError = false;
        $scope.dataLoading = false;

        $scope.login = function () {

            var loginPromise;

            $scope.dataLoading = true;

            loginPromise = AuthenticationService.login($scope.username, $scope.password);

            loginPromise.then(function () {
                $scope.showError = false;
                $scope.errorObject = {};
                $location.path('/home');
            });

            loginPromise.catch(function (errorObject) {
                $scope.showError = true;
                $scope.errorObject = errorObject;
            });

            loginPromise.finally(function () {
                $scope.dataLoading = false;
            });

        };

    };

    return ['$scope', '$rootScope', '$location', 'AuthenticationService', AuthenticationController];
});
