/**
 * @author zhangnx
 * @desc 表单回填
 * @param json(字符串或对象)
 */
$.fn.extend({
    fillForm: function (json) {
        if (typeof json === 'string') {
            json = $.parseJSON(json);
        }
        for (var key in json) {
            this.find("[name='" + key + "']").each(function () {
                var tagName = $(this)[0].tagName;
                var type = $(this).attr('type');
                if (tagName == 'INPUT') {
                    if (type == 'radio') {
                        $(this).attr('checked', $(this).val() == json[key]);
                    } else if (type == 'checkbox') {
                        var arr = json[key].split(',');
                        for (var i = 0; i < arr.length; i++) {
                            if ($(this).val() == arr[i]) {
                                $(this).attr('checked', true);
                                break;
                            }
                        }
                    } else {
                        $(this).val(json[key]);
                    }
                } else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
                    $(this).val(json[key]);
                }
            });
        }
    }
});



/**
 * @Desc 表单系列化转为字符串
 * @returns {string}
 */
$.fn.extend({
    serializeJsonStr: function () {return JSON.stringify($(this).serialize());},
});



/**
 * @Desc Ajax提交
 * @type {{submit: Base.submit}}
 */
var Base = {
    submit:function (url,type,data,dataType,f) {
        $.ajax({ type: type, url:url, data:data,async: true,dataType:dataType,success: function(data){ return f(data);},error:error });
    }
};


/**
 * @desc 错误信息
 * @param XMLHttpRequest
 * @param textStatus
 * @param errorThrown
 */
var error=function (XMLHttpRequest, textStatus, errorThrown) {
    console.log("状态码:"+XMLHttpRequest.status+"->错误信息："+textStatus);
};










