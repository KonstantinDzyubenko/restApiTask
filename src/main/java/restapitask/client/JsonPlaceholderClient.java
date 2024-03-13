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
        RequestEntity<Void> requestEntity = RequestEntity.get(urlTemplate + "/users/").build();
        ResponseEntity<List<UserJsonDTO>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public UserJsonDTO getUserById(int id) {
        RequestEntity<Void> requestEntity = RequestEntity.get(urlTemplate + "/users/" + id).build();
        ResponseEntity<UserJsonDTO> responseEntity = restTemplate.exchange(requestEntity, UserJsonDTO.class);
        return responseEntity.getBody();
    }

    public String deleteUser(int id) {
        RequestEntity<Void> requestEntity = RequestEntity.delete(urlTemplate + "/users/" + id).build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return responseEntity.getBody();
    }

    public UserJsonDTO createUser(UserJsonDTO user) {
        RequestEntity<UserJsonDTO> requestEntity = RequestEntity.post(urlTemplate + "/users/").body(user);
        ResponseEntity<UserJsonDTO> responseEntity = restTemplate.exchange(requestEntity, UserJsonDTO.class);
        return responseEntity.getBody();
    }

    public UserJsonDTO updateUser(UserJsonDTO user, int id) {
        RequestEntity<UserJsonDTO> requestEntity = RequestEntity.put(urlTemplate + "/users/" + id).body(user);
        ResponseEntity<UserJsonDTO> responseEntity = restTemplate.exchange(requestEntity, UserJsonDTO.class);
        return responseEntity.getBody();
    }

    public List<PostJsonDTO> getAllPosts() {
        RequestEntity<Void> requestEntity = RequestEntity.get(urlTemplate + "/posts/").build();
        ResponseEntity<List<PostJsonDTO>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public PostJsonDTO getPostById(int id) {
        RequestEntity<Void> requestEntity = RequestEntity.get(urlTemplate + "/posts/" + id).build();
        ResponseEntity<PostJsonDTO> responseEntity = restTemplate.exchange(requestEntity, PostJsonDTO.class);
        return responseEntity.getBody();
    }

    public List<PostJsonDTO> getPostsByUserId(int userId) {
        RequestEntity<Void> requestEntity = RequestEntity.get(urlTemplate + "/users/" + userId + "/posts/").build();
        ResponseEntity<List<PostJsonDTO>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public String deletePost(int id) {
        RequestEntity<Void> requestEntity = RequestEntity.delete(urlTemplate + "/posts/" + id).build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return responseEntity.getBody();
    }

    public PostJsonDTO createPost(PostJsonDTO post) {
        RequestEntity<PostJsonDTO> requestEntity = RequestEntity.post(urlTemplate + "/posts/").body(post);
        ResponseEntity<PostJsonDTO> responseEntity = restTemplate.exchange(requestEntity, PostJsonDTO.class);
        return responseEntity.getBody();
    }

    public PostJsonDTO updatePost(PostJsonDTO post, int id) {
        RequestEntity<PostJsonDTO> requestEntity = RequestEntity.put(urlTemplate + "/posts/" + id).body(post);
        ResponseEntity<PostJsonDTO> responseEntity = restTemplate.exchange(requestEntity, PostJsonDTO.class);
        return responseEntity.getBody();
    }

    public List<AlbumJsonDTO> getAllAlbums() {
        RequestEntity<Void> requestEntity = RequestEntity.get(urlTemplate + "/albums/").build();
        ResponseEntity<List<AlbumJsonDTO>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public AlbumJsonDTO getAlbumById(int id) {
        RequestEntity<Void> requestEntity = RequestEntity.get(urlTemplate + "/albums/" + id).build();
        ResponseEntity<AlbumJsonDTO> responseEntity = restTemplate.exchange(requestEntity, AlbumJsonDTO.class);
        return responseEntity.getBody();
    }

    public List<AlbumJsonDTO> getAlbumsByUserId(int userId) {
        RequestEntity<Void> requestEntity = RequestEntity.get(urlTemplate + "/users/" + userId + "/albums/").build();
        ResponseEntity<List<AlbumJsonDTO>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public String deleteAlbum(int id) {
        RequestEntity<Void> requestEntity = RequestEntity.delete(urlTemplate + "/albums/" + id).build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return responseEntity.getBody();
    }

    public AlbumJsonDTO createAlbum(AlbumJsonDTO album) {
        RequestEntity<AlbumJsonDTO> requestEntity = RequestEntity.post(urlTemplate + "/albums/").body(album);
        ResponseEntity<AlbumJsonDTO> responseEntity = restTemplate.exchange(requestEntity, AlbumJsonDTO.class);
        return responseEntity.getBody();
    }

    public AlbumJsonDTO updateAlbum(AlbumJsonDTO album, int id) {
        RequestEntity<AlbumJsonDTO> requestEntity = RequestEntity.put(urlTemplate + "/albums/" + id).body(album);
        ResponseEntity<AlbumJsonDTO> responseEntity = restTemplate.exchange(requestEntity, AlbumJsonDTO.class);
        return responseEntity.getBody();
    }
}
