package com.example.socialmedia.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="post")
@Data
public class Post  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) //EAGER ile ilişkili tablodaki verileri de çeker
    @JoinColumn(name="user_id", nullable = false) //user_id kolonuna bağlan anlamında kullanılır
    @OnDelete(action = OnDeleteAction.CASCADE) //Bir user silindiğinde onun postları da silinmelidir. Bu sebep ile kullanılır.
    User user;

    private String title;

    @Lob //String türüne uygulandığında, JPA belirtimine göre, kalıcı sağlayıcının öznitelik değerini saklamak için büyük bir karakter türü
    // nesnesini kullanması gerektiğini belirtir. Bu durumda, SQL bir karakter lobunu bir TEXT türüne çevirebilir
    @Column(columnDefinition = "text")
    private String text;
}
