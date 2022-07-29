package com.jyb.store.mapper;

import com.jyb.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/*表示是一个测试类不会和项目一起打包*/
@SpringBootTest
/*表示启动这个测试类,必须要传此参数*/
@RunWith(SpringRunner.class)
public class UserMapperTests {
    //自动装配 idea检测，接口是不能直接创建Bean（动态代理来解决）
    //在设置中将inspections的Spring Core Autowiring for bean class权限降低
    @Autowired//(required = true)
    private UserMapper userMapper;
    /*
    * 单元测试方法：独立运行，不用启动整个项目，单元测试
    * 1.必须@Test修饰
    * 2.返回类型必须是void
    * 3.方法的参数列表不指定任何值
    * 4.方法访问的修饰符必须是public
    * */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("马六");
        user.setPassword("123");
        Integer fist = userMapper.insert(user);
        System.out.println(fist);
    }

    @Test
    public void findByUserName(){
        User a = userMapper.findUserName("张三");
        System.out.println(a.toString());
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(15, "123", "xiao", new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(13));
    }


}
