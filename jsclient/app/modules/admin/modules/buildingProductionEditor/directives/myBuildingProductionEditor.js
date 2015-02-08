/*jslint node: true */
'use strict';

define(['angularAMD', 'adminDoubleGrid', '../controllers/buildingProductionEditor'], function (angularAMD) {

    angularAMD.directive('myBuildingProductionEditor', ['DataService', '$q', function (DataService, $q) {

        return {
            restrict: 'E',
            templateUrl: './modules/admin/modules/buildingProductionEditor/directives/templates/myBuildingProductionEditor.html',
            controller: 'BuildingProductionEditorController',
            scope: {
                getBuildingId: '&buildingid',
                resourceName: '@resourcename'
            },
            link: function ($scope, $element, $attrs) {

                $scope.resourceName = $attrs.resourcename;

            }

        };

    }]);

});
