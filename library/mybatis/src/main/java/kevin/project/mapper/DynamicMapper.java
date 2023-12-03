package kevin.project.mapper;

import kevin.project.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.cursor.Cursor;

import java.awt.*;
import java.util.Map;
import java.util.Objects;

public interface DynamicMapper {

    int executeInsert(String sql);

    Map<String, Object> selectBlog();

    Cursor<User> selectBlogUser();
}
