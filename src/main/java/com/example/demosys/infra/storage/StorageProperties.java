package com.example.demosys.infra.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * StorageProperties
 * application.yml 示例：
 * storage:
 *   provider: local   # local|minio|oss
 *   local:
 *     base-dir: ./data/files
 *   minio:
 *     endpoint: http://127.0.0.1:9000
 *     access-key: minioadmin
 *     secret-key: minioadmin
 *     bucket: demosys
 */
@Data
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String provider = "local";

    private Local local = new Local();
    private Minio minio = new Minio();

    @Data
    public static class Local {
        private String baseDir = "./data/files";
    }

    @Data
    public static class Minio {
        private String endpoint;
        private String accessKey;
        private String secretKey;
        private String bucket = "demosys";
    }
}
