package shop.mtcoding.springblogriver.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.springblogriver.user.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "chat_tb")
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @JsonIgnore // 채팅때문에 임시로
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt;

    @Builder
    public Chat(Integer id, String message, User sender, LocalDateTime createAt) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.createAt = createAt;
    }
}
