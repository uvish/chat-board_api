package com.project.chatboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long post_id;
    private Long channel_id;
    @NotBlank(message = "Post Name Cannot be Empty")
    private String post_title;
    private String post_content;
    private Long user_id;
    private Instant created;
}
