import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MyBatisTest {
    private static Logger logger = Logger.getLogger(MyBatisTest.class);

    @Test
    public void testFirstCache() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建UserMapper接口的代理实现类对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.selectUserById(1);
        logger.debug("使用同一个SqlSession");
        //在同一SqlSession下
        //若在执行两次查询的期间执行了任何一次增删改操作，则一级缓存失效
//        mapper.insertUser(new User(null,"1","2",2));
        //在执行两次查询的期间执行清空缓存，则一级缓存失效
//        sqlSession.clearCache();
        mapper.selectUserById(1);
        System.out.println("--------");

        //使用不同一个SqlSession
        logger.debug("使用不同一个SqlSession");
        SqlSession sqlSession2 = build.openSession(true);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        mapper2.selectUserById(1);
    }

    @Test
    public void testTwoCache() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession1 = build.openSession(true);
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        mapper.selectUserById(1);
        //SqlSession关闭的时候，一级缓存中的内容会被存入二级缓存
        sqlSession1.close();

        logger.debug("在同一SqlSessionFactory下");
        //在同一SqlSessionFactory下
        SqlSession sqlSession2 = build.openSession(true);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        mapper2.selectUserById(1);
        //SqlSession关闭的时候，一级缓存中的内容会被存入二级缓存
        sqlSession1.close();

        logger.debug("不在同一SqlSessionFactory下");
        //不在同一SqlSessionFactory下
        InputStream iss = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build1 = new SqlSessionFactoryBuilder().build(iss);
        SqlSession sqlSession3 = build1.openSession(true);
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
        mapper3.selectUserById(1);
        //SqlSession关闭的时候，一级缓存中的内容会被存入二级缓存
        sqlSession1.close();
    }
}