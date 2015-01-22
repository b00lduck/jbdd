/*jslint node: true */
'use strict';

define(['HomeController'],

    function (HomeController) {
        return angular.module('Home', [])
            .controller('HomeController', HomeController);
    });