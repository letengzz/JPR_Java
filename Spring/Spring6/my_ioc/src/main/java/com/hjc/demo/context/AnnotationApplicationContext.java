package com.hjc.demo.context;

import com.hjc.demo.annotation.Bean;
import com.hjc.demo.annotation.Di;


import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationApplicationContext implements ApplicationContext {

    //存储bean的容器
    private HashMap<Class,Object> beanFactory = new HashMap<>();

    private static String rootPath;

    /**
     * 返回对象
     * @param clazz
     * @return
     */
    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    /**
     * 创建有参数构造，传递包路径，设置包扫描规则 根据包扫描加载bean
     * 当前包及其子包，哪个类有@Bean注解，把这个类通过反射实例化
     * @param basePackage
     */
    public AnnotationApplicationContext(String basePackage) {
        try {
            //todo 把包名中的.替换成 \
            String packageDirName = basePackage.replaceAll("\\.", "\\\\");
            //todo 通过线程获取包的绝对路径
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

            while(dirs.hasMoreElements()){
                URL url = dirs.nextElement();

                String filePath = URLDecoder.decode(url.getFile(),"utf-8");
                //获取包前面路径部分，字符串截取
                rootPath = filePath.substring(0, filePath.length()-packageDirName.length());
                //包扫描
                loadBean(new File(filePath));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //属性注入
        loadDi();
    }

    /**
     * 包扫描过程，实例化
     * @param fileParent
     */
    private  void loadBean(File fileParent) throws Exception {
        //todo 判断当前是否文件夹
        if (fileParent.isDirectory()){
            //todo 获取文件夹里面所有内容
            File[] childrenFiles = fileParent.listFiles();
            //todo 判断文件夹里面为空，直接返回
            if (childrenFiles == null || childrenFiles.length == 0){
                return;
            }
            //todo 如果文件夹里面不为空，遍历文件夹所有内容
            for (File child : childrenFiles) {
                //todo 遍历得到每个File对象，继续判断，如果还是文件夹，递归
                if (child.isDirectory()){
                    //todo 递归
                    loadBean(child);
                }else {
                    //遍历得到File对象不是文件夹，是文件，
                    //todo 得到包路径+类名称部分-字符串截取
                    String pathWithClass = child.getAbsolutePath().substring(rootPath.length()-1);
                    //todo 判断当前文件类型是否为.class
                    if (pathWithClass.contains(".class")){
                        //todo 如果是.class类型，把路径\替换成.  把.class去掉
                        String allName = pathWithClass.replaceAll("\\\\", ".")
                                .replace(".class", "");
                        //todo 判断类上面是否有注解 @Bean，如果有实例化过程
                        //todo 获取类的Class
                        Class<?> clazz = Class.forName(allName);
                        //todo 判断是否为接口
                        if (!clazz.isInterface()){
                            //todo 判断类上面是否有注解 @Bean
                            Bean annotation = clazz.getAnnotation(Bean.class);
                            if(annotation != null) {
                                //todo 实例化
                                Object instance = clazz.getConstructor().newInstance();
                                //todo 把对象实例化之后，放到map集合beanFactory
                                //todo 判断当前类如果有接口，让接口class作为map的key
                                if(clazz.getInterfaces().length>0) {
                                    beanFactory.put(clazz.getInterfaces()[0],instance);
                                } else {
                                    beanFactory.put(clazz,instance);
                                }
                            }
                        }
                    }

                }
            }

        }
    }

    /**
     * 属性注入
     */
    private void loadDi() {
        //TODO 实例化对象在beanFactory的map集合里面
        Set<Map.Entry<Class, Object>> entries = beanFactory.entrySet();
        //todo 遍历beanFactory的map集合
        for (Map.Entry<Class, Object> entry:entries) {
            //todo 获取map集合每个对象（value），每个对象属性获取到
            Object obj = entry.getValue();

            //todo 获取对象Class
            Class<?> clazz = obj.getClass();

            //todo 获取每个对象属性获取到
            Field[] declaredFields = clazz.getDeclaredFields();

            //todo 遍历得到每个对象属性数组，得到每个属性
            for(Field field:declaredFields) {
                //todo 判断属性上面是否有@Di注解
                Di annotation = field.getAnnotation(Di.class);
                if(annotation != null) {
                    //todo 如果私有属性，设置可以设置值
                    field.setAccessible(true);

                    //todo 如果有@Di注解，把对象进行设置（注入）
                    try {
                        field.set(obj,beanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
