package com.hex.demoauthor.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * User: hexuan
 * Date: 2019/3/26
 * Time: 1:29 PM
 */
@Data
public class OperatorVO implements Serializable {

    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像路径
     */
    private String iconPath;

    /**
     * 头像id
     */
    private String iconId;
}
