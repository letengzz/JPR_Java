import com.hjc.demo.mapper.DeptMapper;
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

public class MybatisTest {
    @Test
    public void testGetAllEmp() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);

        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Employee> allEmp = mapper.getAllEmp();
        allEmp.forEach(System.out::println);
    }

    @Test
    public void testGetEmpAndDept() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee empAndDept = mapper.getEmpAndDept(1);
        System.out.println("员工名字:" + empAndDept.getEmpName() + ",员工工资:" + empAndDept.getEmpSalary() + ",员工所在部门:" + empAndDept.getDept().getDeptName() + ",部门地点:" + empAndDept.getDept().getDeptCity());
    }

    @Test
    public void testGetEmpAndDeptByStep() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee empAndDeptByStepOne = mapper.getEmpAndDeptByStepOne(1);
        System.out.println("员工名字:" + empAndDeptByStepOne.getEmpName() + ",员工工资:" + empAndDeptByStepOne.getEmpSalary() + ",员工所在部门:" + empAndDeptByStepOne.getDept().getDeptName() + ",部门地点:" + empAndDeptByStepOne.getDept().getDeptCity());

        //延迟加载 只获取name
        System.out.println(empAndDeptByStepOne.getEmpName());
    }

    @Test
    public void testGetDeptAndEmp() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        //通过两表联查(collection)
        Department deptAndEmp = mapper.getDeptAndEmp(1);
        System.out.println("部门名:" + deptAndEmp.getDeptName() + ",部门城市:" + deptAndEmp.getDeptCity());
        System.out.println("员工信息:");
        deptAndEmp.getEmployees().forEach((employee) -> {
            System.out.println("员工名:"+employee.getEmpName()+",员工工资:"+employee.getEmpSalary());
        });

    }

    @Test
    public void testGetDeptAndEmpByStep() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = build.openSession(true);
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        //分步查询
        Department deptAndEmpByStepOne = mapper.getDeptAndEmpByStepOne(1);
        System.out.println("部门名:" + deptAndEmpByStepOne.getDeptName() + ",部门城市:" + deptAndEmpByStepOne.getDeptCity());
        System.out.println("员工信息:");
        deptAndEmpByStepOne.getEmployees().forEach((employee) -> {
            System.out.println("员工名:"+employee.getEmpName()+",员工工资:"+employee.getEmpSalary());
        });
    }

}
