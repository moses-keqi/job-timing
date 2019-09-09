/**
 * Project Name:
 * Class Name:com.timing.controller.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/9/9 12:54 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, sioo All Rights Reserved.
 */
package com.timing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/9/9 12:54 PM
 **/
@Controller
public class IndexController {


    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String indexHandler(){
        return "redirect:/scheduled/list";
    }

}
