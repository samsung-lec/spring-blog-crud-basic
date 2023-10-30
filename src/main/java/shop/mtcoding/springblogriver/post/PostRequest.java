package shop.mtcoding.springblogriver.post;

import shop.mtcoding.springblogriver.user.User;

public class PostRequest {
    record SaveDTO(String title, String content) {
        Post toEntity(User sessionUser){
            return Post.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)
                    .build();
        }
    }

    record UpdateDTO(String title, String content) {}
}
