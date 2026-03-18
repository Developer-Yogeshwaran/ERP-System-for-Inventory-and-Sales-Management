package com.example.erp;

import com.example.erp.model.ERole;
import com.example.erp.model.Role;
import com.example.erp.repository.RoleRepository;
import com.example.erp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ErpApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository,
                                      UserRepository userRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            for (ERole role : ERole.values()) {
                roleRepository.findByName(role).orElseGet(() -> roleRepository.save(new Role(role)));
            }

            if (!userRepository.existsByUsername("admin")) {
                var admin = new com.example.erp.model.User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                var adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                admin.getRoles().add(adminRole);
                userRepository.save(admin);
            }
        };
    }
}
