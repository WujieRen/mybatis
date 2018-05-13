import com.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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
}
