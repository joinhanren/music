package com.example.globalConfig.minio;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : join
 * @Description :
 * Date : 2025/2/24 下午9:25
 */
@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endPoint).
                credentials(accessKey, bucketName)
                .build();
    }


}
