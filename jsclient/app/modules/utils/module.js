/*jslint node: true */
'use strict';

define(['UtilsService'],

 function (UtilsService) {
     var app = angular.module('Utils', []);
     app.factory('Utils', UtilsService);
 });
