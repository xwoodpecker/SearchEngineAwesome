import java.util.List;

public class Test {

    public static void main(String[] args) {

        String term = "you";
        int df;
        int size;
        long did = 1165027;
        int lenght;
        InvertedIndex invertedIndex = new InvertedIndex();
        df = invertedIndex.getDF(term);
        List<Posting> indexList;
        indexList = invertedIndex.getIndexList(term);

        size = invertedIndex.getSize();

        lenght = invertedIndex.getLength(did);

        System.out.println(df);
        System.out.println(size);
        System.out.println(lenght);
        for (Posting posting: indexList){
            System.out.println(posting.getDid() + " " + posting.getTf());
        }
    }
}
