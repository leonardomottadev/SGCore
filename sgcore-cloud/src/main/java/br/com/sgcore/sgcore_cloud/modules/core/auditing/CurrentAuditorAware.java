package br.com.sgcore.sgcore_cloud.modules.core.auditing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

@Slf4j
public class CurrentAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            log.debug("SecurityContext does not contain an authenticated Authentication");
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Jwt jwt) {
            String username = jwt.getClaimAsString("preferred_username");

            log.debug("Auditor identified via JWT: {}", username);

            return Optional.ofNullable(username);
        }

        if(principal != null) {
            log.debug("Principal is not a JWT. Received type={}",
                    principal.getClass().getName());
        }

        return Optional.empty();
    }
}