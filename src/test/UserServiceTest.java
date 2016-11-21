//import com.savelife.mvc.configuration.ApplicationConfiguration;
//import com.savelife.mvc.service.user.UserRoleService;
//import com.savelife.mvc.service.user.UserService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
///**
// * Created by gleb on 19.11.16.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {ApplicationConfiguration.class})
//public class UserServiceTest {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRoleService userRoleService;
//
//    @Test
//    public void getFriendsLisTest(){
//        try {
//            userService.addContactById(4L, 2L);
//        }catch (Exception exception){
//            System.out.println("raw already exist");
//        }
//    }
//
//    @Test
//    public void existByPhoneNumberPositiveResultTest(){
//        Assert.assertTrue(userService.existByPhoneNumber("2"));
//    }
//
//    @Test
//    public void existByPhoneNumberNegativeResultTest(){
//        Assert.assertFalse(userService.existByPhoneNumber("6"));
//    }
//
//    @Test
//    public void deleteContactTest(){
//        userService.deleteContact("5", "1");
//    }
//}