package com.example.demosys.common.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AuditAspect
 * AOP 审计切面：记录操作、耗时、用户、IP、URI、异常等。
 *
 * 默认行为：
 * - 不强依赖你的业务类，只使用日志输出（你后续要落库/发MQ，再加一个 AuditPublisher 即可）
 * - 优先从 Spring Security 获取当前用户名；失败则用 anonymous
 */
@Slf4j
@Aspect
@Component
public class AuditAspect {

    private final ObjectMapper objectMapper;

    public AuditAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Around("@annotation(com.example.demosys.common.audit.AuditEvent)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        AuditEvent audit = method.getAnnotation(AuditEvent.class);

        long start = System.currentTimeMillis();

        HttpServletRequest req = currentRequest();
        String uri = (req != null) ? req.getRequestURI() : "";
        String httpMethod = (req != null) ? req.getMethod() : "";
        String ip = (req != null) ? clientIp(req) : "";
        String user = currentUser();

        Map<String, Object> record = new LinkedHashMap<>();
        record.put("ts", LocalDateTime.now().toString());
        record.put("action", audit.action());
        record.put("module", audit.module());
        record.put("resource", audit.resource());
        record.put("desc", audit.description());
        record.put("user", user);
        record.put("ip", ip);
        record.put("httpMethod", httpMethod);
        record.put("uri", uri);
        record.put("method", method.getDeclaringClass().getSimpleName() + "#" + method.getName());

        if (audit.recordArgs()) {
            record.put("args", safeJson(pjp.getArgs()));
        }

        try {
            Object result = pjp.proceed();
            long costMs = System.currentTimeMillis() - start;

            record.put("ok", true);
            record.put("costMs", costMs);

            if (audit.recordResult()) {
                record.put("result", safeJson(result));
            }

            log.info("[AUDIT] {}", safeJson(record));
            return result;
        } catch (Throwable ex) {
            long costMs = System.currentTimeMillis() - start;

            record.put("ok", false);
            record.put("costMs", costMs);
            record.put("errorType", ex.getClass().getName());
            record.put("errorMessage", ex.getMessage());

            if (audit.recordStacktrace()) {
                record.put("stacktrace", stacktraceToString(ex));
            }

            log.warn("[AUDIT] {}", safeJson(record));
            throw ex;
        }
    }

    private HttpServletRequest currentRequest() {
        try {
            RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
            if (attrs == null) return null;
            Object obj = attrs.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            return (obj instanceof HttpServletRequest) ? (HttpServletRequest) obj : null;
        } catch (Throwable ignored) {
            return null;
        }
    }

    private String currentUser() {
        // 尽量不直接依赖 security 包里的 UserPrincipal 类型，避免循环依赖
        try {
            Class<?> holder = Class.forName("org.springframework.security.core.context.SecurityContextHolder");
            Object context = holder.getMethod("getContext").invoke(null);
            Object auth = context.getClass().getMethod("getAuthentication").invoke(context);
            if (auth == null) return "anonymous";

            Object principal = auth.getClass().getMethod("getPrincipal").invoke(auth);
            if (principal == null) return "anonymous";

            if (principal instanceof String) {
                String s = (String) principal;
                return "anonymousUser".equalsIgnoreCase(s) ? "anonymous" : s;
            }
            // 兼容 UserDetails.getUsername()
            try {
                Object username = principal.getClass().getMethod("getUsername").invoke(principal);
                if (username != null && StringUtils.hasText(username.toString())) {
                    return username.toString();
                }
            } catch (NoSuchMethodException ignored) {}

            // 兼容你自定义 principal：getUserId()/getId()
            try {
                Object id = principal.getClass().getMethod("getUserId").invoke(principal);
                if (id != null && StringUtils.hasText(id.toString())) return id.toString();
            } catch (NoSuchMethodException ignored) {}
            try {
                Object id = principal.getClass().getMethod("getId").invoke(principal);
                if (id != null && StringUtils.hasText(id.toString())) return id.toString();
            } catch (NoSuchMethodException ignored) {}

            return principal.toString();
        } catch (Throwable ignored) {
            return "anonymous";
        }
    }

    private String clientIp(HttpServletRequest req) {
        // 常用代理头
        String xff = req.getHeader("X-Forwarded-For");
        if (StringUtils.hasText(xff)) return xff.split(",")[0].trim();
        String xrip = req.getHeader("X-Real-IP");
        if (StringUtils.hasText(xrip)) return xrip.trim();
        return req.getRemoteAddr();
    }

    private String safeJson(Object obj) {
        if (obj == null) return "null";
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return String.valueOf(obj);
        }
    }

    private String stacktraceToString(Throwable ex) {
        StringBuilder sb = new StringBuilder(2048);
        sb.append(ex).append('\n');
        for (StackTraceElement el : ex.getStackTrace()) {
            sb.append("  at ").append(el).append('\n');
            if (sb.length() > 8000) { // 防止日志爆炸
                sb.append("  ... truncated\n");
                break;
            }
        }
        return sb.toString();
    }
}
