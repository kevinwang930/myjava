package kevin.project.swagger.api;

import java.util.List;
import kevin.project.swagger.model.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * A delegate to be called by the {@link UserApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-14T21:54:51.375+08:00")

public interface UserApiDelegate {

    /**
     * @see UserApi#createUser
     */
    ResponseEntity<Void> createUser( User  body);

    /**
     * @see UserApi#createUsersWithArrayInput
     */
    ResponseEntity<Void> createUsersWithArrayInput( List<User>  body);

    /**
     * @see UserApi#createUsersWithListInput
     */
    ResponseEntity<Void> createUsersWithListInput( List<User>  body);

    /**
     * @see UserApi#deleteUser
     */
    ResponseEntity<Void> deleteUser( String  username);

    /**
     * @see UserApi#getUserByName
     */
    ResponseEntity<User> getUserByName( String  username);

    /**
     * @see UserApi#loginUser
     */
    ResponseEntity<String> loginUser( String  username,
         String  password);

    /**
     * @see UserApi#logoutUser
     */
    ResponseEntity<Void> logoutUser();

    /**
     * @see UserApi#updateUser
     */
    ResponseEntity<Void> updateUser( String  username,
         User  body);

}
