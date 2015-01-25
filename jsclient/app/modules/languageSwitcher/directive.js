/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.directive('myLanguageSwitcher', ['$translate', function ($translate) {

        return {

            restrict: 'E',

            templateUrl: 'modules/languageSwitcher/templates/myLanguageSwitcher.html',

            link: function (scope, element, attrs) {

                scope.changeLanguage = function (lang) {

                    if (('en_GB' === lang) || ('de_DE' === lang)) {
                        $translate.use(lang);
                    }

                };
            }
        };
    }]);
});
