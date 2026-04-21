package com.inkWell.inkWell_api.dto;

import com.inkWell.inkWell_api.enums.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PostRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    private String excerpt;

    @NotNull(message = "Author ID is required")
    private Integer authorId;

    private PostStatus status = PostStatus.DRAFT;

    private List<String> tags = new ArrayList<>();

    // Getters and Setters
    public String getTitle()               { return title; }
    public void setTitle(String title)     { this.title = title; }

    public String getContent()             { return content; }
    public void setContent(String c)       { this.content = c; }

    public String getExcerpt()             { return excerpt; }
    public void setExcerpt(String e)       { this.excerpt = e; }

    public Integer getAuthorId()           { return authorId; }
    public void setAuthorId(Integer id)    { this.authorId = id; }

    public PostStatus getStatus()          { return status; }
    public void setStatus(PostStatus s)    { this.status = s; }

    public List<String> getTags()          { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}
