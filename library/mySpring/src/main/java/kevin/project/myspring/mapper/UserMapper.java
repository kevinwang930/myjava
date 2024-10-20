package kevin.project.myspring.mapper;


import kevin.project.myspring.bean.User;

public interface UserMapper {

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User findById(Long id);

}