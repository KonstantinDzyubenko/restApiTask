package p1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AlbumJsonDTO {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
}
