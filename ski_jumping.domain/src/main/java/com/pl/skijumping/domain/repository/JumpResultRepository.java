package com.pl.skijumping.domain.repository;

import com.pl.skijumping.domain.entity.JumpResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface JumpResultRepository extends JpaRepository<JumpResult, Long>, QueryDslPredicateExecutor {
}
