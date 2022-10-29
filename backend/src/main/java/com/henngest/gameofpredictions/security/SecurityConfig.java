package com.henngest.gameofpredictions.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
public class SecurityConfig {

  @Value("${jwt.public.key}") private RSAPublicKey rsaPublicKey;

  @Value("${jwt.private.key}") private RSAPrivateKey rsaPrivateKey;

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetailsService service = new MyUserDetailsService();
    return service;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider
  daoAuthenticationProvider(UserDetailsService userDetailsService,
                            PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider daoAuthenticationProvider =
        new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    return daoAuthenticationProvider;
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(this.rsaPublicKey).build();
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    var jwk = new RSAKey.Builder(this.rsaPublicKey)
                  .privateKey(this.rsaPrivateKey)
                  .build();
    var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  public JwtAuthenticationProvider
  jwtAuthenticationProvider(JwtDecoder jwtDecoder) {
    return new JwtAuthenticationProvider(jwtDecoder);
  }

  @Bean
  public AuthenticationManager
  authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider,
                        JwtAuthenticationProvider jwtAuthenticationProvider) {
    List<AuthenticationProvider> providers =
        new ArrayList<AuthenticationProvider>();
    providers.add(jwtAuthenticationProvider);
    providers.add(daoAuthenticationProvider);
    return new ProviderManager(providers);
  }

  @Bean
  @Order(1)
  public SecurityFilterChain
  loginFilterChain(HttpSecurity http,
                   AuthenticationManager authenticationManager)
      throws Exception {
    JsonPostLoginFilter jsonPostLoginFilter = new JsonPostLoginFilter();
    jsonPostLoginFilter.setAuthenticationManager(authenticationManager);
    jsonPostLoginFilter.setAuthenticationSuccessHandler(
        new ForwardAuthenticationSuccessHandler("/login"));
    return http.antMatcher("/login")
        .authorizeRequests()
        .anyRequest()
        .permitAll()
        .and()
        .addFilterAt(jsonPostLoginFilter,
                     UsernamePasswordAuthenticationFilter.class)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .disable()
        .csrf()
        .disable()
        .build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/register")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/foo")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/bar")
        .authenticated()
        .and()
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .and()
        .csrf()
        .disable()
        .build();
  }
}
