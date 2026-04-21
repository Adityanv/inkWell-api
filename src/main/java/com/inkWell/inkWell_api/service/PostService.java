package com.inkWell.inkWell_api.service;

import com.inkWell.inkWell_api.dto.PostRequestDTO;
import com.inkWell.inkWell_api.dto.PostResponseDTO;
import com.inkWell.inkWell_api.enums.PostStatus;
import com.inkWell.inkWell_api.model.Author;
import com.inkWell.inkWell_api.model.Post;
import com.inkWell.inkWell_api.model.Tag;
import com.inkWell.inkWell_api.repository.AuthorRepository;
import com.inkWell.inkWell_api.repository.CommentRepository;
import com.inkWell.inkWell_api.repository.PostRepository;
import com.inkWell.inkWell_api.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired private PostRepository    postRepository;
    @Autowired private AuthorRepository authorRepository;
    @Autowired private TagRepository tagRepository;
    @Autowired private CommentRepository commentRepository;

    // ── GET ALL (with filters + pagination) ───────────────────
    @Transactional
    public Page<PostResponseDTO> getPosts(
            String search, PostStatus status,
            String tagName, Integer authorId,
            Pageable pageable) {

        Page<Post> posts = postRepository.findWithFilters(
                search, status, tagName, authorId, pageable);

        // map each Post → PostResponse, fetching comment count separately
        return posts.map(post -> {
            long count = commentRepository.countByPostId(post.getId());
            return new PostResponseDTO(post, count);
        });
    }

    // ── CREATE POST ───────────────────────────────────────────
    @Transactional
    public PostResponseDTO createPost(PostRequestDTO request) {

        // 1. Find author — 404 if not found
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Author not found"));

        // 2. Build the post
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setExcerpt(request.getExcerpt());
        post.setAuthor(author);
        post.setStatus(request.getStatus());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        // 3. Set publishedAt if publishing immediately
        if (request.getStatus() == PostStatus.PUBLISHED) {
            post.setPublishedAt(LocalDateTime.now());
        }

        // 4. Resolve tags — find existing or create new
        if (request.getTags() != null && !request.getTags().isEmpty()) {
            List<Tag> resolvedTags = request.getTags().stream()
                    .map(this::findOrCreateTag)
                    .toList();
            post.setTags(resolvedTags);
        }

        Post saved = postRepository.save(post);
        return new PostResponseDTO(saved, 0);
    }

    // ── UPDATE STATUS ─────────────────────────────────────────
    @Transactional
    public PostResponseDTO updateStatus(Integer id, PostStatus newStatus) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Post not found"));

        post.setStatus(newStatus);
        post.setUpdatedAt(LocalDateTime.now());

        // Set publishedAt when going DRAFT → PUBLISHED
        if (newStatus == PostStatus.PUBLISHED && post.getPublishedAt() == null) {
            post.setPublishedAt(LocalDateTime.now());
        }

        Post saved = postRepository.save(post);
        long count = commentRepository.countByPostId(saved.getId());
        return new PostResponseDTO(saved, count);
    }

    // ── DELETE POST ───────────────────────────────────────────
    @Transactional
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Post not found"));
        postRepository.delete(post);
        // comments deleted automatically via CascadeType.ALL
    }

    // ── PRIVATE: find or create tag ───────────────────────────
    private Tag findOrCreateTag(String name) {
        return tagRepository.findByName(name).orElseGet(() -> {
            Tag t = new Tag();
            t.setName(name);
            t.setSlug(name.toLowerCase()
                    .trim()
                    .replaceAll("[^a-z0-9]+", "-"));
            return tagRepository.save(t);
        });
    }
}
