/*jslint node: true */
'use strict';

define(['Base64Service'],

    function(Base64Service) {
        var app = angular.module('Base64', []);
        app.factory('Base64', Base64Service);
    });

