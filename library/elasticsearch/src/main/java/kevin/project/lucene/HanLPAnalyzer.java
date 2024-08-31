package kevin.project.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

import java.io.Reader;

public class HanLPAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new HanLPTokenizer();
        return new TokenStreamComponents(tokenizer);
    }

    @Override
    protected Reader initReader(String fieldName, Reader reader) {
        // You can add any preprocessing of the reader here if needed
        return super.initReader(fieldName, reader);
    }
}