package shop.mtcoding.springblogriver.post;

import lombok.Data;
import shop.mtcoding.springblogriver.user.User;

public class PostRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;

        Post toEntity(User sessionUser) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}
