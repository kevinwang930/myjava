package kevin.project.mongo;

import kevin.project.myspring.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @ClassName MongoController
 * @Description TODO
 * @Date 9/28/24
 **/
@RestController
@RequestMapping("/mongo")
@Slf4j
public class MongoController {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping
    public Person mongoGet() {
        return clientRepo.findByName("test");
    }

    @GetMapping("/template")
    public Person mongoTemplateGet() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name")
                                  .is("test"));
        return mongoTemplate.findOne(query, Person.class);
    }

    @PutMapping("/template")
    public void mongoTemplatePut() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name")
                                  .is("test"));
        Update update = new Update();
        update.set("age", 70);
        mongoTemplate.upsert(query, update, Person.class);
    }

    @PutMapping("/repo")
    public void mongoRepoPut() {
        clientRepo.updatePersonAgeByName("test", 80);
    }
    
}
