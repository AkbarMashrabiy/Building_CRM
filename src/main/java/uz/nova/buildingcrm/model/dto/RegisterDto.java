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
public class RegisterDto {
    @NotBlank(message = "fullName cannot be null")
    private String fullName;
    @NotBlank(message = "username cannot be null")
    private String username;
    @NotBlank(message = "password cannot be null")
    private String password;
    @NotBlank(message = "roleName cannot be null")
    @Enumerated(EnumType.STRING)
    private Set<RoleName> roleName;
}
