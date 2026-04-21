package com.inkWell.inkWell_api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentResponseDTO {
    private Integer id;
    private Integer postId;
    private String authorName;
    private String authorEmail;
    private String body;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Integer id, Integer postId, String authorName, String authorEmail, String body, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.body = body;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
