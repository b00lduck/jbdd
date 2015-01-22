/*jslint node: true */
'use strict';

define(['angular-translate', 'LanguageSwitcherDirective'],

    function (LanguageSwitcherDirective) {
        return angular.module('LanguageSwitcher', ['pascalprecht.translate'])
            .directive('myLanguageSwitcher', LanguageSwitcherDirective);
    });
