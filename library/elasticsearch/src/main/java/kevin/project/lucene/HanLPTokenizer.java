package kevin.project.lucene;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.util.List;

public class HanLPTokenizer extends Tokenizer {
    private List<Term> tokens;
    private int position;
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
    private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class);

    public HanLPTokenizer() {
        super();
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[1024];
        int numCharsRead;
        while ((numCharsRead = input.read(buffer)) != -1) {
            sb.append(buffer, 0, numCharsRead);
        }

        String inputText = sb.toString();
        this.tokens = HanLP.segment(inputText);
        this.position = 0;
        // Print the segmentation results
        printSegmentationResults(inputText);
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (position >= tokens.size()) {
            return false;
        }

        clearAttributes();
        Term term = tokens.get(position);
        termAtt.append(term.word);
        offsetAtt.setOffset(term.offset, term.offset + term.word.length());
        typeAtt.setType(term.nature.toString());
        position++;

        return true;
    }

    // Method to print segmentation results
    private void printSegmentationResults(String inputText) {
        System.out.println("Original text: " + inputText);
        System.out.println("Segmentation results:");
        for (Term term : tokens) {
            System.out.printf("Word: %-10s\tNature: %-10s\tOffset: %d\n",
                    term.word, term.nature, term.offset);
        }
        System.out.println("----------------------------------");
    }
}