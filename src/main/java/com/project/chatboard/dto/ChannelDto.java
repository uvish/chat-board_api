package com.project.chatboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
@Builder
public class ChannelDto {
    private String name;
    private Long admin_id;
    private String description;
}
