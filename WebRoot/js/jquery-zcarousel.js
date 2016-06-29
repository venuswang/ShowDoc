;(function($){
	var zCarousel = function ( $target ) {
		var _self = this; 	// 保存this指向zCarousel的引用，防止this漂移
		/* 默认的配置参数 */
		this.settings = {
			width: "100%",	// 轮播容器的宽度, 默认 100%
			height: "100%",	// 轮播容器的高度, 默认 100%
			speed: 10000,	// 自动播放的时间间隔, 默认 10s
			autoPlay: true,	// 是否自动播放, 默认自动播放
			animateTime: 1500	// 切换过程特效持续时间, 默认 1.5s
		};

		this.target = $target;	// 把目标元素保存到对象上
		this.items = $target.find(".item");	// 轮播切换的容器
		this.masks = this.items.find(".container-mask");	// 容器的半透明层
		this.btns = $target.find(".switch-operation").find(".btn");	// 轮播切换的按钮
		this.currentIndex = 0; 	// 当前显示
		this.canAnimation = true; 	// 当前是否有动画，若有动画，则点击无效

		// 获取配置参数并与默认参数合并
		$.extend( this.settings, this.getSettings() );

		// 设置参数
		this.setSettingValue();

		this.showFirstPage();

		// 鼠标移入移出按钮事件
		this.btns.hover(function(){
			$(this).addClass("active");
		},function(){
			$(this).removeClass("active");
			_self.btns.eq(_self.currentIndex).addClass("active");
		}).on("click", function(){	// 按钮点击事件
			var index = _self.btns.index(this);
			if ( index !== _self.currentIndex && _self.canAnimation ) {
				_self.canAnimation = false;	// 暂时让点击失效
				_self.animateShow( _self.currentIndex, index );
				_self.currentIndex = index;
				_self.btns.removeClass("active").eq(index).addClass("active");
			}
		});

		/*// 是否开启自动播放功能
		if ( this.settings.autoPlay ) {
			// 开启自动播放功能
			this.autoPlay();

			// 鼠标移入移出切换自动播放功能
			this.masks.hover(function(){
				window.clearInterval( _self.trimer );
			},function(){
				_self.autoPlay();
			});
		}*/
		
	};
	zCarousel.prototype = {
		/**
		 * 获取配置参数
		 * @return {[type]} 返回对象
		 */
		getSettings : function () {
			var setting = this.target.attr( "data-setting" );
			if ( setting && setting != "" ) {
				return $.parseJSON( setting );
			} else {
				return {};
			}
		},

		/**
		 * 设置配置的参数
		 */
		setSettingValue: function() {
			this.target.css({
				width: this.settings.width,
				height: this.settings.height
			});
		},

		/**
		 * 让第一幅page显示在视窗中
		 * @return {[type]} [description]
		 */
		showFirstPage: function() {
			this.items.css({
				left: "100%",
				display: "none"
			}).eq(0).css({
				left: 0,
				"zIndex": "5",
				display: "block"
			});
		},

		/**
		 * 动画形式显示当前page
		 * @param  {[type]} oldIndex     先前显示的page
		 * @param  {[type]} currentIndex 当前要显示的page
		 * @return {[type]}              [description]
		 */
		animateShow: function( oldIndex, currentIndex ) {
			var _self = this;

			// 此处一定要注意先让原先的page的层级降低
			this.items.eq(oldIndex).css({
				zIndex: "0"
			}).end().eq(currentIndex).css({
				zIndex: "5",
				display: "block"
			}).animate({
							left:0
						}, _self.settings.animateTime, function(){
							_self.items.eq(oldIndex).css({
								left: "100%",
								display: "none"
							});
							_self.canAnimation = true; 	// 动画完成后让点击有效
						});
		},

		/**
		 * 下个page自动显示的逻辑
		 * @return {[type]} [description]
		 */
		autoPlay: function() {
			var _self = this;

			if ( _self.trimer != "undefined" ){
				window.clearInterval( _self.trimer );
			}

			_self.trimer = window.setInterval(function(){
				_self.nextPageAutoShow();
			}, _self.settings.speed );
		},

		/**
		 * 下个page自动显示的方法
		 * @return {[type]} [description]
		 */
		nextPageAutoShow: function(){
			var _self = this,
				btnsSum = _self.btns.length,
				nextIndex = _self.currentIndex + 1;

			if ( nextIndex === btnsSum ) {
				nextIndex = 0;
			}
			console.log( nextIndex );
			// 触发相应按钮的点击事件，实现自动播放
			_self.btns.eq(nextIndex).click();
		}
	};

	/* 对每个轮播对象进行初始化 */
	zCarousel.init = function( $targets ) {
		var _this_ = this;	// this  表示  zCarousel 本身
		$targets.each(function(){
			new _this_( $(this) );
		});
	};

	// 把插件名添加到window命名空间下
	window['zCarousel'] = zCarousel;
})( jQuery );