package org.orange.domain.enums;

import org.orange.domain.entity.Tag;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "用户名必填"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONTENT_NOTNULL(508,"评论内容不可为空"),
    FILE_NOTEMPTY(509,"文件不能为空"),
    FILE_TYPE_ERROR(510,"文件类型错误"),
    ROLES_NOT_EMPTY(511,"角色不能为空"),
    TAG_NOT_FOUND(512,"标签不存在"),
    MENU_EXISTED(513,"菜单已存在"),
    MENU_HAS_CHILD(514,"存在子菜单无法删除"),
    EMAIL_FORMAT_ERROR(515,"邮箱格式错误" ),
    REQUIRE_PASSWORD(516,"密码不能为空" ),
    PASSWORD_FORMAT_ERROR(517, "密码只能为6-16位数字、字母、下划线"),
    CODE_ERROR(518,"邮件未发送"),
    REQUIRE_VALIDACODE(519,"未填写验证码"),
    CODE_EXPIRED(520,"验证码已过期"),
    CODE_WRONG(521,"验证码错误！");
    int code;
    String msg;
    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
