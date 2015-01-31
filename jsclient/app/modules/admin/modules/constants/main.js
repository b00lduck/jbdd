/*jslint node: true */
'use strict';


define([], function () {

    return {
        templates: {
            cellTemplateI18nField: '<div class="ui-grid-cell-contents">{{getExternalScopes().getFieldByLanguage(COL_FIELD)}}</div>',
            editCellTemplateNonZeroInt: '<div><form name="inputForm"><input type="number" required="true" min="1" pattern="^[0-9]+$" ng-class="\'colt\' + col.uid" ui-grid-editor ng-model="MODEL_COL_FIELD"></form></div>',
            enabledCellTemplate: '<div class="ui-grid-cell-contents ng-binding ng-scope"><span class="glyphicon glyphicon-{{ COL_FIELD == true ? \'ok-circle\' : \'ban-circle\'}}" aria-hidden="true"></span></div>',
            enabledRowTemplate: '<div ng-dblClick="getExternalScopes().editItem(row.entity)" ng-class="{\'red\':row.entity.enabled == false, \'green\':row.entity.enabled == true }"><div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }" ui-grid-cell></div></div>',
            deletableCellTemplate: '<div class="ui-grid-cell-contents ng-binding ng-scope"><button ng-if="COL_FIELD" class="ng-binding" ng-click="getExternalScopes().deleteItem(row.entity)">delete</button></div>'
        }
    };

});



