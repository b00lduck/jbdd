/*jslint node: true */
'use strict';

define(['angularAMD', 'MyMenuDirective', 'LanguageSwitcherDirective', 'MyLoginInfoDirective'], function (angularAMD) {

    angularAMD.directive('myNavbar', [function () {

        return {
            restrict: 'E',
            templateUrl: 'modules/navigation/directives/templates/myNavbar.html'
        };

    }]);

});
