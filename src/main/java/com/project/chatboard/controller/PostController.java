package com.project.chatboard.controller;

import com.project.chatboard.dto.PostEditRequest;
import com.project.chatboard.dto.PostRequest;
import com.project.chatboard.dto.PostResponse;
import com.project.chatboard.model.Post;
import com.project.chatboard.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@CrossOrigin
public class PostController {
    PostService postService;

    @PostMapping("/create")
    public Post createPost(@RequestBody PostRequest postRequest){
        return postService.savePost(postRequest);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long post_id){
     if(postService.deletePost(post_id)){
         return new ResponseEntity<>("Deleted", HttpStatus.OK);
     }
     return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all/{id}")
    public List<PostResponse> getAllPosts(@PathVariable("id") Long user_id){
        return postService.getAllPosts(user_id);
    }

    @GetMapping("/channel/{id}")
    public List<PostResponse> getAllPostsByChannel(@PathVariable("id") Long channel_id){
        return postService.getAllPostsByChannel(channel_id);
    }

    @PostMapping("/edit")
    public Post editPost(@RequestBody PostEditRequest postEditRequest)
    {
      return postService.editPost(postEditRequest);
    }
}
