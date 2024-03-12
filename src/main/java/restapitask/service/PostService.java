package restapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import restapitask.client.JsonPlaceholderClient;
import restapitask.dto.PostJsonDTO;

@Component
@RequiredArgsConstructor
public class PostService {
    private final JsonPlaceholderClient client;

    @Cacheable(value = "allPosts")
    public PostJsonDTO[] getAllPosts() {
        return client.getAllPosts();
    }

    @Cacheable(value = "posts", key = "#id")
    public PostJsonDTO getPostById(int id) {
        return client.getPostById(id);
    }

    @Cacheable(value = "postsByUserId", key="#userId")
    public PostJsonDTO[] getPostsByUserId(int userId) {
        return client.getPostsByUserId(userId);
    }

    @CacheEvict(value = "posts", key = "#id")
    public void deletePost(int id) {
        client.deletePost(id);
    }

    @CachePut(value = "posts", key = "#result.id")
    public PostJsonDTO createPost(PostJsonDTO post) {
        return client.createPost(post);
    }

    @CachePut(value = "posts", key = "#id")
    public PostJsonDTO updatePost(PostJsonDTO post, int id) {
        client.updatePost(post, id);
        return post;
    }
}
