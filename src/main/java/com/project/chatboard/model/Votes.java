package com.project.chatboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Votes {
    @Id
    @GeneratedValue
    private Long vote_id;
    private Long user_id;
    private Long answer_id;
    private String vote;
}
