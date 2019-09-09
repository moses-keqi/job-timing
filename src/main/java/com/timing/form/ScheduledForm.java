/**
 * Project Name:
 * Class Name:com.timing.form.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/21 1:33 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.form;

import com.timing.po.Scheduled;
import com.timing.po.enums.ExecuteType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/21 1:33 PM
 **/
@Data
public class ScheduledForm implements Serializable {

    private Long id;

    //名称
    @NotEmpty(message = "名称不能为空")
    private String name;

    //参数执行器
    @NotEmpty(message = "执行器不能为空")
    private String  execute;

    //备注
    @NotEmpty(message = "备注不能为空")
    private String remarks;

    //自定义参数
//    @NotEmpty(message = "自定义参数不能为空")
    private  String param;

    // @JobHandler  value name
    @NotEmpty(message = "JobHandler不能为空")
    private String handler;

    private ExecuteType executeType = ExecuteType.CRON;

    //状态
    private boolean state;


    public static Scheduled  copyForm(ScheduledForm f){
        Scheduled e = new Scheduled();
        e.setId(f.getId());
        e.setName(f.getName());
        e.setExecute(f.getExecute());
        e.setRemarks(f.getRemarks());
        e.setParam(f.getParam());
        e.setHandler(f.getHandler());
        e.setExecuteType(f.getExecuteType());
        e.setState(f.isState());
        if (f.getId() == null){
            e.setCreateTime(new Date());
        }
        e.setUpdateTime(new Date());
        return e;
    }

    public static ScheduledForm  copyPo(Scheduled e){
        ScheduledForm f = new ScheduledForm();
        f.setId(e.getId());
        f.setName(e.getName());
        f.setExecute(e.getExecute());
        f.setRemarks(e.getRemarks());
        f.setParam(e.getParam());
        f.setHandler(e.getHandler());
        f.setExecuteType(e.getExecuteType());
        f.setState(e.isState());
        return f;
    }
}
