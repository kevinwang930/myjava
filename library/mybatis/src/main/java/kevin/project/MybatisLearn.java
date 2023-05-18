package kevin.project;

import kevin.project.mapper.DynamicMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisLearn {

    public void sessionFactoryXmlLearn() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
         SqlSession sqlSession = sqlSessionFactory.openSession();
         try {
             DynamicMapper mapper = sqlSession.getMapper(DynamicMapper.class);
             mapper.executeInsert("INSERT INTO `blog_db`.`user` (`name`, `email`, `password`) VALUES ('kevin', 'letian@111.com', '123')");
         } finally {
             sqlSession.close();
         }
    }
    public static void main(String[] args) throws IOException {
        MybatisLearn mybatisLearn = new MybatisLearn();
        mybatisLearn. sessionFactoryXmlLearn();
    }
}