package com.pbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class McProj01Sc02LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(McProj01Sc02LoansApplication.class, args);
    }

}
