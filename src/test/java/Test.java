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
/*
        System.out.println(df);
        System.out.println(size);
        System.out.println(lenght);
        for (Posting posting: indexList){
            System.out.println(posting.getDid() + " " + posting.getTf());
        }
*/
        String query1 = "olympics opening ceremony";
        String query2 = "denmark sweden bridge";
        String query3 = "tokyo train disaster";
        List<Accumulator> resultQuery1;
        List<Accumulator> resultQuery2;
        List<Accumulator> resultQuery3;
        QueryProcessor queryProcessor = new QueryProcessor();
        resultQuery1 = queryProcessor.process(query1, 5);
        resultQuery2 = queryProcessor.process(query2, 5);
        resultQuery3 = queryProcessor.process(query3, 5);

        System.out.println(query1);
        for(Accumulator accumulator:resultQuery1){
            System.out.println(accumulator.toString());
        }

        System.out.println(query2);
        for(Accumulator accumulator:resultQuery2){
            System.out.println(accumulator.toString());
        }

        System.out.println(query3);
        for(Accumulator accumulator:resultQuery3){
            System.out.println(accumulator.toString());
        }
    }
}
