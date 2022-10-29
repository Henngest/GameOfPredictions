package com.henngest.gameofpredictions.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JsonPostLoginFilter
    extends AbstractAuthenticationProcessingFilter {

  protected JsonPostLoginFilter() {
    super(new AntPathRequestMatcher("/login", HttpMethod.POST.name()));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response)
      throws AuthenticationServiceException {
    String username;
    String password;

    try {
      Map<String, String> requestMap =
          new ObjectMapper().readValue(request.getInputStream(), Map.class);
      username = requestMap.get("username");
      password = requestMap.get("password");
    } catch (IOException e) {
      throw new AuthenticationServiceException(e.getMessage(), e);
    }

    System.out.println("AUTHENTICATING " + username + ":" + password );
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(username, password);

    Authentication authe = this.getAuthenticationManager().authenticate(token);
    System.out.println("AUTHENTICATED TO " + authe.toString());
    return authe;
  }
}
