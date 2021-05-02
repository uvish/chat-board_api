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
public class JoinRequests {
    @Id
    @GeneratedValue
    private Long request_id;
    private Long user_id;
    private Long channel_id;
    private String status;
    private Long admin_id;
}
