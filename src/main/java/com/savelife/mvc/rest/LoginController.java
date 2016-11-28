package com.savelife.mvc.rest;

import com.savelife.mvc.model.messaging.device.DeviceMessage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.model.user.UserRoleEntity;
import com.savelife.mvc.service.user.UserRoleService;
import com.savelife.mvc.service.user.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class LoginController {

    private static Logger logger = Logger.getLogger(UserPost.class.getName());


    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    Environment environment;


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
        String firstName = newUser.getFirstName();
        String lastName = newUser.getLastName();

        if(phoneNumber == null || password == null)
            return new ResponseEntity<>("phone number or password wasn't got", HttpStatus.UNPROCESSABLE_ENTITY);
        else if(firstName == null || lastName == null)
            return new ResponseEntity<>("first or last name weren't got", HttpStatus.UNPROCESSABLE_ENTITY);
        else if(userService.findByPhoneNumber(phoneNumber) != null)
            return new ResponseEntity<>("user already exists", HttpStatus.UNPROCESSABLE_ENTITY);

        if(deviceMessage.isDoctor()) {
            logger.info("USER IS DOCTOR");
            String doctorInfo = getDoctorInfo(firstName, lastName);
            if (doctorInfo != null)
                newUser.setDoctorSpecialty(doctorInfo);
        }

        newUser.setUserRole(role);
        userService.save(newUser);

        return new ResponseEntity<>("new user was created<br>" + newUser
                + "<br>" + deviceMessage, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/jsonTest"}, method = RequestMethod.GET)
    public String getDoctorInfo(String firstName, String lastName){
        String result = "";
        String requestUrl = environment.getProperty("better.doctor.current.api.link") +
                "/doctors?first_name=" + firstName + "&last_name=" + lastName +
                "&skip=0&limit=10&user_key=" + environment.getProperty("better.doctor.api.key");

        RestTemplate restTemplate = new RestTemplate();
        String doctorInfoStr = restTemplate.getForObject(requestUrl, String.class);
        JSONObject doctorInfo = new JSONObject(doctorInfoStr);

        try {

            JSONArray data = doctorInfo.getJSONArray("data");

            if(data.length() != 1)
                logger.info("more than 1 doctor with this full name was found");

            JSONArray specialityList = data.getJSONObject(0).getJSONArray("specialties");
            for(int i = 0; i < specialityList.length(); i++){
                JSONObject jsonObject = (JSONObject) specialityList.get(i);
                result += jsonObject.get("name") + ", ";
            }
            result = result.substring(0, result.length() - 2);

        }catch (JSONException e){
            logger.info("json wasn't got and parsed correctly");
        }

        if(result != "") return result;
        else return null;
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