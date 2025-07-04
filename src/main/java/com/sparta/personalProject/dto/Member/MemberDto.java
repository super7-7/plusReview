package com.sparta.personalProject.dto.Member;

import java.time.LocalDateTime;

/**
 * MemberListResponseDto에서 활용하는 객체
 */
public class MemberDto {
    //속성
    private Long userId;
    private String username;
    private String userEmail;
    private LocalDateTime createdAt;

    //생성자
    public MemberDto(Long userId, String username, String userEmail, LocalDateTime createdAt) {
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
    }

    //기능
    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
