import com.savelife.mvc.configuration.ApplicationConfiguration;
import com.savelife.mvc.configuration.HibernateConfiguration;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.user.UserRoleService;
import com.savelife.mvc.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

//import com.savelife.mvc.service.user.UserServiceSpringDataExample;

/**
 * Created by gleb-pc on 8/22/16.
 */




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfiguration.class, ApplicationConfiguration.class})
public class TestUserSevice {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;


    @Test
    public void testConfig(){
        Assert.assertNotNull(userService);
    }

    @Test
    public void testSaveFunction(){
        userService.save(newFieldUser());
    }

    @Test
    public void testFindUserByTokenFunction(){
        Assert.assertNotNull(userService.findUserByToken("Mon Aug 29 10:15:16 EEST 2016"));
    }

    @Test
    public void testFindUserByIDFunction(){
        Assert.assertNotNull(userService.findUserById(2));
    }

    @Test
    public void testFindAllUsersFunction(){
        long beforeSize = userService.findAllUsers().size();
        userService.save(newFieldUser());
        long afterSize = userService.findAllUsers().size();
        Assert.assertEquals(beforeSize+1, afterSize);
    }

    @Test
    public void testFindAllByRoleFunction(){
        long roleOne = userService.findAllByRole("first").size();
        long secondRole = userService.findAllByRole("second").size();
        long generalSize = userService.findAllUsers().size();
        Assert.assertEquals(roleOne + secondRole, generalSize);
    }

    @Test
    public void testUpdateFuntion(){
        String currentDate = new Date().toString();
        UserEntity userEntity = userService.findUserById(1);
        userEntity.setToken(currentDate);
        userService.update(userEntity);
        UserEntity secondUser = userService.findUserById(1);
        Assert.assertEquals(currentDate, secondUser.getToken());
    }

    @Test
    public void testDeleteFunction(){
        long size = userService.findAllUsers().size();
        UserEntity userEntity = userService.findUserById(size-1);
        userService.delete(userEntity);
        long finalSize = userService.findAllUsers().size();
        Assert.assertEquals(size-1, finalSize);
    }

    @Test
    public void testExistFunction(){
        Assert.assertFalse(userService.exist(new Date().toString()));
    }

    @Test
    public void testDeleteByTokenFunction(){
        long size = userService.findAllUsers().size();
        UserEntity userEntity = userService.findUserById(size-1);
        userService.deleteByToken(userEntity.getToken());
        long finalSize = userService.findAllUsers().size();
        Assert.assertEquals(size-1, finalSize);
    }

    public UserEntity newFieldUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setCurrentLatitude(6.65656D);
        userEntity.setCurrentLongitude(6.65656D);
        userEntity.setDestinationLatitude(6.65656D);
        userEntity.setDestinationLongitude(6.65656D);
        userEntity.setToken(new Date().toString());
        userEntity.setEnable(true);
        userEntity.setUserRole(userRoleService.findRoleById(2));
        return userEntity;
    }

}
