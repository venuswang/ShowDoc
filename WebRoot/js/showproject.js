$(function() {
	var testEditor,
		$body = $('body'),
		$header = $body.find('#header'),                              // 头部
        $headerTitle = $header.find('.common-middle').find('.middle-title'),  // 项目名称
        $commonRight = $header.find('.common-right').eq(0),           // common-right
        $manageBtn = $commonRight.find('.btn-manage'), // 项目管理按钮
        $manageItems = $header.find('.feature-list'),                   // 项目管理的项
		$main = $body.find('#main'),                                  // main主体
		$navSide = $main.find('.sideNav'),                            // 侧边栏
        $projectList = $navSide.find('#project-nav').find('#project-list'), // 侧边栏中目录列表
		$addPageBtn = $navSide.find('.add-page-item').find('.btn'),   // 新建页面按钮
        $addDirBtn = $navSide.find('.add-dir-item').find('.btn'),     // 新建目录按钮
		$itemList = $navSide.find('#project-nav'),                    // 侧边栏导航
		$dirItemBtn = $itemList.find('.btn-dir'),                     // 目录按钮
        $pageItemBtn = $itemList.find('.btn-page'),                   // 页面按钮
		$showContent = $main.find('.show-content'),                   // 显示markdown页面内容的容器
		$operations = $showContent.find('.content-operations'),       // 页面操作
        $contentShareBtn = $operations.find('.btn-share'),            // 分享按钮
        $contentCopyBtn = $operations.find('.btn-copy'),              // 复制按钮
        $contentEditBtn = $operations.find('.btn-edit'),              // 编辑按钮
        $contentRemoveBtn = $operations.find('.btn-remove'),          // 删除按钮
		$contentTitle = $showContent.find('.content-title').find('.title'),   // markdown 文档展示区的标题
		$markdownContent = $showContent.find('#markdown-content'),    // markdown文档展示区
        $mask = $body.find('#mask'),                                // mask遮罩层
        manageItemsHoving = false,                                  // 控制是否进行动画
        wh = $(window).height(),                                    // 窗口高度
        dh = $(document).height(),                                  // 文档高度
        mh = wh > dh ? wh : dh,                                     // 遮罩层高度
        iscreatingdir = false,                                      // 防止重复点击创建目录时的创建按钮
        windowUrl = window.location,                                // 浏览器当前的地址栏中的url
        urlParams = decodeURI( windowUrl.search ),                  // 浏览器地址栏中的url中带的参数
        projectid,                                                  // 项目ID
        projectName = '',                                           // 项目名字
        projectpassword = '',                                       // 项目访问的密码
        levelUrl = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc" + "/subProject/querySubProject/",    //查询某个parent下的所有目录
        dirchanges = [];                                            // 数组，记录是否有新增的数组，来确定侧边栏的目录树是否要重新获取相关数据

        var temps = urlParams.replace(/\?\s*/g, '').split('&');     // 把参数分割成数组

        // 将参数的值解析保存到相应的变量中
        for( var i = 0, len = temps.length; i < len; i ++) {
            var temp = temps[i].split('=');
            if ( temp[0] === 'id' ){
                projectid = temp[1] || '';
            }
            if ( temp[0] === 'projectname' ){
                projectName = temp[1] || '';
            }
            if ( temp[0] === 'check' ) {
                projectpassword = temp[1] || '';
            }
        }

    $headerTitle.text( projectName );   // 标题显示项目名称


    // 从后台拿某个项目下的一级目录以及一级页面先填充到侧边栏
    function Initial() {
        $.ajax({
            url: levelUrl + projectid + '/-1',      // 获取一级目录
            type: 'GET',
            dataType: 'json',
            success: function( data ) {
                var lihtmlDom = '',
                    len = data.length;

                for ( var i = 0; i < len; i++ ) {
                    lihtmlDom += '<li class="dir-item"><a href="javascript:void(0);" class="btn btn-dir" data-type="dir" data-parent="' + 
                                data[i].subprojectid +'">' + '<span class="triangle left"></span>' + data[i].subprojectname + '</a></li>';
                }
                $projectList.html( lihtmlDom );
            },
            error: function( error ) {
                console.log( error );
            }
        });
    }

    // 进入这个页面先进行一级目录的初始化显示操作
    Initial();
	// editor.md 初始化
    testEditor = editormd("test-editormd", {
        width           : 0,
        height          : 0,        
        path            : "../lib/",
        htmlDecode      : "style,script,iframe", 
    });
    
    // 点击新建页面按钮时的处理
    $addPageBtn.on('click', function(){
    	/* 待处理 */
    });

    // 点击新建目录按钮时的处理
    $addDirBtn.on('click', function(){

        // 重新计算文档的高度
        dh = $(document).height();
        // 重新计算遮罩层的高度
        mh = wh > dh ? wh : dh; 
        $mask.css('height', mh).fadeIn(200);
        $mask.load('tpls/create-dir.html', function( response, status, xhr ){
            var $contrainer = $mask.find('.create-dir-container'),
                height = $contrainer.height(),
                top = (wh - height) / 2;

            top  = top > 0 ? top : 20;
            $contrainer.css('top', -height)
                        .animate({
                            top: top,
                            opacity: 1
                        }, 200, function(){
                            var url = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc" + "/subProject/getAllProject/" + projectid;

                            // 从后台拿到某个项目下已经存在的目录，添加到下拉框中，以及显示到已有目录列表中
                            $.ajax({
                                url: url,
                                type: 'GET',
                                dataType: 'json',
                                success: function( data ){
                                    var $select = $contrainer.find('.form-item').find('#_dirparname'),  // 下拉框
                                        $dirList = $contrainer.find('.exited-dir').find('.dir-list'),   // 已有目录列表ul
                                        selecthtmlDom = '',       // 将要添加到下拉框中的html
                                        dirlisthtmlDom = '';      // 将要添加到dirlist列表中的html

                                    for ( var i = 0, len = data.length; i < len; i++) {
                                        selecthtmlDom += '<option value="' + data[i].subProjectName + '" data-level="' + 
                                                data[i].nextLevel + '" data-parent="' + data[i].subProjectId + '">' + 
                                                data[i].subProjectName + '</option>';
                                        if ( data[i].subProjectName !== '无' ) {
                                            dirlisthtmlDom += '<li class="dir-item">' + '<span class="item-name">' +
                                                        data[i].subProjectName + '</span></li>';
                                        }
                                    }

                                    $select.html( selecthtmlDom );  // 填充内容到下拉框
                                    $dirList.html( dirlisthtmlDom );    // 填充内容到目录列表

                                    // 添加后要重新设置遮罩层的高度，防止被表单高度超过
                                    height = $contrainer.height() + (wh - height > 0 ? wh - height : 20 ) + 20;
                                    mh = mh > height ? mh : height;
                                    $mask.css( 'height', mh );
                                },
                                error: function( error ) {
                                    console.log( error );
                                }
                            });
                        });
        });     // load 新建目录的模板
    });

    // 点击遮罩层的事件，已经把遮罩层里面的元素的事件委托给遮罩层
    $mask.on('click', function( event ){
        var $target = $( event.target ),
            eventTarget = $target.data('event');
        
        // 利用switch选择在$mask触发的点击事件，属于那个节点触发的
        switch( eventTarget ) {
            case 'close':       // 关闭遮罩层
                $mask.fadeOut(200);
                // 清除遮罩层里面的内容
                $mask.html('');
                break;
            case 'createdir':   // 点击新建目录下，点击创建表单时触发的事件，event.target 是创建按钮
                if ( !iscreatingdir ) {

                    iscreatingdir = true;       // 置为 true 防止重复点击仍然生效

                    var $contrainer = $target.parents('.create-dir-container'),
                        $form = $contrainer.find('.create-dir-form'),
                        $formItems = $form.find('.form-item'),           // 表单项
                        $dirname = $formItems.find('#_dirname'),        // 目录名输入框
                        $dirnum = $formItems.find('#_dirnum'),          // 排序输入框
                        $dirparname = $formItems.find('#_dirparname'),  // 下拉框
                        subprojectname = $dirname.val(),                // 将要创建的目录的名字
                        subsortid = parseInt( $dirnum.val() ),          // 排序的数字
                        $parent = $dirparname.find('option:selected'),  // 下拉框中选中的元素
                        parentName = $parent.val(),             // 父级目录名
                        parentid = $parent.data('parent'),      // 父级目录id
                        sublevel = $parent.data('level'),       // 将要创建的目录的层级
                        url = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc" + "/subProject/addSubProject",       // 请求处理的servelet
                        params = {};

                    params = {
                            projectid: projectid,
                            subprojectname: subprojectname,
                            sublevel: sublevel,
                            parentid: parentid,
                            parentName: parentName
                    };

                    // 如果排序输入框中是数字，则添加到要发送的字段中去
                    if ( !isNaN( subsortid ) ) {
                        params.subsortid = subsortid;
                    }

                    // 如果项目有访问密码，则将密码添加到要发送的字段中去
                    if ( projectpassword !== '' ) {
                        params.subprojectpassword = projectpassword;
                    }
                    $.ajax({
                        url: url,
                        type: 'POST',
                        dataType: 'json',
                        data: params,
                        success: function( data ) {
                            iscreatingdir = false;      // 置为 false 让下次点击有效
                            var sucFlag = data.result;
                            if ( sucFlag ) {
                                /**
                                 * 如果创建目录成功，则弹出一个小弹窗显示成功创建，提示框3秒后自动关闭
                                 */
                                layer.msg('已成功创建目录',{
                                    time: 3000
                                }, function(){
                                    $mask.trigger('click');     // 触发遮罩层的点击事件，让其消失

                                    // 新增了一个目录，要把新增的相应的父目录的id添加到dirchanges数组中
                                    dirchanges.push( parentid );

                                    // 如果创建的是一级目录，则要对侧边栏的一级目录重新进行初始化操作
                                    if( parentid === -1 ) {
                                        console.log( '初始化操作' );
                                        Initial();     
                                    }
                                }); 
                            } else {
                                /**
                                 * 如果创建目录失败，则弹出一个小弹窗显示失败的原因
                                 */
                                layer.msg(data.message,{
                                    time: 3000
                                }); 
                            }

                        },
                        error: function( error ) {
                            console.log( error );
                        }
                    });
                }
                break;
        }

        // 去除事件的默认行为，阻止冒泡
        event.preventDefault();
        event.stopPropagation();
    });

    // 将侧边栏项目的目录列表中的目录，page的点击事件都冒泡到其父元素 ul 中处理
    $projectList.on('click', function(event) {
        var $target = $( event.target ),        // 获取点击的目标对象节点
            type = $target.data('type'),        // 根据节点中自定义的type属性，获取该次该处理的事件类型是dir事件，还是page事件
            $triangle = $target.find('.triangle').eq(0);    // 获取目标对象节点下的 span.triangle元素

        // switch 根据获取的type值，分发处理不同的事件程序
        switch( type ) {
            case 'dir':         // 处理的是 dir 事件
                var parentid = $target.data('parent'),      // 获取存储在自定义属性的parent中的值，代表该目录中数据库中的id
                    $ulList = $target.siblings('.project-list');    // 获取该目录下面含有project-list的ul元素列表
                if ( $ulList.length > 0 && $ulList.is(':visible') || $triangle.hasClass('top') ) {

                    /**
                     * 如果$ulList大于0，则表示已经获取过该目录id下面的子目录了，且是展开状态时，则折叠起来
                     */
                    $ulList.slideUp(300);   // 将目录折叠起来

                    // 切换三角形的图标显示
                    $triangle               
                        .toggleClass('left')
                        .css('transition', 'all 0.3s linear')
                        .toggleClass('top')
                        .css('transition', '');
                        return;
                }
                if ( $ulList.length > 0 && dirchanges.indexOf( parentid ) === -1 ) {

                    /**
                     * 如果是已经获取过的情况，且是折叠的情况，则将该目录展开
                     */
                    $ulList.hide().slideDown(300);
                    $triangle
                        .toggleClass('left')
                        .css('transition', 'all 0.3s linear')
                        .toggleClass('top')
                        .css('transition', '');
                } else {

                    /**
                     * 如果还没获取，或者还没有目录的情况，则尝试去获取该目录下是否有子目录，且dirchanges记录的目录变化有相应的目录新增时要重新获取
                     * @param  {[type]} data )             {                            var len [description]
                     * @return {[type]}      [description]
                     */
                    // 如果数组中有变化，且相应的parentid是这个id，则要删除dirchanges数组中该parentid元素
                    var index = dirchanges.indexOf( parentid );

                    if ( index !== -1 ) {
                        dirchanges.splice(index, 1);
                    }

                    $ulList.remove();       // 移除掉以前获取的目录列表
                    $.ajax({
                        url: levelUrl + projectid + '/' +parentid,
                        type: 'GET',
                        dataType: 'json',
                        success: function( data ) {
                            var len = data.length,
                                $ul = $('<ul>'),
                                ulhtmlDom = '';

                            $ul.addClass('project-list');

                            if ( len > 0 ) {
                                for ( var i = 0; i < len; i++ ) {
                                    ulhtmlDom += '<li class="dir-item"><a href="javascript:void(0);" class="btn btn-dir" data-type="dir" data-parent="' + 
                                    data[i].subprojectid +'">' + '<span class="triangle left"></span>' + data[i].subprojectname + '</a></li>';
                                }

                                $ul.html( ulhtmlDom ).hide();
                                $target.after( $ul );
                                $ul.slideDown(300);
                            }
                            $triangle
                                .toggleClass('left')
                                .css('transition', 'all 0.3s linear')
                                .toggleClass('top')
                                .css('transition', '');
                        },
                        error: function( error ) {
                            console.log( error );
                        }
                    });
                }
                break;
            case 'page':
                console.log( '处理page事件' );
                break;
        }
    });

    // 点击页面按钮时获取相关内容显示在 showcontent中
    $pageItemBtn.on('click', function(){

        /* 使容器可见 */
        $showContent.show();

    	/* 设置标题 */
    	$contentTitle.text( $(this).text() );

    	/* 获取markdown 内容 */
        $.get("./tpls/api.md", function(md){
            $markdownContent.html("");
            window.editormd.markdownToHTML('markdown-content',{
                markdown: md
            });
        });
    });

    // 点击分享按钮事件
    $contentShareBtn.on('click', function(){
        console.log( 'share' );
    });

    // 点击复制按钮事件
    $contentCopyBtn.on('click', function(){
        console.log( 'copy' );
    });

    // 点击编辑按钮事件
    $contentEditBtn.on('click', function(){
        console.log( 'edit');
    });

    // 点击删除按钮事件
    $contentRemoveBtn.on('click', function(){
        console.log( 'remove' );
    });

    // 点击项目管理按钮事件
    $manageBtn.on('click', function(){
        // 待处理
        console.log( $manageItems );
    });

    
    // 鼠标移入移除项目管理按钮时的事件处理
    $commonRight.hover(function(event){

        /**
         * 在已经有鼠标移入并且已经开始动画了后，动画还没完成，鼠标就已经移出，再次移入时不处理
         */
        if ( !manageItemsHoving ) {
            $manageBtn.addClass( 'hover' );
            $manageItems.fadeIn(100)
                        .css('top', '25px')
                        .animate({
                            top: '35px'
                        }, 300, function(){
                            manageItemsHoving = false;
                        });
        }

        // 取消事件的默认行为，以及向上冒泡
        event.preventDefault();
        event.stopPropagation();
    }, function(event){
        $manageBtn.removeClass( 'hover' );
        $manageItems.fadeOut( 200 );

        // 取消事件的默认行为，以及向上冒泡
        event.preventDefault();
        event.stopPropagation();
    });

    // 委托项目管理中的各个按钮的点击事件，直接到 $commonRight 项目管理各个按钮的最远父元素
    $commonRight.on('click', function( event ){
        var $target = $( event.target ),
            dataItem = $target.data('item');

        switch( dataItem ){
            case 'share': 
                console.log('分享');
                break;
            case 'modify':
                console.log('修改');
                break;
            case 'member':
                console.log('成员');
                break;
            case 'transfer':
                console.log('转让');
                break;
            case 'delete':
                console.log('删除');
                break;
        }
    });

});