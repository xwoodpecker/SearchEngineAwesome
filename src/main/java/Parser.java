import org.jsoup.Jsoup;
import java.io.File;

public class Parser {
    NYTCorpusDocumentParser nytCorpusDocumentParser = new NYTCorpusDocumentParser();

    /**
     * 1.3 Parsing Documents
     */
    public Document parse(File file) {
        NYTCorpusDocument nytCorpusDocument = nytCorpusDocumentParser.parseNYTCorpusDocumentFromFile(file, false);
        Document document = new Document();
        document.setId(Long.valueOf(nytCorpusDocument.getGuid()));
        document.setTitle(nytCorpusDocument.getHeadline());
        document.setContent(tokenize(nytCorpusDocument.getBody()));
        document.setUrl(nytCorpusDocument.getUrl().toString());
        return document;
    }

    /**
     * 1.4 Tokenization
     */
    private String[] tokenize(String body) {
        try {
            // remove all HTML commands
            String text = Jsoup.parse(body).text();
            // only keep . and - as full stops
            text = text.replaceAll("[^A-Za-z0-9\\.'-]", " ");
            // discard full stops that are not in the middle of a word
            text = text.replaceAll("(\\.|-|'|\\s){2,}", " ");
            text = text.toLowerCase();
            return text.split("\\s+");
        }catch(Exception e){
            return null;
        }
    }
}