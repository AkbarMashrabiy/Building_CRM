package uz.nova.buildingcrm.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.nova.buildingcrm.model.enums.RoleName;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthUserDTO {
    @NotBlank(message = "id cannot be null")
    private String id;
    @NotBlank(message = "fullName cannot be null")
    private String fullName;
    @NotBlank(message = "username cannot be null")
    private String username;
    private boolean active = true;
    @NotBlank(message = "roleName cannot be null")
    @Enumerated(EnumType.STRING)
    private Set<RoleName> roles;
}
