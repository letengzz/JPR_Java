import com.hjc.demo.mapper.EmpMapper;
import com.hjc.demo.pojo.Department;
import com.hjc.demo.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    @Test
    public void testInsertToParam() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        int result = mapper.insertEmpToParam("刘备", 10.1);
        System.out.println("result = " + result);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testInsertToBean() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee employee = new Employee();
        employee.setEmpName("刘备");
        employee.setEmpSalary(10.1);
        employee.setDeptId(1);
        int result = mapper.insertEmpToBean(employee);
        System.out.println("result = " + result);

        //关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testInsertToSelectKey() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee emp = new Employee();
        emp.setEmpName("刘备");
        emp.setEmpSalary(10.1);
        emp.setDeptId(1);
        mapper.insertEmpToKey(emp);
        System.out.println(emp.getEmpId());

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        mapper.updateEmp("钟无艳",10.1,1);

        // 关闭SqlSession
        sqlSession.close();
    }
    @Test
    public void testDelete() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        int result = mapper.deleteEmp(5);
        System.out.println("result = " + result);

        // 关闭SqlSession
        sqlSession.close();
    }
    @Test
    public void testSelect() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        List<Employee> employees = mapper.selectAllEmp();
        employees.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }
    @Test
    public void testSelectAllToMap() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        Map<Integer, Object> maps = mapper.selectAllEmpToMap();
        System.out.println("map = " + maps);

        // 关闭SqlSession
        sqlSession.close();
    }
    @Test
    public void testSelectAll() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        List<Employee> employees = mapper.selectAllByResults();
        employees.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectAllEmp() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        List<Employee> allEmp = mapper.getAllEmp();
        allEmp.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }
    @Test
    public void testSelectEmpDept() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        List<Employee> empDeptOne = mapper.getEmpDeptOne();
        System.out.println("empDeptOne = " + empDeptOne);

        // 关闭SqlSession
        sqlSession.close();

    }
    @Test
    public void testSelectDeptEmp() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        //通过代理模式创建EmpMapper接口的代理实现类对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        List<Department> deptEmpOne = mapper.getDeptEmpOne();
        System.out.println("deptEmpOne = " + deptEmpOne);

        // 关闭SqlSession
        sqlSession.close();
    }
}