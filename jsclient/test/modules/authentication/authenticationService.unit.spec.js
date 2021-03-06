/*jslint node: true */
'use strict';

define(['app', 'AuthenticationService', 'Base64Service', 'angular-cookies'], function () {

    describe('AuthenticationService', function () {

        function mockTranslations($translateProvider) {
            $translateProvider.translations('en_GB', {});
            $translateProvider.translations('de_DE', {});
        }

        beforeEach(function () {
            module('jbddApp', function ($translateProvider) {
                mockTranslations($translateProvider);
            });
            module('ngCookies');
        });

        it('should exist', inject(function (AuthenticationService) {
            expect(AuthenticationService).toBeDefined();
        }));

        it('sets the inital state correctly on missing cookie', inject(function (AuthenticationService, $cookieStore) {
            checkLoggedOut(AuthenticationService, $cookieStore);
        }));

        it('sets the inital state correctly on existing cookie', inject(function (AuthenticationService, $cookieStore) {
            $cookieStore.put('globalAuthInfo', {
                currentUser: {
                    username: 'username',
                    roles: ['ROLE1'],
                    auth: 'bla'
                }
            });
            checkLoggedIn('username', ['ROLE1'], 'bla', AuthenticationService, $cookieStore);
        }));

        it('calls the rest service to login', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'username';
            var password = 'password';
            var roles = ['ROLE1', 'ROLE2'];

            login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64Service);

            expect(AuthenticationService.userIsUserAdmin()).toBe(false);
            expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
            expect(AuthenticationService.userIsAnyAdmin()).toBe(false);

            logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64Service, $cookieStore);

        }));

        it('calls the rest service to login as normal user correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'adminusername';
            var password = 'adminpassword';
            var roles = ['ROLE_PLAYER'];

            login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64Service);

            expect(AuthenticationService.userIsUserAdmin()).toBe(false);
            expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
            expect(AuthenticationService.userIsBuildingAdmin()).toBe(false);
            expect(AuthenticationService.userIsAnyAdmin()).toBe(false);

            logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64Service, $cookieStore);

        }));

        it('calls the rest service to login as full admin correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'adminusername';
            var password = 'adminpassword';
            var roles = ['ROLE_ADMIN_USER', 'ROLE_ADMIN_PLAYER'];

            login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64Service);

            expect(AuthenticationService.userIsUserAdmin()).toBe(true);
            expect(AuthenticationService.userIsPlayerAdmin()).toBe(true);
            expect(AuthenticationService.userIsBuildingAdmin()).toBe(false);
            expect(AuthenticationService.userIsAnyAdmin()).toBe(true);

            logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64Service, $cookieStore);

        }));

        it('calls the rest service to login as user admin correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'adminusername';
            var password = 'adminpassword';
            var roles = ['ROLE_ADMIN_USER'];

            login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64Service);

            expect(AuthenticationService.userIsUserAdmin()).toBe(true);
            expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
            expect(AuthenticationService.userIsBuildingAdmin()).toBe(false);
            expect(AuthenticationService.userIsAnyAdmin()).toBe(true);

            logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64Service, $cookieStore);

        }));

        it('calls the rest service to login as player admin correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'adminusername';
            var password = 'adminpassword';
            var roles = ['ROLE_ADMIN_PLAYER'];

            login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64Service);

            expect(AuthenticationService.userIsUserAdmin()).toBe(false);
            expect(AuthenticationService.userIsPlayerAdmin()).toBe(true);
            expect(AuthenticationService.userIsBuildingAdmin()).toBe(false);
            expect(AuthenticationService.userIsAnyAdmin()).toBe(true);

            logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64Service, $cookieStore);

        }));

        it('calls the rest service to login as building admin correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'adminusername';
            var password = 'adminpassword';
            var roles = ['ROLE_ADMIN_BUILDING'];

            login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64Service);

            expect(AuthenticationService.userIsUserAdmin()).toBe(false);
            expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
            expect(AuthenticationService.userIsBuildingAdmin()).toBe(true);
            expect(AuthenticationService.userIsAnyAdmin()).toBe(true);

            logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64Service, $cookieStore);

        }));

        it('responds correctly to 401 error', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'username';
            var password = 'password';

            $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
                var auth = Base64Service.encode(username + ':' + password);
                return headers.Authorization === 'Basic ' + auth;
            }).respond(401, {});

            AuthenticationService.login(username, password).then(function () {
                expect(false).toBe(true);
            }, function (errorObject) {
                expect(errorObject).toEqual({
                    message: 'errors.login.message.failed',
                    details: 'errors.login.details.unauthorized'
                });
            });

            $rootScope.$digest();
            $httpBackend.flush();

            checkLoggedOut(AuthenticationService, $cookieStore);

        }));

        it('responds correctly to other HTTP errors', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'username';
            var password = 'password';

            $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
                var auth = Base64Service.encode(username + ':' + password);
                return headers.Authorization === 'Basic ' + auth;
            }).respond(500, {'status': 'errorstatus', 'error': 'errortext'});

            AuthenticationService.login(username, password).then(function () {
                expect(false).toBe(true);
            }, function (errorObject) {
                expect(errorObject).toEqual({
                    message: 'errors.login.message.failed',
                    details: 'errorstatus: errortext'
                });
            });

            $rootScope.$digest();
            $httpBackend.flush();

            checkLoggedOut(AuthenticationService, $cookieStore);

        }));

        it('responds correctly to null response with unknown status', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'username';
            var password = 'password';

            $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
                var auth = Base64Service.encode(username + ':' + password);
                return headers.Authorization === 'Basic ' + auth;
            }).respond(666, null);

            AuthenticationService.login(username, password).then(function () {
                expect(false).toBe(true);
            }, function (errorObject) {
                expect(errorObject).toEqual({
                    message: 'errors.login.message.failed',
                    details: 'errors.login.details.unknown'
                });
            });

            $rootScope.$digest();
            $httpBackend.flush();

            checkLoggedOut(AuthenticationService, $cookieStore);

        }));

        it('responds correctly to null response status 0 (connection problem)', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64Service, $cookieStore) {

            var username = 'username';
            var password = 'password';

            $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
                var auth = Base64Service.encode(username + ':' + password);
                return headers.Authorization === 'Basic ' + auth;
            }).respond(0, null);

            AuthenticationService.login(username, password).then(function () {
                expect(false).toBe(true);
            }, function (errorObject) {
                expect(errorObject).toEqual({
                    message: 'errors.login.message.failed',
                    details: 'errors.login.details.couldNotConnect'
                });
            });

            $rootScope.$digest();
            $httpBackend.flush();

            checkLoggedOut(AuthenticationService, $cookieStore);

        }));

        function login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64Service) {

            $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
                var auth = Base64Service.encode(username + ':' + password);
                return headers.Authorization === 'Basic ' + auth;
            }).respond(200, {
                'username': username,
                'roles': roles
            });

            AuthenticationService.login(username, password);

            $rootScope.$digest();
            $httpBackend.flush();

            expect(AuthenticationService.isLoggedIn()).toBe(true);
            expect(AuthenticationService.getUsername()).toBe(username);
            expect(AuthenticationService.getRoles()).toEqual(roles);

        }

        function logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64Service, $cookieStore) {

            $httpBackend.expectDELETE('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
                var auth = Base64Service.encode(username + ':' + password);
                return headers.Authorization === 'Basic ' + auth;
            }).respond(200, {
                'username': null,
                'roles': null
            });

            AuthenticationService.logout();

            $rootScope.$digest();
            $httpBackend.flush();

            checkLoggedOut(AuthenticationService, $cookieStore);
        }

        function checkLoggedOut(AuthenticationService, $cookieStore) {
            var cookieAuthInfo = $cookieStore.get('globalAuthInfo');

            expect(cookieAuthInfo).toEqual({
                    currentUser: {
                        username: null,
                        roles: [],
                        auth: null
                    }
                }
            );

            expect(AuthenticationService.isLoggedIn()).toBe(false);
            expect(AuthenticationService.getUsername()).toBe(null);
            expect(AuthenticationService.getRoles()).toEqual([]);

            expect(AuthenticationService.userIsUserAdmin()).toBe(false);
            expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
            expect(AuthenticationService.userIsAnyAdmin()).toBe(false);
        }

        function checkLoggedIn(username, roles, auth, AuthenticationService, $cookieStore) {
            var cookieAuthInfo = $cookieStore.get('globalAuthInfo');

            expect(cookieAuthInfo.currentUser.username).toEqual(username);
            expect(cookieAuthInfo.currentUser.roles).toEqual(roles);
            expect(cookieAuthInfo.currentUser.auth).toEqual(auth);

            expect(AuthenticationService.isLoggedIn()).toBe(true);
            expect(AuthenticationService.getUsername()).toBe(username);
            expect(AuthenticationService.getRoles()).toEqual(roles);

            expect(AuthenticationService.userIsUserAdmin()).toBe(false);
            expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
            expect(AuthenticationService.userIsAnyAdmin()).toBe(false);
        }

    });

});
