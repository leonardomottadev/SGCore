package br.com.sgcore.sgcore_cloud.modules.core.auditing;

import br.com.sgcore.sgcore_cloud.modules.core.security.CurrentAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new CurrentAuditorAware();
    }
}
