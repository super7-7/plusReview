package com.sparta.personalProject.dto.Member;

public class LoginResponseDto {
    //속성
    private final String token;

    //생성자
    public LoginResponseDto(String token) {
        this.token = token;
    }

    //기능
    public String getToken() {
        return token;
    }
}
