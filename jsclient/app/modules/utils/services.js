/*jslint node: true */
'use strict';

angular.module('Utils')

    .service('Utils', function () {

        this.contains = function (haystack, needle) {

            var i;

            if (typeof (haystack) === 'undefined') {
                return false;
            }

            i = haystack.length;

            while (i--) {
                if (haystack[i] === needle) {
                    return true;
                }
            }

            return false;

        };

    });

