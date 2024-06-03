package com.hjc.demo.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.hjc.demo.dao.EmployeeDao;
import com.hjc.demo.listener.EmployeeListener;
import com.hjc.demo.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 简单的读取
 * @author hjc
 */
@Slf4j
public class CustomRead {
    @Test
public void read(){
    // 读入文件名
    String fileName = "D:/templateWrite.xlsx";
    // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
    // 指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
    // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
    ExcelReader reader = EasyExcel.read(fileName, Employee.class, new EmployeeListener(new EmployeeDao())).build();
    ReadSheet sheet = EasyExcel.readSheet().build();
    reader.read(sheet);
}
}
