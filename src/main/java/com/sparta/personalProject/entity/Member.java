package com.sparta.personalProject.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "members")
@EntityListeners(AuditingEntityListener.class)
public class Member {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_email", nullable = false, length = 100, unique = true)
    private String userEmail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.createdAt = now;
    }

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    //생성자
    protected Member() {}

    /**
     * memberCreateRequestDto 정보를 꺼내와서 entity를 만들 때 사용
     */
    public Member(String username, String userEmail, String password) {
        this.username = username;
        this.userEmail = userEmail;
        this.password = password;
    }
    //기능
    //update 메서드
    public void update(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //soft-delete 메서드
    public void delete() {
        this.isDeleted = true;
    }
    //게터
    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
