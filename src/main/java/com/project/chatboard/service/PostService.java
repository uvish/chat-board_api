package com.project.chatboard.service;

import com.project.chatboard.dto.PostEditRequest;
import com.project.chatboard.dto.PostRequest;
import com.project.chatboard.dto.PostResponse;
import com.project.chatboard.model.Post;
import com.project.chatboard.repository.PostRepository;
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
public class PostService {
    private final PostRepository postRepository;
    private final AuthService authService;
    private final AnswerService answerService;
    public Post savePost (PostRequest postRequest){
        Post post=new Post();
        post.setPost_title(postRequest.getTitle());
        post.setChannel_id(postRequest.getChannel_id());
        post.setUser_id(postRequest.getUser_id());
        post.setPost_content(postRequest.getContent());
        post.setCreated(Instant.now());
        return postRepository.save(post);
    }

    public boolean deletePost(Long id){
        postRepository.deleteById(id);
         answerService.deleteAnswersByPost(id);
        return true;
    }

//    public List<Post> getAllPosts(long user_id){
//      List<Post> allPosts=postRepository.findAll();
//      List<Post> userPosts=new ArrayList<>();
//      for(int i=0;i< allPosts.size();i++){
//          if(allPosts.get(i).getUser_id()==user_id){
//              userPosts.add(allPosts.get(i));
//          }
//      }
//      return userPosts;
//    }
public List<PostResponse> getAllPosts(long user_id){
    List<Post> allPosts=postRepository.findAll();
    List<PostResponse> userPosts=new ArrayList<>();
    for(int i=0;i< allPosts.size();i++){
        if(allPosts.get(i).getUser_id()==user_id){
            PostResponse t= new PostResponse(
                    allPosts.get(i).getPost_title(),
                    allPosts.get(i).getPost_content(),
                    allPosts.get(i).getUser_id(),
                    allPosts.get(i).getChannel_id(),
                    authService.findUserNameById(allPosts.get(i).getUser_id()),
                    allPosts.get(i).getCreated(),
                    allPosts.get(i).getPost_id()
            );
            //channelPosts.add(allPosts.get(i));
            userPosts.add(t);
//            userPosts.add(allPosts.get(i));
        }
    }
    return userPosts;
}
    public List<PostResponse> getAllPostsByChannel(long channel_id){
        List<Post> allPosts=postRepository.findAll();
        List<PostResponse> channelPosts=new ArrayList<>();
        for(int i=0;i< allPosts.size();i++){
            if(allPosts.get(i).getChannel_id()==channel_id){
                PostResponse t= new PostResponse(
                        allPosts.get(i).getPost_title(),
                        allPosts.get(i).getPost_content(),
                        allPosts.get(i).getUser_id(),
                        allPosts.get(i).getChannel_id(),
                        authService.findUserNameById(allPosts.get(i).getUser_id()),
                        allPosts.get(i).getCreated(),
                        allPosts.get(i).getPost_id()
                        );
                //channelPosts.add(allPosts.get(i));
                channelPosts.add(t);
            }
        }
        return channelPosts;
    }

    public Post editPost(PostEditRequest postEditRequest){
        Optional<Post> og=postRepository.findById(postEditRequest.getPost_id());
        Post editedPost=new Post();
        editedPost.setPost_id(og.get().getPost_id());
        editedPost.setPost_title(postEditRequest.getTitle());
        editedPost.setPost_content(postEditRequest.getContent());
        editedPost.setCreated(og.get().getCreated());
        editedPost.setChannel_id(og.get().getChannel_id());
        editedPost.setUser_id(og.get().getUser_id());
        postRepository.save(editedPost);
        return editedPost;
    }
    public List<Post> getRecent(){
        List<Post> all=postRepository.findAll();
        List<Post> recent=new ArrayList<>();
        int length=all.size();
        if(length>10)length=10;
        for(int i=all.size()-1;i>=all.size()-length;i--)
        {
            recent.add(all.get(i));
        }
        return recent;
    }
}
