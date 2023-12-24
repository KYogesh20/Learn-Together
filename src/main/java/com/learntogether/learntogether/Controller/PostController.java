package com.learntogether.learntogether.Controller;

import com.learntogether.learntogether.Dto.BookmarkPostRequestDto;
import com.learntogether.learntogether.Dto.CreatePostRequestDto;
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

        return new ResponseEntity<>("Post created Successfully!!", HttpStatus.CREATED);
    }

    @PostMapping("/upvote-post")
    public ResponseEntity<String> upvotePost(@RequestBody UpvotePostRequestDto upvotePostRequestDto){
        try{
            postService.upvotePost(upvotePostRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Post Upvoted Successfully!",HttpStatus.CREATED);
    }

    @PostMapping("/bookmark-post")
    public ResponseEntity<String> bookmarkPost(@RequestBody BookmarkPostRequestDto bookmarkPostRequestDto){
        try{
            postService.bookmarkPost(bookmarkPostRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Post bookmarked Successfully!",HttpStatus.CREATED);
    }

    @GetMapping("/get-posts/{email}")
    public ResponseEntity getUserPosts(@PathVariable String email){
        List<Post> userPosts;
        userPosts = postService.getUserPosts(email);
        /*
        try{
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
         */

        return new ResponseEntity<>(userPosts,HttpStatus.CREATED);
    }

    @GetMapping("/get-posts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }
}
