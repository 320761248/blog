
function load_page(total_count,cur_page,page_count){

        var setTotalCount = total_count;
        $('#box').paging({
            initPageNo: cur_page, // 初始页码
            totalPages: page_count, //总页数
            // totalCount: '合计' + setTotalCount + '条数据', // 条目总数
            slideSpeed: 600, // 缓动速度。单位毫秒
            jump: true, //是否支持跳转
            callback: function (page) { // 回调函数
                if(page!=cur_page){
                    load_article(page,6)
                }

            }
        })

}
function load_article(pageNo,pageSize){
    //加载博客


    $.ajax({
        type: 'POST',
        url: '/loadArticle',
        dataType: 'json',
        data: {
            pageSize:pageSize,
            pageNo:pageNo
        },
        success: function (data) {

            var blogs_html=""
            var object=data.data
            var blog_list=object.list
            for(var i=0;i<blog_list.length;i++){
                var blog_item='<div class="blog_item" >\n' +
                    '                                <div style="display: none" >'+blog_list[i].id+'</div>\n' +
                    '                                <h4 class="blog_title"  >'+blog_list[i].article_title+'</h4>\n' +
                    '                                <p class="blog_abstract" >机器学习为什么不活了机器学习为什么不活了机器学习为什么不活了z机器学习为什么不活了机器学习为什么不活了v</p>\n' +
                    '                                <!--第二行 图标啥的-->\n' +
                    '                                <div style="height: 20px">\n' +
                    '                                    <div class="blog_category" style="" >\n' +
                    '                                        <span class="glyphicon glyphicon-folder-open" aria-hidden="true" ></span>\n' +
                    '                                        <span >'+blog_list[i].article_category.category_name+'</span>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="blog_label" >\n' +
                    '                                        <span class="glyphicon glyphicon-tag" aria-hidden="true"></span>\n' +
                    '                                        <span >'+blog_list[i].article_label.label_name+'</span>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="blog_time"  >\n' +
                    '                                        <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>\n' +
                    '                                        <span>'+blog_list[i].pulish_date+'</span>\n' +
                    '\n' +
                    '                                    </div>\n' +

                    '                                    <!--靠右-->\n' +
                    '                                    <div class="blog_read_num num_col pull-right hidden-xs"  >\n' +
                    '                                        <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>\n' +
                    '                                        <span >'+blog_list[i].look_number+'</span>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="blog_zan_num num_col pull-right hidden-xs"  >\n' +
                    '                                        <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>\n' +
                    '                                        <span >'+blog_list[i].zan_number+'</span>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="blog_say_num num_col pull-right hidden-xs"   >\n' +
                    '                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>\n' +
                    '                                        <span >'+blog_list[i].comment_number+'</span>\n' +
                    '                                    </div>\n' +
                    '\n' +
                    '                                </div>\n' +
                    '                                <hr style="color: grey">\n' +
                    '                            </div>'

                blogs_html=blogs_html+blog_item;
            }

            $(".blog_list").html(blogs_html)
            //加载分页
            load_page(object.total,object.pageNum,object.pages)
            //滚动到页面顶部
            window.scrollTo(0,0)
        },
        error: function () {
            alert("获得文章信息失败！");
        }
    });
}
$(function () {

    //主页面内容的加载


    load_article(1,10)

    //加载分类
    $.ajax({
        type: 'POST',
        url: '/loadCategory',
        dataType: 'json',
        data:{
            pageSize:10,
            pageNo:1
        },
        success: function (data) {
            //填充博客
            var category_html=""
            var category_list=data.data
            for(var i=0;i<category_list.length;i++){
                var category_item='<button type="button" class="list-group-item category_jump" style="border:none;padding:0px">\n' +
                    '                                            <span>'+category_list[i].category_name+'</span>\n' +
                    '                                            <span class="badge">14</span>\n' +
                    '                                        </button>'
                category_html=category_html+category_item
            }
            $("#category_list").html(category_html)
            //填充分页
        },
        error: function () {
            alert("获得文章信息失败！");
        }
    });


    //加载标签
    $.ajax({
        type: 'POST',
        url: '/loadLabel',
        dataType: 'json',
        data:{
            pageSize:10,
            pageNo:1
        },
        success: function (data) {
            var label_html=""
            var label_list=data.data
            for(var i=0;i<label_list.length;i++){
                var label_item='<div style="float: left; margin: 5px 5px 5px 5px " >\n' +
                    '                                    <span class="label label-info label_jump" >'+label_list[i].label_name+'</span>\n' +
                    '                                    <span  style="display: none"></span>\n' +
                    '                                </div>'
                label_html=label_html+label_item
            }
            $("#label_list").html(label_html)
        },
        error: function () {
            alert("获得文章信息失败！");
        }
    });

    //加载
    $(".blog_title").mouseover(function () {
        $(this).css("text-decoration","underline")
    })
    $(".blog_title").mouseout(function () {
        $(this).css("text-decoration","none")
    })
    $(".blog_title").click(function () {
        var id=$(this).prev().text()
        alert(id)
        window.location.href="../article/"+id
    })

    $(".label_jump").click(function () {
        id=$(this).next().text()
        alert(id)
    })



})