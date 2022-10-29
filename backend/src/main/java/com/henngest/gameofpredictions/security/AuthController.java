package com.henngest.gameofpredictions.security;

import com.henngest.gameofpredictions.model.User;
import com.henngest.gameofpredictions.model.UserWrapper;
import com.henngest.gameofpredictions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<User> login() {
    SecurityContext context = SecurityContextHolder.getContext();
    UserWrapper user = (UserWrapper)context.getAuthentication().getPrincipal();


    return ResponseEntity.ok()
        .header(org.springframework.http.HttpHeaders.AUTHORIZATION,
                user.getJwt())
        .body(user.getUser());
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody
                                         @Validated AuthRequest request) {
    userRepository.save(new User(request.username(),
                                 passwordEncoder.encode(request.password())));
    return ResponseEntity.ok().body("Success.");
  }

  @GetMapping("/foo")
  public ResponseEntity<String> foo() {
    return ResponseEntity.ok().body("Hello world!");
  }

  @GetMapping("/bar")
  public ResponseEntity<String> bar() {
    return ResponseEntity.ok().body("Hello world!");
  }
}
