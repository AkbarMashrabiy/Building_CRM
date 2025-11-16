package uz.nova.buildingcrm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.AuthUserDTO;
import uz.nova.buildingcrm.model.dto.LoginDto;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.RegisterDto;
import uz.nova.buildingcrm.model.entity.AuthUser;

import java.util.List;
import java.util.Optional;

@Component
public interface AuthUserService {
    boolean isExist(String username);
    ResponseEntity<?> login(LoginDto loginDto);
    MyResponse register(RegisterDto registerDto);
    boolean isActive(String username);
    Optional<List<AuthUserDTO>> getAllUsers();
    Optional<AuthUser> getByUsername(String username);
    boolean delete(String username);
    AuthUser getCurrentUser();

}
