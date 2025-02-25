package com.hjc.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.demo.domain.SysConfig;
import com.hjc.demo.service.SysConfigService;
import com.hjc.demo.mapper.SysConfigMapper;
import org.springframework.stereotype.Service;

/**
* @author hjc
* @description 针对表【sys_config(参数配置表 - 对象存储等的配置也可以放在这里)】的数据库操作Service实现
* @createDate 2024-08-04 15:19:12
*/
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig>
    implements SysConfigService{

}




