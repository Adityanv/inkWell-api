package com.inkWell.inkWell_api.repository;

import com.inkWell.inkWell_api.enums.PostStatus;
import com.inkWell.inkWell_api.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByStatus(PostStatus status, Pageable pageable);

    // Filter by author only
    Page<Post> findByAuthorId(Integer authorId, Pageable pageable);

    // Search in title or content
    @Query("SELECT p FROM Post p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.content) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Post> searchPosts(@Param("search") String search, Pageable pageable);

    // Filter by tag name
    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.name = :tagName")
    Page<Post> findByTagName(@Param("tagName") String tagName, Pageable pageable);

    // Combined filter — all params optional
    @Query("SELECT p FROM Post p JOIN p.author a LEFT JOIN p.tags t WHERE " +
            "(:search IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:status IS NULL OR p.status = :status) AND " +
            "(:tagName IS NULL OR t.name = :tagName) AND " +
            "(:authorId IS NULL OR a.id = :authorId)")
    Page<Post> findWithFilters(
            @Param("search")   String search,
            @Param("status") PostStatus status,
            @Param("tagName")  String tagName,
            @Param("authorId") Integer authorId,
            Pageable pageable
    );

    long countByStatus(PostStatus postStatus);
}
