package restapitask.management.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import restapitask.configuration.security.Role;

import java.util.List;

@Data
public class UserDTO {
    @JsonProperty("login")
    @NotBlank
    private String login;
    @JsonProperty("password")
    @NotBlank
    private String password;
    @JsonProperty("roles")
    @NotEmpty
    private List<Role> roles;
}
