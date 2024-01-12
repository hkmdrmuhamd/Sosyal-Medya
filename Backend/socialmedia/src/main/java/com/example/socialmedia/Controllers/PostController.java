package com.example.socialmedia.Controllers;

import com.example.socialmedia.DTOs.request.PostCreateRequest;
import com.example.socialmedia.DTOs.response.PostResponse;
import com.example.socialmedia.Entities.Post;
import com.example.socialmedia.Services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest request){
        return postService.createOnePost(request);
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){ //Bu bir optional parametre. Eğer userId verilirse ona göre filtreleme yapacak.
        //Eğer userId verilmezse tüm postları getirecek.
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostCreateRequest request){
        return postService.updateOnePost(postId, request);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePost(postId);
    }
}
