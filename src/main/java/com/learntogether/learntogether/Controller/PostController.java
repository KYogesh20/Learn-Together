package com.learntogether.learntogether.Controller;

import com.learntogether.learntogether.Dto.BookmarkPostRequestDto;
import com.learntogether.learntogether.Dto.CreatePostRequestDto;
import com.learntogether.learntogether.Dto.GetPostsResponseDto;
import com.learntogether.learntogether.Dto.UpvotePostRequestDto;
import com.learntogether.learntogether.Entity.Post;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create-post")
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequestDto createPostRequestDto){
        try {
            postService.createPost(createPostRequestDto);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Post create Successfully!!!",HttpStatus.CREATED);
    }

    @GetMapping("/get-posts")
    public ResponseEntity getPosts(){
        List<Post> posts = postService.getPosts();
        return new ResponseEntity(posts,HttpStatus.ACCEPTED);
    }
}
