/*jslint node: true */
'use strict';

requirejs.config({
    paths: {
        angular: 'bower_components/angular/angular',
        'angular-route': 'bower_components/angular-route/angular-route',
        async: 'bower_components/requirejs-plugins/src/async',
        angularAMD: 'bower_components/angularAMD/angularAMD',
        'ngload': 'bower_components/angularAMD/ngload',
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
        UserPlayerAssignGridDirectiveController: 'modules/admin/controllers/userPlayerAssignGridDirective',
        MyEditButtonsDirective: 'modules/admin/directives/myEditButtons',
        MyFormFieldIdDirective: 'modules/admin/directives/myFormFieldId',
        MyFormFieldEnabledDirective: 'modules/admin/directives/myFormFieldEnabled',
        MyFormFieldNameAndDescDirective: 'modules/admin/directives/myFormFieldNameAndDesc',
        MyUserPlayerAssignGridsDirective: 'modules/admin/directives/myUserPlayerAssignGrids',
        LoginController: 'modules/authentication/controllers/login',
        MyLoginInfoDirective: 'modules/authentication/directives/myLoginInfo',
        AuthenticationService: 'modules/authentication/services/authentication',
        LocationService: 'modules/authentication/services/location',
        Base64Service: 'modules/base64/services/base64',
        DataService: 'modules/data/services/data',
        JbddDataGridDirective: 'modules/dataGrid/directives/jbddDataGrid',
        DeleteModalInstanceController: 'modules/dataGrid/controllers/deleteModalInstance',
        HomeController: 'modules/home/controllers/home',
        LanguageSwitcherDirective: 'modules/languageSwitcher/directive',
        MyMenuDirective: 'modules/navigation/directives/myMenu',
        MyNavbarDirective: 'modules/navigation/directives/myNavbar',
        UtilsService: 'modules/utils/service',
        domReady: 'bower_components/domReady/domReady',
        depend: 'bower_components/requirejs-plugins/src/depend',
        font: 'bower_components/requirejs-plugins/src/font',
        goog: 'bower_components/requirejs-plugins/src/goog',
        image: 'bower_components/requirejs-plugins/src/image',
        json: 'bower_components/requirejs-plugins/src/json',
        mdown: 'bower_components/requirejs-plugins/src/mdown',
        noext: 'bower_components/requirejs-plugins/src/noext',
        propertyParser: 'bower_components/requirejs-plugins/src/propertyParser',
        'Markdown.Converter': 'bower_components/requirejs-plugins/lib/Markdown.Converter',
        text: 'bower_components/requirejs-plugins/lib/text'
    },
    shim: {
        app: [
            'angularAMD'
        ],
        angularAMD: [
            'angular'
        ],
        'angular-route': [
            'angular'
        ],
        'angular-cookies': [
            'angular'
        ],
        bootstrap: {
            deps: [
                'jquery'
            ]
        }
    },
    deps: [
        'app'
    ],
    packages: [

    ]
});
