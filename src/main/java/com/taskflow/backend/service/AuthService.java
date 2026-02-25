
package com.taskflow.backend.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.taskflow.backend.repository.UserRepository;
import com.taskflow.backend.entity.User;
import com.taskflow.backend.dto.*;
import com.taskflow.backend.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

 private final UserRepository userRepository;
 private final PasswordEncoder passwordEncoder;
 private final JwtUtil jwtUtil;

 public void register(RegisterRequest request) {
  if(userRepository.findByEmail(request.getEmail()).isPresent())
   throw new RuntimeException("Email already exists");

  User user = new User();
  user.setFullName(request.getFullName());
  user.setEmail(request.getEmail());
  user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
  userRepository.save(user);
 }

 public AuthResponse login(LoginRequest request) {
  User user = userRepository.findByEmail(request.getEmail())
   .orElseThrow(() -> new RuntimeException("Invalid credentials"));

  if(!passwordEncoder.matches(request.getPassword(), user.getPasswordHash()))
   throw new RuntimeException("Invalid credentials");

  return new AuthResponse(jwtUtil.generateToken(user.getEmail()));
 }
}
