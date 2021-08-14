package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.junit.jupiter.api.Test;

;

public class UserDaoTest {



    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("admin");
        loginuser.setPassword("123");


        UserDao dao = new UserDao();
        User user = dao.login(loginuser);

        System.out.println(user);
    }
}
