package com.hjc.demo.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.hjc.demo.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 简单的读取
 * @author hjc
 */
@Slf4j
public class SimpleRead {
    @Test
    public void read(){
        // 读入文件名
        String fileName = "D:/simpleWrite.xlsx";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, Employee.class, new PageReadListener<Employee>(dataList -> {
            for (Employee demoData : dataList) {
                log.info("读取到一条数据{}", demoData);
            }
        })).sheet().doRead();
    }
}
