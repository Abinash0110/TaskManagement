
package com.taskflow.backend.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.taskflow.backend.service.AuthService;
import com.taskflow.backend.dto.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

 private final AuthService authService;

 @PostMapping("/register")
 public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
  authService.register(request);
  return ResponseEntity.status(HttpStatus.CREATED).build();
 }

 @PostMapping("/login")
 public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
  return ResponseEntity.ok(authService.login(request));
 }
}
