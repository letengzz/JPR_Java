package com.hjc.demo.mapper;

import com.hjc.demo.pojo.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper映射
 * @author hjc
 */
public interface StudentMapper {
    /**
     * 通过Sql起别名来解决映射问题
     * @return 所有数据
     */
    List<Student> selectAllByAlias();

    /**
     * 通过resultMap来解决映射问题
     * @return 所有数据
     */
    List<Student> selectAllByResultMap();

    /**
     * 通过设置mapUnderscoreToCamelCase来解决映射问题
     * @return 所有数据
     */
    List<Student> selectAllBySettings();

    /**
     * 普通插入
     * @param student
     * @return
     */
    Integer insertOnBatch1(Student student);

    /**
     * foreach插入
     * @param stus
     * @return
     */
    Integer insertOnBatch2(@Param("stus") List<Student> stus);

    /**
     * 模糊查询
     *
     * @param name 需要模糊查询的名字
     * @return 符合条件的字段
     */
    List<Student> selectStuByLike(String name);
    /**
     * 批量删除
     * @param ids 需要删除的id
     * @return 删除的条数
     */
    int deleteMore(@Param("ids") String ids);

    /**
     * 动态设置表名，查询所有的用户信息
     *
     * @param tableName 需要查询的表
     * @return 表中的所有数据
     */
    List<Student> getAllStu(@Param("tableName") String tableName);

    /**
     * 根据id查询用户信息(查询出的数据只有一条)
     * @param id 根据id查询
     * @return 返回一个表中信息
     */
    Student selectById(@Param("id") Integer id);

    /**
     * 根据id查询用户信息(查询出的数据只有一条，通过list接收)
     * @param id 根据id查询
     * @return 返回一个表中信息
     */
    List<Student> selectByIdToList(@Param("id") Integer id);

    /**
     * 根据id查询用户信息为一个map集合
     * @param id 需要查询数据的id
     * @return 所匹配的数据
     */
    @MapKey("id")
    Map<String,Object> getStuByIdToMap(@Param("id") Integer id);

    /**
     * 查询总记录数
     * @return 总记录条数
     */
    Integer getCount();

    /**
     * 通过list接收所有表中信息
     * @return 返回所有表中信息
     */
    List<Student> selectAllToList();

    /**
     * 查询用户信息为一个map集合
     * @return 所匹配的数据
     */
    @MapKey("id")
    Map<Integer,Object> getStuAllToMap();

    /**
     * 将表中所有的数据以map的方式存入到list集合中
     * @return 返回以id为key 把转换成的map集合作为值
     */
    @MapKey("id")
    List<Map<String,Object>> getAllStuToMap();
}