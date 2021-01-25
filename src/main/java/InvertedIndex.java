import java.util.List;
import java.util.Map;

public class InvertedIndex {

    private DataBaseHelper dataBaseHelper;

    public InvertedIndex() {
        dataBaseHelper = new DataBaseHelper();
    }


    public List<Posting> getIndexList(String term) {
        return dataBaseHelper.getPostings(term);
    }

    public int getDF(String term){
        return dataBaseHelper.getDF(term);

    }

    public int getSize() {
        return dataBaseHelper.getSizeOfD();

    }

    public int getLength(long did) {
        return dataBaseHelper.getDocumentLength(did);

    }

}
