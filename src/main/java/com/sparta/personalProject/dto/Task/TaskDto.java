package com.sparta.personalProject.dto.Task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.personalProject.enumdata.Priority;
import com.sparta.personalProject.enumdata.Status;

import java.time.LocalDateTime;

/**
 * taskListResponseDto에서 활용하는 객체입니다.
 */
public class TaskDto {
    //속성
    @JsonProperty("task_id")
    private Long taskId;
    private Long userId;
    private String title;
    private String description;
    private String username;
    private Priority priority;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    @JsonProperty("task_status") // taskStatus 필드도 JSON 이름을 바꿔서
    private Status taskStatus;

    //생성자
    public TaskDto() {}


    public TaskDto(Long taskId, Long userId, String title, String description, String username, Priority priority, LocalDateTime startDate, LocalDateTime dueDate, Status taskStatus) {
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
