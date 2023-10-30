package shop.mtcoding.springblogriver.user;

import java.time.format.DateTimeFormatter;

public class UserResponse {
    record DTO(Integer id, String username, String email, String imgUrl, String createdAt) {
        DTO(User user) {
            this(user.getId(), user.getUsername(), user.getEmail(), user.getImgUrl(), user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        }
    }
}