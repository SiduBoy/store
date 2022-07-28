package com.jyb.store.controller;

import com.jyb.store.service.ex.*;
import com.jyb.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/*控制层类的基类*/
public class BaseController {
    /** 操作成功的状态码*/
    public static final int ok = 200;

    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当项目中产生了异常将，被统一拦截到此方法中，此方法此时就充当时请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler(ServiceException.class)//用于统一处理某种异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户不存在");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("密码错误");
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时未知异常");
        }
        return result;
    }

    //封装session对象，服务器自动创建session对象。

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录对象的uid
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录对象的用户名
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
