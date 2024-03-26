package com.sbgroup.haras.security;

import com.sbgroup.haras.repositories.AuthRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired()
  private TokenService tokenService;

  @Autowired()
  private AuthRepository authRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var token = this.recoverToken(request);
    if (token != null) {
      var login = tokenService.validateToken(token);
      UserDetails auth = authRepository.findByLogin(login);

      var authentication = new UsernamePasswordAuthenticationToken(auth, null, auth.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null) {
      return null;
    } else {
      return authHeader.replace("Bearer ", "");
    }
  }
}
