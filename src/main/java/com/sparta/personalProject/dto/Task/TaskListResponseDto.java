package com.sparta.personalProject.dto.Task;

import java.util.List;

public class TaskListResponseDto {
    //속성
    private Integer status;
    private String message;
    private List<TaskDto> taskList;

    //생성자
    public TaskListResponseDto(Integer status, String message, List<TaskDto> taskList) {
        this.status = status;
        this.message = message;
        this.taskList = taskList;
    }

    //기능
    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<TaskDto> getTaskList() {
        return taskList;
    }
}
