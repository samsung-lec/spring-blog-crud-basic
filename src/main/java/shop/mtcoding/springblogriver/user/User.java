package shop.mtcoding.springblogriver.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String imgUrl;

    @JsonIgnore // 채팅때문에 임시로
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore// 채팅때문에 임시로
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public User(Integer id, String username, String password, String email, String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
}
