package com.example.music.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author : join
 * @Description :
 * Date : 2025/2/24 下午9:15
 */
@Data
@Entity
@Table(name = "music")  // 指定数据库表名
public class MusicEntity  implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private long fileSize;

    @Column(unique = true, length = 32)  // MD5 适合做唯一索引
    private String md5;

    @Column(length = 10)
    private String format;

    @Column(name = "duration_seconds")
    private int durationInSeconds; // 音频时长（秒）

    @Column(name = "auth")
    private String auth;

    @Column(nullable = false)
    private boolean deleted = false;  // `byte delete` -> 改为 `boolean deleted`

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }



}
