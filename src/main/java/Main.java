import java.io.File;

/**
 * Information Retrieval - Programming Assignment 1
 * Julian Altmeyer - 3790940
 * David Kiefer - 3790398
 * Maverick Studer - 3790185
 */
public class Main {

    /**
     * Testing the functionality
     * Replace the directory to test importer and you can check the parsed files in debug
     */
    public static void main(String[] args) {

        String directory;
        directory = "C:/Users/WoodPecker/IdeaProjects/InformationRetrieval/SearchEngineAwesome/nyt/data/2000/01/01";

        Importer importer = new Importer();
        File fileDirectory = new File(directory);
        importer.importFile(fileDirectory);
    }
}
