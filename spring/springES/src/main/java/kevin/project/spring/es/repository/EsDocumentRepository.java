package kevin.project.spring.es.repository;

import kevin.project.spring.es.model.EsDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsDocumentRepository extends ElasticsearchRepository<EsDocument, String> {
    List<EsDocument> findByTitleContainingOrContentContaining(String title, String content);
}
