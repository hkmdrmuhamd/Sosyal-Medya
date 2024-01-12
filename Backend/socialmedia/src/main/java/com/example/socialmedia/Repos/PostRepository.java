package com.example.socialmedia.Repos;

import com.example.socialmedia.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId); //Bu metot bize Jpa tarafından sağlanan bir metot.
    // Bu metot sayesinde userId'ye göre filtreleme yapabileceğiz. Jpa içerisinde findBy metodu var bunla userId birleşip userId'ye göre filtreleme yapacak.
}
