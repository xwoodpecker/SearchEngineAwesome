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

        System.out.print(df);
        System.out.print(size);
        System.out.print(lenght);
        for (Posting posting: indexList){
            System.out.println(posting.getDid() + " " + posting.getTf());
        }
    }
}
