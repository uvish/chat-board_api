package com.project.chatboard.service;

import com.project.chatboard.dto.PostRequest;
import com.project.chatboard.dto.PostResponse;
import com.project.chatboard.model.Post;
import com.project.chatboard.repository.PostRepository;
import com.project.chatboard.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final AuthService authService;
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
}
