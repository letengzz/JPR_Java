package com.hjc.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.demo.domain.SysUserPost;
import com.hjc.demo.service.SysUserPostService;
import com.hjc.demo.mapper.SysUserPostMapper;
import org.springframework.stereotype.Service;

/**
* @author hjc
* @description 针对表【sys_user_post(用户和岗位关联表)】的数据库操作Service实现
* @createDate 2024-08-04 15:19:13
*/
@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost>
    implements SysUserPostService{

}




