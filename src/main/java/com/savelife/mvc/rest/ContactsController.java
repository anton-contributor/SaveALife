package com.savelife.mvc.rest;

import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.user.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by gleb on 17.11.16.
 */
@RestController
public class ContactsController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/manyToManyTest"}, method = RequestMethod.POST)
    public ResponseEntity<Map> checkContactsList(@RequestBody Map<String,Object> contacts) {
        List<String> numbers = (ArrayList)contacts.get("numbers");

        if(numbers == null)
            return new ResponseEntity<Map>(HttpStatus.BAD_REQUEST);

        Map<String, Map<String, String>> resultMap = createParsedJSONContactsMap(numbers);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @RequestMapping(value = {"/deleteContact"}, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteContact(@RequestBody String json) {
        final JSONObject obj = new JSONObject(json);
        String number;

        try {
            number = obj.getString("number");
            if(userService.deleteContact(number))
                return new ResponseEntity<String>("DELETE", HttpStatus.OK);
            else
                return new ResponseEntity<String>("contact wasn't found", HttpStatus.OK);
        }catch (JSONException e){
            return new ResponseEntity<String>("number field wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    public Map createParsedJSONContactsMap(List<String> numbers){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserPhoneNumber = auth.getName();
        HashMap<String, Map<String, String>> resultMap = new HashMap<>();

        for (String number : numbers) {
            Map<String, String> checkedNumber = new HashMap<>();
            UserEntity currentUser = userService.findByPhoneNumber(number);

            if(currentUser == null)
                checkedNumber.put("status", "disable");
            else if(number.equals(currentUserPhoneNumber))
                continue;
            else{
                try {
                    userService.addContactByPhoneNumber(currentUserPhoneNumber, number);
                }catch (Exception e){
                    checkedNumber.put("status", "contact already exist");
                    resultMap.put(number, checkedNumber);
                    continue;
                }
                checkedNumber.put("status", "enable");
                checkedNumber.put("name", currentUser.getName());
            }

            resultMap.put(number, checkedNumber);
        }

        return resultMap;
    }
}

