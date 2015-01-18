/*jslint node: true */
'use strict';

describe('angularjs homepage', function () {

    it('should have a title', function () {

        browser.get('http://juliemr.github.io/protractor-demo/');

        expect(browser.getTitle()).toEqual('Super Calculator');

    });
});
