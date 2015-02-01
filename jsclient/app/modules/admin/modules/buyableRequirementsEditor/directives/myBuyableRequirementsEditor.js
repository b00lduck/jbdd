/*jslint node: true */
'use strict';

define(['angularAMD', 'adminDoubleGrid', '../controllers/buyableRequirementsEditor'], function (angularAMD) {

    angularAMD.directive('myBuyableRequirementsEditor', ['DataService', '$q', function (DataService, $q) {

        return {
            restrict: 'E',
            templateUrl: './modules/admin/modules/buyableRequirementsEditor/directives/templates/myBuyableRequirementsEditor.html',
            controller: 'BuyableRequirementsEditorController',
            scope: {
                getBuyableId: '&buyableid',
                resourceName: '@resourcename'
            },
            link: function ($scope, $element, $attrs) {

                $scope.resourceName = $attrs.resourcename;

            }

        };

    }]);

});
