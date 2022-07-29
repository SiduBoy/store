package com.jyb.store.service;

import com.jyb.store.entity.User;
import com.jyb.store.service.ex.ServiceException;
import com.jyb.store.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    public void Textt(){
        try {
            User user = new User();
            user.setUsername("玛丽");
            user.setPassword("123");
            iUserService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test01(){
        try {
            iUserService.login("小王", "123");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void changePassword(){
        iUserService.changePassword(14,"小王","321","123");
    }
}
