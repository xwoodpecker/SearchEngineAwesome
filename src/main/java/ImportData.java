import java.io.File;
import java.util.List;

/**
 * Information Retrieval - Programming Assignment 1
 * Julian Altmeyer - 3790940
 * David Kiefer - 3790398
 * Maverick Studer - 3790185
 */
public class ImportData {
    /**
     * Testing the functionality
     * Replace the directory to test importer and you can check the parsed files in debug
     */
    public static void main(String[] args) {

        String directory;
        directory = "./nyt/data/2000/01/01";

        //Importer importer = new Importer();
        //File fileDirectory = new File(directory);
        //importer.importFile(fileDirectory);

        InvertedIndex invertedIndex = new InvertedIndex();
        String term = "you";
        int df;
        int size;
        long did = 1165027;
        int lenght;
        df = invertedIndex.getDF(term);
        List<Posting> indexList;
        indexList = invertedIndex.getIndexList(term);

        size = invertedIndex.getSize();

        lenght = invertedIndex.getLength(did);

        System.out.println(df);
        System.out.println(size);
        System.out.println(lenght);
        for (Posting posting: indexList){
            System.out.println(posting.toString());
        }
    }
}
