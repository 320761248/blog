function zan(e){

    var obj=$(e)

    if(obj.prev().text()=="0"){

        //添加赞
        var comment_id=obj.prev().prev().text()

        add_zan(obj,parseInt(comment_id))

    }else{
        //取消赞

        var comment_id=obj.prev().prev().text()

        delete_zan(obj,parseInt(comment_id))
    }


}
function  load_zan() {
    $(".zan_icon").each(function () {
        $this=$(this)
        var obj=$(this).prev()
        var comment_id=obj.prev().text()


        //获取赞对应的评论id
        $.ajax({
            type: 'POST',
            async: false,
            url: '../select_is_zan',
            dataType: 'json',
            data: {
                comment:parseInt(comment_id)


            },
            success: function (data) {


                if(data.code==3){
                    //未登录，不用渲染
                }
                else if(data.code%2==0){


                    obj.html(data.data.toString())
                    if(data.data>0)
                    {
                        alert("渲染")
                        $this.removeClass("span_word")
                        $this.addClass("yz_span_word")
                    }


                }





            },
            error: function () {
                alert("加载异常！");
            }
        });

    })
}
//发表
function publish_reply(e,answere_id,comment_id) {
    alert(answere_id)
    alert(comment_id)
    //获取回复内容
    var reply_content=$(e).prev().prev().val()
    //获取answere_id


    $.ajax({
        type: 'POST',
        url: '../insertReply',
        dataType: 'json',
        data: {
            article:result.id,
            comment_content:reply_content,
            parent_id:parseInt(comment_id),
            answere_id:parseInt(answere_id)


        },
        success: function (data) {

            //f返回的数据就是之前插入的那条
            if(data.code==3){
                alert("请先登陆")
            }
            else if(data.code%2==0){
                var reply=data.data
                var insert_reply_html='                                    <!--评论内评论-->\n' +
                    '                                    <ul class="media-list " >\n' +
                    '                                        <li class="media">\n' +
                    '                                            <div class="media-left">\n' +
                    '                                                <a href="#">\n' +
                    '                                                    <img class="media-object comment_user_head" src="../img/head.jpg">\n' +
                    '                                                </a>\n' +
                    '                                            </div>\n' +
                    '                                            <div class="media-body">\n' +
                    '                                                <h4 class="media-heading"></h4><span class="conment_user_name">'+reply.responser.user_name+'</span>&nbsp;回复&nbsp;<span class="conment_user_name">'+reply.answerer.user_name+'</span></h4>\n' +
                    '                                                <p class="" >'+reply.comment_content+'</p>\n' +
                    '                                                <div class="row zan_response" >\n' +
                    '                                                    <div class="col-md-2 col-sm-2 col-xs-2 " style="width:50px;margin-right: 0px;padding-right: 0px">\n' +
                    '                                                       <span style="display: none">'+reply.id+'</span>\n' +
                    '                                                       <!--是否已赞-->\n' +
                    '                                                       <span style="display: none">0</span>\n' +
                    '                                                        <span class="glyphicon glyphicon-thumbs-up zan_icon span_word" aria-hidden="true" onclick="zan(this)"></span>\n' +
                    reply.zan_num+'</div>\n' +
                    '                                                    <div class="col-md-2 col-sm-2 col-xs-2 " >\n' +
                    '                                                        <!--answere_id-->\n' +
                    '                                                        <span style="display: none">'+reply.responser.user_id+'</span>\n' +
                    '                                                        <!--answere_name-->\n' +
                    '                                                        <span style="display: none">'+reply.responser.user_name+'</span>\n' +

                    '                                                        <span  class="span_word reply_j" onclick="reply(this)">回复</span>\n' +
                    '                                                    </div>\n' +
                    '                                                </div>\n' +
                    '                                            </div>\n' +
                    '                                        </li>\n' +
                    '                                    </ul>\n'





                $("#reply_of_comment_item_"+comment_id).prepend(insert_reply_html)
                alert("插入成功")

                //js添加
            }else{
                alert("插入为空")
            }





        },
        error: function () {
            alert("插入失败！");
        }
    });





}
//弹出编辑框
function reply(e,reply_id,comment_id) {
    //获取回答用户名
    var answerer=$(e).prev().text()
    alert(reply_id)
    alert(comment_id)

    var answere_id=$(e).prev().prev().text()

    //#是要获取到comment_id
    //获取当前评论序号
    var html=''
    //当reply_id=0时，就是reply,否则就是comment
    if(reply_id==0){
        var reply_box='<div id="reply_box" >'+
            '                                        <textarea class="reply_content" placeholder="回复'+answerer+'"></textarea>\n' +
            '                                        <span style="display: none">'+answere_id+'</span>\n' +

            '                                        <button type="button" class="btn btn-default reply_publish_bt"  onclick="publish_reply(this,'+answere_id+','+comment_id+')">发表评论</button>\n' +
            '                                        <button type="button" class="btn btn-default"  >表情</button>\n' +
            '\n' +
            '                                    </div>'
        html+=reply_box
    }else{
        var reply_box='<div id="reply_box" >'+
            '                                        <textarea class="reply_content" placeholder="回复'+answerer+'"></textarea>\n' +
            '                                        <span style="display: none">'+answere_id+'</span>\n' +

            '                                        <button type="button" class="btn btn-default reply_publish_bt"  onclick="publish_reply(this,'+answere_id+','+comment_id+')">发表评论</button>\n' +
            '                                        <button type="button" class="btn btn-default"  >表情</button>\n' +
            '\n' +
            '                                    </div>'
        html+=reply_box
    }




    //有两种parent按钮，不能直接这么弄
    $("#comment_item_"+comment_id).html(html)
    
}
function nan_nv(sex) {
    if(sex==1){
        //男
        return '#icon-xingbie-nan'
    }else{
        //女
        return '#icon-xingbienv'
    }


}


