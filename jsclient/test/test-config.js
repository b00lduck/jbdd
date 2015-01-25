
var allTestFiles = [];
var TEST_REGEXP = /.*\.unit\.spec\.js$/;

var pathToModule = function(path) {
    return path.replace(/^\/base\//, '').replace(/\.js$/, '');
};

Object.keys(window.__karma__.files).forEach(function(file) {
    if (TEST_REGEXP.test(file)) {
        // Normalize paths to RequireJS module names.
        allTestFiles.push('../' + pathToModule(file));
    }
});

function testsRun() {
    window.__karma__.start();
}

requirejs.config({
    baseUrl: '/base/app',
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
        UtilsService: 'modules/utils/service'
    },

    shim: {
        app: ['angular-mocks'],
        angularAMD: ['angular'],
        angular: ['jquery'],
        'angular-translate': ['angular'],
        'angular-mocks': ['angular'],
        'angular-cookies': ['angular'],
        'angular-route': ['angular'],
        bootstrap: ['jquery'],
        'modules/languageSwitcher/templates/myLanguageSwitcher.html': ['angular'],
        'modules/authentication/directives/templates/myLoginInfo.html': ['angular'],
        'modules/authentication/controllers/views/login.html': ['angular'],
        'modules/navigation/directives/templates/myMenu.html': ['angular'],
        'modules/navigation/directives/templates/myNavbar.html': ['angular']
    },

    packages: [

    ],

    // dynamically load all test files
    deps: allTestFiles,

    // we have to kickoff jasmine, as it is asynchronous
    callback: testsRun
});
