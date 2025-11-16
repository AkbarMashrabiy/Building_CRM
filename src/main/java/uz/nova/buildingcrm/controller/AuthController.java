package uz.nova.buildingcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.nova.buildingcrm.model.dto.LoginDto;
import uz.nova.buildingcrm.model.dto.RegisterDto;
import uz.nova.buildingcrm.model.dto.AuthUserDTO;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.entity.AuthUser;
import uz.nova.buildingcrm.security.JwtProvider;
import uz.nova.buildingcrm.service.impl.AuthUserServiceImpl;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthUserServiceImpl service;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        return service.login(loginDto);
    }

//    @PreAuthorize("hasAnyRole('MANAGER')")
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto registerDto) {
        MyResponse register = service.register(registerDto);
        if (register == MyResponse.SUCCESSFULLY_CREATED) {
            return ResponseEntity.ok(register);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(register);
    }

    @GetMapping("/users")
//    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<Optional<List<AuthUserDTO>>> users()  {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        String username = jwtProvider.extractUsername(refreshToken);
        Optional<AuthUser> authUser = service.getByUsername(username);
        if (authUser.isPresent() && jwtProvider.isTokenValid(refreshToken, authUser.get())){
            String newAccessToken = jwtProvider.generateAccessToken(authUser.get());
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        }
        return ResponseEntity.status(401).body("Invalid refresh token");
    }
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("username") String username) {
        return service.delete(username) ? ResponseEntity.ok().build() : ResponseEntity.status(401).build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestBody String test) {
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
