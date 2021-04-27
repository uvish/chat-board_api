package com.project.chatboard.repository;

import com.project.chatboard.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotesRepository extends JpaRepository<Votes,Long> {
}
