package com.sparta.personalProject.repository;

import com.sparta.personalProject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUserEmail(String userEmail);
    Optional<Member> findByUserEmail(String userEmail);
//    Member findByUserEmail(String userEmail);

}
