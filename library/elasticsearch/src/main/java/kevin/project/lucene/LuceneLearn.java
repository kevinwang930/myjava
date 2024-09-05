package kevin.project.lucene;



import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;

public class LuceneLearn {

    public static void main(String[] args) throws Exception {
        // Setup the lucene environment
        HanLPAnalyzer analyzer = new HanLPAnalyzer();
        Directory index = new ByteBuffersDirectory();

        // Indexing part
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);
        addDoc(writer, "Lucene in Action", "193398817");
        addDoc(writer, "Lucene for Dummies", "55320055Z");
        addDoc(writer, "Managing Gigabytes", "55063554A");
        addDoc(writer, "The Art of Computer Science", "9900333X");
        addDoc(writer,"中餐",""+System.currentTimeMillis());
        addDoc(writer,"优衣库更衣室",""+System.currentTimeMillis());
        writer.close();

        // Searching part
        String querystr = args.length > 0 ? args[0] : "优衣";
        Query q = new QueryParser("title", analyzer).parse(querystr);

        // Search
        int hitsPerPage = 10;
        DirectoryReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        ScoreDoc[] hits = searcher.search(q, hitsPerPage).scoreDocs;

        // Display results
        System.out.println("Found " + hits.length + " hits.");
        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("title") + "\t" + hits[i].score + "\t" + d.get("isbn"));
        }
        reader.close();
    }

    private static void addDoc(IndexWriter w, String title, String isbn) throws Exception {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }
}

// Add the dependencies for Lucene in your build system (e.g., Maven or Gradle) to run this code.

