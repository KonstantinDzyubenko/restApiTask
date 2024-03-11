package restapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import restapitask.client.JsonPlaceholderClient;
import restapitask.dto.PostJsonDTO;

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
