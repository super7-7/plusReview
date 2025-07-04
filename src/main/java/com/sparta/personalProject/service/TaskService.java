package com.sparta.personalProject.service;

import com.sparta.personalProject.dto.Task.*;
import com.sparta.personalProject.entity.Member;
import com.sparta.personalProject.entity.Task;
import com.sparta.personalProject.enumdata.Priority;
import com.sparta.personalProject.enumdata.Status;
import com.sparta.personalProject.repository.MemberRepository;
import com.sparta.personalProject.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    //속성
    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    //생성자
    public TaskService(TaskRepository taskRepository, MemberRepository memberRepository) {
        this.taskRepository = taskRepository;
        this.memberRepository = memberRepository;
    }

    //기능
    /**
     * 할 일 생성 서비스
     */
    @Transactional
    public TaskCreateResponseDto createTask(TaskCreateRequestDto requestDto, Long userId) {
        //1.데이터 준비
//        Long userId = requestDto.getUserId(); >>토큰 검증 때문에 Long userId를 파라미터로 받아오면서 중복되니까 삭제
        String title = requestDto.getTitle();
        String description = requestDto.getDescription();
        String username = requestDto.getUsername();
        Priority priority = requestDto.getPriority();
        LocalDateTime startDate = requestDto.getStartDate();
        LocalDateTime dueDate = requestDto.getDueDate();
        Status taskStatus = requestDto.getTaskStatus();
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        //2.검증 로직

        //3.엔티티 만들기 >>> 수정
        Task task = new Task(title, description, username, priority, startDate, dueDate, taskStatus, member);
//        Task task = Task.createFromTaskCreateRequestDto(title, description, priority, ...);

        //4.저장
        Task savedTask = taskRepository.save(task);

        //5.responseDto 만들기
        TaskCreateResponseDto responseDto = new TaskCreateResponseDto(201, "created", savedTask.getTaskId(), savedTask.getMember().getUserId());
        return responseDto;
    }
    /**
     * 할 일 단건 조회 서비스
     */
    public TaskDetailResponseDto getTaskDetail(Long taskId) {
        //1.데이터 준비
        //2.조회
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("해당 내용이 존재하지 않습니다."));
        //3.DTO 만들기
        TaskDetailResponseDto responseDto = new TaskDetailResponseDto(
                200,
                "success",
                foundTask.getTaskId(),
                foundTask.getMember().getUserId(),
                foundTask.getTitle(),
                foundTask.getDescription(),
                foundTask.getUsername(),
                foundTask.getPriority(),
                foundTask.getStartDate(),
                foundTask.getDueDate(),
                foundTask.getTaskStatus()
        );
        return responseDto;
    }
//        Optional<Task> taskOptional= taskRepository.findById(taskId);
//        if (taskOptional.isPresent()) {
//            Task foundTask = taskOptional.get();
//            String foundTaskTitle = foundTask.getTitle();
//            String foundTaskDescription = foundTask.getDescription();
//            String foundTaskUsername = foundTask.getUsername();
//            Priority foundTaskPriority = foundTask.getPriority();
//            LocalDateTime foundTaskStartDate = foundTask.getStartDate();
//            LocalDateTime foundTaskDueDate = foundTask.getDueDate();
//            Status foundTaskTaskStatus = foundTask.getTaskStatus();
//            Integer status = 200;
//            String message = "success";
//            //DTO 만들기
//            TaskDetailResponseDto responseDto = new TaskDetailResponseDto(status, message, foundTaskTitle, foundTaskDescription, foundTaskUsername, foundTaskPriority, foundTaskStartDate, foundTaskDueDate, foundTaskTaskStatus);
//            //반환
//            return responseDto;
//        } else {
//            return null;
//        }
//
    /**
     * 할 일 전체 조회 서비스
     */
    public TaskListResponseDto getTaskList() {
        //조회
        List<Task> taskList = taskRepository.findAll();

        List<TaskDto> taskDtoList = taskList.stream()
                .map(task -> new TaskDto(
                        task.getTaskId(),
                        task.getMember().getUserId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getUsername(),
                        task.getPriority(),
                        task.getStartDate(),
                        task.getDueDate(),
                        task.getTaskStatus())
                ).collect(Collectors.toList());

        //반환 DTO 만들기
        TaskListResponseDto responseDto = new TaskListResponseDto(200, "success", taskDtoList);

        //반환
        return responseDto;
    }

    /**
     * 할 일 수정 서비스
     */
    @Transactional
    public TaskUpdateResponseDto updateTask(Long taskId, TaskUpdateRequestDto requestDto, Long userId) {
        //데이터 준비
        //조회
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("해당 작업이 존재하지 않습니다."));
        Member foundMember = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        //findById->member member 비교/대조 과정 추가(클라이언트 입력 x)
        if (!foundTask.getMember().getUserId().equals(foundMember.getUserId())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }
        //수정
        foundTask.update(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getUsername(),
                requestDto.getPriority(),
                requestDto.getStartDate(),
                requestDto.getDueDate(),
                requestDto.getTaskStatus(),
                foundMember
        );
        //반환 DTO 만들기
        TaskUpdateResponseDto responseDto = new TaskUpdateResponseDto(200, "success", foundTask.getTaskId());
        //반환
        return responseDto;
    }

    /**
     * 할 일 삭제 서비스
     */
    @Transactional
    public TaskDeleteResponseDto deleteTask(Long taskId, Long userId) {
        //데이터 준비
        //조회
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("해당 작업이 존재하지 않습니다."));
        Member foundMember = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
        //검증
        if (!foundTask.getMember().getUserId().equals(foundMember.getUserId())) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        //삭제
        taskRepository.delete(foundTask);
        //DTO 만들기
        TaskDeleteResponseDto responseDto = new TaskDeleteResponseDto(200, "deleted");
        return responseDto;
    }
}
