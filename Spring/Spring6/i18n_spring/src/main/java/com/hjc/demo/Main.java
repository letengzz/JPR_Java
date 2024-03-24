package com.hjc.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //传递动态参数，使用数组形式对应{0} {1}顺序
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        Object[] objs = new Object[]{"hjc",dateStr};

        //www.hjc.com为资源文件的key值,
        //objs为资源文件value值所需要的参数,Local.CHINA为国际化为语言
        String strCN=context.getMessage("www.hjc.com", objs, Locale.CHINA);
        String strUS=context.getMessage("www.hjc.com", objs, Locale.US);
        String str=context.getMessage("www.hjc.com", objs, Locale.ENGLISH);
        System.out.println("zh_CN===" + strCN);
        System.out.println("en_US===" + strUS);
        System.out.println("en_US===" + str);
    }
}
