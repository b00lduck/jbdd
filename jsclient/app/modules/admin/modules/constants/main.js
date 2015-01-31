/*jslint node: true */
'use strict';

define(['text!./templates/cellTemplateI18nField.html',
        'text!./templates/editCellTemplateNonZeroInt.html',
        'text!./templates/enabledCellTemplate.html',
        'text!./templates/enabledRowTemplate.html',
        'text!./templates/deletableCellTemplate.html'],
    function (cellTemplateI18nField,
              editCellTemplateNonZeroInt,
              enabledCellTemplate,
              enabledRowTemplate,
              deletableCellTemplate) {

    return {
        templates: {
            cellTemplateI18nField: cellTemplateI18nField,
            editCellTemplateNonZeroInt: editCellTemplateNonZeroInt,
            enabledCellTemplate: enabledCellTemplate,
            enabledRowTemplate: enabledRowTemplate,
            deletableCellTemplate: deletableCellTemplate
        }
    };

});



