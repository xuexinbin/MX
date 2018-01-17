//构建error提示
function error(id,msg) {
    $(id).hide();
    $(id).parent().append("<div class='error-msg'>"+msg+"</div>");
}

//构建空页面提示
function empty(id,msg) {
    $(id).hide();
    $(id).parent().append("<div class='empty-msg'>"+msg+"<div class='button'>刷新页面</div></div>");
}

//页面跳转
function goto(url,seconds) {
    if(seconds != undefined){
        setTimeout(function(){
            window.location.href = url;
        },seconds*1000);
    }else {
        window.location.href = url;
    }
}

//json分组
function groupBy( array , f ,otherkey) {
    var groups = {};
    array.forEach( function( o ) {
        var group = JSON.stringify( f(o) ).replace(/\"/g,"");
        groups[group] = groups[group] || [];
        groups[group].push( o );
    });
    return Object.keys(groups).map( function( group ) {
        var result={};
        var keyvalue=group;
        result.key = group;
        if(otherkey != undefined) {
            var otherkeyarray = otherkey.split("|");
            otherkeyarray.forEach(function (t) {
                result[t] = groups[group][0][t];
            });
        }
        result.value = groups[group];
        return result;
    });
}

//规格转换为文字
//x|尺寸
//x=0 给定尺寸 可以自定义
// =1 给定尺寸 不可以自定义
// =2 未给定尺寸 需要自定义
//尺寸 长xx;宽xx;高xx;直径xx;其他文字
function sizeConvertStr(size) {
    var split=size.split("|");
    if(split[0]!="2") {
        return split[1].replace(/:/g,"").replace(/;/g, "*");
    }else
    {
        if(split[1]!="长:CM;宽:CM;高:CM")
        {
            return split[1].replace(/:/g,"").replace(/;/g, "*");
        }else {
            return "各餐厅尺寸不一";
        }
    }
}

//规格转换为HTML
function sizeConvertHtml(size,id){
    var split=size.split("|");
    var type=split[0];
    var str="";
    var lwh=split[1].split(";");
    $.each(lwh,function(index,item){
        var tempsplit = item.split(":");
        var title=tempsplit[0];
        var value="";
        if(tempsplit.length>1){
            value=tempsplit[1];
            if(value=="CM")value="";
        }
        var placeHolder=title;
        var enable="";
        if(type==1){
            enable="readonly=true";
        }
        if(title!="直径")placeHolder=placeHolder+"度";

        str+="<div class=\"flex space-between margin-t-b-5\"><span style='width:25%'>"+title+"：</span><input "+enable+" name=\"size_"+id+"\" old='"+value+"' value='"+value+"' type=\"text\" class=\"cm\" placeholder=\""+placeHolder+"，单位CM\"></div>"
    });
    return str;
}

function sizeConvertAcreage(type) {
    var acreage=1.00;
    $.each($("input[name=detailsize]"),function (index,item) {
        acreage = acreage / 100 * $(this).val().replace("CM","");
    });
    if(type==0) {
        return Math.ceil(acreage).toFixed(2);
    }else
    {
        return acreage.toFixed(2);
    }
}

//规格输入框转换为提交尺寸
function htmlCovnerSize(name1,name2)
{
    var error=false;
    var size = $("input[name="+name1+"]").val();
    $.each($("input[name="+name2+"]"),function (index,item) {
        var value = $(item).val().replace("CM","");
        if(value==""||value==0){
            alert('尺寸输入不正确，请重新输入');
            $(item).trigger('focus');
            error=true;
            return "";
        }
        var placeHolder=$(item).attr("placeholder");
        if(placeHolder.indexOf("长")>=0)
        {
            size = size.replace(/长:[\d.]*CM/ig,"长:"+value+"CM");
        }else if(placeHolder.indexOf("宽")>=0)
        {
            size = size.replace(/宽:[\d.]*CM/ig,"宽:"+value+"CM");
        }else if(placeHolder.indexOf("高")>=0)
        {
            size = size.replace(/高:[\d.]*CM/ig,"高:"+value+"CM");
        }else if(placeHolder.indexOf("直径")>=0)
        {
            size = size.replace(/直径:[\d.]*CM/ig,"直径:"+value+"CM");
        }
    });
    if(error)
    {
        return "";
    }else {
        $("input[name="+name1+"]").val(size);
        return size;
    }
}

//规格转换为输入框

//材质说明
function materialConvert(data) {
    var str="";
    if(data.material_key != null)
    {
        str = data.material_key
    }
    if(data.material_memo != null)
    {
        str += data.material_memo
    }
    return str;
}

//空值转换
function  format(value) {
    if(value != undefined && value != null)
    {
        return value;
    }else
    {
        return "";
    }
}

//plan图片转换
function  skuImage(data) {
    if(data.plan_good_image != undefined && data.plan_good_image != null)
    {
        return data.plan_good_image;
    }else
    {
        return data.sku_image;
    }
}

//时间格式转换
function dataFormat(date, fmt) {
    var date = new Date(Date.parse(date.replace(/-/g, "/")));
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function isinCart(plan_id,sku_id) {
    if (plan_id != 0) {
        var item = $.grep(cart, function (item) {
            return item.plan_id == plan_id && item.sku_id == sku_id;
        });
        return item.length > 0;
    }else{
        var item = $.grep(cart, function (item) {
            return item.sku_id == sku_id;
        });
        return item.length > 0;
    }
}

function convertSupperSelect(supplier_id,sku_id,supplier_list)
{
    var html="";
    $.each(supplier_list,function (index,item) {
        if(item.sku_id == undefined)
        {
            html = html + "<option value = '" + item.id + "'>" + item.name + "</option>"
        }else {
            if (item.sku_id == sku_id) {
                if (item.supplier_id == supplier_id) {
                    html = html + "<option value = '" + item.supplier_id + "' selected>" + item.name + "</option>"
                } else {
                    html = html + "<option value = '" + item.supplier_id + "'>" + item.name + "</option>"
                }
            }
        }
    });
    return html;
}

function convertPriceVersionSelect(price_version,version_list)
{
    var html="";
    $.each(version_list,function (index,item) {
        if (item.key == price_version) {
            html = html + "<option value = '" + item.key + "' selected>" + item.key + "</option>"
        } else {
            html = html + "<option value = '" + item.key + "'>" + item.key + "</option>"
        }
    });
    return html;
}

function grepsplit(datalist,id,supplier) {
    var data = {};
    if (datalist != undefined) {

        data.data = $.grep(datalist, function (item) {
            return item.order_newlist_detail_id == id && item.edit_type==1;
        });
        data.supplier = supplier;
    } else {
        data.data = [];
    }
    return data;
}

function grepreplace(datalist,id) {
    return $.grep(datalist, function (item) {
            return item.order_newlist_detail_id == id && item.edit_type==0;
        });
}

if(template) {
    template.defaults.imports.materialConvert = materialConvert;
    template.defaults.imports.sizeConvertStr = sizeConvertStr;
    template.defaults.imports.sizeConvertHtml = sizeConvertHtml;
    template.defaults.imports.format = format;
    template.defaults.imports.skuImage = skuImage;
    template.defaults.imports.dateFormat=dataFormat;
    template.defaults.imports.isinCart=isinCart;
    template.defaults.imports.convertSupperSelect=convertSupperSelect;
    template.defaults.imports.convertPriceVersionSelect=convertPriceVersionSelect;
    template.defaults.imports.grepsplit=grepsplit;
    template.defaults.imports.grepreplace=grepreplace;
}

function initSupperList(id,data){
    var data = groupBy(data,function (item) {
        return item.supplier_id;
    },"name");
    data.forEach(function (t) {
        $("#"+id).append('<option value="'+t.key+'">'+t.name+'</option>');
    });
}
//输入格式化
$(document).on("keyup","input.number",function () {
    return checkNumber(this);
});
$(document).on("keyup","input.cm",function () {
    return checkNumber(this);
});
$(document).on("click","input.cm",function () {
    return checkNumber(this);
});
$(document).on("blur","input.cm",function () {
    if(checkNumber(this)) {
        $(this).val($(this).val() + "CM");
    }
});
function checkNumber(item) {
    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
    var number = $(item).val().replace("CM","");
    if (!re.test(number)) {
        $(item).val("");
        return false;
    }else
    {
        $(item).val(number);
        return true;
    }
}

//输入判断是否有改变
$(document).on("blur","input,textarea",function () {
    var old=$(this).attr("old");
    var val=$(this).val();
    if(old != undefined && old != val)
    {
        $(this).addClass("edited");
    }else
    {
        $(this).removeClass("edited");
    }
});
// $(document).on("blur","textarea",function () {
//     var old=$(this).attr("old");
//     var val=$(this).val();
//     if(old != undefined && old != val)
//     {
//         $(this).addClass("edited");
//     }else
//     {
//         $(this).removeClass("edited");
//     }
// });
// 紧急程度
var UrgencyEnum = {};
UrgencyEnum.COMMON = "0";
UrgencyEnum.EMERGENCY = "1";
UrgencyEnum.MAX_EMERGENCY = "2";

// 任务状态
var TASKSTATUSEnum = {};
TASKSTATUSEnum.TODO = 0;
TASKSTATUSEnum.DOING = 1;
TASKSTATUSEnum.DONE = 2;

var TASKTYPE = {};
TASKTYPE.ALL = "-1";
TASKTYPE.ORDER = "0";
TASKTYPE.PROJECT = "1";
TASKTYPE.PERSONAL = "2";
