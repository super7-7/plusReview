package com.sparta.personalProject.dto.Member;

public class MemberDeleteResponseDto {
    //속성
    private Integer status;
    private String message;

    //생성자
    public MemberDeleteResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    //기능
    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
