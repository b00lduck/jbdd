/*jslint node: true */
'use strict';

requirejs.config({
    paths: {
        myComponent: 'app/app.js',
        angular: 'bower_components/angular/angular',
        'angular-cookies': 'bower_components/angular-cookies/angular-cookies',
        'angular-mocks': 'bower_components/angular-mocks/angular-mocks',
        'angular-route': 'bower_components/angular-route/angular-route',
        'angular-touch': 'bower_components/angular-touch/angular-touch',
        'angular-translate': 'bower_components/angular-translate/angular-translate',
        'angular-translate-loader-static-files': 'bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files',
        'angular-ui-bootstrap-bower': 'bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls',
        'angular-ui-grid': 'bower_components/angular-ui-grid/ui-grid',
        angularAMD: 'bower_components/angularAMD/angularAMD',
        bootstrap: 'bower_components/bootstrap/dist/js/bootstrap',
        jquery: 'bower_components/jquery/dist/jquery',
        AdminModule: 'modules/admin/module',
        AdminEditController: 'modules/admin/controllers/adminEdit.controller',
        AdminListController: 'modules/admin/controllers/adminList.controller',
        UserPlayerAssignGridDirectiveController: 'modules/admin/controllers/userPlayerAssignGridDirective.controller',
        myEditButtonsDirective: 'modules/admin/directives/myEditButtons.directive',
        myFormFieldIdDirective: 'modules/admin/directives/myFormFieldId.directive',
        myFormFieldEnabledDirective: 'modules/admin/directives/myFormFieldEnabled.directive',
        myFormFieldNameAndDescDirective: 'modules/admin/directives/myFormFieldNameAndDesc.directive',
        myUserPlayerAssignGridsDirective: 'modules/admin/directives/myUserPlayerAssignGrids.directive',
        AuthenticationModule: 'modules/authentication/module',
        AuthenticationController: 'modules/authentication/controller',
        AuthenticationDirective: 'modules/authentication/directive',
        AuthenticationService: 'modules/authentication/service',
        Base64Module: 'modules/base64/module',
        Base64Service: 'modules/base64/service',
        DataModule: 'modules/data/module',
        DataService: 'modules/data/service',
        DataGridModule: 'modules/dataGrid/module',
        DataGridDirective: 'modules/dataGrid/directive',
        DeleteModalInstanceController: 'modules/dataGrid/controller',
        HomeModule: 'modules/home/module',
        HomeController: 'modules/home/controller',
        LanguageSwitcherModule: 'modules/home/languageSwitcher/module',
        LanguageSwitcherDirective: 'modules/home/languageSwitcher/directive',
        NavigationModule: 'modules/navigation/module',
        NavigationDirective: 'modules/navigation/directive',
        UtilsModule: 'modules/utils/module',
        UtilsService: 'modules/utils/service'
    },
    shim: {
        angularAMD: [
            'angular'
        ],
        'angular-route': [
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


/*

 <!-- module utils -->
 <script src="modules/utils/module.js"></script>
 <script src="modules/utils/service.js"></script>

 <!-- module base64 -->
 <script src="modules/base64/module.js"></script>
 <script src="modules/base64/service.js"></script>

 <!-- module dataService -->
 <script src="modules/data/module.js"></script>
 <script src="modules/data/service.js"></script>

 <!-- module authentication -->
 <script src="modules/authentication/module.js"></script>
 <script src="modules/authentication/service.js"></script>
 <script src="modules/authentication/directives.js"></script>
 <script src="modules/authentication/controllers.js"></script>

 <!-- module dataGrid -->
 <script src="modules/dataGrid/module.js"></script>
 <script src="modules/dataGrid/controllers.js"></script>
 <script src="modules/dataGrid/directives.js"></script>

 <!-- module languageSwitcher -->
 <script src="modules/languageSwitcher/module.js"></script>
 <script src="modules/languageSwitcher/directives.js"></script>

 <!-- module navigation -->
 <script src="modules/navigation/module.js"></script>
 <script src="modules/navigation/directives.js"></script>

 <!-- module home -->
 <script src="modules/home/module.js"></script>
 <script src="modules/home/controllers.js"></script>

 <!-- module admin -->
 <script src="modules/admin/admin.module.js"></script>
 <script src="modules/admin/directives/myEditButtons.directive.js"></script>
 <script src="modules/admin/directives/myUserPlayerAssignGrids.directive.js"></script>
 <script src="modules/admin/controllers/adminList.controller.js"></script>
 <script src="modules/admin/controllers/adminEdit.controller.js"></script>
 <script src="modules/admin/controllers/userPlayerAssignGridDirective.controller.js"></script>
 */
