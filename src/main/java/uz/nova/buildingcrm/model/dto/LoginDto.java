package uz.nova.buildingcrm.model.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {

    @NotBlank(message = "Username shouldn't be null")
    private String username;

    @NotBlank(message = "Password shouldn't be null")
    private String password;
}
