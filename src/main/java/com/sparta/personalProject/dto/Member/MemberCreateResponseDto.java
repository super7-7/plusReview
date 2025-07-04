package com.sparta.personalProject.dto.Member;

public class MemberCreateResponseDto {
    //속성
    private Integer status;
    private String message;
    private Long userId;

    //생성자
    public MemberCreateResponseDto(Integer status, String message, Long userId) {
        this.status = status;
        this.message = message;
        this.userId = userId;
    }

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
}
