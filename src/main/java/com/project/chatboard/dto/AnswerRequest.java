package com.project.chatboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
@Builder
public class AnswerRequest {
    private String answer;
    private Long post_id;
    private Long user_id;
}
