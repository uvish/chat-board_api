package com.project.chatboard.controller;

import com.project.chatboard.dto.AnswerRequest;
import com.project.chatboard.model.Answer;
import com.project.chatboard.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@AllArgsConstructor
@CrossOrigin
public class AnswerController {
    AnswerService answerService;

    @PostMapping("/create")
    public Answer createAnswer(@RequestBody AnswerRequest answerRequest){
        return answerService.saveAnswer(answerRequest);
    }

    @GetMapping("/get/{id}")
    public List<Answer> getAnswersByPost(@PathVariable("id")Long post_id)
    {
       return answerService.getAnswersByPostId(post_id);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable("id") Long ans_id){
        if(answerService.deleteAnswer(ans_id)){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/upvote/{id}")
//    public Answer upvote(@PathVariable("id") Long ans_id){
//        if(answerService.findExisting(ans_id)){
//           return answerService.upvote(ans_id);
//        }
//        return null;
//    }
//
//    @PostMapping("/downvote/{id}")
//    public Answer downvote(@PathVariable("id") Long ans_id){
//        if(answerService.findExisting(ans_id)){
//            return answerService.downvote(ans_id);
//        }
//        return null;
//    }

}
