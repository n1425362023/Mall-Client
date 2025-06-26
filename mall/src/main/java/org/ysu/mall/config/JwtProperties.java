package org.ysu.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private boolean enabled = true; // 默认启用
    private String secret = "secretsecretsecretsecretsecretsecretsecretsecret";
    private long expiration = 86400000; // 默认 24 小时
}