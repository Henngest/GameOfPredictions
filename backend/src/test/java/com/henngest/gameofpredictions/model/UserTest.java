package com.henngest.gameofpredictions.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
  User user;

  @BeforeEach
  public void beforeEach() {
    user = new User("username", "password");
  }

  @Test
  void testGetAuthorities() {
    assertTrue(user.getAuthorities().isEmpty());
  }

  @Test
  void testIsAccountNonExpired() {
    assertTrue(user.isAccountNonExpired());
  }

  @Test
  void testIsAccountNonLocked() {
    assertTrue(user.isAccountNonLocked());
  }

  @Test
  void testIsCredentialsNonExpired() {
    assertTrue(user.isCredentialsNonExpired());
  }

  @Test
  void testIsEnabled() {
    assertTrue(user.isEnabled());
  }
}
