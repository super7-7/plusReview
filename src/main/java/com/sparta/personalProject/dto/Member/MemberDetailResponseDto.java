package com.sparta.personalProject.dto.Member;

import java.time.LocalDateTime;

public class MemberDetailResponseDto {
    //속성
    private Integer status;
    private String message;
    private Long userId;
    private String username;
    private String userEmail;
    private LocalDateTime createdAt;

    //생성자
    public MemberDetailResponseDto(Integer status, String message, Long userId, String username, String userEmail, LocalDateTime createdAt) {
        this.status = status;
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
    }
//    public MemberDetailResponseDto(Integer status, String message, Member member) {
//        this.status = status;
//        this.message = message;
//        this.userId = member.getUserId();
//        this.username = member.getUsername();
//        ...
//    } 이렇게 하면 MemberService에서 데이터 준비할 필요없이 DTO 만들 수 있음

    //기능
    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

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
