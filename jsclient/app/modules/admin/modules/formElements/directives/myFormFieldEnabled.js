/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.directive('myFormFieldEnabled', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/modules/formElements/directives/templates/myFormFieldEnabled.html'
        };

    });

});

