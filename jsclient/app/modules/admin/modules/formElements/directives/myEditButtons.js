/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.directive('myEditButtons', function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/admin/modules/formElements/directives/templates/myEditButtons.html'
        };

    });

});
