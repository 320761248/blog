

$(function() {

    var editor;



    //加载编辑器
    editor=editormd("my-editormd", { //注意1：这里的就是上面的DIV的id属性值
        width: "100%",
        height: "700px",
        syncScrolling: "single",
        path: "editormd/lib/", //注意2：你的路径

        codeFold : true,
        emoji:true,
        tocm : true, // Using [TOCM]
        tex : true, // 开启科学公式TeX语言支持，默认关闭
        flowChart : true, // 开启流程图支持，默认关闭
        sequenceDiagram : true, // 开启时序/序列图支持，默认关闭,
        htmlDecode : true, //不过滤标签
        imageUpload : true, //上传图片
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp","JPG","JPEG","GIF","PNG","BMP","WEBP"],
        imageUploadURL : "/uploadImage",
        /*上传图片成功后可以做一些自己的处理*/
        onload:function () {
            // console.log('onload', this);
        },
        saveHTMLToTextarea: true, //注意3：这个配置，方便post提交表单
        toolbarIcons : function () {
            return ["bold","del","italic","quote","|","h1","h2","h3","h4","h5","h6","|","list-ul","list-ol","hr","|","link","image","code","code-block","table","datetime","html-entities","emoji","|","watch","preview","fullscreen","clear","search","|","help","info"]
        },

        /**设置主题颜色*/
        editorTheme: "pastel-on-dark",
        theme: "gray",
        previewTheme: "dark"
    });


    //发表博客
    $("#publish_blog").click(function () {

        article_title=$("#article_title").val()
        //获取选中标签内容
        article_label=$("#label_choose option:selected").next().val()
        article_category=$("#category_choose option:selected").next().val()
        alert(article_label)
        alert(article_category)
        //获取内容
        article_content=$("#my-editormd-html-code").val()

        //判断是否内容为空
        if(article_content.trim()==""){
            alert("发布内容为空")
            return;
        }
        if(article_title.trim()==""){

            alert("标题为空")
            return;
        }

        //发送url
        $.ajax({
            url:"publish_article",
            data:{
                "article_label":article_label,
                "article_category":article_category,
                "article_content":article_content,
                "article_title":article_title

            },
            type:"post",
            dataType:"json",
            success:function (result) {
                if(result%2==0){
                    alert(result.msg)
                }else{
                    alert(result.msg)
                }
            },
            error:function (xhr,status,error) {

            }
        })

    })





});
