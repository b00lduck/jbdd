/*jslint node: true */
'use strict';

define(['app', 'MyMenuDirective', 'modules/navigation/directives/templates/myMenu.html'], function () {

    describe('myMenu directive', function () {

        var scope,
            $rootScope,
            $compile,
            $httpBackend,
            authServiceMock;

        function mockTranslations($translateProvider) {
            $translateProvider.translations('en_GB', {});
            $translateProvider.translations('de_DE', {});
        }

        function mockAuthenticationService($provide) {

            var isUserAdmin = false;
            var isPlayerAdmin = false;
            var isBuildingAdmin = false;

            authServiceMock = {
                userIsAnyAdmin: function () { return isUserAdmin || isPlayerAdmin; },
                userIsUserAdmin: function () { return isUserAdmin; },
                userIsPlayerAdmin: function () { return isPlayerAdmin; },
                userIsBuildingAdmin: function () { return isBuildingAdmin; },

                isLoggedIn: function () { return true; },

                setStateA: function () {
                    isUserAdmin = false;
                    isPlayerAdmin = false;
                },

                setStateB: function () {
                    isUserAdmin = true;
                    isPlayerAdmin = false;
                },

                setStateC: function () {
                    isUserAdmin = false;
                    isPlayerAdmin = true;
                },

                setStateD: function () {
                    isUserAdmin = true;
                    isPlayerAdmin = true;
                },

                setStateE: function () {
                    isUserAdmin = true;
                    isPlayerAdmin = true;
                    isBuildingAdmin = true;
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

        });

        beforeEach(inject(function (_$compile_, _$rootScope_, _$httpBackend_) {
            $compile = _$compile_;
            $rootScope = _$rootScope_;
            $httpBackend = _$httpBackend_;
        }));

        it('shows the admin menu only if user has any admin role', function () {

            authServiceMock.setStateA();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).not.toContain('navigation.admin.admin');

            authServiceMock.setStateB();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.admin');

            authServiceMock.setStateC();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.admin');

            authServiceMock.setStateD();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.admin');

        });

        it('shows the admin player menu only if user has the appropiate role', function () {

            authServiceMock.setStateA();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).not.toContain('navigation.admin.player');

            authServiceMock.setStateB();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).not.toContain('navigation.admin.player');

            authServiceMock.setStateC();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.player');

            authServiceMock.setStateD();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.player');

        });

        it('shows the admin user menu only if user has the appropiate role', function () {

            authServiceMock.setStateA();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).not.toContain('navigation.admin.user');

            authServiceMock.setStateB();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.user');

            authServiceMock.setStateC();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).not.toContain('navigation.admin.user');

            authServiceMock.setStateD();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.user');

        });

        it('shows the building user menu only if user has the appropiate role', function () {

            authServiceMock.setStateE();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.building');

        });


        it('shows the admin goods menu', function () {

            authServiceMock.setStateA();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).not.toContain('navigation.admin.good');

            authServiceMock.setStateB();
            var element = $compile('<my-menu></my-menu>')($rootScope);
            $rootScope.$digest();
            expect(element.html()).toContain('navigation.admin.good');

        });

    });

});
