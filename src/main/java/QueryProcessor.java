import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryProcessor {
    private InvertedIndex invertedIndex = null;
    private Parser parser = null;

    public QueryProcessor() {
        invertedIndex = new InvertedIndex();
        parser = new Parser();
    }

    public List<Accumulator> process(String query) {
        String[] queryTerms = parser.tokenize(query);

        Map<Long, Accumulator> accumulatorMap = new HashMap<>();
        for(String queryTerm : queryTerms){
            List<Posting> postingList = invertedIndex.getIndexList(queryTerm);
            int df = invertedIndex.getDF(queryTerm);
            int d = invertedIndex.getSize();

            for(Posting posting : postingList)
            {
                long did = posting.getDid();
                int tf = posting.getTf();
                double score = tf * Math.log(((double)d/df));
                if(!accumulatorMap.containsKey(did)) {
                    Accumulator acc = new Accumulator();
                    acc.setDid(did);
                    acc.setScore(score);
                    accumulatorMap.put(did, acc);
                }else {
                    Accumulator acc = accumulatorMap.get(did);
                    acc.setScore(acc.getScore() + score);
                    accumulatorMap.replace(did, acc);
                }
            }
        }
        List<Accumulator> accumulatorList = accumulatorMap.values().stream().collect(Collectors.toList());
        accumulatorList.sort(Comparator.comparingDouble(Accumulator::getScore).reversed());
        return accumulatorList;
    }

    public List<Accumulator> process(String query, int k) {
        return process(query).stream().limit(k).collect(Collectors.toList());
    }
}
