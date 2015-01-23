/*jslint node: true */
'use strict';

define(['app', 'MyNavbarDirective'], function (app) {

    app.controller('HomeController', function ($scope, $rootScope) {

        $scope.hello = 'hello controller module';

    });

});
