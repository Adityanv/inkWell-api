package com.inkWell.inkWell_api.model;

import com.inkWell.inkWell_api.enums.PostStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private String excerpt;

    @Enumerated(EnumType.STRING)
    private PostStatus status = PostStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Integer getId()                  { return id; }
    public void setId(Integer id)           { this.id = id; }
    public String getTitle()                { return title; }
    public void setTitle(String title)      { this.title = title; }
    public String getContent()              { return content; }
    public void setContent(String content)  { this.content = content; }
    public String getExcerpt()              { return excerpt; }
    public void setExcerpt(String excerpt)  { this.excerpt = excerpt; }
    public PostStatus getStatus()           { return status; }
    public void setStatus(PostStatus s)     { this.status = s; }
    public Author getAuthor()               { return author; }
    public void setAuthor(Author author)    { this.author = author; }
    public List<Tag> getTags()              { return tags; }
    public void setTags(List<Tag> tags)     { this.tags = tags; }
    public List<Comment> getComments()      { return comments; }
    public LocalDateTime getPublishedAt()   { return publishedAt; }
    public void setPublishedAt(LocalDateTime p) { this.publishedAt = p; }
    public LocalDateTime getCreatedAt()     { return createdAt; }
    public void setCreatedAt(LocalDateTime c)   { this.createdAt = c; }
    public LocalDateTime getUpdatedAt()     { return updatedAt; }
    public void setUpdatedAt(LocalDateTime u)   { this.updatedAt = u; }
}
