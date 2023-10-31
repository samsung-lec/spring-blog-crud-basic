package shop.mtcoding.springblogriver.post;

import org.springframework.data.domain.Page;
import shop.mtcoding.springblogriver.user.User;
import shop.mtcoding.springblogriver.user.UserResponse;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostResponse {
    public record DTO(Integer id, String title, String content, String createdAt, UserResponse.DTO user) {
        public DTO(Post post) {
            this(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), new UserResponse.DTO(post.getUser()));
        }
    }

    record SaveDTO(Integer id, String title, String content, String createdAt) {
        SaveDTO(Post post) {
            this(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        }
    }

    record PageDTO(boolean isFirst, boolean isLast, int pageNumber, int size, int totalPage, List<DTO> posts) {
        PageDTO(Page<Post> postPG) {
            this(postPG.isFirst(), postPG.isLast(), postPG.getNumber(), postPG.getSize(), postPG.getTotalPages(),
                    postPG.getContent().stream().map(DTO::new).toList());
        }
    }
}