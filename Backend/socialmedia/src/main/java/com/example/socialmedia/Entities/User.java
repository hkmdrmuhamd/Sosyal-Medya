package com.example.socialmedia.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String userName;

    private String password;
}
