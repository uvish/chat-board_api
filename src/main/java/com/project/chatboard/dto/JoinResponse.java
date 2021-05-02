package com.project.chatboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinResponse {
    private Long request_id;
    private Long user_id;
    private Long channel_id;
    private String status;
    private Long admin_id;
    private String user_name;
    private String channel_name;
}
