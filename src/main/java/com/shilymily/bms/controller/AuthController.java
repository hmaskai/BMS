package com.shilymily.bms.controller;

import com.shilymily.bms.entity.ApplicationUser;
import com.shilymily.bms.entity.Role;
import com.shilymily.bms.entity.RoleName;
import com.shilymily.bms.payload.ApiResponse;
import com.shilymily.bms.payload.JwtAuthenticationResponse;
import com.shilymily.bms.payload.LoginRequest;
import com.shilymily.bms.payload.SignUpRequest;
import com.shilymily.bms.repository.ApplicationUserRepository;
import com.shilymily.bms.repository.RoleRepository;
import com.shilymily.bms.security.JwtTokenProvider;
import com.shilymily.bms.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by hmaskai on 10/3/18.
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping(path = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsernameOrEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
        }
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws Throwable{
        if(applicationUserRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.status(BAD_REQUEST).body("Username is already taken!");
        }

        if(applicationUserRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(BAD_REQUEST).body("Email Address already in use!");
        }

        // Creating user's account
        ApplicationUser applicationUser = new ApplicationUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new Throwable("User Role not set."));

        applicationUser.setRoles(Collections.singleton(userRole));

        ApplicationUser result = applicationUserRepository.save(applicationUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}