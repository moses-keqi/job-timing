/**
 * Project Name:
 * Class Name:com.timing.handle.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/20 11:10 AM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.handle;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/20 11:10 AM
 **/

public abstract class IJobHandler {


    public abstract void execute(String param) throws Exception;


    /**
     * init handler, invoked when JobThread init
     */
    public void init() {
        // TODO
    }


    /**
     * destroy handler, invoked when JobThread destroy
     */
    public void destroy() {
        // TODO
    }
}
