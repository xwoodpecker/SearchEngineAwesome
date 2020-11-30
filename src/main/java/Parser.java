import org.jsoup.Jsoup;

import java.io.File;

public class Parser {
    NYTCorpusDocumentParser nytCorpusDocumentParser = new NYTCorpusDocumentParser();

    public Document parse(File file){
        NYTCorpusDocument nytCorpusDocument = nytCorpusDocumentParser.parseNYTCorpusDocumentFromFile(file, true);
        Document document = new Document();
        document.setId(Long.valueOf(nytCorpusDocument.getGuid()));
        document.setTitle(nytCorpusDocument.getHeadline());
        document.setContent(tokenize(nytCorpusDocument.getBody()));
        document.setUrl(nytCorpusDocument.getUrl().toString());
        return document;
    }

    private String[] tokenize(String body) {
        String text = Jsoup.parse(body).text();
        //only keep . and - as full stops
        text.replaceAll("[^A-Za-z0-9\\.-]", " ");
        //discard full stops that are not in the middle of a word
        text.replaceAll("\\s[\\.|-]|[\\.|-]\\s", " ");
        text.toLowerCase();
        return text.split("\\s+");
    }


}
