/*jslint node: true */
'use strict';

angular.module('Admin')

    .directive('myFormFieldId', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/directives/templates/myFormFieldId.html'
            };
        }])

    .directive('myFormFieldEnabled', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/directives/templates/myFormFieldEnabled.html'
            };
        }])

    .directive('myFormFieldNameAndDesc', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/directives/templates/myFormFieldNameAndDesc.html'
            };
        }])

    .directive('myEditButtons', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'modules/admin/directives/templates/myEditButtons.html'
            };
        }]);
