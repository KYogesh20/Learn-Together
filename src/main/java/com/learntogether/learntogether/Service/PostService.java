package com.learntogether.learntogether.Service;

import com.learntogether.learntogether.Dto.BookmarkPostRequestDto;
import com.learntogether.learntogether.Dto.CreatePostRequestDto;
import com.learntogether.learntogether.Dto.UpvotePostRequestDto;
import com.learntogether.learntogether.Entity.Pool;
import com.learntogether.learntogether.Entity.Post;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Repository.PoolRepository;
import com.learntogether.learntogether.Repository.PostRepository;
import com.learntogether.learntogether.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PoolRepository poolRepository;

    @Autowired
    UserRepository userRepository;

    public void createPost(CreatePostRequestDto createPostRequestDto) {
        User userPosted = userRepository.findByEmail(createPostRequestDto.getEmail());
        Pool poolPosted = poolRepository.findByPoolName(createPostRequestDto.getPoolName());

        // setting post
        Post newPost = new Post();
        newPost.setAuthor(userPosted);
        newPost.setPool(poolPosted);
        newPost.setContent(createPostRequestDto.getContent());

        // setting user
        List<Post> postsCreated = userPosted.getPostsCreated();
        postsCreated.add(newPost);

        // setting pool
        List<Post> posts = poolPosted.getPosts();
        posts.add(newPost);

        // saving all entities
        postRepository.save(newPost);
        userRepository.save(userPosted);
        poolRepository.save(poolPosted);

    }

    public void upvotePost(UpvotePostRequestDto upvotePostRequestDto) {

        Post postUpvoted = postRepository.findByPostId(upvotePostRequestDto.getPostId());
        User userUpvoted = userRepository.findByEmail(upvotePostRequestDto.getEmail());

        // setting post
        List<User> usersUpvoted = postUpvoted.getUsersUpvoted();
        usersUpvoted.add(userUpvoted);

        // setting user
        List<Post> postsUpvoted = userUpvoted.getPostsUpvoted();
        postsUpvoted.add(postUpvoted);

        // saving them in db
        postRepository.save(postUpvoted);
        userRepository.save(userUpvoted);

    }

    public void bookmarkPost(BookmarkPostRequestDto bookmarkPostRequestDto){
        Post post = postRepository.findByPostId(bookmarkPostRequestDto.getPostId());
        User user = userRepository.findByEmail(bookmarkPostRequestDto.getEmail());

        // setting Post;
        List<User> userBookmarked = post.getUsersBookmarked();
        userBookmarked.add(user);

        // setting user
        List<Post> postBookmarked = user.getBookmarkedPosts();
        postBookmarked.add(post);

        // saving them into database;
        postRepository.save(post);
        userRepository.save(user);

    }

    public List<Post> getUserPosts(String email){

       String emailDecoded = URLDecoder.decode(email, StandardCharsets.UTF_8);
        System.out.println(emailDecoded);
        User user = userRepository.findByEmail(emailDecoded);
        return user.getPostsCreated();
    }

    // require DTO as a output else it will go in infinite recursion loop
    public List<Post> getPosts(){
        return postRepository.findAll();
    }
}