/*jslint node: true */
'use strict';

define(['angularAMD', 'adminConstants', 'DataService', 'angular-translate'], function (angularAMD, adminConstants) {

    angularAMD.controller('AdminListDataGridDirectiveController',
        ['$scope', '$rootScope', '$translate', 'DataService', '$location', '$modal', 'uiGridConstants', 'i18nService',
            function ($scope, $rootScope, $translate, DataService, $location, $modal, uiGridConstants, i18nService) {

                var resourceName,
                    configuration;

                var getFixedColumnsLeft = function () {
                    return [{
                        name: 'id',
                        displayName: $translate.instant('dataGrid.columnHeader.fixed.id'),
                        width: 55
                    }];
                };

                var getFixedColumnsRight = function () {
                    return [{
                        name: 'enabled',
                        displayName: $translate.instant('dataGrid.columnHeader.fixed.enabled'),
                        width: 85,
                        cellTemplate: adminConstants.templates.enabledCellTemplate
                    },
                        {
                            name: 'deletable',
                            displayName: $translate.instant('dataGrid.columnHeader.fixed.deletable'),
                            width: 85,
                            cellTemplate: adminConstants.templates.deletableCellTemplate
                        }];
                };

                var getColumnDefs = function (configuration) {

                    var resourceName = configuration.resourceName,
                        arrayLength = configuration.columnDefs.length,
                        i18nKey,
                        i,
                        reconfiguredColumnDefs = [];


                    for (i = 0; i < arrayLength; i++) {
                        reconfiguredColumnDefs[i] = {};
                        i18nKey = 'dataGrid.columnHeader.' + resourceName + '.' + configuration.columnDefs[i].name;
                        reconfiguredColumnDefs[i].displayName = $translate.instant(i18nKey);
                        reconfiguredColumnDefs[i].name = configuration.columnDefs[i].name;
                        if (true === configuration.columnDefs[i].i18nField) {
                            reconfiguredColumnDefs[i].cellTemplate = adminConstants.templates.cellTemplateI18nField;
                            reconfiguredColumnDefs[i].i18nField = true;
                        }
                    }

                    //noinspection NestedFunctionCallJS
                    return getFixedColumnsLeft().concat(reconfiguredColumnDefs).concat(getFixedColumnsRight());
                };

                var pagingOptions = {
                    pageNumber: 1,
                    pageSize: 25,
                    sortColumn: null,
                    sortDesc: false

                };

                var createGridOptions = function () {

                    var configuration = $scope.config;

                    return {
                        pagingPageSizes: [25, 50, 75],
                        pagingPageSize: 25,
                        useExternalPaging: true,
                        useExternalSorting: true,
                        columnDefs: getColumnDefs(configuration),
                        rowTemplate: adminConstants.templates.enabledRowTemplate,
                        onRegisterApi: function (gridApi) {

                            $scope.gridApi = gridApi;

                            $scope.gridApi.core.on.sortChanged($scope, function (grid, sortColumns) {
                                if (0 === sortColumns.length) {
                                    pagingOptions.sortColumn = null;
                                    pagingOptions.sortDesc = false;
                                } else {

                                    switch (sortColumns[0].sort.direction) {
                                        case uiGridConstants.DESC:
                                            pagingOptions.sortDesc = true;
                                            break;
                                        default:
                                            pagingOptions.sortDesc = false;
                                            break;
                                    }

                                    pagingOptions.sortColumn = sortColumns[0];

                                }
                                getPage();
                            });

                            gridApi.paging.on.pagingChanged($scope, function (newPage, pageSize) {
                                pagingOptions.pageNumber = newPage;
                                pagingOptions.pageSize = pageSize;
                                getPage();
                            });
                        }
                    };
                };

                var getPage = function () {

                    var promise = DataService.getList(resourceName, pagingOptions);

                    promise.then(
                        function (payload) {
                            $scope.gridOptions.totalItems = payload.data.meta.totalItems;
                            $scope.gridOptions.data = payload.data.data;
                        }
                    );

                    promise.catch(
                        function (httpError) {
                            throw httpError.status + ' : ' + httpError.data;
                        }
                    );

                };

                var refreshGridOptions = function () {
                    $scope.gridOptions = createGridOptions($scope.config);
                };

                $scope.myViewModel = {

                    deleteItem: function (entity) {

                        var modalInstance = $modal.open({
                            templateUrl: 'modules/admin/modules/listDataGrid/directives/templates/deleteModal.html',
                            controller: 'DeleteModalController',
                            scope: $scope,
                            resolve: {
                                entity: function () {
                                    return entity;
                                }
                            }
                        });

                        modalInstance.result.then(function () {
                            return DataService.deleteItem(resourceName, entity.id);
                        }).then(function () {
                            getPage();
                        });

                    },

                    editItem: function (entity) {
                        var path = $scope.config.editUrlGetter(entity.id);
                        $location.path(path);
                    },

                    getFieldByLanguage: function (obj) {
                        return obj[$translate.use()];
                    }

                };

                $rootScope.$on('$translateChangeSuccess', function (event, value) {
                    var langSimple = value.language.substr(0, 2);
                    i18nService.setCurrentLang(langSimple);
                    refreshGridOptions();

                    getPage();
                });

                this.init = function () {

                    configuration = $scope.config;

                    resourceName = configuration.resourceName;

                    refreshGridOptions();

                    getPage();

                };

            }

        ]);

});
