import com.mybatis.model.Blog;
import com.mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by renwujie on 2018/05/13 at 22:46
 */
public class TestBlogMapper {
    @Test
    public void getBlogList() {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            List<Blog> blogList =  session.selectList(Blog.class.getName()+".getBlogList");
            for(Blog blog : blogList) {
                //POJO中名字和数据库中名字不对应会产生null值。这种情况下可通过再sql中给别名解决
                System.out.println(blog.getTitleName() + " --- " + blog.getContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSession(session);
        }
    }

    @Test
    public void getBlogUserMapList() {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            List<Blog> blogUserMapList = session.selectList(Blog.class.getName()+".getBlogUserMapList");
            for(Blog blog : blogUserMapList) {
                System.out.println(blog);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSession(session);
        }
    }
}
