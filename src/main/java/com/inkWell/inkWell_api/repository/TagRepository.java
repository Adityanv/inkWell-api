package com.inkWell.inkWell_api.repository;

import com.inkWell.inkWell_api.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "SELECT COUNT(post_id) FROM post_tags t WHERE tag_id = :tagId", nativeQuery = true)
    int getPostCountByTagId(@Param("tagId") int tagId);

    Optional<Tag> findByName(String name);

    // Used to check for duplicate slug before creating
    Optional<Tag> findBySlug(String slug);
}
