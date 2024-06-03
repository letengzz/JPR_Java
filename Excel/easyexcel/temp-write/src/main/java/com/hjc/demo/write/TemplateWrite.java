package com.hjc.demo.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hjc.demo.pojo.Employee;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

/**
 * 简单的写入
 *
 * @author hjc
 */
public class TemplateWrite {
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
        String fileName = "D:/templateWrite.xlsx";
        // 模板文件
        String templateFileName = "D:/template.xlsx";
        // 分多次 填充 会使用文件缓存（省内存）
        try (ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build()) {
            long t1 = System.currentTimeMillis();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            for (int i = 0; i < 100; i++) {
                excelWriter.fill(data(1000), writeSheet);
            }
            long t2 = System.currentTimeMillis();
            System.out.println(t2 - t1);
        }
    }
}
