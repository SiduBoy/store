package com.jyb.store.service.impl;

import com.jyb.store.entity.User;
import com.jyb.store.mapper.UserMapper;
import com.jyb.store.service.IUserService;
import com.jyb.store.service.ex.InsertException;
import com.jyb.store.service.ex.PasswordNotMatchException;
import com.jyb.store.service.ex.UserNotFoundException;
import com.jyb.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service//将当前对象交给Spring管理，可以自动创建对象和对象的维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User userName = userMapper.findUserName(username);
        if (userName != null) {
            throw new UsernameDuplicatedException("用户名已存在！");
        }
        //密码加密处理：md5算的形式 eg:F85056DB6E7BCF97914085E0A43EA6F9
        //（随机串+password+随机串【盐值】）---md5算法进行加密，连续加密三次
        String oldPassword = user.getPassword();
        //获取盐值
        String yanzhi = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值一起加密,忽略了原有的密码强度，提升了数据的安全性
        String md5Password = getMD5Password(oldPassword, yanzhi);
        //将加密的密码给到user对象中
        user.setPassword(md5Password);

        //补全用户信息
        user.setSalt(yanzhi);//盐值
        user.setIsDelete(0);//是否被删除
        user.setCreatedUser(user.getUsername());//创建人
        user.setModifiedUser(user.getUsername());//修改人
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("服务器宕机请重试！");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名来查询用户的数据是否存在，如果没有抛出异常
        User result = userMapper.findUserName(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在！");
        }
        //检测用户密码是否匹配
        //1.先获取数据库中加密的密码
        String oldPassword = result.getPassword();

        //2.和用户传递过来的密码进行比较
        //2.1 获取数据库中的盐值
        String salt = result.getSalt();
        //2.2 将用户传递的密码进行md5算法加密
        String md5Password = getMD5Password(password, salt);

        //3.密码进行比较
        if (!md5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("密码错误！");
        }

        //判断id_delete字段的值是否为1,1是被逻辑删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在！");
        }

        //提升系统效率，将要展示数据存到一个新对象中
        User user = new User();
        user.setUsername(result.getUsername());
        user.setUid(result.getUid());
        user.setAvatar(result.getAvatar());

        //将当前的用户数据返回，返回的目的是为了辅助其他页面做展示
        return result;
    }

    private String getMD5Password(String oldPassword ,String yanzhi){
        //md5加密算法的调用（进行三次加密）
        String mima = "加密失败";
        for (int i =  0; i < 3; i++) {
            mima= DigestUtils.md5DigestAsHex((yanzhi + oldPassword + yanzhi).getBytes()).toUpperCase();
        }
        return mima;
    }
}
