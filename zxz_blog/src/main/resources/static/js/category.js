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
                var category_id= $("#cur_category_id").text()
                load_articleByCategory(page,10,category_id)
            }

        }
    })

}
function load_articleByCategory(pageNo,pageSize,category_id){
    //加载博客


    $.ajax({
        type: 'POST',
        url: '/selectArticleNumByCategoryId',
        dataType: 'json',
        data: {
            pageSize:pageSize,
            pageNo:pageNo,
            category_id:category_id

        },
        success: function (data) {

            var blogs_html=""
            var object=data.data
            var blog_list=object.list
            for(var i=0;i<blog_list.length;i++){
                var time=blog_list[i].pulish_date.substring(0,10)
                var blog_item='<div class="category_blog_item">\n' +
                    '\n' +
                    '                    <div class=" container blog_div_item border_shadow padding_left_right_small margin_buttom_middle">\n' +
                    '\n' +
                    '                        <div><h4><a href="../article/'+blog_list[i].id+'">'+blog_list[i].article_title+'</a></h4></div>\n' +
                    '                        <hr  style="width: 670px;background-color: darkgrey;height: 1px;border: none">\n' +
                    '                        <div class=" category_blog_item_icon" >\n' +
                    '                            <div class="category_blog_yuanchuang"><span>原创</span></div>\n' +
                    '                            <div class="category_blog_time">\n' +
                    '                                <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>\n' +
                    '                                '+time+'</div>\n' +
                    '                            <div class="category_blog_look">\n' +
                    '                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>\n' +
                    '                                '+blog_list[i].look_number+'\n' +
                    '                            </div>\n' +
                    '                            <div class="category_blog_zan">\n' +
                    '                                <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>\n' +
                    '                                '+blog_list[i].zan_number+'\n' +
                    '                            </div>\n' +
                    '                            <div class="category_blog_say">\n' +
                    '                                <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>\n' +
                    '                                '+blog_list[i].comment_number+'\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>'

                blogs_html=blogs_html+blog_item;
            }

            $("#category_article_list").html(blogs_html)
            //加载分页
            load_page(object.total,object.pageNum,object.pages)
            //滚动到页面顶部
            window.scrollTo(0,0)
        },
        error: function () {
            alert("获取分类对应文章失败！");
        }
    });
}
function jumpy_articles(e){

    var category_id= $(e).children('span').eq(0).text()
    var category_name=$(e).children('span').eq(2).text()
    alert(category_name)
    load_articleByCategory(1,10,category_id)
    $("#cur_category").text(category_name)
    $("#cur_category_id").text(category_id)
}
$(function () {



    //加载目录
    $.ajax({
        type: 'POST',
        url: '/selectAllCategoryAndNum',
        dataType: 'json',
        data:{

        },
        success: function (data) {

            var category_html=""
            var category_list=data.data

            for(var i=0;i<category_list.length;i++){
                var category_item=' <li class="list-group-item category_articles" onclick="jumpy_articles(this)">\n' +
                    '                    <span style="display: none" >'+category_list[i].id+'</span>\n' +
                    '                    <span class="badge">'+category_list[i].article_num+'</span>\n' +
                    '<span>'+category_list[i].category_name+'</span>'+
                    '                </li>'
                category_html=category_html+category_item
            }
            $("#category_list").html(category_html)
        },
        error: function () {
            alert("加载目录失败！");
        }
    });



    //加载文章
})