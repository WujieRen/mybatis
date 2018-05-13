import com.mybatis.util.Pager;
import com.mybatis.model.User;
import com.mybatis.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by renwujie on 2018/05/13 at 10:19
 */
public class TestMyBatis {
    @Test
    public void test01() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "developmentDemo");
        SqlSession session = sqlSessionFactory.openSession();
        User user = new User("renwujie", "rwj", "rwj@eurasia.edu");
        session.insert("com.mybatis.model.User.addUser", user);
        session.commit();
    }

    @Test
    public void getUserCount() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "developmentDemo");
        SqlSession session = sqlSessionFactory.openSession();
        int count = session.selectOne("com.mybatis.model.User.getUserCount");
        //Assert.assertEquals(10, count);//断言
        System.out.println(count);
    }

    @Test
    public void getUserList() {
        //System.out.println(User.class.getName());
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            //TODO:TypeAliases的作用是在.xml间能生效，这儿可以这么用是因为userMapper.xml中的namespace的名字和User的包名相同。
            List<User> userList = session.selectList(User.class.getName()+".getAllUser");
            //List<User> userList = session.selectList(User.class.getName()+"s.getAllUser");
            for(User user : userList) {
                System.out.println(user.getId() + " --- " + user.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           MyBatisUtil.closeSession(session);
        }
    }

    @Test
    public void updateUser() {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            int id = 1;
            //先loaUser
            User user = session.selectOne(User.class.getName()+".loadAUser", id);
            user.setUsername("sixin");
            //再修改
            session.update(User.class.getName()+".updateUser", user);
            session.commit();//不强制commit有时候不生效
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSession(session);
        }
    }

    @Test
    public void deleteUser() {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            int id = 1;
            session.delete(User.class.getName()+".deleteUser", id);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSession(session);
        }
    }

    @Test
    public void getPageUserList() {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            Pager page = new Pager();
            //Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '-5,5' at line 2
            page.setPageIndex(1);//第一次写没给pageIndex值，结果报错找半天。
            page.setOrder("id");
            page.setOrderWay("desc");
            page.setPageSize(5);
            List<User> userPageList = session.selectList(User.class.getName()+".getPageUserList", page);
            //System.out.println(userPageList.size());
            for(User user : userPageList) {
                System.out.println(user.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSession(session);
        }
    }
}
