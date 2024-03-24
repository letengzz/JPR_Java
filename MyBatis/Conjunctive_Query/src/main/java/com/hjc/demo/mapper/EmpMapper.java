package com.hjc.demo.mapper;

import com.hjc.demo.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    /**
     * 查询所有的员工信息
     * @return l
     */
    List<Employee> getAllEmp();

    /**
     * 获取员工以及所在部门信息
     * 两表联查
     * @param eid l
     * @return l
     */
    Employee getEmpAndDept(@Param("eid") Integer eid);

    /**
     * 通过分步查询查询员工以及员工所对应的部门信息
     * 分布查询第一步：查询员工信息
     * @param eid 员工id
     * @return 员工信息
     */
    Employee getEmpAndDeptByStepOne(@Param("eid") Integer eid);

    /**
     * 通过分布查询查询部门以及部门中所有的员工信息
     * 分步查询第二步:根据did查询员工信息
     * @param did 员工id
     * @return 员工信息
     */
    List<Employee> getDeptAndEmpByStepTwo(@Param("did") Integer did);
}
