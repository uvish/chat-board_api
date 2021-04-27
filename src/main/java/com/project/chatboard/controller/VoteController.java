package com.project.chatboard.controller;

import com.project.chatboard.dto.VoteResponse;
import com.project.chatboard.dto.VotesRequest;
import com.project.chatboard.model.Votes;
import com.project.chatboard.service.VotesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/vote")
@AllArgsConstructor
public class VoteController {
    VotesService votesService;

    @PostMapping("/upvote")
    public ResponseEntity<String> upvote(@RequestBody VotesRequest vote){
        votesService.upvote(vote.getUser_id(),vote.getAnswer_id());
        return new ResponseEntity<>("UpVoted", HttpStatus.OK);
    }
    @PostMapping("/downvote")
    public ResponseEntity<String> vote(@RequestBody VotesRequest vote){
        votesService.downvote(vote.getUser_id(),vote.getAnswer_id());
        return new ResponseEntity<>("DownVoted", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public VoteResponse getVotes(@PathVariable("id") Long answer_id){
       return votesService.getVotes(answer_id);
    }

}
