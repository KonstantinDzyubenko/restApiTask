package restapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import restapitask.client.JsonPlaceholderClient;
import restapitask.dto.AlbumJsonDTO;

@Component
@RequiredArgsConstructor
public class AlbumService {
    private final JsonPlaceholderClient client;

    @Cacheable(value = "allAlbums")
    public AlbumJsonDTO[] getAllAlbums() {
        return client.getAllAlbums();
    }

    @Cacheable(value = "albums", key = "#id")
    public AlbumJsonDTO getAlbumById(int id) {
        return client.getAlbumById(id);
    }

    @Cacheable(value = "albumsByUserId", key="#userId")
    public AlbumJsonDTO[] getAlbumsByUserId(int userId) {
        return client.getAlbumsByUserId(userId);
    }

    @CacheEvict(value = "albums", key = "#id")
    public void deleteAlbum(int id) {
        client.deleteAlbum(id);
    }

    @CachePut(value = "albums", key = "#result.id")
    public AlbumJsonDTO createAlbum(AlbumJsonDTO album) {
        return client.createAlbum(album);
    }

    @CachePut(value = "albums", key = "#id")
    public AlbumJsonDTO updateAlbum(AlbumJsonDTO album, int id) {
        client.updateAlbum(album, id);
        return album;
    }
}
