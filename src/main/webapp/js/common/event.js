/**
 * 表单重置事件
 */
$(document).on('click', 'form button[data-toggle=reset]', function (e) {
    var form = $(e.currentTarget).parents("form")[0];
    if (form == null) {
        return;
    }
    // input重置为""
    var inputs = $(form).find("input");
    for (var i = 0; i < inputs.length; i++) {
        $(inputs[i]).val("");
    }
    // select重置为第一个
    var selects = $(form).find("select");
    for (var i = 0; i < selects.length; i++) {
        var select = $(selects[i]);
        var option = select.find("option");
        if (option.length > 0) {
            select.selectpicker("val", option[0].value);
        }
    }

});