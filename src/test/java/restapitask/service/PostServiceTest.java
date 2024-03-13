package restapitask.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import restapitask.client.JsonPlaceholderClient;
import restapitask.dto.PostJsonDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = PostService.class)
@ContextConfiguration(classes = PostServiceTest.Config.class)
public class PostServiceTest {
    @MockBean
    private JsonPlaceholderClient client;
    @Autowired
    private PostService underTest;
    @Autowired
    private CacheManager cacheManager;

    @AfterEach
    public void afterEach() {
        cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
    }

    @Test
    public void getAllPostsTest() {
        when(client.getAllPosts()).thenReturn(createPosts());
        List<PostJsonDTO> result = underTest.getAllPosts();
        assertEquals(createPosts().get(0), result.get(0));
        result = underTest.getAllPosts();
        verify(client, times(1)).getAllPosts();
        assertEquals(createPosts().get(0), result.get(0));
    }

    @Test
    public void getPostByIdTest() {
        when(client.getPostById(eq(1))).thenReturn(createPost());
        PostJsonDTO result = underTest.getPostById(1);
        assertEquals(createPost(), result);
        result = underTest.getPostById(1);
        verify(client, times(1)).getPostById(eq(1));
        assertEquals(createPost(), result);
    }

    @Test
    public void getPostsByUserIdTest() {
        when(client.getPostsByUserId(eq(1))).thenReturn(createPosts());
        List<PostJsonDTO> result = underTest.getPostsByUserId(1);
        assertEquals(createPosts().get(0), result.get(0));
        result = underTest.getPostsByUserId(1);
        verify(client, times(1)).getPostsByUserId(eq(1));
        assertEquals(createPosts().get(0), result.get(0));
    }

    @Test
    public void createPostTest() {
        when(client.createPost(any())).thenReturn(createPost());
        assertEquals(createPost(), underTest.createPost(createPost()));
        underTest.getPostById(1);
        verify(client, never()).getPostById(eq(1));
    }

    @Test
    public void deletePostTest() {
        when(client.createPost(any())).thenReturn(createPost());
        underTest.createPost(createPost());
        underTest.deletePost(1);
        underTest.getPostById(1);
        verify(client, times(1)).getPostById(eq(1));
    }

    @Test
    public void updatePostTest() {
        when(client.createPost(any())).thenReturn(createPost());
        PostJsonDTO updatedPost = createPost();
        updatedPost.setTitle("updated");
        when(client.updatePost(any(), eq(1))).thenReturn(updatedPost);
        underTest.updatePost(updatedPost, 1);
        assertEquals(updatedPost, underTest.getPostById(1));
        verify(client, never()).getPostById(eq(1));
    }

    @Configuration
    @EnableCaching
    public static class Config {
        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("posts", "allPosts", "postsByUserId");
        }
    }

    private List<PostJsonDTO> createPosts() {
        return List.of(new PostJsonDTO());
    }

    private PostJsonDTO createPost() {
        PostJsonDTO result = new PostJsonDTO();
        result.setId(1);
        return result;
    }
}
