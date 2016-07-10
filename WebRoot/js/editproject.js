$(function(){
	var editor,
		$body = $('body'),
		$contrainer = $body.find('.contrainer'),
		$form = $contrainer.find('#project-form'),
		$mdItmes = $form.find('.form-markdown-items'),
		$mdItmesBtn = $mdItmes.find('.form-markdown-operations').find('.btn');

	// editor.md 初始化
    editor = editormd("markdown-editormd", {
        height          : 720,        
        path            : "../lib/",
        htmlDecode      : "style,script,iframe",
        placeholder		: "用mardown语法编辑你的项目,右边可以实时预览" 
    });

    $mdItmesBtn.on('click', function(){
    	var data = $(this).data('role');

    	if( data === "API" ) {
    		$.get('./tpls/api.md', function(md) {
    			editor.appendMarkdown(md);
    		});
    	} else if ( data === "data" ) {
    		$.get('./tpls/data.md', function(md) {
    			editor.appendMarkdown(md);
    		});
    	} else if ( data === "json" ) {

    		/* json 的情况下待处理 */
    	} else if ( data === "http" ) {

    		/* http 的情况下待处理 */
    	}
    });
});