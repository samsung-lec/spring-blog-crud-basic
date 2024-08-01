package shop.mtcoding.springblogriver.post;

import java.time.format.DateTimeFormatter;

public class PostResponse {
    public record DTO(Integer id, String title) {
        public DTO(Post post) {
            this(
                    post.getId(),
                    post.getTitle()
            );
        }
    }

    public record DetailDTO(Integer id, String title, String content, String createdAt, String updatedAt) {
        public DetailDTO(Post post) {
            this(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            );
        }
    }
}