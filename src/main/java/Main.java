import java.io.File;

public class Main {

    public static void main(String[] args) {

        String directory;
        directory = "C:/Users/julia/Downloads/nyt/nyt/data/2000";

        Importer importer = new Importer();
        File fileDirectory = new File(directory);
        //importer.importFile(fileDirectory);

        Document document;
        File file = new File(directory + "/01/01/1165027.xml");
        Parser parser = new Parser();
        document = parser.parse(file);
        System.out.println(document.toString());


    }
}
