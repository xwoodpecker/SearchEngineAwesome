import java.io.File;

public class Parser {
    NYTCorpusDocumentParser nytCorpusDocumentParser = new NYTCorpusDocumentParser();

    public Document parse(File file){
        NYTCorpusDocument nytCorpusDocument = nytCorpusDocumentParser.parseNYTCorpusDocumentFromFile(file, false);
        Document document = new Document();
        document.setId(Long.valueOf(nytCorpusDocument.getGuid()));
        document.setTitle(nytCorpusDocument.getHeadline());
        document.setUrl(nytCorpusDocument.getUrl().toString());
        return document;
    }
}
