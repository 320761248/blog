function isnull(obj){
    if(typeof username != "undefined" && username != null && username != "") {
        return true
    }
    return flase;
}

$(document).ready(function() {


    //登陆操作

    $("#login_btn").click(function () {

        //判断用户名密码是否为空
        var username=$("#login_number").val()
        var password=$("#login_password").val()

        if(typeof username != "undefined" && username != null && username != "") {

            if(typeof password != "undefined" && password != null && password != ""){
                $.ajax({
                    url:"/userLogin",
                    data:{
                        "userName":username,
                        "passWord":password
                    },
                    type:"post",
                    dataType:"json",
                    success:function (result) {
                        if(result.code==0){
                            alert("登陆成功")
                        }else{
                            alert(result.msg)
                        }
                    },
                    error:function (xhr,status,error) {
                        alert("博客发布失败")
                    }
                })

            }else{
                alert("密码为空")
            }
        }else{
            alert("用户名为空")
        }


        //
        
    })


    //注册操作

    $("#regist_btn").click(function () {
        alert("zhuce")
        //判断用户名什么的是否为空
        var username=$("#regist_account").val()
        var password=$("#regist_password1").val()
        var email=$("#regist_phone").val()
        var repeatpassword=$("#regist_password2").val()
        var regist_vcode=$("#regist_vcode").val()

        if(typeof username != "undefined" && username != null && username != "") {

            if(typeof password != "undefined" && password != null && password != ""){
                if(typeof email != "undefined" && email != null && email != ""){
                    if(typeof repeatpassword != "undefined" && repeatpassword != null && repeatpassword != ""){
                        if(typeof regist_vcode != "undefined" && regist_vcode != null && regist_vcode != ""){
                            if(repeatpassword==password){
                                $.ajax({
                                    url:"/user/regist",
                                    data:{
                                        "email":username,
                                        "password":password,
                                        "regist_vcode":regist_vcode,

                                    },
                                    type:"post",
                                    dataType:"json",
                                    success:function (result) {
                                        if(result.code%2==0){
                                            //成功
                                            alert(result.msg)
                                        }else{
                                            alert(result.msg)
                                        }
                                    },
                                    error:function (xhr,status,error) {
                                        alert("注册失败")
                                    }
                                })


                            }
                        }
                    }
                }


            }else{
                alert("密码为空")
            }
        }else{
            alert("用户名为空")
        }


    })

    //发送验证码给邮箱



    //打开会员登录
    $("#Login_start_").click(function() {
        $("#regist_container").hide();
        $("#_close").show();
        $("#_start").animate({

            right: '200px',
            height: '520px',
            width: '400px'
        }, 500, function() {
            $("#login_container").show(500);
            $("#_close").animate({
                height: '40px',
                width: '40px'
            }, 500);
        });
    });
    //打开会员注册
    $("#Regist_start_").click(function() {
        $("#login_container").hide();
        $("#_close").show();
        $("#_start").animate({

            right: '200px',
            height: '520px',
            width: '400px'
        }, 500, function() {
            $("#regist_container").show(500);
            $("#_close").animate({
                height: '40px',
                width: '40px'
            }, 500);
        });
    });
    //关闭
    $("#_close").click(function() {

        $("#_close").animate({
            height: '0px',
            width: '0px'
        }, 500, function() {
            $("#_close").hide(500);
            $("#login_container").hide(500);
            $("#regist_container").hide(500);
            $("#_start").animate({
                right: '0px',
                height: '0px',
                width: '0px'
            }, 500);
        });
    });
    //去 注册
    $("#toRegist").click(function(){
        $("#login_container").hide(500);
        $("#regist_container").show(500);
    });
    //去 登录
    $("#toLogin").click(function(){
        $("#regist_container").hide(500);
        $("#login_container").show(500);
    });
});


var clock = '';
var nums = 30;
var btn;


function sendCode(thisBtn) {
    btn = thisBtn;
    btn.disabled = true; //将按钮置为不可点击
    btn.value = '重新获取（'+nums+'）';
    clock = setInterval(doLoop, 1000); //一秒执行一次

    //获取邮箱
    var email=$("#regist_phone").val()
    alert(email)
    //发送验证码
    $.ajax({

        url:"/user/sendVericationCode",
        data:{
            "email":email
        },
        type:"post",
        dataType:"json",
        success:function (result) {
            if(result.code==0){
                alert(result.msg)
            }
        },
        error:function (xhr,status,error) {
            alert("验证码发送失败")
        }
    })



}

function doLoop() {
    nums--;
    if (nums > 0) {
        btn.value = '重新获取（'+nums+'）';
    } else {
        clearInterval(clock); //清除js定时器
        btn.disabled = false;
        btn.value = '点击发送验证码';
        nums = 10; //重置时间
    }
}

$(document).ready(function(){
    $("#login_QQ").click(function(){
        alert("暂停使用！");
    });
    $("#login_WB").click(function(){
        alert("暂停使用！");
    });
});