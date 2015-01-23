/*jslint node: true */
'use strict';

define(['app'], function (app) {

    app.directive('myFormFieldId', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/directives/templates/myFormFieldId.html'
        };

    });

});
