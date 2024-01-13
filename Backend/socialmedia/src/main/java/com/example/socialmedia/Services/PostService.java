package com.example.socialmedia.Services;

import com.example.socialmedia.DTOs.request.PostCreateRequest;
import com.example.socialmedia.DTOs.response.LikeResponse;
import com.example.socialmedia.DTOs.response.PostResponse;
import com.example.socialmedia.Entities.Like;
import com.example.socialmedia.Entities.Post;
import com.example.socialmedia.Entities.User;
import com.example.socialmedia.Repos.LikeRepository;
import com.example.socialmedia.Repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Autowired //Burada farklı bir costurctor oluşturduk çünkü üstteki constructor'ı kullanırsak döngü oluşuyor sürekli birbirini çağırıyor.
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()){ //Eğer userId verilmişse ona göre filtreleme yapacak.
            list = postRepository.findByUserId(userId.get());
        } else {
            list = postRepository.findAll();
        }
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikes(Optional.ofNullable(null), Optional.of(p.getId())); //Bu post'a ait like'ları getiriyoruz.
            return new PostResponse(p,likes);
        }).collect(Collectors.toList()); //Bu satırda listeyi PostResponse'a çeviriyoruz.
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        if (user == null){
            return null;
        } else {
            Post post = new Post();
            post.setText(request.getText());
            post.setTitle(request.getTitle());
            post.setUser(user);
            return postRepository.save(post);
        }
    }

    public Post updateOnePost(Long postId, PostCreateRequest request) {
        Optional<Post> post = Optional.ofNullable(postRepository.findById(postId).orElse(null));
        if (post.isPresent()){
            Post foundPost = post.get();
            foundPost.setText(request.getText());
            foundPost.setTitle(request.getTitle());
            postRepository.save(foundPost);
            return foundPost;
        } else {
            return null;
        }
    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
