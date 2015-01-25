/*jslint node: true */
'use strict';

define(['angularAMD', 'MyNavbarDirective', 'AuthenticationService'], function (angularAMD) {

    angularAMD.controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $rootScope, $location, AuthenticationService) {

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

        }]);

});
