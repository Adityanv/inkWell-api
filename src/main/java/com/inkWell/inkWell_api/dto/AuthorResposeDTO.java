package com.inkWell.inkWell_api.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public class AuthorResposeDTO {
    private Integer id;
    private String name;
    private String bio;
    private String email;
    private int postCount;

    public AuthorResposeDTO(Integer id, String name, String bio, String email, int postCount) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.email = email;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}
