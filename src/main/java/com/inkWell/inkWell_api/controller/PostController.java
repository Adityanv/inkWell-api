package com.inkWell.inkWell_api.controller;

import com.inkWell.inkWell_api.dto.PostRequestDTO;
import com.inkWell.inkWell_api.dto.PostResponseDTO;
import com.inkWell.inkWell_api.enums.PostStatus;
import com.inkWell.inkWell_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponseDTO>> getPosts(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false)    String search,
            @RequestParam(required = false) PostStatus status,
            @RequestParam(required = false)    String tag,
            @RequestParam(required = false)    Integer authorId
    ) {
        Pageable pageable = PageRequest.of(
                page, size, Sort.by("createdAt").descending());

        return ResponseEntity.ok(
                postService.getPosts(search, status, tag, authorId, pageable));
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO request) {
        return ResponseEntity.status(201).body(postService.createPost(request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PostResponseDTO> updateStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body
    ) {
        PostStatus newStatus = PostStatus.valueOf(body.get("status"));
        return ResponseEntity.ok(postService.updateStatus(id, newStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
