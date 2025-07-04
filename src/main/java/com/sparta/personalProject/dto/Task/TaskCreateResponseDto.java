package com.sparta.personalProject.dto.Task;

public class TaskCreateResponseDto {
    //속성
    private Integer status;
    private String message;
    private Long taskId;
    private Long userId;

    //생성자

    public TaskCreateResponseDto(Integer status, String message, Long taskId, Long userId) {
        this.status = status;
        this.message = message;
        this.taskId = taskId;
        this.userId = userId;
    }

    //기능
    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getUserId() {
        return userId;
    }
}
