/*jslint node: true */
'use strict';

angular.module('Admin')

    .directive('myUserPlayerAssignGrids', ['DataService', '$q',
        function (DataService, $q) {

            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myUserPlayerAssignGrids.html',
                controller: 'UserPlayerAssignGridDirectiveController',
                scope: {
                    getUserId: '&userid'
                },
                link: function (scope, element, attrs, controller) {
                    controller.init();
                }
            };

        }])

    .directive('myFormFieldId', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myFormFieldId.html'
            };
        }])

    .directive('myFormFieldEnabled', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myFormFieldEnabled.html'
            };
        }])

    .directive('myEditButtons', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myEditButtons.html'
            };
        }])

    .directive('myFormFieldNameAndDesc', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/templates/myFormFieldNameAndDesc.html'
            };
        }]);
