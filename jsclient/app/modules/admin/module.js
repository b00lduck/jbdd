/*jslint node: true */
'use strict';

define(['angular-ui-grid', 'AdminEditController', 'AdminListController', 'UserPlayerAssignGridDirectiveController',
        'myEditButtonsDirective', 'myFormFieldIdDirective', 'myFormFieldEnabledDirective',
        'myFormFieldNameAndDescDirective', 'myUserPlayerAssignGridsDirective', 'DataGridModule'],

    function (AdminEditController, AdminListController, UserPlayerAssignGridDirectiveController, myEditButtonsDirective,
              myFormFieldIdDirective, myFormFieldEnabledDirective, myFormFieldNameAndDescDirective,
              myUserPlayerAssignGridsDirective) {

        return angular.module('Admin', ['DataGrid', 'ui.grid.selection'])
            .controller('AdminEditController', AdminEditController)
            .controller('AdminListController', AdminListController)
            .controller('UserPlayerAssignGridDirectiveController', UserPlayerAssignGridDirectiveController);
        //.directive('myEditButtons', myEditButtonsDirective)
        //.directive('myFormFieldId', myFormFieldIdDirective)
        //.directive('myFormFieldEnabled', myFormFieldEnabledDirective)
        //.directive('myFormFieldNameAndDesc', myFormFieldNameAndDescDirective)
        //.directive('myUserPlayerAssignGrids', myUserPlayerAssignGridsDirective);

    }
);


