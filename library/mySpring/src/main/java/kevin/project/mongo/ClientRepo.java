package kevin.project.mongo;

import kevin.project.myspring.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version 1.0
 * @ClassName MogoRepository
 * @Description TODO
 * @Date 9/28/24
 **/
@Repository
public interface ClientRepo extends MongoRepository<Person, String> {

    Person findByName(String name);

    @Query("{ 'name' : ?0 }")
    @Transactional
    @Update("{ '$set' : { 'age' : ?1 } }")
    void updatePersonAgeByName(String name, Integer age);
}
