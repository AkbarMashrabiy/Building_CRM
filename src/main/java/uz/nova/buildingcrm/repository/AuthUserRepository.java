package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nova.buildingcrm.model.entity.AuthUser;

import javax.validation.constraints.NotBlank;
import java.util.Optional;


public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findAuthUserByUsername(String username);
    boolean existsByUsernameAndActiveTrue(String username);
    boolean existsByUsername(String username);

    boolean existsByFullName(@NotBlank(message = "Full name shouldn't be null") String fullName);
}
