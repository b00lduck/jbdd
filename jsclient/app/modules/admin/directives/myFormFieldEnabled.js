/*jslint node: true */
'use strict';

define(['app'], function (app) {

    app.directive('myFormFieldEnabled', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/directives/templates/myFormFieldEnabled.html'
        };

    });

});

