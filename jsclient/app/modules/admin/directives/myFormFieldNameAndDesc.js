/*jslint node: true */
'use strict';

define(['app'], function (app) {

    app.directive('myFormFieldNameAndDesc', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/directives/templates/myFormFieldNameAndDesc.html'
        };

    });

});
