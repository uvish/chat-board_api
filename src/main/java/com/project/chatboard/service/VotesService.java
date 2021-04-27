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
    private final AnswerService answerService;

    public void upvote(Long user_id,Long answer_id){
        List<Votes> allVotes=voteRepository.findAll();

        String status="not_voted";
        Long vote_id= Long.valueOf(0);
        for(int i=0;i<allVotes.size();i++){
            if(allVotes.get(i).getUser_id()==user_id && allVotes.get(i).getAnswer_id()==answer_id)
                status="already_voted";
                vote_id=allVotes.get(i).getVote_id();
        }

        if(status.equalsIgnoreCase("already_voted")){
            voteRepository.deleteById(vote_id);
//                answerService.remove_upvote(answer_id);
        }

        else if(status.equals("not_voted")) {
            Votes v = new Votes();
            v.setUser_id(user_id);
            v.setAnswer_id(answer_id);
            v.setVote("upvote");
//            answerService.upvote(answer_id);
            voteRepository.save(v);
        }
    }
    public void downvote(Long user_id,Long answer_id){
        List<Votes> allVotes=voteRepository.findAll();

        String status="not_voted";

        Long vote_id=Long.valueOf(0);;
        for(int i=0;i<allVotes.size();i++){
            if(allVotes.get(i).getUser_id()==user_id && allVotes.get(i).getAnswer_id()==answer_id)
                status="already_voted";
                vote_id=allVotes.get(i).getVote_id();
        }

        if(status.equalsIgnoreCase("already_voted")){
//            answerService.remove_downvote(answer_id);
            voteRepository.deleteById(vote_id);
        }

        else if(status.equals("not_voted")) {
            Votes v = new Votes();
            v.setUser_id(user_id);
            v.setAnswer_id(answer_id);
            v.setVote("downvote");
//            answerService.downvote(answer_id);
            voteRepository.save(v);
        }
    }
    public VoteResponse getVotes(Long answer_id){
        List<Votes> allVotes=voteRepository.findAll();
        int upvotes=0;
        int downvotes=0;
        for(int i=0;i<allVotes.size();i++)
        {
            if(allVotes.get(i).getAnswer_id()==answer_id)
            {
                if(allVotes.get(i).getVote().equalsIgnoreCase("upvote"))
                    upvotes+=1;
                if(allVotes.get(i).getVote().equalsIgnoreCase("downvote"))
                    downvotes+=1;
            }
        }
        VoteResponse votes=new VoteResponse();
        votes.setUpvotes(Long.valueOf(upvotes));
        votes.setDownvotes(Long.valueOf(downvotes));
        return votes;
    }
}
