package com.hex.demoauthor.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * User: hexuan
 * Date: 2019/3/18
 * Time: 4:41 PM
 */
@Data
public class OperatorForm {

    /**
     * 用户名
     */
    @NotBlank(message = "登录名称不能为空")
    private String name;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

}
