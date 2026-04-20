package com.inkWell.inkWell_api.controller;

import com.inkWell.inkWell_api.dto.AuthorRequestDTO;
import com.inkWell.inkWell_api.dto.AuthorResposeDTO;
import com.inkWell.inkWell_api.model.Author;
import com.inkWell.inkWell_api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResposeDTO>> getAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping
    public ResponseEntity<Author> createNewAuthor(@RequestBody AuthorRequestDTO newAuthorDetails){
        return ResponseEntity.ok(authorService.addAuthors(newAuthorDetails));
    }
}
