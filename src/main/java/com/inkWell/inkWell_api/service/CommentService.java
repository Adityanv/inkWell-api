package com.inkWell.inkWell_api.service;

import com.inkWell.inkWell_api.dto.CommentRequestDTO;
import com.inkWell.inkWell_api.dto.CommentResponseDTO;
import com.inkWell.inkWell_api.dto.PostRequestDTO;
import com.inkWell.inkWell_api.model.Comment;
import com.inkWell.inkWell_api.model.Post;
import com.inkWell.inkWell_api.repository.CommentRepository;
import com.inkWell.inkWell_api.repository.PostRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public List<CommentResponseDTO> getAllComments(){
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponseDTO> allComments = new ArrayList<>();
        for(Comment cmt : comments){
            Integer postId = (cmt.getPost() != null) ? cmt.getPost().getId() : null;
            allComments.add(new CommentResponseDTO(
                    cmt.getId(),
                    postId,
                    cmt.getAuthorName(),
                    cmt.getAuthorEmail(),
                    cmt.getBody(),
                    cmt.getCreatedAt()
            ));
        }
        return allComments;
    }

    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    public List<CommentResponseDTO> getAllCommentsByPostId(Integer postId) {
        List<CommentResponseDTO> allComments = getAllComments();
        List<CommentResponseDTO> allCommentsByPostID = new ArrayList<>();

        for(CommentResponseDTO cmt : allComments){
            if(cmt.getPostId() == postId){
                allCommentsByPostID.add(cmt);
            }
        }
        return allCommentsByPostID;
    }

    @Transactional
    public CommentResponseDTO addCommentByPostId(Integer postId, CommentRequestDTO newCmt) {
        Post commentedPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment newComment = new Comment(
                commentedPost,
                newCmt.getAuthorName(),
                newCmt.getAuthorEmail(),
                newCmt.getBody(),
                LocalDateTime.now()
        );
        Comment savedComment = commentRepository.save(newComment);
        return new CommentResponseDTO(
                savedComment.getId(),
                postId,
                savedComment.getAuthorName(),
                savedComment.getAuthorEmail(),
                savedComment.getBody(),
                savedComment.getCreatedAt()
        );
    }
}
