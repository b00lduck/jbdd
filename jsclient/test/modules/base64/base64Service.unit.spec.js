/*jslint node: true */
'use strict';

define(['app', 'Base64Service'], function () {

    describe('Base64Service', function () {

        beforeEach(function () {
            module('jbddApp');
        });

        it('should exist', inject(function (Base64Service) {
            expect(Base64Service).toBeDefined();
        }));

        it('should encode UTF-8 test string', inject(function (Base64Service) {
            var raw = 'Polyfon zwitschernd aßen Mäxchens Vögel Rüben, Joghurt und Quark';
            var encoded = Base64Service.encode(raw);
            var preEncoded = 'UG9seWZvbiB6d2l0c2NoZXJuZCBhw59lbiBNw6R4Y2hlbnMgVsO2Z2VsIFLDvGJlbiwgSm9naHVydCB1bmQgUXVhcms=';
            expect(encoded).toEqual(preEncoded);
        }));

        it('should decode UTF-8 test string', inject(function (Base64Service) {
            var raw = 'VGhpcyBpcyDDpG4gw5xtbGF1dCBUZcOfdC1TdHJpbmcgaW4gw5xURi04Lg==';
            var decoded = Base64Service.decode(raw);
            var preDecoded = 'This is än Ümlaut Teßt-String in ÜTF-8.';
            expect(decoded).toEqual(preDecoded);
        }));

    });

});
