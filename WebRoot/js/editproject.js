$(function(){
	var editor,    // 初始markdown编辑器
		$body = $('body'),    // body
		$contrainer = $body.find('.contrainer'),  // container容器元素
		$form = $contrainer.find('#project-form'),    // 表单
		$mdItmes = $form.find('.form-markdown-items'),    // markdown内容的相关来源
		$mdItmesBtn = $mdItmes.find('.form-markdown-operations').find('.btn'),    // 导入markdown内容按钮
        $formOpera = $form.find('.form-operations-item'),                           // 表单操作容器
        $formSubmitBtn = $formOpera.find('.btn-submit'),                            // 确定按钮即提交表单
        $formCancelBtn = $formOpera.find('.btn-cancel'),                           // 取消按钮即不创建页面，返回
        windowUrl = window.location,                                // 浏览器当前的地址栏中的url
        urlParams = decodeURI( windowUrl.search ),                  // 浏览器地址栏中的url中带的参数
        projectid = '',                                             // 项目ID
        projectpassword = '',                                       // 项目访问的密码
        projectname = '',                                           // 项目的名字
        type = '',                                                  // 记录要操作的类型
        pageid = '',                                                // 如果是复制或者编辑操作，还有记录要操作的pageid
        dirurl = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc" + "/subProject/getAllProject/",  // 获取所有目录的url
        iscreatingpage = false,                                      // 控制是否正在创建页面，防止多次点击
        pageinfourl = window.location.protocol + "//" + window.location.host +
                                "/ShowDoc/page/selectPageById/", // 获取页面相应信息的url
        markdownInitialized = false;                            // markdown编辑区是否完成初始化


    var temps = urlParams.replace(/\?\s*/g, '').split('&');     // 把参数分割成数组

    // 将参数的值解析保存到相应的变量中
    for( var i = 0, len = temps.length; i < len; i ++) {
        var temp = temps[i].split('=');
        if ( temp[0] === 'projectid' ){ // 记录projectid
            projectid = temp[1] || '';
        }
        if ( temp[0] === 'projectname' ){ // 记录projectname
            projectName = temp[1] || '';
        }
        if ( temp[0] === 'check' ) {    // 记录project的密码
            projectpassword = temp[1] || '';
        }
        if ( temp[0] === 'type' ) {     // 记录操作的类型
            type = temp[1] || '';
        }
        if ( temp[0] === 'pageid' ) {   // 记录pageid
            pageid = temp[1] || '';
        }
    }

    // 如果是复制或者编辑操作，则要根据相应的pageid拿到相应的page信息
    if ( type === 'copy' || type === 'edit' ) {
        $.ajax({
            url: pageinfourl + pageid + '/' + projectid,
            type: 'GET',
            dataType: 'json',
            success: function( data ) {
                var pageinfo = data;

                // 如果成功获取页面的信息，则继续后续处理，否则不继续进行后续处理
                if ( pageinfo.result ) {
                    // 从后台拿到某个项目下已经存在的目录，添加到下拉框中，以及显示到已有目录列表中
                    $.ajax({
                        url: dirurl + projectid,
                        type: 'GET',
                        dataType: 'json',
                        success: function( data ){
                            
                            var $select = $form.find('.form-items').find('#_pageparent'),  // 下拉框
                                selecthtmlDom = '';       // 将要添加到下拉框中的html

                            // 填充表单
                            // 填充标题输入框
                            if ( type === 'copy' ) {

                                /**
                                 * 如果是copy，则在标题处注明是副本
                                 */
                                $form.find('.form-items-title').find('#_pagetitle').val( pageinfo.page.pagetitle + '（副本）' );
                            } else {

                                // 否则直接填充标题
                                
                                $form.find('.form-items-title').find('#_pagetitle').val( pageinfo.page.pagetitle );
                            }

                            // 填充索引输入框
                            $form.find('.form-items-sort').find('#_pagesortid').val( pageinfo.page.pagesortid );

                            // 填充下拉框输入框
                            for ( var i = 0, len = data.length; i < len; i++) {

                                // 如果是要复制的pageid的父目录，则相应的默认选中
                                if ( data[i].subProjectId === pageinfo.page.pagesubprejectid ) {
                                    selecthtmlDom += '<option value="' + data[i].subProjectId + '" selected="selected">' + 
                                        data[i].subProjectName + '</option>';
                                } else {
                                    selecthtmlDom += '<option value="' + data[i].subProjectId + '">' + 
                                        data[i].subProjectName + '</option>';
                                }
                            }

                            $select.html( selecthtmlDom );  // 填充内容到下拉框

                            // 填充markdown编辑块中的内容
                            // 把页面的内容通过markdown展示
                            // editor.md 初始化
                            editor = editormd("markdown-editormd", {
                                markdown: pageinfo.page.pagecontext,
                                height          : 720,        
                                path            : "../lib/",
                                htmlDecode      : "style,script,iframe",
                                placeholder     : "用mardown语法编辑你的项目,右边可以实时预览",
                                onload: function(){
                                     markdownInitialized = true;     // markdown编辑区已经完成初始化
                                } 
                            });
                        },
                        error: function( error ) {
                            // 若返回的是error，则是登录信息已经过了有效期，需要重新登录
                            layer.msg('登录信息失效，请返回首页重新登录',{
                                time: 2000
                            }, function(){
                                window.location.href = window.location.protocol + "//" + window.location.host + "/ShowDoc";
                            });
                        }
                    });
                } else {
                    console.log( data );
                }
            },
            error: function( error ) {
                console.log( error );
            }
        });

    } else {
        // 否则，则是新建操作，则直接拿到相应的目录信息即可
        
        // editor.md 初始化
        editor = editormd("markdown-editormd", {
            height          : 720,        
            path            : "../lib/",
            htmlDecode      : "style,script,iframe",
            placeholder     : "用mardown语法编辑你的项目,右边可以实时预览",
            onload: function(){
                markdownInitialized = true;     // markdown编辑区已经完成初始化
            } 
        });
        // 从后台拿到某个项目下已经存在的目录，添加到下拉框中，以及显示到已有目录列表中
        $.ajax({
            url: dirurl + projectid,
            type: 'GET',
            dataType: 'json',
            success: function( data ){
                var $select = $form.find('.form-items').find('#_pageparent'),  // 下拉框
                    selecthtmlDom = '';       // 将要添加到下拉框中的html

                for ( var i = 0, len = data.length; i < len; i++) {
                    selecthtmlDom += '<option value="' + data[i].subProjectId + '">' + 
                            data[i].subProjectName + '</option>';
                }

                $select.html( selecthtmlDom );  // 填充内容到下拉框
            },
            error: function( error ) {
                // 若返回的是error，则是登录信息已经过了有效期，需要重新登录
                layer.msg('登录信息失效，请返回首页重新登录',{
                    time: 2000
                }, function(){
                    window.location.href = window.location.protocol + "//" + window.location.host + "/ShowDoc";
                });
            }
        });
    }

    // markdown内容来源中的按钮的点击事件
    $mdItmesBtn.on('click', function(){
    	var data = $(this).data('role');   // 根据 自定义的属性，确定那个按钮的点击事件

        switch( data ) {
            case 'API': 
                if ( markdownInitialized ) {

                    /**
                     * 如果mardown编辑已经完成初始化，则受理此次点击事件，否则不受理
                     */
                    $.get('./tpls/api.md', function( md ){
                        editor.clear();
                        editor.appendMarkdown( md );
                    });
                } else {
                    // 当markdown还没有完成初始化时，给用户一个小提示告知还没完成初始化
                    layer.msg( 'markdown编辑区还未能完成初始化，请您在给它一点时间啦!',{
                        time: 1500
                    });
                }
                break;
            case 'data':
                if ( markdownInitialized ) {
                    $.get('./tpls/data.md', function( md ){
                        editor.clear();
                        editor.appendMarkdown( md );    // 把数据字典模板导入markdown编辑器中
                    });
                } else {
                    // 当markdown还没有完成初始化时，给用户一个小提示告知还没完成初始化
                    layer.msg( 'markdown编辑区还未能完成初始化，请您在给它一点时间啦!',{
                        time: 1500
                    });
                }
                break;
            case 'http': 
                // 在线测试 http 请求
                /*console.log( 'http' );*/
                break;
        }
    });

    // 点击确定按钮的事件，即把表单内容post到后台，创建一个页面
    $formSubmitBtn.on('click', function( event ){
        var $pagetitle = $form.find('.form-items-title').find('#_pagetitle'),   // 页面标题输入框
            $pagesortid = $form.find('.form-items-sort').find('#_pagesortid'),  // 页面索引输入框
            $pageparent = $form.find('#_pageparent'),                           // 页面父目录下拉框
            pagetitle = $pagetitle.val(),                                       // 页面标题
            pagesortid = parseInt($pagesortid.val()),                                     // 页面索引
            pagesubprejectid = $pageparent.val(),                               // 页面父目录id
            pagecontext = editor.getMarkdown(),                                 // 页面markdown内容
            pageUrl = window.location.protocol + "//" + window.location.host + "/ShowDoc/page/addPage",  // 创建页面处理url
            updateUrl = window.location.protocol + "//" + window.location.host + 
                        "/ShowDoc/page/updatePage", // 更新页面处理url
            params = {
                pageprojectid: projectid,
                pagesubprejectid: pagesubprejectid,
                pagetitle: pagetitle  
            };  // 需要post上去的表单字段

        // 如果排序输入框中是数字，则添加到要发送的字段中去
        if ( !isNaN( pagesortid ) ) {
            params.pagesortid = pagesortid;
        }

        // 如果项目有访问密码，则将密码添加到要发送的字段中去
        if ( projectpassword !== '' ) {
            params.pageprojectpassword = projectpassword;
        }

        // 如果markdown 内容不为空，则把markdown内容post到服务器上保存
        if( pagecontext !== '' ) {
            params.pagecontext = pagecontext;
        }

        if ( !iscreatingpage ) {

            /**
             * 如果没有正在创建或者更新page，则尝试去创建或者更新一个page，否则不进行处理，防止多次点击
             */

            if ( type === 'edit' ) {

                // 如果是编辑，则是更新相应的page， 则要带上相应的pageid
                params.pageid = pageid;

                // 更新一个page的请求
                $.ajax({
                    url: updateUrl,
                    type: 'POST',
                    dataType: 'json',
                    data: params,
                    success: function( data ) {
                        iscreatingdir = false;      // 置为 false 让下次点击有效
                        var sucFlag = data.result;
                        if ( sucFlag ) {
                            /**
                             * 如果创建页面成功，则弹出一个小弹窗显示成功创建，提示框3秒后自动关闭
                             */
                            layer.msg('已成功更新页面并保存',{
                                time: 1000
                            }, function(){
                                // 创建页面成功后返回用户该项目的主页面
                                var pUrl = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc/jsp/project/showproject.jsp?id=" + projectid + 
                                        '&projectname=' + projectName + '&check=' + projectpassword + '&pageid=' + data.page.pageid;

                                // 带参数进行跳转到展示用户项目的页面
                                 window.location.href= pUrl;
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

            } else {

                // 如果不是更新，则是创建或者复制，复制即是创建一个新的page，内容是原来page的副本
                
                // 创建一个page的请求
                $.ajax({
                    url: pageUrl,
                    type: 'POST',
                    dataType: 'json',
                    data: params,
                    success: function( data ) {
                        iscreatingdir = false;      // 置为 false 让下次点击有效
                        var sucFlag = data.result;
                        if ( sucFlag ) {
                            /**
                             * 如果创建页面成功，则弹出一个小弹窗显示成功创建，提示框3秒后自动关闭
                             */
                            layer.msg('已成功创建页面并保存',{
                                time: 1000
                            }, function(){
                                // 创建页面成功后返回用户该项目的主页面
                                var pUrl = window.location.protocol + "//" + window.location.host +
                                        "/ShowDoc/jsp/project/showproject.jsp?id=" + projectid + 
                                        '&projectname=' + projectName + '&check=' + projectpassword + '&pageid=' + data.page.pageid;

                                // 带参数进行跳转到展示用户项目的页面
                                 window.location.href= pUrl;
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
        }

        // 取消事件的默认行为并阻止冒泡
        event.preventDefault();
        event.stopPropagation();
    });

    // 点击取消按钮的事件，即不创建页面，返回到用户项目展示页
    $formCancelBtn.on('click', function(){
        var showUrl = window.location.protocol + "//" + window.location.host +
                "/ShowDoc/jsp/project/showproject.jsp?id=" + projectid + 
                '&projectname=' + projectName + '&check=' + projectpassword;

        // 带参数进行跳转到展示用户项目的页面
        window.location.href= showUrl;

        // 阻止事件的默认行为以及冒泡
        event.preventDefault();
        event.stopPropagation();
    });
});