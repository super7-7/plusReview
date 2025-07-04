package com.sparta.personalProject.dto.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    //속성
    @NotBlank(message = "null/공백은 입력 불가합니다.")
    @Email(message = "email 형식을 입력하세요.")
    private String userEmail;

    @NotBlank(message = "null/공백은 입력 불가합니다.")
    private String password;

    //생성자
    public LoginRequestDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    //기능
    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }
}
