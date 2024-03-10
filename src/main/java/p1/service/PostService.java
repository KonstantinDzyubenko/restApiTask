package p1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import p1.client.JsonPlaceholderClient;
import p1.dto.PostJsonDTO;

@Component
@RequiredArgsConstructor
public class PostService {
    private final JsonPlaceholderClient client;

    public PostJsonDTO[] getAllPosts() {
        return client.getAllPosts();
    }

    public PostJsonDTO getPostById(int id) {
        return client.getPostById(id);
    }

    public PostJsonDTO[] getPostsByUserId(int userId) {
        return client.getPostsByUserId(userId);
    }

    public void deletePost(int id) {
        client.deletePost(id);
    }

    public PostJsonDTO createPost(PostJsonDTO post) {
        return client.createPost(post);
    }

    public PostJsonDTO updatePost(PostJsonDTO post, int id) {
        client.updatePost(post, id);
        return post;
    }
}
