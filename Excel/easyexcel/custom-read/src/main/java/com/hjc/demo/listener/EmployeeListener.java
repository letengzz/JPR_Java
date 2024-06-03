package com.hjc.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.hjc.demo.dao.EmployeeDao;
import com.hjc.demo.pojo.Employee;

import java.util.ArrayList;


/**
 * 自定义监听器
 * @author hjc
 */
public class EmployeeListener implements ReadListener<Employee> {
    private int count = 100;
    private ArrayList<Employee> list  = new ArrayList<>(count);

    private EmployeeDao dao;

    public EmployeeListener(EmployeeDao dao) {
        this.dao = dao;
    }
    /**
     *  每解析一行数据，都会调用该方法
     * @param employee
     * @param analysisContext
     */
    @Override
    public void invoke(Employee employee, AnalysisContext analysisContext) {
        //将读取到的一行数据添加到集合
        list.add(employee);
        //判断是不是到达换存量
        if(list.size() >= count){
            //操作数据库
            dao.save(list);
            list = new ArrayList<>(count);
        }
    }

    /**
     *  所有数据解析完成了 都会来调用该方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //余下的数据量
        if(!list.isEmpty()){
            //操作数据库
            dao.save(list);
            list = new ArrayList<>(count);
        }
    }
}
