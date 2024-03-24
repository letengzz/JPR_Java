import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import com.hjc.demo.pojo.UserExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicTest {
    /*@Test
    public void testMyBatis3Simple() throws IOException {
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

        //添加功能
        int result = mapper.insert(new User(null, "李白", "123456", 19));
        System.out.println("result = " + result);

        //根据主键删除
        mapper.deleteByPrimaryKey(2);

        //查询所有
        List<User> users = mapper.selectAll();
        users.forEach(System.out::println);

        //根据主键查询
        User user = mapper.selectByPrimaryKey(1);
        System.out.println("user = " + user);

        //根据主键更改
        int result2 = mapper.updateByPrimaryKey(new User(1, "李白", "123456", 19));
        System.out.println("result2 = " + result2);

        sqlSession.close();
    }*/
    @Test
    public void testMyBatis3() throws IOException {
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

        // 1.创建EmployeeExample对象
        UserExample example = new UserExample();

        // 2.通过example对象创建Criteria对象
        UserExample.Criteria criteria01 = example.createCriteria();
        UserExample.Criteria criteria02 = example.or();

        // 3.在Criteria对象中封装查询条件
        criteria01
                .andAgeBetween(15,99)
                .andUsernameLike("%李%")
                .andPasswordEqualTo("123456");
        criteria02
                .andAgeBetween(0,20)
                .andUsernameLike("%刘%")
                .andPasswordEqualTo("123123");


        // 4.基于Criteria对象进行查询
        List<User> tempList = mapper.selectByExample(example);

        for (User user : tempList) {
            System.out.println("user = " + user);
        }

        sqlSession.close();

        // 最终SQL的效果：
        // WHERE ( age between ? and ? and username like ? and password = ? ) or( age between ? and ? and username like ? and password = ? )

        User user = new User(null,"李白",null,10);
        //添加:属性是null 执行直接赋值为null
        int insert = mapper.insert(user);
        System.out.println("insert = " + insert);
        //添加:不会将null赋值添加到字段的 只会将不为空赋值
        int selective = mapper.insertSelective(user);
        System.out.println("selective = " + selective);

        //根据主键删除
        int deleteByPrimaryKey = mapper.deleteByPrimaryKey(5);
        System.out.println("deleteByPrimaryKey = " + deleteByPrimaryKey);
        //根据条件删除:年龄等于十岁的或id大于30的
        //两个sql语句通过or来连接
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAgeEqualTo(10);
        userExample.or().andIdGreaterThan(30);
        mapper.deleteByExample(userExample);


        User user1 = new User(2,"嬴政",null,null);
        //根据主键更改:属性是null 执行直接赋值为null
        mapper.updateByPrimaryKey(user1);
        //根据主键更改:不会将null赋值添加到字段的 只会将不为空赋值
        mapper.updateByPrimaryKeySelective(user1);

        User user2 = new User(1,"冯巩",null,null);
        UserExample userExample1 = new UserExample();
        userExample1.createCriteria().andPasswordIsNotNull().andIdIn(Arrays.asList(21,22,23));

        //根据条件更改:属性是null 执行直接赋值为null
        mapper.updateByExample(user2,userExample1);

        //根据条件更改:不会将null赋值添加到字段的 只会将不为空赋值
        mapper.updateByExampleSelective(user2,userExample1);

        //根据主键查询
        User eig1 = mapper.selectByPrimaryKey(1);
        System.out.println("eig1 = " + eig1);

        //根据条件查询
        UserExample userExample2 = new UserExample();
        userExample2.createCriteria().andIdLessThan(50);
        List<User> users = mapper.selectByExample(userExample2);
        users.forEach(System.out::println);

        //根据条件查询总数:名字带有李且年龄是0-60的总人数
        UserExample userExample3 = new UserExample();
        userExample3.createCriteria().andUsernameLike("%李%").andAgeBetween(0,60);
        int result = (int) mapper.countByExample(userExample3);
        System.out.println("result = " + result);

        sqlSession.close();
    }
}
