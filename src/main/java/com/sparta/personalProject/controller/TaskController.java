package com.sparta.personalProject.controller;

import com.sparta.personalProject.dto.Task.*;
import com.sparta.personalProject.service.JwtService;
import com.sparta.personalProject.service.TaskService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    //속성
    private final TaskService taskService;
    private final JwtService jwtService;

    //생성자
    public TaskController (TaskService taskService, JwtService jwtService) {
        this.taskService = taskService;
        this.jwtService = jwtService;
    }

    //기능
    /**
     * 할 일 생성 API
     */
    @PostMapping
    public ResponseEntity<TaskCreateResponseDto> createTaskAPI(HttpServletRequest request, @RequestBody TaskCreateRequestDto requestDto) {
        //1.헤더에서 토큰 추출
        String authorization = request.getHeader("Authorization");
        System.out.println("Authorization = " + authorization);
        String token = authorization.substring(7);

        //2.토큰 검증 및 데이터 추출
        Long userId = jwtService.verifyToken(token);
        System.out.println("userId = " + userId);

        //3.비즈니스 로직
        TaskCreateResponseDto responseDto = taskService.createTask(requestDto, userId);

        ResponseEntity<TaskCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        return response;
    }

    /**
     * 할 일 단건 조회 API
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDetailResponseDto> getTaskDetailAPI(
    @PathVariable("taskId") Long taskId
        ) {
        TaskDetailResponseDto responseDto = taskService.getTaskDetail(taskId);
        ResponseEntity<TaskDetailResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 할 일 전제 조회 API
     */
    @GetMapping
    public ResponseEntity<TaskListResponseDto> getTaskListAPI() {
        TaskListResponseDto responseDto = taskService.getTaskList();
        ResponseEntity<TaskListResponseDto> response = new ResponseEntity(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 할 일 수정 API
     */
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskUpdateResponseDto> updateTaskAPI(
            HttpServletRequest request,
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskUpdateRequestDto requestDto
    ) {
        //1.헤더에서 토큰 추출
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);

        //2.토큰 검증 및 데이터 추출
        Long userId = jwtService.verifyToken(token);

        //3.비즈니즈 로직
        TaskUpdateResponseDto responseDto = taskService.updateTask(taskId, requestDto, userId);
        ResponseEntity<TaskUpdateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 할 일 삭제 API
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<TaskDeleteResponseDto> deleteTaskAPI(
            HttpServletRequest request,
            @PathVariable("taskId") Long taskId
    ) {
        //1.헤더에서 토큰 추출
         String authorization = request.getHeader("Authorization");
         String token = authorization.substring(7);

        //2.토큰 검증 및 데이터 추출
        Long userId = jwtService.verifyToken(token);

        //3.비즈니스 로직
        TaskDeleteResponseDto responseDto = taskService.deleteTask(taskId, userId);
        ResponseEntity<TaskDeleteResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
}
