package com.learntogether.learntogether.Service;

import com.learntogether.learntogether.Dto.BookmarkPostRequestDto;
import com.learntogether.learntogether.Dto.CreatePostRequestDto;
import com.learntogether.learntogether.Dto.GetPostsResponseDto;
import com.learntogether.learntogether.Dto.UpvotePostRequestDto;
import com.learntogether.learntogether.Entity.Pool;
import com.learntogether.learntogether.Entity.Post;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Exception.PoolNotFoundException;
import com.learntogether.learntogether.Exception.UserNotFoundException;
import com.learntogether.learntogether.Repository.PoolRepository;
import com.learntogether.learntogether.Repository.PostRepository;
import com.learntogether.learntogether.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PoolRepository poolRepository;

    @Autowired
    UserRepository userRepository;

    public void createPost(CreatePostRequestDto createPostRequestDto) throws PoolNotFoundException, UserNotFoundException {
        User user = userRepository.findByEmail(createPostRequestDto.getEmail());
        Pool pool = poolRepository.findByPoolName(createPostRequestDto.getPoolName());

        // exceptional handling
        if(user==null)
            throw new UserNotFoundException("User not found!!!");

        if(pool==null)
            throw new PoolNotFoundException("Pool not found!!!");

        // setting post
        Post newPost = new Post();
        newPost.setUser(user);
        newPost.setPool(pool);
        newPost.setContent(createPostRequestDto.getContent());
        newPost = postRepository.save(newPost);

        // setting user
        List<Post> postsCreated = user.getPostsCreated();
        postsCreated.add(newPost);

        // setting pool
        List<Post> posts = pool.getPosts();
        posts.add(newPost);

        // saving all entities
        userRepository.save(user);
        poolRepository.save(pool);

    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }
}