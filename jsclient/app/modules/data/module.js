/*jslint node: true */
'use strict';

define(['DataService'],

    function (DataService) {
        var app = angular.module('Data', []);
        app.service('DataService', DataService);
    });

