
package com.taskflow.backend.config;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.taskflow.backend.util.JwtUtil;
import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

 private final JwtUtil jwtUtil;

 @Override
 protected void doFilterInternal(HttpServletRequest request,
   HttpServletResponse response,
   FilterChain filterChain)
   throws ServletException, IOException {

  String header = request.getHeader("Authorization");
  if(header != null && header.startsWith("Bearer ")) {
   String token = header.substring(7);
   String email = jwtUtil.extractEmail(token);
   if(email != null) {
    UsernamePasswordAuthenticationToken auth =
     new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(auth);
   }
  }
  filterChain.doFilter(request, response);
 }
}
