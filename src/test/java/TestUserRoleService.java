import com.savelife.mvc.configuration.ApplicationConfiguration;
import com.savelife.mvc.configuration.HibernateConfiguration;
import com.savelife.mvc.model.user.UserRoleEntity;
import com.savelife.mvc.service.user.UserRoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//import com.savelife.mvc.service.user.UserServiceSpringDataExample;

/**
 * Created by gleb-pc on 8/22/16.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfiguration.class, ApplicationConfiguration.class})
public class TestUserRoleService {


    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void testConfig(){
        Assert.assertNotNull(userRoleService);
    }

    @Test
    public void testGetByID() {Assert.assertNotNull(userRoleService.findRoleById(1));}

    @Test
    public void testFindAllRolesFunction(){
        List<UserRoleEntity> all = userRoleService.findAll();
        long beforeSize = all.size();
        Assert.assertEquals(beforeSize, all.get(all.size()-1).getId().longValue());
    }

    @Test
    public void testFindRoleByName(){
        Assert.assertNotNull(userRoleService.findRoleByName("driver"));
    }

}
