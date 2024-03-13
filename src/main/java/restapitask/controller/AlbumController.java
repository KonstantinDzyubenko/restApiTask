package restapitask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restapitask.dto.AlbumJsonDTO;
import restapitask.service.AlbumService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService service;

    @GetMapping("/api/albums")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ALBUMS_VIEWER')")
    public List<AlbumJsonDTO> getAllAlbums() {
        return service.getAllAlbums();
    }

    @GetMapping("/api/albums/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ALBUMS_VIEWER')")
    public AlbumJsonDTO getAlbumById(@PathVariable int id) {
        return service.getAlbumById(id);
    }

    @GetMapping("/api/users/{userId}/albums")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ALBUMS_VIEWER')")
    public List<AlbumJsonDTO> getAlbumsByUserId(@PathVariable int userId) {
        return service.getAlbumsByUserId(userId);
    }

    @DeleteMapping("/api/albums/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ALBUMS_EDITOR')")
    public String deleteAlbum(@PathVariable int id) {
        return service.deleteAlbum(id);
    }

    @PostMapping("/api/albums")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ALBUMS_EDITOR')")
    public AlbumJsonDTO createAlbum(@RequestBody AlbumJsonDTO album) {
        return service.createAlbum(album);
    }

    @PutMapping("/api/albums/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ALBUMS_EDITOR')")
    public AlbumJsonDTO updateAlbum(@RequestBody AlbumJsonDTO album, @PathVariable int id) {
        return service.updateAlbum(album, id);
    }
}
