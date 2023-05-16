package com.gyj.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户表
 *
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
public class SysUser extends BaseEntity {

    /**
     *
     */
    @TableField(value = "username")
    private String username;

    /**
     *
     */
    @TableField(value = "password")
    private String password;

    /**
     *
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     *
     */
    @TableField(value = "phone")
    private String phone;

    /**
     *
     */
    @TableField(value = "email")
    private String email;

    /**
     *
     */
    @TableField(value = "login_date")
    private Date loginDate;

    /**
     *
     */
    @TableField(value = "status")
    private Integer status;



    /**
     * 所属角色，多个角色逗号隔开
     */
    @TableField(exist = false)
    private String roles;

    @TableField(exist = false)
    private String oldPassword;

    @TableField(exist = false)
    private String newPassword;


}
