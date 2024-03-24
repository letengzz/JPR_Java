import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjc.demo.mapper.UserMapper;
import com.hjc.demo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    /**
     * MyBatis的分页插件:
     *  1.添加依赖
     *  2.在核心配置文件中配置分页插件
     *  3.在查询功能之前开启分页功能就可以了
     * <p></p>
     * pageNum当前页的页码 pageSize每页显示的条数  total数据条数
     * startRow从第几行开始 endRow从第几行结束 pages一共多少页
     * navigate导航分页的页码数(一般都是奇数)
     * list就是获取的page
     * pageNum当前页码 pagesize每页显示的条数 size当前页的真实程度：当前页显示的真实数量
     * startRow从几行开始 endRow从几行结束 total总条数 pages总页数
     * prepage上一页 nextPage下一页  isFristPage是否是第一页 isLastPage是否是最后一页
     * hasPreviousPage是否有上一页 hasNextPage是否有下一页
     * navigatepageNums导航分页的页码
     */

    @Test
    public void testPagingLimit() throws IOException {
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

        List<User> users = mapper.selectAllUserLimit(1, 3);
        users.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testPaging() throws IOException {
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

        PageHelper.startPage(1,3);
        List<User> users = mapper.selectAllUser();
        //查询之后 获取分页的所有数据
        PageInfo<User> page = new PageInfo<>(users,5);
        System.out.println("page = " + page);
        users.forEach(System.out::println);

        // 关闭SqlSession
        sqlSession.close();
    }
}