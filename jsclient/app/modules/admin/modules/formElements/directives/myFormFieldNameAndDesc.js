/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.directive('myFormFieldNameAndDesc', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/modules/formElements/directives/templates/myFormFieldNameAndDesc.html'
        };

    });

});
