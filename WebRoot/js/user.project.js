$(function(){
	var $header = $( '#header' ),
		$userCenter = $header.find( '#user-center' ),
		$userPortraint = $userCenter.find('.user-portraint').eq(0),
		$subNav = $header.find( '.sub-nav' ).eq(0),
		$subNavItems = $subNav.find( '.sub-btn' ),
		$exitBtn = $header.find('#user-exit').find('.btn-exit'),
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
		$projectBtn = $projectList.find('.project-item').find('.btn-detail'),
		$addProBtn = $projectList.find('.project-item-add').find('.project-btn');

	if ( modifyInfoResult === "success" ) {

		// 跳转到项目首页
   		window.location.href = window.location.protocol + "//" + window.location.host +
                "/ShowDoc/" + "project/showProject.action";

		// 如果修改个人信息成功
		layer.msg( '个人信息修改成功并保存', {
			time: 1000
		}, function(){
		});

	} else if( modifyInfoResult != undefined ) {
		/*// 修改不成功,给个小提示，并且跳转
		layer.msg( '修改信息失败', {
			time: 1500
		}, function(){
			window.location.href = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc/" + "project/showProject.action";
		});*/
	}

	// 退出按钮点击事件, 跳转到相应的退出servlet处理
	$exitBtn.on('click', function(event) {

		var url = window.location.protocol + "//" + window.location.host +
				"/ShowDoc/" + "voucher/exitVoucher.action";
		window.location.href = url;

		// 阻止事件冒泡并取消默认行为
		event.stopPropagation();
		event.preventDefault();
	});

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
		var data = $(this).data("dist"),
			wH = $(window).height(),		// 窗口高度
			docH = $(document).height(),	// 文档高度
			mBottom = wH - (docH + 30 );	// 文档高度与窗口高度的差值

		mBottom = mBottom > 0 ? 0 : mBottom;	// 确定遮罩层的bottom位置

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
				var updateValidator = $updateForm.validate({
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

				// 清空上次的验证信息
				updateValidator.resetForm();

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

				 //表单验证 
				var validator = $updatePwdForm.validate({
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

				// 清空表单验证的信息
				validator.resetForm();

				// 从后台获取用户的信息
				$.ajax({
					url: uinfourl,
					type: "POST",
					dataType: "json",
					success: function(data){
						$updateUsername.val( data.voucher.username );
					}
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

									if ( result === "success" ) {

										/**
										 * 如果修改成功，则隐藏遮罩层并隐藏修改密码UI后，显示一个小提示框
										 */
										layer.msg( '修改成功并保存', {
											time: 1000
										}, function(){

											// 关闭遮罩层以及密码修改区
											$target.fadeOut();
											$mask.fadeOut();
										});


									} else if ( result === "fail" ) {

										layer.msg( '账号和原始密码不匹配', {
											time: 1500
										});

									} else if ( result === "illegal") {
										layer.msg('密码的格式不正确', {
											time: 1500
										});
									}
									
								}
							});
						}
					}
					return false;
				});
			}

			// 将遮罩层显示出来以及显示相应的用户选择的视图
			$mask.css("bottom", mBottom).show(100, function(){
				$target.css("top","-100%").show();
				var targetH = $target.height(),
					targetTop = (wH - targetH) / 2;

				if ( data === 'modify-person-info' ) {

					/**
					 * 如果是修改信息的视图，因为修改信息中有select多选框，会导致弹出窗的高度变化
					 * 故需要监听其点击事件，若高度变化则应该相应的重新定位遮罩层的bottom位置
					 */
					$target.find('.modify-info-skill').find('.CaptionCont').on('click', function(){

						targetH += $target.find('.modify-info-skill').find('.optWrapper').height();

						// 再次确定遮罩层的bottom位置, 防止多选框高度的变化, 导致遮罩层不能全部遮罩的情况
						docH = docH > targetH ? docH : targetH;
						mBottom = wH - ( docH + 30 );
						mBottom  = mBottom > 0 ? 0 : mBottom;
						$mask.css('bottom', mBottom);	// 重新定位bottom位置
					});
				}

				targetTop = targetTop > 10 ? targetTop : 10;	// 确定弹出框与视口顶部的距离

				// 再次确定遮罩层的bottom位置，防止弹出框的高度高过窗口高度，超过文档高度，导致遮罩层不能全部遮罩的情况
				docH = docH > targetH ? docH : targetH;
				mBottom = wH - ( docH + 30 );
				mBottom  = mBottom > 0 ? 0 : mBottom;
				$mask.css('bottom', mBottom);	// 重新定位bottom位置

				$target.animate({
					top: targetTop
				},function(){
					$target.siblings().hide();
				});
			});
		}
	});

	// 点击个人信息下的展示后返回
	$personInfoBtnBack.on('click', function(){
		$mask.hide(function(){
			$mask.children().fadeOut(200);
		});
	});

	// 点击修改信息下的取消按钮返回
	$modifyInfoCancel.on('click', function() {
		$mask.hide(function(){
			$mask.children().fadeOut(200);
		});
	});

	// 点击修改密码下的取消按钮返回
	$updatePwdCancel.on('click', function() {
		$mask.hide(function(){
			$mask.children().fadeOut(200);
		});
	});

	// 点击遮罩层时,遮罩层消失
	$mask.on('click',function( event ){
		if ( event.target === this ) {
			$mask.fadeOut(200).children().hide();
		}
	});
	
	// 点击新建项目时触发的事件
	$addProBtn.on('click', function(){
		var $body = $('body'),
			bdHeight = $body.height(),
			wH = $(window).height(),
			docH = $(document).height(),
			bottom = 0,
			$creatProCon = $('#create-project-container'),
			$creatProForm = $creatProCon.find('#create-project-form'),
			fH = 0,
			$creatProBtnSub = $creatProForm.find('.project-form-operations').find('.btn-submit'),
			$creatProBtnCan = $creatProForm.find('.project-form-operations').find('.btn-cancel'),
			formTop = 0;
		
		// 让滚动条回到顶端
		$(document).scrollTop(10);

		setTimeout(function(){
			$creatProCon.css("bottom", bottom).show(100, function(){

				// 让表单弹框以透明度为0为的形式出现，用来获取高度
				$creatProForm.css({
					opacity: "0",
					top: "-100%"
				}).show();

				// form 表单在遮罩层的位置
				fH = $creatProForm.outerHeight();
				formTop = wH / 2 - fH / 2;
				formTop = formTop > 20 ? formTop : 10;

				fH += 20;
				docH = docH > fH ? docH : fH;
				bottom = wH - docH;
				bottom = bottom > 0 ? 0 : bottom;

				$creatProCon.css("bottom", bottom);

				$creatProForm.animate({
					top: formTop,
					opacity: "1"
				}, 100);
			}).on('click', function(event){
				
				if (event.target === this ) {
					$(this).hide(function(){
						$creatProForm.hide();
					});
				}
			});
		}, 100);

		// 项目失去点击时需要异步验证该项目是否已经构建
		$creatProForm.find('.projectname').on('blur', function(event){

			var proname = $(this).val(),
				prourl = window.location.protocol + "//" + window.location.host + "/ShowDoc/" +
				"project/checkProject/" + proname;

			// 异步验证项目名是否已经创建
			$.ajax({
				url: prourl,
				type: "POST",
				dataType: 'text',
				success: function(data) {
					var result = data.trim();
					if ( result === 'success' ) {

						/**
						 * 如果异步验证返回的是success，则表明此项目该用户还没创建过，表示可以创建
						 */
						 // 项目名异步验证成功时不处理

					} else if ( result === 'fail' ) {

						/**
						 * 如果异步验证返回的是fail，则表明此项目该用户已经创建过，不可再创建
						 */
						// 提示该项目名已创建
						layer.msg( '项目名已存在', {
							time: 1500
						});

					} else if ( result === 'illegal' ) {

						/**
						* 如果异步验证返回的是illegal，则表明此项目名格式错误，应该提醒用户重新输入项目名
						*/
						// 格式错误时也提示用户
						layer.msg( '项目名格式错误', {
							time: 1500
						});
					}
				}
			});

			event.preventDefault();
			event.stopPropagation();
		});

		// 点击取消按钮事件
		$creatProBtnCan.on('click', function(){
			$creatProCon.trigger('click');
			return false;
		});

		var iscreatingPro = false;	// 控制点击确定按钮，防止多次点击
		// 点击确定按钮事件
		$creatProBtnSub.on('click', function(){
			if ( !iscreatingPro ) {

				/**
				 * 如果还没有点击确定按钮，则处理此次点击，否则直接不处理
				 */
				
				iscreatingPro = true;	// 表示正在处理有效的点击事件
				var $projectname = $creatProForm.find('.projectname'),
					$projectdesc = $creatProForm.find('.projectdesc'),
					$projectsort = $creatProForm.find('.sortid'),
					$username = $creatProForm.find('.username'),
					$password = $creatProForm.find('.password'),
					projectname = $projectname.val(),
					projectdesc = $projectdesc.val(),
					projectsort = $projectsort.val(),
					username = $username.val(),
					password = $password.val(),
					createUrl = window.location.protocol + "\/\/" + 
										window.location.host + "\/ShowDoc\/" + "project\/addProject?projectname=" + 
										projectname + "&projectdesc=" + projectdesc + "&sortid=" + projectsort + 
										"&authorname=" + username + "&projectpassword=" + password ;
				$.ajax({
					url: createUrl,
					type: "POST",
					dataType: "text",
					success: function(data) {
						var result = data.trim();
							res = result.split(',');
						if ( res[0] === "success" ) { 

							/**
							 * 如果创建成功，则显示相应的小提示框显示创建成功
							 */
							/*var $tmpdiv = $('<div>'),
								tmpStrDom = '<span>项目创建成功</span>';

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
									iscreatingPro = false;		// 表示可以接受下次点击事件了
									// 删除后要重新进行跳转
									window.location.href = window.location.protocol + "\/\/" + 
										window.location.host + "\/ShowDoc\/" + "project\/showProject.action";
								},1200); // 显示 0.7s 后隐藏提示框并且从文档流中移除
							});*/

							// 小提示框提示创建成功
							layer.msg( '项目已创建成功', {
								time: 1000
							}, function(){
								iscreatingPro = false;		// 表示可以接受下次点击事件了
								// 删除后要重新进行跳转
								window.location.href = window.location.protocol + "\/\/" + 
								window.location.host + "\/ShowDoc\/" + "project\/showProject.action";
							});
						} else if ( res[0] === "fail" ) {

							/**
							 * 如果创建失败，则显示相应的小提示框显示创建失败
							 */
							/*var $tmpdiv = $('<div>'),
								tmpStrDom = '<span>项目创建时出错</span>';

							// 小提示层的提示文字以及样式
							$tmpdiv.html(tmpStrDom).css({
								    position: "absolute",
								    left: "50%",
								    "marginLeft": "-15px",
								    top: "10%",
								    "lineHeight": "35px",
								    "backgroundColor": "#E29685",
								    color: "#fff",
								    padding: "10px",
								    "borderRadius": "5px",
								    display: "none"
							}).appendTo($('body')).show(function(){
								setTimeout(function(){
									$tmpdiv.fadeOut(300);
									document.body.removeChild($tmpdiv[0]);
									iscreatingPro = false;		// 表示可以接受下次点击事件了
								},1200); // 显示 0.7s 后隐藏提示框并且从文档流中移除
							});*/

							// 小提示框提示创建失败的原因
							layer.msg( '项目创建时出错', {
								time: 1500
							}, function(){
								iscreatingPro = false;		// 表示可以接受下次点击事件了
							});

						} else if ( res[0] === "illegal") {

							/**
							 * 如果创建项目时，返回 illegal的结果，则说明创建项目的表单出现不合格的字段
							 */
							/*var $tmpdiv = $('<div>'),
								tmpStrDom = '<span>' + res[1] + '</span>';

							// 小提示层的提示文字以及样式
							$tmpdiv.html(tmpStrDom).css({
								    position: "absolute",
								    left: "50%",
								    "marginLeft": "-15px",
								    top: "10%",
								    "lineHeight": "35px",
								    "backgroundColor": "#E29685",
								    color: "#fff",
								    padding: "10px",
								    "borderRadius": "5px",
								    display: "none"
							}).appendTo($('body')).show(function(){
								setTimeout(function(){
									$tmpdiv.fadeOut(300);
									document.body.removeChild($tmpdiv[0]);
									iscreatingPro = false;		// 表示可以接受下次点击事件了
								},1200); // 显示 0.7s 后隐藏提示框并且从文档流中移除
							});*/

							// 小提示框提示项目创建时失败时，后台返回的原因
							layer.msg( res[1], {
								time: 1500
							}, function(){
								iscreatingPro = false;		// 表示可以接受下次点击事件了
							});
						} else {

							/**
							 * 如果返回的结果除了上面相应的结果，则返回的是未知错误，相应的跳到错误的页面
							 */
							var nospace = data.replace(/\s/, ""),
							leftPos = nospace.indexOf('<h3 class=\"error-info\">') + "<h3 class=\"error-info\">".length,
							rightPos = nospace.lastIndexOf("<\/h3>"),
							errorText = nospace.substring(leftPos, rightPos),
							currentUrl = window.location.protocol + "\/\/" + window.location.host +
										"\/ShowDoc\/",
							errorUrl = currentUrl +	"exception\/operateVoucherHandle.action?message=" +
										errorText;
			
							// 改变当前 url
							window.location.href = errorUrl;
						}
					}
				});
			}
			return false;
		});
		return false;
	});
});