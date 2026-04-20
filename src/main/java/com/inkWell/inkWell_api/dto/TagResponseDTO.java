package com.inkWell.inkWell_api.dto;

public class TagResponseDTO {
    private Integer id;
    private String name;
    private String slug;
    private int postCount;

    public TagResponseDTO() {
    }

    public TagResponseDTO(Integer id, String name, String slug, int postCount) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.postCount = postCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}
