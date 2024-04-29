package com.pbs.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * @return - this method returns a value who is updating the record
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS");
    }
}
