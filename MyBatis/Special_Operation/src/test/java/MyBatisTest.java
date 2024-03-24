import com.hjc.demo.mapper.StudentMapper;
import com.hjc.demo.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    /**
     * MyBatis的各种查询功能：
     *  1.若查询出的数据只有一条，可以通过实体类对象、list结合、map集合接收
     *  2.若查询出的数据有多条，可以通过list集合接收，一定不能通过实体类对象接收，此时会抛异常
     *  3.MyBatis中设置了默认的类型别名：java.lang.Integer、Integer、integer、int、Int、_int
     *  4.将数据转换为一个map：如果查询出来的只有一条数据可以直接转换成map、如果是多条数据时，需要在mapper接口的方法上添加@MapKey
     *  此时就可以将每条数据转换的map集合作为值，以某个字段的值作为键，放在同一个map集合中
     */
    @Test
    public void testSelectByAlias() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建UserMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        //执行查询
        List<Student> students = mapper.selectAllByAlias();
        students.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectByResultMap() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        //执行查询
        List<Student> students = mapper.selectAllByResultMap();
        students.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectBySettings() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //执行查询
        List<Student> students = mapper.selectAllBySettings();
        students.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testDeleteMore1() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //执行批量添加
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setsName("stu"+i);
            student.setAge(i);
            student.setSex("男");
            mapper.insertOnBatch1(student);
        }

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testDeleteMore2() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //执行批量添加
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setsName("stu"+i);
            student.setAge(i);
            student.setSex("男");
            list.add(student);
        }
        mapper.insertOnBatch2(list);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testInsert3() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交
        SqlSession sqlSession = build.openSession(ExecutorType.BATCH,false);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        int BATCH = 1000;
        for (int i = 0; i < BATCH; i++) {
            Student student = new Student();
            student.setsName("stu"+i);
            student.setAge(i);
            student.setSex("男");
            mapper.insertOnBatch1(student);
            if (i != 0 && i % 100 == 0) {
                sqlSession.commit();
            }
        }
        sqlSession.commit();

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectByLike() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        //执行查询
        List<Student> students = mapper.selectStuByLike("小");
        students.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testDeleteMore() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        //执行批量删除
        int result = mapper.deleteMore("1,2,3");
        System.out.println("result = " + result);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectByTable() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //查询指定表中的数据
        List<Student> students = mapper.getAllStu("student");
        students.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testOneQueryToBean() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //查询数据
        Student student = mapper.selectById(1);
        System.out.println("student = " + student);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testOneQueryToList() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //查询数据
        List<Student> students = mapper.selectByIdToList(1);
        students.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testOneQueryToMap() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //查询数据
        Map<String, Object> map = mapper.getStuByIdToMap(1);
        System.out.println("map = " + map);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testCount() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //查询总记录数
        Integer count = mapper.getCount();
        System.out.println("count = " + count);

        // 关闭SqlSession
        sqlSession.close();
    }
    @Test
    public void testAllQueryToList() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //查询数据
        List<Student> students = mapper.selectAllToList();
        students.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testAllQueryToMap() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //查询数据
        Map<Integer, Object> map = mapper.getStuAllToMap();
        System.out.println("map = " + map);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testAllQueryToListMap() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建StudentMapper接口的代理实现类对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //查询数据
        List<Map<String, Object>> stu = mapper.getAllStuToMap();
        stu.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }
}