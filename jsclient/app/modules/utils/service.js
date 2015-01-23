/*jslint node: true */
'use strict';


define(['app'], function (app) {

    app.service('UtilsService', function () {

        var service = {};

        service.contains = function (haystack, needle) {

            var i;

            if ('undefined' === typeof (haystack)) {
                return false;
            }

            i = haystack.length;

            //noinspection IncrementDecrementResultUsedJS
            while (i--) {
                if (haystack[i] === needle) {
                    return true;
                }
            }

            return false;

        };

        return service;
    });

});
