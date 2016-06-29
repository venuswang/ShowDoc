;(function( $ ){
	var LoginForm = function ( $target ) {

	};

	LoginForm.prototype = {

	};

	/**
	 * 对包装集中的每个包装对象进行实例化
	 * @param  {[type]} $targets 包装集
	 * @return {[type]}          返回一个 LoginForm 实例对象
	 */
	LoginForm.init = function ( $targets ) {
		// 保存 this 对象, 防止 this 漂移
		var _this_ = this;

		// 对每个选中的包装对象进行实例化
		$targets.each(function(){
			return _this_( $(this) );
		});
	};


})( jQuery );