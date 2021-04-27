package com.project.chatboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class PostResponse {
    private String title;
    private String content;
    private Long user_id;
    private Long channel_id;
    private String user_name;
    private Instant created;
    private Long post_id;
}
