package com.example.socialmedia.DTOs.response;

import com.example.socialmedia.Entities.Like;
import com.example.socialmedia.Entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<LikeResponse> postLikes; //Bu postun aldığı like'ları tutacak.

    public PostResponse(Post entity, List<LikeResponse> likes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes = likes;
    }
}
