package com.sparta.personalProject.dto.Task;

public class TaskDeleteResponseDto {
    //속성
    private Integer status;
    private String massage;

    //생성자
    public TaskDeleteResponseDto(Integer status, String massage) {
        this.status = status;
        this.massage = massage;
    }

    //기능
    public Integer getStatus() {
        return status;
    }

    public String getMassage() {
        return massage;
    }
}
