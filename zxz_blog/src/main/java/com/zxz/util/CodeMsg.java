package com.zxz.util;

public enum CodeMsg {


    USER_LOGIN_ERROR(1,"用户登陆失败"),


    DEFAULT_STATUS_SUCCESS(0, "成功"),

    NOT_LOGIN(3,"未登录"),
    ADD_USER_SUCCESS(30,"添加用户成功"),
    ADD_USER_FAIL(31,"添加用户失败"),

    VERFICATION_TIMEOUT(33,"验证码超时"),


    ARTICLE_QUERY_EMPTY(35,"文章暂时为空"),



    /**
     * 标签操作
     */

    CURRENT_LABEL_EMPTY(39,"当前暂无标签"),

    ADD_LABEL_ERROR(41,"添加标签失败"),
    ADD_LABEL_SUCCESS(42,"添加标签成功"),

    DELETE_LABEL_ERROR(43,"删除标签失败"),
    DELETE_LABEL_SUCCESS(44,"删除标签成功"),
    UPDATE_LABEL_ERROR(45,"修改标签失败"),
    UPDATE_LABEL_SUCCESS(46,"修改标签成功"),

    /**
     * 分类操作
     */
    CURRENT_CATEGORY_EMPTY(37,"当前暂无分类"),
    ADD_CATEGORY_ERROR(47,"添加分类失败"),
    ADD_CATEGORY_SUCCESS(48,"添加分类成功"),

    DELETE_CATEGORY_ERROR(49,"删除分类失败"),
    DELETE_CATEGORY_SUCCESS(50,"删除分类成功"),
    UPDATE_CATEGORY_ERROR(51,"修改分类失败"),
    UPDATE_CATEGORY_SUCCESS(52,"修改分类成功"),


    /**
     * 文章操作
     *
     */

    PUBLISH_ARTICLE_ERROR(53,"文章发布失败"),
    PUBLISH_ARTICLE_SUCCESS(54,"文章发布成功"),
    LINK_CUR_ARTICLE(55,"链接这篇文章失败"),

    LOAD_ARTICLE_ERROR(57,"文章不存在"),

    /**
     * 评论
     */

    LOAD_COMMENT_EMPTY(59,"暂时还没有评论"),

    INSERT_COMMENT_SUCCESS(60,"评论成功"),

    INSERT_COMMENT_ERROR(61,"评论失败"),


    ZAN_ERROR(63,"点赞失败"),

    DELETE_ZAN_ERROR(65,"取消点赞失败");






    private int code;
    private String msg;

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
