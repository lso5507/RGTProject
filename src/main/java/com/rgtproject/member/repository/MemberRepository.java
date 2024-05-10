package com.rgtproject.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rgtproject.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
	Member findByUserId(String userId);
}
