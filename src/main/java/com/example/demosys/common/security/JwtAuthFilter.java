package com.example.demosys.common.security;

import com.example.demosys.common.constants.HeaderConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;




/**
 * JwtAuthFilter（最小版）
 * 说明：
 * - 这里只做“能解析 JWT 并放行”的骨架
 * - 你后续要把 UserPrincipal 放入 SecurityContext，需要补 UserDetails + Authentication
 */
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final byte[] secret;

    public JwtAuthFilter(String secret) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(auth)) {
            auth = request.getHeader(HeaderConstants.AUTHORIZATION);
        }

        if (StringUtils.hasText(auth) && auth.startsWith(HeaderConstants.BEARER_PREFIX)) {
            String token = auth.substring(HeaderConstants.BEARER_PREFIX.length()).trim();
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(new SecretKeySpec(secret, "HmacSHA256"))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.get("username", String.class);
                if (username == null) username = claims.getSubject();

                String displayName = claims.get("displayName", String.class);

                UserPrincipal principal = new UserPrincipal(
                        username,
                        displayName,
                        Collections.emptySet(),
                        Collections.emptySet()
                );

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                principal,
                                null,
                                Collections.emptyList()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                log.warn("JWT parse failed: {}", e.getMessage());
                // 可选：直接 401
                // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // return;
            }
        }

        chain.doFilter(request, response);
    }

}
