package com.sample.repository;

import com.sample.entity.Comment;
import com.sample.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void given_post_with_multiple_comments_all_comments_should_be_saved() {
        Post post = new Post();
        post.setName("Post 1");
        post.addComment(new Comment("Comment 1"));
        post.addComment(new Comment("Comment 2"));
        postRepository.save(post);
        entityManager.flush();

        Post dbPost = postRepository.getOne(post.getId());
        assertThat(dbPost.getComments()).hasSize(2);
    }

    @Test
    void given_post_with_two_comments_one_comment_should_be_removed_successfully() {
        Post post = new Post();
        post.setName("Post 1");
        post.addComment(new Comment("Comment 1"));
        post.addComment(new Comment("Comment 2"));
        postRepository.save(post);
        entityManager.flush();

        Post dbPost = postRepository.getOne(post.getId());
        dbPost.removeComment(dbPost.getComments().iterator().next());
        postRepository.save(post);
        entityManager.flush();

        dbPost = postRepository.getOne(post.getId());
        assertThat(dbPost.getComments()).hasSize(1);
    }
}
