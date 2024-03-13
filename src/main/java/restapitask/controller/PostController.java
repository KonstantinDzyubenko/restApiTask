package restapitask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restapitask.dto.PostJsonDTO;
import restapitask.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping("/api/posts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_POSTS_VIEWER')")
    public List<PostJsonDTO> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/api/posts/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_POSTS_VIEWER')")
    public PostJsonDTO getPostById(@PathVariable int id) {
        return service.getPostById(id);
    }

    @GetMapping("/api/users/{userId}/posts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_POSTS_VIEWER')")
    public List<PostJsonDTO> getPostsByUserId(@PathVariable int userId) {
        return service.getPostsByUserId(userId);
    }

    @DeleteMapping("/api/posts/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_POSTS_EDITOR')")
    public String deletePost(@PathVariable int id) {
        return service.deletePost(id);
    }

    @PostMapping("/api/posts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_POSTS_EDITOR')")
    public PostJsonDTO createPost(@RequestBody PostJsonDTO post) {
        return service.createPost(post);
    }

    @PutMapping("/api/posts/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_POSTS_EDITOR')")
    public PostJsonDTO updatePost(@RequestBody PostJsonDTO post, @PathVariable int id) {
        return service.updatePost(post, id);
    }
}
