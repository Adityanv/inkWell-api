package com.inkWell.inkWell_api.service;

import com.inkWell.inkWell_api.dto.AuthorRequestDTO;
import com.inkWell.inkWell_api.dto.AuthorResposeDTO;
import com.inkWell.inkWell_api.model.Author;
import com.inkWell.inkWell_api.repository.AuthorRepository;
import com.inkWell.inkWell_api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRepository;

    public List<AuthorResposeDTO> getAllAuthors(){

        List<Author> authors = authorRepository.findAll();
        List<AuthorResposeDTO> allAuthors = new ArrayList<>();

        for(Author author : authors){
            int id = author.getId();
            int postCount = authorRepository.getPostCountByAuthorId(id);
            allAuthors.add(new AuthorResposeDTO(id, author.getName(), author.getBio(), author.getEmail(), postCount));
        }
        return allAuthors;
    }

    public Author addAuthors(AuthorRequestDTO authorRequestDTO){
        String authorName = authorRequestDTO.getName();
        String authorBio = authorRequestDTO.getBio();
        String authorEmail = authorRequestDTO.getEmail();
        LocalDateTime authorCreatedAt = LocalDateTime.now();

        Author newAuthor = new Author(authorName, authorEmail, authorBio, authorCreatedAt);
        return authorRepository.save(newAuthor);
    }
}
