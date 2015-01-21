/*jslint node: true */
'use strict';

angular.module('Authentication')

    .service('AuthenticationService',
    ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout', '$location', '$q', '$translate', 'Utils',
        function (Base64, $http, $cookieStore, $rootScope, $timeout, $location, $q, $translate, Utils) {
            var service = {};

            var globalAuthInfo = {
                currentUser: {}
            };

            var resetCredentials = function() {
                globalAuthInfo.currentUser = {
                    username: null,
                    roles: [],
                    auth: null
                };
                $cookieStore.put('globalAuthInfo', globalAuthInfo);
            };

            var tempAuthInfo = $cookieStore.get('globalAuthInfo');
            if ('undefined' === typeof (tempAuthInfo)) {
                resetCredentials();
            }

            service.login = function (username, password) {

                var auth = Base64.encode(username + ':' + password);

                $http.defaults.headers.common.Authorization = 'Basic ' + auth;

                return $q(function (resolve, reject) {

                    $http.get('http://localhost:8080/jbdd-restservice/authentication')
                        .success(function (data, status, headers, config) {

                            globalAuthInfo.currentUser.username = data.username;
                            globalAuthInfo.currentUser.roles = data.roles;
                            globalAuthInfo.currentUser.auth = auth;

                            $cookieStore.put('globalAuthInfo', globalAuthInfo);

                            resolve();

                        }).error(function (data, status, headers, config) {

                            var errorObject = {
                                message: '',
                                details: ''
                            };

                            resetCredentials();
                            delete $http.defaults.headers.common.Authorization;

                            if (null === data) {
                                if (0 === status) {
                                    errorObject.message = 'errors.login.message.failed';
                                    errorObject.details = 'errors.login.details.couldNotConnect';
                                } else {
                                    errorObject.message = 'errors.login.message.failed';
                                    errorObject.details = 'errors.login.details.unknown';
                                }
                            } else {
                                if (401 === status) {
                                    errorObject.message = 'errors.login.message.failed';
                                    errorObject.details = 'errors.login.details.unauthorized';
                                } else {
                                    errorObject.message = 'errors.login.message.failed';
                                    errorObject.details = data.status + ': ' + data.error;
                                }
                            }

                            reject(errorObject);

                        });

                });

            };

            service.logout = function () {
                $http.delete('http://localhost:8080/jbdd-restservice/authentication')
                    .success(function (response) {
                        delete $http.defaults.headers.common.Authorization;
                    }).finally(function () {
                        resetCredentials();
                        $location.path('/');
                    });
            };

            service.getUsername = function () {
                return globalAuthInfo.currentUser.username;
            };

            service.getRoles = function () {
                return globalAuthInfo.currentUser.roles;
            };

            service.isLoggedIn = function () {

                var tempAuthInfo = $cookieStore.get('globalAuthInfo');

                if (('undefined' !== typeof(globalAuthInfo.currentUser.username)) &&
                    (null !== globalAuthInfo.currentUser.username)) {
                    return true;
                }

                if ('undefined' === typeof tempAuthInfo) {
                    resetCredentials();
                    return false;
                }

                globalAuthInfo = tempAuthInfo;

                if (null !== globalAuthInfo.currentUser.username) {
                    $http.defaults.headers.common.Authorization = 'Basic ' + globalAuthInfo.currentUser.auth;
                    return true;
                }

                return false;

            };

            service.userIsUserAdmin = function () {
                return Utils.contains(globalAuthInfo.currentUser.roles, 'ROLE_ADMIN_USER');
            };

            service.userIsPlayerAdmin = function () {
                return Utils.contains(globalAuthInfo.currentUser.roles, 'ROLE_ADMIN_PLAYER');
            };

            service.userIsBuildingAdmin = function () {
                return Utils.contains(globalAuthInfo.currentUser.roles, 'ROLE_ADMIN_BUILDING');
            };

            service.userIsAnyAdmin = function () {
                return (service.userIsPlayerAdmin() || service.userIsUserAdmin() || service.userIsBuildingAdmin());
            };

            return service;

        }]);

