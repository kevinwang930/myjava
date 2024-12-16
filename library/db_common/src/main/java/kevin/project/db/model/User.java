package kevin.project.db.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private String createdAt;

}
