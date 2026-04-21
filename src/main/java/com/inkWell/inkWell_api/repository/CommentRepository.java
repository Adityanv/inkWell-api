package com.inkWell.inkWell_api.repository;

import com.inkWell.inkWell_api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // Used in PostService to get comment count without loading all comments
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.post.id = :postId")
    long countByPostId(@Param("postId") Integer postId);

    // Used in CommentController to get all comments for a post
    List<Comment> findByPostId(Integer postId);

    // Used in StatsController
    @Query("SELECT COUNT(c) FROM Comment c")
    long countAllComments();

}
