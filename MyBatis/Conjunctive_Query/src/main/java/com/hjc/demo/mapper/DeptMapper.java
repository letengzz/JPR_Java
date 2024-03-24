package com.hjc.demo.mapper;

import com.hjc.demo.pojo.Department;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    /**
     * 通过分步查询查询员工以及员工所对应的部门信息
     * 分布查询第二步:通过did查询员工所对应的部门
     * @param did 员工id
     * @return 员工信息
     */
    Department getEmpAndDeptByStepTwo(@Param("did") Integer did);

    /**
     * 获取部门以及部门中所有的员工信息
     * 两表联查
     * @param did 部门id
     * @return 部门信息
     */
    Department getDeptAndEmp(@Param("did") Integer did);

    /**
     * 通过分步查询查询部门以及部门中所有的员工信息
     * 分布查询第一步：查询部门信息
     * @param did 部门id
     * @return 部门信息
     */
    Department getDeptAndEmpByStepOne(@Param("did") Integer did);
}
