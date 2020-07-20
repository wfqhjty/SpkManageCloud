$(function () {
    /**
     * Ajax用户登录
     */
    $("#btnLogin").click(function () {
        var mdrpsd = hex_md5($("#password").val());
        var data = {username: $("#username").val(), password: mdrpsd};
        var url = getRootPath();
        var flag=true;
        $(".require").each(function () {
            if ('' == $(this).val()) {
                flag=false;
                $(this).focus();
                $(this).parent().next().show();
                return false;
            }
            else{
                $(this).parent().next().hide();
            }
        });
        if(flag){
            $.ajax({
                url: url + "/api/auth/login",
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                timeout: 10000,
                success: function (data) {
                    if (data.token) {
                        window.localStorage.setItem("usertoken", data.token);
                        window.location.href = "index";
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("您的用户名或密码不正确");
                }
            });
        }
    });

});