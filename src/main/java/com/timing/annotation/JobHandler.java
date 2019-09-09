/**
 * Project Name:
 * Class Name:com.timing.annotation.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/19 5:35 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.annotation;

import java.lang.annotation.*;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/19 5:35 PM
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JobHandler {

    String value() default "";

}
