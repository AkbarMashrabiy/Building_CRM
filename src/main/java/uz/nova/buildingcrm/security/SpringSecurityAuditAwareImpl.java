package uz.nova.buildingcrm.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class SpringSecurityAuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.of("system");
            }

            Object principal = authentication.getPrincipal();

            if (principal instanceof org.springframework.security.core.userdetails.User user) {
                return Optional.of(user.getUsername());
            } else if (principal instanceof uz.nova.buildingcrm.model.entity.AuthUser authUser) {
                return Optional.of(authUser.getUsername());
            } else if (principal instanceof String username) {
                return Optional.of(username);
            }

            return Optional.of("unknown");
        } catch (Exception e) {
            return Optional.of("system");
        }
    }
}
