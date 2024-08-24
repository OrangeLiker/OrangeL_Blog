package org.orange.domain.entity;
import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2024-08-05 11:09:57
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User {

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @JsonAlias("username")
    private String userName;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,12}$", message = "密码必须为6-12位的包含英文、数字、下划线的字符串")
    private String password;
    /**
     * 用户类型：0代表普通用户，1代表管理员
     */
    private String type;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */     
 
    private String phonenumber;

    /**
     * 用户性别（0男，1女，2未知）
     */     
 
    private String sex;

    /**
     * 头像
     */     
 
    private String avatar;

    /**
     * 创建人的用户id
     */     
 
    private Long createBy;

    /**
     * 创建时间
     */     
 
    private Date createTime;

    /**
     * 更新人
     */     
 
    private Long updateBy;

    /**
     * 更新时间
     */     
 
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */     
 
    private Integer delFlag;
    @TableField(exist = false)
    private String validaCode;

}

