$(function() {
	var testEditor,
		$body = $('body'),
		$header = $body.find('#header'),
		$main = $body.find('#main'),
		$navSide = $main.find('.sideNav'),
		$addItemBtn = $navSide.find('.add-page-item').find('.btn'),
		$itemList = $navSide.find('#page-nav').find('.page-list'),
		$itemBtns = $itemList.find('.page-item').find('.btn'),
		$showContent = $main.find('.show-content'),
		$contentBtns = $showContent.find('.content-operations').find('.btn'),
		$contentTitle = $showContent.find('.content-title').find('.title'),
		$markdownContent = $showContent.find('#markdown-content');

	// editor.md 初始化
    testEditor = editormd("test-editormd", {
        width           : 0,
        height          : 0,        
        path            : "../lib/",
        htmlDecode      : "style,script,iframe", 
    });
    
    /* 点击新建按钮时的处理 */
    $addItemBtn.on('click', function(){
    	/* 待处理 */
    });

    /* 点击每个侧边栏内容导航按钮时的处理 */
    $itemBtns.on('click', function(){

        /* 使容器可见 */
        $showContent.show();

    	/* 设置标题 */
    	$contentTitle.text( $(this).text() );

    	/* 获取markdown 内容 */
        $.get("./tpls/table.md", function(md){
            $markdownContent.html("");
            window.editormd.markdownToHTML('markdown-content',{
                markdown: md
            });
        });
    });
});