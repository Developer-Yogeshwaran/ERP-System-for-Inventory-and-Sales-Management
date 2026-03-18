package com.example.erp.controller;

import com.example.erp.model.ERole;
import com.example.erp.model.Role;
import com.example.erp.model.User;
import com.example.erp.payload.request.LoginRequest;
import com.example.erp.payload.request.SignupRequest;
import com.example.erp.payload.response.JwtResponse;
import com.example.erp.payload.response.MessageResponse;
import com.example.erp.repository.RoleRepository;
import com.example.erp.repository.UserRepository;
import com.example.erp.security.JwtUtils;
import com.example.erp.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_SALES)
                    .orElseThrow(() -> new RuntimeException("Error: Role not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.trim().toUpperCase()) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        roles.add(adminRole);
                        break;
                    case "PURCHASE":
                        Role purchaseRole = roleRepository.findByName(ERole.ROLE_PURCHASE)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        roles.add(purchaseRole);
                        break;
                    case "INVENTORY":
                        Role inventoryRole = roleRepository.findByName(ERole.ROLE_INVENTORY)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        roles.add(inventoryRole);
                        break;
                    case "ACCOUNTANT":
                        Role accountantRole = roleRepository.findByName(ERole.ROLE_ACCOUNTANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        roles.add(accountantRole);
                        break;
                    default:
                        Role salesRole = roleRepository.findByName(ERole.ROLE_SALES)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        roles.add(salesRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
