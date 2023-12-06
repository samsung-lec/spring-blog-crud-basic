package shop.mtcoding.springblogriver.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
        private String imgBase64;

        User toEntity(String encPassword, String imgUrl) {
            return User.builder()
                    .username(username)
                    .password(encPassword)
                    .email(email)
                    .imgUrl(imgUrl)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    public record PasswordUpdateDTO(String password) {}
    public record ImgBase64UpdateDTO(String imgBase64) {}
}
