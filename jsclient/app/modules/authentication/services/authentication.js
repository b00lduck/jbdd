/*jslint node: true */
'use strict';

define(['angularAMD', 'Base64Service', 'ContainsService', 'angular-cookies'], function (angularAMD) {

    angularAMD.service('AuthenticationService', ['Base64Service', '$http', '$cookieStore', '$location', '$q', 'ContainsService', '$rootScope',
        function (Base64Service, $http, $cookieStore, $location, $q, ContainsService, $rootScope) {

            var service = {};

            var globalAuthInfo = {
                currentUser: {}
            };

            var resetCredentials = function () {
                globalAuthInfo.currentUser = {
                    username: null,
                    roles: [],
                    auth: null
                };
                $cookieStore.put('globalAuthInfo', globalAuthInfo);
            };

            function checkLocation() {

                // redirect to login page if not logged in
                if ('/' !== $location.path() && !service.isLoggedIn()) {
                    $location.path('/');
                }

                if ('/' === $location.path() && service.isLoggedIn()) {
                    $location.path('/home');
                }

                if ('' === $location.path() && service.isLoggedIn()) {
                    $location.path('/home');
                }

            }

            service.login = function (username, password) {

                var auth = Base64Service.encode(username + ':' + password);

                $http.defaults.headers.common.Authorization = 'Basic ' + auth;

                return $q(function (resolve, reject) {

                    $http.get('http://localhost:8080/authentication')
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
                $http.delete('http://localhost:8080/authentication')
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

	        service.hasRole = function(role) {
		        return ContainsService.contains(globalAuthInfo.currentUser.roles, role);
	        };

	        service.hasAnyRole = function(rolesArray) {
		        var arrayLength = rolesArray.length,
			        i;
		        for (i = 0; i < arrayLength; i++) {
			        if (ContainsService.contains(globalAuthInfo.currentUser.roles, rolesArray[i])) {
				        return true;
			        }
		        }
		        return false;
	        };

            // Check Auth info on startup
            if ('undefined' === typeof ($cookieStore.get('globalAuthInfo'))) {
                resetCredentials();
            }

            // Set location change event
            $rootScope.$on('$locationChangeStart', function (event, next, current) {
                checkLocation();
            });
            checkLocation();




            return service;
        }]);


});


