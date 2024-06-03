package com.hjc.demo.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.util.ListUtils;
import com.hjc.demo.pojo.Employee;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

/**
 * 简单的写入
 *
 * @author hjc
 */
public class SimpleWrite {
    /**
     * 创建数据
     *
     * @param count 传入几条数据
     * @return list
     */
    private List<Employee> data(int count) {
        List<Employee> list = ListUtils.newArrayList();
        for (int i = 0; i < count; i++) {
            Employee data = new Employee(i, "测试数据" + i, new Date(), 6.6 * i, "忽略字段" + i);
            list.add(data);
        }
        return list;
    }

    /**
     * 简单写入数据
     */
    @Test
    public void write() {
        // 写入文件名
        String fileName = "D:/simpleWrite.xlsx";
        // 调用write方法写入 按照指定的某个class去写
        // excelType(): 指定写入的类型 如xlsx、xls、cvs
        // sheet(): 指定sheet的名字
        // doWrite(): 写入的方法
        EasyExcel.write(fileName, Employee.class).excelType(ExcelTypeEnum.XLSX).sheet("模板").doWrite(data(10));
    }
}
