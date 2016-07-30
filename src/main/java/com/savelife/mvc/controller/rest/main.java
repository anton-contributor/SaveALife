package com.savelife.mvc.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by anton on 29.07.16.
 */
@Controller
@RequestMapping(value = {"/"})
public class main {
    @RequestMapping()
    public String loadIndex(){
        return "index";
    }
}
