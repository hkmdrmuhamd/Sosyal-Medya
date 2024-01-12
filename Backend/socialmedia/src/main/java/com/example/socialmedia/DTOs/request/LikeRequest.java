package com.example.socialmedia.DTOs.request;

import lombok.Data;

@Data
public class LikeRequest {
    private Long postId;
    private Long userId;
    private Long commentId;
}
