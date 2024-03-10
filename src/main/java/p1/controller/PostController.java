package p1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import p1.dto.PostJsonDTO;
import p1.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping("/api/posts")
    public PostJsonDTO[] getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/api/posts/{id}")
    public PostJsonDTO getPostById(@PathVariable int id) {
        return service.getPostById(id);
    }

    @GetMapping("/api/users/{userId}/posts")
    public PostJsonDTO[] getPostsByUserId(@PathVariable int userId) {
        return service.getPostsByUserId(userId);
    }

    @DeleteMapping("/api/posts/{id}")
    public void deletePost(@PathVariable int id) {
        service.deletePost(id);
    }

    @PostMapping("/api/posts")
    public PostJsonDTO createPost(@RequestBody PostJsonDTO post) {
        return service.createPost(post);
    }

    @PutMapping("/api/posts/{id}")
    public PostJsonDTO updatePost(@RequestBody PostJsonDTO post, @PathVariable int id) {
        return service.updatePost(post, id);
    }
}
