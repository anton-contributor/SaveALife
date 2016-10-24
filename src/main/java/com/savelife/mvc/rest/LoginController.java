package com.savelife.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public String welcomePage() {
        return "Wellcome page";
    }

    @RequestMapping(value = { "/logout"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String logout(HttpServletResponse response, HttpSession session) {
        session.invalidate();
//        response.addCookie(new Cookie("test", "invalidSession"));
//        return "logout page";
//        session.invalidate();
        return "logoutpage";
    }



//    //Spring Security see this :
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login(
//            @RequestParam(value = "error", required = false) String error,
//            @RequestParam(value = "logout", required = false) String logout) {
//
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
//        model.setViewName("login");
//
//        return model;
//    }

}