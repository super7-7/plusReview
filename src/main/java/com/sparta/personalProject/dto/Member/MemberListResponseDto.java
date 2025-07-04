package com.sparta.personalProject.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberListResponseDto {
    //속성
    private Integer status;
    private String message;
    private List<MemberDto> memberList;

    //생성자
    public MemberListResponseDto(Integer status, String message, List<MemberDto> memberList) {
        this.status = status;
        this.message = message;
        this.memberList = memberList;
    }

    //기능
    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<MemberDto> getMemberList() {
        return memberList;
    }
}
