package com.sparta.personalProject.entity;

import com.sparta.personalProject.enumdata.Priority;
import com.sparta.personalProject.enumdata.Status;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {

    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "username", nullable = false, length = 50)
    private String username; //검색 속도 높이는

    @Column (name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column (name = "start_date")
    private LocalDateTime startDate;

    @Column (name = "due_date")
    private LocalDateTime dueDate;

    @Column (name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @PrePersist
//    public void onCreate() {
//        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
//        this.createdAt = now;
//    }

    //생성자
    /**
     * 이 생성자는 JPA에서 활용하는 것입니다.
     */
    protected Task() {}

    /**
     *taskCreateRequestDto 정보를 꺼내와서 entity를 만들 때 사용
     */
    public Task(String title, String description, String username, Priority priority, LocalDateTime startDate, LocalDateTime dueDate, Status taskStatus, Member member) {
        this.title = title;
        this.description = description;
        this.username = username;
        this.priority = priority;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
        this.member = member;
    }
//    public static Task createFromTaskCreateRequestDto(String title, String description, Priority priority, LocalDateTime startDate, LocalDateTime dueDate, Status status) {
//        return new Task(title, description, priority, startDate, dueDate, status);
//    } 위 생성자와 동일 -> TaskService.createTask 3.엔티티 만들기 참고

    //기능
    //update 메서드
    public void update(String title, String description, String username, Priority priority, LocalDateTime startDate, LocalDateTime dueDate, Status taskStatus, Member member) {
        this.title = title;
        this.description = description;
        this.username = username;
        this.priority = priority;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
        this.member = member;
    }

    //게터
    public Long getTaskId() {
        return taskId;
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

    public Member getMember() {
        return member;
    }
}
