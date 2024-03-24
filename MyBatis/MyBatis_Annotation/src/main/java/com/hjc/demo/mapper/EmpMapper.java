package com.hjc.demo.mapper;

import com.hjc.demo.pojo.Department;
import com.hjc.demo.pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * mapper接口
 * @author hjc
 */
public interface EmpMapper {

    /**
     * 通过普通参数 注解添加用户
     *
     * @param empName   姓名
     * @param empSalary 工资
     * @return 表中受影响条数
     */
    @Insert("insert into t_emp(emp_name, emp_salary) values (#{empName},#{empSalary})")
    int insertEmpToParam(@Param("empName") String empName, @Param("empSalary")Double empSalary);

    /**
     * 通过Bean 注解添加用户
     *
     * @param emp 用户
     * @return 表受影响条数
     */
    @Insert("insert into t_emp(emp_name, emp_salary) values (#{empName},#{empSalary})")
    int insertEmpToBean(Employee emp);

    /**
     * 获取插入的id
     *
     * @param emp 用户
     */
    @Insert("insert into t_emp(emp_name, emp_salary) values (#{emp.empName},#{emp.empSalary})")
    @SelectKey(statement = "SELECT last_insert_id()", keyProperty = "emp.empId",keyColumn = "emp_id",before = false, resultType = Integer.class)
    void insertEmpToKey(@Param("emp") Employee emp);

    /**
     * 更新功能
     *
     * @param empName   名字
     * @param empSalary 工资
     * @param empId     id
     */
    @Update("update t_emp set emp_name=#{empName},emp_salary=#{empSalary} where emp_id = #{empId}")
    void updateEmp(@Param("empName") String empName, @Param("empSalary") Double empSalary, @Param("empId") Integer empId);

    /**
     * 删除功能
     *
     * @param empId id
     * @return 表中受影响的行数
     */
    @Delete("delete from t_emp where emp_id=#{empId}")
    int deleteEmp(Integer empId);

    /**
     * 查询
     *
     * @return 查询的内容
     */
    @Select("select * from t_emp")
    @Results({
            @Result(id = true, column = "emp_id", property = "empId"),
            @Result(column = "emp_name", property = "empName"),
            @Result(column = "emp_salary", property = "empSalary")
    })
    List<Employee> selectAllEmp();


    /**
     * 查询所有数据
     * @return 返回map集合
     */
    @MapKey("empId")
    Map<Integer, Object> selectAllEmpToMap();

    /**
     * 查询所有
     * @return 返回集合
     */
    @Select("select * from t_emp")
    @Results({
            @Result(id = true,column = "emp_id",property = "empId"),
            @Result(column = "emp_name",property = "empName"),
            @Result(column = "emp_salary",property = "empSalary")
    })
    List<Employee> selectAllByResults();

    /**
     * 查询所有
     * @return 所有用户
     */
    @Select("select * from t_emp")
    @ResultMap("empMap")
    List<Employee> getAllEmp();

    /**
     * 查询员工的信息
     * @return 员工信息
     */
    @Select("SELECT * FROM t_emp")
    @Results({
            @Result(id = true,column = "emp_id",property = "empId"),
            @Result(column = "emp_name",property = "empName"),
            @Result(column = "emp_salary",property = "empSalary"),
            @Result(column = "dept_id",property = "dept",one = @One(select = "getEmpDeptTwo"))
    })
    List<Employee> getEmpDeptOne();

    /**
     * 根据id查询部门信息
     * @param id id
     * @return 所需要的部门信息
     */
    @Select("select * from t_dept where dept_id=#{id}")
    @Results({
            @Result(id=true, column="dept_id", property="deptId"),
            @Result(column="dept_name", property="deptName"),
            @Result(column="dept_city", property="deptCity")
    })
    Department getEmpDeptTwo(Integer id);


    /**
     * 查询部门中的员工信息
     * @return 部门及部门中的信息
     */
    @Select("select * from t_dept")
    @Results({
            @Result(id=true, column="dept_id", property="deptId"),
            @Result(column="dept_name", property="deptName"),
            @Result(column="dept_city", property="deptCity"),
            @Result(column = "dept_id",property = "employees",many = @Many(select = "getDeptEmpTwo"))
    })
    List<Department> getDeptEmpOne();

    /**
     * 根据id查询员工信息
     * @param id id
     * @return 所需要的员工信息
     */
    @Select("SELECT * FROM t_emp where dept_id = #{id}")
    @Results({
            @Result(id = true,column = "emp_id",property = "empId"),
            @Result(column = "emp_name",property = "empName"),
            @Result(column = "emp_salary",property = "empSalary"),
    })
    List<Employee> getDeptEmpTwo(Integer id);
}
