package com.hjc.demo.mapper;

import com.hjc.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * mapper接口
 *
 * @author hjc
 */
public interface UserMapper {
    /**
     * if动态语句
     * @param user 查询用户
     * @return
     */
    List<User> getUserByConditionIf(User user);

    /**
     * where动态语句
     * @param user 查询用户
     * @return
     */
    List<User> getUserByConditionWhere(User user);

    /**
     * trim动态语句
     * @param user 查询用户
     * @return
     */
    List<User> getUserByConditionTrim(User user);

    /**
     * choose、when、otherwise动态语句
     * @param user 查询用户
     * @return 返回用户
     */
    List<User> getUserByConditionCWO(User user);

    /**
     * sql语句
     * @return 所有用户
     */
    List<User> getUserByConditionSql();

    int updateUser(User user);
    /**
     * 批量删除
     * @param ids 所有用户
     * @return 返回表中修改的条数
     */
    int deleteMoreByArray(@Param("ids") Integer[] ids);

    /**
     * 批量添加
     * @param users 需要的用户
     * @return 返回表中修改的条数
     */
    int insertMoreByList(@Param("users") List<User> users);
}
