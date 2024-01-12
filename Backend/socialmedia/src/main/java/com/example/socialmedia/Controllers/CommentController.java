package com.example.socialmedia.Controllers;

import com.example.socialmedia.DTOs.request.CommentRequest;
import com.example.socialmedia.Entities.Comment;
import com.example.socialmedia.Services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentRequest request){
        return commentService.createOneComment(request);
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId , @RequestParam Optional<Long> postId){
        return commentService.getAllComments(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest){
        return commentService.updateOneComment(commentId, commentRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneComment(commentId);
    }
}
