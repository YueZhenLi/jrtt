package com.cskaoyan.smzdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/11
 */
@Controller
public class User {

    @RequestMapping(path = "/index")
    public String homne(Model model){
        
        return "home";
    }
}
