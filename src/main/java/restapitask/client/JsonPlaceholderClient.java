package restapitask.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import restapitask.dto.AlbumJsonDTO;
import restapitask.dto.PostJsonDTO;
import restapitask.dto.UserJsonDTO;

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
        return getAll(urlTemplate + "/users/");
    }

    public UserJsonDTO getUserById(int id) {
        return get(urlTemplate + "/users/" + id, UserJsonDTO.class);
    }

    public String deleteUser(int id) {
        return delete(urlTemplate + "/users/" + id);
    }

    public UserJsonDTO createUser(UserJsonDTO user) {
        return create(urlTemplate + "/users/", user, UserJsonDTO.class);
    }

    public UserJsonDTO updateUser(UserJsonDTO user, int id) {
        return update(urlTemplate + "/users/" + id, user, UserJsonDTO.class);
    }

    public List<PostJsonDTO> getAllPosts() {
        return getAll(urlTemplate + "/posts/");
    }

    public PostJsonDTO getPostById(int id) {
        return get(urlTemplate + "/posts/" + id, PostJsonDTO.class);
    }

    public List<PostJsonDTO> getPostsByUserId(int userId) {
        return getAll(urlTemplate + "/users/" + userId + "/posts/");
    }

    public String deletePost(int id) {
        return delete(urlTemplate + "/posts/" + id);
    }

    public PostJsonDTO createPost(PostJsonDTO post) {
        return create(urlTemplate + "/posts/", post, PostJsonDTO.class);
    }

    public PostJsonDTO updatePost(PostJsonDTO post, int id) {
        return update(urlTemplate + "/posts/" + id, post, PostJsonDTO.class);
    }

    public List<AlbumJsonDTO> getAllAlbums() {
        return getAll(urlTemplate + "/albums/");
    }

    public AlbumJsonDTO getAlbumById(int id) {
        return get(urlTemplate + "/albums/" + id, AlbumJsonDTO.class);
    }

    public List<AlbumJsonDTO> getAlbumsByUserId(int userId) {
        return getAll(urlTemplate + "/users/" + userId + "/albums/");
    }

    public String deleteAlbum(int id) {
        return delete(urlTemplate + "/albums/" + id);
    }

    public AlbumJsonDTO createAlbum(AlbumJsonDTO album) {
        return create(urlTemplate + "/albums/", album, AlbumJsonDTO.class);
    }

    public AlbumJsonDTO updateAlbum(AlbumJsonDTO album, int id) {
        return update(urlTemplate + "/albums/" + id, album, AlbumJsonDTO.class);
    }

    private <T> List<T> getAll(String url) {
        RequestEntity<Void> requestEntity = RequestEntity.get(url).build();
        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    private <T> T get(String url, Class<T> clazz) {
        RequestEntity<Void> requestEntity = RequestEntity.get(url).build();
        ResponseEntity<T> responseEntity = restTemplate.exchange(requestEntity, clazz);
        return responseEntity.getBody();
    }

    private String delete(String url) {
        RequestEntity<Void> requestEntity = RequestEntity.delete(url).build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return responseEntity.getBody();
    }

    private <T> T create(String url, T body, Class<T> clazz) {
        RequestEntity<T> requestEntity = RequestEntity.post(url).body(body);
        ResponseEntity<T> responseEntity = restTemplate.exchange(requestEntity, clazz);
        return responseEntity.getBody();
    }

    private <T> T update(String url, T body, Class<T> clazz) {
        RequestEntity<T> requestEntity = RequestEntity.put(url).body(body);
        ResponseEntity<T> responseEntity = restTemplate.exchange(requestEntity, clazz);
        return responseEntity.getBody();
    }
}
