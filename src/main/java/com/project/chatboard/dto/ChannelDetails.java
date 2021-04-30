package com.project.chatboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
@Builder
@NoArgsConstructor
public class ChannelDetails {
    private String channel_name;
    private String channel_description;
    private String admin;
}
