package shop.mtcoding.springblogriver.user;

import java.time.format.DateTimeFormatter;

public class UserResponse {
    record DTO(Integer id, String username, String email, String imgBase64, String createdAt) {
        DTO(User user) {
            this(user.getId(), user.getUsername(), user.getEmail(), user.getImgBase64(), user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        }
    }
}