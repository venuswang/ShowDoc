$(function(){
	var $header = $( '#header' ),
		$userCenter = $header.find( '#user-center' ),
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
		$modifyInfoCancel = $modifyInfo.find( '.btn-cancel' );

	// 鼠标移入移除用户头像时显示下拉菜单	
	$userCenter.hover(function(){
		$subNav.show(100);
	},function(){
		$subNav.hide(100);
	});

	//点击下拉菜单时相应切换不同的视图
	$subNavItems.on('click', function(){
		var data = $(this).data("dist");

		$subNav.hide();
		if ( data != undefined ) {
			var $target = $( '#' + data ),
				wH = $(window).height();

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

	//下拉菜单插件配置参数	
	$('.SlectBox').SumoSelect({
		csvDispCount: 12,
		forceCustomRendering: true,
		search: true
	});
});