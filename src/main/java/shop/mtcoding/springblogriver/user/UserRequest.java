package shop.mtcoding.springblogriver.user;

public class UserRequest {
    record JoinDTO(
            String username,
            String password,
            String email,
            String imgBase64) {
        User toEntity(String encPassword, String imgUrl){
            return User.builder()
                    .username(username)
                    .password(encPassword)
                    .email(email)
                    .imgUrl(imgUrl)
                    .build();
        }
    }

    record LoginDTO(String username, String password) {}
    record PasswordUpdateDTO(String password) {}
    record ImgBase64UpdateDTO(String imgBase64) {}
}
