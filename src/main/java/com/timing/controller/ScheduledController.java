/**
 * Project Name:
 * Class Name:com.timing.controller.java
 * <p>
 * Version     Date         Author
 * -----------------------------------------
 * 1.0    2019/8/19 3:57 PM      HanKeQi
 * <p>
 * Copyright (c) 2019, Timing All Rights Reserved.
 */
package com.timing.controller;

import com.timing.business.ConcurrentBusiness;
import com.timing.form.ScheduledForm;
import com.timing.handle.IJobHandler;
import com.timing.po.Scheduled;
import com.timing.service.DefaultSchedulingService;
import com.timing.service.ScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/19 3:57 PM
 **/

@Controller
@Slf4j
@RequestMapping(value = "/scheduled")
public class ScheduledController {

    @Autowired
    private ScheduledService scheduledService;

    @Autowired
    private ConcurrentBusiness concurrentBusiness;

    @Autowired
    private DefaultSchedulingService defaultSchedulingService;


    @RequestMapping(value = {"/list", "/list/"}, method = RequestMethod.GET)
    public String scheduledHandler(Model model) throws Exception {
        List<Scheduled> schedules = scheduledService.getAll();
        model.addAttribute("schedules", schedules);
        return "scheduled-list";
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.GET)
    public String initCreateHandler(Model model){
        model.addAttribute("form", new ScheduledForm());
        return "scheduled-edit";
    }


    @RequestMapping(value = {"/create", "/create/"}, method = RequestMethod.POST)
    public String createHandler(@Valid @ModelAttribute("form") ScheduledForm form, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()){
            return "scheduled-edit";
        }
        if (concurrentBusiness.get(form.getHandler()) == null){
            bindingResult.rejectValue("handler", "handler", String.format("代码中不存在%s,请检查注解", form.getHandler()));
            return "scheduled-edit";
        }
        Scheduled scheduled = ScheduledForm.copyForm(form);
        scheduledService.save(scheduled);
        resetTimer(scheduled);
        return "redirect:/scheduled/list";
    }



    @RequestMapping(value = {"/update/{id}", "/update/{id}/"}, method = RequestMethod.GET)
    public String initUpdateHandler(@PathVariable("id") Long id, Model model) throws Exception {
        Scheduled scheduled = scheduledService.getById(id);
        model.addAttribute("form", ScheduledForm.copyPo(scheduled));
        return "scheduled-edit";
    }

    /**
     * 修改
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = {"/update/{id}", "/update/{id}/"}, method = RequestMethod.POST)
    public String updateHandler(@PathVariable("id") Long id, @Valid @ModelAttribute("form") ScheduledForm form, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()){
            return "scheduled-edit";
        }
        Scheduled scheduled = scheduledService.getById(id);
        if (scheduled == null){
            bindingResult.rejectValue("name", "name", "没有找到相关信息");
            return "scheduled-edit";
        }
        if (concurrentBusiness.get(form.getHandler()) == null){
            bindingResult.rejectValue("handler", "handler", String.format("代码中不存在%s,请检查注解", scheduled.getHandler()));
            return "scheduled-edit";
        }
        scheduledService.save(ScheduledForm.copyForm(form));
        resetTimer(scheduled);
        return "redirect:/scheduled/list";
    }

    /**
     * 启动执行
     * @param id
     * @return
     */
    @RequestMapping(value = {"/start/{id}", "/start/{id}/"}, method = RequestMethod.GET)
    @ResponseBody
    public String stateHandler(@PathVariable("id") Long id) throws Exception {
        Scheduled scheduled = scheduledService.getById(id);
        if (scheduled != null){
            boolean state = scheduled.isState();
            scheduled.setState(!state);
            scheduledService.save(scheduled);
            resetTimer(scheduled);
            return "0";
        }
        return "1";
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/del/{id}", "/del/{id}/"}, method = RequestMethod.GET)
    @ResponseBody
    public String delHandler(@PathVariable("id") Long id) throws Exception {
        try {
            Scheduled scheduled = scheduledService.getById(id);
            if (scheduled != null){
                if (defaultSchedulingService.exist(scheduled)){
                    defaultSchedulingService.cancelTask(scheduled);
                }
                scheduledService.del(id);
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }

    /**
     * 单次执行
     * @param id
     * @return
     */
    @RequestMapping(value = {"/execute/{id}", "/execute/{id}/"}, method = RequestMethod.GET)
    @ResponseBody
    public String executeHandler(@PathVariable("id") Long id) throws Exception {
        Scheduled scheduled = scheduledService.getById(id);
        if (scheduled != null){
            IJobHandler jobHandler = (IJobHandler) concurrentBusiness.get(scheduled.getHandler());
            jobHandler.execute(scheduled.getParam());
            return "0";
        }
        return "1";
    }


    private void resetTimer(Scheduled scheduled){
        try {
            if (scheduled.isState()){
                defaultSchedulingService.add(scheduled);
            }else{
                defaultSchedulingService.cancelTask(scheduled);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
