package com.sparta.personalProject.dto.Task;

import com.sparta.personalProject.enumdata.Priority;
import com.sparta.personalProject.enumdata.Status;

import java.time.LocalDateTime;

public class TaskCreateRequestDto {
    //속성
    private Long userId;
    private String title;
    private String description;
    private String username;
    private Priority priority;
    private Status taskStatus;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    //생성자

    //기능
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

    public Status getTaskStatus() {
        return taskStatus;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
