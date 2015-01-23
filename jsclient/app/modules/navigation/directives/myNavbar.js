/*jslint node: true */
'use strict';

define(['app', 'MyMenuDirective', 'LanguageSwitcherDirective', 'MyLoginInfoDirective'], function (app) {

    app.directive('myNavbar', [function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/navigation/directives/templates/myNavbar.html'
        };

    }]);

});
