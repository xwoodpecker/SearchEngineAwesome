public class Posting {

    public long did;
    public int tf;

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
}
