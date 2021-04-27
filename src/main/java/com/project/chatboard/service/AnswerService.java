package com.project.chatboard.service;

import com.project.chatboard.dto.AnswerRequest;
import com.project.chatboard.model.Answer;
import com.project.chatboard.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AuthService authService;
    public Answer saveAnswer(AnswerRequest answerRequest){
        Answer ans=new Answer();
        ans.setAnswer(answerRequest.getAnswer());
        ans.setPost_id(answerRequest.getPost_id());
        ans.setCreated(Instant.now());
        ans.setUser_id(answerRequest.getUser_id());
        ans.setUser_name(authService.findUserNameById(answerRequest.getUser_id()));
        return answerRepository.save(ans);
    }
    public boolean deleteAnswer(Long id){
        answerRepository.deleteById(id);
        return true;
    }

    public List<Answer> getAnswersByPostId(Long post_id){
        List<Answer> allAnswers=answerRepository.findAll();
        List<Answer> postAnswers=new ArrayList<>();
        for(int i=0;i<allAnswers.size();i++){
            if(allAnswers.get(i).getPost_id()==post_id)
                postAnswers.add(allAnswers.get(i));
        }
        return postAnswers;
    }

    public boolean findExisting(Long id){
        List<Answer> answer=answerRepository.findAll();
        for(Answer ans:answer){
            if(ans.getAnswer_id().equals(id)){
                return true;
            }
        }
        return false;
    }

//    public Answer upvote(Long id){
//        Optional<Answer> ans=answerRepository.findById(id);
//           Answer ans_up=new Answer();
//           ans_up.setAnswer(ans.get().getAnswer());
//           ans_up.setAnswer_id(ans.get().getAnswer_id());
//           ans_up.setCreated(ans.get().getCreated());
//           ans_up.setUser_id(ans.get().getUser_id());
//           ans_up.setPost_id(ans.get().getPost_id());
//           ans_up.setDown_votes(ans.get().getDown_votes());
//           ans_up.setUp_votes(ans.get().getUp_votes()+1);
//           ans_up.setUser_name(ans.get().getUser_name());
//           if(ans.get().getUp_votes()==0)
//           return answerRepository.save(ans_up);
//           return null;
//        }
//    public Answer remove_upvote(Long id){
//        Optional<Answer> ans=answerRepository.findById(id);
//        if(ans.get().getUp_votes()==1) {
//            Answer ans_up = new Answer();
//            ans_up.setAnswer(ans.get().getAnswer());
//            ans_up.setAnswer_id(ans.get().getAnswer_id());
//            ans_up.setCreated(ans.get().getCreated());
//            ans_up.setUser_id(ans.get().getUser_id());
//            ans_up.setPost_id(ans.get().getPost_id());
//            ans_up.setDown_votes(ans.get().getDown_votes());
//            ans_up.setUp_votes(ans.get().getUp_votes() - 1);
//            ans_up.setUser_name(ans.get().getUser_name());
//            return answerRepository.save(ans_up);
//        }
//        return null;
//    }
//
//    public Answer downvote(Long id){
//        Optional<Answer> ans=answerRepository.findById(id);
//        Answer ans_down=new Answer();
//        ans_down.setAnswer(ans.get().getAnswer());
//        ans_down.setAnswer_id(ans.get().getAnswer_id());
//        ans_down.setCreated(ans.get().getCreated());
//        ans_down.setUser_id(ans.get().getUser_id());
//        ans_down.setPost_id(ans.get().getPost_id());
//        ans_down.setDown_votes(ans.get().getDown_votes()+1);
//        ans_down.setUp_votes(ans.get().getUp_votes());
//        ans_down.setUser_name(ans.get().getUser_name());
//        if(ans.get().getDown_votes()==0)
//        return answerRepository.save(ans_down);
//        return null;
//    }
//    public Answer remove_downvote(Long id){
//        Optional<Answer> ans=answerRepository.findById(id);
//        Answer ans_down=new Answer();
//        ans_down.setAnswer(ans.get().getAnswer());
//        ans_down.setAnswer_id(ans.get().getAnswer_id());
//        ans_down.setCreated(ans.get().getCreated());
//        ans_down.setUser_id(ans.get().getUser_id());
//        ans_down.setPost_id(ans.get().getPost_id());
//        ans_down.setDown_votes(ans.get().getDown_votes()-1);
//        ans_down.setUp_votes(ans.get().getUp_votes());
//        ans_down.setUser_name(ans.get().getUser_name());
//        if(ans.get().getDown_votes()==1)
//        return answerRepository.save(ans_down);
//        return null;
//    }
}
