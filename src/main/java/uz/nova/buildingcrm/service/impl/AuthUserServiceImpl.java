package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nova.buildingcrm.mapper.AuthUserMapper;
import uz.nova.buildingcrm.model.dto.*;
import uz.nova.buildingcrm.model.entity.AuthUser;
import uz.nova.buildingcrm.repository.AuthUserRepository;
import uz.nova.buildingcrm.security.JwtProvider;
import uz.nova.buildingcrm.service.AuthUserService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final AuthUserMapper authUserMapper;

    @Override
    public boolean isExist(String username) {
        return authUserRepository.existsByUsername(username);
    }

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        Optional<AuthUser> byUsername = authUserRepository.findAuthUserByUsername(loginDto.getUsername());
        if (byUsername.isPresent()) {
            AuthUser authUser = byUsername.get();
            if (passwordEncoder.matches(loginDto.getPassword(), authUser.getPassword())) {
                String accessToken = jwtProvider.generateAccessToken(authUser);
                String refreshToken = jwtProvider.generateRefreshToken(authUser);

                return ResponseEntity.ok(
                        new ResultLogin(
                                "Successfully login",
                                true,
                                accessToken,
                                refreshToken,
                                byUsername.get().getRoles()
                        )
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MyResponse.WRONG_PASSWORD);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MyResponse.USER_NOT_FOUND);
    }

    @Override
    @Transactional
    public MyResponse register(RegisterDto registerDto) {
        if (isExist(registerDto.getUsername())) {
            return MyResponse.USERNAME_EXISTS;
        }
        if (authUserRepository.existsByFullName(registerDto.getFullName())) {
            return MyResponse.FAILED_TO_CREATE;
        }

        AuthUser authUser = new AuthUser();
        authUser.setUsername(registerDto.getUsername());
        authUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        authUser.setFullName(registerDto.getFullName());
        authUser.setActive(true);
        authUser.setRoles(registerDto.getRoleName());
        authUserRepository.save(authUser);

        return MyResponse.SUCCESSFULLY_CREATED;
    }

    @Override
    public boolean isActive(String username) {
        return authUserRepository.existsByUsernameAndActiveTrue(username);
    }

    @Override
    public Optional<List<AuthUserDTO>> getAllUsers() {
        List<AuthUser> all = authUserRepository.findAll();
        if (all.isEmpty()) {
            return Optional.empty();
        }
        return authUserMapper.toDto(all);
    }

    @Override
    public Optional<AuthUser> getByUsername(String username) {
        return authUserRepository.findAuthUserByUsername(username);
    }

    @Override
    @Transactional
    public boolean delete(String username) {
        Optional<AuthUser> user = getByUsername(username);
        if (user.isPresent()) {
            user.get().setActive(false);
            return true;
        }
        return false;
    }

    @Override
    public AuthUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = (AuthUser) authentication.getPrincipal();
        return user;
    }
}
