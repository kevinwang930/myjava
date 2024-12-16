package kevin.project.db.mapper;


import kevin.project.db.model.User;

public interface UserMapper {

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User findById(Long id);

}
