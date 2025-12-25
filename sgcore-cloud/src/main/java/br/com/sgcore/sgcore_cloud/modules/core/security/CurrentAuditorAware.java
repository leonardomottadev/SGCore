package br.com.sgcore.sgcore_cloud.modules.core.security;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class CurrentAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}