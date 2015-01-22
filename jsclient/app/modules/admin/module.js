/*jslint node: true */
'use strict';

define(['angular-ui-grid'],
    function () {
        return angular.module('Admin', ['DataGrid', 'ui.grid.selection']);
    }
);

