/**
 * Created by Administrator on 2016/7/13.
 */
var key_title=1; //对登录注册框标题底部效果设置的状态变量

function move(i){ //点击导航栏使页面滚动，list数组存储对应位置的元素class，传入i
    var list=[];
    list[1]=".top";
    list[2]=".introduction1";
    list[3]=".help";
    list[4]=".about";
    var scroll_offset = $(list[i]).offset();  //得到pos这个div层的offset，包含两个值，top和left
        $("body,html").animate({
            scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
        },500);
}

window.onscroll=function(){ //遮罩层和侧导航栏滚动监听
    var timer;
    var navigation_col=document.getElementsByClassName("navigation_col");
    var cover = document.getElementById("cover");

    var t=document.documentElement.scrollTop||document.body.scrollTop;
    if(timer){
        clearTimeout(timer);
    }else{
        timer=setTimeout(function(){
            navigation_col[0].style.bottom = -t + 20 + "px";
            cover.style.top=t+"px";
        },350)
    }
};

function change_login_title(i){ //鼠标动作对登录框标题底部的影响
    var login=document.getElementById("id_login_title");
    var register=document.getElementById("id_register_title");
    if(i==1){
        if(key_title==1){
            login.className="login_title"+" "+"c_title";
            register.className="register_title";
        }else{
            login.className="login_title"+" "+"c_title";
            register.className="register_title";
        }
    }
    else{
        if(key_title==1){
            login.className="login_title"+" "+"c_title";
            register.className="register_title";
        }else{
            login.className="login_title";
            register.className="register_title"+" "+"c_title";
        }
    }
}

function change_register_title(i){ //鼠标动作对注册框标题底部的影响
    var login=document.getElementById("id_login_title");
    var register=document.getElementById("id_register_title");
    if(i==1){
        if(key_title==1){
            register.className="register_title"+" "+"c_title";
            login.className="login_title";
        }else{
            register.className="register_title"+" "+"c_title";
            login.className="login_title";
        }
    }
    else
    {
        if(key_title==1){
            login.className="login_title"+" "+"c_title";
            register.className="register_title";
        }else{
            register.className="register_title"+" "+"c_title";
            login.className="login_title";
        }
    }
}

function change_login_input(i){ //登录和注册选项卡的切换
    if(i==0){//鼠标点击注册
        var register_i=document.getElementById("id_register_input");
        register_i.className="register_input";
        var login_i=document.getElementById("id_login_input");
        login_i.className="login_input"+" "+"c_input";
        key_title=0;

    }else{//1鼠标点击登录
        var register_i=document.getElementById("id_register_input");
        register_i.className="register_input"+" "+"c_input";
        var login_i=document.getElementById("id_login_input");
        login_i.className="login_input";
        key_title=1;
    }
}


document.getElementById("forget_pw").onclick = function () { //遮罩层提示框的打开函数
    document.getElementById("cover").style.display="block";
};

document.getElementById("cover_close").onclick = function () { //遮罩层提示框的关闭函数
    document.getElementById("cover").style.display="none";
};


    var cover_close2=document.getElementById("cover");
    cover_close2.addEventListener("click",function(e){
        if(e.target==cover_close2){
            document.getElementById("cover").style.display="none";
        }
    });


var navigation_li=document.getElementsByClassName("navigation_li");
    for(var i = 0, len = navigation_li.length; i < len; i ++ ) {
        (function (index) {
            navigation_li[index].onclick = function () {
                move(4-index);
            };
        })(i);
    }

var navigation_col_li=document.getElementsByClassName("navigation_col_li");
    for(var i = 0, len = navigation_col_li.length; i < len; i ++ ) {
        (function (index) {
            navigation_col_li[index].onclick = function () {
                move(index+1);
            };
        })(i);
    }

var id_login_title=document.getElementById("id_login_title");

    id_login_title.onclick=function(){change_login_input(1);};
    id_login_title.onmouseover=function(){change_login_title(1);};
    id_login_title.onmouseout=function(){change_login_title(0);};


var id_register_title=document.getElementById("id_register_title");

    id_register_title.onclick=function(){change_login_input(0);};
    id_register_title.onmouseover=function(){change_register_title(1);};
    id_register_title.onmouseout=function(){change_register_title(0);};

