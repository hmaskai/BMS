package com.shilymily.apigatewayservice.controllers;

import com.shilymily.apigatewayservice.models.Role;
import com.shilymily.apigatewayservice.models.RoleName;
import com.shilymily.apigatewayservice.models.User;
import com.shilymily.apigatewayservice.payloads.ApiResponse;
import com.shilymily.apigatewayservice.payloads.AuthenticationResponse;
import com.shilymily.apigatewayservice.payloads.LoginRequest;
import com.shilymily.apigatewayservice.payloads.SignUpRequest;
import com.shilymily.apigatewayservice.repositories.RoleRepository;
import com.shilymily.apigatewayservice.repositories.UserRepository;
import com.shilymily.apigatewayservice.services.UserDetailsServiceImpl;
import com.shilymily.apigatewayservice.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


/**
 * Created by hmaskai
 * 5/24/20.
 */

@RestController
@RequestMapping("/api/auth")
public class SecurityController {

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleRepository roleRepository;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                                                                                 loginRequest.getPassword()));
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(UNAUTHORIZED).body("Incorrect username or password");
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
    String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @PostMapping(path = "/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) throws Throwable {
    if (userRepository.findByUserName(signUpRequest.getUserName()).isPresent()) {
      return ResponseEntity.status(BAD_REQUEST).body("Username is already taken!");
    }

    User user = new User(signUpRequest.getUserName(), signUpRequest.getPassword());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                  .orElseThrow(() -> new Throwable("User Role not set."));
    user.setRoles(Collections.singleton(userRole));
    user.setActive(true);

    User result = userRepository.save(user);

    URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath().path("/api/users/{username}")
        .buildAndExpand(result.getUserName()).toUri();

    return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
  }

  @GetMapping(path = "test")
  public String test() {
    return "Welcome";
  }
}
