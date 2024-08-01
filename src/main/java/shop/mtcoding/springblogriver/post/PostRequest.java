package shop.mtcoding.springblogriver.post;

import lombok.Data;

public class PostRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;

        Post toEntity() {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}
