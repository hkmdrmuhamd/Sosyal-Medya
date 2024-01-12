package com.example.socialmedia.DTOs.request;

import lombok.Data;

@Data
public class CommentRequest {
    Long userId;
    Long postId;
    String text;
}
