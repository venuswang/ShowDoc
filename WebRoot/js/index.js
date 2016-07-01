$(function(){

	// 初始化插件轮播组件
	zCarousel.init( $( '#container' ) );

	var $container = $( '#container' ),
		$funcitems = $container.find( '.function-container' ).find( '.list-item' ),
		$tooltip = $container.find( '.tooltip' ),
		$tooltipText = $tooltip.find( '.tooltip-content' ),
		$tooltipIcon = $tooltip.find( '.icon' ),
		$btnLogin = $container.find( '.login-container' ).find( '.login-operations' ).find( '.btn-login' ),
		tooltipInfo = {
			API: "服务端提供API,APP端或者网页前端便可方便调用数据,用ShowDoc可以非常方便快速地编写出美观的API文档.",
			dictionary: "一份好的数据字典可以很方便地向别人说明你的数据库结构，如各个字段的释义等.",
			doc: "你完全可以使用showdoc来编写一些工具的说明书,也可以编写一些技术规范说明文档以供团队查阅.",
			sharing: "项目导出成word文件，以便离线浏览.",
			competence: "提供对自己项目的权限管理,有权限者才有权限修改.",
			project: "公开项目可供任何登录与非登录的用户访问,而私密项目则需要输入密码验证访问,密码由项目创建者设置.",
			member: "你可以很方便地为ShowDoc的项目添加、删除项目成员,项目成员可以对项目进行编辑,但不可转让或删除项目（只有项目创建者才有权限）.",
			edit: "ShowDoc采用markdown编辑器,无论是编辑还是阅读体验都极佳很棒.",
			template: "在ShowDoc的编辑页面,点击编辑器上方的按钮可方便地插入API接口模板和数据字典模板,插入模板后,剩下的就是改动数据了,省去了很多编辑的力气.",
			history: "ShowDoc为页面提供历史版本功能,你可以方便地把页面恢复到之前的版本."
		},
		canAnimation = true;	// 判断当前是否有tooltip显示的动画正在执行

	$funcitems.hover(function(){

		/**
		 * 判断当前是否有tooltip显示的动画正在执行,没有tooltip动画执行的情况才能执行
		 */
		if ( canAnimation ) {
			canAnimation = false;	// 此时要执行动画, 让可以执行动画变为不可再执行下一个动画
			var offset = $( this ).offset(),
				left = offset.left,
				top = offset.top,
				height = $( this ).height(),
				width = $( this ).outerWidth(),
				right = left + width,
				topdis = top + height / 2,
				middle = $( window ).width() / 2,
				itemStyle = $( this ).find(".item-content").data( "item" );

			// 插入提示信息
			if ( itemStyle && itemStyle !== "" ) {

				/**
				 * 如果提示信息的类型不为空,则向提示框中插入相应的提示内容
				 */
				$tooltipText.text( tooltipInfo[ itemStyle ] );
			}
			if ( left < middle ) {

				/**
				 * 对于左边的列表的提示框，出现在列表项的右边
				 */
				
				$tooltip.css({
					left: right,
					opacity: 0
				}).show( 200, function(){
					var tooltipH = $( this ).height() / 2;

					$tooltipIcon.addClass( 'left-triangle' ).removeClass( 'right-triangle' );
					topdis -= tooltipH;
					$( this ).css({
						top: topdis,
						opacity: 1
					});

					canAnimation = true;	// 此时动画执行完毕, 状态变为可以执行下个动画
				});

			} else {

				/**
				 * 对于右边的列表的提示框，出现在列表的左边
				 */
				$tooltip.css({
					opacity: 0
				}).show( 200, function(){
					var tooltipH = $( this ).height() / 2,
						tooltipW = $( this ).outerWidth();

					$tooltipIcon.addClass( 'right-triangle' ).removeClass( 'left-triangle' );
					left -= tooltipW;
					topdis -= tooltipH;
					$( this ).css( {
						left: left,
						top: topdis,
						opacity: 1
					});
					
					canAnimation = true;	// 此时动画执行完毕, 状态变为可以执行下个动画
				});
			}
		}

	},function(){
		$tooltipText.text( "" );
		$tooltip.hide();
	});

	// 点击登陆按钮时弹出登录UI
	$btnLogin.on("click", function() {
		var $loginContainer = $( '.login-form-container' ),
			$loginContent = $loginContainer.find( '.login-form-pos' ),
			$loginSubmit = $loginContent.find( '.form-submit'),
			$form = $loginContent.find( '.login-form' ),
			$username = $form.find('#login-username'),
			$password = $form.find('#login-password'),
			$loginError = $loginContent.find('.login-error'),
			offset = 0,
			width = 0,
			height = 0,
			left = 0,
			top = 0;

		$loginContainer.css("display", "block").on("click", function(event) {
			if ( this === event.target ) {
				$( this ).hide();
			}
		});

		// 特别注意当父元素为  display: none 时，获取的子元素的宽度高度都为0
			offset = $loginContent.offset();
			width = $loginContent.width();
			height = $loginContent.height();
			left = width + offset.left;
			top = height + offset.top;

			// 以动画形式显示登录UI
			$loginContent.css({
				left: "50%",
				top: -top,
				marginLeft: -( width / 2 )
			}).animate({
				top: "50%",
				marginTop: -( height / 2 ),
				opacity: 1
			}, 200);

		// 绑定 关闭按钮事件
		$loginContainer.find( '.btn-close' ).on("click", function(){
			$loginContainer.trigger("click");
		});

		// 绑定submit事件
		$loginSubmit.on('click', function(){
			$loginError.hide();
			var action = $form.attr('action'),
				username = $username.val(),
				password = $password.val(),
				url = action + "?username=" + username + "&password=" + password;
			$.ajax({
				url: url,
				type: "post",
				dataType: "text",
				success: function( data ){
					var result = data.trim(),
						results = result.split(",");
					if ( results[0] === "success" ) {
						/*results[1] = "jsp/project/userproject.jsp";*/
						var nextUrl = window.location.href + results[1];
						window.location.href = nextUrl;
					} else if ( results[0] === "fail" || results[0] === "illegal") {
						$loginError.show();
					} else {
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
				},
				error: function( xhr ) {
					console.log( xhr );
					console.log( "error" );
				}
			});
			return false;
		});
	});
});