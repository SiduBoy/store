package com.jyb.store.mapper;
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

    Integer updateByUsername(@Param("updated")User updated,@Param("username")String username);

	Integer deleteByUsername(@Param("username")String username);

	List<User> selectAllByUsername(@Param("username")String username);


}
