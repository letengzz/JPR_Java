import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicTest {
    /*
     * Mybatis获取参数值的两种方式：${} 和 #{}  (尽量能用#{}不用${})
     * ${}本质是字符串拼接
     * #{}本质是占位符赋值
     * Mybatis获取参数值的各种情况：
     * 1.mapper接口方法的参数为单个的字面量类型
     *  可以通过${}和#{}以任意的名称获取参数值，但是需要注意${}的单引号
     * 2.mapper接口方法的参数为多个的字面量类型
     *  Mybatis会将这些参数放在一个map集合中，以两种方式进行存储
     *      1.以arg0，arg1...为键，以参数为值
     *      2.以param1，param2...为键，以参数为值
     *  通过#{}和${}以键的方式访问值即可
     * 3.若mapper接口方法的参数有多个时，可以手动将这些参数放在一个map中存储
     * 4.mapper接口方法的参数值为实体类类型的参数时，通过#{}和${}以属性的方式访问属性值即可。
     * 5.使用@Param注解命名参数
     *
     */
    @Test
    public void testInsert() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = build.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.insertUserForName("张三");
        System.out.println("共插入" + result + "条数据");
        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testInsertByMap() throws IOException {
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

        //定义map集合 key必须与xml中的#{}一致
        Map<String, Object> map = new HashMap<>();
        map.put("username", "Boss");
        map.put("password", "456456");
        map.put("age", 44);
        //执行插入操作
        int result = mapper.insertUserByMap(map);
        System.out.println("通过 Map 成功向数据库中添加了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testInsertByParam() throws IOException {
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

        int result = mapper.insertUserByParam("Admin", "121212", 78);
        System.out.println("通过注解成功向数据库中添加了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testInsertByBean() throws IOException {
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
        //执行插入操作
        int result = mapper.insertUserByBean(new User(null, "user", "password", 19));
        System.out.println("通过JavaBean成功向数据库中添加了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testInsertForId() throws IOException {
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

        //添加用户信息(除id)
        User user = new User();
        user.setUsername("哈利波特");
        user.setPassword("456566");
        user.setAge(19);
        //执行插入
        int result = mapper.insertUserForId(user);
        System.out.println("添加了 " + result + " 条记录");
        //获取回填的主键
        System.out.println("添加记录的主键是:" + user.getId());

        // 关闭SqlSession
        sqlSession.close();

    }

    @Test
    public void testInsertByCustom() throws IOException {
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

        //添加用户信息(除id)
        User user = new User();
        user.setUsername("陈申");
        user.setPassword("4561566");
        user.setAge(21);
        //执行插入
        int result = mapper.insertUserByCustom(user);
        System.out.println("添加了 " + result + " 条记录");
        //获取回填的主键
        System.out.println("添加记录的主键是:" + user.getId());

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testUpdateForName() throws IOException {
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

        //执行更新
        int result = mapper.updateUserForName("刘备");
        System.out.println("更新了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testUpdateByMap() throws IOException {
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

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("username", "刘邦");
        map.put("password", "49111");
        map.put("age", 30);
        //执行更新
        int result = mapper.updateUserByMap(map);
        System.out.println("通过 Map 成功向数据库中更新了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testUpdateByParam() throws IOException {
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

        //执行更新
        int result = mapper.updateUserByParam("孔刘", "787878", 20, 1);
        System.out.println("通过注解成功向数据库中更新了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testUpdateByBean() throws IOException {
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

        //执行更新
        int result = mapper.updateUserByBean(new User(2, "user", "password", 19));
        System.out.println("通过JavaBean成功向数据库中更新了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testDeleteForName() throws IOException {
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

        //执行删除
        int result = mapper.deleteUserForName("张飞");
        System.out.println("共删除了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testDeleteByMap() throws IOException {
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

        Map<String, Object> map = new HashMap<>();
        map.put("username", "张飞");
        map.put("password", "124356");
        //执行删除
        int result = mapper.deleteUserByMap(map);
        System.out.println("通过 Map 成功向数据库中删除了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testDeleteByParam() throws IOException {
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

        //执行删除
        int result = mapper.deleteUserByParam("张飞", "124356");
        System.out.println("通过注解成功向数据库中删除了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testDeleteByBean() throws IOException {
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

        //执行删除
        int result = mapper.deleteUserByBean(new User(null, "张飞", "124356", 21));
        System.out.println("通过JavaBean成功向数据库中删除了 " + result + " 条记录");

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectForName() throws IOException {
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

        //执行查询操作
        User user = mapper.selectUserForName("关");
        if (user != null) {
            System.out.println("id:" + user.getId() + ",用户名:" + user.getUsername() + ",密码:" + user.getPassword() + ",年龄:" + user.getAge());
        }

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectByMap() throws IOException {
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

        Map<String, Object> map = new HashMap<>();
        map.put("username", "飞");
        map.put("password", "124356");
        //执行查询操作
        List<User> users = mapper.selectUserByMap(map);
        users.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectByParam() throws IOException {
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

        //执行查询操作
        List<User> list = mapper.selectUserByParam("刘", "123");
        list.forEach(System.out::println);


        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectByBean() throws IOException {
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

        //执行查询操作
        List<User> list = mapper.selectUserByBean(new User(null, "刘", "123", null));
        list.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }
}