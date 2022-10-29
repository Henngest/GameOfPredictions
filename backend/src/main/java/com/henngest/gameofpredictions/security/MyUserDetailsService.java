package com.henngest.gameofpredictions.security;

import com.henngest.gameofpredictions.model.User;
import com.henngest.gameofpredictions.model.UserWrapper;
import com.henngest.gameofpredictions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyUserDetailsService implements UserDetailsService {

  @Autowired private UserRepository repository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    User user =  repository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("Username not found."));
    return new UserWrapper(user);
  }

  public UserDetails create(AuthRequest request) {
    User user = new User(request.username(),
                         passwordEncoder.encode(request.password()));
    repository.save(user);
    return new UserWrapper(user);
  }
}
