/*jslint node: true */
'use strict';

define(['angularAMD', 'MyNavbarDirective'], function (angularAMD) {

    angularAMD.controller('HomeController', function ($scope, $rootScope) {

        $scope.hello = 'hello controller module';

    });

});
