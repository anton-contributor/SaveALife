package com.savelife.mvc.rest;

import com.savelife.mvc.model.messaging.device.DeviceMessage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public String welcomePage() {
        return "Wellcome page";
    }

    @RequestMapping(value = {"/signUp"}, method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestBody DeviceMessage deviceMessage){
        String email = deviceMessage.getEmail();
        if(email == null)
            return new ResponseEntity<String>("email is invalid", HttpStatus.UNPROCESSABLE_ENTITY);
        else if(userService.findUserByEmail(email) != null)
            return new ResponseEntity<String>("user already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        else {
            userService.save(deviceMessage.setUserFieldsFromDeviceMessage(new UserEntity()));
        }
        return new ResponseEntity<String>("new user was created", HttpStatus.CREATED);
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