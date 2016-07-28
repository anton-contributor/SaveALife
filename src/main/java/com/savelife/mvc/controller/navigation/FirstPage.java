package com.savelife.mvc.controller.navigation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is the navigation controller for first page.
 * When the request corresponds identified mappings in the current controller the first page loads on screen.
 */
@Controller
@RequestMapping(value = {"/","/home","/map"})
public class FirstPage {
    @RequestMapping
    public String loadFirstPage(){
        return "index";
    }
}
