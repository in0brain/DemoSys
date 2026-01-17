package com.example.demosys.infra.storage;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * StorageConfig
 * 根据 storage.provider 选择具体实现。
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfig {

    @Bean
    public StorageService storageService(StorageProperties props) {
        if ("minio".equalsIgnoreCase(props.getProvider())) {
            return new MinioStorageService(props);
        }
        // default local
        return new LocalStorageService(props);
    }
}
