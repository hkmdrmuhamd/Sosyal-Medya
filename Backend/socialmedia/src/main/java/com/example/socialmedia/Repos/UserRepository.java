package com.example.socialmedia.Repos;

import com.example.socialmedia.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
