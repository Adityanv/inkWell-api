package com.inkWell.inkWell_api.repository;

import com.inkWell.inkWell_api.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT COUNT(p) FROM Post p WHERE p.author.id = :id")
    int getPostCountByAuthorId(@Param("id") int id);

    Optional<Author> findByEmail(String email);
}
