package com.sparta.personalProject.controller;

import com.sparta.personalProject.config.PasswordEncoder;
import com.sparta.personalProject.dto.Member.*;
import com.sparta.personalProject.entity.Member;
import com.sparta.personalProject.service.JwtService;
import com.sparta.personalProject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    //속성
    private final MemberService memberService;
    private final JwtService jwtService;

    //생성자
    public MemberController(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService; this.jwtService = jwtService;
    }

    //기능
    /**
     * 회원 가입 API
     */
    @PostMapping
    public ResponseEntity<MemberCreateResponseDto> createMemberAPI(@RequestBody MemberCreateRequestDto requestDto) {
        MemberCreateResponseDto responseDto = memberService.createMember(requestDto);
        ResponseEntity<MemberCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        return response;
    }

    /**
     * 로그인 API
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginAPI(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto responseDto = memberService.login(requestDto);
        ResponseEntity<LoginResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 멤버 단건 조회 API
     */
    @GetMapping("/{userId}")
    public ResponseEntity<MemberDetailResponseDto> getMemberDetailAPI(@PathVariable("userId") Long userId) {
        MemberDetailResponseDto responseDto = memberService.getMemberDetail(userId);
        ResponseEntity<MemberDetailResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
    /**
     * 멤버 전체 조회 API
     */
    @GetMapping
    public ResponseEntity<MemberListResponseDto> getMemberListAPI() {
        MemberListResponseDto responseDto = memberService.getMemberList();
        ResponseEntity<MemberListResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 멤버 수정 API
     */
    @PatchMapping
    public ResponseEntity<MemberUpdateResponseDto> updateMemberAPI(
            HttpServletRequest request,
            @RequestBody MemberUpdateRequestDto requestDto
    ) {
        //1.헤더에서 토큰 추출
         String authorization = request.getHeader("Authorization");
         String token = authorization.substring(7);

        //2.토큰 검증 및 데이터 추출
        Long userId = jwtService.verifyToken(token);

        //3.비즈니스 로직
        MemberUpdateResponseDto responseDto = memberService.updateMember(userId, requestDto);
        ResponseEntity<MemberUpdateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 멤버 삭제 API
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<MemberDeleteResponseDto> deleteMemberAPI(@PathVariable("userId") Long userId) {
        MemberDeleteResponseDto responseDto = memberService.deleteMember(userId);
        ResponseEntity<MemberDeleteResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;

    }

}
