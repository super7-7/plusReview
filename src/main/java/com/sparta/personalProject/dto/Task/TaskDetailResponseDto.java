package com.sparta.personalProject.dto.Task;

import com.sparta.personalProject.enumdata.Priority;
import com.sparta.personalProject.enumdata.Status;

import java.time.LocalDateTime;

public class TaskDetailResponseDto {
    //속성
    private Integer status;
    private String message;
    private Long taskId;
    private Long userId;
    private String title;
    private String description;
    private String username;
    private Priority priority;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private Status taskStatus;

    //생성자

    public TaskDetailResponseDto(Integer status, String message, Long taskId, Long userId, String title, String description, String username, Priority priority, LocalDateTime startDate, LocalDateTime dueDate, Status taskStatus) {
        this.status = status;
        this.message = message;
        this.taskId = taskId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.username = username;
        this.priority = priority;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }
}
