public class Accumulator {

    public long did;
    public double score;

    public Accumulator(long did, double score) {
        this.did = did;
        this.score = score;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
