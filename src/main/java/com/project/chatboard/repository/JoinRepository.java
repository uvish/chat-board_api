package com.project.chatboard.repository;

import com.project.chatboard.model.JoinRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<JoinRequests,Long> {
}
