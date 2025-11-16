package uz.nova.buildingcrm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.nova.buildingcrm.model.enums.RoleName;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultLogin {
    private String message;
    private boolean active;
    private String accessToken;
    private String refreshToken;
    private Set<RoleName> role;
}