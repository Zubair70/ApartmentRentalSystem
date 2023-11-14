package model;

public class TenantLetter {

    private int id;
    private String content;

    public TenantLetter(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public TenantLetter() {
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
