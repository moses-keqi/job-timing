/**
 * Project Name:
 * Class Name:com.timing.po.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/19 5:10 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.po;

import com.timing.po.enums.ExecuteType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/19 5:10 PM
 **/
@Entity
@Table(name="scheduled")
@Data
public class Scheduled implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    //名称
    private String name;

    //参数执行器
    private String  execute;

    //备注
    private String remarks;

    //自定义参数
    private  String param;

    // @JobHandler  value name
    private String handler;

    //执行类型
    @Enumerated(EnumType.STRING)
    private ExecuteType executeType;

    //状态
    private boolean state;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;


}
