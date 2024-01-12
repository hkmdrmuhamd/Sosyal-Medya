package com.example.socialmedia.DTOs.request;

import lombok.Data;

@Data
public class PostCreateRequest {
    String text;
    Long userId;
    String title;
}
