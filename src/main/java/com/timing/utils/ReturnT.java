/**
 * Project Name:
 * Class Name:com.timing.utils.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 11:10 AM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 11:10 AM
 **/
@Data
public class ReturnT<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    public static final ReturnT<String> SUCCESS = new ReturnT<String>(null);
    public static final ReturnT<String> FAIL = new ReturnT<String>(FAIL_CODE, null);

    private int code;
    private String msg;
    private T content;

    public ReturnT(){}

    public ReturnT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ReturnT(T content) {
        this.code = SUCCESS_CODE;
        this.content = content;
    }

}
