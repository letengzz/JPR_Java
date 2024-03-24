package com.hjc.demo.mapper;

import com.hjc.demo.pojo.Company;

/**
 * Mapper映射
 * @author hjc
 */
public interface CompanyMapper {
    /**
     * 查询信息
     * @param id id
     * @return 所需要的信息
     */
    Company selectCompany(Integer id);
}