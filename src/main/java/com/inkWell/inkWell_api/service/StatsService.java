    package com.inkWell.inkWell_api.service;

    import com.inkWell.inkWell_api.dto.StatsResponseDTO;
    import com.inkWell.inkWell_api.enums.PostStatus;
    import com.inkWell.inkWell_api.repository.AuthorRepository;
    import com.inkWell.inkWell_api.repository.CommentRepository;
    import com.inkWell.inkWell_api.repository.PostRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class StatsService {
        @Autowired
        private PostRepository postRepository;

        @Autowired
        private AuthorRepository authorRepository;

        @Autowired
        private CommentRepository commentRepository;

        public StatsResponseDTO getAllStats(){
            long totalPosts = postRepository.count();
            long publishedPosts = postRepository.countByStatus(PostStatus.PUBLISHED);
            long totalComments = commentRepository.count();
            long totalAuthors = authorRepository.count();
            return new StatsResponseDTO(totalComments, totalPosts, publishedPosts, totalAuthors);
        }
    }
