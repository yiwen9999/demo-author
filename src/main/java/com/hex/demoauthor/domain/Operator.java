package com.hex.demoauthor.domain;

import com.hex.demoauthor.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作人员
 *
 * @author hexuan
 */
@Entity
@DynamicUpdate
@Data
public class Operator implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 50)
    private String id;

    /**
     * 用户名
     */
    @Column(nullable = false, length = 40, unique = true)
    private String name;

    /**
     * 昵称
     */
    @Column(nullable = false, length = 40)
    private String nickname;

    /**
     * 密码
     */
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * 头像文件id
     */
    private String iconId;

    /**
     * 头像路径
     */
    private String iconPath;

    /**
     * 状态
     */
    private Integer state = StateEnum.START.getCode();

    /**
     * 创建人
     */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Operator creator;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

}
