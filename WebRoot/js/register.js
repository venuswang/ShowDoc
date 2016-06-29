$(function(){
	var $username = $('#register-username'),
		$form = $('#register-form'),
		$items = $form.find('.register-item').add('.register-logo-item').add('.register-item-select');

	//下拉菜单插件配置参数	
	$('.SlectBox').SumoSelect({
		csvDispCount: 12,
		forceCustomRendering: true,
		search: true
	});

	// 点击每个注册项时改变边框阴影
	$items.on('click', function(){
		$items.css("boxShadow", "0 0 0 #888,0 0 1px #888,0 0 2px #888,0 0 3px #888,0 0 4px #888,0 0 5px #888");
		$(this).css("boxShadow", "0 0 0 #23A7DE,0 0 1px #23A7DE,0 0 2px #23A7DE,0 0 3px #23A7DE,0 0 4px #23A7DE,0 0 5px #23A7DE");
	});

	// 点击的时候获取表单下拉框的数据的方法
	/*$('.form-opera').on('click', function(e){
		var select = $('#register-life-love option:selected');
		select.each(function(item, index){
			console.log(item);
			console.log(index);
		})
		e.preventDefault();
	});*/

	//表单验证 
	/*$('#register-form').validate({
		rules:{
			username: {
				required: true,
				rangelength: [6, 15]
			},
			password: {
				required: true,
				rangelength: [6, 40]
			},
			passwordAgain: {
				required: true,
				rangelength: [6, 40],
				equalTo: "#register-password"
			}
		},
		messages: {
			username: {
				required: "未输入用户名",
				rangelength: "用户名为6-30位"
			},
			pwd: {
				required: "未输入密码",
				rangelength: "密码长度为6-16位"
			},
			pwd2: {
				required: "未输入确认密码",
				rangelength: "密码长度为6-16位",
				equalTo: "两次输入的密码不一致"
			}
		},
		focusInvalid: true,
		errorClass: 'loginError',
		validClass: 'loginPass',
		errorElement: 'i',
		errorContainer: true
	});

	// 提交表单
	$('#form-submit').bind('click', function(event) {
		if ($('#register-form').valid()) {
			$('#register-form').submit();
		}
		return false;
	});

	// 用户名失去焦点时验证用户名可用性
	$username.on('blur', function(){
		var username = $(this).val(),
			url = "CheckUserLogging?username=" + username;
		$.ajax({
			type: "POST",
			url: url,
			dataType: "text",
			success: function(data) {
				alert(data);
			}
		})
	});*/
});