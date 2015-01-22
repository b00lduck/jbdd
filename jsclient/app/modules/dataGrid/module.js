/*jslint node: true */
'use strict';

define(['DataModule', 'DataGridDirective', 'DeleteModalInstanceController', 'angular-translate', 'angular-ui-grid', 'angular-touch'],

    function (DataGridDirective, DeleteModalInstanceController) {
        return angular.module('DataGrid', ['ui.grid', 'ui.grid.paging', 'pascalprecht.translate', 'ui.bootstrap', 'Data'])
            .directive('jbddDataGrid', DataGridDirective)
            .controller('DeleteModalInstanceController', DeleteModalInstanceController);

    });




