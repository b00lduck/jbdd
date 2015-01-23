/*jslint node: true */
'use strict';

define(['app', 'angular-ui-grid', 'angular-ui-bootstrap-bower', 'DataService'], function (app) {

    app.directive('jbddDataGrid', ['$translate', '$http', '$templateCache', 'uiGridConstants', '$translate', '$rootScope', 'i18nService',
        '$modal', '$location', 'DataService',
        function ($http, $templateCache, uiGridConstants, $translate, $rootScope, i18nService, $modal, $location, DataService) {

            var enabledCellTemplate = '<div class="ui-grid-cell-contents ng-binding ng-scope"><span class="glyphicon glyphicon-{{ COL_FIELD == true ? \'ok-circle\' : \'ban-circle\'}}" aria-hidden="true"></span></div>';
            var deletableCellTemplate = '<div class="ui-grid-cell-contents ng-binding ng-scope"><button ng-if="COL_FIELD" class="ng-binding" ng-click="getExternalScopes().deleteItem(row.entity)">delete</button></div>';

            var enabledRowTemplate = '<div ng-dblClick="getExternalScopes().editItem(row.entity)" ng-class="{\'red\':row.entity.enabled == false, \'green\':row.entity.enabled == true }"><div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }" ui-grid-cell></div></div>';

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
                    cellTemplate: enabledCellTemplate
                },
                    {
                        name: 'deletable',
                        displayName: $translate.instant('dataGrid.columnHeader.fixed.deletable'),
                        width: 85,
                        cellTemplate: deletableCellTemplate
                    }];
            };

            var getColumnDefs = function (configuration) {

                var configuredColumnDefs = configuration.columnDefs,
                    resourceName = configuration.resourceName,
                    arrayLength = configuredColumnDefs.length,
                    i18nKey,
                    i;

                for (i = 0; i < arrayLength; i++) {
                    i18nKey = 'dataGrid.columnHeader.' + resourceName + '.' + configuredColumnDefs[i].name;
                    configuredColumnDefs[i].displayName = $translate.instant(i18nKey);
                }

                //noinspection NestedFunctionCallJS
                return getFixedColumnsLeft().concat(configuredColumnDefs).concat(getFixedColumnsRight());
            };

            var pagingOptions = {
                pageNumber: 1,
                pageSize: 25,
                sortColumn: null,
                sortDesc: false
            };

            return {

                restrict: 'E',

                templateUrl: 'modules/dataGrid/directives/templates/jbddDataGrid.html',

                link: function (scope, element, attrs) {

                    var createGridOptions = function () {

                        var configuration = scope[attrs.config]();

                        return {
                            pagingPageSizes: [25, 50, 75],
                            pagingPageSize: 25,
                            useExternalPaging: true,
                            useExternalSorting: true,
                            columnDefs: getColumnDefs(configuration),
                            rowTemplate: enabledRowTemplate,
                            onRegisterApi: function (gridApi) {

                                scope.gridApi = gridApi;

                                scope.gridApi.core.on.sortChanged(scope, function (grid, sortColumns) {
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

                                        pagingOptions.sortColumn = sortColumns[0].name;

                                    }
                                    getPage();
                                });

                                gridApi.paging.on.pagingChanged(scope, function (newPage, pageSize) {
                                    pagingOptions.pageNumber = newPage;
                                    pagingOptions.pageSize = pageSize;
                                    getPage();
                                });
                            }
                        };
                    };

                    var resourceName = scope[attrs.config]().resourceName;

                    var getPage = function () {

                        var promise = DataService.getList(resourceName, pagingOptions);

                        promise.then(
                            function (payload) {
                                scope.gridOptions.totalItems = payload.data.meta.totalItems;
                                scope.gridOptions.data = payload.data.data;
                            }
                        );

                        promise.catch(
                            function (httpError) {
                                throw httpError.status + ' : ' + httpError.data;
                            }
                        );

                    };

                    var refreshGridOptions = function () {
                        scope.gridOptions = createGridOptions(scope[attrs.config]());
                    };

                    scope.myViewModel = {

                        deleteItem: function (entity) {

                            var modalInstance = $modal.open({
                                templateUrl: 'modules/dataGrid/templates/deleteModal.html',
                                controller: 'DeleteModalInstanceController',
                                scope: scope,
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
                            var path = scope[attrs.config]().editUrlGetter(entity.id);
                            $location.path(path);
                        }

                    };

                    /*
                     $rootScope.$on('$translateChangeSuccess', function (event, value) {
                     var langSimple = value.language.substr(0, 2);
                     i18nService.setCurrentLang(langSimple);
                     refreshGridOptions();
                     });
                     */

                    refreshGridOptions();

                    getPage();

                }

            };

        }

    ]);

});
