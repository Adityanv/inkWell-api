package com.inkWell.inkWell_api.dto;

public class CommentRequestDTO {
    private String authorName;
    private String authorEmail;
    private String body;


    public CommentRequestDTO(String authorName, String authorEmail, String body) {
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
