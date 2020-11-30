public class Document {

    // Document identifier
    private Long id;

    // Document title
    private String title;

    // Document URL
    private String url;

    // Document content
    private String[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public Document() {
    }

    public Document(Long id) {
        this.id = id;
    }

    public Document(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
