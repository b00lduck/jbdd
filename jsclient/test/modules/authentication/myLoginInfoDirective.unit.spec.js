/*jslint node: true */
'use strict';

describe('myLoginInfo Directive', function () {

    var scope,
        $rootScope,
        controller,
        $q,
        $compile,
        $httpBackend,
        authServiceMock;

    function mockTranslations($translateProvider) {
        $translateProvider.translations('en_GB', {});
        $translateProvider.translations('de_DE', {});
    }

    function mockAuthenticationService($provide) {

        var loggedIn = false;
        var username = '';
        var roles = [];

        authServiceMock = {

            getRoles: function () {
                return roles;
            },

            isLoggedIn: function () {
                return loggedIn;
            },

            getUsername: function () {
                return username;
            },

            login: function () {
                loggedIn = true;
                username = 'fancyUsername';
                roles = ['ROLE1', 'ROLE2'];
            },

            logout: function () {
                loggedIn = false;
                username = null;
                roles = [];
            }

        };

        $provide.value('AuthenticationService', authServiceMock);
    }

    beforeEach(function () {

        module('jbddApp', function ($provide, $translateProvider) {
            mockTranslations($translateProvider);
            mockAuthenticationService($provide);
        });

        module('precompiledTemplates');

        inject(function (_$rootScope_, _$q_, _$controller_) {
            $rootScope = _$rootScope_;
            $q = _$q_;
            scope = _$rootScope_.$new();
            controller = _$controller_('LoginController', {
                '$scope': scope
            });

        });

    });

    beforeEach(inject(function (_$compile_, _$rootScope_, _$httpBackend_) {
        $compile = _$compile_;
        $rootScope = _$rootScope_;
        $httpBackend = _$httpBackend_;
    }));

    it('shows the roles of the user', function () {
        authServiceMock.login();
        var element = $compile('<my-login-info></my-login-info>')($rootScope);
        var i;
        $rootScope.$digest();
        for (i = 0; i < authServiceMock.getRoles().length; i++) {
            expect(element.html()).toContain(authServiceMock.getRoles()[i]);
        }

        expect(element.html()).toContain('ROLE2');
    });

    it('shows the number of roles of the user', function () {
        authServiceMock.login();
        var element = $compile('<my-login-info></my-login-info>')($rootScope);
        $rootScope.$digest();
        expect(element.html()).toContain(authServiceMock.getRoles().length);
    });

    it('shows the username', function () {
        authServiceMock.login();
        var element = $compile('<my-login-info></my-login-info>')($rootScope);
        $rootScope.$digest();
        expect(element.html()).toContain('fancyUsername');
    });

    it('shows notLoggedIn message', function () {
        authServiceMock.logout();
        var element = $compile('<my-login-info></my-login-info>')($rootScope);
        $rootScope.$digest();
        expect(element.html()).toContain('authentication.notLoggedIn');
    });

    it('show logout button if logged in', function () {
        authServiceMock.login();
        var element = $compile('<my-login-info></my-login-info>')($rootScope);
        $rootScope.$digest();

        var button = element.find('button');
        expect(button.length).toBe(1);
        expect(button.html()).toContain('authentication.logout');
    });

    it('do not show logout button if not logged in', function () {
        authServiceMock.logout();
        var element = $compile('<my-login-info></my-login-info>')($rootScope);
        $rootScope.$digest();

        var button = element.find('button');
        expect(button.length).toBe(0);
        expect(element.html()).not.toContain('authentication.logout');
    });

    it('calls logout on click on logout button', function () {

        spyOn(authServiceMock, "logout");

        authServiceMock.login();
        var element = $compile('<my-login-info></my-login-info>')($rootScope);
        $rootScope.$digest();

        var button = element.find('button');
        button.click();

        expect(authServiceMock.logout).toHaveBeenCalled();

    });

});
