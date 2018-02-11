jQuery.extend({
    // 获得项目配置文件
    config: function () {
        var configObj = null;
        BJUI.ajax('doajax', {
            url: 'json/config.json',
            loadingmask: false,
            async: false,
            okCallback: function (json, options) {
                configObj = json;
            }
        });
        return configObj;
    }
});