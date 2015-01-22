/*jslint node: true */
'use strict';

define(['DataModule', 'DataGridDirective', 'DeleteModalInstanceController', 'angular-translate', 'angular-ui-grid',
        'angular-touch', 'angular-ui-bootstrap-bower'],

    function (DataGridDirective, DeleteModalInstanceController) {
        return angular.module('DataGrid', ['ui.grid', 'pascalprecht.translate', 'ui.bootstrap', 'Data'])
            //.directive('jbddDataGrid', DataGridDirective)
            .controller('DeleteModalInstanceController', DeleteModalInstanceController);

    });




