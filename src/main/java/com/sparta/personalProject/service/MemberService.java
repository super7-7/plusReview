package com.sparta.personalProject.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sparta.personalProject.config.PasswordEncoder;
import com.sparta.personalProject.dto.Member.*;
import com.sparta.personalProject.entity.Member;
import com.sparta.personalProject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    //속성
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    //생성자
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //기능
    /**
     * 멤버 가입 서비스
     */
    @Transactional
    public MemberCreateResponseDto createMember(MemberCreateRequestDto requestDto) {
        //1.데이터 준비
        String username = requestDto.getUsername();
        String userEmail = requestDto.getUserEmail();
        String password = requestDto.getPassword();

        //2.검증 로직(이메일 중복 체크, 비밀번호 검증)
        if (memberRepository.existsByUserEmail(userEmail)) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).+$")) {
            throw new RuntimeException("비밀번호는 대소문자, 숫자, 특수문자를 포함해야 합니다.");
        }

        //3.비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        //4.엔티티 만들기
        Member member = new Member(username, userEmail, encodedPassword);

        //5.저장
        Member savedMember = memberRepository.save(member);
        Long savedMemberId = savedMember.getUserId();

        //6.responseDto 만들기
        MemberCreateResponseDto responseDto = new MemberCreateResponseDto(201, "created", savedMemberId);
        return responseDto;
    }

    /**
     * 로그인 서비스
     */
    public LoginResponseDto login(LoginRequestDto requestDto) {
        //1.데이터 준비
        String userEmail = requestDto.getUserEmail();
        String password = requestDto.getPassword();

        //2.검증로직(이메일, 비밀번호 일치 확인)
        Member loginMember = memberRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));
        if (loginMember.isDeleted()) {
            throw new RuntimeException("탈퇴한 회원입니다.");
        }
        //3.비밀번호 확인 후, 일치 시 토큰 생성 및 반환
        if (passwordEncoder.matches(password, loginMember.getPassword())) {
            String token = jwtService.createToken(loginMember);
            return new LoginResponseDto(token);
        } else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    /**
     * 멤버 단건 조회 서비스
     */
    public MemberDetailResponseDto getMemberDetail(Long userId) {
        //1.데이터 준비
        //2.조회
        Member foundMember = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원이 조회되지 않습니다."));

        //DTO 만들기
//            MemberDetailResponseDto responseDto = new MemberDetailResponseDto(foundMember);
        MemberDetailResponseDto responseDto = new MemberDetailResponseDto(200, "success",
                foundMember.getUserId(), foundMember.getUsername(), foundMember.getUserEmail(), foundMember.getCreatedAt());
        return responseDto;
    }

    /**
     * 멤버 전체 조회 서비스
     */
    public MemberListResponseDto getMemberList() {
        //조회
        List<Member> memberList = memberRepository.findAll();

        List<MemberDto> memberDtoList = memberList.stream()
                .map(member -> new MemberDto(
                        member.getUserId(),
                        member.getUsername(),
                        member.getUserEmail(),
                        member.getCreatedAt())
                ).collect(Collectors.toList());

        //반환 DTO 만들기
        MemberListResponseDto responseDto = new MemberListResponseDto(200, "success", memberDtoList);

        //반환
        return responseDto;
    }

    /**
     * 멤버 수정 서비스
     */
    @Transactional
    public MemberUpdateResponseDto updateMember(Long userId, MemberUpdateRequestDto requestDto) {
        //1.데이터 준비
        //2.조회
        Member foundMember = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        //사용자가 입력한 비밀번호를 인코딩
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        //수정
        foundMember.update(
                requestDto.getUsername(),
                encodedPassword
        );

        //반환 DTO 만들기
        MemberUpdateResponseDto responseDto = new MemberUpdateResponseDto(200, "success", foundMember.getUserId());

        //반환
        return responseDto;
    }

    /**
     * 멤버 삭제 서비스(논리 삭제로 변경)
     */
    @Transactional
    public MemberDeleteResponseDto deleteMember(Long userId) {
        //데이터 준비
        //조회
        Member foundMember = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 멤버가 존재하지 않습니다."));
        //삭제
        foundMember.delete();
        //DTO 만들기
        MemberDeleteResponseDto responseDto = new MemberDeleteResponseDto(200, "deleted");

        //반환
        return responseDto;
    }
}

