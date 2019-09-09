/**
 * Project Name:
 * Class Name:com.timing.controller.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/21 5:09 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/21 5:09 PM
 **/

@Controller
@RequestMapping(value = "/log")
public class LogController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String login(){
        return "log-index";
    }
}