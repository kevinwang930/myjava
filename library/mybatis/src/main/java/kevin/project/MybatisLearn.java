package kevin.project;

import kevin.project.mapper.DynamicMapper;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class MybatisLearn {

    public void sessionFactoryXmlLearn() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
         SqlSession sqlSession = sqlSessionFactory.openSession();
         try {
             DynamicMapper mapper = sqlSession.getMapper(DynamicMapper.class);
             mapper.executeInsert("INSERT INTO `blog_db`.`user` (`name`, `email`, `password`) VALUES ('kevin', 'letian@111.com', '123')");
         } finally {
             sqlSession.commit();
             sqlSession.close();
         }
    }

    public void selectLearn() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
         SqlSession sqlSession = sqlSessionFactory.openSession();
         try {
             DynamicMapper mapper = sqlSession.getMapper(DynamicMapper.class);
             Map<String,Object> maps =  mapper.selectBlog();
             System.out.println(maps.get("name"));
         } finally {
             sqlSession.close();
         }
    }

    public void cursorLearn() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
         SqlSession sqlSession = sqlSessionFactory.openSession();
         try {
             DynamicMapper mapper = sqlSession.getMapper(DynamicMapper.class);
             Cursor<User> cursor =  mapper.selectBlogUser();
             Map<String,Object> maps =  mapper.selectBlog();
             mapper.executeInsert("INSERT INTO `blog_db`.`user` (`name`, `email`, `password`) VALUES ('kevin1', 'letian1@111.com', '1234')");
             Iterator<User> users = cursor.iterator();

             while (users.hasNext()) {

                 System.out.println(users.next().getEmail());
             }
             System.out.println(maps.get("name"));
             cursor.close();
         } finally {
             sqlSession.close();
         }
    }
    public static void main(String[] args) throws IOException {
        MybatisLearn mybatisLearn = new MybatisLearn();
        mybatisLearn. sessionFactoryXmlLearn();
        mybatisLearn.selectLearn();
        mybatisLearn.cursorLearn();
    }
}