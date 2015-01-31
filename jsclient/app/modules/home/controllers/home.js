/*jslint node: true */
'use strict';

define(['angularAMD', 'MyNavbarDirective'], function (angularAMD) {

    angularAMD.controller('HomeController', ['$scope', function ($scope) {

        $scope.hello = 'hello controller module';

    }]);

});
