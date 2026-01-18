package com.example.demosys.common.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityConfig {

    private final JwtProperties jwtProperties;

    public SecurityConfig(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        // 建议加非空校验，避免再次 NPE（可选）
        if (jwtProperties.getSecret() == null || jwtProperties.getSecret().trim().isEmpty()) {
            throw new IllegalStateException("Missing config: security.jwt.secret");
        }
        return new JwtAuthFilter(jwtProperties.getSecret());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

                // -------- public
                .antMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/error"
                ).permitAll()
                .antMatchers("/auth/**").permitAll()

                // -------- authenticated basic
                .antMatchers("/me/**").authenticated()

                // =========================================================
                // ✅ 动作级规则：必须写在模块级 /education/** 之前（顺序很重要）
                // education - study plans
                // - 学生：编辑/提交
                // - 导师/管理员：审批/驳回
                // - 查看：学生/导师/管理员
                // =========================================================
                .antMatchers(HttpMethod.POST,
                        "/education/study-plans/*/approve",
                        "/education/study-plans/*/reject"
                ).hasAnyRole("TEACHER", "ADMIN")

                .antMatchers(HttpMethod.PUT,
                        "/education/study-plans/*"
                ).hasRole("STUDENT")

                .antMatchers(HttpMethod.POST,
                        "/education/study-plans/*/submit"
                ).hasRole("STUDENT")

                .antMatchers(HttpMethod.GET,
                        "/education/study-plans",
                        "/education/study-plans/*"
                ).hasAnyRole("STUDENT", "TEACHER", "ADMIN")

                // =========================================================
                // ✅ 模块级兜底规则（与你 Swagger 分组一致）
                // =========================================================
                .antMatchers("/admissions/**").hasRole("ADMIN")
                .antMatchers("/education/**").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                .antMatchers("/defense/**").hasAnyRole("STUDENT", "TEACHER", "ADMIN", "EXPERT")

                // 其他接口：都需要登录
                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, res, e) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .accessDeniedHandler((req, res, e) -> res.sendError(HttpServletResponse.SC_FORBIDDEN));

        return http.build();
    }
}
