package restapitask.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import restapitask.dto.AlbumJsonDTO;
import restapitask.dto.PostJsonDTO;
import restapitask.dto.UserJsonDTO;

import java.util.Arrays;
import java.util.List;

@Component
public class JsonPlaceholderClient {
    private final String urlTemplate;
    private final RestTemplate restTemplate;

    public JsonPlaceholderClient(@Value("${JsonPlaceholderUrl}") String urlTemplate, RestTemplate restTemplate) {
        this.urlTemplate = urlTemplate;
        this.restTemplate = restTemplate;
    }

    public List<UserJsonDTO> getAllUsers() {
        return Arrays.asList(restTemplate.getForObject(urlTemplate + "/users/", UserJsonDTO[].class));
    }

    public UserJsonDTO getUserById(int id) {
        return restTemplate.getForObject(urlTemplate + "/users/" + id, UserJsonDTO.class);
    }

    public void deleteUser(int id) {
        restTemplate.delete(urlTemplate + "/users/" + id);
    }

    public UserJsonDTO createUser(UserJsonDTO user) {
        return restTemplate.postForObject(urlTemplate + "/users/", user, UserJsonDTO.class);
    }

    public void updateUser(UserJsonDTO user, int id) {
        restTemplate.put(urlTemplate + "/users/" + id, user);
    }

    public List<PostJsonDTO> getAllPosts() {
        return Arrays.asList(restTemplate.getForObject(urlTemplate + "/posts/", PostJsonDTO[].class));
    }

    public PostJsonDTO getPostById(int id) {
        return restTemplate.getForObject(urlTemplate + "/posts/" + id, PostJsonDTO.class);
    }

    public List<PostJsonDTO> getPostsByUserId(int userId) {
        return Arrays.asList(restTemplate.getForObject(urlTemplate + "/users/" + userId + "/posts/", PostJsonDTO[].class));
    }

    public void deletePost(int id) {
        restTemplate.delete(urlTemplate + "/posts/" + id);
    }

    public PostJsonDTO createPost(PostJsonDTO post) {
        return restTemplate.postForObject(urlTemplate + "/posts/", post, PostJsonDTO.class);
    }

    public void updatePost(PostJsonDTO post, int id) {
        restTemplate.put(urlTemplate + "/posts/" + id, post);
    }

    public List<AlbumJsonDTO> getAllAlbums() {
        return Arrays.asList(restTemplate.getForObject(urlTemplate + "/albums/", AlbumJsonDTO[].class));
    }

    public AlbumJsonDTO getAlbumById(int id) {
        return restTemplate.getForObject(urlTemplate + "/albums/" + id, AlbumJsonDTO.class);
    }

    public List<AlbumJsonDTO> getAlbumsByUserId(int userId) {
        return Arrays.asList(restTemplate.getForObject(urlTemplate + "/users/" + userId + "/albums/", AlbumJsonDTO[].class));
    }

    public void deleteAlbum(int id) {
        restTemplate.delete(urlTemplate + "/albums/" + id);
    }

    public AlbumJsonDTO createAlbum(AlbumJsonDTO album) {
        return restTemplate.postForObject(urlTemplate + "/albums/", album, AlbumJsonDTO.class);
    }

    public void updateAlbum(AlbumJsonDTO album, int id) {
        restTemplate.put(urlTemplate + "/albums/" + id, album);
    }
}
