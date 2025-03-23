package com.example.user.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/23 下午3:09
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 用户 ID（主键）

    @Column(nullable = false, length = 50, unique = true)
    private String username; // 用户名

    @Column(nullable = false, length = 255)
    private String password; // 加密存储的密码

    @Column(nullable = false, length = 100, unique = true)
    private String email; // 邮箱地址

    @Column(length = 20, unique = true)
    private String phone; // 手机号码

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender = Gender.OTHER; // 性别（枚举类型）

    @Temporal(TemporalType.DATE)
    private Date birthDate; // 生日

    @Column(length = 255)
    private String avatarUrl; // 头像链接

    @Column(nullable = false)
    private Boolean status = true; // 账号状态（true=正常, false=禁用）

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date(); // 创建时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt = new Date(); // 更新时间

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public enum Gender {
        MALE, FEMALE, OTHER
    }


}
