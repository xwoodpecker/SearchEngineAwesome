public class Posting {

    private long did;
    private int tf;

    public Posting(long did, int tf) {
        this.did = did;
        this.tf = tf;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    @Override
    public String toString() {
        return "Posting{" +
                "did=" + did +
                ", tf=" + tf +
                '}';
    }
}
