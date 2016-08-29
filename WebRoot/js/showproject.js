$(function() {
	var testEditor = '',
		$body = $('body'),
		$header = $body.find('#header'),                              // 头部
        $headerTitle = $header.find('.common-middle').find('.middle-title'),  // 项目名称
        $commonRight = $header.find('.common-right').eq(0),           // common-right
        $manageBtn = $commonRight.find('.btn-manage'), // 项目管理按钮
        $manageItems = $header.find('.feature-list'),                   // 项目管理的项
		$main = $body.find('#main'),                                  // main主体
		$navSide = $main.find('.sideNav'),                            // 侧边栏
        $projectList = $navSide.find('#project-nav').find('#project-list'), // 侧边栏中目录列表
		$addPageBtn = $navSide.find('.add-item-operations').find('.btn-add-page'),   // 新建页面按钮
        $addDirBtn = $navSide.find('.add-item-operations').find('.btn-add-dir'),     // 新建目录按钮
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
        newPageid = '',                                             // 记录创建或者更新传过来的pageid
        levelUrl = window.location.protocol + "//" + window.location.host +
                    "/ShowDoc" + "/subProject/querySubProject/",    // 查询某个parent下的所有目录url
        pageUrl = window.location.protocol + "//" + window.location.host +
                    "/ShowDoc/page/selectPageByPid/",       // 查询某个parent下的所有页面url
        dirchanges = [], // 数组，记录是否有新增的数组，来确定侧边栏的目录树是否要重新获取相关数据
        isaddinguser = false,       // 控制变量，防止多次点击添加项目协作者的点击事件有效
        isdeletinguser = false,     // 控制变量，防止多次点击删除项目协作者的点击事件有效
        pageId = '',                // 记录当前的pageid
        isupdatingdir = false;      // 控制阻止多次点击更新目录信息的保存按钮事件有效
        isdeletingdir = false;      // 控制阻止多次点击删除目录的按钮事件有效

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
        if ( temp[0] === 'pageid' ) {
            newPageid = temp[1] || '';
        }
    }

    $headerTitle.text( projectName );   // 标题显示项目名称

    
   /* // 把页面的内容通过markdown展示
        window.editormd.markdownToHTML('markdown-content',{
            markdown: ''
        });
*/    // 从后台拿某个项目下的一级目录以及一级页面先填充到侧边栏
    function Initial() {
        $.ajax({
            url: levelUrl + projectid + '/-1',      // 获取一级目录
            type: 'GET',
            dataType: 'json',
            success: function( data ) {
                var lihtmlDom = '',
                    len = data.length;

                for ( var i = 0; i < len; i++ ) {
                    lihtmlDom += '<li class="dir-item" data-tooltip="' + '目录：' + data[i].subprojectname + '">' + 
                        '<a href="javascript:void(0);" class="btn btn-dir" data-type="dir" data-parent="' + data[i].subprojectid  + '">' + 
                        '<span class="triangle left"></span>' + data[i].subprojectname + '</a></li>';
                }

                // 从后台获取该项目的的一级page页面的数据
                $.ajax({
                    url: pageUrl + '-1/' + projectid,
                    type: 'GET',
                    dataType: 'json',
                    success: function( data ) {
                        var plen = data.length;

                        // 将返回的页面添加到ul列表中
                        for( var i = 0; i < plen; i++ ) {
                            lihtmlDom += '<li class="page-item" data-tooltip="' + 'page：' + data[i].pagetitle + '">' + 
                                        '<a href="javascript:void(0);" ' + 'class="btn btn-page" data-type="page" data-page="' + 
                                        data[i].pageid + '" data-parent="-1">' + data[i].pagetitle + '</a></li>';
                        }

                        // 将目录，page填充到侧边栏中
                        $projectList.html( lihtmlDom );

                        $pages = $projectList.find('.page-item').find('.btn-page');
                        for( var i = 0, len = $pages.length; i < len; i++ ) {
                            if ( $pages.eq(i).data('page') == newPageid ) {
                                $pages.eq(i).addClass('active');
                            }
                        }

                    },
                    error: function( error ) {
                        console.log( error );
                    }
                });
            },
            error: function( error ) {
                console.log( error );
            }
        });
    }

    // 进入这个页面先进行一级目录的初始化显示操作
    Initial();

    // 如果是新创建或者编辑页面后跳转到这个页面，则需要把相应的页面内容load出来
    if ( newPageid != '' ) {
            pageId = newPageid; // 记录当前的page
            showPageByURL( window.location.protocol + "//" + window.location.host +
                    "/ShowDoc/page/selectPageById/" + newPageid + '/' + projectid );
    }
    
    // 点击新建页面按钮时的处理
    $addPageBtn.on('click', function( event ){
    	var editUrl = window.location.protocol + "//" + window.location.host +
                "/ShowDoc/jsp/project/editproject.jsp?projectid=" + projectid + 
                '&projectname=' + projectName + '&check=' + projectpassword + '&type=new'; // type 参数为new表示是新建page

        // 带参数进行跳转到编辑页面
        window.location.href= editUrl;
        // 阻止事件的默认行为以及冒泡
        event.preventDefault();
        event.stopPropagation();
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
                                            dirlisthtmlDom += '<li class="dir-item">' + 
                                                    '<a href="javascript:void(0);" class="item-name btn" data-event="editdir"' + ' data-parent="' + data[i].subProjectId + '">' + data[i].subProjectName + '</a></li>';
                                        }
                                    }

                                    $select.html( selecthtmlDom );  // 填充内容到下拉框
                                    $dirList.html( dirlisthtmlDom );    // 填充内容到目录列表

                                    // 添加后要重新设置遮罩层的高度，防止被表单高度超过
                                    height = $contrainer.height() + (wh - height > 0 ? wh - height : 20 );
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

                    // 新建mul成功后，将表单置空
                    $dirname.val('');
                    $dirnum.val('');
                    $dirparname.val('');


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
                                var dirurl = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc" + "/subProject/getAllProject/" + projectid
                                // 从后台拿到某个项目下已经存在的目录，更新当前目录列表下框中值已经已经存在的目录列表
                                $.ajax({
                                    url: dirurl,
                                    type: 'GET',
                                    dataType: 'json',
                                    success: function( data ){
                                        var $select = $contrainer.find('.form-item').find('#_dirparname'),  // 下拉框
                                            $dirList = $contrainer.find('.exited-dir').find('.dir-list'),   // 已有目录列表ul
                                            selecthtmlDom = '',       // 将要添加到下拉框中的html
                                            dirlisthtmlDom = '',      // 将要添加到dirlist列表中的html
                                            height = $contrainer.height();  // 容器高度

                                        for ( var i = 0, len = data.length; i < len; i++) {
                                            selecthtmlDom += '<option value="' + data[i].subProjectName + '" data-level="' + 
                                                    data[i].nextLevel + '" data-parent="' + data[i].subProjectId + '">' + 
                                                    data[i].subProjectName + '</option>';
                                            if ( data[i].subProjectName !== '无' ) {
                                                dirlisthtmlDom += '<li class="dir-item">' + 
                                                    '<a href="javascript:void(0);" class="item-name btn" data-event="editdir"' + ' data-parent="' + data[i].subProjectId + '">' + data[i].subProjectName + '</a></li>';
                                            }
                                        }

                                        $select.html( selecthtmlDom );  // 填充内容到下拉框
                                        $dirList.html( dirlisthtmlDom );    // 填充内容到目录列表

                                        // 添加后要重新设置遮罩层的高度，防止被表单高度超过
                                        height = $contrainer.height() + (wh - height > 0 ? (wh - height)/2 : 20 );
                                        mh = mh > height ? mh : height;
                                        $mask.css( 'height', mh );
                                    },
                                    error: function( error ) {
                                        console.log( error );
                                    }
                                });

                                layer.msg('已成功创建目录',{
                                    time: 1000
                                }, function(){

                                    // 新增了一个目录，要把新增的相应的父目录的id添加到dirchanges数组中
                                    dirchanges.push( parentid );

                                    // 如果创建的是一级目录，则要对侧边栏的一级目录重新进行初始化操作
                                    if( parentid === -1 ) {
                                        Initial();     
                                    }
                                }); 
                            } else {
                                /**
                                 * 如果创建目录失败，则弹出一个小弹窗显示失败的原因
                                 */
                                layer.msg(data.message,{
                                    time: 1500
                                }); 
                            }

                        },
                        error: function( error ) {
                            console.log( error );
                        }
                    });
                }
                break;
            case 'removepage':
                var removepageid = $showContent.data('page'),   // 将要删除的page ID
                    project = $showContent.data('project'),     // page 所在的项目id
                    removeparentid = $showContent.data('parent'), // 将要删除的页面所属的目录id
                    removeUrl = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc/page/deletePageById/" + pageId + '/' + projectid; // 处理这个请求的服务地址

                // 往服务器发送请求，处理删除这个pgae
                $.ajax({
                    url: removeUrl,
                    type: 'POST',
                    dataType: 'json',
                    success: function( data ){

                        $mask.trigger('click');     // 关闭遮罩层

                        $showContent.fadeOut(1);    // 清除markdown中的展示区

                        /**
                         * 如果成功删除了这个页面，则要重新加载侧边栏中的目录树
                         * @param  {[type]} data.result [description]
                         * @return {[type]}             [description]
                         */

                        if ( data.result ) {

                            // 如果删除的是直接项目底下的页面，则直接调用初始化侧边栏目录树的方法即可
                            if ( removeparentid === -1 ) {
                                Initial();      // 重新初始化该项目的一级目录以及一级page
                            } else {
                                // 告诉相应的父级说明，说明该目录下有更新，要重新去后台拿取新的数据
                                dirchanges.push( parentid );

                                // 视图中相应的删除这个page列表
                                $projectList.find('.active').remove();
                            }
                        }

                        // 成功后小提示框提示用户操作后返回的信息
                        layer.msg( data.message, {
                            time: 1000
                        });
                    },
                    error: function( error ){
                        console.log( error );
                    }
                });


                break;
            case 'removecancel':
                $mask.trigger('click'); // 取消删除page，则直接把遮罩层关闭即可
                break;
            case 'sharecancel':         // 分享框中返回按钮事件，直接关闭遮罩层即可
                $mask.trigger('click');
                break;
            case 'memberadd':           // 成员管理添加事件
                if ( !isaddinguser ) { // 如果没有正在处理添加项目协作者的请求，则受理此次请求

                    isaddinguser = true; // 反转isaddinguser表示正在受理一个请求，控制暂时不接受新的点击的受理

                    var $memberContainer = $mask.find('.member-manage-container'),  // 成员管理容器
                        $memberForm = $memberContainer.find('.create-member-form'), // 成员管理表单
                        $username = $memberForm.find('#_username'),                 // 新增用户名输入框
                        username = $username.val(),                                 // 用户名
                        adduserUrl = window.location.protocol + "//" + window.location.host +
                                    "/ShowDoc/project/addProjectAuthor/" + projectid; // 处理添加项目协作者的服务器地址

                    // 发送添加项目协作者的请求
                    $.ajax({
                        url: adduserUrl,
                        type: 'POST',
                        data: {
                            vname: username
                        },
                        success: function( data ) {
                            var result = data.trim();

                            // 处理完后清除用户名的输入框
                            $username.val('');

                            if ( result === 'success' ) {

                                /**
                                 * 如果成功添加，则要更新成员列表中的成员，并且返回成功添加的小提示框
                                 */
                                var memberlistUrl = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc/project/queryProjectAuthor/" + projectid;  // 查询项目协作者的服务器地址

                                // 更新成员列表
                                $.ajax({
                                    url: memberlistUrl,
                                    type: 'GET',
                                    dataType: 'json',
                                    success: function( data ){
                                        var itemDOM = '',
                                            len = data.length;
                                        for( var i = 0; i < len; i++ ) {
                                            itemDOM += '<li class="member-item"><a href="javascript:void(0);" class="btn btn-member" data-event="removemember"' + ' data-user="' + data[i].pid + '">' + 
                                                data[i].vname + '</a></li>';
                                        }
                                        $mask.find('.member-manage-container')
                                                .find('.member-list').eq(0).html( itemDOM );
                                    },
                                    error: function( error ) {
                                        console.log( error );
                                    }
                                });

                                // 提示框
                                layer.msg( '已成功添加项目协作者并保存', {
                                    time: 1000
                                }, function(){
                                    isaddinguser = false;   // 控制isaddinguser反转，表示可以接受新的点击事件
                                });
                            } else if ( result === 'fail' ) {

                                /**
                                 * 如果添加失败，则给出相应的失败的原因
                                 */
                                layer.msg( '对不起！添加失败啦<br/>可能的原因：项目不存在或您没有添加协作者的权限', {
                                    time: 1500
                                }, function(){
                                    isaddinguser = false;   // 控制isaddinguser反转，表示可以接受新的点击事件
                                });
                            } else if( result === 'illegal' ) {
                                layer.msg( '请输入协作者的用户名', {
                                    time: 1500
                                }, function(){
                                    isaddinguser = false;   // 控制isaddinguser反转，表示可以接受新的点击事件
                                });
                            } else {
                                layer.msg( '该用户名不存在或者已经添加过该用户啦', {
                                    tiemout: 1500
                                }, function(){
                                    isaddinguser = false;   // 控制isaddinguser反转，表示可以接受新的点击事件
                                });
                            }
                        },
                        error: function( error ) {
                            console.log( error );
                        }
                    });
                }

                break;
            case 'memberback':          // 成员管理返回事件，直接关闭遮罩层即可
                $mask.trigger('click');
                break;
            case 'removemember':        // 删除项目协作者
                if ( !isdeletinguser ) {

                    isdeletinguser = true;              // 控制反转，防止多次点击有效

                    var vname = $target.text(),         // 协作者名字
                        pid = $target.data('user'),     // 协作者所属项目
                        deleteuserUrl = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc/project/deleteProjectAuthor/" + pid;  // 处理这个请求的服务器地址

                    // 向服务器发送请求处理删除项目协作者的操作
                    $.ajax({
                        url: deleteuserUrl,
                        type: 'POST',
                        data: {
                            vname: vname
                        },
                        success: function( data ) {
                            var result = data.trim();

                            if ( result === 'success' ) {

                                /**
                                 * 如果删除成功，则要更新成员列表中的成员，并且返回成功添加的小提示框
                                 */
                                var memberlistUrl = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc/project/queryProjectAuthor/" + projectid;  // 查询项目协作者的服务器地址

                                // 更新成员列表
                                $.ajax({
                                    url: memberlistUrl,
                                    type: 'GET',
                                    dataType: 'json',
                                    success: function( data ){
                                        var itemDOM = '',
                                            len = data.length;
                                        for( var i = 0; i < len; i++ ) {
                                            itemDOM += '<li class="member-item"><a href="javascript:void(0);" class="btn btn-member" data-event="removemember"' + ' data-user="' + data[i].pid + '">' + 
                                                data[i].vname + '</a></li>';
                                        }
                                        $mask.find('.member-manage-container')
                                                .find('.member-list').eq(0).html( itemDOM );
                                    },
                                    error: function( error ) {
                                        console.log( error );
                                    }
                                });

                                // 提示框
                                layer.msg( '已成功删除项目协作者', {
                                    time: 1000
                                }, function(){
                                    isdeletinguser = false;   // 控制isdeletinguser反转，表示可以接受新的点击事件
                                });
                            } else if ( result === 'fail' ) {

                                /**
                                 * 如果添加失败，则给出相应的失败的原因
                                 */
                                layer.msg( '对不起！删除失败啦<br/>可能的原因：项目不存在或您没有删除协作者的权限', {
                                    time: 2000
                                }, function(){
                                    isdeletinguser = false;   // 控制isdeletinguser反转，表示可以接受新的点击事件
                                });
                            } else {
                                layer.msg( '该用户名不存在或者已经删除过该用户啦', {
                                    tiemout: 1500
                                }, function(){
                                    isdeletinguser = false;   // 控制isdeletinguser反转，表示可以接受新的点击事件
                                });
                            }
                        },
                        error: function( error ) {
                            console.log( error );
                        }
                    });
                }
                break;
            case 'editdir':             // 编辑目录事件
                var $createDir = $mask.find('.create-dir-container'),   // 创建目录的容器
                    dirId = $target.data('parent'),     // 目录id
                    top = 0;                            // 记录偏移位置

                if ( $createDir.length < 1) {   

                    // 判断是否是在新建目录容器里点击的编辑事件，如果包装器中的长度小于1， 则说明没有选择到相关元素
                    // 则此时出发的编辑事件，仍然是在编辑目录容器里出发的
                    $createDir = $mask.find('.edit-dir-container');
                }

                top = $createDir.position().top;    // 记录原来新建/编辑目录的容器的top的偏移位置的值

                $createDir.fadeOut(function(){
                    $mask.load('./tpls/edit-dir.html', function( response, status, xhr ){
                        var $editContainer = $mask.find('.edit-dir-container'),     // 编辑目录的容器
                            dirInfoUrl = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc/subProject/querySubProjectByID/" + projectid + '/' + dirId,    // 获取该目录信息的地址
                            dirsInfoUrl = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc" + "/subProject/getAllProject/" + projectid,   // 获取该项目所有目录信息的地址
                            $form = $editContainer.find('.edit-dir-form'),          // 编辑目录表单
                            $dirname = $form.find('.form-item').find('#_dirname'),  // 编辑目录名输入框
                            $dirnum = $form.find('.form-item').find('#_dirnum'),    // 编辑目录排序输入框
                            $select = $form.find('.form-item').find('#_dirparname'); // 编辑目录上级目录下拉框

                        // 把要操作的目录的id保存到$editContainer容器上
                        $editContainer.attr('data-dirid', dirId);

                        // 先隐藏容器并且偏移到指定位置
                        $editContainer.css({
                            opacity: '0',
                            top: top
                        });
                        // 从后台获取目录信息的请求
                        $.ajax({
                            url: dirInfoUrl,
                            type: 'GET',
                            dataType: 'json',
                            success: function( data ) {
                                $dirname.val( data.subprojectname );    // 填充目录名
                                $dirnum.val( data.subsortid );  // 填充排序输入框
                                var result = data;

                                // 把要操作的目录的父级目录的id也要保存到$editContainer容器中，当目录发生改变时需要重新调整目录树
                                $editContainer.attr('data-dirparent', result.parentid );

                                // 定位编辑目录的top位置
                                
                                $editContainer.css('top', top);
                                // 从后台拿到某个项目下已经存在的目录，添加到下拉框中，以及显示到已有目录列表中
                                $.ajax({
                                    url: dirsInfoUrl,
                                    type: 'GET',
                                    dataType: 'json',
                                    success: function( data ){
                                            var $dirList = $editContainer.find('.exited-dir').find('.dir-list'),   // 已有目录列表ul
                                            selecthtmlDom = '',       // 将要添加到下拉框中的html
                                            dirlisthtmlDom = '',      // 将要添加到dirlist列表中的html
                                            height = $editContainer.height();       // 编辑目录容器的高度

                                        for ( var i = 0, len = data.length; i < len; i++) {
                                            
                                            if ( result.parentid === data[i].subProjectId ) {

                                                // 如果这个目录的parentid 等于返回目录中的某个目录的subProjectId则，应该在下拉框目录中自动选择上 
                                                selecthtmlDom += '<option value="' + data[i].subProjectName + '" data-level="' + 
                                                    data[i].nextLevel + '" data-parent="' + data[i].subProjectId + '" selected="selected">' + 
                                                    data[i].subProjectName + '</option>';
                                            } else if( data[i].subProjectId !== dirId ) {
                                                selecthtmlDom += '<option value="' + data[i].subProjectName + '" data-level="' + 
                                                    data[i].nextLevel + '" data-parent="' + data[i].subProjectId + '">' + 
                                                    data[i].subProjectName + '</option>';
                                            }
                                            if ( data[i].subProjectName !== '无' ) {
                                                dirlisthtmlDom += '<li class="dir-item">' + 
                                                        '<a href="javascript:void(0);" class="item-name btn" data-event="editdir"' + ' data-parent="' + data[i].subProjectId + '">' + data[i].subProjectName + '</a></li>';
                                            }
                                        }

                                        $select.html( selecthtmlDom );  // 填充内容到下拉框
                                        $dirList.html( dirlisthtmlDom );    // 填充内容到目录列表

                                        // 显示容器
                                        $editContainer.animate({
                                            opacity: '1'
                                        }, 300, function(){
                                            // 添加后要重新设置遮罩层的高度，防止被表单高度超过
                                            height = $editContainer.height() + (wh - height > 0 ? (wh - height)/2 : 20 );
                                            mh = mh > height ? mh : height;
                                            $mask.css( 'height', mh );
                                        });
                                    },
                                    error: function( error ) {
                                        console.log( error );
                                    }
                                });
                            },
                            error: function( error ) {
                                console.log( error );
                            }
                        });
                    });
                });
                break;
            case 'updatedir':            // 保存编辑的目录信息，即更新目录事件
                if ( !isupdatingdir ) { // 如果没有正在更新，则受理此次点击事件

                    isupdatingdir = true;       // 控制反转，在处理完此事件之前，不在接受新的有效的点击事件

                    var $editContainer = $mask.find('.edit-dir-container'),             // 编辑目录容器
                        $editForm = $editContainer.find('.edit-dir-form'),              // 编辑目录表单
                        $dirname = $editForm.find('.form-item').find('#_dirname'),      // 目录名输入框
                        $dirnum = $editForm.find('.form-item').find('#_dirnum'),        // 目录索引排序输入框
                        $dirparname = $editForm.find('.form-item').find('#_dirparname'), // 目录的父级目录列表
                        $select = $dirparname.find('option:selected'), // 编辑目录上级目录下拉框
                        dirname = $dirname.val(),                                           // 目录名
                        dirnum = parseInt( $dirnum.val() ),                                            // 排序
                        dirparname = $select.val(),                                        // 上级目录名
                        parent = $select.data('parent'),                                   // 父级目录id
                        level = $select.data('level'),                                     // 层级
                        subprojectid = $editContainer.data('dirid'),                       // 目录id
                        oldParent = $editContainer.data('dirparent'),                      // 目录原来父级目录的id
                        updatedirurl = window.location.protocol + "//" + window.location.host +
                                    "/ShowDoc/subProject/updateSubProject",     // 出来更新目录的url
                        params = {},                                           // 需要post的参数
                        dirsUrl = window.location.protocol + "//" + window.location.host +
                                    "/ShowDoc" + "/subProject/getAllProject/" + projectid;  // 获取所有目录信息的url地址

                    params = {
                        subprojectid: subprojectid,
                        projectid: projectid,
                        subprojectname: dirname,
                        sublevel: level,
                        parentid: parent,
                        parentName: dirparname
                    };

                    // 如果有输入索引序号，则post到后台处理
                    if ( !isNaN( dirnum ) ) {
                        params.subsortid = dirnum;
                    }

                    // 清空输入框
                    $dirname.val('');
                    $dirnum.val('');

                    // 发送更新目录的请求
                    $.ajax({
                        url: updatedirurl,
                        type: 'POST',
                        data: params,
                        dataType: 'json',
                        success: function( data ) {

                            /**
                             * 如果成功修改，则要做相应的目录树的修改调整，并且给出小提示框提示成功修改
                             */
                            if ( data.result ) {

                                // 更新编辑容器中下拉框中的信息，以及已有目录列表中的信息
                                loadDirInfo( dirsUrl );

                                layer.msg( '目录信息已更新并保存', {
                                    tiemout: 1500
                                }, function(){

                                    /**
                                     * 如果更新到的父级目录是-1，则要重新初始化一级目录
                                     */
                                    
                                    isupdatingdir = false;      // 此次事件已经处理完毕，控制反转，接受新的有效的事件

                                    if ( parent === -1 || oldParent === -1 ) {
                                        Initial();
                                    } else if( parent !== -1 && oldParent !== -1 ){

                                        /**
                                         * 如果新的父级目录id，以及旧的父级目录id都不为 -1 则需要通知这两个目录，它们的子目录已经更新啦
                                         */

                                        // 通知parent这个目录，下面有目录更新，下次要重新加载目录下的目录
                                        dirchanges.push( parent ); 

                                        // 这个目录原来的父级目录也需要重新load它下面的目录树了
                                        dirchanges.push( oldParent );  

                                    } else if ( parent === -1 && oldParent !== -1 ) {
                                        
                                        /**
                                         * 如果新的父级目录id为-1，则说明更新到一级目录了，则需要重新初始化侧边栏目录树了，
                                         * 并且，另外一个目录id不为-1，要通知它，它的目录树已经被更新啦
                                         */
                                        
                                        // 初始化一级目录
                                        Initial();

                                        // 通知更新
                                        dirchanges.push( oldParent );

                                    } else if ( parent !== -1 && oldParent === -1 ) {

                                        // 初始化一级目录
                                        Initial();

                                        // 通知更新
                                        dirchanges.push( parent );
                                    }
                                });
                            } else {

                                // 没有更新成功，则提示失败的信息
                                layer.msg( data.message, {
                                    time: 1500
                                }, function(){

                                    isupdatingdir = false;      // 此次事件已经处理完毕，控制反转，接受新的有效的事件

                                });
                            }
                        },
                        error: function( error ) {
                            console.log( error );
                        }
                    });
                }
                break;
            case 'deletedir':           // 删除目录事件

                // 如果没有正在处理删除目录的事件，则接受此次事件有效
                if ( !isdeletingdir ) {

                    isdeletingdir = true;       // 控制反转，不在接受删除目录的点击事件

                    var $editContainer = $mask.find('.edit-dir-container'),     // 编辑目录容器
                        dirId = $editContainer.data('dirid'),                   // 目录id
                        dirparent = $editContainer.data('dirparent'),           // 目录的父级目录的id
                        $editForm = $editContainer.find('.edit-dir-form'),      // 编辑目录的表单
                        $dirname = $editForm.find('.form-item').find('#_dirname'), // 编辑目录的目录名输入框
                        $dirnum = $editForm.find('.form-item').find('#_dirnum'),   // 编辑目录的目录索引输入框
                        $select = $editForm.find('.form-item').find('#_dirparname'),    // 编辑目录的下拉框
                        ddirurl = window.location.protocol + "//" + window.location.host +
                                    "/ShowDoc/subProject/deleteSubProject/" + projectid + "/" + dirId + "/" + dirparent;   // 处理删除目录的url

                    // 发送删除目录的请求
                    $.ajax({
                        url: ddirurl,
                        type: 'POST',
                        dataType: 'json',
                        success: function( data ) {

                            // 如果删除成功，则要清空编辑目录中的输入框中的值，以及要更新下拉列表中的目录，以及更新目录列表
                            // 以及还要更新侧边栏中的目录树
                            if ( data.result ) {

                                // 清空编辑目录中的输入框
                                $dirname.val('');
                                $dirnum.val('');

                                // 更新下拉列表框以及更新目录列表
                                var dirsUrl = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc" + "/subProject/getAllProject/" + projectid;  // 获取所有目录信息的url地址

                                // 更新
                                loadDirInfo( dirsUrl );


                                // 小提示框显示
                                layer.msg( '删除成功', {
                                    time: 1500
                                }, function(){

                                    isdeletingdir = false;      // 控制反转，能够继续接受删除目录的点击事件

                                    // 更新侧边栏的目录树
                                    if ( dirparent === -1 ) {

                                        // 如果删除的是项目下的一级目录，则需要重新加载侧边栏中的目录树
                                        Initial();
                                    } else if( dirparent !== -1 ){

                                        // 通知parent这个目录，下面有目录更新，下次要重新加载目录下的目录
                                        dirchanges.push( dirparent ); 
                                    }
                                    
                                });
                            } else {

                                // 提示框显示删除失败的信息
                                layer.msg( data.message, {
                                    time: 1500
                                }, function(){
                                    isdeletingdir = false;      // 控制反转，能够继续接受删除目录的点击事件
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
            type = $target.data('type'), // 根据节点中自定义的type属性，获取该次该处理的事件类型是dir事件，还是page事件
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
                        url: levelUrl + projectid + '/' + parentid,
                        type: 'GET',
                        dataType: 'json',
                        success: function( data ) {
                            var len = data.length,
                                $ul = $('<ul>'),
                                ulhtmlDom = '';

                            $ul.addClass('project-list');

                            if ( len > 0 ) {
                                for ( var i = 0; i < len; i++ ) {
                                    ulhtmlDom += '<li class="dir-item" data-tooltip="' + '目录：' + data[i].subprojectname + '">' + 
                                        '<a href="javascript:void(0);" class="btn btn-dir" data-type="dir" data-parent="' + 
                                        data[i].subprojectid + '">' + '<span class="triangle left"></span>' + data[i].subprojectname + 
                                        '</a></li>';
                                }

                                
                            }


                            // 从后台获取该目录的page页面的数据
                            $.ajax({
                                url: pageUrl + parentid + '/' + projectid,
                                type: 'GET',
                                dataType: 'json',
                                success: function( data ) {
                                    var plen = data.length;

                                    // 将返回的页面添加到ul列表中
                                    for( var i = 0; i < plen; i++ ) {
                                        ulhtmlDom += '<li class="page-item" data-tooltip="' + 'page：' + data[i].pagetitle + '">' + 
                                            '<a href="javascript:void(0);" ' + 'class="btn btn-page" data-type="page" data-page="' + 
                                            data[i].pageid + '" data-parent="' + parentid + '">' + data[i].pagetitle + '</a></li>';
                                    }

                                    // 将目录，page填充到页面，并且用下拉滑动的动画形式显示
                                    $ul.html( ulhtmlDom ).hide();
                                    $target.after( $ul );
                                    $ul.slideDown(300);

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

                        },
                        error: function( error ) {
                            console.log( error );
                        }
                    });
                }
                break;
            case 'page':
                var pageid = $target.data('page'),  // 页面id
                    pageparentid = $target.data('parent'),  // 页面所属的目录id
                    pUrl = window.location.protocol + "//" + window.location.host +
                            "/ShowDoc/page/selectPageById/" + pageid + '/' + projectid;

                pageId = pageid;        // 保存当前的pageid

                // 如果点击的不是已经在显示的页面，则切换页面
                if ( !$target.hasClass( 'active') ) {
                    $( this ).find( '.btn-page' ).removeClass('active');    // 所有page 类按钮都移除active类
                    $target.removeClass('hover').addClass('active');     // 目标page类移除hover类，并且添加active类

                    // 展示信息
                    showPageByURL( pUrl );
                }               
                break;
        }

        // 阻止事件的默认行为以及冒泡
        event.preventDefault();
        event.stopPropagation();
    });
    

    // 当鼠标移入移除侧边栏按钮时触发的事件
    $projectList.on('mouseover', function( event ){
        var $target = $( event.target ),
            type = $target.data( 'type' );

        // 如果目标target是page类型的节点，且不含 hover 类， 则添加hover 类
        if ( type === 'page' && !$target.hasClass('hover') && !$target.hasClass('active') ) {
            $target.addClass('hover');
        }
        $target.parent().addClass('hover');
    }).on('mouseout', function( event ){
        var $target = $( event.target ),
            type = $target.data( 'type' );

        // 如果目标target是page类型的节点，且含有 hover 类， 则移除hover 类
        if( type === 'page' && $target.hasClass('hover') ) {
            $target.removeClass('hover');
        }
        $target.parent().removeClass('hover');
    });

    // 将markdown内容的分享，复制，编辑，删除的点击事件冒泡到main父元素中处理
    $main.on('click', function( event ){
        var $target = $( event.target ),
            role = $target.data( 'role' );

        switch( role ) {
            case 'share': 
                shareUILoader();    // loader 分享页面UI
                break;
            case 'copy': 
                // copyUrl 带参数导航到处理页面，type 参数为copy表示是新建复制page
                var copypageUrl = window.location.protocol + "//" + window.location.host +
                        "/ShowDoc/jsp/project/editproject.jsp?projectid=" + projectid + '&projectname=' + projectName + 
                        '&check=' + projectpassword + '&pageid=' + pageId + '&type=copy'; 
                
                // 导航的处理页面
                window.location.href = copypageUrl;
                break;
            case 'edit':
                // copyUrl 带参数导航到处理页面，type 参数为edit表示是编辑page
                var copypageUrl = window.location.protocol + "//" + window.location.host +
                        "/ShowDoc/jsp/project/editproject.jsp?projectid=" + projectid + '&projectname=' + projectName + 
                        '&check=' + projectpassword + '&pageid=' + pageId + '&type=edit'; 
                
                // 导航的处理页面
                window.location.href = copypageUrl;
                break;
            case 'remove':
                // 重新计算文档的高度
                 dh = $(document).height();
                // 重新计算遮罩层的高度
                mh = wh > dh ? wh : dh; 
                $mask.css('height', mh).fadeIn(200);

                $mask.load('./tpls/remove-page.html', function( response, status, xhr){
                    var $removeContainer = $mask.find('.remove-page-container'),    // 移除页面时的提示框容器
                        height = $removeContainer.height(),                         // 提示框容器高度
                        top = (wh - height ) / 2;                                   // 将要偏移的值

                    top  = top > 0 ? top : 20;   // 如果wh(窗口高度) 比这个容器高度小，则取 top值为20

                    // 动画显示提示框
                    $removeContainer.css('top', -height)
                                    .animate({
                                        opacity: '1',
                                        top: top
                                    }, 200, function(){
                                        $removeContainer.animate({
                                            top: (top - 30 )
                                        }, 200, function(){
                                            $removeContainer.animate({
                                                top: top
                                            }, 200);
                                        });
                                    });
                });
                break;
        } 

        // 取消事件的默认行为，阻止事件冒泡
        event.preventDefault();
        event.stopPropagation();
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
                shareUILoader();    // loader 分享页面UI
                break;
            case 'member':          // 成员管理
                // 重新计算文档的高度
                dh = $(document).height();
                // 重新计算遮罩层的高度
                mh = wh > dh ? wh : dh; 
                $mask.css('height', mh).fadeIn(200);

                $mask.load('./tpls/member-manage.html', function( response, status, xhr){
                    var $memManContainer = $mask.find('.member-manage-container'),    // 移除页面时的提示框容器
                        $memberlist = $memManContainer.find('.member-list').eq(0),    // 成员列表
                        height = $memManContainer.height(),                         // 提示框容器高度
                        top = (wh - height ) / 2,                                  // 将要偏移的值
                        memberlistUrl = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc/project/queryProjectAuthor/" + projectid;  // 查询项目协作者的服务器地址

                    top  = top > 0 ? top : 20;   // 如果wh(窗口高度) 比这个容器高度小，则取 top值为20

                    // 发送请求拿到该项目的协作者成员
                    $.ajax({
                        url: memberlistUrl,
                        type: 'GET',
                        dataType: 'json',
                        success: function( data ){
                            var itemDOM = '',
                                len = data.length;
                            for( var i = 0; i < len; i++ ) {
                                itemDOM += '<li class="member-item"><a href="javascript:void(0);" class="btn btn-member" data-event="removemember"' + ' data-user="' + data[i].pid + '">' + 
                                    data[i].vname + '</a></li>';
                            }
                            $memberlist.html( itemDOM );

                            height = $memManContainer.height();                        // 提示框容器高度
                            top = (wh - height ) / 2;                                  // 将要偏移的值
                            top  = top > 0 ? top : 20;   // 如果wh(窗口高度) 比这个容器高度小，则取 top值为20
                            // 动画显示成员管理UI
                            $memManContainer.css('top', -height)
                                            .animate({
                                                opacity: '1',
                                                top: top
                                            }, 200, function(){
                                                $memManContainer.animate({
                                                    top: (top - 30 )
                                                }, 200, function(){
                                                    $memManContainer.animate({
                                                        top: top
                                                    }, 200);
                                                });
                                            });
                        },
                        error: function( error ) {
                            console.log( error );
                        }
                    });
                });
                break;
            case 'delete':         // 删除项目
                var deleteProUrl = window.location.protocol + "//" + window.location.host +
                        "/ShowDoc/project/deleteProject/" + projectid;
                $.ajax({
                    url: deleteProUrl,
                    type: 'POST',
                    dataType: 'json',
                    success: function( data ) {
                        if ( data.result ) {
                            layer.msg('已成功删除项目', {
                                time: 1000
                            }, function(){
                                // 删除项目成功后返回项目的主页
                                // 跳转到项目首页
                                window.location.href = window.location.protocol + "//" + window.location.host +
                                                        "/ShowDoc/" + "project/showProject.action";
                            });
                        } else {
                            layer.msg( data.message, {
                                time: 1500
                            }, function(){

                            });
                        }
                    },
                    error: function( error ) {
                        console.log( error );
                    }
                });
                break;
            case 'project':     // 返回项目首页
                // 跳转到项目首页
                window.location.href = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc/" + "project/showProject.action";
                break;
        }

        // 取消事件的默认行为以及组织冒泡
        event.preventDefault();
        event.stopPropagation();
    });

    // 分享页面的UI展示
    function shareUILoader() {
        // 重新计算文档的高度
         dh = $(document).height();
        // 重新计算遮罩层的高度
        mh = wh > dh ? wh : dh; 
        $mask.css('height', mh).fadeIn(200);

        $mask.load('./tpls/share-page.html', function( response, status, xhr){
            var $shareContainer = $mask.find('.page-share-container'),    // 移除页面时的提示框容器
                $link = $shareContainer.find('.link-address').find('.link'),    // 显示url的元素
                height = $shareContainer.height(),                         // 提示框容器高度
                top = (wh - height ) / 2;                                   // 将要偏移的值

            top  = top > 0 ? top : 20;   // 如果wh(窗口高度) 比这个容器高度小，则取 top值为20

            // 将当前的浏览器中的url地址填充的$link节点中
            $link.text( windowUrl );

            // 动画显示提示框
            $shareContainer.css('top', -height)
                            .animate({
                                opacity: '1',
                                top: top
                            }, 200, function(){
                                $shareContainer.animate({
                                    top: (top - 30 )
                                }, 200, function(){
                                    $shareContainer.animate({
                                        top: top
                                    }, 200);
                                });
                            });
        });
    }

    // 根据页面page的URL展示相关信息
    function showPageByURL( pgurl ) {
        // 根据页面的id拿到该页面的详细信息后展示
        $.ajax({
            url: pgurl,
            type: 'GET',
            dataType: 'json',
            success: function( data ) {
                
                /* 设置标题 */
                $contentTitle.text( data.page.pagetitle );

                // 清空markdown展示区的内容
                /*$markdownContent.html( '' );*/

                // editor.md 初始化
                if ( testEditor === '' ) {
                    testEditor = editormd('test-editormd', {
                        width: 0,
                        height: 0,
                        path: "../lib/",
                        onload: function() {
                            editormd.markdownToHTML("markdown-content", {
                                markdown: data.page.pagecontext
                            });
                        }
                    });
                } else {
                    $markdownContent.html('');
                    editormd.markdownToHTML("markdown-content", {
                        markdown: data.page.pagecontext
                    });
                }

                // 先把markdown展示区隐藏
                $showContent.fadeIn(function(){
                    // 使markdown展示容器可见，并且把这个页面的相关的信息以属性的形式保存起来
                    setTimeout(100, function(){
                        console.log('page');
                        $showContent
                                .attr({
                                    'data-page': data.page.pageid,
                                    'data-project': data.page.pageprojectid,
                                    'data-parent': data.page.pagesubprejectid
                                })
                                .slideDown(500, function(){

                                    // 是否需要重新定位侧边栏的底部位置
                                    /*var mh = $showContent.height(),
                                        navH = $navSide.height();

                                    if ( mh > navH ) {
                                        $navSide.height( mh );
                                    } else {
                                        $navSide.height( wh );
                                    }*/
                                });
                    },200);
                });
            },
            error: function( error ) {
                console.log( error );
            }
        }); 
    }

    function loadDirInfo( dirsUrl ) {
        // 从后台拿到某个项目下已经存在的目录，添加到下拉框中，以及显示到已有目录列表中
        $.ajax({
            url: dirsUrl,
            type: 'GET',
            dataType: 'json',
            success: function( data ){
                var $editContainer = $mask.find('.edit-dir-container'),                 // 编辑目录容器
                    $dirList = $editContainer.find('.exited-dir').find('.dir-list'),   // 已有目录列表ul
                    $select = $editContainer.find('.edit-dir-form').find('.form-item').find('#_dirparname'),    // 上级目录
                    selecthtmlDom = '',       // 将要添加到下拉框中的html
                    dirlisthtmlDom = '',      // 将要添加到dirlist列表中的html
                    height = $editContainer.height();       // 编辑目录容器的高度

                for ( var i = 0, len = data.length; i < len; i++) {

                    selecthtmlDom += '<option value="' + data[i].subProjectName + '" data-level="' + 
                            data[i].nextLevel + '" data-parent="' + data[i].subProjectId + '">' + 
                            data[i].subProjectName + '</option>';

                    if ( data[i].subProjectName !== '无' ) {
                        dirlisthtmlDom += '<li class="dir-item">' + 
                                '<a href="javascript:void(0);" class="item-name btn" data-event="editdir"' + ' data-parent="' + data[i].subProjectId + '">' + data[i].subProjectName + '</a></li>';
                    }
                }

                $select.html( selecthtmlDom );  // 填充内容到下拉框
                $dirList.html( dirlisthtmlDom );    // 填充内容到目录列表

                // 添加后要重新设置遮罩层的高度，防止被表单高度超过
                height = $editContainer.height() + (wh - height > 0 ? wh - height : 20 );
                mh = mh > height ? mh : height;
                $mask.css( 'height', mh );
            },
            error: function( error ) {
                console.log( error );
            }
        });
    }
});