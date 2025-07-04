package com.sparta.personalProject.dto.Member;

import com.sparta.personalProject.enumdata.Priority;
import com.sparta.personalProject.enumdata.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class MemberCreateRequestDto {
    //속성
    @NotBlank(message = "null/공백은 입력 불가합니다.")
    private String username;

    @NotBlank(message = "null/공백은 입력 불가합니다.")
    @Email(message = "올바른 email 형식을 입력하세요.")
    private String userEmail;

    @NotBlank(message = "null/공백은 입력 불가합니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).+$",
            message = "비밀번호는 대소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;

    //기능
    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }
}
