package shop.mtcoding.springblogriver.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.format.DateTimeFormatter;

public class UserResponse {
    // jwt는 service -> controller 넘어갈 때만 사용
    public record DTO(Integer id, String username, String email, String imgUrl, String createdAt) {
        public DTO(User user) {
            this(user.getId(), user.getUsername(), user.getEmail(), user.getImgUrl(), user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        }
    }

    record LoginDTO(@JsonIgnore String jwt, Integer id, String username, String email, String imgUrl, String createdAt) {
        LoginDTO(String jwt, User user) {
            this(jwt, user.getId(), user.getUsername(), user.getEmail(), user.getImgUrl(), user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        }
    }
}