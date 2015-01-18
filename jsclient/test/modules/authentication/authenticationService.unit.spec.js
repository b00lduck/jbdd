/*jslint node: true */
'use strict';

describe('AuthenticationService', function () {

    function mockTranslations($translateProvider) {
        $translateProvider.translations('en_GB', {});
        $translateProvider.translations('de_DE', {});
    }

    beforeEach(function () {
        module('jbddApp', function ($translateProvider) {
            mockTranslations($translateProvider);
        });
    });

    it('should exist', inject(function (AuthenticationService) {
        expect(AuthenticationService).toBeDefined();
    }));

    it('sets the inital state correctly', inject(function (AuthenticationService, $cookieStore) {
        checkLoggedOut(AuthenticationService, $cookieStore);
    }));

    it('calls the rest service to login', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'username';
        var password = 'password';
        var roles = ['ROLE1', 'ROLE2'];

        login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64);

        expect(AuthenticationService.userIsUserAdmin()).toBe(false);
        expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
        expect(AuthenticationService.userIsAnyAdmin()).toBe(false);

        logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64, $cookieStore);

    }));

    it('calls the rest service to login as normal user correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'adminusername';
        var password = 'adminpassword';
        var roles = ['ROLE_PLAYER'];

        login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64);

        expect(AuthenticationService.userIsUserAdmin()).toBe(false);
        expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
        expect(AuthenticationService.userIsAnyAdmin()).toBe(false);

        logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64, $cookieStore);

    }));

    it('calls the rest service to login as full admin correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'adminusername';
        var password = 'adminpassword';
        var roles = ['ROLE_ADMIN_USER', 'ROLE_ADMIN_PLAYER'];

        login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64);

        expect(AuthenticationService.userIsUserAdmin()).toBe(true);
        expect(AuthenticationService.userIsPlayerAdmin()).toBe(true);
        expect(AuthenticationService.userIsAnyAdmin()).toBe(true);

        logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64, $cookieStore);

    }));

    it('calls the rest service to login as user admin correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'adminusername';
        var password = 'adminpassword';
        var roles = ['ROLE_ADMIN_USER'];

        login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64);

        expect(AuthenticationService.userIsUserAdmin()).toBe(true);
        expect(AuthenticationService.userIsPlayerAdmin()).toBe(false);
        expect(AuthenticationService.userIsAnyAdmin()).toBe(true);

        logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64, $cookieStore);

    }));

    it('calls the rest service to login as player admin correctly', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'adminusername';
        var password = 'adminpassword';
        var roles = ['ROLE_ADMIN_PLAYER'];

        login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64);

        expect(AuthenticationService.userIsUserAdmin()).toBe(false);
        expect(AuthenticationService.userIsPlayerAdmin()).toBe(true);
        expect(AuthenticationService.userIsAnyAdmin()).toBe(true);

        logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64, $cookieStore);

    }));

    it('responds correctly to 401 error', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'username';
        var password = 'password';

        $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
            var auth = Base64.encode(username + ':' + password);
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

    it('responds correctly to other HTTP errors', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'username';
        var password = 'password';

        $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
            var auth = Base64.encode(username + ':' + password);
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

    it('responds correctly to null response with unknown status', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'username';
        var password = 'password';

        $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
            var auth = Base64.encode(username + ':' + password);
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

    it('responds correctly to null response status 0 (connection problem)', inject(function (AuthenticationService, $httpBackend, $rootScope, Base64, $cookieStore) {

        var username = 'username';
        var password = 'password';

        $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
            var auth = Base64.encode(username + ':' + password);
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

    function login(username, password, roles, $httpBackend, $rootScope, AuthenticationService, Base64) {

        $httpBackend.expectGET('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
            var auth = Base64.encode(username + ':' + password);
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

    function logout(username, password, $httpBackend, $rootScope, AuthenticationService, Base64, $cookieStore) {

        $httpBackend.expectDELETE('http://localhost:8080/jbdd-restservice/authentication', function (headers) {
            var auth = Base64.encode(username + ':' + password);
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

});
