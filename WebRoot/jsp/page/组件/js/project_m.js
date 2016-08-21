/**
 * Created by stein on 2016/7/28.
 */
$("#dropdownMenu1").on("mouseover", function() {
    if ($(this).parent().is(".open")) {
        return
    }
    $(this).dropdown("toggle")
});



//分享遮罩层打开事件
document.getElementById("project_share").onclick=function(){
    document.getElementById("cover_share").style.display="block";
};
//分享遮罩层关闭事件
document.getElementById("cover_share_close").onclick = function () { //遮罩层提示框的关闭函数
    document.getElementById("cover_share").style.display="none";
};
var cover_share_close2=document.getElementById("cover_share");
cover_share_close2.addEventListener("click",function(e){ //遮罩层提示框的关闭函数
    if(e.target==cover_share_close2){
        document.getElementById("cover_share").style.display="none";
    }
});


//导出遮罩层打开事件
document.getElementById("project_export").onclick=function(){
    document.getElementById("cover_export").style.display="block";
};
//分享遮罩层关闭事件
document.getElementById("cover_export_close").onclick = function () { //遮罩层提示框的关闭函数
    document.getElementById("cover_export").style.display="none";
};
var cover_export_close2=document.getElementById("cover_export");
cover_export_close2.addEventListener("click",function(e){ //遮罩层提示框的关闭函数
    if(e.target==cover_export_close2){
        document.getElementById("cover_export").style.display="none";
    }
});


//成员管理遮罩层打开事件
document.getElementById("project_member").onclick=function(){
    document.getElementById("cover_member").style.display="block";
};
//分享遮罩层关闭事件
document.getElementById("cover_member_close").onclick = function () { //遮罩层提示框的关闭函数
    document.getElementById("cover_member").style.display="none";
};
var cover_member_close2=document.getElementById("cover_member");
cover_member_close2.addEventListener("click",function(e){ //遮罩层提示框的关闭函数
    if(e.target==cover_member_close2){
        document.getElementById("cover_member").style.display="none";
    }
});


//转让遮罩层打开事件
document.getElementById("project_transfer").onclick=function(){
    document.getElementById("cover_transfer").style.display="block";
};
//分享遮罩层关闭事件
document.getElementById("cover_transfer_close").onclick = function () { //遮罩层提示框的关闭函数
    document.getElementById("cover_transfer").style.display="none";
};
var cover_transfer_close2=document.getElementById("cover_transfer");
cover_transfer_close2.addEventListener("click",function(e){ //遮罩层提示框的关闭函数
    if(e.target==cover_transfer_close2){
        document.getElementById("cover_transfer").style.display="none";
    }
});


//删除遮罩层打开事件
document.getElementById("project_delete").onclick=function(){
    document.getElementById("cover_delete").style.display="block";
};
//分享遮罩层关闭事件
document.getElementById("cover_delete_close").onclick = function () { //遮罩层提示框的关闭函数
    document.getElementById("cover_delete").style.display="none";
};
var cover_delete_close2=document.getElementById("cover_delete");
cover_delete_close2.addEventListener("click",function(e){ //遮罩层提示框的关闭函数
    if(e.target==cover_delete_close2){
        document.getElementById("cover_delete").style.display="none";
    }
});




//遮罩层滚动监听
window.onscroll=function(){
    var timer;
    var cover_share = document.getElementById("cover_share");
    var cover_export = document.getElementById("cover_export");
    var cover_member = document.getElementById("cover_member");
    var cover_transfer = document.getElementById("cover_transfer");
    var cover_delete = document.getElementById("cover_delete");

    var t=document.documentElement.scrollTop||document.body.scrollTop;
    if(timer){
        clearTimeout(timer);
    }else{
        timer=setTimeout(function(){

            cover_share.style.top=t+"px";
            cover_export.style.top=t+"px";
            cover_member.style.top=t+"px";
            cover_transfer.style.top=t+"px";
            cover_delete.style.top=t+"px";
        },350)
    }
};