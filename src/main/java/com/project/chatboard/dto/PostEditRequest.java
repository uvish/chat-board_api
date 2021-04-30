package com.project.chatboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
@Builder
public class PostEditRequest {
    private String title;
    private String content;
    private Long post_id;
}
