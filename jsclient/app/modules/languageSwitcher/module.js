/*jslint node: true */
'use strict';

define(['LanguageSwitcherDirective'],

    function (LanguageSwitcherDirective) {
        return angular.module('LanguageSwitcher', ['pascalprecht.translate'])
            .directive('myLanguageSwitcher', LanguageSwitcherDirective);
    });
