package kevin.project.spring.es.controller;

import kevin.project.spring.es.model.EsDocument;
import kevin.project.spring.es.repository.EsDocumentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/es")
public class EsController {

    private final EsDocumentRepository repository;

    public EsController(EsDocumentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public EsDocument add(@RequestBody AddRequest request) {
        String id = request.getId();
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
        EsDocument document = new EsDocument(id, request.getTitle(), request.getContent(), Instant.now());
        return repository.save(document);
    }

    @GetMapping("/search")
    public List<EsDocument> search(@RequestParam("q") String query) {
        return repository.findByTitleContainingOrContentContaining(query, query);
    }

    public static class AddRequest {
        private String id;
        private String title;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
