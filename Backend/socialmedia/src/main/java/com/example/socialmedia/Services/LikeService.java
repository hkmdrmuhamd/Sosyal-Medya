package com.example.socialmedia.Services;

import com.example.socialmedia.DTOs.request.LikeRequest;
import com.example.socialmedia.Entities.Like;
import com.example.socialmedia.Entities.Post;
import com.example.socialmedia.Entities.User;
import com.example.socialmedia.Repos.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private PostService postService;
    private UserService userService;
    private LikeRepository likeRepository;

    public LikeService(PostService postService, UserService userService, LikeRepository likeRepository) {
        this.postService = postService;
        this.userService = userService;
        this.likeRepository = likeRepository;
    }
    public Like createOneLike(LikeRequest likeRequest) {
        User user = userService.getOneUserById(likeRequest.getUserId());
        Post post = postService.getOnePostById(likeRequest.getPostId());
        if (user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        } else {
            return null;
        }
    }

    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if (postId.isPresent() && userId.isPresent()){
            return likeRepository.findByPostIdAndUserId(postId.get(), userId.get());
        } else if (userId.isPresent()){
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()){
            return likeRepository.findByPostId(postId.get());
        } else {
            return likeRepository.findAll();
        }
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

}
