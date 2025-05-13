package com.example.potato_tuto.security.jwt;

import com.example.potato_tuto.exception.requestError.InvalidTokenException;
import com.example.potato_tuto.security.CustomUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 스프링 빈 등록 안하는 이유 : 꼭 필요할 때 특정 순서에서 작동해야 해서
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, CustomUserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (uri.startsWith("/auth") || uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String bearer = request.getHeader("Authorization");
        String token = null;

        if (bearer != null && bearer.startsWith("Bearer ")) {
            token = bearer.substring(7);
        }

        if (token != null && tokenProvider.validateToken(token)) {
            if (!"access".equals(tokenProvider.getTokenType(token))) {
                throw new InvalidTokenException("AccessToken만 허용됩니다.");
            }

            String email = tokenProvider.getUserEmailFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

}