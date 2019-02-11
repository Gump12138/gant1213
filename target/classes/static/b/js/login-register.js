

function showRegisterForm(){
    $('.loginBox').fadeOut('fast',function(){
        $('.registerBox').fadeIn('fast');
        $('.login-footer').fadeOut('fast',function(){
            $('.register-footer').fadeIn('fast');
        });
        $('.modal-title').html('Register with');
    }); 
    $('.error').removeClass('alert alert-danger').html('');
       
}
function showLoginForm(){
    $('#loginModal .registerBox').fadeOut('fast',function(){
        $('.loginBox').fadeIn('fast');
        $('.register-footer').fadeOut('fast',function(){
            $('.login-footer').fadeIn('fast');    
        });
        
        $('.modal-title').html('Login with');
    });       
     $('.error').removeClass('alert alert-danger').html(''); 
}

function openLoginModal(){
    showLoginForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}
function openRegisterModal(){
    showRegisterForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}

function loginAjax(){
    /*   Remove this comments when moving to server
    $.post( "/login", function( data ) {
            if(data == 1){
                window.location.replace("/home");            
            } else {
                 shakeModal(); 
            }
        });
    */

/*   Simulate error message from the server   */
     shakeModal();
}

function shakeModal(){
    $('#loginModal .modal-dialog').addClass('shake');
             $('.error').addClass('alert alert-danger').html("Invalid email/password combination");
             $('input[type="password"]').val('');
             setTimeout( function(){ 
                $('#loginModal .modal-dialog').removeClass('shake'); 
    }, 1000 ); 
}

/*
				表单校验：
					1.用户名：单词字符，长度8到20位
					2.密码：单词字符，长度8到20位
					3.email：邮件格式
					4.姓名：非空
					5.手机号：手机号格式
					6.出生日期：非空
					7.验证码：非空
			 */

//校验用户名
//单词字符，长度8到20位
function checkUsername() {
    //1.获取用户名值
    var username = $("#regist-username").val();
    //2.定义正则
    var reg_username = /^\w{3,20}$/;

    //3.判断，给出提示信息
    var flag = reg_username.test(username);
    if(flag){
        //用户名合法
        $("#regist-username").css("border","");
    }else{
        //用户名非法,加一个红色边框
        $("#regist-username").css("border","1px solid red");
    }

    return flag;
}

//校验密码
function checkPassword() {
    //1.获取密码值
    var password = $("#regist-password").val();
    //2.定义正则
    var reg_password = /^\w{3,8}$/;

    //3.判断，给出提示信息
    var flag = reg_password.test(password);
    if(flag){
        //密码合法
        $("#regist-password").css("border","");
    }else{
        //密码非法,加一个红色边框
        $("#regist-password").css("border","1px solid red");
    }

    return flag;
}

//校验邮箱
function checkEmail(){
    //1.获取邮箱
    var email = $("#regist-email").val();
    //2.定义正则		itcast@163.com
    var reg_email = /^\w+@\w+\.\w+$/;

    //3.判断
    var flag = reg_email.test(email);
    if(flag){
        $("#regist-email").css("border","");
    }else{
        $("#regist-email").css("border","1px solid red");
    }

    return flag;
}




   