import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MybatisTest {
    //Mybatis框架啊的动态sql技术时一种根据特定条件动态拼装SQL语句的功能

    /*
    判断标签if:根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中
    注意:test必须用到

    解决后接and的问题
         1.where拼接恒成立语句
         2.使用where标签

    如果用where可以动态生成where关键字并且可以识别去掉多余的and/or关键字,如果没有任何内容也将没有where
         注意:where标签不能将其中内容后面多余的and或or去掉
    trim标签:
        prefix/suffix:将trim标签中内容前缀或后面添加指定内容\
        prefixOverrides/suffixOverrides:将trim标签中内容前面或后面去掉指定内容

    choose\when\otherwise,相当于if...else if...else
    when至少要有一个,otherwise最多只能有一个

    foreach标签:
     collection:设置需要循环的数组或集合
     item:表示数组或集合中的每一个数据
     separator:循环体之间的分隔符
     open:foreach标签所循环的所有内容的开始符
     close:foreach标签所循环的所有内容的结束符

     sql标签:
      设置SQL片段:<sql id="stuColumns">id,username,password</sql>
      引用SQL片段:<include refid="stuColumns"/>
         */
    @Test
    public void testIf() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUserByConditionIf(new User(null, "刘备", null, null));
        user.forEach(System.out::println);
    }

    @Test
    public void testWhere() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUserByConditionWhere(new User(null, "刘备", null, null));
        user.forEach(System.out::println);
    }

    @Test
    public void testTrim() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUserByConditionTrim(new User(null, "刘备", null, null));
        user.forEach(System.out::println);
    }

    @Test
    public void testCWO() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUserByConditionTrim(new User(null, "刘备", null, null));
        user.forEach(System.out::println);
    }

    @Test
    public void testSql() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUserByConditionSql();
        user.forEach(System.out::println);
    }

    @Test
    public void testUpdate() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.updateUser(new User(1, "root", "123123", 2));
        System.out.println("result = " + result);
    }

    @Test
    public void testInsert() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = new ArrayList<>();
        users.add(new User(null, "123", "456", 45));
        users.add(new User(null, "123", "456", 45));
        users.add(new User(null, "123", "456", 45));
        int result = mapper.insertMoreByList(users);
        System.out.println("result = " + result);
    }

    @Test
    public void testDelete() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.deleteMoreByArray(new Integer[]{1, 2, 3, 4, 5, 6});
        System.out.println("result = " + result);
    }
}
