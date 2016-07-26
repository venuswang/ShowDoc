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
        },30)
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

















