/*jslint node: true */
'use strict';

define(['app', 'ContainsService'], function () {

    describe('ContainsService', function () {

        beforeEach(function () {
            module('jbddApp');
        });

        it('should exist', inject(function (ContainsService) {
            expect(ContainsService).toBeDefined();
        }));

        it('returns true on contain', inject(function (ContainsService) {
            var haystack = ['FOO', null, 1, 'BAR', 'BAZ', 1, 2, 3];
            expect(ContainsService.contains(haystack, 'BAR')).toBe(true);
            expect(ContainsService.contains(haystack, 1)).toBe(true);
            expect(ContainsService.contains(haystack, null)).toBe(true);
        }));

        it('returns false on not contain', inject(function (ContainsService) {
            var haystack = ['FOO', null, 1, 'BAR', 'BAZ', 1, 2, 3];
            var needle = 'kiffi-mc-piffi';
            expect(ContainsService.contains(haystack, needle)).toBe(false);
        }));

    });

});
