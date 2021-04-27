package com.project.chatboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
@Builder
public class PostRequest {
    private String title;
    private String content;
    private Long user_id;
    private Long channel_id;
}
