/*jslint node: true */
'use strict';

define(['AuthenticationService', 'AuthenticationDirective', 'AuthenticationController'],

    function(AuthenticationService, AuthenticationDirective, AuthenticationController) {
        return angular.module('Authentication', [])
            .service('Authentication', AuthenticationService)
            .directive('myLoginInfo', AuthenticationDirective)
            .controller('AuthenticationController', AuthenticationController);
    });
