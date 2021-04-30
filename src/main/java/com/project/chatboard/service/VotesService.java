package com.project.chatboard.service;

import com.project.chatboard.dto.VoteResponse;
import com.project.chatboard.dto.VotesRequest;
import com.project.chatboard.model.Answer;
import com.project.chatboard.model.Votes;
import com.project.chatboard.repository.AnswerRepository;
import com.project.chatboard.repository.VotesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class VotesService {
    private final VotesRepository voteRepository;
//    private final AnswerService answerService;

    public void upvote(Long user_id,Long answer_id){
        List<Votes> allVotes=voteRepository.findAll();

        String status="not_voted";
        Long vote_id= Long.valueOf(0);
        for(int i=0;i<allVotes.size();i++){
            if(allVotes.get(i).getUser_id().equals(user_id) && allVotes.get(i).getAnswer_id().equals(answer_id)) {
                status = "already_voted";
                vote_id = allVotes.get(i).getVote_id();
                voteRepository.deleteById(vote_id);
            }
        }

//        if(status.equalsIgnoreCase("already_voted")){
//            voteRepository.deleteById(vote_id);
//        }

         if(status.equals("not_voted")) {
            Votes v = new Votes();
            v.setUser_id(user_id);
            v.setAnswer_id(answer_id);
            v.setVote("upvote");
            voteRepository.save(v);
        }
    }
    public void downvote(Long user_id,Long answer_id){
        List<Votes> allVotes=voteRepository.findAll();

        String status="not_voted";

        Long vote_id=Long.valueOf(0);
        for(int i=0;i<allVotes.size();i++){
            if(allVotes.get(i).getUser_id().equals(user_id) && allVotes.get(i).getAnswer_id().equals(answer_id)) {
                status = "already_voted";
                vote_id = allVotes.get(i).getVote_id();
                voteRepository.deleteById(vote_id);
            }
        }

//        if(status.equalsIgnoreCase("already_voted")){
//            voteRepository.deleteById(vote_id);
//        }

        if(status.equals("not_voted")) {
            Votes v = new Votes();
            v.setUser_id(user_id);
            v.setAnswer_id(answer_id);
            v.setVote("downvote");
            voteRepository.save(v);
        }
    }
    public VoteResponse getVotes(Long answer_id){
        List<Votes> allVotes=voteRepository.findAll();
        int upvotes=0;
        int downvotes=0;
        for(int i=0;i<allVotes.size();i++)
        {
            if(allVotes.get(i).getAnswer_id().equals(answer_id))
            {
                if(allVotes.get(i).getVote().equalsIgnoreCase("upvote"))
                    upvotes+=1;
              else if(allVotes.get(i).getVote().equalsIgnoreCase("downvote"))
                    downvotes+=1;
            }
        }
        VoteResponse votes=new VoteResponse();
        votes.setUpvotes(Long.valueOf(upvotes));
        votes.setDownvotes(Long.valueOf(downvotes));
        System.out.println("Votes Service answers_id:"+answer_id+" "+"Upvotes:"+upvotes+" Downvotes:"+downvotes);
        return votes;
    }
}
