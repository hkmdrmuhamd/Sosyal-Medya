package com.example.socialmedia.Controllers;

import com.example.socialmedia.DTOs.request.LikeRequest;
import com.example.socialmedia.DTOs.response.LikeResponse;
import com.example.socialmedia.Entities.Like;
import com.example.socialmedia.Services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeRequest likeRequest) {
        return likeService.createOneLike(likeRequest);
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return likeService.getAllLikes(userId, postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLikeById(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLikeById(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }

}
