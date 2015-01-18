/*jslint node: true */
'use strict';

describe('myLanguageSwitcher directive', function () {

    var $rootScope,
        $compile,
        $httpBackend,
        $translate;

    function mockTranslations($translateProvider) {
        $translateProvider.translations('en_GB', {});
        $translateProvider.translations('de_DE', {});
    }

    beforeEach(function () {

        module('jbddApp', function ($provide, $translateProvider) {
            mockTranslations($translateProvider);
        });

        module('precompiledTemplates');

    });

    beforeEach(inject(function (_$compile_, _$rootScope_, _$httpBackend_, _$translate_) {
        $compile = _$compile_;
        $rootScope = _$rootScope_;
        $httpBackend = _$httpBackend_;
        $translate = _$translate_;
    }));

    it('changes to german', function () {

        spyOn($translate, 'use');

        var element = $compile('<my-language-switcher></my-language-switcher>')($rootScope);
        $rootScope.$digest();

        element.find('img:eq(0)').click();
        expect($translate.use).toHaveBeenCalledWith('de_DE');

    });

    it('changes to english', function () {

        spyOn($translate, 'use');

        var element = $compile('<my-language-switcher></my-language-switcher>')($rootScope);
        $rootScope.$digest();

        element.find('img:eq(1)').click();
        expect($translate.use).toHaveBeenCalledWith('en_GB');

    });

    it('changes not to illegal language', function () {

        var scope = $rootScope.$new();

        spyOn($translate, 'use');

        var element = $compile('<my-language-switcher></my-language-switcher>')(scope);

        scope.$digest();

        scope.changeLanguage('nl_NL');

        expect($translate.use).not.toHaveBeenCalled();
    });


});

