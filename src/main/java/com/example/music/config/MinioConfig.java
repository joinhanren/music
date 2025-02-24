package com.example.music.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : join
 * @Description :
 * Date : 2025/2/24 下午9:25
 */
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;


    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(endPoint).
                credentials(accessKey,bucketName)
                .build();
    }



}
