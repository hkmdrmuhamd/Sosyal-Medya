package com.example.socialmedia.Services;

import com.example.socialmedia.DTOs.request.CommentRequest;
import com.example.socialmedia.Entities.Comment;
import com.example.socialmedia.Entities.Post;
import com.example.socialmedia.Entities.User;
import com.example.socialmedia.Repos.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Comment createOneComment(CommentRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user != null && post != null){
            Comment commentToSave = new Comment();
            commentToSave.setText(request.getText());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            return commentRepository.save(commentToSave);
        } else {
            return null;
        }
    }

    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        if (postId.isPresent() && userId.isPresent()){
            return commentRepository.findByPostIdAndUserId(postId.get(), userId.get());
        } else if (userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment updateOneComment(Long commentId, CommentRequest commentRequest) {
        Optional<Comment> foundComment = Optional.ofNullable(commentRepository.findById(commentId).orElse(null));
        if (foundComment.isPresent()){
            Comment updateComment = foundComment.get();
            updateComment.setText(commentRequest.getText());
            commentRepository.save(updateComment);
            return updateComment;
        } else {
            return null;
        }
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
