package com.hjc.demo.service.Imp;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.demo.mapper.ProductMapper;
import com.hjc.demo.pojo.Product;
import com.hjc.demo.service.ProductService;
import org.springframework.stereotype.Service;


/**
 * @author hjc
 */
@DS("slave_1")
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {
}
