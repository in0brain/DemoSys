package com.example.demosys.common.security;

import com.example.demosys.common.constants.HeaderConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

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
                        // ✅ 修正：必须是 HmacSHA256（别写成 HmacSHA）
                        .setSigningKey(new SecretKeySpec(secret, "HmacSHA256"))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.get("username", String.class);
                if (username == null) username = claims.getSubject();

                String displayName = claims.get("displayName", String.class);

                // roles 可能是 List，也可能解析成 ArrayList<Object>，统一转成 List<String>
                List<String> roles = extractRoles(claims.get("roles"));

                // ✅ 修正：用 List<? extends GrantedAuthority> 或者转成 List<GrantedAuthority>
                List<? extends GrantedAuthority> authorities = roles.stream()
                        .filter(Objects::nonNull)
                        .map(String::valueOf)
                        .filter(s -> !s.isBlank())
                        .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UserPrincipal principal = new UserPrincipal(
                        username,
                        displayName,
                        Collections.emptySet(),
                        Collections.emptySet()
                );

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(principal, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 调试用（确认角色进来了）
                 log.info("[JWT] user={} roles={} auth={}", username, roles, authorities);

            } catch (Exception e) {
                log.warn("JWT parse failed: {}", e.getMessage());
                // 可选：严格模式直接 401
                // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // return;
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * 把 claims.roles 解析成 List<String>，兼容：
     * - null
     * - List<String>
     * - List<Object>
     * - 单个 String
     */
    private List<String> extractRoles(Object raw) {
        if (raw == null) return Collections.emptyList();

        if (raw instanceof String) {
            String s = ((String) raw).trim();
            return s.isEmpty() ? Collections.emptyList() : List.of(s);
        }

        if (raw instanceof Collection<?>) {
            List<String> out = new ArrayList<>();
            for (Object o : (Collection<?>) raw) {
                if (o != null) {
                    String s = String.valueOf(o).trim();
                    if (!s.isEmpty()) out.add(s);
                }
            }
            return out;
        }

        // 兜底
        String s = String.valueOf(raw).trim();
        return s.isEmpty() ? Collections.emptyList() : List.of(s);
    }
}
