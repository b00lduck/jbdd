/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.directive('myFormFieldId', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/modules/formElements/directives/templates/myFormFieldId.html'
        };

    });

});
