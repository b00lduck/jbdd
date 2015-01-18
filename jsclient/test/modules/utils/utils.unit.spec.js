/*jslint node: true */
'use strict';

describe('Utils', function () {

    beforeEach(function () {
        module('jbddApp');
    });

    it('should exist', inject(function (Utils) {
        expect(Utils).toBeDefined();
    }));

    it('returns true on contain', inject(function (Utils) {
        var haystack = ['FOO', null, 1, 'BAR', 'BAZ', 1, 2, 3];
        expect(Utils.contains(haystack, 'BAR')).toBe(true);
        expect(Utils.contains(haystack, 1)).toBe(true);
        expect(Utils.contains(haystack, null)).toBe(true);
    }));

    it('returns false on not contain', inject(function (Utils) {
        var haystack = ['FOO', null, 1, 'BAR', 'BAZ', 1, 2, 3];
        var needle = 'kiffi-mc-piffi';
        expect(Utils.contains(haystack, needle)).toBe(false);
    }));

});
