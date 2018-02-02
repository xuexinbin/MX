/**
 * 判断是否有功能权限
 */
var hasFunction = function (functionName) {
    // 后台权限带前缀
    var functions = localStorage.getItem("functions");
    if (functions == null || functions == "") {
        var list = JSON.parse(functions);
        for (var i = 0; i < list.length; i++) {
            if (functionName == list[i].name) {
                return true;
            }
        }
    }
    return false;
};