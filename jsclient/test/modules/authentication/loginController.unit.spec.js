/*jslint node: true */
'use strict';

define(['app', 'LoginController'], function () {

    describe('Login Controller', function () {

        var scope,
            $rootScope,
            controller,
            $q;

        function mockTranslations($translateProvider) {
            $translateProvider.translations('en_GB', {});
            $translateProvider.translations('de_DE', {});
        }

        function mockAuthenticationService($provide) {

            var loggedIn = false;

            var authServiceMock = {

                login: function (username, password) {
                    return $q(function (resolve, reject) {
                        if ((username === 'test') && (password === 'test')) {
                            loggedIn = true;
                            resolve();
                        } else {
                            loggedIn = false;
                            reject({
                                message: 'errors.login.message.failed',
                                details: 'errors.login.details.unauthorized'
                            });
                        }
                    });
                },

                isLoggedIn: function () {
                    return loggedIn;
                }

            };

            $provide.value('AuthenticationService', authServiceMock);
        }

        beforeEach(function () {

            module('jbddApp', function ($provide, $translateProvider) {
                mockTranslations($translateProvider);
                mockAuthenticationService($provide);
            });

            inject(function (_$rootScope_, _$q_, _$controller_) {
                $rootScope = _$rootScope_;
                $q = _$q_;
                scope = _$rootScope_.$new();
                controller = _$controller_('LoginController', {
                    '$scope': scope
                });

            });

        });

        it('sets the initial state', function () {
            expect(scope.showError).toBe(false);
            expect(scope.dataLoading).toBe(false);
        });

        it('shows error on wrong login', function () {

            scope.username = 'test';
            scope.password = 'padpassword';
            scope.login();

            $rootScope.$digest();

            expect(scope.showError).toBe(true);
            expect(scope.dataLoading).toBe(false);

        });

        it('shows no error on correct login', function () {

            scope.username = 'test';
            scope.password = 'test';
            scope.login();

            $rootScope.$digest();

            expect(scope.showError).toBe(false);
            expect(scope.dataLoading).toBe(false);
            expect(scope.errorObject).toEqual({});
        });

        it('shows error on empty username', function () {

            scope.username = '';
            scope.password = 'test';
            scope.login();

            $rootScope.$digest();

            expect(scope.showError).toBe(true);
            expect(scope.dataLoading).toBe(false);
            expect(scope.errorObject).toEqual({
                message: 'errors.login.message.failed',
                details: 'errors.login.details.unauthorized'
            });
        });

    });

});
