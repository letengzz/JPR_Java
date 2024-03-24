package com.hjc.demo.mapper;

import com.hjc.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper映射
 * @author hjc
 */
public interface UserMapper {
    //返回值：int (获取受影响的行数)、void (不获取返回值)

    /**
     * 添加用户(只添加用户名)
     *
     * @param username 用户名
     * @return SQL受影响行数
     */
    int insertUserForName(String username);

    /**
     * 多个参数时使用Map传递参数
     *
     * @param params 存放数据的map集合
     * @return SQL受影响行数
     */
    int insertUserByMap(Map<String, Object> params);

    /**
     * 多个参数时使用注解传递参数
     * @param username 账号
     * @param password 密码
     * @param age 年龄
     * @return SQL受影响行数
     */
    int insertUserByParam(@Param("username") String username, @Param("password") String password, @Param("age") Integer age);

    /**
     * 多个参数时使用Bean传递参数
     * @param user Bean对象
     * @return SQL受影响行数
     */
    int insertUserByBean(User user);

    /**
     * 主键(自动递增)回填
     *
     * @param user Bean对象
     * @return SQL受影响行数
     */
    int insertUserForId(User user);

    /**
     * 自定义主键
     *
     * @param user Bean对象
     * @return SQL受影响行数
     */
    int insertUserByCustom(User user);
    /**
     * 通过名字更改密码为123123
     *
     * @param username 用户名
     * @return SQL受影响行数
     */
    int updateUserForName(String username);

    /**
     * 多个参数时使用Map传递参数
     *
     * @param params 存放数据的map集合
     * @return SQL受影响行数
     */
    int updateUserByMap(Map<String, Object> params);

    /**
     * 多个参数时使用注解传递参数
     *
     * @param username 账号
     * @param password 密码
     * @param age      年龄
     * @param id       id
     * @return SQL受影响行数
     */
    int updateUserByParam(@Param("username") String username, @Param("password") String password, @Param("age") Integer age, @Param("id") Integer id);

    /**
     * 多个参数时使用Bean传递参数
     *
     * @param user Bean对象
     * @return SQL受影响行数
     */
    int updateUserByBean(User user);

    /**
     * 删除用户(通过用户名删除)
     *
     * @param username 用户名
     * @return SQL受影响行数
     */
    int deleteUserForName(String username);

    /**
     * 多个参数时使用Map传递参数
     *
     * @param params 存放数据的map集合
     * @return SQL受影响行数
     */
    int deleteUserByMap(Map<String, Object> params);

    /**
     * 多个参数时使用注解传递参数
     *
     * @param username 账号
     * @param password 密码
     * @return SQL受影响行数
     */
    int deleteUserByParam(@Param("username") String username, @Param("password") String password);

    /**
     * 多个参数时使用Bean传递参数
     *
     * @param user Bean对象
     * @return SQL受影响行数
     */
    int deleteUserByBean(User user);


    /**
     * 通过用户名查询
     *
     * @param username 用户名
     * @return User 返回数据
     */
    User selectUserForName(String username);

    /**
     * 多个参数时使用Map传递参数
     *
     * @param params 存放数据的map集合
     * @return SQL受影响行数
     */
    List<User> selectUserByMap(Map<String, Object> params);

    /**
     * 多个参数时使用注解传递参数
     *
     * @param username 账号
     * @param password 密码
     * @return SQL受影响行数
     */
    List<User> selectUserByParam(@Param("username") String username, @Param("password") String password);

    /**
     * 多个参数时使用Bean传递参数
     *
     * @param user Bean对象
     * @return SQL受影响行数
     */
    List<User> selectUserByBean(User user);
}