package com.savelife.mvc.rest;

import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * user rest controller to receiving get requests
 */
@RestController
@Async
@RequestMapping(value = {"/rest/user"})
public class UserGet {

    private static Logger logger = Logger.getLogger(UserGet.class.getName());

    /*
    * CRUD user service
    * */
    @Autowired
    UserService userService;

    @GetMapping()
    public Callable<ResponseEntity<String>> get(@RequestParam("role") String role, @RequestParam("token") String token) {
        return () -> {
            logger.info("Received role= " + role);
            userService.findAllUnableDrivers().forEach(v -> System.out.println(v.toString()));
            userService.findAllBeyondCurrent(token).forEach(v -> System.out.println(v.toString()));
            return new ResponseEntity(HttpStatus.OK);
        };
    }

    @GetMapping(value = {"/"})
    public Callable<ResponseEntity<String>> test() {
        return () -> {
            return new ResponseEntity<String>("Save A Life", HttpStatus.OK);
        };
    }


    @GetMapping(value = {"/test"})
    public ResponseEntity<String> test2(HttpServletRequest request) {
//        HttpSession httpSession = request.getSession();

//        HttpSessionManager sessionManager=(HttpSessionManager)request.getAttribute(
//                "org.springframework.session.web.http.HttpSessionManager");

//        return new ResponseEntity<String>(httpSession.getId(), HttpStatus.OK);
        String str = "";
        String subStr = "";
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            subStr = attributeNames.nextElement();
            str += subStr + "  :  " + request.getAttribute(subStr) + "\n------------------------------------\n";
        }
        return new ResponseEntity<String>(str, HttpStatus.OK);

    }
}
