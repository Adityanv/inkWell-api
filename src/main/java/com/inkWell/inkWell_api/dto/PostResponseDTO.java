package com.inkWell.inkWell_api.dto;

import com.inkWell.inkWell_api.enums.PostStatus;
import com.inkWell.inkWell_api.model.Post;
import com.inkWell.inkWell_api.model.Tag;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO  {
    private Integer id;
    private String title;
    private String excerpt;
    private PostStatus status;

    // From the Author relationship — flattened
    private Integer authorId;
    private String authorName;

    // From the Tag relationship — kept as a small list
    private List<TagInfo> tags;

    // Computed — not a column, queried separately
    private long commentCount;

    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;

    // Constructor that takes a Post entity and maps it
    public PostResponseDTO(Post post, long commentCount) {
        this.id           = post.getId();
        this.title        = post.getTitle();
        this.excerpt      = post.getExcerpt();
        this.status       = post.getStatus();
        this.authorId     = post.getAuthor().getId();
        this.authorName   = post.getAuthor().getName();
        this.tags         = post.getTags().stream()
                .map(TagInfo::new)
                .toList();
        this.commentCount = commentCount;
        this.publishedAt  = post.getPublishedAt();
        this.createdAt    = post.getCreatedAt();
    }

    // ── Getters ──────────────────────────────────────────────
    public Integer getId()            { return id; }
    public String getTitle()          { return title; }
    public String getExcerpt()        { return excerpt; }
    public PostStatus getStatus()     { return status; }
    public Integer getAuthorId()      { return authorId; }
    public String getAuthorName()     { return authorName; }
    public List<TagInfo> getTags()    { return tags; }
    public long getCommentCount()     { return commentCount; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public LocalDateTime getCreatedAt()   { return createdAt; }

    // ── Inner class for tag info inside the response ──────────
    public static class TagInfo {
        private Integer id;
        private String name;
        private String slug;

        public TagInfo(Tag tag) {
            this.id   = tag.getId();
            this.name = tag.getName();
            this.slug = tag.getSlug();
        }

        public Integer getId()   { return id; }
        public String getName()  { return name; }
        public String getSlug()  { return slug; }
    }
}
