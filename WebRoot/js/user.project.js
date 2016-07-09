$(function(){
	var $header = $( '#header' ),
		$userCenter = $header.find( '#user-center' ),
		$userPortraint = $userCenter.find('.user-portraint').eq(0),
		$subNav = $header.find( '.sub-nav' ).eq(0),
		$subNavItems = $subNav.find( '.sub-btn' ),
		$mask = $( '#mask-container' ),
		$personInfo = $mask.find( '#person-info' ),
		$personInfoBtnBack = $personInfo.find( '.btn-back' ),
		$updatePwd = $mask.find( '#modify-person-password' ),
		$updatePwdSubmit = $updatePwd.find( '.btn-submit' ),
		$updatePwdCancel = $updatePwd.find( '.btn-cancel' ),
		$modifyInfo = $mask.find( '#modify-person-info' ),
		$modifyInfoSubmit = $modifyInfo.find( '.btn-submit' ),
		$modifyInfoCancel = $modifyInfo.find( '.btn-cancel' ),
		isAnimating = false,
		modifyInfoResult = $('body').children('.modify-info-result').text().trim(),
		$main = $('#main'),
		$projectList = $main.find('.project-list'),
		$addProBtn = $projectList.find('.project-item').find('.project-btn');

	if ( modifyInfoResult === "success" ) {
		var $tmpdiv = $('<div>'),
			tmpStrDom = '<span>已经保存成功</span>';

		// 小提示层的提示文字以及样式
		$tmpdiv.html(tmpStrDom).css({
			    position: "absolute",
			    left: "50%",
			    "marginLeft": "-15px",
			    top: "10%",
			    "lineHeight": "35px",
			    "backgroundColor": "#58f69e",
			    color: "#fff",
			    padding: "10px",
			    "borderRadius": "5px",
			    display: "none"
		}).appendTo($('body')).show(function(){
			setTimeout(function(){
				$tmpdiv.fadeOut(300);
				document.body.removeChild($tmpdiv[0]);
			},700); // 显示 0.7s 后隐藏提示框并且从文档流中移除
		});
	} else if( modifyInfoResult != undefined ) {
		// 修改不成功 待处理
	}

	// 鼠标移入移除用户头像时显示下拉菜单	
	$userCenter.hover(function(event){
		if ( !isAnimating ) {
			isAnimating = true;
			$subNav.show(100);
		}
	},function(event){
		$subNav.hide(100, function(){
			isAnimating = false;
		});
	});

	// 鼠标移入移出头像的动画
	$userPortraint.hover(function(){
		$(this).addClass('rotate');
	},function(){
		$(this).removeClass('rotate');
	});
	//点击下拉菜单时相应切换不同的视图
	$subNavItems.on('click', function(){
		var data = $(this).data("dist");

		$subNav.hide();
		if ( data !== undefined ) {
				// 获取切换的目标容器
			var $target = $( '#' + data ),
				wH = $(window).height();

			if ( data === 'person-info' ) {
				var userId = $target.data('user'),
					$username = $target.find('.item-name-content'),
					$email = $target.find('.item-email-content'),
					$image = $target.find('.item-portraint-content'),
					$skill = $target.find('.info-item-skill'),
					url = window.location.protocol + "\/\/" + window.location.host +
							"\/ShowDoc\/" + "voucher\/queryVoucherById.action?id=" + userId;

				
				// ajax 异步获取用户信息
				$.ajax({
					url: url,
					type: "GET",
					dataType: 'json',
					success: function( data ){
						$username.text( data.voucher.username );	// 替换模板中的名字
						$email.text( data.voucherInfo.email );		// 替换模板中的邮箱

						// 生成编程技能项
						var skill = data.voucherInfo.skill,
							strDom = '';
						if ( skill === null ) {

							/**
							 * 如果skill 为null 则编程技能为无
							 * @type {String}
							 */
							strDom = '<span class="item-skill-title">编程技能</span>'+ 
									'<span class="item-skill-other">无</span>';
							$skill.html( strDom );
						} else {
							var skills = skill.split('-'),
								ulstrDom = "",
								$ul = $('<ul class="item-skill-list">');
							$.each(skills, function(index, value) {
								ulstrDom = ulstrDom + 
									'<li class="item-skill-item"><span class="item-skill-content">' + 
									value + '</span></li>';
							});
							$ul.html( ulstrDom );
							strDom = '<span class="item-skill-title">编程技能</span>';
							$skill.html( strDom );
							$skill.append( $ul );
						}

						// img的处理
						var $img = $('<img />'),
							src = window.location.protocol + "\/\/" + window.location.host + 
								data.voucherInfo.picture;

						// 当相应路径的图片已经加载完成后, 替换模板中图片的src，并且添加rotate类实现动画
						$img.on('load', function(){
							$image.attr("src", src).hover(function(){
								$image.addClass('rotate');
							},function(){
								$image.removeClass('rotate');
							});
						});
						$img.attr("src", src);
					}
				});
				
			} else if ( data === 'modify-person-info' ) {
				var modifyId = $target.data('user'),
					$updateForm = $target.find('#update-info-form'),
					$mdName = $target.find('#modify-info-username'),
					$mdEmail = $target.find('#modify-info-email' ),
					$imageItem = $target.find('.modify-info-portraint'),
					$imageName = $imageItem.find('.item-upimg-name'),
					$mdImage = $imageItem.find('.item-img-result'),
					$uploadImage = $imageItem.find('#modify-picture'),
					$loadImgError = $imageItem.find('.valid-error'),
					$mdSkills = $target.find('.modify-info-skill').find('#modify-skill').find('option'),
					$btnSubmit = $target.find('.modify-form-operations').find('.btn-submit'),
					infourl = window.location.protocol + "\/\/" + window.location.host +
							"\/ShowDoc\/" + "voucher\/queryVoucherById.action?id=" + modifyId;

				$mdImage.hide().siblings().show();


				// 异步获取需要修改信息的用户的基本信息
				$.ajax({
					url: infourl,
					type: "GET",
					dataType: "json",
					success: function( data ){
						
						// 设置用户名
						$mdName.val(data.voucher.username);

						// 设置用户邮箱
						$mdEmail.val(data.voucherInfo.email);

						// img的处理
						var $img = $('<img />'),
							src = window.location.protocol + "\/\/" + window.location.host + 
								data.voucherInfo.picture;

						// 当相应路径的图片已经加载完成后, 替换模板中图片的src，并且添加rotate类实现动画
						$img.on('load', function(){
							$mdImage.attr("src", src).show().siblings().hide();
						});
						$img.attr("src", src);

						// 已经选好的编程技能的处理
						var selectedSkill = data.voucherInfo.skill;

						if ( selectedSkill != null ) {
							var selectedSkills = selectedSkill.split('-');
							$mdSkills.each(function(){

								// 保存this，防止this漂移
								var _self = this;

								// 如果option中的值等于已经选择的值，则选上
								$.each(selectedSkills, function(index, value){
									if ($(_self).val() === value ) {
										$(_self).attr("selected", "selected");
										return;
									}
								});
							});
						}

						//需要对select进行初始化参数配置
						$target.find('.modify-info-skill').find('.SlectBox').SumoSelect({
							csvDispCount: 12,
							forceCustomRendering: true,
							search: true
						});

						// 自定义验证上传文件只能是图片的规则
						$.validator.addMethod("isImage", function(value, element) {
						    return this.optional(element) || (/\w*.jpg|\w*.png|\w*.gif/.test(value));
						}, "只能上传图片");

						// 自定义验证上传文件大小的规则
						$.validator.addMethod("isToLarge", function(value, element) { 
							var file = element.files[0];
						    return this.optional(element) || ((file.size / 1024) < 3072 );
						}, "文件大小不能超过3M");

						// 对修改表单进行验证操作
						$updateForm.validate({
							rules: {
								email: {
									required: true,
									email: true
								},
								picture: {
									isImage: true,
									isToLarge: true
								}
							},
							messages: {
								email: {
									required: "邮箱不能为空",
									email: "邮箱格式不合格"
								},
								picture: {
									isImage: "只能上传图片",
									isToLarge: "文件大小不能超过3M"
								}
							},
							errorElement: 'p',
							errorContainer: true,
							errorClass: 'valid-error',
							validClass: 'valid-pass',
						});

						// 上传图片后预览
						$uploadImage.on('change', function() {
							var file = this.files[0],
								_self = this;

							$imageName.html( file.name );

							$updateForm.validate().element(this);
							if ( !$updateForm.validate().element(this) ) {
								$uploadImage.blur();
								return;
							}
							var reader = new FileReader();
							reader.readAsDataURL(file);
							reader.onload = function( e ) {
								$('.item-img-result').attr("src", this.result ).show();
								$('.item-show-title').hide();
								$loadImgError.text("").hide();
							};
						});
						// 点击确定按钮时先验证，如果验证通过则提交修改的信息 
						$btnSubmit.on('click', function(){
							if( $updateForm.valid() ) {
								/*var reEmail = $mdEmail.val(),
									rePicture = $uploadImage.val(),
									$reSkills = $mdSkills.filter(':selected'),
									dealUrl = window.location.protocol + "\/\/" + 
											window.location.host + "\/ShowDoc\/" + 
											"voucher\/updateVoucherInfo.action?email=" + 
											reEmail + "&picture=" + rePicture;

								$reSkills.each(function(){
									dealUrl = dealUrl + "&skills=" + $(this).val();
								});
								
								$.ajax({
									url: dealUrl,
									type: "POST",
									dataType: "text",
									success: function( data ) {
										console.log( data );
									}
								});*/
								console.log( 'submit' );
								$target.find('#update-info-form').submit();
							}
							return false;
						});
					}
				});
			} else if ( data === 'modify-person-password' ) {
				var updateId =  $target.data('user'),
					$updatePwdForm = $target.find('#update-password-form'),
					$updateBtnPwd = $updatePwdForm.find('.update-form-operations').find('.btn-submit'),
					$updatePwd = $target.find('#update-old-password'),
					$updateNewPwd = $target.find('#update-new-password'),
					$updateUsername = $updatePwdForm.find('#update-username'),
					uinfourl = window.location.protocol + "\/\/" + window.location.host +
							"\/ShowDoc\/" + "voucher\/queryVoucherById.action?id=" + updateId;

				$target.find('.modify-restul-info').hide();	// 刚进来时隐藏显示反馈信息的h3b标签

				// 以及清除上次在输入框中的数据
				$updatePwd.val("");
				$updateNewPwd.val("");
				$updatePwdForm.find('#update-new-password-again').val("");

				$.ajax({
					url: uinfourl,
					type: "POST",
					dataType: "json",
					success: function(data){
						$updateUsername.val( data.voucher.username );
					}
				});

				//表单验证 
				$updatePwdForm.validate({
					rules:{
						"password": {
							required: true,
							rangelength: [6, 40]
						},
						"newPassword": {
							required: true,
							rangelength: [6, 40]
						},
						"newPasswordAgain": {
							required: true,
							rangelength: [6, 40],
							equalTo: "#update-new-password"
						}
					},
					messages: {
						"password": {
							required: "未输入原密码",
							rangelength: "密码长度为6-40位"
						},
						newPassword: {
							required: "未输入新密码",
							rangelength: "密码长度为6-40位"
						},
						newPasswordAgain: {
							required: "未输入确认密码",
							rangelength: "密码长度为6-40位",
							equalTo: "两次输入的密码不一致"
						}
					},
					errorElement: 'p',
					errorContainer: true,
					errorClass: 'valid-error',
					validClass: 'valid-pass',
				});

				var isupdating = false;	// 防止用户多次点击
				$updateBtnPwd.on('click', function(){

					/**
					 * 如果是第一次点击修改按钮则受理用户的此操作，如果不是则不受理用户的此次点击操作
					 * @param  {[type]} !isupdating [description]
					 * @return {[type]}             [description]
					 */
					if ( !isupdating ) {
						isupdating = true;
						var updatepassword = $updatePwd.val(),
							updateNewPassword = $updateNewPwd.val(),
							upPwdUrl = window.location.protocol + "\/\/" + 
									window.location.host + "\/ShowDoc\/" + 
									"voucher\/updatePassword.action?id=" + updateId + "&username=" + 
									$updateUsername.val() + "&password=" + updatepassword + 
									"&newPassword=" + updateNewPassword;

						if ( $updatePwdForm.valid()) {
							
							$.ajax({
								url: upPwdUrl,
								type: "POST",
								dataType: "text",
								success: function( data ) {
									var result = data.trim();
									isupdating = false;		// 反转 isupdating，使可以受理用户的下次点击

									/*console.log( data );*/

									if ( result === "success" ) {

										/**
										 * 如果修改成功，则隐藏遮罩层并隐藏修改密码UI后，显示一个小提示框
										 */
										$target.hide();
										$mask.hide();

										var $tmpdiv = $('<div>'),
											tmpStrDom = '<span>已经保存成功</span>';

										// 小提示层的提示文字以及样式
										$tmpdiv.html(tmpStrDom).css({
											    position: "absolute",
											    left: "50%",
											    "marginLeft": "-15px",
											    top: "10%",
											    "lineHeight": "35px",
											    "backgroundColor": "#58f69e",
											    color: "#fff",
											    padding: "10px",
											    "borderRadius": "5px",
											    display: "none"
										}).appendTo($('body')).show(function(){
											setTimeout(function(){
												$tmpdiv.fadeOut(300);
												document.body.removeChild($tmpdiv[0]);
											},700); // 显示 0.7s 后隐藏提示框并且从文档流中移除
										});
									} else if ( result === "fail" ) {
										var $resultInfo = $target.find('.modify-restul-info');

										$resultInfo.html('账号和原始密码不匹配').show();
									} else if ( result === "illegal") {
										var $resultInfo = $target.find('.modify-restul-info');
										$resultInfo.html('密码的格式不正确').show();
									}
									
								}
							});
						}
					}
					return false;
				});
			}

			// 将遮罩层显示出来以及显示相应的用户选择的视图
			$mask.show(100, function(){
				$target.css("top","-100%").show();
				var targetH = $target.height();

				$target.animate({
					top: ( wH - targetH ) / 2
				},function(){
					$target.siblings().hide();
				});
			});
		}
	});

	// 点击个人信息下的展示后返回
	$personInfoBtnBack.on('click', function(){
		$mask.hide(function(){
			$mask.children().hide();
		});
	});

	// 点击修改信息下的取消按钮返回
	$modifyInfoCancel.on('click', function() {
		$mask.hide(function(){
			$mask.children().hide();
		});
	});

	// 点击修改密码下的取消按钮返回
	$updatePwdCancel.on('click', function() {
		$mask.hide(function(){
			$mask.children().hide();
		});
	});

	// 点击遮罩层时,遮罩层消失
	$mask.on('click',function( event ){
		if ( event.target === this ) {
			$mask.hide().children().hide();
		}
	});

	
	// 点击新建项目时触发的事件
	$addProBtn.on('click', function(){
		var $body = $('body'),
			bdHeight = $body.height(),
			wH = $(window).height(),
			bottom = wH - bdHeight,
			$creatProCon = $('#create-project-container'),
			$creatProForm = $creatProCon.find('#create-project-form'),
			fH = 0,
			$creatProBtnSub = $creatProForm.find('.project-form-operations').find('.btn-submit'),
			$creatProBtnCan = $creatProForm.find('.project-form-operations').find('.btn-cancel');

		$creatProCon.css("bottom", bottom).show(100, function(){
			$creatProForm.css({
				opacity: "0",
				top: "-100%"
			}).show();
			fH = $creatProForm.height();
			$creatProForm.animate({
				top: ( wH / 2 - fH / 2),
				opacity: "1"
			}, 100);
		}).on('click', function(event){
			
			if (event.target === this ) {
				$(this).hide(function(){
					$creatProForm.hide();
				});
			}
		});

		// 点击取消按钮事件
		$creatProBtnCan.on('click', function(){
			$creatProCon.trigger('click');
			return false;
		});

		// 点击确定按钮事件
		$creatProBtnSub.on('click', function(){
			// 是否提交表单
			return false;
		});
		return false;
	});
});