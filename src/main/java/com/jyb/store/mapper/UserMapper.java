package com.jyb.store.mapper;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.jyb.store.entity.User;
//@Mapper

public interface UserMapper {
    /**
     * 插入用户数据
     * @param user
     * @return 受影响的行数作为返回值
     */
    Integer insert(User user);

    /**
     * 根据用户名来查找
     * @param username
     * @return
     */
    User findUserName(String username);

    /**
     * 根据id修改密码
     * @param uid id
     * @param password 新密码
     * @param modifiedUser 修改执行者
     * @param modifiedTime 时间
     * @return 受影响行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * id查对象
     * @param uid 已有id
     * @return 对象
     */
    User findByUid(Integer uid);



}
