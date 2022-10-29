package com.henngest.gameofpredictions.model;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

public class UserWrapper implements UserDetails {
  private User user;
  @Autowired private JwtEncoder jwtEncoder;

  public UserWrapper(User user) { this.user = user; }

  public User getUser() { return this.user; }

  @Override
  public Set<GrantedAuthority> getAuthorities() {
    return new HashSet<GrantedAuthority>();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public String getJwt() {
    String roles = getAuthorities()
                       .stream()
                       .map(GrantedAuthority::getAuthority)
                       .collect(joining(" "));

    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer("gameofpredictions.com")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(3600))
            .subject(format("%s,%s", user.getId(), user.getUsername()))
            .claim("roles", roles)
            .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
