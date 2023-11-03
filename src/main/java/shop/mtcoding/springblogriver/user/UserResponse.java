package shop.mtcoding.springblogriver.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.format.DateTimeFormatter;

public class UserResponse {

    public record DTO(Integer id, String username, String imgUrl) {
        public DTO(User user) {
            this(user.getId(), user.getUsername(), user.getImgUrl());
        }
    }

    public record DetailDTO(Integer id, String username, String email, String imgUrl, String createdAt, String updatedAt) {
        public DetailDTO(User user) {
            this(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getImgUrl(),
                    user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
            );
        }
    }

    // jwt는 service -> controller 넘어갈 때만 사용
    record LoginDTO(@JsonIgnore String jwt, Integer id, String username, String imgUrl) {
        LoginDTO(String jwt, User user) {
            this(jwt, user.getId(), user.getUsername(), user.getImgUrl());
        }
    }
}