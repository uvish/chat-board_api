package com.project.chatboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Channel {
    @Id
    @GeneratedValue
    private Long channel_id;
    private Long admin_id;
    @NotBlank(message = "Description Cannot be blank")
    private String descripition;
    @NotBlank(message = "Channel name cannot be blank")
    private String name;
}
