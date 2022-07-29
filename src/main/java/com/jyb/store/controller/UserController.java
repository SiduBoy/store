package com.jyb.store.controller;

import com.jyb.store.entity.User;
import com.jyb.store.service.IUserService;
import com.jyb.store.service.ex.InsertException;
import com.jyb.store.service.ex.UsernameDuplicatedException;
import com.jyb.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

//@Component
//@Controller
@RestController//@Controller+@ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired//用到service的接口交给spring装配
    private IUserService userService;

    /**
     * 约定大于配置的：开发思想来完成
     * 1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端的数据，
     * SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较，如果
     * 相同将值注入到pojo类中
     * @param user
     * @return
     */
    @RequestMapping("reg")
    //@ResponseBody  //此方法表示将相应结果以js的形式响应给前端
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(ok);
    }
/*    @RequestMapping("reg")
    //@ResponseBody  //此方法表示将相应结果以js的形式响应给前端
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }catch (InsertException e) {
            result.setState(5000);
            result.setMessage("注册时位置异常");
        }
        return result;
    }*/

    /**
     * 2.接收数据方式：请求处理方法的参数列表设置为非pojo类型，
     * SpringBoot会直接将请求的参数比较，相同注入
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public  JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(ok,data);
    }

    /**
     * 修改密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param session 前端全局对象
     * @return json
     */
    @RequestMapping("change_password")
    public  JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        userService.changePassword(getuidFromSession(session),
                getUsernameFromSession(session),
                oldPassword,
                newPassword);
        return new JsonResult<>(ok);
    }
}
