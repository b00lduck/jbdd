/*jslint node: true */
'use strict';

define(['angularAMD', 'adminDoubleGrid', '../controllers/jobProductionEditor'], function (angularAMD) {

    angularAMD.directive('myJobProductionEditor', ['DataService', '$q', function (DataService, $q) {

        return {
            restrict: 'E',
            templateUrl: './modules/admin/modules/jobProductionEditor/directives/templates/myJobProductionEditor.html',
            controller: 'JobProductionEditorController',
            scope: {
                getJobId: '&jobid',
                resourceName: '@resourcename'
            },
            link: function ($scope, $element, $attrs) {

                $scope.resourceName = $attrs.resourcename;

            }

        };

    }]);

});