// 登录逻辑
var $doc = $(document), // 保存document引用，优化
    $body = $doc.find('body'),   // 保存 body
    $login = $body.find('#id_login_input'),  // 登录容器
    $form = $login.find('.form-signin'),     // 登录表单
    $username = $form.find('#login-username'),  // 用户名
    $password = $form.find('#login-password'),  // 密码
    $vCode = $form.find('#login-checkImg'),     // 验证码
    $checkImg = $form.find('#show-checkImg'),   // 验证码图片
    $loginSubmit = $form.find( '.form-submit'),     // 登录按钮
    srcUrl = window.location.protocol + "\/\/" + window.location.host + "/ShowDoc/" + 
            "voucher/getCaptchar.action?temp=" +  (new Date().getTime().toString(36)), // 拿取验证图片的url
    codeUrl = window.location.protocol + "\/\/" + window.location.host +    
                        "/ShowDoc/voucher/getVcode.action",             // 拿取验证码的url
    $tmpImg = $('<img />'),                                             // 临时预加载图片的元素
    isdealinglogin = false;                                             // 控制处理登录，防止多次点击有效

    $vCode.val("");

    // 去加载验证码
    $tmpImg.on('load', function(){
        $checkImg.attr("src", srcUrl); // 将获取的验证码图片显示出来

    });
    $tmpImg.attr('src', srcUrl);

    // 点击图片时刷新验证码
    $checkImg.on('click', function(){

        // 点击时更新时间获取不同的验证码
        srcUrl = window.location.protocol + "\/\/" + window.location.host +
                    "\/ShowDoc\/" + "voucher/getCaptchar.action?temp=" + 
                    (new Date().getTime().toString(36));

        $tmpImg.on('load', function(){
            $checkImg.attr("src", srcUrl); // 将获取的验证码图片显示出来
        });

        // 开始加载验证码
        $tmpImg.attr('src', srcUrl);
    });

    // 绑定submit事件
    $loginSubmit.on('click', function( event ){
        
        // 如果没有在处理一个登录事件，则接受并处理
        if ( !isdealinglogin ) {

            isdealinglogin = true;      // 控制反转，正在处理登录事件，则不应多次处理

            var url = window.location.protocol + "//" + window.location.host + "/ShowDoc/voucher/checkVoucher.action",  // 处理登录的url
                username = $username.val() || '',     // 用户名
                password = $password.val() || '',     // 密码
                vcode = $vCode.val() || '',           // 验证码
                params = {
                    username: username,
                    password: password,
                    vcode: vcode
                };                          // 需要post的参数
             
             // 如果检测到用户没有输入用户名，则不再进行后续处理，并小提示框告诉用户输入用户名
             if ( username === undefined || username === '' ) {
                layer.msg( '用户名不能为空', {
                    time: 1500
                });
                isdealinglogin = false;     // 控制反转，说明可以接受点击事件，并处理

                return false;
             } 

            // 如果检测到用户没有输入密码，则不再进行后续处理，并小提示框告诉用户输入密码
             if ( password === undefined || password === '' ) {
                layer.msg( '密码不能为空', {
                    time: 1500
                });
                isdealinglogin = false;     // 控制反转，说明可以接受点击事件，并处理

                return false;
             } 

             // 如果检测到用户没有输入验证码，则不再进行后续处理，并小提示框告诉用户输入验证码
             if ( vcode === undefined || vcode === '' ) {
                layer.msg( '请输入验证码', {
                    time: 1500
                });
                isdealinglogin = false;     // 控制反转，说明可以接受点击事件，并处理

                return false;
             }

            // 先检测验证码，异步链编程模式
            $.ajax({
                url: codeUrl,
                type: "GET",
                dataType: "text"
            })
            .then(function( data ){
                var result = data.trim();

                // 如果验证码正确，则继续维持异步链中的状态
                if ( vcode === result ) {
                    $.Deferred().resolve();
                } else {

                    // 如果验证码不对，则小提示框显示
                    layer.msg( '验证码错误', {
                        time: 1500
                    });

                    // 修改异步链的对象直接为fail，不再进行后面异步链中相关操作的执行
                    $.Deferred().reject();  
                }
            })
            .then(function(){

                // 当前面的异步链都成功执行，并且状态没有变为reject，才执行到这来
                // 发送登录的请求
                $.ajax({
                    url: url,
                    type: "post",
                    dataType: "text",
                    data: params,
                    success: function( data ){

                        var result = data.trim(),
                            results = result.split(",");

                        isdealinglogin = false;     // 控制反转，说明可以接受点击事件，并处理

                        // 如果成功，需要进行跳转
                        if ( results[0] === "success" ) {

                            var nextUrl = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc/" + results[1];

                            window.location.href = nextUrl;
                        } else if ( (results[0] === "fail" && results[1] === undefined ) || results[0] === "illegal") {

                            // 如果登录不成，则给出小提示框显示原因
                            layer.msg( '账号或密码错误', {
                                time: 1500
                            });

                        } else if ( results[0] === "fail" && results[1].length > 0 ) {

                            // 如果验证码错误，则也给出原因
                            layer.msg( '验证码错误', {
                                time: 1500
                            });

                        }else {
                            var nospace = data.replace(/\s/, ""),
                                leftPos = nospace.indexOf('<h3 class=\"error-info\">') + "<h3 class=\"error-info\">".length,
                                rightPos = nospace.lastIndexOf("<\/h3>"),
                                errorText = nospace.substring(leftPos, rightPos),
                                currentUrl = window.location.protocol + "//" + window.location.host +
                                            "/ShowDoc/",
                                errorUrl = currentUrl + "exception/operateVoucherHandle.action?message=" +
                                            errorText;
                
                            // 改变当前 url
                            window.location.href = errorUrl;
                        }
                    },
                    error: function( xhr ) {
                    }
                });
            });
        }
        
        // 取消事件的默认行为，以及阻止冒泡
        event.preventDefault();
        event.stopPropagation();

        return false;
    });




