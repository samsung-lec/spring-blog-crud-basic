package shop.mtcoding.springblogriver.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import shop.mtcoding.springblogriver.bookmark.Bookmark;
import shop.mtcoding.springblogriver.reply.Reply;
import shop.mtcoding.springblogriver.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "post_tb")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // Cascade는 하지 않는다. 조회시에만 사용, 정방향으로만 데이터를 관리
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Bookmark> bookmarks = new ArrayList<>();

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Builder
    public Post(Integer id, String title, String content, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
