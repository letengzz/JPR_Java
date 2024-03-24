package com.hjc.demo;

import com.hjc.demo.entity.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class JDBCTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * JDBCTemplate 增加操作
     */
    @Test
    void testInsert() {
        String sql = "insert into t_emp values(null,?,?,?)";
        int result = jdbcTemplate.update(sql, "张三", 23, "男");
        System.out.println("result = " + result);
    }
    /**
     * JDBCTemplate 删除操作
     */
    @Test
    void testDelete() {
        String sql = "delete from t_emp where id = ?";
        int result = jdbcTemplate.update(sql, 1);
        System.out.println("result = " + result);
    }

    /**
     * JDBCTemplate 更改操作
     */
    @Test
    void testUpdate() {
        String sql = "update t_emp set name=? where id=?";
        int result = jdbcTemplate.update(sql, "李四",2);
        System.out.println("result = " + result);
    }

    /**
     * 查询返回对象
     */
    @Test
    void testSelectObj() {
        String sql = "select * from t_emp where id=?";
        //todo 写法一
        Emp emp1 = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Emp emp = new Emp();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setAge(rs.getInt("age"));
            emp.setSex(rs.getString("sex"));
            return emp;
        }, 2);
        System.out.println("emp1 = " + emp1);
        //todo 写法二
        Emp emp = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class), 2);
        System.out.println("emp = " + emp);
    }

    /**
     * 查询返回list
     */
    @Test
    void testSelectList() {
        String sql = "select * from t_emp";
        List<Emp> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
        System.out.println(list);
    }

    /**
     * 查询单行单列的值
     */
    @Test
    public void selectCount(){
        String sql = "select count(id) from t_emp";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}