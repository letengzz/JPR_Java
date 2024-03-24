import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {
    @Test
    public void testSelectCompany() throws IOException {

        // 1.创建SqlSessionFactory对象
        // ①声明Mybatis全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";

        // ②以输入流的形式加载Mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);

        // ③基于读取Mybatis配置文件的输入流创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用SqlSessionFactory对象开启一个会话
        SqlSession session = sessionFactory.openSession();

        // 3.根据Mapper配置文件的名称空间+SQL语句的id找到具体的SQL语句
        // 格式是：名称空间.SQL语句的id
        String statement = "com.hjc.demo.mapper.CompanyMapper.selectCompany";

        // 要传入SQL语句的参数
        Integer id = 1;

        // 执行SQL语句
        Object result = session.selectOne(statement, id);

        System.out.println("查询结果:" + result);

        // 4.关闭SqlSession
        session.close();
    }
}