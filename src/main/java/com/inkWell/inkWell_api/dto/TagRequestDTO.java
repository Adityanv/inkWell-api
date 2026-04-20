package com.inkWell.inkWell_api.dto;

public class TagRequestDTO {
    private String name;
    private String slug;

    public TagRequestDTO(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public TagRequestDTO() {
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
}
