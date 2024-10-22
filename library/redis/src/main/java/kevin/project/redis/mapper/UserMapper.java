package kevin.project.redis.mapper;


import kevin.project.redis.bean.User;

public interface UserMapper {

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User findById(Long id);

}