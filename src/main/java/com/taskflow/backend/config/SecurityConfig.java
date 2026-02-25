
package com.taskflow.backend.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

 private final JwtAuthenticationFilter jwtFilter;

 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http.csrf().disable()
   .authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/auth/**").permitAll()
    .anyRequest().authenticated())
   .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

  http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
 }
}
