/*jslint node: true */
'use strict';

define(['app'], function (app) {

    app.directive('myEditButtons', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/directives/templates/myEditButtons.html'
        };

    });

});
