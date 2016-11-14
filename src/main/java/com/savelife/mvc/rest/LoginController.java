package com.savelife.mvc.rest;

import com.savelife.mvc.model.messaging.device.DeviceMessage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.model.user.UserRoleEntity;
import com.savelife.mvc.service.user.UserRoleService;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;



    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public String welcomePage() {
        return "Wellcome page";
    }


    @RequestMapping(value = {"/signUp"}, method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestBody DeviceMessage deviceMessage){

        if(deviceMessage == null)
            return new ResponseEntity<>("device message wasn't got", HttpStatus.BAD_REQUEST);

        UserEntity newUser = deviceMessage.setUserFieldsFromDeviceMessage(new UserEntity());
        if(newUser == null)
            return new ResponseEntity<>("userWasn't created from device message", HttpStatus.BAD_REQUEST);

        UserRoleEntity role = userRoleService.findRoleByName(deviceMessage.getRole());
        if(role == null)
            return new ResponseEntity<>("role wasn't found", HttpStatus.UNPROCESSABLE_ENTITY);

        String password = newUser.getPassword();
        String phoneNumber = newUser.getPhoneNumber();

        if(phoneNumber == null || password == null)
            return new ResponseEntity<>("phone number or password wasn't got", HttpStatus.UNPROCESSABLE_ENTITY);
        else if(userService.findByPhoneNumber(phoneNumber) != null)
            return new ResponseEntity<>("user already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        else {
            newUser.setUserRole(role);
            userService.save(newUser);
        }
        return new ResponseEntity<>("new user was created<br>" + newUser
                + "<br>" + deviceMessage, HttpStatus.CREATED);
    }


    @RequestMapping(value = {"/signIn"}, method = RequestMethod.GET)
    public ResponseEntity<String> signIn() {
        return new ResponseEntity<String>("you have successfully logged in", HttpStatus.OK);
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