function zan_no_zan(num) {
    if(num>0){
        return "yz_span_word"
    }
    return "span_word"

}
function add_zan(obj,comment_id) {
    $.ajax({
        type: 'POST',
        url: '../add_zan',
        dataType: 'json',
        data: {
            comment:comment_id


        },
        success: function (data) {
            if(data.code==3){
                alert("请先登陆")
            }
            else if(data.code%2==0){
                alert(data.msg)
                //控制发光
                obj.prev().text("1")
                obj.addClass("yz_span_word")
                obj.removeClass("span_word")
                //点赞加1
                new_zan_num=parseInt(obj.next().text())+1
                obj.next().text(new_zan_num)
            }else{
                alert("点赞失败")
            }





        },
        error: function () {
            alert("点赞异常！");
        }
    });
}
function delete_zan(obj,comment_id) {
    $.ajax({
        type: 'POST',
        url: '../delete_zan',
        dataType: 'json',
        data: {
            comment:comment_id


        },
        success: function (data) {
            if(data.code==3){
                alert("请先登陆")
            }
            else if(data.code%2==0){
                alert(data.msg)
                //控制发光
                obj.prev().text("0")
                obj.addClass("span_word")
                obj.removeClass("yz_span_word")
                //点赞加1
                new_zan_num=parseInt(obj.next().text())-1
                obj.next().text(new_zan_num)
            }else{
                alert("取消点赞失败")
            }





        },
        error: function () {
            alert("取消点赞异常！");
        }
    });
}
function insertReply(){

}

