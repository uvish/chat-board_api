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
public class Answer {
   @Id
   @GeneratedValue
   private Long answer_id;
   private Long post_id;
   @NotBlank(message = "Answer can't be empty")
   private String answer;
   private Long user_id;
   private int up_votes;
   private int down_votes;
   private Instant created;
   private String user_name;
}
