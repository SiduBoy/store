package com.jyb.store.service;

import com.jyb.store.entity.User;
import org.springframework.stereotype.Repository;


/*用户模块业务接口*/
//@Repository
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 注册对象
     */
    void reg(User user);

    /**
     * 用户登陆功能
     * @param username 登陆名
     * @param password 登陆密码
     * @return 当前匹配的数据没有为null
     */
    User login(String username,String password);

    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);
}
