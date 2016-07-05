$(function(){
	var $username = $('#register-username'),
		$form = $('#register-form'),
		$items = $form.find('.border-item'),
		$uploadImage = $('#register-picture'),
		isUsername = false,
		isFirstValidation = true,
		$password = $('#register-password'),
		$passwordAgain = $('#register-checkpwd'),
		$email = $('#register-email'),
		$tmpImg = $('<img />'),
		$vCode = $form.find('#register-checkImg'),
		$checkImg = $form.find('#show-checkImg'),
		$checkImgIcon = $form.find('.checkImg-icon'),
		$checkImgInfo = $form.find('.checkImg-error-info'),
		srcUrl = window.location.protocol + "\/\/" + window.location.host +
					"\/ShowDoc\/" + "voucher/getCaptchar.action?temp=" + 
					(new Date().getTime().toString(36)),
		codeUrl = window.location.protocol + "\/\/" + window.location.host +
					"\/ShowDoc\/" + "voucher/getVcode.action";

	//下拉菜单插件配置参数	
	$('.SlectBox').SumoSelect({
		csvDispCount: 12,
		forceCustomRendering: true,
		search: true
	});

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
			$checkImgIcon.html("");
			$vCode.focus();
			$checkImgInfo.hide();
			$loginSubmit.attr("disabled", "disabled").css("cursor","not-allowed");
		});
		$tmpImg.attr('src', srcUrl);
	});
	// 点击每个注册项时改变边框阴影
	$items.on('click', function(){
		$items.removeClass('border-active-item').addClass('border-item');
		$(this).removeClass('border-item').addClass('border-active-item');
	});

	// 上传图片后预览
	$uploadImage.on('change', function() {
		var file = this.files[0],
			_self = this;
		if ( file === undefined || !/image\/\w+/.test(file.type)) {
			alert("文件必须为图片");
			return;
		}
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function( e ) {
			$('.item-img-result').attr("src", this.result ).show();
			$('.item-show-title').hide();
		};
	});

	// 焦点离开验证码输入框时，检测验证输入是否正确
		$vCode.on('blur', function(){
		var inputCode = $vCode.val();

		// 获取验证码字符串
		$.ajax({
			url: codeUrl,
			type: "GET",
			dataType: "text",
			async: false,	// 设为同步
			success: function( data ) {
				var result = data.trim();
				if ( inputCode === result ) {
					$checkImgIcon.css("color","#87F880").html("&#xe900;").show();
					$checkImgInfo.hide();
					/*$loginSubmit.removeAttr("disabled").css("cursor", "pointer");*/
				} else {
					$checkImgIcon.css("color","#F9998E").html("&#xe901;").show();
					$checkImgInfo.show();
					/*$loginSubmit.attr("disabled", "disabled").css("cursor","not-allowed");*/
				}
			}
		});
	});
	//表单验证 
	$('#register-form').validate({
		rules:{
			"voucher.username": {
				required: true,
				rangelength: [6, 15]
			},
			"voucher.password": {
				required: true,
				rangelength: [6, 40]
			},
			passwordAgain: {
				required: true,
				rangelength: [6, 40],
				equalTo: "#register-password"
			},
			"voucherInfo.email": {
				required: true,
				email:true
			}
		},
		messages: {
			"voucher.username": {
				required: "未输入用户名",
				rangelength: "用户名为6-15位"
			},
			"voucher.password": {
				required: "未输入密码",
				rangelength: "密码长度为6-40位"
			},
			passwordAgain: {
				required: "未输入确认密码",
				rangelength: "密码长度为6-40位",
				equalTo: "两次输入的密码不一致"
			},
			"voucherInfo.email": {
				required: "未输入邮箱",
				email: "无效的电子邮件地址"
			}
		},
		onfocusout: false,
		onkeyup: false,
		errorClass: 'valid-error',
		validClass: 'valid-pass',
		errorElement: 'i',
		errorContainer: true
	});

	(function(){
		$("#register-form").validate().element($username);
		$('#register-username-error').css("opacity","0");
	})();
	// 提交表单
	$('#form-submit').bind('click', function(event) {

		isFirstValidation = false;	// 已经第一次验证，让第一次验证为false

		$('#register-username-error').css("opacity","1");	// 让第一次用户名验证信息显示
		/**
		 * 先判断表单验证是否通过，如果通过则进行验证用户名是否已经被注册了
		 */
		if ($('#register-form').valid() && isUsername ) {
			/*var username = $username.val(),
				url = "/ShowDoc/voucher/queryByName.action?username=" + username;

			$.ajax({
				type: "POST",
				url: url,
				dataType: "text",
				success: function(data) {
					var result = data.trim();
					if ( result === "success" ) {
						$('#register-form').submit();
					} else if ( result === "fail" ) {
						$username.removeClass("valid-pass").addClass("valid-error").siblings("i").text("用户名已经被注册.").css("display","inline");
					} else if ( result === "illegal"){
						console.log("3");
						$username.removeClass("valid-pass").addClass("valid-error").siblings("i").text("用户名应该为6~15位.").css("display","inline");
					} else {
						console.log( data );
					}
				}
			});*/
			$('#register-form').submit();
		}
		return false;
	});

	// 用户名失去焦点时验证用户名可用性
	$username.on('blur', function(){

		$('#register-username-error').css("opacity","1");

		var username = $(this).val(),
			url = "/ShowDoc/voucher/queryByName.action?username=" + username;
		
		/**
		 * 如果用户名通过验证规则，则接着判断用户名是否已经被注册
		 * @param  {[type]} $("#register-form").validate().element(this) [description]
		 * @return {[type]}                                              [description]
		 */
		if( $("#register-form").validate().element(this) ) {
			$.ajax({
				type: "POST",
				url: url,
				dataType: "text",
				success: function(data) {
					var result = data.trim();
					if ( result === "success" ) {
						isUsername = true;	// 如果用户名是还没被注册的，则isUsername 置为 true
					} else if ( result === "fail" ) {
						isUsername = false;	// 如果用户名是还没被注册的，则isUsername 置为 false
						$username.removeClass("valid-pass").addClass("valid-error").siblings("i").text("用户名已经被注册.").css("display","inline");
					} else if ( result === "illegal"){
						$username.removeClass("valid-pass").addClass("valid-error").siblings("i").text("用户名应该为6~15位.").css("display","inline");
					} else {
						var nospace = data.replace(/\s/, ""),
							leftPos = nospace.indexOf("body>") + "body>".length,
							rightPos = nospace.lastIndexOf("<\/body>"),
							errorText = nospace.substring(leftPos, rightPos),
							errorUrl = window.location.href + 
										"exception/operateVoucherHandle.action?message=" + errorText;
						window.location.href = errorUrl;
					}
				}
			});
		}
	});

	// 密码验证
	$password.on('blur', function(){

		/**
		 * 如果不是第一次验证，则用户焦点离开时就需要验证了
		 * @param  {[type]} !isFirstValidation [description]
		 * @return {[type]}                    [description]
		 */
		if ( !isFirstValidation ) {
			$("#register-form").validate().element(this);
		}
	});

	// 确认密码验证
	$passwordAgain.on('blur', function(){

		/**
		 * 如果不是第一次验证，则用户焦点离开时就需要验证了
		 * @param  {[type]} !isFirstValidation [description]
		 * @return {[type]}                    [description]
		 */
		if ( !isFirstValidation ) {
			$("#register-form").validate().element(this);
		}
	});

	// 邮箱验证
	$email.on('blur', function(){

		/**
		 * 如果不是第一次验证，则用户焦点离开时就需要验证了
		 * @param  {[type]} !isFirstValidation [description]
		 * @return {[type]}                    [description]
		 */
		if ( !isFirstValidation ) {
			$("#register-form").validate().element(this);
		}
	});
});