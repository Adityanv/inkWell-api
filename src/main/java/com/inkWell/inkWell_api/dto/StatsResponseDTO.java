package com.inkWell.inkWell_api.dto;

public class StatsResponseDTO {
    private long totalComments;
    private long totalPosts;
    private long publishedPosts; // Renamed from publishedComments
    private long totalAuthors;

    // Standard order: Total Comments, Total Posts, Published Posts, Total Authors
    public StatsResponseDTO(long totalComments, long totalPosts, long publishedPosts, long totalAuthors) {
        this.totalComments = totalComments;
        this.totalPosts = totalPosts;
        this.publishedPosts = publishedPosts;
        this.totalAuthors = totalAuthors;
    }

    public long getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(long totalComments) {
        this.totalComments = totalComments;
    }

    public long getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(long totalPosts) {
        this.totalPosts = totalPosts;
    }

    public long getPublishedPosts() {
        return publishedPosts;
    }

    public void setPublishedPosts(long publishedPosts) {
        this.publishedPosts = publishedPosts;
    }

    public long getTotalAuthors() {
        return totalAuthors;
    }

    public void setTotalAuthors(long totalAuthors) {
        this.totalAuthors = totalAuthors;
    }
}