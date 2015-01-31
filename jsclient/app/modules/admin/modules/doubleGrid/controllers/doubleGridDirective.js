/*jslint node: true */
'use strict';

define(['angularAMD'], function (angularAMD) {

    angularAMD.controller('DoubleGridDirectiveController', ['$scope', '$q', '$rootScope', '$translate',
        function ($scope, $q, $rootScope, $translate) {

            var leftGridApi,
                rightGridApi,
                leftGridConfig,
                rightGridConfig,
                moveItemToLeft,
                moveItemToRight,
                getLeftGridData,
                getRightGridData;

            function internalGetLeftGridData() {
                getLeftGridData().then(
                    function (payload) {
                        leftGridConfig.totalItems = payload.data.meta.totalItems;
                        leftGridConfig.data = payload.data.data;
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            function internalGetRightGridData() {
                getRightGridData().then(
                    function (payload) {
                        rightGridConfig.totalItems = payload.data.meta.totalItems;
                        rightGridConfig.data = payload.data.data;
                    },
                    function () {
                        window.alert('ERROR');
                        // TODO: proper error handling
                    });
            }

            function refresh() {
                internalGetLeftGridData();
                internalGetRightGridData();
            }

            function getMoveArrayLeftPromise(rows) {
                var promises = [],
                    length = rows.length,
                    i,
                    promise;

                for (i = 0; i < length; i++) {
                    promise = moveItemToLeft(rows[i]);
                    promises.push(promise);
                }

                return $q.all(promises);
            }

            function getMoveArrayRightPromise(rows) {
                var promises = [],
                    length = rows.length,
                    i,
                    promise;

                for (i = 0; i < length; i++) {
                    promise = moveItemToRight(rows[i]);
                    promises.push(promise);
                }

                return $q.all(promises);
            }

            function moveArrayLeft(rows) {
                var promise = getMoveArrayLeftPromise(rows);
                promise.then(function () {
                    rightGridApi.selection.clearSelectedRows();
                });
                promise.catch(function () {
                    window.alert('ERROR');
                    // TODO: proper error handling
                });
                promise.finally(function () {
                    refresh();
                });
            }

            function moveArrayRight(rows) {
                var promise = getMoveArrayRightPromise(rows);
                promise.then(function () {
                    leftGridApi.selection.clearSelectedRows();
                });
                promise.catch(function () {
                    window.alert('ERROR');
                    // TODO: proper error handling
                });
                promise.finally(function () {
                    refresh();
                });
            }

            var cellTemplateNonZeroInt = '<div><form name="inputForm"><input type="number" required="true" min="1" pattern="^[0-9]+$" ng-class="\'colt\' + col.uid" ui-grid-editor ng-model="MODEL_COL_FIELD"></form></div>';

            var cellTemplateI18nField = '<div class="ui-grid-cell-contents">{{getExternalScopes().getFieldByLanguage(COL_FIELD)}}</div>';

            function parseGridConfig(config) {
                var columnDefs = config.columnDefs;
                var arrayLength = columnDefs.length;
                var i;
                for (i = 0; i < arrayLength; i++) {

                    if (columnDefs[i].i18nField === true) {
                        columnDefs[i].cellTemplate = cellTemplateI18nField;
                    }

                    if (columnDefs[i].enableCellEdit !== true) {
                        columnDefs[i].enableCellEdit = false;
                    }

                    if (typeof columnDefs[i].validator !== 'undefined') {
                        switch (columnDefs[i].validator) {
                            case 'nonzero_int':
                                columnDefs[i].editableCellTemplate = cellTemplateNonZeroInt;
                                break;
                            default:
                                throw ('unkown validator ' + columnDefs[i].validator);
                        }
                    }
                }
                return config;
            }

            this.init = function () {

                leftGridConfig = $scope.config.leftGridConfig;
                rightGridConfig = $scope.config.rightGridConfig;
                moveItemToLeft = $scope.config.moveItemToLeft;
                moveItemToRight = $scope.config.moveItemToRight;
                getLeftGridData = $scope.config.getLeftGridData;
                getRightGridData = $scope.config.getRightGridData;

                leftGridConfig.onRegisterApi = function (gridApi) {
                    leftGridApi = gridApi;
                    $scope.config.onRegisterLeftApi(gridApi);
                };

                rightGridConfig.onRegisterApi = function (gridApi) {
                    rightGridApi = gridApi;
                    $scope.config.onRegisterRightApi(gridApi);
                };

                $scope.leftGridOptions = parseGridConfig(leftGridConfig);

                $scope.rightGridOptions = parseGridConfig(rightGridConfig);

                refresh();

	            $rootScope.$on('$translateChangeSuccess', function (event, value) {
					$scope.panelTitle = $translate.instant($scope.config.panelTitle);
	            });
	            $scope.panelTitle = $translate.instant($scope.config.panelTitle);

            };

            this.init();

            $scope.moveSelectionLeft = function () {
                var rows = rightGridApi.selection.getSelectedRows();
                moveArrayLeft(rows);
            };

            $scope.moveSelectionRight = function () {
                var rows = leftGridApi.selection.getSelectedRows();
                moveArrayRight(rows);
            };

            $scope.moveAllLeft = function () {
                var rows = rightGridConfig.data;
                moveArrayLeft(rows);
            };

            $scope.moveAllRight = function () {
                var rows = leftGridConfig.data;
                moveArrayRight(rows);
            };

            $scope.langHelper = {
                getFieldByLanguage: function (obj) {
                    return obj[$translate.use()];
                }
            };

        }
    ]);
});