$(function () {

    $("#cur_user_head").attr("src","../img/head.jpg")
    $(document).on('click', '#reply_t', function(){
        //弹出编辑框
        $("#reply_box").css("display","block")

    });

    $("#my_article").text(result.article_content)

    $("#article_time").text(result.pulish_date)

    $("#article_look").text(result.look_number)


    testEditormdView2 = editormd.markdownToHTML("article-view", {
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        emoji           : true,
        taskList        : true,
        tex             : true,  // 默认不解析
        flowChart       : true,  // 默认不解析
        sequenceDiagram : true,  // 默认不解析
    });


    // 把内容中的目录移到左边


    my_toc=$(".markdown-toc")
    if(my_toc.length){

        toc_html=my_toc.get(0).outerHTML
        my_toc.remove()
        $("#article_dire").html(toc_html)
    }else{
        $("#article_dire").html("目录为空")
    }




    //加载上一篇和下一篇
    $.ajax({
        type: 'POST',
        url: '../loadlastAndNext',
        dataType: 'json',
        data: {
            cur_id:result.id
        },
        success: function (data) {


            if(data["lastArticle"]==null){
                $("#last_next").append('<div class="col-md-6 col-xs-6 col-sm-6 magrin_small ">没有上一篇了</div>')
            }else{
                $("#last_next").append('<div class="col-md-6 col-xs-6 col-sm-6 magrin_small"><a href="./'+data["lastArticle"].id+'">上一篇:<span>'+data["lastArticle"].article_title+'</span></a></div>')
            }

            if(data["nextArticle"]==null){
                $("#last_next").append('<div class="col-md-6 col-xs-6 col-sm-6 magrin_small " style="text-align: right">没有下一篇了</div>')
            }else{
                $("#last_next").append('<div class="col-md-6 col-xs-6 col-sm-6 magrin_small" style="text-align: right"><a href="./'+data["nextArticle"].id+'">下一篇:<span>'+data["nextArticle"].article_title+'</span></a></div>')
            }





        },
        error: function () {
            alert("获取上一篇，下一篇失败！");
        }
    });


    //加载评论
    $.ajax({
        type: 'POST',
        url: '../loadComment',
        dataType: 'json',
        data: {
            article_id:result.id

        },
        success: function (data) {

            if(data.code%2!=0){
                alert("还没有评论")
            }else{
                var comment_html=""

                for(var i=0;i<data.data.length;i++){


                    var comment=data.data[i]
                    var end_html='                                 </div>\n'  +
                        '                                                   <div class="no_tag" id="comment_item_'+comment.id+'">\n'  +
                        '                                                   </div>\n'  +
                        '                                            <hr class="col-md-12">\n' +
                        '                                          </li>'

                    //判断性别


                    var comment_item='<li class="media comment_item">\n' +
                        '                                <div class="media-left">\n' +
                        '                                    <a href="#">\n' +
                        '                                        <img class="media-object comment_user_head" src="../img/head.jpg" >\n' +
                        '                                    </a>\n' +
                        '                                </div>\n' +
                        '                                <div class="media-body">\n' +
                        '                                    <div>\n' +
                        '                                        <h4 class="media-heading"><span class="conment_user_name">'+comment.responser.user_name+'</span>\n' +
                        '                                            <!--评论id-->\n' +
                        '                                            <span class="get_comment_id" style="display:none ">'+comment.id+'</span>\n' +
                        '                                            <svg class="icon" aria-hidden="true">\n' +
                        '                                            <use xlink:href="'+nan_nv(comment.responser.sex)+'"></use>\n' +
                        '                                            </svg>\n' +
                        '                                            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# '+(i+1)+'楼</span>\n' +
                        '                                            </h4>\n' +
                        '                                    </div>\n' +
                        '                                    <p class="" >'+comment.comment_content+'</p>\n' +
                        '                                    <div class="row zan_response" >\n' +
                        '                                        <div class="col-md-2 col-sm-2 col-xs-2 " style="width:50px;margin-right: 0px;padding-right: 0px">\n' +
                        '                                            <!--comment_id-->\n' +
                        '                                            <span style="display: none">'+comment.id+'</span>\n' +
                        '                                            <!--是否已赞-->\n' +
                        '                                            <span style="display: none">0</span>\n' +
                        '                                            <span class="glyphicon glyphicon-thumbs-up zan_icon span_word" aria-hidden="true" onclick="zan(this)"></span>\n' +
                        '                                            <span>'+comment.zan_num+'</span></div>\n' +
                        '                                        <div class="col-md-2 col-sm-2 col-xs-2" style="width:100px;margin-right: 0px;padding-right: 0px">\n' +
                        '                                            <!--answere_id-->\n' +
                        '                                            <span style="display: none">'+comment.responser.user_id+'</span>\n' +
                        '                                            <!--answere_name-->\n' +
                        '                                            <span style="display: none">'+comment.responser.user_name+'</span>\n' +
                        '                                            <span  class="reply_t span_word" onclick="reply(this,0,'+comment.id+')">回复</span>\n' +
                        '                                        </div>\n' +
                        '                                    </div>\n'


                    var replys_html='<div class="second_reply_insert_address" id="reply_of_comment_item_'+comment.id+'">'

                    for(var j=0;j<comment.replys.length;j++){


                        var reply=comment.replys[j]
                        var reply_html= '                                    <!--评论内评论-->\n' +
                        '                                    <ul class="media-list " >\n' +
                        '                                        <li class="media">\n' +
                        '                                            <div class="media-left">\n' +
                        '                                                <a href="#">\n' +
                        '                                                    <img class="media-object comment_user_head" src="../img/head.jpg">\n' +
                        '                                                </a>\n' +
                        '                                            </div>\n' +
                        '                                            <div class="media-body">\n' +
                        '                                                <h4 class="media-heading"></h4><span class="conment_user_name">'+reply.responser.user_name+'</span>&nbsp;回复&nbsp;<span class="conment_user_name">'+reply.answerer.user_name+'</span></h4>\n' +
                        '                                                <p class="" >'+reply.comment_content+'</p>\n' +
                        '                                                <div class="row zan_response" >\n' +
                        '                                                    <div class="col-md-2 col-sm-2 col-xs-2 " style="width:50px;margin-right: 0px;padding-right: 0px">\n' +
                        '                                                       <span style="display: none">'+reply.id+'</span>\n' +
                        '                                                       <!--是否已赞-->\n' +
                        '                                                       <span style="display: none">0</span>\n' +
                        '                                                        <span class="glyphicon glyphicon-thumbs-up zan_icon span_word" aria-hidden="true" onclick="zan(this)"></span>\n' +
                                                                                reply.zan_num+'</div>\n' +
                        '                                                    <div class="col-md-2 col-sm-2 col-xs-2 " >\n' +
                        '                                                        <!--answere_id-->\n' +
                        '                                                        <span style="display: none">'+reply.responser.user_id+'</span>\n' +
                        '                                                        <!--answere_name-->\n' +
                        '                                                        <span style="display: none">'+reply.responser.user_name+'</span>\n' +
                        '                                                        <span  class="span_word reply_j" onclick="reply(this,'+reply.id+','+comment.id+')">回复</span>\n' +
                        '                                                    </div>\n' +
                        '                                                </div>\n' +
                        '                                            </div>\n' +
                        '                                        </li>\n' +
                        '                                    </ul>\n'
                        replys_html=replys_html+reply_html
                    }
                    replys_html=replys_html+'</div>'
                    comment_item=comment_item+replys_html
                    comment_item=comment_item+end_html
                    comment_html=comment_html+comment_item

                    
                }


                //吧html加载进去
                $(".all_comment_box").html(comment_html)
                
                
                load_zan()



            }





        },
        error: function () {
            alert("评论加载失败");
        }
    });

    $("#publish_comment").click(function () {
        //发表评论
        var comment_content=$("#comment_content").val()
        $.ajax({
            type: 'POST',
            url: '../insertComment',
            dataType: 'json',
            data: {
                article:result.id,
                comment_content:comment_content

            },
            success: function (data) {

                if(data.code==3){
                    alert("请先登陆")
                }else if(data.code%2!=0){
                    alert("插入失败")
                 }else{
                    //添加评论
                    var comment=data.data
                    var comment_item='<li class="media comment_item">\n' +
                        '                                <div class="media-left">\n' +
                        '                                    <a href="#">\n' +
                        '                                        <img class="media-object comment_user_head" src="../img/head.jpg" >\n' +
                        '                                    </a>\n' +
                        '                                </div>\n' +
                        '                                <div class="media-body">\n' +
                        '                                    <div>\n' +
                        '                                        <h4 class="media-heading"><span class="conment_user_name">'+comment.responser.user_name+'</span>\n' +
                        '                                            <!--评论id-->\n' +
                        '                                            <span class="get_comment_id" style="display:none ">'+comment.id+'</span>\n' +
                        '                                            <svg class="icon" aria-hidden="true">\n' +
                        '                                            <use xlink:href="'+nan_nv(comment.responser.sex)+'"></use>\n' +
                        '                                            </svg>\n' +
                        // '                                            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# '+(i+1)+'楼</span>\n' +
                        '                                            </h4>\n' +
                        '                                    </div>\n' +
                        '                                    <p class="" >'+comment.comment_content+'</p>\n' +
                        '                                    <div class="row zan_response" >\n' +
                        '                                        <div class="col-md-2 col-sm-2 col-xs-2 " style="width:50px;margin-right: 0px;padding-right: 0px">\n' +
                        '                                            <!--comment_id-->\n' +
                        '                                            <span style="display: none">'+comment.id+'</span>\n' +
                        '                                            <!--是否已赞-->\n' +
                        '                                            <span style="display: none">0</span>\n' +
                        '                                            <span class="glyphicon glyphicon-thumbs-up zan_icon span_word" aria-hidden="true" onclick="zan(this)"></span>\n' +
                        '                                            <span>'+comment.zan_num+'</span></div>\n' +
                        '                                        <div class="col-md-2 col-sm-2 col-xs-2" style="width:100px;margin-right: 0px;padding-right: 0px">\n' +
                        '                                            <!--answere_id-->\n' +
                        '                                            <span style="display: none">'+comment.responser.user_id+'</span>\n' +
                        '                                            <!--answere_name-->\n' +
                        '                                            <span style="display: none">'+comment.responser.user_name+'</span>\n' +
                        '                                            <span  class="reply_t span_word" onclick="reply(this,0,'+comment.id+')">回复</span>\n' +
                        '                                        </div>\n' +
                        '                                    </div><div class="second_reply_insert_address" id="reply_of_comment_item_'+comment.id+'"></div>'

                    var end_html='                                 </div>\n'  +
                        '                                                   <div class="no_tag" id="comment_item_'+comment.id+'">\n'  +
                        '                                                   </div>\n'  +
                        '                                            <hr class="col-md-12">\n' +
                        '                                          </li>'


                    //加到顶部
                    $(".all_comment_box").prepend(comment_item+end_html)

                }





            },
            error: function () {
                alert("发表评论失败");
            }
        });
    })
    
    


})

