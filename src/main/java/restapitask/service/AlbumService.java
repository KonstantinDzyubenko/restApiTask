package restapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import restapitask.client.JsonPlaceholderClient;
import restapitask.dto.AlbumJsonDTO;

@Component
@RequiredArgsConstructor
public class AlbumService {
    private final JsonPlaceholderClient client;

    public AlbumJsonDTO[] getAllAlbums() {
        return client.getAllAlbums();
    }

    public AlbumJsonDTO getAlbumById(int id) {
        return client.getAlbumById(id);
    }

    public AlbumJsonDTO[] getAlbumsByUserId(int userId) {
        return client.getAlbumsByUserId(userId);
    }

    public void deleteAlbum(int id) {
        client.deleteAlbum(id);
    }

    public AlbumJsonDTO createAlbum(AlbumJsonDTO album) {
        return client.createAlbum(album);
    }

    public AlbumJsonDTO updateAlbum(AlbumJsonDTO album, int id) {
        client.updateAlbum(album, id);
        return album;
    }
}
