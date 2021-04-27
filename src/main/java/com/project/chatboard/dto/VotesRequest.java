package com.project.chatboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotesRequest {
    private Long user_id;
    private Long answer_id;
}
