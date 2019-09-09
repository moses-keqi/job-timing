/**
 * Project Name:
 * Class Name:com.timing.po.enums.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/19 5:12 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.po.enums;

import lombok.Getter;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/19 5:12 PM
 **/

public enum ExecuteType {

    CRON("CRON表达式", "例如0/5 * * * * ? 5秒执行一次"), //例如0/5 * * * * ? 5秒执行一次

    DATE_DEF("DATE时间", "日期表达式支持两种第一种：2019-08-20 22:22:22  第二种：22:22:22(每天这个点执行)"), //日期表达式支持两种第一种：2019-08-20 22:22:22  第二种：22:22:22(每天这个点执行)

    FIXED_RATE("INT时间", "数字毫秒表达式 如：1000 1秒执行一次"); //数字毫秒表达式 如：1000 1秒执行一次

    @Getter
    private String name;

    @Getter
    private String explain;

    ExecuteType(String name, String explain){
        this.name = name;
        this.explain = explain;
    }

}
