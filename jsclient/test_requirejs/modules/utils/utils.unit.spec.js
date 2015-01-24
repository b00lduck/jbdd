/*jslint node: true */
'use strict';

define(['../../../app/bower_components/angular/angular', 'UtilsService'], function() {

    describe('Utils', function () {

        beforeEach(function () {
            module('jbddApp');
        });

        it('should exist', inject(function (UtilsService) {
            expect(UtilsService).toBeDefined();
        }));

        it('returns true on contain', inject(function (UtilsService) {
            var haystack = ['FOO', null, 1, 'BAR', 'BAZ', 1, 2, 3];
            expect(UtilsService.contains(haystack, 'BAR')).toBe(true);
            expect(UtilsService.contains(haystack, 1)).toBe(true);
            expect(UtilsService.contains(haystack, null)).toBe(true);
        }));

        it('returns false on not contain', inject(function (UtilsService) {
            var haystack = ['FOO', null, 1, 'BAR', 'BAZ', 1, 2, 3];
            var needle = 'kiffi-mc-piffi';
            expect(UtilsService.contains(haystack, needle)).toBe(false);
        }));

    });

});
