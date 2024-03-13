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
import restapitask.dto.AlbumJsonDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@SpringBootTest(classes = AlbumService.class)
@ContextConfiguration(classes = AlbumServiceTest.Config.class)
class AlbumServiceTest {
    @MockBean
    private JsonPlaceholderClient client;
    @Autowired
    private AlbumService underTest;
    @Autowired
    private CacheManager cacheManager;

    @AfterEach
    public void afterEach() {
        cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
    }

    @Test
    public void getAllAlbumsTest() {
        when(client.getAllAlbums()).thenReturn(createAlbums());
        List<AlbumJsonDTO> result = underTest.getAllAlbums();
        assertEquals(createAlbums().get(0), result.get(0));
        result = underTest.getAllAlbums();
        verify(client, times(1)).getAllAlbums();
        assertEquals(createAlbums().get(0), result.get(0));
    }

    @Test
    public void getAlbumByIdTest() {
        when(client.getAlbumById(eq(1))).thenReturn(createAlbum());
        AlbumJsonDTO result = underTest.getAlbumById(1);
        assertEquals(createAlbum(), result);
        result = underTest.getAlbumById(1);
        verify(client, times(1)).getAlbumById(eq(1));
        assertEquals(createAlbum(), result);
    }

    @Test
    public void getAlbumsByUserIdTest() {
        when(client.getAlbumsByUserId(eq(1))).thenReturn(createAlbums());
        List<AlbumJsonDTO> result = underTest.getAlbumsByUserId(1);
        assertEquals(createAlbums().get(0), result.get(0));
        result = underTest.getAlbumsByUserId(1);
        verify(client, times(1)).getAlbumsByUserId(eq(1));
        assertEquals(createAlbums().get(0), result.get(0));
    }

    @Test
    public void createAlbumTest() {
        when(client.createAlbum(any())).thenReturn(createAlbum());
        assertEquals(createAlbum(), underTest.createAlbum(createAlbum()));
        underTest.getAlbumById(1);
        verify(client, never()).getAlbumById(eq(1));
    }

    @Test
    public void deleteAlbumTest() {
        when(client.createAlbum(any())).thenReturn(createAlbum());
        underTest.createAlbum(createAlbum());
        underTest.deleteAlbum(1);
        underTest.getAlbumById(1);
        verify(client, times(1)).getAlbumById(eq(1));
    }

    @Test
    public void updateAlbumTest() {
        when(client.createAlbum(any())).thenReturn(createAlbum());
        AlbumJsonDTO updatedAlbum = createAlbum();
        updatedAlbum.setTitle("updated");
        underTest.updateAlbum(updatedAlbum, 1);
        assertEquals(updatedAlbum, underTest.getAlbumById(1));
        verify(client, never()).getAlbumById(eq(1));
    }

    @Configuration
    @EnableCaching
    public static class Config {
        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("albums", "allAlbums", "albumsByUserId");
        }
    }

    private List<AlbumJsonDTO> createAlbums() {
        return List.of(new AlbumJsonDTO());
    }

    private AlbumJsonDTO createAlbum() {
        AlbumJsonDTO result = new AlbumJsonDTO();
        result.setId(1);
        return result;
    }
}