package com.inkWell.inkWell_api.controller;

import com.inkWell.inkWell_api.dto.CommentRequestDTO;
import com.inkWell.inkWell_api.dto.CommentResponseDTO;
import com.inkWell.inkWell_api.model.Comment;
import com.inkWell.inkWell_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments(){
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Integer id){
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getAllPostsByPostId(@PathVariable Integer postId){
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> addCommentsToPosts(@PathVariable Integer postId, @RequestBody CommentRequestDTO newComent){
        return ResponseEntity.ok(commentService.addCommentByPostId(postId, newComent));
    }
}
