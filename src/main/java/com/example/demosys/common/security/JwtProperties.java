package com.example.demosys.common.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    /**
     * HS256 secret（建议 >= 32 字符）
     */
    private String secret;

    /**
     * token 有效期（秒）
     */
    private long ttlSeconds = 7200;

}
