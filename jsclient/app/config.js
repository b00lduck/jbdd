/*jslint node: true */
'use strict';

requirejs.config({
    paths: {
        angular: 'bower_components/angular/angular',
        'angular-route': 'bower_components/angular-route/angular-route',
        async: 'bower_components/requirejs-plugins/src/async',
        angularAMD: 'bower_components/angularAMD/angularAMD',
        ngload: 'bower_components/angularAMD/ngload',
        'angular-cookies': 'bower_components/angular-cookies/angular-cookies',
        'angular-mocks': 'bower_components/angular-mocks/angular-mocks',
        'angular-touch': 'bower_components/angular-touch/angular-touch',
        'angular-translate': 'bower_components/angular-translate/angular-translate',
        'angular-translate-loader-static-files': 'bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files',
        'angular-ui-bootstrap-bower': 'bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls',
        'angular-ui-grid': 'bower_components/angular-ui-grid/ui-grid',
        bootstrap: 'bower_components/bootstrap/dist/js/bootstrap',
        jquery: 'bower_components/jquery/dist/jquery',
        AdminEditController: 'modules/admin/controllers/adminEdit',
        AdminListController: 'modules/admin/controllers/adminList',
        LoginController: 'modules/authentication/controllers/login',
        MyLoginInfoDirective: 'modules/authentication/directives/myLoginInfo',
        AuthenticationService: 'modules/authentication/services/authentication',
        DataService: 'modules/data/services/data',
        DeleteModalInstanceController: 'modules/dataGrid/controllers/deleteModalInstance',
        HomeController: 'modules/home/controllers/home',
        LanguageSwitcherDirective: 'modules/languageSwitcher/directive',
        MyMenuDirective: 'modules/navigation/directives/myMenu',
        MyNavbarDirective: 'modules/navigation/directives/myNavbar',
        Base64Service: 'modules/utils/services/base64',
        ContainsService: 'modules/utils/services/contains'
    },
    packages: [
        {
            name: 'adminBuyableCostEditor',
            location: 'modules/admin/modules/buyableCostEditor'
        },
        {
            name: 'adminFormElements',
            location: 'modules/admin/modules/formElements'
        },
        {
            name: 'adminUserPlayerAssignGrid',
            location: 'modules/admin/modules/userPlayerAssignGrid'
        },
        {
            name: 'adminDoubleGrid',
            location: 'modules/admin/modules/doubleGrid'
        },
        {
            name: 'adminListDataGrid',
            location: 'modules/admin/modules/listDataGrid'
        },
        {
            name: 'adminConstants',
            location: 'modules/admin/modules/constants'
        }
    ],
    shim: {
        app: [
            'angularAMD'
        ],
        angularAMD: [
            'angular'
        ],
        bootstrap: [
            'jquery'
        ]
    },
    deps: [
        'app'
    ]
});
