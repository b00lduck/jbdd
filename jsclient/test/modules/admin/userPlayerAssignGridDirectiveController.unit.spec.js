/*jslint node: true */
'use strict';

describe('UserPlayerAssignGridDirectiveController', function () {

    var scope,
        controller,
        dataServiceMock;

    function mockDataService($provide) {

        dataServiceMock = {
            getPlayersOfUser: function (userId) {
                return {
                    then: function () {
                    }
                };
            },
            getUnusedPlayers: function (userId) {
                return {
                    then: function () {
                    }
                };
            },
            assignPlayerToUser: function (userId, playerId) {
                return {
                    then: function () {
                    }
                };
            },
            removePlayerFromUser: function (userId, playerId) {
                return {
                    then: function () {
                    }
                };
            }
        };

        $provide.value('DataService', dataServiceMock);
    }


    beforeEach(function () {

        module('jbddApp', function ($provide, $translateProvider) {
            //mockTranslations($translateProvider);
            mockDataService($provide);
        });

        inject(function (_$rootScope_, _$controller_) {
            scope = {
                getUserId: function () {
                    return 1;
                }
            };
            controller = _$controller_('UserPlayerAssignGridDirectiveController', {
                '$scope': scope
            });

        });

    });

    it('sets the grid config of the left grid', function () {

        var gridOptionsWithoutRegisterApi = scope.leftGridOptions;
        delete gridOptionsWithoutRegisterApi.onRegisterApi;

        expect(gridOptionsWithoutRegisterApi).toEqual({
            columnDefs: [
                {name: 'id', width: 55},
                {name: 'nickname'}
            ],
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            multiSelect: true,
            modifierKeysToMultiSelect: true,
            noUnselect: false,
            enableSorting: false,
            enableSelectionBatchEvent: false
        });
    });

    it('sets the grid config of the right grid', function () {

        var gridOptionsWithoutRegisterApi = scope.leftGridOptions;
        delete gridOptionsWithoutRegisterApi.onRegisterApi;

        expect(gridOptionsWithoutRegisterApi).toEqual({
            columnDefs: [
                {name: 'id', width: 55},
                {name: 'nickname'}
            ],
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            multiSelect: true,
            modifierKeysToMultiSelect: true,
            noUnselect: false,
            enableSorting: false,
            enableSelectionBatchEvent: false
        });
    });

    function prefillGrids() {
        scope.rightGridOptions.onRegisterApi({
            selection: {
                getSelectedRows: function () {
                    return [
                        {id: 10},
                        {id: 11}
                    ];
                }
            }
        });

        scope.leftGridOptions.onRegisterApi({
            selection: {
                getSelectedRows: function () {
                    return [
                        {id: 20},
                        {id: 21}
                    ];
                }
            }
        });

        scope.rightGridOptions.data = [
            {id: 10},
            {id: 11},
            {id: 12}
        ];

        scope.leftGridOptions.data = [
            {id: 20},
            {id: 21},
            {id: 22}
        ];

    }

    it('moves selected items to the left', function () {

        prefillGrids();

        spyOn(dataServiceMock, 'assignPlayerToUser');

        scope.moveSelectionLeft();

        expect(dataServiceMock.assignPlayerToUser).toHaveBeenCalledWith(1, 10);
        expect(dataServiceMock.assignPlayerToUser).toHaveBeenCalledWith(1, 11);
        expect(dataServiceMock.assignPlayerToUser.calls.count()).toEqual(2);

    });

    it('moves selected items to the right', function () {

        prefillGrids();

        spyOn(dataServiceMock, 'removePlayerFromUser');

        scope.moveSelectionRight();

        expect(dataServiceMock.removePlayerFromUser).toHaveBeenCalledWith(1, 20);
        expect(dataServiceMock.removePlayerFromUser).toHaveBeenCalledWith(1, 21);
        expect(dataServiceMock.removePlayerFromUser.calls.count()).toEqual(2);

    });

    it('moves all items to the right', function () {

        prefillGrids();

        spyOn(dataServiceMock, 'removePlayerFromUser');

        scope.moveAllRight();

        expect(dataServiceMock.removePlayerFromUser).toHaveBeenCalledWith(1, 20);
        expect(dataServiceMock.removePlayerFromUser).toHaveBeenCalledWith(1, 21);
        expect(dataServiceMock.removePlayerFromUser).toHaveBeenCalledWith(1, 22);
        expect(dataServiceMock.removePlayerFromUser.calls.count()).toEqual(3);

    });


    it('moves all items to the left', function () {

        prefillGrids();

        spyOn(dataServiceMock, 'assignPlayerToUser');

        scope.moveAllLeft();

        expect(dataServiceMock.assignPlayerToUser).toHaveBeenCalledWith(1, 10);
        expect(dataServiceMock.assignPlayerToUser).toHaveBeenCalledWith(1, 11);
        expect(dataServiceMock.assignPlayerToUser).toHaveBeenCalledWith(1, 12);
        expect(dataServiceMock.assignPlayerToUser.calls.count()).toEqual(3);

    });

});
